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

package com.bbva.kltt.apirest.generator.java.velocity;

import com.bbva.kltt.apirest.generator.java.interfaces.IOperationsRestInterfaceJava;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * Base class that gives the common methods for every Translator rest generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorJavaRestInterfaceBase extends TranslatorGeneratorBaseJava implements IOperationsRestInterfaceJava
{
	/**
	 * Constructor of the class
	 * @param translatorType	with the translator type
	 * @param generationParams  with the generation parameters information for java
	 * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
	 * @param packageName		with the package name
	 */
	public TranslatorGeneratorJavaRestInterfaceBase(final String translatorType,
													final GenerationParameters generationParams,
													final ParsedInfoHandler parsedInfoHandler,
													final String packageName)
	{
		super(translatorType, generationParams, parsedInfoHandler, packageName) ;
	}
	
	/**
	 * Generate the specific code
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	@Override
	public void generateSpecific() throws APIRestGeneratorException
	{
        // Generate the rest handler classes
		this.generateRestHandler(this.getSourceDir()) ;
		
		// Generate the rest handler utility classes
		this.generateRestHandlerUtils(this.getSourceDir()) ;
        
        // Generate the POM file
		this.generatePOM(this.getPomDir()) ;
		
		// Generate the specific Java Framework
		this.generateSpecificJavaFramework(this.getResourceDir()) ;
	}
	
	@Override
	public String getTranslatorTypeForProjectName()
	{
		return this.getTranslatorType() + ConstantsCommon.STRING_HYPHEN  ;
	}
	
	@Override
	public String getOutputCodeType()
	{
		return ConstantsOutput.OUTPUT_CODE_TYPE_REST + ConstantsCommon.STRING_HYPHEN + ConstantsOutput.OUTPUT_CODE_SUBTYPE_INTERF ;
	}
}
