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

package com.bbva.kltt.apirest.generator.client.angular2.velocity;

import java.io.File;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.angular2.output.language.OutputLanguageConsumesProducesClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.output.language.OutputLanguageItemsClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.output.language.OutputLanguageParametersClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.PackageUtilsClientAngular2;
import com.bbva.kltt.apirest.generator.client.web.velocity.GeneratorBaseClientWeb;

/**
 * Base methods for Angular2 generation
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBaseClientAngular2 extends GeneratorBaseClientWeb
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
     * Attribute - Package utility - Angular2
     */
    private final PackageUtilsClientAngular2 packageUtilsAngular2 ;

    /**
     * Build the generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler with the parsed information handler
     */
    public GeneratorBaseClientAngular2(final File baseDestDir,
                                 final GenerationParameters generationParams,
                                 final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, ConstantsOutput.EXTENSION_TYPESCRIPT) ;

        this.outputLanguageItems      = new OutputLanguageItemsClientAngular2(parsedInfoHandler);
        this.outputLanguageParameters = new OutputLanguageParametersClientAngular2(parsedInfoHandler);
        this.outputLanguageConsProd   = new OutputLanguageConsumesProducesClientAngular2(parsedInfoHandler);
        
        this.packageUtilsAngular2     = new PackageUtilsClientAngular2(this.getOutputLanguageGeneratorParams().getBUnit(),
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
     * @return the packageUtilsAngular2
     */
    public PackageUtilsClientAngular2 getPackageUtilsAngular2()
    {
        return this.packageUtilsAngular2 ;
    }
    
    /**
     * @return the translator type name
     */
    public String getTranslatorType()
    {
        return ConstantsOutputClientAngular2.MODULE_NAME;
    }
}
