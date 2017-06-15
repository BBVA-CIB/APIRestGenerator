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

package com.bbva.kltt.apirest.generator.client.javascript.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutputClientJavascript
{
	/***************** Velocity - Templates - Javascript ****************/
	
	/** Velocity template - Javascript template - MethodCommentsClientJavascriptTemplate */
	public static final String VP_JS_TEMPL_METH_COMMENTS	= "MethodCommentsClientJavascriptTemplate" ;
	/** Velocity template - Javascript template - RestHandlerMethodHeadersClientJavascriptTemplate */
	public static final String VP_JS_TEMPL_REST_MET_HEA 	= "RestHandlerMethodHeadersClientJavascriptTemplate" ;
	/** Velocity template - Javascript template - RestHandlerMethodHeadersClientJavascriptTemplateTest */
	public static final String VP_JS_TEMPL_REST_MET_H_T 	= "RestHandlerMethodHeadersClientJavascriptTemplateTest" ;
	/** Velocity template - Javascript template - RestListenerMethodHeadersClientJavascriptTemplate */
	public static final String VP_JS_TEMPL_LIST_MET_H   	= "RestListenerMethodHeadersClientJavascriptTemplate" ;
	/** Velocity template - Javascript template - RestListenerMethodHeadersClientJavascriptTemplateTest */
	public static final String VP_JS_TEMPL_LIST_MET_H_T 	= "RestListenerMethodHeadersClientJavascriptTemplateTest" ;
	/** Velocity template - Javascript template - ExampleTesterMethodsClientJavascriptTemplate */
	public static final String VP_JS_TEMPL_EXA_TEST_MET 	= "ExampleTesterMethodsClientJavascriptTemplate" ;
	/** Velocity Parameter - Javascript template - ListenerExampleClientJavascriptTemplate */
	public static final String VP_JS_TEMPL_LISTE_EXA 		= "ListenerExampleClientJavascriptTemplate" ;
	/** Velocity Parameter - Javascript template - ListenerExampleMethodsClientJavascriptTemplate */
	public static final String VP_JS_TEMPL_LISTE_EXA_ME 	= "ListenerExampleMethodsClientJavascriptTemplate" ;


    /*********************** Velocity parameters *************************/
    /** Velocity Parameter - Rest Listener File */
    public static final String VP_REST_HANDLER_FILE     	= "restHandlerFile" ;
    /** Velocity Parameter - Rest Handler File */
    public static final String VP_REST_LISTENER_FILE     	= "restListenerFile" ;
    /** Velocity Parameter - Module Name */
    public static final String VP_MODULE_NAME     	        = "moduleName" ;
    /** Velocity Parameter - Template for imports */
    public static final String VP_IMPORT_REST_HANDLER     	= "/// <reference path=\"%s%s\" />" ;

	/*************** Velocity - Macros - Javascript ********************/
	
	/** Velocity Parameter - Javascript macro - CommonClientJavascriptMacros */
	public static final String VP_JS_MACRO_COMMON			= "CommonClientJavascriptMacros" ;
	/** Velocity Parameter - Javascript macro - ModelsComplexClientJavascriptMacros */
	public static final String VP_JS_MACRO_MOD_COMPLEX  	= "ModelsComplexClientJavascriptMacros" ;
	/** Velocity Parameter - Javascript macro - RestHandlerClientJavascriptMacros */
	public static final String VP_JS_MACRO_REST_HANDLER 	= "RestHandlerClientJavascriptMacros" ;
	/** Velocity Parameter - Javascript macro - ExampleTesterClientJavascriptMacros */
	public static final String VP_JS_MACRO_EXAMPLE_TEST 	= "ExampleTesterClientJavascriptMacros" ;
	
	
	/************************ Module properties **************************/

	/** Path to VM files parent directory */
	public static final String PACKAGE_VM_RESOURCES			= "com.bbva.kltt.apirest.generator.client.javascript.velocity" ;

	/** Name of the module */
	public static final String MODULE_NAME					= "client.javascript";

	/** Name to display on website */
	public static final String OFF_MODULE_NAME				= "Javascript";

	/** Class with the generate method */
	public static final String GENERATOR_INIT_CLASS	   		= "GeneratorGlobalClientJavascript" ;

	/** Type of generator (client or server) */
	public static final String TYPE_MODULE					= "client";
	
	/** Project name - Client Jaxrs */
	public static final String PROJECT_NAME					= ConstantsOutput.PREFIX_GENERATOR_PROJECTS + ConstantsCommon.STRING_DOT + MODULE_NAME ;
	
	
	/**
	 * Private constructor
	 */
	private ConstantsOutputClientJavascript()
	{
		// Empty constructor
	}
}
