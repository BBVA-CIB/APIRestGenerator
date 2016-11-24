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

package com.bbva.kltt.parser.swagger.util;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsInputParser
{
	
	/*********************** SWAGGER - Main Schema ***********************/
	
	/** Swagger - Main Schema - swagger */
	public static final String SW_MAIN_SCH_SWAGGER  = "swagger" ;
	/** Swagger - Main Schema - host */
	public static final String SW_MAIN_SCH_HOST 	= "host" ;
	/** Swagger - Main Schema - basePath */
	public static final String SW_MAIN_SCH_BASEPATH = "basePath" ;
	/** Swagger - Main Schema - info */
	public static final String SW_MAIN_SCH_INFO 	= "info" ;
	/** Swagger - Main Schema - definitions */
	public static final String SW_MAIN_SCH_DEFINIT 	= "definitions" ;
	/** Swagger - Main Schema - paths */
	public static final String SW_MAIN_SCH_PATHS 	= "paths" ;
	/** Swagger - Main Schema - Generator properties parameters */
	public static final String SW_MAIN_SCH_GENERA	= "x-generator-properties" ;
	
	
	/*************SWAGGER - Generator Properties Sub-Schema **************/
	
	/** Swagger - Sub Schema - extra parameter - business unit */
	public static final String SW_GEN_SUBSC_BUNIT	= "business-unit" ;
	/** Swagger - Sub Schema - extra parameter - osgi */
	public static final String SW_GEN_SUBSC_OSGI	= "osgi" ;
	/** Swagger - Sub Schema - extra parameter - architecture-type */
	public static final String SW_GEN_SUBSC_O_AR_T 	= "architecture-type" ;
	/** Swagger - Sub Schema - extra parameter - cxf-address */
	public static final String SW_GEN_SUBSC_O_ADDR	= "cxf-address" ;
	/** Swagger - Sub Schema - extra parameter - cxf-context */
	public static final String SW_GEN_SUBSC_O_CONT	= "cxf-context" ;
	
	
	/******************** SWAGGER - Info Sub-Schema **********************/
	
	/** Swagger - Info Sub-Schema - title */
	public static final String SW_INFO_SUBSC_TITLE	= "title" ;
	/** Swagger - Info Sub-Schema - description */
	public static final String SW_INFO_SUBSC_DESC	= "description" ;
	/** Swagger - Info Sub-Schema - contact */
	public static final String SW_INFO_SUBSC_CONTAC	= "contact" ;
	/** Swagger - Info Sub-Schema - license */
	public static final String SW_INFO_SUBSC_LICENS	= "license" ;
	/** Swagger - Info Sub-Schema - version */
	public static final String SW_INFO_SUBSC_VERSI	= "version" ;
	
	/**************** SWAGGER - Info-Contact Sub-Schema ******************/
	
	/** Swagger - Info-Contact Sub-Schema - name */
	public static final String SW_INFO_CONT_SUBSC_N	= "name" ;
	/** Swagger - Info-Contact Sub-Schema - url */
	public static final String SW_INFO_CONT_SUBSC_U	= "url" ;
	/** Swagger - Info-Contact Sub-Schema - email */
	public static final String SW_INFO_CONT_SUBSC_E	= "email" ;
	
	/********* SWAGGER - Common for definition or parameters *************/	
	
	/** Swagger - Common attribute - alias */
	public static final String SW_COMMON_ALIAS	    = "alias" ;
	/** Swagger - Common attribute - description */
	public static final String SW_COMMON_DESCR	    = "description" ;
	/** Swagger - Common attribute - $ref */
	public static final String SW_COMMON_REF   		= "$ref" ;
	/** Swagger - Common attribute - type */
	public static final String SW_COMMON_TYPE  		= "type" ;
	/** Swagger - Common attribute - format */
	public static final String SW_COMMON_FORMAT		= "format" ;
	/** Swagger - Common attribute - items */
	public static final String SW_COMMON_ARR_ITEM   = "items" ;
	/** Swagger - Common attribute - required */
	public static final String SW_COMMON_REQUIRED   = "required" ;
	/** Swagger - Common attribute - schema */
	public static final String SW_COMMON_SUBSC_SCHE	= "schema" ;
	/** Swagger - Common attribute - schemes */
	public static final String SW_COMMON_SCHEMES  	= "schemes" ;
	/** Swagger - Common attribute - consumes */
	public static final String SW_COMMON_CONSUMES 	= "consumes" ;
	/** Swagger - Common attribute - produces */
	public static final String SW_COMMON_PRODUCES 	= "produces" ;
	/** Swagger - Common attribute - responses */
	public static final String SW_COMMON_RESPONSES	= "responses" ;
	/** Swagger - Common attribute - parameters */
	public static final String SW_COMMON_PARAMS 	= "parameters" ;
	
	/**************** SWAGGER - Definitions Sub-Schema ******************/
	
	/** Swagger - Definitions Sub-Schema - properties */
	public static final String SW_DEFIN_SUBSC_PROPE = "properties" ;
	
	/**************** SWAGGER - Path Operation **************************/
	
	/** Swagger - Path Operation - delete */
	public static final String SW_PATH_OP_DELETE 	= "delete" ;
	/** Swagger - Path Operation - get */
	public static final String SW_PATH_OP_GET   	= "get" ;
	/** Swagger - Path Operation - head */
	public static final String SW_PATH_OP_HEAD  	= "head" ;
	/** Swagger - Path Operation - options */
	public static final String SW_PATH_OP_OPTIONS 	= "options" ;
	/** Swagger - Path Operation - patch */
	public static final String SW_PATH_OP_PATCH 	= "patch" ;
	/** Swagger - Path Operation - post */
	public static final String SW_PATH_OP_POST  	= "post" ;
	/** Swagger - Path Operation - put */
	public static final String SW_PATH_OP_PUT   	= "put" ;
	
	/**************** SWAGGER - Operation ********************************/
	
	/** Swagger - Operation - operationId */
	public static final String SW_OP_OPERATION_ID   = "operationId" ;
	/** Swagger - Operation - deprecated */
	public static final String SW_OP_DEPRECATED     = "deprecated" ;
	
	/************** SWAGGER - Parameters Sub-Schema *********************/
	
	/** Swagger - Parameters Sub-Schema - name */
	public static final String SW_PARAM_SUBSC_NAME  = "name" ;
	/** Swagger - Parameters Sub-Schema - in */
	public static final String SW_PARAM_SUBSC_IN  	= "in" ;
	/** Swagger - Parameters Sub-Schema - required */
	public static final String SW_PARAM_SUBSC_REQ  	= "required" ;
	
	/************** SWAGGER - Parameters - in values ********************/
	
	/** Swagger - Parameters - in value - header */
	public static final String SW_PARAM_IN_HEADER 	= "header" ;
	/** Swagger - Parameters - in value - path */
	public static final String SW_PARAM_IN_PATH 	= "path" ;
	/** Swagger - Parameters - in value - formData */
	public static final String SW_PARAM_IN_FORMDATA = "formData" ;
	/** Swagger - Parameters - in value - body */
	public static final String SW_PARAM_IN_BODY 	= "body" ;
	/** Swagger - Parameters - in value - query */
	public static final String SW_PARAM_IN_QUERY 	= "query" ;
	

	/*************** SWAGGER - OSGi Architecture Types ********************/
	
	/** Swagger - OSGi - Architecture type - online */
	public static final String OSGI_ARC_TYPE_ONLINE = "online" ;
	/** Swagger - OSGi - Architecture type - batch */
	public static final String OSGI_ARC_TYPE_BATCH  = "batch" ;
	

	/**
	 * Private constructor
	 */
	private ConstantsInputParser()
	{
		// Empty constructor
	}
}
