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
package org.sample.spring.boot.integration.sample1;

import java.util.Collection;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@Configuration
@EnableAutoConfiguration
@IntegrationComponentScan
public class AppConfiguration {
	
	@MessagingGateway
	public interface Upcase {

		@Gateway(requestChannel = "upcase.input")
		Collection<String> upcase(Collection<String> strings);
	}	

	@Bean
	public IntegrationFlow upcase() {
		return IntegrationFlows
					.from("upcase.input")
					.split()
					.transform("payload.toUpperCase()")
					.aggregate()
					.get();
	}
}
