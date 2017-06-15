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

package com.bbva.kltt.apirest.generator.client.angular2.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutputClientAngular2
{
	/********************** Folders - Angular2 ************************/
	
	/** Angular2 - main folder */
	public static final String ANGULAR2_MAIN_FOLDER		 = "app" ;
	
	
	/**************** Velocity - Parameters - Angular2 ****************/
	
	/** Velocity - Parameter - Main JS Extension - Reference */
	public static final String VP_MAIN_JS_EXTENSION_REF  = "mainReferenceJSExtension" ;
	
	/** Velocity - Parameter - Module Class - Reference */
	public static final String VP_MODULE_CLASS_REF 		 = "moduleClassRef" ;
	
	/** Velocity - Parameter - Template Example - Reference */
	public static final String VP_TEMPLATE_EXAMPLE_REF   = "templateExampleRef" ;
	
	/***************** Velocity - Templates - Angular2 ****************/
	
	/** Velocity template - Angular2 template - MethodCommentsClientAngular2Template */
	public static final String VP_A2_TEMPL_METH_COMMENTS = "MethodCommentsClientAngular2Template" ;
	/** Velocity template - Angular2 template - RestHandlerMethodHeadersClientAngular2Template */
	public static final String VP_A2_TEMPL_REST_MET_HEA  = "RestHandlerMethodHeadersClientAngular2Template" ;
	/** Velocity template - Angular2 template - RestHandlerMethodHeadersClientAngular2TemplateTest */
	public static final String VP_A2_TEMPL_REST_MET_H_T  = "RestHandlerMethodHeadersClientAngular2TemplateTest" ;
	/** Velocity template - Angular2 template - RestListenerMethodHeadersClientAngular2Template */
	public static final String VP_A2_TEMPL_LIST_MET_H    = "RestListenerMethodHeadersClientAngular2Template" ;
	/** Velocity template - Angular2 template - RestListenerMethodHeadersClientAngular2TemplateTest */
	public static final String VP_A2_TEMPL_LIST_MET_H_T  = "RestListenerMethodHeadersClientAngular2TemplateTest" ;
	/** Velocity template - Angular2 template - ExampleTesterMethodsClientAngular2Template */
	public static final String VP_A2_TEMPL_EXA_TEST_MET  = "ExampleTesterMethodsClientAngular2Template" ;
	/** Velocity Parameter - Angular2 template - ListenerExampleClientAngular2Template */
	public static final String VP_A2_TEMPL_LISTE_EXA 	 = "ListenerExampleClientAngular2Template" ;
	/** Velocity Parameter - Angular2 template - ListenerExampleMethodsClientAngular2Template */
	public static final String VP_A2_TEMPL_LISTE_EXA_ME  = "ListenerExampleMethodsClientAngular2Template" ;
	
	
	/*************** Velocity - Macros - Angular2 ********************/
	
	/** Velocity Parameter - Java macro - CommonClientAngular2Macros */
	public static final String VP_A2_MACRO_COMMON		 = "CommonClientAngular2Macros" ;
	/** Velocity template - ModelsComplexClientAngular2Macros */
	public static final String VP_A2_MACRO_MOD_COMPLEX   = "ModelsComplexClientAngular2Macros" ;
	/** Velocity template - RestHandlerClientAngular2Macros */
	public static final String VP_A2_MACRO_REST_HANDLER  = "RestHandlerClientAngular2Macros" ;
	/** Velocity template - ExampleTesterClientAngular2Macros */
	public static final String VP_A2_MACRO_EXAMPLE_TEST  = "ExampleTesterClientAngular2Macros" ;
	/** Velocity template - ExampleTemplateClientAngular2Macros */
	public static final String VP_A2_MACRO_EXAMPLE_TEMPL = "ExampleTemplateClientAngular2Macros" ;
	
	
	/************************ Module properties **************************/

	/** Path to VM files parent directory */
	public static final String PACKAGE_VM_RESOURCES		 = "com.bbva.kltt.apirest.generator.client.angular2.velocity" ;

	/** Name of the module */
	public static final String MODULE_NAME				 = "client.angular2";

	/** Name to display on website */
	public static final String OFF_MODULE_NAME			 = "Angular2";

	/** Class with the generate method */
	public static final String GENERATOR_INIT_CLASS	   	 = "GeneratorGlobalClientAngular2" ;

	/** Type of generator (client or server) */
	public static final String TYPE_MODULE				 = "client" ;
	
	/** Project name - Client Jaxrs */
	public static final String PROJECT_NAME				 = ConstantsOutput.PREFIX_GENERATOR_PROJECTS + ConstantsCommon.STRING_DOT + MODULE_NAME ;
	
	
	/***************** Specific Names for Angular  ***********************/

	/** File name - SystemJS configuration - Example */
	public static final String SYSTEMJS_CFG_EXAMPLE    	 = "systemjs.config" ;
	
	/** File name - Main configuration - Example */
	public static final String MAIN_CFG_EXAMPLE    		 = "Main" ;
	
	/** File name - Module configuration - Example */
	public static final String MODULE_CFG_EXAMPLE  		 = "AppModule" ;
	
	/** File name - Template - Example */
	public static final String TEMPLATE_EXAMPLE  		 = "ExampleTemplate" ;
	
	/******************* Third party references **************************/
	
	/** Reference - External Lib - RXJS */
	public static final String R_EXT_LIB_RXJS	     	 = "rxjs/Rx" ;
	/** Dependency - 1 - External Lib - RXJS - Observable */
	public static final String D_1_EXT_LIB_RXJS 	 	 = "Observable" ;
	
	/** Reference - External Lib - Angular 2 Core */
	public static final String R_EXT_LIB_ANG_COR	 	 = "@angular/core" ;
	/** Dependency - 1 - External Lib - Angular 2 Core - Injectable */
	public static final String D_1_EXT_LIB_ANG_COR       = "Injectable" ;
	/** Dependency - 2 - External Lib - Angular 2 Core - Injectable */
	public static final String D_2_EXT_LIB_ANG_COR       = "Component" ;
	/** Dependency - 3 - External Lib - Angular 2 Core - NgModule */
	public static final String D_3_EXT_LIB_ANG_COR       = "NgModule" ;
	
	/** Reference - External Lib - Angular 2 Core Platform */
	public static final String R_EXT_LIB_ANG_COR_PT_B    = "@angular/platform-browser" ;
	/** Dependency - 1 - External Lib - Angular 2 Core Platform - Injectable */
	public static final String D_1_EXT_LIB_ANG_COR_PT_B  = "BrowserModule" ;
	
	/** Reference - External Lib - Angular 2 Core Platform dynamic */
	public static final String R_EXT_LIB_ANG_COR_PT_BD   = "@angular/platform-browser-dynamic" ;
	/** Dependency - 1 - External Lib - Angular 2 Core Platform dynamic - platformBrowserDynamic */
	public static final String D_1_EXT_LIB_ANG_COR_PT_BD = "platformBrowserDynamic" ;
	
	/** Reference - External Lib - RXJS Subject */
	public static final String R_EXT_LIB_RX_SUBJ	 	 = "rxjs/Subject" ;
	/** Dependency - 1 - External Lib - RXJS Subject */
	public static final String D_1_EXT_LIB_RX_SUBJ  	 = "Subject" ;
	
	/********* Index.html - Javascript dependencies - External *************/
	
	/** External - Javascript - Dependency - RX JS - Folder */
	public static final String RX_JS_FOLDER 	   		 = "node_modules/rxjs/bundles/" ;
	
	/** External - Javascript - Dependency - RX JS - File */
	public static final String RX_JS_FILE   	   		 = "Rx.js" ;

	/** External - Javascript - Dependency - JQuery - Folder */
	public static final String JQUERY_FOLDER 	   		 = "node_modules/jquery/dist/" ;
	
	/** External - Javascript - Dependency - JQuery - File */
	public static final String JQUERY_FILE 		   		 = "jquery.js" ;
    
	/** External - Javascript - Dependency - Core JS Shim - Folder */
    public static final String CORE_JS_SHIM_FOLDER 		 = "node_modules/core-js/client/" ;
    
    /** External - Javascript - Dependency - Core JS Shim - File */
    public static final String CORE_JS_SHIM_FILE   		 = "shim.min.js" ;
    
    /** External - Javascript - Dependency - Zone JS - Folder */
    public static final String ZONE_JS_FOLDER 		  	 = "node_modules/zone.js/dist/" ;
    
    /** External - Javascript - Dependency - Zone JS - File */
    public static final String ZONE_JS_FILE 		  	 = "zone.js" ;
    
    /** External - Javascript - Dependency - Reflect Metadata - Folder */
    public static final String REFLECTMETADATA_FOLDER 	 = "node_modules/reflect-metadata/" ;
    
    /** External - Javascript - Dependency - Reflect Metadata - File */
    public static final String REFLECTMETADATA_FILE 	 = "Reflect.js" ;
    
    /** External - Javascript - Dependency - System JS - Folder */
    public static final String SYSTEMJS_FOLDER			 = "node_modules/systemjs/dist/" ;
    
    /** External - Javascript - Dependency - System JS - File */
    public static final String SYSTEMJS_FILE 			 = "system.src.js" ;
    
    
    /********* Index.html - CSS dependencies - External *************/
    
    /** External - CSS - Dependency - Bootstrap - Folder */
    public static final String BOOTSTRAP_CSS_FOLDER		 = "node_modules/bootstrap/dist/css/" ;
    
    /** External - CSS - Dependency - Bootstrap - File */
    public static final String BOOTSTRAP_CSS_FILE 		 = "bootstrap.min.css" ;
    
	/**
	 * Private constructor
	 */
	private ConstantsOutputClientAngular2()
	{
		// Empty constructor
	}
}
