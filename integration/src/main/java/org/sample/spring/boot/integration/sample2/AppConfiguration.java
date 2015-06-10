/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.sample.spring.boot.integration.sample2;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.EnricherSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.support.Consumer;
import org.springframework.integration.dsl.support.Function;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.messaging.Message;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@Configuration
@EnableAutoConfiguration
@IntegrationComponentScan
@ImportResource("application-context.xml")
public class AppConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(ThreadedUpperCaseFlowApp.class);	

	@MessagingGateway
	public interface Upcase {

		@Gateway(requestChannel = "upcase.input")
		Collection<String> upcase(Collection<String> strings);
	}	

	@Bean
	public IntegrationFlow upcase() {
		return IntegrationFlows
					.from("upcase.input")
					.enrich(enricher())
					.split()
					.channel(MessageChannels.executor(executorService()).get())
					.transform("payload.toUpperCase()")
					.handle(randomDelayHandler())
					.aggregate()
					.get();
	}

	@Bean
	public GenericHandler<String> randomDelayHandler() {
		return new GenericHandler<String>() {
			@Override
			public Object handle(String payload, Map<String, Object> headers) {
				long sleep = (long) (Math.random()*1000);
				try {
					log.info("Sleeping for: {}", sleep);
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					log.error("Interrupted: ", e);
				}
				return payload;
			}
		};
	}

	@Bean(destroyMethod = "shutdownNow")
	public ExecutorService executorService() {
		return Executors.newCachedThreadPool();
	}

	@Bean
	public Consumer<EnricherSpec> enricher() {
		return new Consumer<EnricherSpec>() {
			@Override
			public void accept(EnricherSpec spec) {
				spec.requestPayload(new Function<Message<Collection<String>>, Collection<String>>() {
					@Override
					public Collection<String> apply(Message<Collection<String>> t) {
						Collection<String> payload = t.getPayload();
						payload.add("Sample");
						return payload;
					}
				});
			}
		};
	}
	
	
}
