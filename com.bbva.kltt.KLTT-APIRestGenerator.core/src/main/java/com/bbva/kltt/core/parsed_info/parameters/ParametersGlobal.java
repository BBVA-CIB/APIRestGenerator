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

import com.bbva.kltt.core.parsed_info.Definitions;
import com.bbva.kltt.core.parsed_info.common.ItemFactory;
import com.bbva.kltt.core.parser.IItemFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParametersGlobal extends ParametersGlobalBase
{
	/** Item Factory */
	@JsonIgnore
	private final IItemFactory itemFactory ;
	
	/** Consumes */
	@JsonIgnore
	private final Set<String> consumes ;
	
	/**
	 * Public constructor
     * @param globalDefinitions with the definitions
     * @param consumes    		with the consumes 
	 */
	public ParametersGlobal(final Definitions globalDefinitions, final Set<String> consumes)
	{
		this.itemFactory = new ItemFactory(globalDefinitions.getItemsMap()) ;
		this.consumes    = consumes ;
	}
	
	/**
	 * @return the itemFactory
	 */
	public IItemFactory getItemFactory()
	{
		return this.itemFactory ;
	}

	/**
	 * @return the consumes
	 */
	public Set<String> getConsumes()
	{
		return this.consumes ;
	}
}
