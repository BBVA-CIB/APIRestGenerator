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

package com.bbva.kltt.core.parser;

import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IParameterFactory
{
    /**
     * @param in          with the in parameter
     * @param name        with the item name
     * @param alias		  with the alias name
     * @param description with the description
	 * @param required	  true if the item is required
     * @param type   	  with the type
     * @return a new parameter
     * @throws APIRestGeneratorException with an occurred exception
     */
	public Parameter createNewParameter(final String in,
                                        final String name,
                                        final String alias,
                                        final String description,
                                        final String required,
                                        final String type) throws APIRestGeneratorException ;
	
	/**
	 * @param in with the in parameter
	 * @param key_bparam with the key of the body param
	 * @return true if the parameter is a body type
	 */
	public boolean isBodyParameter(final String in, final String key_bparam) ;
	
	/**
	 * @param itemParameter with the item parameter
	 * @param item 			with the item
	 */
	public void addItemIntoParameter(final Item itemParameter, final Item item) ;
}
