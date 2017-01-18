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

package com.bbva.kltt.generator.util;

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
	/** Velocity parameter - restHandlerTestClassName */
	public static final String VP_RANDOM_UTILS_CL_NAME  	= "randomUtilsClassName" ;
	
	
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
	
	/** Velocity Parameter - Java template - ListenerExampleJavaTemplate */
	public static final String VP_COMMON_J_TEMPL_LISTE_EXA 	= "ListenerExampleJavaTemplate" ;
	/** Velocity Parameter - Java template - ListenerExampleMethodsJavaTemplate */
	public static final String VP_COMM_J_TEMPL_LISTE_EXA_ME = "ListenerExampleMethodsJavaTemplate" ;
	
	
	/******** Velocity - Java Templates - Client Example *************/
	
	/** Velocity Parameter - Java template - ClientExampleJavaTemplate */
	public static final String VP_COMMON_J_TEMPL_CLI_EXA 	= "ClientExampleJavaTemplate" ;
	/** Velocity Parameter - Java template - ClientExampleMethodsJavaTemplate */
	public static final String VP_COMMON_J_TEMPL_CLI_EXA_ME = "ClientExampleMethodsJavaTemplate" ;
	/** Velocity Parameter - Java Macro - ClientExampleJavaMacros */
	public static final String VP_COMMON_J_MACRO_CLI_EXA 	= "ClientExampleJavaMacros" ;
	
	
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
	
	
	/**
	 * Private constructor
	 */
	private ConstantsOutputJava()
	{
		// Empty constructor
	}
}
