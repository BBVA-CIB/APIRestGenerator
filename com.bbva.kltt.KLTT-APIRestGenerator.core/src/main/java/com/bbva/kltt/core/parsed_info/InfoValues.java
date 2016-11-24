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

import com.bbva.kltt.core.util.mapper.JacksonViews;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class InfoValues
{
	/** Contact values */
	@JsonView(JacksonViews.GeneratorView.class)
	private final ContactValues contactValues ;
	/** Title */
	@JsonView(JacksonViews.GeneratorView.class)
	private String title ;
	/** Description */
	@JsonView(JacksonViews.GeneratorView.class)
	private String description ;
	/** Version */
	@JsonView(JacksonViews.GeneratorView.class)
	private String version ;
	
	/**
	 * Public constructor
	 * @param title		  with the title
	 * @param description with the description
	 * @param version	  with the version
	 */
	public InfoValues(final String title, final String description, final String version)
	{
		this.title 		   = title ;
		this.description   = description ;
		this.version 	   = version ;
		
		this.contactValues = new ContactValues() ;
	}

	/**
	 * Add Contact values
	 * @param name  with the name
	 * @param url   with the url
	 * @param email with the email
	 */
	public void addContactValues(final String name, final String url, final String email)
	{
		this.contactValues.addValues(name, url, email) ;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return this.title ;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return this.description ;
	}

	/**
	 * @return the version
	 */
	public String getVersion()
	{
		return this.version ;
	}

	/**
	 * @return the contactValues
	 */
	public ContactValues getContactValues()
	{
		return this.contactValues ;
	}
}
