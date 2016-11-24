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

package com.bbva.kltt.generator.java.models;

import com.bbva.kltt.core.generator.IGenerator;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.core.parsed_info.common.ItemRef;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.java.TranslatorGeneratorJavaModelsBase;
import com.bbva.kltt.generator.java.models.test.ModelsJavaGeneratorTest;
import com.bbva.kltt.generator.java.models.utils.APIRestGeneratorRandomUtils;
import com.bbva.kltt.generator.java.models.utils.JacksonMapperJavaGenerator;
import com.bbva.kltt.generator.java.models.utils.JacksonViewsJavaGenerator;
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
public class TranslatorGeneratorJavaModels extends TranslatorGeneratorJavaModelsBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorJavaModels.class);

    /**
     * Attribute - Map of complex objects (complex and references)
     */
    private final Map<String, Item> definitionsMap;

    /**
     * Creates the Java Models Translator
     *
     * @param generationParams  with the generator parameters for java
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     * @param generationPackage with the package of the generator module
     */
    public TranslatorGeneratorJavaModels(final GenerationParameters generationParams,
                                         final ParsedInfoHandler parsedInfoHandler,
                                         final String generationPackage)
    {
        super(ConstantsCommon.STRING_EMPTY, generationParams, parsedInfoHandler, generationPackage);

        this.definitionsMap = this.getParsedInfoHandler().generateDefinitionsMap();
    }

    @Override
    public void generateBaseInterfaceModels(final File destDir) throws APIRestGeneratorException
    {
        LOGGER.info("Generating the Java common base interface for all the models");

        final IGenerator baseInterfaceModelsGenerator = new BaseInterfaceModelsJavaGenerator(destDir,
                                                                                             this.getGenerationParams(),
                                                                                             this.getParsedInfoHandler(),
                                                                                             this.getGenerationPackage());
        baseInterfaceModelsGenerator.generate();

        LOGGER.info("Generated the Java common base interface for all the models");
    }

    @Override
    public void generateModels(final File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        LOGGER.info("Generating the Java common definition classes and subclasses");

        for (final Entry<String, Item> entry : this.definitionsMap.entrySet())
        {
            IGenerator definitionsGenerator = null;

            final String className = entry.getKey();
            final Item item = entry.getValue();
            if (item instanceof ItemComplex)
            {
                definitionsGenerator = new ModelsComplexJavaGenerator(destDir,
                                                                      this.getGenerationParams(),
                                                                      this.getParsedInfoHandler(),
                                                                      className,
                                                                      (ItemComplex) item,
                                                                      this.getGenerationPackage());
            }
            else if (item instanceof ItemRef)
            {
                definitionsGenerator = new ModelsRefJavaGenerator(destDir,
                                                                  this.getGenerationParams(),
                                                                  this.getParsedInfoHandler(),
                                                                  className,
                                                                  (ItemRef) item,
                                                                  this.getGenerationPackage());
            }

            definitionsGenerator.generate();
        }

        LOGGER.info("Generated the Java common definition classes and subclasses");
    }

    @Override
    public void generateModelUtilities(File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        LOGGER.info("Generating the Java utility classes for definition classes");

        final IGenerator jacksonViewsGenerator = new JacksonViewsJavaGenerator(destDir,
                                                                               this.getGenerationParams(),
                                                                               this.getParsedInfoHandler(),
                                                                               this.getGenerationPackage());
        jacksonViewsGenerator.generate();

        final IGenerator jacksonMapperGenerator = new JacksonMapperJavaGenerator(destDir,
                                                                                 this.getGenerationParams(),
                                                                                 this.getParsedInfoHandler(),
                                                                                 this.getGenerationPackage());
        jacksonMapperGenerator.generate();

        final IGenerator randomUtilsGenerator = new APIRestGeneratorRandomUtils(destDir,
                                                                                this.getGenerationParams(),
                                                                                this.getParsedInfoHandler(),
                                                                                this.getGenerationPackage());
        randomUtilsGenerator.generate();

        LOGGER.info("Generated the Java common utility classes for definition classes");
    }

    @Override
    public void generateModelTests(final File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        LOGGER.info("Generating the Java common definition test-classes and subclasses");

        for (final String className : this.definitionsMap.keySet())
        {
            final IGenerator definitionsGenerator = new ModelsJavaGeneratorTest(destDir,
                                                                                this.getGenerationParams(),
                                                                                this.getParsedInfoHandler(),
                                                                                className,
                                                                                this.getGenerationPackage());
            definitionsGenerator.generate();
        }

        LOGGER.info("Generated the Java common definition test-classes and subclasses");
    }

    @Override
    public void generatePOM(final File destDir) throws APIRestGeneratorException
    {
        LOGGER.info("Generating the Java common pom files for the models...");

        // Generate the pom.xml file
        final IGenerator normalPom = new ModelsPOMJavaGenerator(destDir,
                                                                this.getGenerationParams(),
                                                                this.getParsedInfoHandler(),
                                                                ConstantsOutput.EXTENSION_POM,
                                                                this.getGenerationPackage());
        normalPom.generate();

        // Generate the pom file again but using the name that correspond to the pom stored in nexus / artifactory
        final IGenerator artifactoryPom = new ModelsPOMJavaGenerator(destDir,
                                                                     this.getGenerationParams(),
                                                                     this.getParsedInfoHandler(),
                                                                     this.getArtifactoryPomName(),
                                                                     this.getGenerationPackage());
        artifactoryPom.generate();

        LOGGER.info("Generated the Java common pom files for the models...");
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_MODELS;
    }
}
