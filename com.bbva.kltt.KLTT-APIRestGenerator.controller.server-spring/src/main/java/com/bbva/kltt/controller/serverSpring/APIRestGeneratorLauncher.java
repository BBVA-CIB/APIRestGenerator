/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.bbva.kltt.controller.serverSpring;

import com.bbva.kltt.core.web.IAPIRestGeneratorService;
import com.bbva.kltt.core.web.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
@SpringBootApplication
public class APIRestGeneratorLauncher implements CommandLineRunner
{
	@Autowired
	public IAPIRestGeneratorService apiRestGenService ;
	
    public static void main(String[] args)
    {
        SpringApplication.run(APIRestGeneratorLauncher.class, args);
    }
	
	@Override
	public void run(String... args) throws Exception
	{

		if (args != null && args.length > 0)
		{
			final String contentFile 	= new String(Utilities.readFileContent(args[0])) ;
			final Properties properties = Utilities.parsePropertiesString(contentFile) ;
			this.apiRestGenService.processProperties(properties) ;
		}
	}
}
