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

package com.bbva.kltt.apirest.generator.java.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutputJava
{
	/*********************** Generator directories *******************************/
	
	/** Generator Classes Directory - Java */
	public static final String GEN_CLASSES_DIR_JAVA 	    = "src/main/java" ;
	/** Generator Resources Directory - Java */
	public static final String GEN_RESOURCES_DIR_JAVA 	    = "src/main/resources" ;
	/** Generator Test Directory - Java */
	public static final String GEN_TEST_DIR_JAVA 		    = "src/test/java" ;
	/** Generator Test Resources Directory - Java */
	public static final String GEN_TEST_RESOURCES_DIR_JAVA  = "src/test/resources" ;
	
	
	/**************************** TopLevel POM *************************************/
	
	/** Velocity Parameter - Top Level POM - module */
	public static final String VP_TOP_LEVEL_POM_PROJ_MODULE	= "projectModule" ;
	
	/*************************** POM parameters ***********************************/
	
	/** Velocity Parameter - POM - groupId */
	public static final String VP_POM_PROJ_GROUP_ID		    = "projectGroupId" ;
	/** Velocity Parameter - POM - artifactId */
	public static final String VP_POM_PROJ_ARTIFACT_ID		= "projectArtifactId" ;
	/** Velocity Parameter - POM - Version */
	public static final String VP_POM_PROJ_VERSION			= "projectVersion" ;

	/** Velocity Parameter - POM - Translator Type */
	public static final String VP_POM_PROJ_TRANSLATOR_TYPE  = "projectTranslatorType" ;
	
	/** Velocity Parameter - POM - Description */
	public static final String VP_POM_PROJ_DESCRIPTION	  	= "projectDescription" ;
	
	/** Velocity Parameter - POM - Contact name */
	public static final String VP_POM_PROJ_CONTACT_NAME  	= "projectContactName" ;
	/** Velocity Parameter - POM - Contact email */
	public static final String VP_POM_PROJ_CONTACT_EMAIL  	= "projectContactEmail" ;
	/** Velocity Parameter - POM - Contact url */
	public static final String VP_POM_PROJ_CONTACT_URL	  	= "projectContactUrl" ;
	
	
	/*********************** Java Generated code tokens ***************************/
	
	/** Java token - final */
	public static final String JAVA_TOKEN_FINAL			    = "final" ;
	/** Default value test - Java - null */
	public static final String DEFAULT_VAL_TEST_JAVA_NULL   = "null" ;

	
	/************************** JAVA TYPES *******************************/
	
	/** JAVA - type - Boolean */
	public static final String JAVA_TYPE_BOOLEAN   	   	    = "Boolean" ;
	/** JAVA - type - Boolean */
	public static final String JAVA_TYPE_BOOLEAN_ARRAY 	    = "boolean";
	/** JAVA - type - Integer */
	public static final String JAVA_TYPE_INTEGER   	   	    = "Integer" ;
	/** JAVA - type - Integer */
	public static final String JAVA_TYPE_INTEGER_ARRAY 	    = "int" ;
	/** JAVA - type - Long */
	public static final String JAVA_TYPE_LONG   	   	    = "Long" ;
	/** JAVA - type - Long */
	public static final String JAVA_TYPE_LONG_ARRAY    	    = "long" ;
	/** JAVA - type - Float */
	public static final String JAVA_TYPE_FLOAT   	   	    = "Float" ;
	/** JAVA - type - Float */
	public static final String JAVA_TYPE_FLOAT_ARRAY   	    = "float" ;
	/** JAVA - type -  Double */
	public static final String JAVA_TYPE_DOUBLE   	   	    = "Double" ;
	/** JAVA - type -  Double */
	public static final String JAVA_TYPE_DOUBLE_ARRAY  	    = "double" ;
	/** JAVA - type - String */
	public static final String JAVA_TYPE_STRING   	   	    = "String" ;
	/** JAVA - type - String */
	public static final String JAVA_TYPE_STRING_ARRAY  	    = "String" ;
	
	/** JAVA - type - void */
	public static final String JAVA_TYPE_VOID			    = "void" ;
	
	
	/*************** Velocity - Java Common Macros *********************/
	
	/** Velocity Parameter - Java macro - CommonJavaMacros */
	public static final String VP_JAVA_MACRO_COMMON			= "CommonJavaMacros" ;
	/** Velocity Parameter - Java macro - MvnInstallFileMacros */
	public static final String VP_JAVA_MACRO_COMMON_MVN_INS = "MvnInstallFileMacros" ;
	
	
	/**************** Velocity - Java Common Templates *****************/
	
	/** Velocity Parameter - Java template - MethodCommentsJavaTemplate */
	public static final String VP_JAVA_TEMPL_METH_COMMENTS	= "MethodCommentsJavaTemplate" ;
	/** Velocity Parameter - Java macro - RandomGeneratorJavaMacros */
	public static final String VP_JAVA_RANDOM_GENERAT_METH	= "RandomGeneratorJavaMacros" ;
	
	
	/***************** Velocity - Java Macros - Models *****************/
	
	/** Velocity Parameter - Java macro - FillRandomlyJavaMacros */
	public static final String VP_JAVA_MACRO_FILL_RANDOMLY	= "FillRandomlyJavaMacros" ;
	
	
	/*************** Velocity - Java Templates - Models ****************/
	
	/** Velocity Parameter - Java template - AttributesDefinitionJavaTemplate */
	public static final String VP_JAVA_TEMPL_ATTR_DEFINITI	= "AttributesDefinitionJavaTemplate" ;
	/** Velocity Parameter - Java template - GetterSetterJavaTemplate */
	public static final String VP_JAVA_TEMPL_GET_SET 		= "GetterSetterJavaTemplate" ;
    /** Velocity Parameter - Java template - FillRandomlyJavaTemplate */
	public static final String VP_JAVA_TEMPL_FILL_RANDOMLY	= "FillRandomlyJavaTemplate" ;
	
	
	/******** Velocity - Java Templates - Listener Example *************/
	
	/** Velocity Parameter - Java template - ListenerExampleJavaServerTemplate */
	public static final String VP_COMMON_J_T_LISTE_EXA_SE 	= "ListenerExampleJavaServerTemplate" ;
	/** Velocity Parameter - Java template - ListenerExampleMethodsJavaServerTemplate */
	public static final String VP_COMM_J_T_LISTE_EXA_SE_ME  = "ListenerExampleMethodsJavaServerTemplate" ;
	/** Velocity Parameter - Java template - ListenerExampleJavaClientTemplate */
	public static final String VP_COMMON_J_T_LISTE_EXA_CL 	= "ListenerExampleJavaClientTemplate" ;
	/** Velocity Parameter - Java template - ListenerExampleMethodsJavaClientTemplate */
	public static final String VP_COMM_J_T_LISTE_EXA_CL_ME  = "ListenerExampleMethodsJavaClientTemplate" ;
	
	
	/********** Velocity - Java Macro - Listener Example ***************/
	
	/** Velocity Parameter - Java macro - ListenerExampleJavaClientMacros */
	public static final String VP_COMM_J_M_LISTE_EXA_CL_ME  = "ListenerExampleJavaClientMacros" ;
	
	
	/*********************** Velocity parameters ***********************/

    /** Velocity parameter - restHandlerTestClassName */
    public static final String VP_RANDOM_UTILS_CL_NAME  	= "randomUtilsClassName" ;
    /** Velocity parameter - injectListenerMethodName */
    public static final String VP_INJECT_METHOD_NAME  	    = "injectListenerMethodName" ;
    /** Velocity parameter - injectListener value */
    public static final String VP_INJECT_METHOD_VALUE  	    = "injectListener" ;
    /** Velocity parameter - interfaceGeneratedExceptionClassName */
    public static final String VP_INTERFACE_GEN_EXC_NAME 	= "generatedExceptionInterfaceName" ;
    
    
    /*********************** Velocity class name ***********************/
    
	/** Velocity class name - JacksonViews.GenerationView */
	public static final String CLASSNAME_GENERATION_VIEW    = "JacksonViews.GenerationView" ;
	/** Velocity class name - JacksonMapper */
	public static final String CLASSNAME_JACKSON_MAPPER     = "JacksonMapper" ;
	/** Velocity class name - JacksonViews */
	public static final String CLASSNAME_JACKSON_VIEWS      = "JacksonViews" ;
	/** Velocity class name - JacksonViews */
	public static final String CLASSNAME_RANDOM_UTILS       = "APIRestGeneratorRandomUtils" ;
	/** Velocity class name - RestHandlerTest */
	public static final String CLASSNAME_REST_HANDLER_TEST  = "RestHandlerTest" ;
	/** Velocity class name - RestListenerTest */
	public static final String CLASSNAME_REST_LISTENER_TES  = "RestListenerTest" ;
	/** Velocity class name - Example - Launcher as App name */
	public static final String CLASSNAME_EXAMPLE_LAUNCHER   = "App" ;
	/** Velocity class name - Example - Listener */
	public static final String CLASSNAME_EXAMPLE_LISTENER   = "Listener" ;
	
    
	/********************** Velocity interface name ********************/
	
	/** Velocity class name - IGeneratedModel */
    public static final String INTERFACE_N_IGENERATED_MODEL = "IGeneratedModel" ;
	/** Velocity class name - IRestHandler */
	public static final String INTERFACE_NAME_REST_HANDLER  = "IRestHandler" ;
	/** Velocity class name - IRestListener */
	public static final String INTERFACE_NAME_REST_LISTENER = "IRestListener" ;
	/** Velocity class name - IGeneratedException */
	public static final String INTERFACE_N_IGENERATED_EXC 	= "IGeneratedException" ;	
	
	
	/********************** Folder hierarchy name ********************/
	
	/** Subfolder - Utils */
	private static final String SUBFOLDER_UTILS				= ".utils" ;
	
	/** Folders name - Model Utils */
	public static final String FOLDER_MODEL_UTILS 			= ConstantsOutput.FOLDER_MODEL + SUBFOLDER_UTILS ;
	/** Folders name - Model Test */
	public static final String FOLDER_MODEL_TEST 			= ConstantsOutput.FOLDER_MODEL ;
	/** Folders name - Rest Utils */
	public static final String FOLDER_REST_UTILS 			= ConstantsOutput.FOLDER_REST  + SUBFOLDER_UTILS ;
	/** Folders name - Rest Implementations */
	public static final String FOLDER_REST_IMPL 			= ConstantsOutput.FOLDER_REST  + ".impl" ;
	/** Folders name - Rest Test */
	public static final String FOLDER_REST_TEST 			= ConstantsOutput.FOLDER_REST  ;
	/** Folders name - Rest Utils Test */
	public static final String FOLDER_REST_UTILS_TEST 		= ConstantsOutput.FOLDER_REST  + SUBFOLDER_UTILS ;


	/********************* Common directories **************************/
	
	/** Common Java directory - Common parent directory */
	public static final String COMMON_JAVA_DIR_PARENT 		= "common/" ;
	/** Common Java directory - Client Example */
	public static final String COMMON_JAVA_DIR_CLIENT_EXA 	= ConstantsOutputJava.COMMON_JAVA_DIR_PARENT + "client_example/" ;
	/** Common Java directory - Listener Example */
	public static final String COMMON_JAVA_DIR_LISTENER_EXA = ConstantsOutputJava.COMMON_JAVA_DIR_PARENT + "listener_example/" ;
	/** Common Java directory - Macros */
	public static final String COMMON_JAVA_DIR_MACROS	    = ConstantsOutputJava.COMMON_JAVA_DIR_PARENT + "macros/" ;
	/** Common Java directory - Templates */
	public static final String COMMON_JAVA_DIR_TEMPLATES    = ConstantsOutputJava.COMMON_JAVA_DIR_PARENT + "templates/" ;
	
	
	/************************ File names *******************************/
	
	/** MVN Install - file name */
	public static final String MVN_INSTALL_FILE_NAME		= "mvn_install" ;


	/************************ TOP Level POM *****************************/

	/** TOP Level POM File name */
	public static final String TOP_LEVEL_POM_FILE_NAME 		= "topLevel" ;
	
	
	/************************ Project Name ******************************/
	
	/** Project name - Java */
	public static final String PROJECT_NAME 				= ConstantsOutput.PREFIX_GENERATOR_PROJECTS + ConstantsCommon.STRING_DOT + "java"  ;
	
	
	/******************** Output types - specific ***********************/

	/** Output type - File */
	public static final String OUTPUT_TYPE_FILE			   	= "byte[]" ;
	
	
	/**
	 * Private constructor
	 */
	private ConstantsOutputJava()
	{
		// Empty constructor
	}
}
