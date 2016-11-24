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

import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class OutputParameter
{
	/** Attribute - Item */
	private final Item item ;
	
	/** Attribute - Description */
	private final String description ;
	
	/** Attribute - Type */
	private final String type ;
	
	/** Attribute - Name */
	private final String name ;
	
	/**
	 * Public constructor
	 * @param item		  with the item parameter information
	 * @param description with the description
	 * @param type		  with the type
	 * @param name		  with the name 
	 */
	public OutputParameter(final Item item, final String description, final String type, final String name)
	{
		this.item 		 = item ;
		this.description = description ;
		this.type 		 = type ;
		this.name		 = name ;
	}
	
	/**
	 * @return the item
	 */
	public Item getItem()
	{
		return this.item ;
	}
	
	/**
	 * @return the type
	 */
	public String getType()
	{
		return this.type ;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return this.name ;
	}
	
	/**
	 * @return the parameter description
	 */
	public String generateParameterDescription()
	{
		return this.name + ConstantsCommon.STRING_BLANK + this.description ;
	}
	
	/**
	 * @return the output type + name
	 */
	public abstract String getOutputTypeName() ;

	/**
	 * @return a full string with the parameters declared for the listener interface
	 */
	public abstract String getParameterHeader() ;
	
	/**
	 * @return a full string with the parameters declared for the listener call from the web service
	 */
	public abstract String getParameterCall() ;

	/**
	 * @return a full string with the parameters declared for the rest web service
	 */
	public abstract String getParameterHeaderRest() ;

	/**
	 * @return a full string with the parameters by default in the specific language (typically 'null')
	 */
	public abstract String getParameterTest() ;
}
