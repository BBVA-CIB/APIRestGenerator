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

package com.bbva.kltt.apirest.generator.client.feign.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutputClientFeign
{
	/********************* Common directories **************************/
	
	/** Common Feign directory - Common parent directory */
	public static final String COMMON_JAVA_DIR_PARENT 		= "common/" ;
	/** Common Feign directory - Macros */
	public static final String COMMON_JAVA_DIR_MACROS	    = ConstantsOutputClientFeign.COMMON_JAVA_DIR_PARENT + "macros/" ;
	
	/******************** Velocity - Feign Templates ********************/
	
	/** Velocity template - Feign template - RestHandlerMethodHeadersClientFeignInterfaceTemplate */
	public static final String VP_FEI_TEMPL_REST_MET_HEA_IN = "RestHandlerMethodHeadersClientFeignInterfaceTemplate" ;
	/** Velocity template - Feign template - RestHandlerMethodHeadersClientFeignImplTemplate */
	public static final String VP_FEI_TEMPL_REST_MET_HEA_IM = "RestHandlerMethodHeadersClientFeignImplTemplate" ;
	/** Velocity template - Feign template - HystrixFallbackMethodHeadersClientFeignImplTemplate */
	public static final String VP_FEI_TEMPL_HYST_MET_HEA_IM = "HystrixFallbackMethodHeadersClientFeignImplTemplate" ;
	/** Velocity template - Feign template - RestHandlerMethodHeadersClientFeignImplTemplateTest */
	public static final String VP_FEI_TEMPL_REST_MET_H_IM_T = "RestHandlerMethodHeadersClientFeignImplTemplateTest" ;
	/** Velocity template - Feign template - RestListenerMethodHeadersClientFeignInterfaceTemplate */
	public static final String VP_FEI_TEMPL_LIST_MET_H_IN   = "RestListenerMethodHeadersClientFeignInterfaceTemplate" ;
	/** Velocity template - Feign template - ExampleLauncherClientFeignTemplate */
	public static final String VP_FEI_TEMPL_EXAM_LAUNCHER   = "ExampleLauncherClientFeignTemplate" ;
	/** Velocity template - Feign template - ApplicationPropertiesClientFeignTemplate */
	public static final String VP_FEI_TEMPL_EXAM_APP_PROP   = "ApplicationPropertiesClientFeignTemplate" ;

	
	/******************** Velocity - Feign Templates ********************/
	
	/** Velocity template - Feign Macro - CommonClientFeignMacros */
	public static final String VP_FEI_COMMON_MACROS         = "CommonClientFeignMacros" ;
	/** Velocity template - Feign Macro - CommonClientFeignMacros */
	public static final String VP_FEI_EXAM_LAUNCHER_MACROS  = "ExampleLauncherClientFeignMacros" ;
	
	
	/*********************** Velocity parameters ***********************/
	
    /** Velocity parameter - Port */
    public static final String VP_FEIGN_PORT_NUMBER   		= "portNumber" ;
	/** Velocity parameter - Start-class */
	public static final String VP_FEIGN_PROJECT_TITLE       = "projectTitle" ;
    /** Velocity parameter - Start-class */
    public static final String VP_FEIGN_REST_INTERFACE_PACK = "restInterfacePackage" ;
	
	
	/**************************** File names ***************************/
	
	/** Application properties - file name */
	public static final String APP_PROPERTIES_FILE_NAME		= "application" ;
	
	
	/****************** Classes and interfaces name *********************/
	
	/** Feign - Interface name - IHystrixFallback */
	public static final String INTERF_NAME_HYSTRIX_FALLBACK	= "IHystrixFallback" ;
	/** Feign - Interface name - HystrixFallbackImpl */
	public static final String CLASS_NAME_HYSTRIX_FALLBACK	= "HystrixFallbackImpl" ;
	/** Feign - Remote Service Name - YAML Param */
	public static final String REMOTE_SERV_NAME_YAML_PARAM	= "${feign.name." ;
	
	
	/********************** Velocity parameters *************************/
	
	/** Feign - Interface name - HystrixFallBack */
	public static final String VP_INT_NAME_HYSTRIX_FALLBACK	= "hystrixFallbackInterfaceName" ;
	/** Feign - Interface name - HystrixFallBack - Example */
	public static final String VP_IMP_NAME_HYSTRIX_FALLBACK	= "hystrixFallbackClassName" ;
	/** Feign - Remote Service Name - YAML Param */
	public static final String VP_REM_SERV_NAME_YAML_PARAM	= "remoteServiceNameYamlParam" ;
	
	
	
	/*********************** Feign parameters ***************************/
	
	/** Feign - Type - FormData - MultipartFile */
	public static final String FEIGN_FORM_DATA_MULTPART     = "MultipartFile" ;
	
	/** Feign - Annotation - RequestBody */
	public static final String FEIGN_ANN_REQUEST_BODY       = "@RequestBody" ;
	/** Feign - Annotation - RequestParam */
	public static final String FEIGN_ANN_REQUEST_PARAM      = "@RequestParam" ;
	/** Feign - Annotation - RequestHeader */
	public static final String FEIGN_ANN_REQUEST_HEADER     = "@RequestHeader" ;
	/** Feign - Annotation - PathVariable */
	public static final String FEIGN_ANN_PATH_VARIABLE      = "@PathVariable" ;
	
	
	/******************** Output types - specific ***********************/

	/** Output type - File */
	public static final String OUTPUT_TYPE_FILE			   	= "byte[]" ;
	
	
	/************* Additional imports for the example classes **********/
	
	/** Additional import - Example Listener - Feign - MultipartFile */
	public static final String SPRING_MULTIPART_CLASS_PKG   = "org.springframework.web.multipart.MultipartFile" ;
	/** Additional import - Example Listener - Feign - Java IO Exception */
	public static final String JAVA_IO_IO_EXCEPTION_PKG     = "java.io.IOException" ;

	
	/************************ Module properties ************************/

	/** Path to VM files parent directory */
	public static final String PACKAGE_VM_RESOURCES			= "com.bbva.kltt.apirest.generator.client.feign.velocity" ;

	/** Name of the module */
	public static final String MODULE_NAME					= "client.feign";

	/** Name to display on website */
	public static final String OFF_MODULE_NAME				= "Feign";

	/** Class with the generate method */
	public static final String GENERATOR_INIT_CLASS	   		= "GeneratorGlobalClientFeign" ;

	/** Type of generator (client or server) */
	public static final String TYPE_MODULE					= "client";
	
	/** Project name - Client Feign */
	public static final String PROJECT_NAME					= ConstantsOutput.PREFIX_GENERATOR_PROJECTS + ConstantsCommon.STRING_DOT + MODULE_NAME ;
	
	
	/**
	 * Private constructor
	 */
	private ConstantsOutputClientFeign()
	{
		// Empty constructor
	}
}
