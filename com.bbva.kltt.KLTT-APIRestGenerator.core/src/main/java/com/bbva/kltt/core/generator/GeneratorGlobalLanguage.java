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
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorGlobalLanguage
{
	/** Parameters for the generation */
	private final GenerationParameters generationParams ;

	/** Information to generate the wrappers */
	private final ParsedInfoHandler parsedInfoHandler ;

	/** Path to the resources vm files */
	private String generationPackage ;

	/**
	 * Constructs a new global generator for Java
	 * @param genParams  		with the parameters for the generation for java
	 * @param parsedInfoHandler with the parsed information handler to generate from
	 */
	public GeneratorGlobalLanguage(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler,
								   final String generationPackage)
	{
		this.generationParams  = genParams ;
		this.parsedInfoHandler = parsedInfoHandler ;
		this.generationPackage = generationPackage ;
	}

	/**
	 * @return the generationPackage
	 */
	public String getGenerationPackage()
	{
		return this.generationPackage ;
	}

	/**
	 * @return the generationParams
	 */
	public GenerationParameters getGenerationParams()
	{
		return this.generationParams ;
	}
	
	/**
	 * @return the parsedInfoHandler
	 */
	public ParsedInfoHandler getParsedInfoHandler()
	{
		return this.parsedInfoHandler ;
	}
	
	/**
	 * @param translatorType with the translator type
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public abstract void generate(final String translatorType) throws APIRestGeneratorException ;

}
