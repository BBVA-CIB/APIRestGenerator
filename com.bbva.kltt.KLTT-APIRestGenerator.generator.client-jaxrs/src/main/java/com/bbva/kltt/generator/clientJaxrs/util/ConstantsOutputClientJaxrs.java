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

package com.bbva.kltt.generator.clientJaxrs.util;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutputClientJaxrs
{
	/******************** Velocity - JAX-RS Templates ********************/
	
	/** Velocity template - JAX-RS template - RestHandlerMethodHeadersClientJaxrsInterfaceTemplate */
	public static final String VP_CLI_JAXRS_T_REST_M_H_INT = "RestHandlerMethodHeadersClientJaxrsInterfaceTemplate" ;
	/** Velocity template - JAX-RS template - RestHandlerMethodHeadersClientJaxrsImplTemplate */
	public static final String VP_CLI_JAXRS_T_REST_M_H_IMP = "RestHandlerMethodHeadersClientJaxrsImplTemplate" ;
	/** Velocity template - JAX-RS template - RestListenerMethodHeadersClientJaxrsInterfaceTemplate */
	public static final String VP_CLI_JAXRS_T_LIST_MET_H   = "RestListenerMethodHeadersClientJaxrsInterfaceTemplate" ;
	/** Velocity template - JAX-RS template - ExampleListenerMethodHeadersClientJaxrsTemplate */
	public static final String VP_CLI_JAXRS_T_EXAM_MET_H_T = "ExampleListenerMethodHeadersClientJaxrsTemplate" ;
	
	
	/********************* Velocity - JAX-RS Macros **********************/
	
	/** Velocity Parameter - JAX-RS macro - RestHandlerMethodHeadersClientJaxrsImplMacros */
	public static final String VP_CLI_JAXRS_M_REST_M_H_IMP = "RestHandlerMethodHeadersClientJaxrsImplMacros" ;
	
	
	/******************** JAX-RS Client parameters ************************/
	
	/** Output type - File */
	public static final String OUTPUT_TYPE_FILE			   = "InputStream" ;


	/************************ Module properties **************************/

	/** Path to VM files parent directory */
	public static final String PACKAGE_VM_RESOURCES			= "com.bbva.kltt.generator.clientJaxrs.java" ;

	/** Name of the module */
	public static final String MODULE_NAME					= "clientJaxrs";

	/** Name to display on website */
	public static final String OFF_MODULE_NAME				= "JAX-RS";

	/** Class with the generate method */
	public static final String GENERATOR_INIT_CLASS	   		= "GeneratorGlobalClientJaxrs" ;

	/** Type of generator (client or server) */
	public static final String TYPE_MODULE					= "client";


	/*********************** Velocity parameters *************************/

	/** Velocity parameter - restHandlerInterfaceClassName */
	public static final String VP_R_HANDLER_INTERFACE_NAME  = "restHandlerInterfaceClassName" ;
	/** Velocity key parameter - restHandlerVariable */
	public static final String VP_R_REST_HANDLER_VARIABLE   = "restHandlerVariable" ;
    /** Velocity key parameter - restListenerVariable */
    public static final String VP_R_REST_LISTENER_VARIABLE  = "restListenerVariable" ;
    /** Velocity value parameter - restListener */
    public static final String VP_REST_LISTENER             = "restListener" ;
    /** Velocity value parameter - restHandler */
    public static final String VP_REST_HANDLER              = "restHandler" ;

	/**
	 * Private constructor
	 */
	private ConstantsOutputClientJaxrs()
	{
		// Empty constructor
	}
}
