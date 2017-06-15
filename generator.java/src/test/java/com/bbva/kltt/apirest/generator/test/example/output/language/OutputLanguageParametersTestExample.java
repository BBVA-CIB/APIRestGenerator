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

package com.bbva.kltt.apirest.generator.test.example.output.language;

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
import com.bbva.kltt.apirest.generator.test.example.output.parameters.OutputParameterTestExample;
import com.bbva.kltt.apirest.generator.test.example.output.parameters.OutputParametersTestExample;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageParametersTestExample extends OutputLanguageParametersJava
{
	/**
	 * Public constructor
	 * @param parsedInfoHandler   with the parsed information handler
	 * @param outputLanguageItems with the output language items
	 */
	public OutputLanguageParametersTestExample(final ParsedInfoHandler parsedInfoHandler, final IOutputLanguageItems outputLanguageItems)
	{
		super(parsedInfoHandler, outputLanguageItems) ;
	}
	
	@Override
	public OutputParameters createNewOutputParameters(final List<OutputParameter> outputParamList)
	{
		return new OutputParametersTestExample(this.getOutputLangSeparators(), outputParamList) ;
	}
	
	@Override
	public OutputParameter createNewOutputParameter(final Parameter parameter)
	{
		final String type		= this.getTypeOutputFile(parameter) ;
		final String annotation = this.getAnnotation(parameter) ;
		
		return new OutputParameterTestExample(parameter.getItem(), parameter.getDescription(), type, parameter.getName(), annotation) ;
	}
	
	/**
	 * @param parameter with the parameter
	 * @return the annotation
	 */
	private String getAnnotation(final Parameter parameter)
	{
		final StringBuilder annotation = new StringBuilder() ;
		
		String annotationName = ConstantsCommon.STRING_EMPTY ;
		if (parameter instanceof ParameterBody)
		{
			annotationName = ConstantsOutputTestExample.SPRING_ANN_REQUEST_BODY ;
		}
		else if (parameter instanceof ParameterFormData || parameter instanceof ParameterQuery)
		{
			annotationName = ConstantsOutputTestExample.SPRING_ANN_REQUEST_PARAM ;
		}
		else if (parameter instanceof ParameterHeader)
		{
			annotationName = ConstantsOutputTestExample.SPRING_ANN_REQUEST_HEADER ;
		}
		else if (parameter instanceof ParameterPath)
		{
			annotationName = ConstantsOutputTestExample.SPRING_ANN_PATH_VARIABLE ;
		}
		
		// Add the annotation name
		annotation.append(annotationName) ;
		
		final String annotationParams = this.getAnnotationParams(parameter.getAlias(), parameter.isRequired(), annotationName) ;
		if (annotationParams != null && !annotationParams.isEmpty())
		{
			annotation.append(ConstantsCommon.STRING_PARENTH_OPENED) ;
			annotation.append(annotationParams) ;
			annotation.append(ConstantsCommon.STRING_PARENTH_CLOSED) ;
		}
		
		return annotation.toString() ;
	}
	
	/**
	 * @param aliasName      with the alias
	 * @param required       as true if the parameter is required
	 * @param annotationName with the annotation name
	 * @return the annotation parameters 
	 */
	protected String getAnnotationParams(final String aliasName, final boolean required, final String annotationName)
	{
		final StringBuilder outcome = new StringBuilder() ;
		
		// Set the annotation "value" if it is not a @RequestBody
		boolean valueAnnotationFilled = !ConstantsOutputTestExample.SPRING_ANN_REQUEST_BODY.equals(annotationName) ;
		if (valueAnnotationFilled)
		{
			outcome.append(this.setAnnotationValue(aliasName)) ;
			valueAnnotationFilled = !ConstantsCommon.STRING_EMPTY.equals(outcome.toString()) ;
		}
		
		// The only annotation that not accepts the "required" value is "@PathVariable"
		if (!ConstantsOutputTestExample.SPRING_ANN_PATH_VARIABLE.equals(annotationName))
		{
			outcome.append(this.setAnnotationRequired(required, valueAnnotationFilled)) ;
		}
		
		return outcome.toString() ;
	}
	
	/**
	 * @param aliasName      with the alias
	 * @return the annotation value filled
	 */
	private StringBuilder setAnnotationValue(final String aliasName)
	{
		final StringBuilder outcome = new StringBuilder() ;
		
		if (aliasName != null && !aliasName.isEmpty())
		{
			outcome.append(ConstantsOutput.COMMON_ANNOTATION_ALIAS_PAR) ;
			outcome.append(ConstantsCommon.STRING_EQUAL) ;
			outcome.append(ConstantsCommon.STRING_DOUBLE_QUOTE) ;
			outcome.append(aliasName) ;
			outcome.append(ConstantsCommon.STRING_DOUBLE_QUOTE) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param required 				as true if the parameter is required
	 * @param valueAnnotationFilled as true if the value annotation was filled
	 * @return the annotation value filled
	 */
	private StringBuilder setAnnotationRequired(final boolean required, final boolean valueAnnotationFilled)
	{
		final StringBuilder outcome = new StringBuilder() ;
		
		if (valueAnnotationFilled)
		{
			outcome.append(ConstantsCommon.STRING_COMMA) ;
			outcome.append(ConstantsCommon.STRING_BLANK) ;
		}
		
		outcome.append(ConstantsOutput.COMMON_ANNOTATION_REQUI_PAR) ;
		outcome.append(ConstantsCommon.STRING_EQUAL) ;
		outcome.append(required) ;
		
		return outcome ;
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
			type = ConstantsOutputTestExample.SPRING_FORM_DATA_MULTPART ;
		}
		
		return type ;
	}
}
