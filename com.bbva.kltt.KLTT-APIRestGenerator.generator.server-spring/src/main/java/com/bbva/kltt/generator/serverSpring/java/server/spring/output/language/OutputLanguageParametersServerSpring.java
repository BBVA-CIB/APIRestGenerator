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

package com.bbva.kltt.generator.serverSpring.java.server.spring.output.language;

import com.bbva.kltt.generator.java.output.language.OutputLanguageSeparatorsJava;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.core.generator.output.language.OutputLanguageParameters;
import com.bbva.kltt.core.generator.output.parameters.OutputParameter;
import com.bbva.kltt.core.generator.output.parameters.OutputParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.parsed_info.parameters.*;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.generator.serverSpring.java.server.spring.output.parameters.OutputParameterServerSpring;
import com.bbva.kltt.generator.serverSpring.java.server.spring.output.parameters.OutputParametersServerSpring;
import com.bbva.kltt.generator.serverSpring.util.ConstantsOutputServerSpring;

import java.util.List;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageParametersServerSpring extends OutputLanguageParameters
{
	/** Attribute - IOutputLanguageItems */
	private final IOutputLanguageItems outputLanguageItems ;
	
	/**
	 * Public constructor
	 * @param parsedInfoHandler   with the parsed information handler
	 * @param outputLanguageItems with the output language items
	 */
	public OutputLanguageParametersServerSpring(final ParsedInfoHandler parsedInfoHandler, final IOutputLanguageItems outputLanguageItems)
	{
		super(parsedInfoHandler, new OutputLanguageSeparatorsJava()) ;
		
		this.outputLanguageItems = outputLanguageItems ;
	}
	
	@Override
	public OutputParameters createNewOutputParameters(final List<OutputParameter> outputParamList)
	{
		return new OutputParametersServerSpring(this.getOutputLangSeparators(), outputParamList) ;
	}
	
	@Override
	public OutputParameter createNewOutputParameter(final Parameter parameter)
	{
		final String type		= this.getTypeOutputFile(parameter) ;
		final String annotation = this.getAnnotation(parameter) ;
		
		return new OutputParameterServerSpring(parameter.getItem(), parameter.getDescription(), type, parameter.getName(), annotation) ;
	}
	
	/**
	 * @param parameter with the parameter
	 * @return the annotation
	 */
	private String getAnnotation(final Parameter parameter)
	{
		final StringBuffer annotation = new StringBuffer() ;
		
		if (parameter instanceof ParameterBody)
		{
			annotation.append(ConstantsOutputServerSpring.SPRING_ANN_REQUEST_BODY) ;
		}
		else if (parameter instanceof ParameterFormData)
		{
			annotation.append(ConstantsOutputServerSpring.SPRING_ANN_REQUEST_PARAM) ;
		}
		else if (parameter instanceof ParameterHeader)
		{
			annotation.append(ConstantsOutputServerSpring.SPRING_ANN_REQUEST_HEADER) ;
		}
		else if (parameter instanceof ParameterPath)
		{
			annotation.append(ConstantsOutputServerSpring.SPRING_ANN_PATH_VARIABLE) ;
		}
		else if (parameter instanceof ParameterQuery)
		{
			annotation.append(ConstantsOutputServerSpring.SPRING_ANN_REQUEST_PARAM) ;
		}
		
		final String annotationParams = this.getAnnotationParams(parameter.getAlias()) ;
		if (annotationParams != null && !annotationParams.isEmpty())
		{
			annotation.append(ConstantsCommon.STRING_PARENTH_OPENED) ;
			annotation.append(annotationParams) ;
			annotation.append(ConstantsCommon.STRING_PARENTH_CLOSED) ;
		}
		
		return annotation.toString() ;
	}

	/**
	 * @param parameter with the parameter
	 * @return the output type
	 */
	private String getTypeOutputFile(final Parameter parameter)
	{
		String type = this.outputLanguageItems.getFullTypeOutput(parameter.getItem()) ;
		
		if (parameter instanceof ParameterFormData && ConstantsCommon.TYPE_FILE.equalsIgnoreCase(parameter.getType()))
		{
			type = ConstantsOutputServerSpring.SPRING_FORM_DATA_MULTPART ;
		}
		
		return type ;
	}
}
