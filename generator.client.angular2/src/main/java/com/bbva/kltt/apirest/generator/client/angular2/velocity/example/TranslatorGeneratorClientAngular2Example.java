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

package com.bbva.kltt.apirest.generator.client.angular2.velocity.example;

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
import com.bbva.kltt.apirest.generator.client.angular2.velocity.TranslatorGeneratorClientAngular2ExampleBase;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.example.config.ExampleMainConfigClientAngular2Generator;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.example.config.ExampleModuleConfigClientAngular2Generator;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.example.config.ExampleSystemJsConfigClientAngular2Generator2;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorClientAngular2Example extends TranslatorGeneratorClientAngular2ExampleBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorClientAngular2Example.class);

    /**
     * Creates the Angular2 Example Translator
     *
     * @param generationParams  with the generator parameters for the Angular2
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorClientAngular2Example(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(generationParams, parsedInfoHandler);
    }
    
	@Override
	public void generateExampleConfiguration(final File destDir) throws APIRestGeneratorException
	{
        TranslatorGeneratorClientAngular2Example.LOGGER.info("Generating the Angular2 example configuration classes") ;

        final IGenerator exampleMainConfigConGen     = new ExampleMainConfigClientAngular2Generator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        exampleMainConfigConGen.generate() ;
        
        final IGenerator exampleModuleConfigConGen   = new ExampleModuleConfigClientAngular2Generator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        exampleModuleConfigConGen.generate() ;
        
        final IGenerator exampleSystemJsConfigConGen = new ExampleSystemJsConfigClientAngular2Generator2(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        exampleSystemJsConfigConGen.generate() ;

        TranslatorGeneratorClientAngular2Example.LOGGER.info("Generated the Angular2 example configuration classes") ;		
	}

    @Override
    public void generateExampleLauncher(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorClientAngular2Example.LOGGER.info("Generating the Angular2 example html class and its template");

        final IGenerator exampleLauncherGenerator = new ExampleLauncherClientAngular2Generator(destDir,
                                                                                         this.getGenerationParams(),
                                                                                         this.getParsedInfoHandler()) ;
        exampleLauncherGenerator.generate() ;
        
        final IGenerator exampleTemplateGenerator = new ExampleTemplateClientAngular2Generator(destDir,
                                                                                         this.getGenerationParams(),
                                                                                         this.getParsedInfoHandler()) ;
        
        exampleTemplateGenerator.generate() ;

        TranslatorGeneratorClientAngular2Example.LOGGER.info("Generated the Angular2 example launcher html and its template");
    }

    @Override
    public void generateExampleCalls(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorClientAngular2Example.LOGGER.info("Generating the Angular2 example calls file");

        final IGenerator exampleTesterGenerator = new ExampleTesterClientAngular2Generator(destDir,
                                                                                     this.getGenerationParams(),
                                                                                     this.getParsedInfoHandler());
        exampleTesterGenerator.generate();

        TranslatorGeneratorClientAngular2Example.LOGGER.info("Generated the Angular2 example calls file");
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_EXAMPLE;
    }
    
	@Override
	public String getGeneratorInfoProjectsChildrenWeb()
	{
		return FilesUtility.loadFileContentFromClasspath(ConstantsOutputClientAngular2.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
