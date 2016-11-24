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

package com.bbva.kltt.generator.clientJaxrs.java.client.jaxrs.output.parameters;

import com.bbva.kltt.generator.java.output.parameters.OutputParametersJava;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageSeparators;
import com.bbva.kltt.core.generator.output.parameters.OutputParameter;

import java.util.List;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputParametersClientJaxrs extends OutputParametersJava
{
	/**
	 * Public constructor
	 * @param outputLangSeparators with the output language separators
	 * @param outputParamsList 	   with the output parameters list
	 */
	public OutputParametersClientJaxrs(final IOutputLanguageSeparators outputLangSeparators, final List<OutputParameter> outputParamsList)
	{
		super(outputLangSeparators, outputParamsList) ;
	}
	
	@Override
	protected void getParametersDescriptionSpecific(final List<String> currentOutcome, final OutputParameter outputParameter)
	{
		// There is nothing awesome to do	
	}

	@Override
	protected void getParametersHeaderSpecific(final StringBuffer currentOutcome, final OutputParameter outputParameter)
	{
		// There is nothing awesome to do
	}

	@Override
	protected void getParametersCallSpecific(final StringBuffer currentOutcome, final OutputParameter outputParameter)
	{
		// There is nothing awesome to do
	}

	@Override
	protected void getParametersHeaderRestSpecific(final StringBuffer currentOutcome, final OutputParameter outputParameter)
	{
		// There is nothing awesome to do
	}
	
	@Override
	protected void getParametersTestSpecific(final StringBuffer currentOutcome, final OutputParameter outputParameter)
	{
		// There is nothing awesome to do
	}
}
