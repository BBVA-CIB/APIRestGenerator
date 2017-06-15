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

package com.bbva.kltt.apirest.generator.server.jaxrs;

import com.bbva.kltt.apirest.generator.java.GeneratorGlobalJava;
import com.bbva.kltt.apirest.generator.server.jaxrs.util.ConstantsOutputServerJaxrs;
import com.bbva.kltt.apirest.generator.server.jaxrs.velocity.RestrictionsVerifierServerJaxrs;
import com.bbva.kltt.apirest.generator.server.jaxrs.velocity.example.TranslatorGeneratorServerJaxrsExample;
import com.bbva.kltt.apirest.generator.server.jaxrs.velocity.rest.impl.TranslatorGeneratorServerJaxrsImplRest;
import com.bbva.kltt.apirest.generator.server.jaxrs.velocity.rest.interfaces.TranslatorGeneratorServerJaxrsInterfaceRest;
import com.bbva.kltt.apirest.core.generator.ITranslatorGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorGlobalServerJaxrs extends GeneratorGlobalJava
{
    /**
     * Constructs a new global generator for JAX-RS
     *
     * @param genParams         with the parameters for the generation for jax-rs
     * @param parsedInfoHandler with the parsed information handler to generate from
     */
    public GeneratorGlobalServerJaxrs(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(genParams, parsedInfoHandler, ConstantsOutputServerJaxrs.PACKAGE_VM_RESOURCES);
    }

    @Override
    protected void verifyRestrictions() throws APIRestGeneratorException
    {
        RestrictionsVerifierServerJaxrs restrictionsVerifier = new RestrictionsVerifierServerJaxrs(this.getParsedInfoHandler());
        restrictionsVerifier.verify();
    }

    /**
     * Generate the interface project - jax-rs
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    protected void generateRestInterfaceProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator jaxrsRestGenerator = new TranslatorGeneratorServerJaxrsInterfaceRest(this.getGenerationParams(),
                                                                                                        this.getParsedInfoHandler(),
                                                                                                        this.getGenerationPackage());
        jaxrsRestGenerator.generate();
    }

    /**
     * Generate the implementation project - jax-rs
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    protected void generateRestImplementationProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator jaxrsRestGenerator = new TranslatorGeneratorServerJaxrsImplRest(this.getGenerationParams(),
                                                                                                   this.getParsedInfoHandler());
        jaxrsRestGenerator.generate();
    }

    /**
     * Generate the example project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    protected void generateExampleProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator jaxrsExampleGenerator = new TranslatorGeneratorServerJaxrsExample(this.getGenerationParams(),
                                                                                                     this.getParsedInfoHandler());
        jaxrsExampleGenerator.generate();
    }
}
