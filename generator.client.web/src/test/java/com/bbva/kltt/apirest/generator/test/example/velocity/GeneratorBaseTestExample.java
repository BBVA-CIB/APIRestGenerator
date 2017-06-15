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

package com.bbva.kltt.apirest.generator.test.example.velocity;

import java.io.File;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.web.velocity.GeneratorBaseClientWeb;
import com.bbva.kltt.apirest.generator.test.example.output.language.OutputLanguageConsumesProducesTestExample;
import com.bbva.kltt.apirest.generator.test.example.output.language.OutputLanguageItemsTestExample;
import com.bbva.kltt.apirest.generator.test.example.output.language.OutputLanguageParametersTestExample;
import com.bbva.kltt.apirest.generator.test.example.util.PackageUtilsTestExample;

/**
 * Base methods for Javascript generation
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBaseTestExample extends GeneratorBaseClientWeb
{
    /**
     * Attribute - Parsed information handler
     */
    private final IOutputLanguageItems outputLanguageItems;

    /**
     * Attribute - Output Language Parameters
     */
    private final IOutputLanguageParameters outputLanguageParameters;

    /**
     * Attribute - Output Language Consumes Produces
     */
    private final IOutputLanguageConsumesProduces outputLanguageConsProd;
    
    /**
     * Attribute - Package utility - Javascript
     */
    private final PackageUtilsTestExample packageUtilsJavascript ;

    /**
     * Build the generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the JavaScript
     * @param parsedInfoHandler with the parsed information handler
     */
    public GeneratorBaseTestExample(final File baseDestDir,
                                   final GenerationParameters generationParams,
                                   final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, ConstantsOutput.EXTENSION_TYPESCRIPT) ;

        this.outputLanguageItems      = new OutputLanguageItemsTestExample(parsedInfoHandler);
        this.outputLanguageParameters = new OutputLanguageParametersTestExample(parsedInfoHandler);
        this.outputLanguageConsProd   = new OutputLanguageConsumesProducesTestExample(parsedInfoHandler);
        
        this.packageUtilsJavascript   = new PackageUtilsTestExample(this.getOutputLanguageGeneratorParams().getBUnit(),
												   				   parsedInfoHandler.getInfoValues().getTitle(),
												   				   parsedInfoHandler.getInfoValues().getVersion()) ;
    }

    @Override
    public IOutputLanguageItems getOutputLanguageItems()
    {
        return this.outputLanguageItems;
    }

    @Override
    public IOutputLanguageParameters getOutputLanguageParameters()
    {
        return this.outputLanguageParameters;
    }

    @Override
    public IOutputLanguageConsumesProduces getOutputLanguageConsProd()
    {
        return this.outputLanguageConsProd;
    }
    
    /**
     * @return the packageUtilsJavascript
     */
    public PackageUtilsTestExample getPackageUtilsJavascript()
    {
        return this.packageUtilsJavascript ;
    }
}
