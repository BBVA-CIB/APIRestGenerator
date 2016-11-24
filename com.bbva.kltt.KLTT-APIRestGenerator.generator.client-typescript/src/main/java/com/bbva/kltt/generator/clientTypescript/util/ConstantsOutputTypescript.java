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

package com.bbva.kltt.generator.clientTypescript.util;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutputTypescript
{
	/********************* Generator directories **************************/
	
	/** Generator Sources Directory - TypeScript */
	public static final String GEN_RESOURCES_DIR_TYPESCRIPT = "resource" ;
	
	
	/*********************** Typescript types ****************************/
	
	/** TypeScript - type - definition - var */
	public static final String TYPESCRIPT_TYPE_VAR 		    = "var" ;
	/** TypeScript - type - definition - boolean */
	public static final String TYPESCRIPT_TYPE_BOOLEAN	    = "boolean" ;
	/** TypeScript - type - definition - number */
	public static final String TYPESCRIPT_TYPE_NUMBER 	    = "number" ;
	/** TypeScript - type - definition - string */
	public static final String TYPESCRIPT_TYPE_STRING 	    = "string" ;
	/** TypeScript - type - definition - file */
	public static final String TYPESCRIPT_TYPE_FILE 	    = "any" ;

	
	/********************* Typescript class names ************************/
	
	/** Velocity parameter - TypeScript - randomValuesClassName */
	public static final String VP_TS_RAND_VAL_CL_NAME  		= "randomValuesClassName" ;
	/** Velocity parameter - TypeScript - parserValuesClassName */
	public static final String VP_TS_PAR_VAL_CL_NAME     	= "parserValuesClassName" ;
	/** Velocity parameter - TypeScript - Example imports JS */	
	public static final String VP_TS_EXAMP_IMPORTS_JS  		= "exampleImportsJS" ;
	/** Velocity parameter - TypeScript - Example imports CSS */	
	public static final String VP_TS_EXAMP_IMPORTS_CSS 		= "exampleImportsCSS" ;

	/** Velocity class name - TypeScript - RestListener */
	public static final String CLASSNAME_REST_LISTENER_TS   = "RestListener" ;
	/** Velocity html file - TypeScript - index.html */
	public static final String INDEX_PAGE_TS   				= "index" ;
	/** Velocity html file - TypeScript - Example Tester */
	public static final String EXAMPLE_TESTER_TS			= "ExampleTester" ;
	/** Velocity class name - TypeScript - RandomValues */
	public static final String CLASSNAME_RANDOM_VALUES_TS   = "RandomValues" ;
	/** Velocity class name - TypeScript - ParserValues */
	public static final String CLASSNAME_PARSER_VALUES_TS   = "ParserValues" ;
	
	
	/***************** Velocity - Templates - Typescript ****************/
	
	/** Velocity template - MethodCommentsTypescriptTemplate */
	public static final String VP_TS_TEMPL_METH_COMMENTS	= "MethodCommentsTypescriptTemplate" ;
	/** Velocity template - RestHandlerMethodHeadersTypescriptTemplate */
	public static final String VP_TS_TEMPL_REST_MET_HEA 	= "RestHandlerMethodHeadersTypescriptTemplate" ;
	/** Velocity template - RestHandlerMethodHeadersTypescriptTemplateTest */
	public static final String VP_TS_TEMPL_REST_MET_H_T 	= "RestHandlerMethodHeadersTypescriptTemplateTest" ;
	/** Velocity template - RestListenerMethodHeadersTypescriptTemplate */
	public static final String VP_TS_TEMPL_LIST_MET_H   	= "RestListenerMethodHeadersTypescriptTemplate" ;
	/** Velocity template - RestListenerMethodHeadersTypescriptTemplateTest */
	public static final String VP_TS_TEMPL_LIST_MET_H_T 	= "RestListenerMethodHeadersTypescriptTemplateTest" ;
	/** Velocity template - ExampleTesterMethodsTypescriptTemplate */
	public static final String VP_TS_TEMPL_EXA_TEST_MET 	= "ExampleTesterMethodsTypescriptTemplate" ;
	/** Velocity Parameter - Spring template - ListenerExampleTypescriptTemplate */
	public static final String VP_TS_TEMPL_LISTE_EXA 		= "ListenerExampleTypescriptTemplate" ;
	/** Velocity Parameter - Spring template - ListenerExampleMethodsTypescriptTemplate */
	public static final String VP_TS_TEMPL_LISTE_EXA_ME 	= "ListenerExampleMethodsTypescriptTemplate" ;
	
	
	/*************** Velocity - Macros - TypeScript ********************/
	
	/** Velocity Parameter - Java macro - CommonTypescriptMacros */
	public static final String VP_TS_MACRO_COMMON			= "CommonTypescriptMacros" ;
	/** Velocity template - ModelsComplexMacros */
	public static final String VP_TS_MACRO_MOD_COMPLEX  	= "ModelsComplexTypescriptMacros" ;
	/** Velocity template - RestHandlerMacros */
	public static final String VP_TS_MACRO_REST_HANDLER 	= "RestHandlerTypescriptMacros" ;
	/** Velocity template - ExampleTesterMacros */
	public static final String VP_TS_MACRO_EXAMPLE_TEST 	= "ExampleTesterTypescriptMacros" ;
	
	
	/*************** Velocity - Constants - TypeScript *****************/
	
	/** Velocity - Constants - TypeScript - TSConfig file name */
	public static final String VEL_TYPESCRIPT_CFG_FILE_NAME = "tsconfig" ;
	
	
	/*************** TypeScript - External libraries *******************/
	
	/** TypeScript - External libraries - ES6 Promise */
	public static final String JS_EXT_LIBS_ES6_PROMISE		= "es6-promise" ;
	/** TypeScript - External libraries - JQuery */
	public static final String JS_EXT_LIBS_JQUERY			= "jquery" ;
	/** TypeScript - External libraries - RX ALL */
	public static final String JS_EXT_LIBS_RX_ALL			= "rx" ;
	
	/** TypeScript - External libraries Example - Bootstrap */
	public static final String JS_BOOTSTRAP					= "bootstrap" ;
	
	
	/********************** Typescript - Folders ************************/
	
	/** Typescript - Javascript main folder */
	public static final String TYPESCRIPT_JS_MAIN_FOLDER	= "js" ;
	/** Typescript - CSS main folder */
	public static final String TYPESCRIPT_CSS_MAIN_FOLDER   = "css" ;
	
	/** Typescript - external folder */
	public static final String TYPESCRIPT_EXTERNAL_FOLDER   = "external" ;
	/** Typescript - external folder */
	public static final String TYPESCRIPT_GENERATED_FOLDER  = "generated" ;	
	/** Typescript - external folder */
	public static final String TYPESCRIPT_EXAMPLE_FOLDER    = "example" ;
	
	/** Typescript - Folder rest */
	public static final String TYPESCRIPT_FOLDER_REST		= "rest" ;
	
	
	/********************* Typescript - File names **********************/
	
	/** CSS - External libraries - Bootstrap */
	public static final String CSS_BOOSTRAP					= "bootstrap" ;
	/** CSS - External libraries - Example */
	public static final String CSS_EXAMPLE					= "example" ;
	/** CSS - External libraries - Example fonts */
	public static final String CSS_EXAMPLE_FONTS			= "example-fonts" ;
	
	
	/****************** Typescript Generated code tokens *****************/
	
	/** Default value test - Typescript - null */
	public static final String DEFAULT_VAL_TEST_TS_NULL     = "null" ;


	/************************ Module properties **************************/

	/** Path to VM files parent directory */
	public static final String PACKAGE_VM_RESOURCES			= "com.bbva.kltt.generator.clientTypescript" ;

	/** Name of the module */
	public static final String MODULE_NAME					= "clientTypescript";

	/** Name to display on website */
	public static final String OFF_MODULE_NAME				= "Javascript";

	/** Class with the generate method */
	public static final String GENERATOR_INIT_CLASS	   		= "GeneratorGlobalTypescript" ;

	/** Type of generator (client or server) */
	public static final String TYPE_MODULE					= "client";
	
	
	/**
	 * Private constructor
	 */
	private ConstantsOutputTypescript()
	{
		// Empty constructor
	}
}
