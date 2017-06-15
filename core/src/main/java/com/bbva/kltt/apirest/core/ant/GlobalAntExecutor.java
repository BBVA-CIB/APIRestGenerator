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

package com.bbva.kltt.apirest.core.ant;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsAnt;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.core.util.InvokeModule;
import com.bbva.kltt.apirest.core.util.parser.ParserUtil;
import com.bbva.kltt.apirest.core.web.Utilities;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GlobalAntExecutor
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalAntExecutor.class) ;

    /** Attribute - Parser Type */
    private final String parserType ;

    /** Attribute - Translator Type */
    private final String translatorType ;

    /**
     * @param parserType     with the parser type
     * @param translatorType with the generator type
     */
    public GlobalAntExecutor(final String parserType, final String translatorType)
    {
        this.parserType     = parserType ;
        this.translatorType = translatorType ;
    }

    /**
     * @param generatorBuilderPath with the generator builder path
     * @param fileName             with the ant file
     * @param deliverableFileName  with the deliverable file name
     * @param temporaryDir         with the temporary directory
     * @param errorStreamWrapper   with error stream
     * @param outputStreamWrapper  with the output stream
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void launchAntExecutor(final String generatorBuilderPath,
                                  final String fileName,
                                  final String deliverableFileName,
                                  final String temporaryDir,
                                  final PrintStream errorStreamWrapper,
                                  final PrintStream outputStreamWrapper) throws APIRestGeneratorException
    {
        // Get the full path of the ant executor module
        String packageName = new StringBuilder(ConstantsOutput.COMMON_GEN_TYPE_PACKAGE)
				                                              .append(ConstantsCommon.STRING_DOT)
				                                              .append(this.translatorType)
				                                              .append(ConstantsCommon.STRING_DOT)
				                                              .append(ConstantsAnt.ANT_FOLDER).toString() ;
        
        InvokeModule invokeModule       = InvokeModule.initInvokeModule(packageName, ConstantsAnt.ANT_LAUNCHER_CLASS) ;

        if (invokeModule != null)
        {
            Object[] constParams        = {generatorBuilderPath, this.parserType, deliverableFileName} ;
            Class<?>[] consTypeParams   = {String.class, String.class, String.class} ;

            Object executorInstance     = invokeModule.createInstance(constParams, consTypeParams) ;

            Object[] methodParams       = {fileName, errorStreamWrapper, outputStreamWrapper, temporaryDir} ;
            Class<?>[] methodTypeParams = {String.class, PrintStream.class, PrintStream.class, String.class} ;

            invokeModule.invokeMethod(executorInstance, "executeAntTask", methodParams, methodTypeParams) ;
        }
    }

    /**
     * Generate a new temporary directory as string
     *
     * @param generatorBuilderPath with the generator builder path
     * @return the path of the temporary directory
     */
    public String generateTemporaryOutputDirectory(final String generatorBuilderPath)
    {
        return Utilities.generateTemporaryOutputDirectory(generatorBuilderPath) ;
    }

    /**
     * @param generatorBuilderPath with the generator builder path
     * @param temporaryDir         with the temporary directory
     * @return the full temporary directory as string
     * @throws APIRestGeneratorException with an occurred exception
     */
    public String generateFullTemporaryDir(final String generatorBuilderPath, final String temporaryDir) throws APIRestGeneratorException
    {
        final String fullTemporaryDir = generatorBuilderPath + File.separator + ConstantsAnt.BASE_GEN_FOLDERS + temporaryDir ;
        FilesUtility.createFullDirectoryTree(fullTemporaryDir);

        return fullTemporaryDir;
    }

    /**
     * @param fullTemporaryDir with the full temporary directory
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void removeFullTemporaryDirectory(final String fullTemporaryDir) throws APIRestGeneratorException
    {
        try
        {
            if (fullTemporaryDir != null)
            {
                FileUtils.deleteDirectory(new File(fullTemporaryDir)) ;
            }
        }
        catch (IOException ioException)
        {
            final String errorString = "IOException while removing the temporary directory: " + fullTemporaryDir ;

            GlobalAntExecutor.LOGGER.error(errorString, ioException) ;
            throw new APIRestGeneratorException(errorString) ;
        }
    }

    /**
     * Validate the file extension
     *
     * @param fileName with the file name
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void validateFileExtension(final String fileName) throws APIRestGeneratorException
    {
        ParserUtil.validateExtension(fileName) ;
    }

    /**
     * Generate the deliverable file name
     * @param fileName 		 with the input file name
     * @param translatorType with the translator type
     * @return the eliverable file name
     */
	public String getDeliverableFileName(final String fileName, final String translatorType)
	{
		final int lastDot = fileName.lastIndexOf(ConstantsCommon.STRING_DOT) ;
		
		return fileName.substring(0, lastDot) + ConstantsCommon.STRING_HYPHEN + translatorType + 
			   ConstantsCommon.STRING_DOT 	  + ConstantsOutput.EXTENSION_ZIP ;
	}
}
