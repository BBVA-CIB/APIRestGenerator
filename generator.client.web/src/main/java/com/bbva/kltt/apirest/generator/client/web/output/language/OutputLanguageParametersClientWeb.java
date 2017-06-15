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

package com.bbva.kltt.apirest.generator.client.web.output.language;

import java.util.ArrayList;
import java.util.List;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageSeparators;
import com.bbva.kltt.apirest.core.generator.output.language.OutputLanguageParameters;
import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParameter;
import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class OutputLanguageParametersClientWeb extends OutputLanguageParameters
{
	/**
	 * Public constructor
	 * @param parsedInfoHandler    with the parsed information handler
	 * @param outputLangSeparators with the output language separators
	 */
	public OutputLanguageParametersClientWeb(final ParsedInfoHandler parsedInfoHandler, final IOutputLanguageSeparators outputLangSeparators)
	{
		super(parsedInfoHandler, outputLangSeparators) ;
	}
	
	@Override
	public OutputParameters generateAllOutputParameters(final String pathValue, final String pathOperation)
	{
		// Firstly, get all the  parameters (no filtered)
		final List<Parameter> parametersListNoFiltered = this.getParametersList(pathValue, pathOperation) ;
		
		// Secondly, filter all the 'auto-injected' parameters
		final List<Parameter> parametersListFiltered   = this.filterNoAutoInjectedParameters(parametersListNoFiltered) ;
		
		// Finally, generate the 'OutputParameters' instance
		final List<OutputParameter> outputParamList    = this.generateOutputParameterList(parametersListFiltered) ;
		return this.createNewOutputParameters(outputParamList)  ;
	}
	
	@Override
	public OutputParameters generateOutputParametersHeader(final String pathValue, final String pathOperation)
	{
		// Firstly, get all the header parameters (no filtered)
		final List<Parameter> parametersListNoFiltered = this.getParametersListHeader(pathValue, pathOperation) ;
		
		// Secondly, filter all the 'auto-injected' parameters
		final List<Parameter> parametersListFiltered   = this.filterNoAutoInjectedParameters(parametersListNoFiltered) ;
		
		// Finally, generate the 'OutputParameters' instance
		final List<OutputParameter> outputParamList    = this.generateOutputParameterList(parametersListFiltered) ;
		return this.createNewOutputParameters(outputParamList)  ;
	}
	
	/**
	 * @param pathValue	    with the path value 
	 * @param pathOperation with the path operation
	 * @return
	 */
	private List<Parameter> filterNoAutoInjectedParameters(final List<Parameter> parametersListNoFiltered)
	{
		// Get all the headers parameters and filter (no auto-injected parameters)
		final List<Parameter> parametersListFiltered   = new ArrayList<Parameter>() ;
		
		for (final Parameter parameter : parametersListNoFiltered)
		{
			if (!parameter.isAutoInjected())
			{
				parametersListFiltered.add(parameter) ;
			}
		}
		
		return parametersListFiltered ;
	}
}
