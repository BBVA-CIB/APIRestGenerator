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

package com.bbva.kltt.apirest.generator.test.example.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutputTestExample
{
	/***************** Velocity - Templates - Javascript ****************/
	
	/** Velocity template - Javascript template - MethodCommentsTestExampleTemplate */
	public static final String VP_JS_TEMPL_METH_COMMENTS	= "MethodCommentsTestExampleTemplate" ;
	/** Velocity template - Javascript template - RestHandlerMethodHeadersTestExampleTemplate */
	public static final String VP_JS_TEMPL_REST_MET_HEA 	= "RestHandlerMethodHeadersTestExampleTemplate" ;
	/** Velocity template - Javascript template - RestHandlerMethodHeadersTestExampleTemplateTest */
	public static final String VP_JS_TEMPL_REST_MET_H_T 	= "RestHandlerMethodHeadersTestExampleTemplateTest" ;
	/** Velocity template - Javascript template - RestListenerMethodHeadersTestExampleTemplate */
	public static final String VP_JS_TEMPL_LIST_MET_H   	= "RestListenerMethodHeadersTestExampleTemplate" ;
	/** Velocity template - Javascript template - RestListenerMethodHeadersTestExampleTemplateTest */
	public static final String VP_JS_TEMPL_LIST_MET_H_T 	= "RestListenerMethodHeadersTestExampleTemplateTest" ;
	/** Velocity template - Javascript template - ExampleTesterMethodsTestExampleTemplate */
	public static final String VP_JS_TEMPL_EXA_TEST_MET 	= "ExampleTesterMethodsTestExampleTemplate" ;
	/** Velocity Parameter - Javascript template - ListenerExampleTestExampleTemplate */
	public static final String VP_JS_TEMPL_LISTE_EXA 		= "ListenerExampleTestExampleTemplate" ;
	/** Velocity Parameter - Javascript template - ListenerExampleMethodsTestExampleTemplate */
	public static final String VP_JS_TEMPL_LISTE_EXA_ME 	= "ListenerExampleMethodsTestExampleTemplate" ;


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
	
	/** Velocity Parameter - Javascript macro - CommonTestExampleMacros */
	public static final String VP_JS_MACRO_COMMON			= "CommonTestExampleMacros" ;
	/** Velocity Parameter - Javascript macro - ModelsComplexTestExampleMacros */
	public static final String VP_JS_MACRO_MOD_COMPLEX  	= "ModelsComplexTestExampleMacros" ;
	/** Velocity Parameter - Javascript macro - RestHandlerTestExampleMacros */
	public static final String VP_JS_MACRO_REST_HANDLER 	= "RestHandlerTestExampleMacros" ;
	/** Velocity Parameter - Javascript macro - ExampleTesterTestExampleMacros */
	public static final String VP_JS_MACRO_EXAMPLE_TEST 	= "ExampleTesterTestExampleMacros" ;
	
	
	/************************ Module properties **************************/

	/** Path to VM files parent directory */
	public static final String PACKAGE_VM_RESOURCES			= "com.bbva.kltt.apirest.generator.test.example.velocity" ;

	/** Name of the module */
	public static final String MODULE_NAME					= "test.example";

	/** Name to display on website */
	public static final String OFF_MODULE_NAME				= "Javascript";

	/** Class with the generate method */
	public static final String GENERATOR_INIT_CLASS	   		= "GeneratorGlobalTestExample" ;

	/** Type of generator (client or server) */
	public static final String TYPE_MODULE					= "test";
	
	/** Project name - Client Jaxrs */
	public static final String PROJECT_NAME					= ConstantsOutput.PREFIX_GENERATOR_PROJECTS + ConstantsCommon.STRING_DOT + MODULE_NAME ;
	
	
	/**
	 * Private constructor
	 */
	private ConstantsOutputTestExample()
	{
		// Empty constructor
	}
}
