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

package com.bbva.kltt.apirest.generator.client.web.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutputClientWeb
{
	/********************* Generator directories **************************/
	
	/** Generator Sources Directory - Web */
	public static final String GEN_RESOURCES_DIR_WEB 	   = "resource" ;
	
	
	/*********************** Web types ****************************/
	
	/** Web - type - definition - var */
	public static final String WEB_TYPE_VAR 		       = "var" ;
	/** Web - type - definition - boolean */
	public static final String WEB_TYPE_BOOLEAN	    	   = "boolean" ;
	/** Web - type - definition - number */
	public static final String WEB_TYPE_NUMBER 	    	   = "number" ;
	/** Web - type - definition - string */
	public static final String WEB_TYPE_STRING 	    	   = "string" ;
	/** Web - type - definition - file */
	public static final String WEB_TYPE_FILE 	    	   = "any" ;

	
	/********************* Web class names ************************/
	
	/** Velocity parameter - Web - randomValuesClassName */
	public static final String VP_WEB_RAND_VAL_CL_NAME     = "randomValuesClassName" ;
	/** Velocity parameter - Web - parserValuesClassName */
	public static final String VP_WEB_PAR_VAL_CL_NAME      = "parserValuesClassName" ;
	/** Velocity parameter - Web - Example imports JS */	
	public static final String VP_WEB_EXAMP_IMPORTS_JS     = "exampleImportsJS" ;
	/** Velocity parameter - Web - Example imports CSS */	
	public static final String VP_WEB_EXAMP_IMPORTS_CSS    = "exampleImportsCSS" ;
	/** Velocity parameter - Web - Example Tester ref */
	public static final String VP_WEB_EXAMP_TESTER_CL_NAME = "exampleTesterClassName" ;
	/** Velocity parameter - Web - Original reference class from server */
	public static final String VP_WEB_ORIGINAL_REF_CL_NAME = "originalReferenceClassName" ;

	/** Velocity class name - Web - RestListener */
	public static final String CLASSNAME_REST_LISTENER_WEB = "RestListener" ;
	/** Velocity html file - Web - index.html */
	public static final String INDEX_PAGE_WEB  		       = "index" ;
	/** Velocity html file - Web - Example Tester */
	public static final String EXAMPLE_TESTER_WEB		   = "ExampleTester" ;
	/** Velocity class name - Web - RandomValues */
	public static final String CLASSNAME_RANDOM_VALUES_WEB = "RandomValues" ;
	/** Velocity class name - Web - ParserValues */
	public static final String CLASSNAME_PARSER_VALUES_WEB = "ParserValues" ;
		
		
	/*************** Velocity - Constants - Web *****************/
	
	/** Velocity - Constants - Web - Web file name */
	public static final String VEL_WEB_CFG_FILE_NAME 	   = "tsconfig" ;
	
	
	/*************** Web - External libraries *******************/
	
	/** Web - External libraries - ES6 Promise */
	public static final String WEB_EXT_LIBS_ES6_PROMISE	   = "es6-promise" ;
	/** Web - External libraries - JQuery */
	public static final String WEB_EXT_LIBS_JQUERY		   = "jquery" ;
	/** Web - External libraries - RX ALL */
	public static final String WEB_EXT_LIBS_RX_ALL		   = "rx" ;
	
	/** Web - External libraries Example - Bootstrap */
	public static final String WEB_BOOTSTRAP			   = "bootstrap" ;
	
	
	/********************** Web - Folders ************************/
	
	/** Web - Web main folder */
	public static final String WEB_JS_MAIN_FOLDER		   = "js" ;
	/** Web - CSS main folder */
	public static final String WEB_CSS_MAIN_FOLDER   	   = "css" ;
	
	/** Web - external folder */
	public static final String WEB_EXTERNAL_FOLDER   	   = "external" ;
	/** Web - external folder */
	public static final String WEB_GENERATED_FOLDER  	   = "generated" ;	
	/** Web - external folder */
	public static final String WEB_EXAMPLE_FOLDER    	   = "example" ;
	
	/** Web - Folder rest */
	public static final String WEB_FOLDER_REST			   = "rest" ;
	
	
	/********************* Web - File names **********************/
	
	/** CSS - External libraries - Bootstrap */
	public static final String CSS_BOOSTRAP				   = "bootstrap" ;
	/** CSS - External libraries - Example */
	public static final String CSS_EXAMPLE				   = "example" ;
	/** CSS - External libraries - Example fonts */
	public static final String CSS_EXAMPLE_FONTS		   = "example-fonts" ;
	
	
	/****************** Web Generated code tokens *****************/
	
	/** Default value test - Angular2 - null */
	public static final String DEFAULT_VAL_TEST_WEB_NULL   = "null" ;
	
	
	/************************ Project Name ******************************/
	
	/** Project name - Java */
	public static final String PROJECT_NAME 			   = ConstantsOutput.PREFIX_GENERATOR_PROJECTS + ConstantsCommon.STRING_DOT + "client.web"  ;


	/**
	 * Private constructor
	 */
	private ConstantsOutputClientWeb()
	{
		// Empty constructor
	}
}
