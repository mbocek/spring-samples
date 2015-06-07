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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.sample.spring.boot.integration.sample1.AppConfiguration.Upcase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);	

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AppConfiguration.class, args);
		ctx.registerShutdownHook();
		
		List<String> strings = Arrays.asList("foo", "bar");
		log.info("Transformed strings: {}", ctx.getBean(Upcase.class).upcase(strings));
	}
}
