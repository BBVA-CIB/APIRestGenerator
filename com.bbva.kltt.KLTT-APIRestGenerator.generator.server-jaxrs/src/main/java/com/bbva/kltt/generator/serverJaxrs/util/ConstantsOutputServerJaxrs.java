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

package com.bbva.kltt.generator.serverJaxrs.util;

import java.io.File;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsOutputServerJaxrs
{
	/********************** Velocity - XML files **********************/
	
	/** Velocity Parameter - XML Context - File Name */
	public static final String VP_JAXRS_XML_CTX_FN 		   = "ws-context" ;	
	/** Velocity Parameter - XML Context - OSGi File Name */
	public static final String VP_JAXRS_XML_CTX_OSGI_FN	   = "ws-context-osgi" ;
	
	
	/****************** JAX-RS Server parameters **********************/
	
	/** JAX-RS - Type - FormData - MultipartFile - InputStream */
	public static final String JAXRS_FORM_DATA_INPUTSTREAM = "InputStream" ;
	/** JAX-RS - Type - FormData - Details - FormDataContentDisposition */
	public static final String JAXRS_FORM_DATA_DETAILS_TYP = "FormDataContentDisposition" ;
	/** JAX-RS - Type Name - FormData - Details - Suffix */
	public static final String JAXRS_FORM_DATA_DETAILS_SUF = "Details" ;
	
	/** JAX-RS - Annotation - FormDataParam */
	public static final String JAXRS_ANN_FORM_DATA_PARAM   = "@FormParam" ;
	/** JAX-RS - Annotation - RequestBody */
	public static final String JAXRS_ANN_REQUEST_BODY      = "" ; // It is not necessary any annotation
	/** JAX-RS - Annotation - RequestParam */
	public static final String JAXRS_ANN_REQUEST_PARAM     = "@QueryParam" ;
	/** JAX-RS - Annotation - RequestHeader */
	public static final String JAXRS_ANN_REQUEST_HEADER    = "@HeaderParam" ;
	/** JAX-RS - Annotation - PathVariable */
	public static final String JAXRS_ANN_PATH_VARIABLE     = "@PathParam" ;

	/** Velocity Parameter - XML - Rest Handler Interface - Full Package */
	public static final String VP_JAXRS_REST_HANDLER_IN_FP = "restHandlerInterfaceFullPackage" ;
	/** Velocity Parameter - XML - Rest Handler Impl - Full Package */
	public static final String VP_JAXRS_REST_HANDLER_IM_FP = "restHandlerImplFullPackage" ;
	/** Velocity Parameter - XML - Rest Listener Interface - Full Package */
	public static final String VP_JAXRS_REST_LISTENE_IN_FP = "restListenerInterfaceFullPackage" ;
	/** Velocity Parameter - XML - Example Listener Interface - Full Package */
	public static final String VP_JAXRS_EXAM_LISTENE_IN_FP = "exampleListenerInterfaceFullPackage" ;
	/** Velocity Parameter - XML - Rest Listener ID bean  */
	public static final String VP_JAXRS_LISTENER_SERVICE   = "restListenerOsgiService" ;
	/** Velocity Parameter - XML - Rest Service Implementation  */
	public static final String VP_JAXRS_REST_SERVICE_IMPL  = "restServiceImplRest" ;
	/** Velocity Parameter - XML - Rest Listener Implementation */
	public static final String VP_JAXRS_REST_LISTENER_IMPL = "restListenerImpl" ;
	/** Velocity Parameter - XML - Rest Listener OSGI */
	public static final String VP_JAXRS_REST_LISTENER_OSGI = "restListenerOsgi" ;
	
	
	/******************** Velocity - JAX-RS Templates ********************/
	
	/** Velocity template - JAX-RS template - RestHandlerMethodHeadersServerJaxrsInterfaceTemplate */
	public static final String VP_SER_JAXRS_T_REST_M_H_INT = "RestHandlerMethodHeadersServerJaxrsInterfaceTemplate" ;
	/** Velocity template - JAX-RS template - RestHandlerMethodHeadersServerJaxrsImplTemplate */
	public static final String VP_SER_JAXRS_T_REST_M_H_IMP = "RestHandlerMethodHeadersServerJaxrsImplTemplate" ;
	/** Velocity template - JAX-RS template - RestHandlerMethodHeadersServerJaxrsImplTemplateTest */
	public static final String VP_SER_JAXRS_T_REST_MET_H_T = "RestHandlerMethodHeadersServerJaxrsImplTemplateTest" ;
	/** Velocity template - JAX-RS template - RestListenerMethodHeadersServerJaxrsInterfaceTemplate */
	public static final String VP_SER_JAXRS_T_LIST_MET_H   = "RestListenerMethodHeadersServerJaxrsInterfaceTemplate" ;
	
	
	/******************** Velocity - JAX-RS Macros ********************/
	
	/** Velocity Parameter - JAX-RS macro - listenerExampleMethodsJaxrsMacros */
	public static final String VP_JAXRS_MACRO_LISTE_EXA	   = "ListenerExampleMethodsJaxrsMacros" ;


	/*********************** Velocity parameters *************************/

	/** Velocity parameter - restHandlerInterfaceClassName */
	public static final String VP_R_HANDLER_INTERFACE_NAME = "restHandlerInterfaceClassName" ;


	/******************* Extra folders - XML files ********************/
	
	/** Extra folders - XML files */
	public static final String XML_FOLDERS				   = "META-INF" + File.separator + "spring" ;
	
	
	/********************** Regular expressions ***********************/
	
	/** Regular Expression - Path restriction */
	public static final String PATT_PATHS		 		   = "[a-zA-Z0-9{}\\/]+" ;


	/************************ Module properties ***********************/

	/** Path to VM files parent directory */
	public static final String PACKAGE_VM_RESOURCES			= "com.bbva.kltt.generator.serverJaxrs.java" ;

	/** Name of the module */
	public static final String MODULE_NAME					= "serverJaxrs";

	/** Name to display on website */
	public static final String OFF_MODULE_NAME				= "JAX-RS";

	/** Class with the generate method */
	public static final String GENERATOR_INIT_CLASS	   		= "GeneratorGlobalServerJaxrs" ;

	/** Type of generator (client or server) */
	public static final String TYPE_MODULE					= "server";


	/**
	 * Private constructor
	 */
	private ConstantsOutputServerJaxrs()
	{
		// Empty constructor
	}
}
