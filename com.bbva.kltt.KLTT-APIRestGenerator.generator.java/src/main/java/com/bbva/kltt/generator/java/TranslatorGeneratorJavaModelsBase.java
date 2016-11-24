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

package com.bbva.kltt.generator.java;

import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.generator.java.interfaces.IOperationsModelJava;

/**
 * Base class that gives the common methods for every Translator generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorJavaModelsBase extends TranslatorGeneratorBaseJava implements IOperationsModelJava
{
    /**
     * Constructor of the class
     *
     * @param translatorType    with the translator type
     * @param generationParams  with the generation parameters information for java
     * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
     * @param generationPackage with the package of the generator module
     */
    public TranslatorGeneratorJavaModelsBase(final String translatorType,
                                             final GenerationParameters generationParams,
                                             final ParsedInfoHandler parsedInfoHandler,
                                             final String generationPackage)
    {
        super(translatorType, generationParams, parsedInfoHandler, generationPackage);
    }

    /**
     * Generate the specific code
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    public void generateSpecific() throws APIRestGeneratorException
    {
        // Generate the base interface for all the models
        this.generateBaseInterfaceModels(this.getSourceDir());

        // Generate the model classes
        this.generateModels(this.getSourceDir());

        // Generate the utility classes for model classes
        this.generateModelUtilities(this.getSourceDir());

        // Generate the model tests
        this.generateModelTests(this.getTestDir());

        // Generate the POM file
        this.generatePOM(this.getPomDir());
    }

    @Override
    public String getTranslatorTypeForProjectName()
    {
        // models generation is common for all the generators. Then, the translator suffix for the project name is empty
        return ConstantsCommon.STRING_EMPTY;
    }
}
