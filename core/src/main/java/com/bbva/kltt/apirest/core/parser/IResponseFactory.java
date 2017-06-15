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

package com.bbva.kltt.apirest.core.parser;

import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IResponseFactory
{
	/**
	 * @param name     	  with the name
	 * @param alias		  with the alias name
	 * @param description with the description
	 * @param type        with the type
     * @return a new response
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	Response createNewResponse(final String name, final String alias, final String description, final String type) throws APIRestGeneratorException ;

	/**
	 * @param itemResponse with the item response
	 * @param item		   with the item
	 */
	void addItemIntoResponse(final Item itemResponse, final Item item) ;
}
