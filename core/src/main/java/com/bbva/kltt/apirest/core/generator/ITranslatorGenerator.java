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

package com.bbva.kltt.apirest.core.generator;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

import java.io.File;

/**
 * Base interface for any translators generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface ITranslatorGenerator
{
	/**
	 * Entry point for the generation, it will generate all the translators, tests and auxiliar classes
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	void generate() throws APIRestGeneratorException ;
	
	/**
	 * @return the translator type
	 */
	String getTranslatorType() ;

	/**
	 * @return the translator type
	 */
	String getGenerationPackage() ;
	
	/**
	 * @return the generationParams
	 */
	GenerationParameters getGenerationParams() ;
	
	/**
	 * Add the specification file
	 *
	 * @param destDir a {@link File} instance with the information of the destination directory
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	void addSourceSpecificationFile(final File destDir) throws APIRestGeneratorException ;
	
	/**
	 * Add the parsed info file
	 *
	 * @param destDir a {@link File} instance with the information of the destination directory
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	void addParsedInfoFile(final File destDir) throws APIRestGeneratorException ;
	
	/**
	 * Add the generator project information
	 *
	 * @param destDir a {@link File} instance with the information of the destination directory
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	void addGeneratorInfo(final File destDir) throws APIRestGeneratorException ;
	
	/**
	 * @return the source directory for generated code
	 */
	File getSourceDir() ;
	
	/**
	 * @return the resource directory for generated code
	 */
	File getResourceDir() ;
}
