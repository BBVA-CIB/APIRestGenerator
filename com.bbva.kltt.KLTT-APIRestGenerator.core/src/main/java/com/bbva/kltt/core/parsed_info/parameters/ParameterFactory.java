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

package com.bbva.kltt.core.parsed_info.parameters;

import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.parser.IParameterFactory;
import com.bbva.kltt.core.util.ConstantsMiddle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParameterFactory implements IParameterFactory
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterFactory.class) ;
	
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
     * @param inValue     with the in value
     * @param name        with the item name
     * @param alias		  with the alias name
     * @param description with the description
	 * @param required	  true if the item is required
     * @param type   	  with the type
     * @return a new item parameter
     * @throws APIRestGeneratorException with an occurred exception
     */
	public Parameter createNewParameter(final String inValue,
                                        final String name,
                                        final String alias,
                                        final String description,
                                        final String required,
                                        final String type) throws APIRestGeneratorException
	{
		Parameter parameter = null ;

		if (ConstantsMiddle.PARAM_IN_BODY.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterBody(name, alias, description, required, type) ;
		}
		else if (ConstantsMiddle.PARAM_IN_QUERY.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterQuery(name, alias, description, required, type) ;
		}
		else if (ConstantsMiddle.PARAM_IN_HEADER.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterHeader(name, alias, description, required, type) ;
		}
		else if (ConstantsMiddle.PARAM_IN_PATH.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterPath(name, alias, description, required, type) ;
		}
		else if (ConstantsMiddle.PARAM_IN_FORMDATA.equalsIgnoreCase(inValue))
		{
			parameter = new ParameterFormData(name, alias, description, required, type, this.consumes) ;
		}
		
		// Finally, we need to validate the input parameters
		parameter.validate() ;
		
		return parameter ;
	}
	
	/**
	 * @param in with the in parameter
	 * @param key_bparam with the key of the body param
	 * @return true if the parameter is a body type
	 */
	public boolean isBodyParameter(final String in, final String key_bparam)
	{
		return key_bparam.equalsIgnoreCase(in) ;
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
