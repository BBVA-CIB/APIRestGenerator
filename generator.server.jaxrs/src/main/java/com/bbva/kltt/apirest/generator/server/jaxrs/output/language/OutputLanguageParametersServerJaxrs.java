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

package com.bbva.kltt.apirest.generator.server.jaxrs.output.language;

import java.util.List;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParameter;
import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterBody;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterFormData;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterHeader;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterPath;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterQuery;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.output.language.OutputLanguageParametersJava;
import com.bbva.kltt.apirest.generator.server.jaxrs.output.parameters.OutputParameterServerJaxrs;
import com.bbva.kltt.apirest.generator.server.jaxrs.output.parameters.OutputParametersServerJaxrs;
import com.bbva.kltt.apirest.generator.server.jaxrs.util.ConstantsOutputServerJaxrs;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageParametersServerJaxrs extends OutputLanguageParametersJava
{
	/**
	 * Public constructor
	 * @param parsedInfoHandler   with the parsed information handler
	 * @param outputLanguageItems with the output language items
	 */
	public OutputLanguageParametersServerJaxrs(final ParsedInfoHandler parsedInfoHandler, final IOutputLanguageItems outputLanguageItems)
	{
		super(parsedInfoHandler, outputLanguageItems) ;
	}
	
	@Override
	public OutputParameters createNewOutputParameters(final List<OutputParameter> outputParamList)
	{
		return new OutputParametersServerJaxrs(this.getOutputLangSeparators(), outputParamList) ;
	}
	
	@Override
	public OutputParameter createNewOutputParameter(final Parameter parameter)
	{
		final String type		= this.getTypeOutputFile(parameter) ;
		final String annotation = this.getAnnotation(parameter) ;
		
		return new OutputParameterServerJaxrs(parameter.getItem(), parameter.getDescription(), type, parameter.getName(), annotation) ;
	}
	
	/**
	 * @param parameter with the parameter
	 * @return the annotation
	 */
	private String getAnnotation(final Parameter parameter)
	{
		final StringBuilder annotation = new StringBuilder() ;
		
		if (parameter instanceof ParameterBody)
		{
			annotation.append(ConstantsOutputServerJaxrs.JAXRS_ANN_REQUEST_BODY) ;
		}
		else
		{
			if (parameter instanceof ParameterFormData)
			{
				annotation.append(ConstantsOutputServerJaxrs.JAXRS_ANN_FORM_DATA_PARAM) ;
			}
			else if (parameter instanceof ParameterHeader)
			{
				annotation.append(ConstantsOutputServerJaxrs.JAXRS_ANN_REQUEST_HEADER) ;
			}
			else if (parameter instanceof ParameterPath)
			{
				annotation.append(ConstantsOutputServerJaxrs.JAXRS_ANN_PATH_VARIABLE) ;
			}
			else if (parameter instanceof ParameterQuery)
			{
				annotation.append(ConstantsOutputServerJaxrs.JAXRS_ANN_REQUEST_PARAM) ;
			}
			
			// Get the "value" inside the annotation
			this.setValueAnnotation(parameter, annotation) ;
		}
		
		return annotation.toString() ;
	}
	
	/**
	 * @param parameter  with the parameter
	 * @param annotation with the annotation
	 */
	private void setValueAnnotation(final Parameter parameter, final StringBuilder annotation)
	{
		String attributeName = parameter.getName() ;
		if (parameter.getAlias() != null && !parameter.getAlias().isEmpty())
		{
			attributeName = parameter.getAlias() ;
		}
		
		final String annotationParams = this.getAnnotationParams(attributeName) ;
		if (annotationParams != null && !annotationParams.isEmpty())
		{
			annotation.append(ConstantsCommon.STRING_PARENTH_OPENED) ;
			annotation.append(annotationParams) ;
			annotation.append(ConstantsCommon.STRING_PARENTH_CLOSED) ;
		}
	}
	
	/**
	 * @param attributeName with the attribute name
	 * @return the annotation parameters 
	 */
	protected String getAnnotationParams(final String attributeName)
	{
		final StringBuilder outcome = new StringBuilder() ;
		
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

	/**
	 * @param parameter with the parameter
	 * @return the output type
	 */
	private String getTypeOutputFile(final Parameter parameter)
	{
		String type = this.getOutputLanguageItems().getFullTypeOutput(parameter.getItem()) ;
		
		if (parameter instanceof ParameterFormData && ConstantsCommon.TYPE_FILE.equalsIgnoreCase(parameter.getType()))
		{
			type = ConstantsOutputServerJaxrs.JAXRS_FORM_DATA_INPUTSTREAM ;
		}
		
		return type ;
	}
}
