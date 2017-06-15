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

package com.bbva.kltt.apirest.generator.client.angular2.velocity.utils;

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
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.TranslatorGeneratorClientAngular2UtilsBase;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorClientAngular2Utils extends TranslatorGeneratorClientAngular2UtilsBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorClientAngular2Utils.class);

    /**
     * Creates the utilities generator
     *
     * @param generationParams  with the generator parameters for the Angular2
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorClientAngular2Utils(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(generationParams, parsedInfoHandler);
    }

    @Override
    public void generateUtilities(final File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        TranslatorGeneratorClientAngular2Utils.LOGGER.info("Generating the Angular2 utility classes") ;

        // Generate the random values
        IGenerator randomValuesGenerator 		  = new RandomValuesClientAngular2Generator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        randomValuesGenerator.generate() ;

        // Generate the parser values
        IGenerator parserValuesGenerator 		  = new ParserValuesClientAngular2Generator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        parserValuesGenerator.generate() ;

        // Generate the scheme values
        IGenerator schemesValuesGenerator		  = new SchemesValuesClientAngular2Generator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        schemesValuesGenerator.generate() ;
        
        // Common exception class
        final IGenerator commonExceptionGenerator = new CommonExceptionClientAngular2Generator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        commonExceptionGenerator.generate() ;

        TranslatorGeneratorClientAngular2Utils.LOGGER.info("Generated the Angular2 utility classes") ;
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_UTILS;
    }
    
	@Override
	public String getGeneratorInfoProjectsChildrenWeb()
	{
		return FilesUtility.loadFileContentFromClasspath(ConstantsOutputClientAngular2.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
