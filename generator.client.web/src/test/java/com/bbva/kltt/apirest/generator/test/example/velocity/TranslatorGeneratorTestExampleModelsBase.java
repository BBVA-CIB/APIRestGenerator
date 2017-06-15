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

package com.bbva.kltt.apirest.generator.test.example.velocity;

import com.bbva.kltt.apirest.core.generator.interfaces.IOperationsModel;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;

/**
 * Base class that gives the common methods for every Translator generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorTestExampleModelsBase extends TranslatorGeneratorBaseTestExample implements IOperationsModel
{
	/**
	 * Constructor of the class
	 * @param generationParams  with the generation parameters information for the JavaScript
	 * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
	 */
	public TranslatorGeneratorTestExampleModelsBase(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
	{
		super(ConstantsOutputTestExample.MODULE_NAME, generationParams, parsedInfoHandler) ;
	}
	
	/**
	 * Generate the specific code
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void generateSpecific() throws APIRestGeneratorException
	{
		// Generate the model classes
		this.generateModels(this.getSourceDir()) ;
	}
}
