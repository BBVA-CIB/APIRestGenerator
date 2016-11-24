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

import com.bbva.kltt.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ContactValues
{
	/** Contact - Name */
	@JsonView(GeneratorView.class)
	private String name ;
	/** Contact - url */
	@JsonView(GeneratorView.class)
	private String url ;
	/** Contact - Email */
	@JsonView(GeneratorView.class)
	private String email ;
	
	/**
	 * Add Contact values
	 * @param name  with the name
	 * @param url   with the url
	 * @param email with the email
	 */
	protected void addValues(final String name, final String url, final String email)
	{
		this.name  = name ;
		this.url   = url ;
		this.email = email ;
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return this.name ;
	}

	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return this.url ;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return this.email ;
	}
}
