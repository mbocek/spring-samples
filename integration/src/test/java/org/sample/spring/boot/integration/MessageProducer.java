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
package org.sample.spring.boot.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.utils.TestUtils;

import org.junit.Test;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
public class MessageProducer {

	@Test
	public void sendMessage() {
		Properties properties = TestUtils.getProducerConfig("localhost:" + 9092);

		ProducerConfig pConfig = new ProducerConfig(properties);
		Producer<String, String> producer = new Producer<String, String>(pConfig);
		KeyedMessage<String, String> data = new KeyedMessage<String, String>("sample", "test");
		producer.send(data);
		producer.close();
	}
}