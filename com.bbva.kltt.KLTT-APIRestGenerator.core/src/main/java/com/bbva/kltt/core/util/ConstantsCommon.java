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

package com.bbva.kltt.core.util;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ConstantsCommon
{
	/** String Constants - Empty */
	public static final String STRING_EMPTY		   	   = "" ;
	/** String Constants - Blank */
	public static final String STRING_BLANK		   	   = " " ;
	/** String Constants - Comma */
	public static final String STRING_COMMA		   	   = "," ;
	/** String Constants - Slash */
	public static final String STRING_SLASH			   = "/" ;
	/** String Constants - Dot */
	public static final String STRING_DOT			   = "." ;
	/** String Constants - Hyphen */
	public static final String STRING_HYPHEN		   = "-" ;
	/** String Constants - Underscore */
	public static final String STRING_UNDERSCORE	   = "_" ;
	/** String Constants - Parenthesis Opened */
	public static final Object STRING_PARENTH_OPENED   = "(" ;
	/** String Constants - Parenthesis Closed */
	public static final Object STRING_PARENTH_CLOSED   = ")" ;
	/** String Constants - Colon */
	public static final String STRING_COLON			   = ":" ;
	/** String Constants - Semicolon */
	public static final String STRING_SEMICOLON		   = ";" ;
	/** String Constants - Open brackets */
	public static final String STRING_BRACKETS_OPEN    = "{" ;
	/** String Constants - Close brackets */
	public static final String STRING_BRACKETS_CLOSE   = "}" ;
	/** String Constants - Double quote */
	public static final String STRING_DOUBLE_QUOTE	   = "\"" ;
	/** String Constants - Equal */
	public static final String STRING_EQUAL	   		   = "=" ;
	
	/** Type - File */
	public static final String TYPE_FILE		   	   = "file" ;
	
	/** Previous directory - String */
	public static final String PREVIOUS_DIRECTORY	   = ".." ;
	
	
	/***************** Scheme types *********************/
	
	/** Scheme type - http */
	public static final String SCHEMA_HTTP 			   = "http" ;
	/** Scheme type - https */
	public static final String SCHEMA_HTTPS 		   = "https" ;
	/** Scheme type - ws */
	public static final String SCHEMA_WS 			   = "ws" ;
	/** Scheme type - wss */
	public static final String SCHEMA_WSS 			   = "wss" ;
	
	/***************** Common extensions *********************/
	
	/** Extension - YAML */
	public static final String EXTENSION_YAML		   = "yaml" ;
	/** Extension - YAML */
	public static final String EXTENSION_YAML_OTHER	   = "yml" ;
	/** Extension - JSON */
	public static final String EXTENSION_JSON		   = "json" ;
	/** Extension - XML */
	public static final String EXTENSION_XML		   = "xml" ;
	
	/**
	 * Private constructor
	 */
	private ConstantsCommon()
	{
		// Empty constructor
	}
}
