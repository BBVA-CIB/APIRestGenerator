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

package com.bbva.kltt.core.parsed_info.responses;

import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Response extends Item
{
	/** Attribute - Item which represents the type and format */
	@JsonView(GeneratorView.class)
	private Item item ;

	/** Attribute - Type which represents the type of the response */
	private String type;

	/**
	 * Public constructor
	 * @param name 		  with the name
	 * @param alias		  with the alias
	 * @param description with the description
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public Response(final String name, final String alias, final String description, final String type) throws APIRestGeneratorException
	{
		super(name, alias, description, "true") ;

		this.type = type;
	}
	
	/**
	 * @return the item
	 */
	public Item getItem()
	{
		return this.item ;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(final Item item)
	{
		this.item = item ;
	}

	@Override
	public String getType()
	{
		return this.type ;
	}

	@Override
	public String getClassName()
	{
		return this.getClass().getSimpleName() ;
	}
}
