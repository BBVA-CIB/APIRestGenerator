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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParameterPath extends ParameterNoBody
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterPath.class) ;
	
	/**
	 * Public constructor
	 * @param name 		   with the name
	 * @param alias		   with the alias name
	 * @param description  with the description
	 * @param required	   true if the item is required
	 * @param type		   with the type
	 * @param autoInjected true if the item is auto injected
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public ParameterPath(final String name,
						 final String alias,
						 final String description,
						 final String required,
						 final String type,
						 final String autoInjected) throws APIRestGeneratorException
	{
		super(name, alias, description, required, type, autoInjected) ;
	}
	
	/**
	 * Validate instance attributes
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	@Override
	public void validate() throws APIRestGeneratorException
	{
		super.validate() ;
		
		String errorString = null ;
		
		if (!this.isRequired())
		{
			errorString = "The 'name' field '" + this.getName()  + "' in PATH must contain a field 'required' as 'true'" ;
		}
		else if (this.getAlias() != null && !this.getAlias().equals(this.getName()))
		{
			// The alias name must be the same than the 'name'
			errorString = "The 'alias' field '" + this.getAlias()  + "' in PATH must be the same than the 'name' value '" + this.getName() + "'" ;
		}
		else if (this.isAutoInjected())
		{
			errorString = "Auto Injection cannot be implemented for path parameters" ;
		}
		
		if (errorString != null)
		{
			ParameterPath.LOGGER.error(errorString) ;
			throw new APIRestGeneratorException(errorString) ;
		}
	}

	@Override
	public String getClassName()
	{
		return this.getClass().getSimpleName() ;
	}
}
