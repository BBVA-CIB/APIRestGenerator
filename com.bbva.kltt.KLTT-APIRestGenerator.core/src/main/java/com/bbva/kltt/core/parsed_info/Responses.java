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
import com.bbva.kltt.core.parsed_info.common.ItemFactory;
import com.bbva.kltt.core.parsed_info.responses.Response;
import com.bbva.kltt.core.parsed_info.responses.ResponseFactory;
import com.bbva.kltt.core.parser.IItemFactory;
import com.bbva.kltt.core.parser.IResponseFactory;
import com.bbva.kltt.core.util.ConstantsMiddle;
import com.bbva.kltt.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Responses
{
	/** Types Map */
	@JsonView(GeneratorView.class)
	private final Map<String, Response> responsesMap ;
	
	/** Item Factory */
	@JsonIgnore
	private final IItemFactory itemFactory ;
	
	/** Response Factory */
	@JsonIgnore
	private final IResponseFactory responseFactory ;
	
	/**
	 * Public constructor
     * @param globalDefinitions with the definitions
	 */
	public Responses(final Definitions globalDefinitions)
	{
		this.responsesMap 	 = new HashMap<String, Response>() ;
		this.itemFactory   	 = new ItemFactory(globalDefinitions.getItemsMap()) ;
		this.responseFactory = new ResponseFactory() ;
	}
	
	/**
	 * @return the responsesMap
	 */
	public Map<String, Response> getResponsesMap()
	{
		return this.responsesMap ;
	}
	
	/**
	 * @return the itemFactory
	 */
	public IItemFactory getItemFactory()
	{
		return this.itemFactory ;
	}

	/**
	 * @return the responseFactory
	 */
	public IResponseFactory getResponseFactory()
	{
		return this.responseFactory ;
	}

	/**
	 * @return the return item type
	 */
	@JsonIgnore
	public Item getOutboundServerItemType()
	{
		Item outcome = null ;
		
		final Iterator<Entry<String, Response>> iterator = this.responsesMap.entrySet().iterator() ;
		while (iterator.hasNext() && outcome == null)
		{
			final Entry<String, Response> entry = iterator.next() ;
			if (ConstantsMiddle.RESP_CODE_SUCCESS.equals(entry.getKey()))
			{
				outcome = entry.getValue() ;
			}
		}
		
		return outcome ;
	}
}
