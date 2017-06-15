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

package com.bbva.kltt.apirest.core.parsed_info.common;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.bbva.kltt.apirest.core.util.mapper.JacksonViews;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemRef extends Item
{
	/** String reference to the already defined item */
	@JsonView(JacksonViews.GeneratorView.class)
	private String itemRef ;
	
	/**
	 * Public constructor
	 * @param name 		  with the name
	 * @param alias		  with the alias name
	 * @param description with the description
	 * @param required	  true if the item is required
	 * @param itemRef	  with the reference to the already defined item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public ItemRef(final String name, final String alias, final String description, final String required, final String itemRef) throws APIRestGeneratorException
	{
		super(name, alias, description, required) ;
		
		this.itemRef = itemRef ;
	}

	/**
	 * @return the itemRef
	 */
	public String getItemRef()
	{
		return this.itemRef ;
	}

	/**
	 * @param itemRef the itemRef to set
	 */
	public void setItemRef(final String itemRef)
	{
		this.itemRef = itemRef ;
	}
	
	/**
	 * @return the type
	 */
	@Override
	public String getType()
	{
		return ConstantsInput.NO_JSON_TYPE_IS_REF ;
	}

	@Override
	public String getClassName()
	{
		return this.getClass().getSimpleName() ;
	}
}
