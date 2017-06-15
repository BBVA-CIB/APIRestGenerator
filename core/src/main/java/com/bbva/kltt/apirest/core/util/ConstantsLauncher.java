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
public final class ConstantsLauncher
{
	/**************************** Command Line Common *************************/
	
	/** File path flag */
	public static final String FILE_PATH_FLAG 		      = "f" ;
	/** File path extended flag */
	public static final String FILE_PATH_EXTE_FLAG 	      = "specificationFilePath" ;
	/** File path flag description */
	public static final String FILE_PATH_DESCRIP          = "The file path where is the specification file content" ;
	
	/** CodeGen output directory flag */
	public static final String CODE_GEN_OUT_DIR_FLAG	  = "o" ;
	/** CodeGen output directory extended flag */
	public static final String CODE_GEN_OUT_DIR_EXTE_FLAG = "codeGenOutputDirectory" ;
	/** CodeGen output directory flag description */
	public static final String CODE_GEN_OUT_DIR_DESCRIP   = "The directory where code will be generated" ;
	
	/** Parser type directory flag */
	public static final String PARSER_TYPE_FLAG	  		  = "p" ;
	/** Translator output directory extended flag */
	public static final String PARSER_TYPE_EXTE_FLAG 	  = "parserType" ;
	/** Translator output directory flag description */
	public static final String PARSER_TYPE_DESCRIP   	  = "The parser type that will be used" ;
	
	/** Name of main launcher parser class */
	public static final String MAIN_LAUNCH_PARSER		  = "Parser";

    /** Name of the parser package */
    public static final String PARSER_PACKAGE             = "com.bbva.kltt.apirest.parser";
	
	
	/**
	 * Private constructor
	 */
	private ConstantsLauncher()
	{
		// Empty constructor
	}
}
