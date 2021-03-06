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
package org.sample.spring.boot.integration.sample3;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.splitter.DefaultMessageSplitter;
import org.springframework.messaging.MessageChannel;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@Configuration
@EnableAutoConfiguration
@IntegrationComponentScan
@ImportResource("application-context.xml")
public class IntegrationConfiguration {
	
	@Bean
	public MessageChannel upcase() {
		return MessageChannels.direct().get();
	}

	@Bean
	public IntegrationFlow upcaseFlow() {
		return IntegrationFlows
					.from("upcase")
					.split(splitter(), null)
					.transform("payload.toUpperCase()")
					.channel("nullChannel")
					.get();
	}

	@Bean
	public DefaultMessageSplitter splitter() {
		DefaultMessageSplitter splitter = new DefaultMessageSplitter();
		splitter.setDelimiters(" ");
		return splitter;
	}
}
