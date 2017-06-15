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

package com.bbva.kltt.apirest.core.parsed_info.parameters;

import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.bbva.kltt.apirest.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class ParametersBase
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParametersBase.class) ;
    
	/** Attribute - Body Parameter */
	@JsonView(GeneratorView.class)
	private ParameterBody parameterBody ;

	/**
	 * Add a new body parameter
	 * @param parameterBody with the body parameter
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	protected void addParameterBody(final ParameterBody parameterBody) throws APIRestGeneratorException
	{
		if (this.parameterBody != null)
		{
			final String errorString = "Error while adding a new body parameter '" + parameterBody.getName() + "'. It is already defined a body parameter" ;

			ParametersBase.LOGGER.error(errorString) ;
			throw new APIRestGeneratorException(errorString) ;
		}

		this.parameterBody = parameterBody ;
	}

	/**
	 * @param itemReference with the item reference
	 * @return the referenced parameter
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public Parameter getReferenceParameter(final ItemRef itemReference) throws APIRestGeneratorException
	{
    	return this.getReferenceParameter(itemReference.getItemRef()) ;
	}

	/**
	 * @param itemRefString with the item reference string
	 * @return the referenced parameter
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public Parameter getReferenceParameter(final String itemRefString) throws APIRestGeneratorException
	{
		Parameter parameter   = null ;

    	final Pattern pattern = Pattern.compile(ConstantsInput.PATT_PARAMETERS_REF) ;
		final Matcher matcher = pattern.matcher(itemRefString) ;
		if (matcher.matches())
		{
			final String itemRefSimpleName = matcher.group(1) ;
			parameter 					   = this.getReferenceParameterSimpleName(itemRefSimpleName) ;
		}
		else
		{
			final String errorString = "The following reference was not defined properly to be search in the global parameters: " + itemRefString ;

			ParametersBase.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}

		return parameter ;
	}

	/**
	 * @param itemRefSimpleName with the item reference simple name
	 * @return the referenced parameter
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Parameter getReferenceParameterSimpleName(final String itemRefSimpleName) throws APIRestGeneratorException
	{
		Parameter parameter = this.getParameterFormData(itemRefSimpleName) ;

		if (parameter == null)
		{
			parameter = this.getParameterHeader(itemRefSimpleName) ;
		}

		if (parameter == null)
		{
			parameter = this.getParameterPath(itemRefSimpleName) ;
		}

		if (parameter == null)
		{
			parameter = this.getParameterQuery(itemRefSimpleName) ;
		}

		if (parameter == null)
		{
			parameter = this.getParameterBody(itemRefSimpleName) ;
		}

		if (parameter == null)
		{
			final String errorString = "No reference was found in the global parameters: " + itemRefSimpleName ;

			ParametersBase.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}

		return parameter ;
	}

	/**
	 * @param parameterName with the parameter name
	 * @return the form data parameter
	 */
	public abstract Parameter getParameterFormData(final String parameterName) ;

	/**
	 * @param parameterName with the parameter name
	 * @return the header parameter
	 */
	public abstract Parameter getParameterHeader(final String parameterName) ;

	/**
	 * @param parameterName with the parameter name
	 * @return the path parameter
	 */
	public abstract Parameter getParameterPath(final String parameterName) ;

	/**
	 * @param parameterName with the parameter name
	 * @return the query parameter
	 */
	public abstract Parameter getParameterQuery(final String parameterName) ;

	/**
	 * @return true if it has any form data parameter
	 */
	public abstract boolean hasAnyParameterFormData() ;

	/**
	 * @return true if it has any header parameter
	 */
	public abstract boolean hasAnyParameterHeader() ;

	/**
	 * @return true if it has any path parameter
	 */
	public abstract boolean hasAnyParameterPath() ;

	/**
	 * @return true if it has any query parameter
	 */
	public abstract boolean hasAnyParameterQuery() ;

	/**
	 * @param parameterName with the parameter name
	 * @return the body parameter
	 */
	private Parameter getParameterBody(final String parameterName)
	{
		Parameter parameter = null ;
		
		if (this.parameterBody != null && parameterName.equals(this.parameterBody.getName()))
		{
			parameter = this.parameterBody ;
		}
		
		return parameter ;
	}
	
	/**
	 * @return true if it has a body parameter
	 */
	public boolean hasAnyParameterBody()
	{
		return this.parameterBody != null ;
	}

	/**
	 * @return the parameterBody
	 */
	public ParameterBody getParameterBody()
	{
		return this.parameterBody ;
	}
}
