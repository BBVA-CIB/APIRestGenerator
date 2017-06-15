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

package com.bbva.kltt.apirest.generator.java.velocity;

import java.io.File;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.generator.java.output.language.OutputLanguageItemsModelsJava;

/**
 * Generator to create the class that represents a common model from the specification in java
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBaseJavaModels extends GeneratorBaseJava
{
    /**
     * Attribute - Parsed information handler
     */
    private final IOutputLanguageItems outputLanguageItems;

    /**
     * Build the models generator (common models in java)
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information handler
     */
    public GeneratorBaseJavaModels(final File baseDestDir, final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;

        this.outputLanguageItems = new OutputLanguageItemsModelsJava(parsedInfoHandler) ;
    }

    @Override
    public IOutputLanguageItems getOutputLanguageItems()
    {
        return this.outputLanguageItems ;
    }

    @Override
    public IOutputLanguageParameters getOutputLanguageParameters()
    {
        // This method is not used in this generation, then null
        return null ;
    }

    @Override
    public IOutputLanguageConsumesProduces getOutputLanguageConsProd()
    {
        // This method is not used in this generation, then null
        return null ;
    }
}
