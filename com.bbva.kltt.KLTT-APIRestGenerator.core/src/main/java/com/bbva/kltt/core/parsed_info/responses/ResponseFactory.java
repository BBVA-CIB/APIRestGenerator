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
import com.bbva.kltt.core.parser.IResponseFactory;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ResponseFactory implements IResponseFactory
{
	/**
	 * @param name     	  with the name
	 * @param alias		  with the alias name
	 * @param description with the description
	 * @param type		  with the type
	 * @return a new response
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public Response createNewResponse(final String name, final String alias, final String description, final String type) throws APIRestGeneratorException
	{
		return new Response(name, alias, description, type) ;
	}

	/**
	 * @param itemResponse with the item response
	 * @param item		   with the item
	 */
	public void addItemIntoResponse(final Item itemResponse, final Item item)
	{
		((Response)itemResponse).setItem(item) ;
	}
}
