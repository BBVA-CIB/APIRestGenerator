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

package com.bbva.kltt.core.parsed_info.common;

import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsInput;
import com.bbva.kltt.core.util.mapper.JacksonViews.GeneratorView;
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
public abstract class Item
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(Item.class) ;
	
	/** Attribute - alias */
	@JsonView(GeneratorView.class)
	private final String alias ;
	
	/** Attribute - description */
	@JsonView(GeneratorView.class)
	private final String description ;
	
	/** Attribute - required */
	@JsonView(GeneratorView.class)
	private final boolean required ;
	
	/** Attribute - name */
	@JsonView(GeneratorView.class)
	private String name ;
	
	/**
	 * Public constructor
	 * @param name 		  with the name
	 * @param alias		  with the alias name
	 * @param description with the description
	 * @param required	  true if the item is required
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public Item(final String name, final String alias, final String description, final String required) throws APIRestGeneratorException
	{
		this.name 		 = name ;
		this.alias		 = alias ;
		this.description = description ;
		this.required    = Boolean.parseBoolean(required) ;
		
		// Validate the name attribute. The content must be a valid attribute definition
		this.validateName() ;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return this.name ;
	}
	
	/**
	 * @return the alias
	 */
	public String getAlias()
	{
		return this.alias ;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return this.description ;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired()
	{
		return this.required ;
	}
	
	/**
	 * @return the type
	 */
	public abstract String getType() ;
	
	/**
	 * Utility method to know the type when printing the content
	 * @return the class name
	 */
	public abstract String getClassName() ;
	
	/**
	 * Validate the name attribute. The content must be a valid attribute definition and not to be a reserved word in any language
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void validateName() throws APIRestGeneratorException
	{
		final Pattern pattern = Pattern.compile(ConstantsInput.PATT_VARIABLE_VALID) ;
		final Matcher matcher = pattern.matcher(this.name) ;
		if (!matcher.matches())
		{
			final String errorString = "The following attribute 'name' was not defined properly to be a class attribute: " + this.name ;
			
			Item.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
		
		final int lengthReservedWords = ConstantsInput.RESERVED_WORDS.length ;
		for (int i=0 ; i < lengthReservedWords ; i++)
		{
			if (ConstantsInput.RESERVED_WORDS[i].equals(this.name))
			{
				final String errorString = "The following 'name' was defined but it is a reserved word in a specific language: " + this.name ;
				
				Item.LOGGER.error(errorString) ;
		    	throw new APIRestGeneratorException(errorString) ;
			}
		}
	}
}
