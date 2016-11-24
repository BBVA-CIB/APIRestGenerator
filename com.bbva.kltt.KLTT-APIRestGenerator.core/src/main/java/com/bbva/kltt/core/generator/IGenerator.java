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

import com.bbva.kltt.core.generator.output.language.*;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.util.APIRestGeneratorException;

/**
 * Base interface for any code generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IGenerator
{
	/**
	 * Generates the code, this is the entry point of the generator
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	void generate() throws APIRestGeneratorException ;
	
	/**
	 * @return the generation parameters
	 */
	GenerationParameters getGenerationParams() ;
	
	/**
	 * @return the Output Language Items
	 */
	IOutputLanguageItems getOutputLanguageItems() ;

	/**
	 * @return the Output Language Parameters
	 */
	IOutputLanguageParameters getOutputLanguageParameters() ;

	/**
	 * @return the Output Language Consumes Produces
	 */
	IOutputLanguageConsumesProduces getOutputLanguageConsProd() ;

	/**
	 * @return the Output Language Naming
	 */
	IOutputLanguageNaming getOutputLanguageNaming() ;
	
	/**
	 * @return the Output Language Generator properties parameters
	 */
	IOutputLanguageGeneratorParams getOutputLanguageGeneratorParams() ;
	
	/**
	 * @return the Output Language Root values
	 */
	IOutputLanguageRootValues getOutputLanguageRootValues() ;
	
	/**
	 * @return the Output Language Info values
	 */
	IOutputLanguageInfoValues getOutputLanguageInfoValues() ;
	
	/**
	 * @return the Output Language Paths
	 */
	IOutputLanguagePaths getOutputLanguagePaths() ;

	/**
	 * @return the Output Language Operations
	 */
	IOutputLanguageOperations getOutputLanguageOperations() ;
}
