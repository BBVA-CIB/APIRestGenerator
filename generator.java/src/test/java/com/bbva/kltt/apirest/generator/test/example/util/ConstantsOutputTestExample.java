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
	/*********************** Spring parameters ***************************/
	
	/** Spring - Type - FormData - MultipartFile */
	public static final String SPRING_FORM_DATA_MULTPART    = "MultipartFile" ;
	
	/** Spring - Annotation - RequestBody */
	public static final String SPRING_ANN_REQUEST_BODY      = "@RequestBody" ;
	/** Spring - Annotation - RequestParam */
	public static final String SPRING_ANN_REQUEST_PARAM     = "@RequestParam" ;
	/** Spring - Annotation - RequestHeader */
	public static final String SPRING_ANN_REQUEST_HEADER    = "@RequestHeader" ;
	/** Spring - Annotation - PathVariable */
	public static final String SPRING_ANN_PATH_VARIABLE     = "@PathVariable" ;
	
	
	/******************** Velocity - Spring Templates ********************/
	
	/** Velocity template - Spring template - RestHandlerMethodHeadersTestExampleInterfaceTemplate */
	public static final String VP_SPR_TEMPL_REST_MET_HEA_IN = "RestHandlerMethodHeadersTestExampleInterfaceTemplate" ;
	/** Velocity template - Spring template - RestHandlerMethodHeadersTestExampleImplTemplate */
	public static final String VP_SPR_TEMPL_REST_MET_HEA_IM = "RestHandlerMethodHeadersTestExampleImplTemplate" ;
	/** Velocity template - Spring template - RestHandlerMethodHeadersTestExampleImplTemplateTest */
	public static final String VP_SPR_TEMPL_REST_MET_H_IM_T = "RestHandlerMethodHeadersTestExampleImplTemplateTest" ;
	/** Velocity template - Spring template - RestListenerMethodHeadersTestExampleInterfaceTemplate */
	public static final String VP_SPR_TEMPL_LIST_MET_H_IN   = "RestListenerMethodHeadersTestExampleInterfaceTemplate" ;


	/******************** Output types - specific ***********************/

	/** Output type - File */
	public static final String OUTPUT_TYPE_FILE			   	= "byte[]" ;
	
	
	/************* Additional imports for the example classes **********/
	
	/** Additional import - Example Listener - Spring - MultipartFile */
	public static final String SPRING_MULTIPART_CLASS_PKG   = "org.springframework.web.multipart.MultipartFile" ;
	/** Additional import - Example Listener - Spring - Java IO Exception */
	public static final String JAVA_IO_IO_EXCEPTION_PKG     = "java.io.IOException" ;

	
	/*********************** Velocity parameters ***********************/
	
	/** Additional constants - Port */
	public static final String VP_SPR_PORT_NUMBER   		= "portNumber" ;
	
	/**************************** File names ***************************/
	
	/** Application properties - file name */
	public static final String APP_PROPERTIES_FILE_NAME		= "application" ;


	/************************ Module properties ************************/

	/** Path to VM files parent directory */
	public static final String PACKAGE_VM_RESOURCES			= "com.bbva.kltt.apirest.generator.server.spring.velocity" ;

	/** Name of the module */
	public static final String MODULE_NAME					= "test.example";

	/** Name to display on website */
	public static final String OFF_MODULE_NAME				= "Test Example";

	/** Class with the generate method */
	public static final String GENERATOR_INIT_CLASS	   		= "GeneratorGlobalTestExample" ;

	/** Type of generator (client or server) */
	public static final String TYPE_MODULE					= "test";
	
	/** Project name - Server Spring */
	public static final String PROJECT_NAME					= ConstantsOutput.PREFIX_GENERATOR_PROJECTS + ConstantsCommon.STRING_DOT + MODULE_NAME ;

	
	/**
	 * Private constructor
	 */
	private ConstantsOutputTestExample()
	{
		// Empty constructor
	}
}
