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

package com.bbva.kltt.core.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ConstantsInput
{
	/** HTTP Method - Options */
	public static final String HTTP_METHOD_OPTIONS    = "OPTIONS" ;
	/** HTTP Method - Head */
	public static final String HTTP_METHOD_HEAD       = "HEAD" ;
	/** HTTP Method - Delete */
	public static final String HTTP_METHOD_DELETE     = "DELETE" ;
	/** HTTP Method - Patch */
	public static final String HTTP_METHOD_PATCH      = "PATCH" ;
	/** HTTP Method - Put */
	public static final String HTTP_METHOD_PUT 	   	  = "PUT" ;
	/** HTTP Method - Get */
	public static final String HTTP_METHOD_GET 	   	  = "GET" ;
	/** HTTP Method - Post */
	public static final String HTTP_METHOD_POST    	  = "POST" ;

	/** JSON Type - Array */
	public static final String JSON_TYPE_ARRAY 		  = "array" ;
	/** JSON Type - Boolean */
	public static final String JSON_TYPE_BOOLEAN	  = "boolean" ;
	/** JSON Type - Integer */
	public static final String JSON_TYPE_INTEGER	  = "integer" ;
	/** JSON Type - Number */
	public static final String JSON_TYPE_NUMBER 	  = "number" ;
	/** JSON Type - Null */
	public static final String JSON_TYPE_NULL 		  = "null" ;
	/** JSON Type - Object */
	public static final String JSON_TYPE_OBJECT 	  = "object" ;
	/** JSON Type - String */
	public static final String JSON_TYPE_STRING		  = "string" ;
	/** JSON Type - String */
	public static final String NO_JSON_TYPE_IS_REF	  = "reference" ;

	/** JSON Format - int32 */
	public static final String JSON_FORMAT_INT32	  = "int32" ;
	/** JSON Format - int64 */
	public static final String JSON_FORMAT_INT64	  = "int64" ;
	/** JSON Format - float */
	public static final String JSON_FORMAT_FLOAT	  = "float" ;
	/** JSON Format - double */
	public static final String JSON_FORMAT_DOUBLE	  = "double" ;
	/** JSON Format - byte */
	public static final String JSON_FORMAT_BYTE		  = "byte" ;
	/** JSON Format - binary */
	public static final String JSON_FORMAT_BINARY	  = "binary" ;
	/** JSON Format - date */
	public static final String JSON_FORMAT_DATE		  = "date" ;
	/** JSON Format - date-time */
	public static final String JSON_FORMAT_DATE_TIME  = "date-time" ;
	/** JSON Format - password */
	public static final String JSON_FORMAT_PASSWORD	  = "password" ;

	/** SO - Path String Prefix */
	public static final String SO_PATH_STRING_PREFIX  = "file://" ;

	/*********************** Root JSON Node ****************************/

	/** Root JSON Node Name */
	public static final String ROOT_JSON_NODE_NAME    = "root";

	/*********************** Form data constants ***********************/

	public static final String CON_APP_X_WWW_FORM_URL = "application/x-www-form-urlencoded" ;
	public static final String CON_MULTIPART_FORM_DAT = "multipart/form-data" ;

	/**************** PARSER - Regular Expressions *********************/

	/** Parser - Regular Expression - Variable */
	public static final String PATT_VARIABLE_VALID  = "[A-Za-z0-9_]+" ;
	/** Parser - Regular Expression - Title */
	public static final String PATT_TITLE_VALID     = "[A-Za-z][A-Za-z0-9_\\-]+" ;
	/** Parser - Regular Expression - Definition References */
	public static final String PATT_DEFINITION_REF  = "\\#\\/definitions\\/([a-zA-Z0-9_\\-]+)\\/?" ;
	/** Parser - Regular Expression - Parameters References */
	public static final String PATT_PARAMETERS_REF  = "\\#\\/parameters\\/([a-zA-Z0-9_\\-]+)\\/?" ;
	/** Parser - Regular Expression - Responses References */
	public static final String PATT_RESPONSES_REF   = "\\#\\/responses\\/([a-zA-Z0-9_\\-]+)\\/?" ;
	/** Parser - Path key values */
	public static final String PATT_PATH_KEY_VALUES = "\\{([a-zA-Z_]+)\\}" ;
	/** Parser - Regular Expression - Business Unit */
	public static final String PATT_BUNIT_VALID  	= "[A-Za-z]{4}" ;

	/** Pattern for variables name - remove blanks */
	public static final String PATT_BLANKS 			= "\\s+" ;

	/************************** RESERVED WORDS ****************************/

	public static final String[] RESER_WORDS_JAVA   = new String[]{"true",      "false",    "null",       "abstract", "assert",       "boolean",  "break",      "byte",
			"case",      "catch",    "char",       "class",    "const",        "continue", "default",    "do",
			"double",    "else",     "enum",       "extends",  "final",        "finally",  "float",      "for",
			"goto",      "if",       "implements", "import",   "instanceof",   "int",      "interface",  "long",
			"native",    "new",      "package",    "private",  "protected",    "public",   "return",     "short",
			"static",    "strictfp", "super",      "switch",   "synchronized", "this",     "throw",      "throws",
			"transient", "try",      "void",       "volatile", "while"} ;

	public static final String[] RESER_WORDS_TYPESC = new String[]{"abstract",    "await",     "boolean",  "break",     "byte",       "case",   "catch",    "char",
			"class",       "const",     "continue", "debugger",  "default",    "delete", "do",       "double",
			"else",        "enum",      "export",   "extends",   "false",      "final",  "finally", "float",
			"for",         "function",  "goto",     "if",        "implements", "import", "in",      "instanceof",
			"int",         "interface", "let",      "long",      "native",     "new",    "null",    "package",
			"private",     "protected", "public",   "return",    "short",      "static", "super",   "switch",
			"synchronized", "this",     "throw",    "transient", "true",       "try",    "typeof",  "var",
			"void",         "volatile", "while",    "with",      "yield"} ;

	public static final String[] RESER_WORDS_JAVAS = new String[]{"abstract",     "arguments",     "boolean", "break",    "byte",           "case",     "catch",
			"char",         "class",         "const",   "continue", "debugger",       "default",  "delete",
			"do",           "double",        "else",    "enum",     "eval",           "export",   "extends",
			"false",        "final",         "finally", "float",    "for",            "function", "goto",
			"if",           "implements",    "import",  "in",       "instanceof",     "int",      "interface",
			"let",          "long",          "native",  "new",      "null",           "package",  "private",
			"protected",    "public",        "return",  "short",    "static",         "super",    "switch",
			"synchronized", "this",          "throw",   "throws",   "transient",      "true",     "try",
			"typeof",       "var",           "void",    "volatile", "while",          "with",     "yield",
			"Array",        "Date",          "eval",    "function", "hasOwnProperty", "Infinity", "isFinite",
			"isNaN",        "isPrototypeOf", "length",  "Math",     "NaN",            "name",     "Number",
			"Object",       "prototype",     "String",  "toString", "undefined",      "valueOf"} ;

	public static final String[] RESERVED_WORDS	   = (String[]) ArrayUtils.addAll(RESER_WORDS_JAVA, ArrayUtils.addAll(RESER_WORDS_TYPESC, RESER_WORDS_JAVAS)) ;

	/**
	 * Private constructor
	 */
	private ConstantsInput()
	{
		// Empty constructor
	}
}
