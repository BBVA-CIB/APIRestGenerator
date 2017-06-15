package com.bbva.kltt.apirest.generator.client.web.output.parameters;

import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParameter;

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

import com.bbva.kltt.apirest.core.parsed_info.common.Item;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class OutputParameterClientWeb extends OutputParameter
{
	/** Attribute - Auto Injected */
	private final boolean autoInjected ;
	
	/**
	 * Public constructor
	 * @param item		   with the item parameter information
	 * @param description  with the description
	 * @param type		   with the type
	 * @param name		   with the name 
     * @param autoInjected true if the item is auto injected
	 */
	public OutputParameterClientWeb(final Item item, final String description, final String type, final String name, final boolean autoInjected)
	{
		super(item, description, type, name) ;
		
		this.autoInjected = autoInjected ;
	}
	
	/**
	 * @return the autoInjected
	 */
	public boolean isAutoInjected()
	{
		return this.autoInjected ;
	}
}

