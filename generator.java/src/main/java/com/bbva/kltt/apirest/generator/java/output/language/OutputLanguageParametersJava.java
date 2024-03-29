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

package com.bbva.kltt.apirest.generator.java.output.language;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.OutputLanguageParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class OutputLanguageParametersJava extends OutputLanguageParameters
{
	/** Attribute - IOutputLanguageItems */
	private final IOutputLanguageItems outputLanguageItems ;
	
	/**
	 * Public constructor
	 * @param parsedInfoHandler   with the parsed information handler
	 * @param outputLanguageItems with the output language items
	 */
	public OutputLanguageParametersJava(final ParsedInfoHandler parsedInfoHandler, final IOutputLanguageItems outputLanguageItems)
	{
		super(parsedInfoHandler, new OutputLanguageSeparatorsJava()) ;
		
		this.outputLanguageItems = outputLanguageItems ;
	}
	
	/**
	 * @return the outputLanguageItems
	 */
	public IOutputLanguageItems getOutputLanguageItems()
	{
		return this.outputLanguageItems ;
	}
}
