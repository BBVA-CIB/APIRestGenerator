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

package com.bbva.kltt.generator.clientTypescript.typescript;

import com.bbva.kltt.core.generator.GeneratorBase;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.clientTypescript.typescript.output.language.OutputLanguageConsumesProducesTypescript;
import com.bbva.kltt.generator.clientTypescript.typescript.output.language.OutputLanguageItemsTypescript;
import com.bbva.kltt.generator.clientTypescript.typescript.output.language.OutputLanguageParametersTypescript;
import com.bbva.kltt.generator.clientTypescript.util.ConstantsOutputTypescript;

import java.io.File;

/**
 * Base methods for Javascript generation
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBaseTypescript extends GeneratorBase
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
     * @param generationParams  with the parameters for the generation for the TypeScript
     * @param parsedInfoHandler with the parsed information handler
     */
    public GeneratorBaseTypescript(final File baseDestDir,
                                   final GenerationParameters generationParams,
                                   final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, ConstantsOutputTypescript.PACKAGE_VM_RESOURCES);

        this.outputLanguageItems      = new OutputLanguageItemsTypescript(parsedInfoHandler);
        this.outputLanguageParameters = new OutputLanguageParametersTypescript(parsedInfoHandler);
        this.outputLanguageConsProd   = new OutputLanguageConsumesProducesTypescript(parsedInfoHandler);
    }

    /**
     * Generates the code, this is the entry point of the generator
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    public void generate() throws APIRestGeneratorException
    {
        super.generateFile(this.createVelocityContext());
    }

    @Override
    protected String getOutputExtension()
    {
        return ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_TYPESCRIPT;
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
