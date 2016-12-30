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

package com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs;

import com.bbva.kltt.generator.java.fwk.GeneratorBaseFrameworkJava;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.output.language.OutputLanguageConsumesProducesServerJaxrs;
import com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.output.language.OutputLanguageItemsServerJaxrs;
import com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.output.language.OutputLanguageParametersServerJaxrs;
import com.bbva.kltt.generator.serverJaxrs.util.ConstantsOutputServerJaxrs;

import java.io.File;

/**
 * Base methods for Java generation
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBaseServerJaxrs extends GeneratorBaseFrameworkJava
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
     * Build the generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for jax-rs
     * @param parsedInfoHandler with the parsed information handler
     */
    public GeneratorBaseServerJaxrs(final File baseDestDir,
                                    final GenerationParameters generationParams,
                                    final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, ConstantsOutputServerJaxrs.PACKAGE_VM_RESOURCES);

        this.outputLanguageItems = new OutputLanguageItemsServerJaxrs(parsedInfoHandler);
        this.outputLanguageParameters = new OutputLanguageParametersServerJaxrs(parsedInfoHandler, this.outputLanguageItems);
        this.outputLanguageConsProd = new OutputLanguageConsumesProducesServerJaxrs(parsedInfoHandler);
    }

    @Override
    public String getTranslatorType()
    {
        return ConstantsOutputServerJaxrs.MODULE_NAME;
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
}