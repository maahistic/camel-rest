/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.camel;

import java.util.TimeZone;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A Camel route that calls the REST service using a timer
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class RestRoute extends RouteBuilder {

	@Autowired
	Processor processor;


	@Override
	public void configure() throws Exception {

		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

		//http://localhost:8080/camel/rest/mahi to see the result
		
		rest().path("/rest").consumes("application/json").produces("application/json")
				.get("{name}")
				.to("bean:serviceBean?method=hello(${headers.name})")

				.post().type(User.class).outType(User.class)  
				.to("bean:serviceBean?method=response")
		
				.get("log").to("direct:log");
		
		from("direct:log")
				.process(processor)
		.log("The log message");
		
		
		} 
		 

}
