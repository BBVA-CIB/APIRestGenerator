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

package com.bbva.kltt.generator.clientTypescript.typescript.output.language;

import com.bbva.kltt.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.core.generator.output.language.OutputLanguageParameters;
import com.bbva.kltt.core.generator.output.parameters.OutputParameter;
import com.bbva.kltt.core.generator.output.parameters.OutputParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.generator.clientTypescript.typescript.output.parameters.OutputParameterTypescript;
import com.bbva.kltt.generator.clientTypescript.typescript.output.parameters.OutputParametersTypescript;

import java.util.List;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageParametersTypescript extends OutputLanguageParameters
{
	/** Attribute - IOutputLanguageItems */
	private final IOutputLanguageItems outputLanguageItems ;
	
	/**
	 * Public constructor
	 * @param parsedInfoHandler with the parsed information handler
	 */
	public OutputLanguageParametersTypescript(final ParsedInfoHandler parsedInfoHandler)
	{
		super(parsedInfoHandler, new OutputLanguageSeparatorsTypescript()) ;
		
		this.outputLanguageItems = new OutputLanguageItemsTypescript(parsedInfoHandler) ;
	}
	
	@Override
	public OutputParameters createNewOutputParameters(final List<OutputParameter> outputParamList)
	{
		return new OutputParametersTypescript(this.getOutputLangSeparators(), outputParamList) ;
	}
	
	@Override
	public OutputParameter createNewOutputParameter(final Parameter parameter)
	{
		final String type = this.outputLanguageItems.getFullTypeOutput(parameter.getItem()) ;
		
		return new OutputParameterTypescript(parameter.getItem(), parameter.getDescription(), type, parameter.getName()) ;
	}
}
