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
public class ConstantsMiddle
{
	/** Constant - Path - get */
	public static final String PATH_OP_DELETE    = "DELETE" ;
	/** Constant - Path - get */
	public static final String PATH_OP_GET 	     = "GET" ;
	/** Constant - Path - head */
	public static final String PATH_OP_HEAD      = "HEAD";
	/** Constant - Path - options */
	public static final String PATH_OP_OPTIONS   = "OPTIONS" ;
	/** Constant - Path - patch */
	public static final String PATH_OP_PATCH     = "PATCH" ;
	/** Constant - Path - post */
	public static final String PATH_OP_POST      = "POST" ;
	/** Constant - Path - put */
	public static final String PATH_OP_PUT	     = "PUT" ;

	/** Constant Parameters - type */

	/** Parameters - header */
	public static final String PARAM_IN_HEADER 	= "header" ;
	/** Parameters - path */
	public static final String PARAM_IN_PATH 	= "path" ;
	/** Parameters - formData */
	public static final String PARAM_IN_FORMDATA = "formData" ;
	/** Parameters - body */
	public static final String PARAM_IN_BODY 	= "body" ;
	/** Parameters - query */
	public static final String PARAM_IN_QUERY 	= "query" ;
	
	/** Response Code - Success */
	public static final String RESP_CODE_SUCCESS = "200" ;
	
	/**
	 * Private constructor
	 */
	private ConstantsMiddle()
	{
		// Empty constructor
	}
}
