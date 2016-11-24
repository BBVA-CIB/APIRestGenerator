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

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemSimpleDouble extends ItemNumber
{
	/**
	 * Public constructor
	 * @param name 		  with the name
	 * @param alias		  with the alias name
	 * @param description with the description
	 * @param required	  true if the item is required
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public ItemSimpleDouble(final String name, final String alias, final String description, final String required) throws APIRestGeneratorException
	{
		super(name, alias, description, required) ;
	}
	
	/**
	 * @return the type
	 */
	@Override
	public String getType()
	{
		return ConstantsInput.JSON_TYPE_NUMBER ;
	}
	
	@Override
	public String getClassName()
	{
		return this.getClass().getSimpleName() ;
	}
}
