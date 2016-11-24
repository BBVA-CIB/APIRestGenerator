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

package com.bbva.kltt.core.generator.output.parameters;

import com.bbva.kltt.core.generator.output.language.IOutputLanguageSeparators;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class OutputParameters
{
	/** Attribute - Output Language separators */
	private final IOutputLanguageSeparators outputLangSeparators ;
	
	/** Attribute - List of OutputParameter */
	private final List<OutputParameter> parametersList ;
	
	/**
	 * Public constructor
	 * @param outputLangSeparators with the output language separators
	 * @param parametersList 	   with the parameters list
	 */
	public OutputParameters(final IOutputLanguageSeparators outputLangSeparators, final List<OutputParameter> parametersList)
	{
		this.outputLangSeparators = outputLangSeparators ;
		this.parametersList 	  = parametersList ;
	}
	
	/**
	 * @return the outputLangSeparators
	 */
	public IOutputLanguageSeparators getOutputLangSeparators()
	{
		return this.outputLangSeparators ;
	}

	/**
	 * @return the parametersList
	 */
	public List<OutputParameter> getParametersList()
	{
		return this.parametersList ;
	}

	/**
	 * @return the parameter and description separated by blank
	 */
	public List<String> getParametersDescription()
	{
		List<String> outcome = null ;
		
		if (this.parametersList != null)
		{
			outcome = new ArrayList<String>() ;
			for (final OutputParameter outputParam : this.parametersList)
			{
				outcome.add(outputParam.generateParameterDescription()) ;
				
				// Handler additional actions in the specific translator
				this.getParametersDescriptionSpecific(outcome, outputParam) ;
			}
		}
		
		return outcome ;
	}
	
	/**
	 * @param currentOutcome  with the current outcome
	 * @param outputParameter with the output parameter
	 */
	protected abstract void getParametersDescriptionSpecific(final List<String> currentOutcome, final OutputParameter outputParameter) ;
	
	/**
	 * @return a full string with the parameters declared for the listener interface
	 */
	public String getParametersHeader()
	{
		StringBuffer outcome = null ;
		
		if (this.getParametersList() != null)
		{
			outcome = new StringBuffer() ;
			for (final OutputParameter outputParam : this.getParametersList())
			{
				if (!outcome.toString().isEmpty())
				{
					outcome.append(this.getOutputLangSeparators().generateSeparatorValues()) ;
				}
				
				outcome.append(outputParam.getParameterHeader()) ;
				
				// Handler additional actions in the specific translator
				this.getParametersHeaderSpecific(outcome, outputParam) ;
			}
		}
		
		return outcome.toString() ;
	}
	
	/**
	 * @param currentOutcome  with the current outcome
	 * @param outputParameter with the output parameter
	 */
	protected abstract void getParametersHeaderSpecific(final StringBuffer currentOutcome, final OutputParameter outputParameter) ;
	
	/**
	 * @return a full string with the parameters declared for the listener call from the web service
	 */
	public String getParametersCall()
	{
		StringBuffer outcome = null ;
		
		if (this.parametersList != null)
		{
			outcome = new StringBuffer() ;
			for (final OutputParameter outputParam : this.parametersList)
			{
				if (!outcome.toString().isEmpty())
				{
					outcome.append(this.getOutputLangSeparators().generateSeparatorValues()) ;
				}
				
				outcome.append(outputParam.getParameterCall()) ;
				
				// Handler additional actions in the specific translator
				this.getParametersCallSpecific(outcome, outputParam) ;
			}
		}
		
		return outcome.toString() ;
	}
	
	/**
	 * @param currentOutcome  with the current outcome
	 * @param outputParameter with the output parameter
	 */
	protected abstract void getParametersCallSpecific(final StringBuffer currentOutcome, final OutputParameter outputParameter) ;
}
