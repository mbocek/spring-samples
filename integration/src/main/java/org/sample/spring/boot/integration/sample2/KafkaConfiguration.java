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

import javax.inject.Inject;

import kafka.serializer.StringDecoder;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.kafka.core.ConnectionFactory;
import org.springframework.integration.kafka.core.DefaultConnectionFactory;
import org.springframework.integration.kafka.core.ZookeeperConfiguration;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.integration.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.integration.kafka.support.ZookeeperConnect;
import org.springframework.messaging.MessageChannel;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@Configuration
@EnableAutoConfiguration
@IntegrationComponentScan
public class KafkaConfiguration {

	@Inject
	private MessageChannel upcase;
	
	@Bean
	public org.springframework.integration.kafka.core.Configuration zkConfiguration() {
	   return new ZookeeperConfiguration(new ZookeeperConnect());
	}

	@Bean
	public ConnectionFactory kafkaConnectionFactory() {
	   return new DefaultConnectionFactory(zkConfiguration());
	}

	@Bean
	public MessageProducer kafkaMessageDrivenChannelAdapter() {
		KafkaMessageDrivenChannelAdapter adapter = new KafkaMessageDrivenChannelAdapter(
				new KafkaMessageListenerContainer(kafkaConnectionFactory(), "sample"));
	   adapter.setOutputChannel(upcase);
	   adapter.setPayloadDecoder(new StringDecoder(null));
	   return adapter;
	}
}
