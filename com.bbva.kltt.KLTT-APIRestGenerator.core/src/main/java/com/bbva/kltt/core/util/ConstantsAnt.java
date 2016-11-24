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

import org.apache.tools.ant.Project;

import java.io.File;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ConstantsAnt
{
	/** Ant File - Java */
	public static final String ANT_FILE_JAVA	  		  = "LauncherGeneratorJava.xml" ;
	/** Ant File - TypeScript */
	public static final String ANT_FILE_CLI_TYPE_SCRIPT   = "LauncherGeneratorClientTypescript.xml" ;

	/** Ant folder */
	public static final String ANT_FOLDER                 = "ant" ;
	/** Ant file property */
	public static final String ANT_FILE_PROPERTY	      = "ant.file" ;
	/** Ant reference - project helper */
	public static final String ANT_REF_PROJECT_HELPER     = "ant.projectHelper" ;
	/** Ant property - translator type */
	public static final String ANT_PROP_FULL_PKG_LAUN_CL  = "fullPackageLauncherClass" ;
	/** Ant property - full package launcher class */
	public static final String ANT_PROP_TRANSLATOR_TYPE	  = "translatorType" ;
	/** Ant property - type of parser */
	public static final String ANT_PROP_PARSER_TYPE	      = "parserType" ;
	
    /** Operative System - Windows */
	public static final String OS_WINDOWS		  		  = "win" ;
    
    /** Goal task - Windows */
	public static final String GOAL_TASK_WINDOWS 		  = "compress-deliverables-directory" ;
    /** Goal task - Linux */
	public static final String GOAL_TASK_LINUX   		  = "compress-deliverables-directory-linux" ;
    
    /** Ant Logger Level */
	public static final int ANT_LEVEL_LOGGER	  		  = Project.MSG_INFO ;
	
	/** Base generation folders */
	public static final String BASE_GEN_FOLDERS			  = "build" + File.separator + "codegen" + File.separator ;
	
	/** Temporary directory prefix */
	public static final String TEMP_DIRECTORY_PREFIX   	  = "temp" ;

	/** Ant property - launcher class */
	public static final String ANT_LAUNCHER_CLASS	      = "AntExecutor" ;

	/**
	 * Private constructor
	 */
	private ConstantsAnt()
	{
		// Empty constructor
	}
}
