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

package com.bbva.kltt.apirest.core.util;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutput
{
    /** Package root - Prefix */
    public static final String PACKAGE_ROOT_PREFIX	        = "com.bbva." ;
    /** Package root - Suffix */
    public static final String PACKAGE_ROOT_SUFFIX	        = ".apirestgen" ;


    /************************* Common Generation *********************************/
	
	/** This constant is useful for parent languages which has common generations for children languages like Java (models) */
	public static final String COMMON_LANGUAGE				= "common" ;
	public static final String COMMON_GEN_TYPE_PACKAGE		= "com.bbva.kltt.apirest.generator";
	
	
	/************************ Output code Types **********************************/

	/** Output code type - model */
	public static final String OUTPUT_CODE_TYPE_MODELS	    = "model" ;
	/** Output code type - rest */
	public static final String OUTPUT_CODE_TYPE_REST	    = "rest" ;
	/** Output code type - example */
	public static final String OUTPUT_CODE_TYPE_EXAMPLE	    = "example" ;
	/** Output code type - utils */
	public static final String OUTPUT_CODE_TYPE_UTILS	    = "utils" ;
	
	/** Output code type - subtype - interfaces */
	public static final String OUTPUT_CODE_SUBTYPE_INTERF	= "interfaces" ;
	/** Output code type - subtype - implementation */
	public static final String OUTPUT_CODE_SUBTYPE_IMPL		= "impl" ;
	
	
	/********************* Common Templates Parameters ***************************/
	
	/** Common template parameter - now */
	public static final String COMMON_TEMP_PARAM_NOW    	= "now" ;
	/** Common template parameter - Output Language Items */
	public static final String COMMON_TEMP_OUT_LANG_ITEMS   = "outputLanguageItems" ;
	/** Common template parameter - Output Language Parameters */
	public static final String COMMON_TEMP_OUT_LANG_PARAMS  = "outputLanguageParameters" ;
	/** Common template parameter - Output Language Consumes Produces */
	public static final String COMMON_TEMP_OUT_LANG_CON_PRO = "outputLanguageConsumesProduces" ;
	/** Common template parameter - Output Language Naming */
	public static final String COMMON_TEMP_OUT_LANG_NAMING  = "outputLanguageNaming" ;
	/** Common template parameter - Output Language Generator properties parameters */
	public static final String COMMON_TEMP_OUT_LANG_GEN_P   = "outputLanguagebGeneratorParams" ;
	/** Common template parameter - Output Language Root values */
	public static final String COMMON_TEMP_OUT_LANG_ROOT_V  = "outputLanguageRootValues" ;
	/** Common template parameter - Output Language Paths */
	public static final String COMMON_TEMP_OUT_LANG_PATHS   = "outputLanguagePaths" ;
	/** Common template parameter - Output Language Operations */
	public static final String COMMON_TEMP_OUT_LANG_OPERAT  ="outputLanguageOperations" ;
	/** Common template parameter - Output Language Exceptions */
	public static final String COMMON_TEMP_OUT_LANG_EXCEPT  = "outputLanguageExceptions" ;

	
	/************************** File extensions **********************************/
	
	/** Extension - XML */
	public static final String EXTENSION_XML			    = "xml" ;
	/** Extension - POM */
	public static final String EXTENSION_POM			    = "pom" ;
	/** Extension - Java */
	public static final String EXTENSION_JAVA			    = "java" ;
	/** Extension - JavaScript */
	public static final String EXTENSION_JAVASCRIPT		    = "js" ;
	/** Extension - TypeScript */
	public static final String EXTENSION_TYPESCRIPT		    = "ts" ;
	/** Extension - Velocity */
	public static final String EXTENSION_VELOCITY		    = "vm" ;
	/** Extension - HTML */
	public static final String EXTENSION_HTML		    	= "html" ;
	/** Extension - CSS */
	public static final String EXTENSION_CSS		    	= "css" ;
	/** Extension - CMD */
	public static final String EXTENSION_CMD		    	= "cmd" ;
	/** Extension - SH */
	public static final String EXTENSION_SH		    		= "sh" ;
	/** Extension - Properties */
	public static final String EXTENSION_PROPERTIES	    	= "properties" ;
	/** Extension - Properties */
	public static final String EXTENSION_YAML		    	= "yml" ;
	/** Extension - Properties */
	public static final String EXTENSION_ZIP		    	= "zip" ;
	
	
	/************************** Common Types *****************************/
	
	/** Common - type - Array */
	public static final String COMMON_TYPE_ARRAY   	   	    = "[]" ;
	
	
	/*********************** Common Parameters ***************************/
	
	/** Common - Annotation - Alias parameter */
	public static final String COMMON_ANNOTATION_ALIAS_PAR  = "value" ;
	/** Common - Annotation - Required */
	public static final String COMMON_ANNOTATION_REQUI_PAR  = "required" ;
	
	
	/********************* Velocity Common Param *************************/
	
	public static final String VEL_TABULATOR				= "\t" ;
	
	
	/*********************** Velocity parameters *************************/
	
	/** Velocity parameter - tab */
	public static final String VEL_PAR_TAB 	    			= "tab" ;
	
	/** Velocity parameter - package name */
    public static final String VP_PACKAGE_NAME 	    		= "packageName" ;
    /** Velocity parameter - model package */
    public static final String VP_MODEL_PACKAGE 	    	= "modelPackage" ;
    /** Velocity parameter - rest package */
    public static final String VP_REST_PACKAGE 	    		= "restPackage" ;
    /** Velocity parameter - model class name */
    public static final String VP_MODEL_CLASSNAME 	   		= "modelClassName" ;
    /** Velocity parameter - additionalImports */
	public static final String VP_ADDITIONAL_IMPORTS   		= "additionalImports" ;
	/** Velocity parameter - classDescription */
	public static final String VP_CLASS_DESCRIPTION    		= "classDescription" ;
	/** Velocity parameter - className */
	public static final String VP_CLASS_NAME 		    	= "className" ;
	/** Velocity parameter - attributes */
	public static final String VP_ATTRIBUTES 		    	= "attributes";
	/** Velocity parameter - attribute referenced */
	public static final String VP_ATTRIBUTE_REF	    		= "attributeRef";
	/** Velocity parameter - languageHandler */	
	public static final String VP_LANGUAGE_HANDLER     		= "languageHandler";
	/** Velocity parameter - response exception */
	public static final String VP_RESP_EXCEPTION 			= "responseException" ;
	/** Velocity parameter - response exception attribute */
	public static final String VP_RESP_EXCEPTION_ATTRIBUTE 	= "responseExceptionAttribute" ;
	
	/** Velocity parameter - iGeneratedModel */	
	public static final String VP_GEN_MODEL_CL_NAME    		= "iGeneratedModelClassName" ;
	/** Velocity parameter - iGeneratedModel */	
	public static final String VP_JACKSON_MAP_CL_NAME  		= "jacksonMapperClassName" ;
	/** Velocity parameter - restHandlerInterfaceName */	
	public static final String VP_R_HANDLER_INTERFACE_NAME  = "restHandlerInterfaceName" ;
	/** Velocity parameter - restHandlerClassName */	
	public static final String VP_R_HANDLER_CL_NAME    		= "restHandlerClassName" ;
	/** Velocity parameter - restHandlerTestClassName */
	public static final String VP_R_HANDLER_T_CL_NAME  		= "restHandlerTestClassName" ;
	/** Velocity parameter - restListenerInterfaceName */	
	public static final String VP_R_LISTE_INTERFACE_NAME    = "restListenerInterfaceName" ;
	/** Velocity parameter - restListenerTestClassName */	
	public static final String VP_R_LISTE_T_CL_NAME    		= "restListenerTestClassName" ;
	/** Velocity parameter - restListenerExampleClassName */
	public static final String VP_LIST_EXAMP_CL_NAME   		= "restListenerExampleClassName" ;
	/** Velocity parameter - schemesValuesClassName */
	public static final String VP_SCHEMES_VAL_CL_NAME     	= "schemesValuesClassName" ;
	

	/*********************** velocity class name ***********************/
	
	/** Velocity class name - RestHandler */
	public static final String CLASSNAME_REST_HANDLER       = "RestHandler" ;
	/** Velocity class name - SchemesValues */
	public static final String CLASSNAME_SCHEMES_VALUES		= "SchemesValues" ;
	/** Velocity class name - CommonException */
	public static final String CLASSNAME_COMMON_EXCEPTION	= "CommonException" ;
	
	
	/**************** Folders hierarchy name ***************************/
	
	/** Folders name - Model */
	public static final String FOLDER_MODEL 				= "model" ;
	/** Folders name - Model Exception */
	public static final String FOLDER_MODEL_EXCEPTION		= "model.exception" ;
	/** Folders name - Rest Interfaces */
	public static final String FOLDER_REST					= "rest" ;
	/** Folders name - Example */
	public static final String FOLDER_EXAMPLE 				= "example" ;
	/** Folders name - Utils */
	public static final String FOLDER_UTILS 				= "utils" ;
	
	
	/************** Generator information file name *********************/
	
	/** Generator information - File name */
	public static final String GENERATOR_INFO_FILE_NAME		= "project.properties" ;
	/** Project name - Core */
	public static final String PROJECT_NAME                 = "core" ;
	/** Prefix - Generator - Projects */
	public static final String PREFIX_GENERATOR_PROJECTS	= "generator" ;
	
	
	/********************** Directories back ****************************/
	
	/** One directory back */
	public static final String DIRECTORY_BACK_ONE			= "../" ;
	/** Two directory back */
	public static final String DIRECTORY_BACK_TWO			= ConstantsOutput.DIRECTORY_BACK_ONE   + ConstantsOutput.DIRECTORY_BACK_ONE ;
	/** Three directory back */
	public static final String DIRECTORY_BACK_THREE			= ConstantsOutput.DIRECTORY_BACK_TWO   + ConstantsOutput.DIRECTORY_BACK_ONE ;
	/** Four directory back */
	public static final String DIRECTORY_BACK_FOUR			= ConstantsOutput.DIRECTORY_BACK_THREE + ConstantsOutput.DIRECTORY_BACK_ONE ;
	/** Five directory back */
	public static final String DIRECTORY_BACK_FIVE			= ConstantsOutput.DIRECTORY_BACK_FOUR  + ConstantsOutput.DIRECTORY_BACK_ONE ;

	
	/***************************  Exceptions Constants ****************************/
	
	/** Exception Prefix Custom Class Name */
	public static final String EXC_PREFIX_CUSTOM_CLASS_NAME = "Exception";
	

	/************************** Other constants ************************/

	/** Regular Expression - Host and port */
	public static final String PATT_HOST_AND_PORT 			= "[a-zA-Z0-9_\\.\\-]+:([0-9]+)\\/?" ;

	/** Default port number */
	public static final int DEFAULT_PORT_NUMBER				= 80 ;
	
	
	/**
	 * Private constructor
	 */
	private ConstantsOutput()
	{
		// Empty constructor
	}
}
