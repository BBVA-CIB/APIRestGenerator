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

package com.bbva.kltt.apirest.generator.client.angular2;

import com.bbva.kltt.apirest.core.generator.GeneratorGlobalLanguage;
import com.bbva.kltt.apirest.core.generator.ITranslatorGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.example.TranslatorGeneratorClientAngular2Example;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.models.TranslatorGeneratorClientAngular2Models;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.rest.TranslatorGeneratorClientAngular2Rest;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.utils.TranslatorGeneratorClientAngular2Utils;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorGlobalClientAngular2 extends GeneratorGlobalLanguage
{
    /**
     * Constructs a new global generator for Java Spring Boot
     *
     * @param genParams         with the parameters for the generation for the TypScript
     * @param parsedInfoHandler with the parsed information handler to generate from
     */
    public GeneratorGlobalClientAngular2(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(genParams, parsedInfoHandler, ConstantsOutputClientAngular2.PACKAGE_VM_RESOURCES);
    }

    /**
     * @param translatorType with the translator type
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void generate(final String translatorType) throws APIRestGeneratorException
    {
        // Generate the Angular2 code
        this.generateCodeGeneratorAngular2();

        // Generate the Spring Boot example project
        this.generateExampleProjectAngular2();
    }

    /**
     * Generate the Spring Boot classes
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateCodeGeneratorAngular2() throws APIRestGeneratorException
    {
        final ITranslatorGenerator angular2UtilsGenerator  = new TranslatorGeneratorClientAngular2Utils(this.getGenerationParams(), this.getParsedInfoHandler());
        angular2UtilsGenerator.generate();

        final ITranslatorGenerator angular2ModelsGenerator = new TranslatorGeneratorClientAngular2Models(this.getGenerationParams(), this.getParsedInfoHandler());
        angular2ModelsGenerator.generate();

        final ITranslatorGenerator angular2RestGenerator   = new TranslatorGeneratorClientAngular2Rest(this.getGenerationParams(), this.getParsedInfoHandler());
        angular2RestGenerator.generate();
    }

    /**
     * Generate the Angular2 Example Project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateExampleProjectAngular2() throws APIRestGeneratorException
    {
        final ITranslatorGenerator angular2ExampleGenerator = new TranslatorGeneratorClientAngular2Example(this.getGenerationParams(), this.getParsedInfoHandler());
        angular2ExampleGenerator.generate();
    }
}
