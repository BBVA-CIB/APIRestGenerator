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

import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.core.parsed_info.common.ItemFactory;
import com.bbva.kltt.core.parsed_info.common.ItemRef;
import com.bbva.kltt.core.parser.IItemFactory;
import com.bbva.kltt.core.util.mapper.JacksonViews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Definitions
{
	/** Types Map */
	@JsonView(JacksonViews.GeneratorView.class)
	private final Map<String, Item> itemsMap ;
	
	/** Item Factory */
	@JsonIgnore
	private final IItemFactory itemFactory ;
	
	/**
	 * Public constructor
	 */
	public Definitions()
	{
		this.itemsMap    = new HashMap<String, Item>() ;
		this.itemFactory = new ItemFactory(this.itemsMap) ;
	}
	
	/**
	 * @return the itemsMap
	 */
	public Map<String, Item> getItemsMap()
	{
		return this.itemsMap ;
	}
	
	/**
	 * @return the itemFactory
	 */
	public IItemFactory getItemFactory()
	{
		return this.itemFactory ;
	}

	/**
	 * @return a new map with the full definitions (complex and references)
	 */
	@JsonIgnore
	protected Map<String, Item> generateDefinitionsMap()
	{
		// Generate the map with the first level of referenced definitions
		final Map<String, Item> outcome = this.generateRefDefinitionsMap() ;
		
		// Generate the map with the complex definitions
		this.generateComplexDefinitionsMap(this.itemsMap, outcome) ;
		
		return outcome ;
	}
	
	/**
	 * @return the ref definitions in a map
	 */
	@JsonIgnore
	private Map<String, Item> generateRefDefinitionsMap()
	{
		final Map<String, Item> outcome = new HashMap<String, Item>() ;
		
		for (final Entry<String, Item> entry : this.itemsMap.entrySet())
        {
			final Item item = entry.getValue() ;
			
			if (item instanceof ItemRef)
			{
				outcome.put(item.getName(), item) ;
			}
        }
		
		return outcome ;
	}

	/**
	 * @param itemsMap       with the items map which could have more complex sub types or referenced types
	 * @param currentOutcome with the outcome map with the full linked definitions
	 */
	private void generateComplexDefinitionsMap(final Map<String, Item> itemsMap, final Map<String, Item> currentOutcome)
	{
		for (final Entry<String, Item> entry : itemsMap.entrySet())
        {
			final Item item = entry.getValue() ;
			
			if (item instanceof ItemComplex)
			{
				this.generateComplexDefinitionsMap(((ItemComplex) item).getItemsMap(), currentOutcome) ;
				currentOutcome.put(item.getName(), item) ;
			}
        }
	}
}
