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

package com.bbva.kltt.apirest.generator.client.feign;

import com.bbva.kltt.apirest.core.generator.ITranslatorGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.generator.client.feign.util.ConstantsOutputClientFeign;
import com.bbva.kltt.apirest.generator.client.feign.velocity.example.TranslatorGeneratorClientFeignExample;
import com.bbva.kltt.apirest.generator.client.feign.velocity.rest.impl.TranslatorGeneratorClientFeignImplRest;
import com.bbva.kltt.apirest.generator.client.feign.velocity.rest.interfaces.TranslatorGeneratorClientFeignInterfaceRest;
import com.bbva.kltt.apirest.generator.java.GeneratorGlobalJava;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorGlobalClientFeign extends GeneratorGlobalJava
{
    /**
     * Constructs a new global generator for Feign
     *
     * @param genParams         with the parameters for the generation for Feign
     * @param parsedInfoHandler with the parsed information handler to generate from
     */
    public GeneratorGlobalClientFeign(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(genParams, parsedInfoHandler, ConstantsOutputClientFeign.PACKAGE_VM_RESOURCES) ;
    }

    @Override
    protected void verifyRestrictions() throws APIRestGeneratorException
    {
    	RestrictionsVerifierClientFeign restrictionsVerifier = new RestrictionsVerifierClientFeign(this.getParsedInfoHandler()) ;
        restrictionsVerifier.verify() ;
    }
    
    /**
     * Generate the interface project - Feign
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    protected void generateRestInterfaceProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator feignRestGenerator = new TranslatorGeneratorClientFeignInterfaceRest(this.getGenerationParams(),
                                                                                                        this.getParsedInfoHandler(),
                                                                                                        this.getGenerationPackage()) ;
        feignRestGenerator.generate() ;
    }

    /**
     * Generate the implementation project - Feign
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    protected void generateRestImplementationProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator feignRestGenerator = new TranslatorGeneratorClientFeignImplRest(this.getGenerationParams(),
                                                                                                   this.getParsedInfoHandler()) ;
        feignRestGenerator.generate() ;
    }

    /**
     * Generate the Feign Example Project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    protected void generateExampleProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator feignExampleGenerator = new TranslatorGeneratorClientFeignExample(this.getGenerationParams(),
                                                                                                     this.getParsedInfoHandler()) ;
        feignExampleGenerator.generate() ;
    }
}
