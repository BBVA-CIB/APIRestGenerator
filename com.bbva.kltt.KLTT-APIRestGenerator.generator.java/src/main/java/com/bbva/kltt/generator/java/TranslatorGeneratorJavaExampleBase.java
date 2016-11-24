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
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.java.interfaces.IOperationsExampleJava;

/**
 * Base class that gives the common methods for every Translator example generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorJavaExampleBase extends TranslatorGeneratorBaseJava implements IOperationsExampleJava
{
    /**
     * Constructor of the class
     *
     * @param translatorType    with the translator type
     * @param generationParams  with the generation parameters information for java
     * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
     * @param generationPackage with the package of the generator module
     */
    public TranslatorGeneratorJavaExampleBase(final String translatorType,
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
        // Generate the example launcher
        this.generateExampleLauncher(this.getSourceDir());

        // Generate the example listener
        this.generateExampleListener(this.getSourceDir());

        // Generate the POM file
        this.generateExamplePOM(this.getPomDir());

        // Generate the specific Java Framework
        this.generateSpecificJavaFramework(this.getResourceDir());
    }

    @Override
    public String getTranslatorTypeForProjectName()
    {
        return this.getTranslatorType() + ConstantsCommon.STRING_HYPHEN;
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_EXAMPLE;
    }
}
