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

package com.bbva.kltt.core.parsed_info;

import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public enum Scheme
{
	/** Schema type - HTTP */
	SCHEMA_HTTP(ConstantsCommon.SCHEMA_HTTP),
	/** Schema type - HTTPS */
	SCHEMA_HTTPS(ConstantsCommon.SCHEMA_HTTPS),
	/** Schema type - WS */
	SCHEMA_WS(ConstantsCommon.SCHEMA_WS),
	/** Schema type - WSS */
	SCHEMA_WSS(ConstantsCommon.SCHEMA_WSS) ;

	/** Attribute - Schema type - name */
	@JsonView(GeneratorView.class)
	private final String name ;
	
	/**
	 * Private constructor
	 * @param name with the name
	 */
	private Scheme(final String name)
	{
		this.name = name ;
	}

	/**
	 * Returns true if the given translator name is a valid translator
	 *
	 * @param name the string name of the translator
	 * @return true if the name is valid
	 */
	public static boolean isValid(final String name)
	{
		return  ConstantsCommon.SCHEMA_HTTP.equalsIgnoreCase(name)  ||
				ConstantsCommon.SCHEMA_HTTPS.equalsIgnoreCase(name) ||
				ConstantsCommon.SCHEMA_WS.equalsIgnoreCase(name)    ||
				ConstantsCommon.SCHEMA_WSS.equalsIgnoreCase(name)   ;
	}

	/**
	 * Get the correct Scheme given the representing String
	 *
	 * @param name string representation of the scheme
	 * @return the ENUM value
	 * @throws APIRestGeneratorException exception thrown if the provided name is not valid
	 */
	public static Scheme fromStringName(final String name) throws APIRestGeneratorException
	{
		if (!Scheme.isValid(name))
		{
			throw new APIRestGeneratorException("Invalid scheme type name " + name) ;
		}
		
		Scheme outcome = null ;
		
		if (ConstantsCommon.SCHEMA_HTTP.equalsIgnoreCase(name))
		{
			outcome = Scheme.SCHEMA_HTTP ;
		}
		else if (ConstantsCommon.SCHEMA_HTTPS.equalsIgnoreCase(name))
		{
			outcome = Scheme.SCHEMA_HTTPS ;
		}
		else if (ConstantsCommon.SCHEMA_WS.equalsIgnoreCase(name))
		{
			outcome = Scheme.SCHEMA_WS ;
		}
		else if (ConstantsCommon.SCHEMA_WSS.equalsIgnoreCase(name))
		{
			outcome = Scheme.SCHEMA_WSS ;
		}
		
		return outcome ;
	}

	/** 
	 * @return the String name representation of the translator
	 */
	public String getName()
	{
		return this.name ;
	}
	
	@Override
	public String toString()
	{
		return this.name ;
	}
}