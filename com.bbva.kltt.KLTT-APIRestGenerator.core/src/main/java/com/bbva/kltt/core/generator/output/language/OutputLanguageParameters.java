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

package com.bbva.kltt.core.generator.output.language;

import com.bbva.kltt.core.generator.output.parameters.OutputParameter;
import com.bbva.kltt.core.generator.output.parameters.OutputParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class OutputLanguageParameters extends OutputLanguageBase implements IOutputLanguageParameters
{
	/** Output Language Separators */
	private final IOutputLanguageSeparators outputLangSeparators ;
	
	/**
	 * Public constructor
	 * @param parsedInfoHandler with the parsed information handler
	 * @param outputLangSeparators with the output language separators
	 */
	public OutputLanguageParameters(final ParsedInfoHandler parsedInfoHandler, final IOutputLanguageSeparators outputLangSeparators)
	{
		super(parsedInfoHandler) ;
		
		this.outputLangSeparators = outputLangSeparators ;
	}
	
	/**
	 * @return the outputLangSeparators
	 */
	public IOutputLanguageSeparators getOutputLangSeparators()
	{
		return this.outputLangSeparators ;
	}
	
	@Override
	public OutputParameters generateAllOutputParameters(final String pathValue, final String pathOperation)
	{
		final List<Parameter> parametersList		= this.getParametersList(pathValue, pathOperation) ;
		final List<OutputParameter> outputParamList = this.generateOutputParameterList(parametersList) ;
		return this.createNewOutputParameters(outputParamList)  ;
	}
	
	@Override
	public OutputParameters generateOutputParameterBody(final String pathValue, final String pathOperation)
	{
		final List<Parameter> parametersList		= this.getParameterListBody(pathValue, pathOperation) ;
		final List<OutputParameter> outputParamList = this.generateOutputParameterList(parametersList) ;
		return this.createNewOutputParameters(outputParamList)  ;
	}
	
	@Override
	public OutputParameters generateOutputParametersFormData(final String pathValue, final String pathOperation)
	{
		final List<Parameter> parametersList		= this.getParametersListFormData(pathValue, pathOperation) ;
		final List<OutputParameter> outputParamList = this.generateOutputParameterList(parametersList) ;
		return this.createNewOutputParameters(outputParamList)  ;
	}
	
	@Override
	public OutputParameters generateOutputParametersHeader(final String pathValue, final String pathOperation)
	{
		final List<Parameter> parametersList		= this.getParametersListHeader(pathValue, pathOperation) ;
		final List<OutputParameter> outputParamList = this.generateOutputParameterList(parametersList) ;
		return this.createNewOutputParameters(outputParamList)  ;
	}
	
	@Override
	public OutputParameters generateOutputParametersPath(final String pathValue, final String pathOperation)
	{
		final List<Parameter> parametersList		= this.getParametersListPath(pathValue, pathOperation) ;
		final List<OutputParameter> outputParamList = this.generateOutputParameterList(parametersList) ;
		return this.createNewOutputParameters(outputParamList)  ;
	}
	
	@Override
	public OutputParameters generateOutputParametersQuery(final String pathValue, final String pathOperation)
	{
		final List<Parameter> parametersList		= this.getParametersListQuery(pathValue, pathOperation) ;
		final List<OutputParameter> outputParamList = this.generateOutputParameterList(parametersList) ;
		return this.createNewOutputParameters(outputParamList)  ;
	}
	
	/**
	 * @param pathValue     with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with all the parameters
	 */
	private List<Parameter> getParametersList(final String pathValue, final String pathOperation)
	{
		final List<Parameter> outcome = new ArrayList<Parameter>() ;

		// Add the body parameter
		outcome.addAll(this.getParameterListBody(pathValue, pathOperation)) ;

		// Add all the form data parameters
		outcome.addAll(this.getParametersListFormData(pathValue, pathOperation)) ;
		
		// Add all the header parameters
		outcome.addAll(this.getParametersListHeader(pathValue, pathOperation)) ;

		// Add all the path parameters
		outcome.addAll(this.getParametersListPath(pathValue, pathOperation)) ;
		
		// Add all the query parameters
		outcome.addAll(this.getParametersListQuery(pathValue, pathOperation)) ;
		
		return outcome ;
	}
	
	@Override
	public List<Parameter> getParameterListBody(final String pathValue, final String pathOperation)
	{
		final List<Parameter> outcome = new ArrayList<Parameter>() ;
		
		final Parameter parameterBody = this.getParsedInfoHandler().getParameterBody(pathValue, pathOperation) ;
		if (parameterBody != null)
		{
			outcome.add(parameterBody) ;
		}
		
		return outcome ;
	}
	
	@Override
	public List<Parameter> getParametersListFormData(final String pathValue, final String pathOperation)
	{
		final List<Parameter> outcome = new ArrayList<Parameter>() ;
		
		final Map<String, Parameter> paramsFormDataMap = this.getParsedInfoHandler().getParametersFormDataMap(pathValue, pathOperation) ;
		if (paramsFormDataMap != null)
		{
			outcome.addAll(paramsFormDataMap.values()) ;
		}
		
		return outcome ;
	}
	
	@Override
	public List<Parameter> getParametersListHeader(final String pathValue, final String pathOperation)
	{
		final List<Parameter> outcome = new ArrayList<Parameter>() ;
		
		final Map<String, Parameter> paramsHeaderMap = this.getParsedInfoHandler().getParametersHeaderMap(pathValue, pathOperation) ;
		if (paramsHeaderMap != null)
		{
			outcome.addAll(paramsHeaderMap.values()) ;
		}
		
		return outcome ;
	}
	
	@Override
	public List<Parameter> getParametersListPath(final String pathValue, final String pathOperation)
	{
		final List<Parameter> outcome = new ArrayList<Parameter>() ;
		
		final Map<String, Parameter> paramsPathMap = this.getParsedInfoHandler().getParametersPathMap(pathValue, pathOperation) ;
		if (paramsPathMap != null)
		{
			outcome.addAll(paramsPathMap.values()) ;
		}
		
		return outcome ;
	}
	
	@Override
	public List<Parameter> getParametersListQuery(final String pathValue, final String pathOperation)
	{
		final List<Parameter> outcome = new ArrayList<Parameter>() ;
		
		final Map<String, Parameter> paramsQueryMap = this.getParsedInfoHandler().getParametersQueryMap(pathValue, pathOperation) ;
		if (paramsQueryMap != null)
		{
			outcome.addAll(paramsQueryMap.values()) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param parameters with a list of parameters
	 * @return a new list with the output parameters
	 */
	private List<OutputParameter> generateOutputParameterList(List<Parameter> parameters)
	{
		final List<OutputParameter> outputParamList = new ArrayList<OutputParameter>() ;
		
		for (final Parameter parameter : parameters)
		{
			outputParamList.add(this.createNewOutputParameter(parameter)) ;
		}
		
		return outputParamList ;
	}
	
	/**
	 * @param attributeName with the attribute name (it is usually the alias, but there is a case like Jaxrs that it is the name) 
	 * @return the annotation parameters 
	 */
	protected String getAnnotationParams(final String attributeName)
	{
		final StringBuffer outcome = new StringBuffer() ;
		
		if (attributeName != null && !attributeName.isEmpty())
		{
			outcome.append(ConstantsOutput.COMMON_ANNOTATION_ALIAS_PAR) ;
			outcome.append(ConstantsCommon.STRING_EQUAL) ;
			outcome.append(ConstantsCommon.STRING_DOUBLE_QUOTE) ;
			outcome.append(attributeName) ;
			outcome.append(ConstantsCommon.STRING_DOUBLE_QUOTE) ;
		}
		
		return outcome.toString() ;
	}
}
