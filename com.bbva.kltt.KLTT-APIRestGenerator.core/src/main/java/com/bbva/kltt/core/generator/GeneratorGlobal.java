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

package com.bbva.kltt.core.generator;

import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.core.util.InvokeModule;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Global generator class, this class perform the whole code generation steps
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorGlobal
{
	/** Logger of the class */
	private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorGlobal.class) ;

	/** Parameters for the generation */
	private final GenerationParameters generationParams ;

	/** Information to generate the wrappers */
	private final ParsedInfoHandler parsedInfoHandler ;
	
	/**
	 * Constructs a new global generator
	 * @param genParams  		with the parameters for the generation
	 * @param parsedInfoHandler with the parsed information handler to generate from
	 */
	public GeneratorGlobal(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler)
	{
		this.generationParams  = genParams ;
		this.parsedInfoHandler = parsedInfoHandler ;
	}

	/**
	 * Start the generation of the code
	 * @param moduleName   with the module name
	 * @param className    with the name of the init generator class
	 * @throws APIRestGeneratorException exception thrown if there is a problem generating
	 */
	public void start(final String moduleName, final String className) throws APIRestGeneratorException
	{
		// Security checks
		String errorString = null ; 
		if (this.parsedInfoHandler == null)
		{
			errorString = "The parsed information provided is null" ;
		}
		else if (!this.parsedInfoHandler.hasInfoToGenerate())
		{
			errorString = "The parsed information does not contain any path to generate" ;
		} else if (moduleName == null || className == null)
		{
			errorString = "Module or init method name does not contain any data" ;
		}
		
		if (errorString != null)
		{
			GeneratorGlobal.LOGGER.error(errorString) ;
			throw new APIRestGeneratorException(errorString) ;
		}

		// Generate the the classes
		this.generate(moduleName, className) ;
	}
	
	/**
	 * Generate the code
	 * @param moduleName   with the name of the artifact
	 * @param className    with the name of the init generator class
	 * @throws APIRestGeneratorException exception thrown if there is any problem during the code generation
	 */
	private void generate(final String moduleName, final String className) throws APIRestGeneratorException
	{
		String packageName = new StringBuilder(ConstantsOutput.COMMON_GEN_TYPE_PACKAGE).append(".").append(moduleName).toString();

		InvokeModule invokeModule = InvokeModule.initInvokeModule(packageName, className);

		if (invokeModule != null) {
			Object[] params = {this.generationParams, this.parsedInfoHandler};
			Class<?>[] typeParams = {GenerationParameters.class, ParsedInfoHandler.class};

			final GeneratorGlobalLanguage globalGenerator = (GeneratorGlobalLanguage)invokeModule.createInstance(params, typeParams);
			globalGenerator.generate(moduleName);

		}
	}
}
