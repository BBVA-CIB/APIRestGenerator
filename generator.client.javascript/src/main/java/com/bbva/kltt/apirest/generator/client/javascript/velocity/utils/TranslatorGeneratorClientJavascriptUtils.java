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

package com.bbva.kltt.apirest.generator.client.javascript.velocity.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.generator.IGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.generator.client.javascript.util.ConstantsOutputClientJavascript;
import com.bbva.kltt.apirest.generator.client.javascript.velocity.TranslatorGeneratorClientJavascriptUtilsBase;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorClientJavascriptUtils extends TranslatorGeneratorClientJavascriptUtilsBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorClientJavascriptUtils.class);

    /**
     * Creates the utilities generator
     *
     * @param generationParams  with the generator parameters for the JavaScript
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorClientJavascriptUtils(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(generationParams, parsedInfoHandler);
    }

    @Override
    public void generateUtilities(final File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        TranslatorGeneratorClientJavascriptUtils.LOGGER.info("Generating the JavaScript utility classes");

        // Generate the random values
        IGenerator randomValuesGenerator 		  = new RandomValuesClientJavascriptGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        randomValuesGenerator.generate();

        // Generate the parser values
        IGenerator parserValuesGenerator 		  = new ParserValuesClientJavascriptGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        parserValuesGenerator.generate();

        // Generate the scheme values
        IGenerator schemesValuesGenerator 		  = new SchemesValuesClientJavascriptGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        schemesValuesGenerator.generate();
        
        // Common exception class
        final IGenerator commonExceptionGenerator = new CommonExceptionClientJavascriptGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        commonExceptionGenerator.generate() ;

        TranslatorGeneratorClientJavascriptUtils.LOGGER.info("Generated the JavaScript utility classes");
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_UTILS;
    }
    
	@Override
	public String getGeneratorInfoProjectsChildrenWeb()
	{
		return FilesUtility.loadFileContentFromClasspath(ConstantsOutputClientJavascript.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
