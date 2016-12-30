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

package com.bbva.kltt.generator.clientTypescript.typescript.models;

import com.bbva.kltt.core.generator.IGenerator;
import com.bbva.kltt.generator.clientTypescript.typescript.TranslatorGeneratorTypescriptModelsBase;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.core.parsed_info.common.ItemRef;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorTypescriptModels extends TranslatorGeneratorTypescriptModelsBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorTypescriptModels.class);

    /**
     * Attribute - Map of complex objects (complex and references)
     */
    private final Map<String, Item> definitionsMap;

    /**
     * Creates an XML global generator
     *
     * @param generationParams  with the generator parameters for the TypeScript
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorTypescriptModels(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(generationParams, parsedInfoHandler);

        this.definitionsMap = this.getParsedInfoHandler().generateDefinitionsMap();
    }

    @Override
    public void generateModels(final File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        TranslatorGeneratorTypescriptModels.LOGGER.info("Generating the TypeScript definition classes and subclasses");

        for (final Entry<String, Item> entry : this.definitionsMap.entrySet())
        {
            IGenerator definitionsGenerator = null;

            final String className = entry.getKey();
            final Item item = entry.getValue();
            if (item instanceof ItemComplex)
            {
                definitionsGenerator = new ModelsComplexTypescriptGenerator(destDir,
                                                                            this.getGenerationParams(),
                                                                            this.getParsedInfoHandler(),
                                                                            className,
                                                                            (ItemComplex) item);
            }
            else if (item instanceof ItemRef)
            {
                definitionsGenerator = new ModelsRefTypescriptGenerator(destDir,
                                                                        this.getGenerationParams(),
                                                                        this.getParsedInfoHandler(),
                                                                        className,
                                                                        (ItemRef) item);
            }

            definitionsGenerator.generate();
        }

        TranslatorGeneratorTypescriptModels.LOGGER.info("Generated the TypeScript definition classes and subclasses");
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_MODELS;
    }
}