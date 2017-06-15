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

package com.bbva.kltt.apirest.generator.client.angular2.velocity.models;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.generator.IGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.TranslatorGeneratorClientAngular2ModelsBase;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorClientAngular2Models extends TranslatorGeneratorClientAngular2ModelsBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorClientAngular2Models.class);

    /**
     * Attribute - Map of complex objects (complex and references)
     */
    private final Map<String, Item> definitionsMap;

    /**
     * Creates an XML global generator
     *
     * @param generationParams  with the generator parameters for the Angular2
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorClientAngular2Models(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(generationParams, parsedInfoHandler);

        this.definitionsMap = this.getParsedInfoHandler().generateDefinitionsMap();
    }

    @Override
    public void generateModels(final File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        TranslatorGeneratorClientAngular2Models.LOGGER.info("Generating the Angular2 definition classes and subclasses");

        for (final Entry<String, Item> entry : this.definitionsMap.entrySet())
        {
            IGenerator definitionsGenerator = null;

            final String className = entry.getKey();
            final Item item = entry.getValue();
            if (item instanceof ItemComplex)
            {
                definitionsGenerator = new ModelsComplexClientAngular2Generator(destDir,
                                                                            this.getGenerationParams(),
                                                                            this.getParsedInfoHandler(),
                                                                            className,
                                                                            (ItemComplex) item);
            }
            else if (item instanceof ItemRef)
            {
                definitionsGenerator = new ModelsRefClientAngular2Generator(destDir,
                                                                        this.getGenerationParams(),
                                                                        this.getParsedInfoHandler(),
                                                                        className,
                                                                        (ItemRef) item);
            }

            if (definitionsGenerator != null)
            {
            	definitionsGenerator.generate() ;
            }
        }

        TranslatorGeneratorClientAngular2Models.LOGGER.info("Generated the Angular2 definition classes and subclasses");
    }
    
	@Override
	public void generateModelExceptions(final File destDir) throws APIRestGeneratorException
	{
		// The common exception class is being generated by the class 'TranslatorGeneratorAngular2Utils' because it is a common generated class for whatever SPEC
	}

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_MODELS;
    }

	@Override
	public String getGeneratorInfoProjectsChildrenWeb()
	{
		return FilesUtility.loadFileContentFromClasspath(ConstantsOutputClientAngular2.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
