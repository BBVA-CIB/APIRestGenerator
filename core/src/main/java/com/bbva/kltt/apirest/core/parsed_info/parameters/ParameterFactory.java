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

import java.util.Set;

import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parser.IParameterFactory;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsMiddle;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParameterFactory implements IParameterFactory
{
	/** Attribute - consumes */
	private final Set<String> consumes ;
	
	/**
	 * Public constructor
	 * @param consumes    with the consumes
	 */
	public ParameterFactory(final Set<String> consumes)
	{
		this.consumes = consumes ;
	}
	
    /**
     * @param inValue      with the in value
     * @param name         with the item name
     * @param alias		   with the alias name
     * @param description  with the description
	 * @param required	   true if the item is required
     * @param type   	   with the type
     * @param autoInjected true if the item is auto injected
     * @return a new item parameter
     * @throws APIRestGeneratorException with an occurred exception
     */
	public Parameter createNewParameter(final String inValue,
                                        final String name,
                                        final String alias,
                                        final String description,
                                        final String required,
                                        final String type,
                                        final String autoInjected) throws APIRestGeneratorException
	{
		Parameter parameter = null ;

		// Parse the "required value"
		final String requiredParsed = this.parseRequiredParam(required) ;
		
		if (ConstantsMiddle.PARAM_IN_BODY.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterBody(name, alias, description, requiredParsed, type, autoInjected) ;
		}
		else if (ConstantsMiddle.PARAM_IN_QUERY.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterQuery(name, alias, description, requiredParsed, type, autoInjected) ;
		}
		else if (ConstantsMiddle.PARAM_IN_HEADER.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterHeader(name, alias, description, requiredParsed, type, autoInjected) ;
		}
		else if (ConstantsMiddle.PARAM_IN_PATH.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterPath(name, alias, description, requiredParsed, type, autoInjected) ;
		}
		else if (ConstantsMiddle.PARAM_IN_FORMDATA.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterFormData(name, alias, description, requiredParsed, type, this.consumes, autoInjected) ;
		}
		
		// Finally, we need to validate the input parameters
		if (parameter != null)
		{
			parameter.validate() ;
		}
		
		return parameter ;
	}
	
	/**
	 * Parse the "required" parameter. It should be "true" if the parameter is null or empty
	 * @param required as true if the parameter is null, empty or true (ignoring case)
	 * @return the parsed value
	 */
	private String parseRequiredParam(final String required)
	{
		String requiredParsed = Boolean.TRUE.toString() ;
		
		if (required != null && !required.isEmpty())
		{
			requiredParsed = Boolean.valueOf(required).toString() ;
		}
				
		return requiredParsed ;
	}
	
	/**
	 * @param inParameter  with the in parameter
	 * @param keyBodyParam with the key of the body param
	 * @return true if the parameter is a body type
	 */
	public boolean isBodyParameter(final String inParameter, final String keyBodyParam)
	{
		return keyBodyParam.equalsIgnoreCase(inParameter) ;
	}

	/**
	 * @param itemParameter with the item parameter
	 * @param item 			with the item
	 */
	public void addItemIntoParameter(final Item itemParameter, final Item item)
	{
		((Parameter)itemParameter).setItem(item) ;
	}
}
