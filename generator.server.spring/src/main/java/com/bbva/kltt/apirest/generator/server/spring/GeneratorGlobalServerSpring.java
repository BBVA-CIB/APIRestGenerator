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

package com.bbva.kltt.apirest.generator.server.spring;

import com.bbva.kltt.apirest.generator.java.GeneratorGlobalJava;
import com.bbva.kltt.apirest.generator.server.spring.util.ConstantsOutputServerSpring;
import com.bbva.kltt.apirest.generator.server.spring.velocity.example.TranslatorGeneratorServerSpringExample;
import com.bbva.kltt.apirest.generator.server.spring.velocity.rest.impl.TranslatorGeneratorServerSpringImplRest;
import com.bbva.kltt.apirest.generator.server.spring.velocity.rest.interfaces.TranslatorGeneratorServerSpringInterfaceRest;
import com.bbva.kltt.apirest.core.generator.ITranslatorGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorGlobalServerSpring extends GeneratorGlobalJava
{
    /**
     * Constructs a new global generator for Spring
     *
     * @param genParams         with the parameters for the generation for spring
     * @param parsedInfoHandler with the parsed information handler to generate from
     */
    public GeneratorGlobalServerSpring(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(genParams, parsedInfoHandler, ConstantsOutputServerSpring.PACKAGE_VM_RESOURCES);
    }

    @Override
    protected void verifyRestrictions() throws APIRestGeneratorException
    {
        // No restrictions to be applied
    }

    /**
     * Generate the interface project - Spring
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    protected void generateRestInterfaceProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator springRestGenerator = new TranslatorGeneratorServerSpringInterfaceRest(this.getGenerationParams(),
                                                                                                          this.getParsedInfoHandler(),
                                                                                                          this.getGenerationPackage());
        springRestGenerator.generate();
    }

    /**
     * Generate the implementation project - Spring
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    protected void generateRestImplementationProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator springRestGenerator = new TranslatorGeneratorServerSpringImplRest(this.getGenerationParams(),
                                                                                                     this.getParsedInfoHandler());
        springRestGenerator.generate();
    }

    /**
     * Generate the Spring Example Project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    protected void generateExampleProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator springExampleGenerator = new TranslatorGeneratorServerSpringExample(this.getGenerationParams(),
                                                                                                       this.getParsedInfoHandler());
        springExampleGenerator.generate();
    }
}
