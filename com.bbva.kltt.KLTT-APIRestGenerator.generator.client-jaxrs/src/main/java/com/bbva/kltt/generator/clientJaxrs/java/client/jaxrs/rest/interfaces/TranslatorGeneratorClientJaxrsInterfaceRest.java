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

package com.bbva.kltt.generator.clientJaxrs.java.client.jaxrs.rest.interfaces;

import com.bbva.kltt.core.generator.IGenerator;
import com.bbva.kltt.generator.java.TranslatorGeneratorJavaRestInterfaceBase;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.clientJaxrs.java.client.jaxrs.rest.interfaces.utils.SchemesValuesClientJaxrsGenerator;
import com.bbva.kltt.generator.clientJaxrs.util.ConstantsOutputClientJaxrs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorClientJaxrsInterfaceRest extends TranslatorGeneratorJavaRestInterfaceBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorClientJaxrsInterfaceRest.class);

    /**
     * Creates the JAX-RS Rest Translator
     *
     * @param generationParams  with the generator parameters for jaxrs
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorClientJaxrsInterfaceRest(final GenerationParameters generationParams,
                                                       final ParsedInfoHandler parsedInfoHandler,
                                                       final String packageName)
    {
        super(ConstantsOutputClientJaxrs.MODULE_NAME, generationParams, parsedInfoHandler, packageName);
    }

    @Override
    public void generateRestHandler(final File destDir) throws APIRestGeneratorException
    {
        // Generate the rest handler class
        TranslatorGeneratorClientJaxrsInterfaceRest.LOGGER.info("Generating JAXRS (client) Rest - Two interfaces: handler and listener");

        // Rest Handler Listener class
        final IGenerator restListenerGenerator = new RestListenerClientJaxrsInterfaceGenerator(destDir,
                                                                                               this.getGenerationParams(),
                                                                                               this.getParsedInfoHandler());
        restListenerGenerator.generate();

        // Rest Handler Generator - Interface
        final IGenerator restHandlerInterfaceGenerator = new RestHandlerClientJaxrsInterfaceGenerator(destDir,
                                                                                                      this.getGenerationParams(),
                                                                                                      this.getParsedInfoHandler());
        restHandlerInterfaceGenerator.generate();

        TranslatorGeneratorClientJaxrsInterfaceRest.LOGGER.info("Generated JAXRS (client) Rest - Two interfaces: handler and listener");
    }

    @Override
    public void generateRestHandlerUtils(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorClientJaxrsInterfaceRest.LOGGER.info("Generating JAXRS (client) utility classes for the rest implementation");

        // Generate the pom.xml file
        final IGenerator schemeValuesGenerator = new SchemesValuesClientJaxrsGenerator(destDir,
                                                                                       this.getGenerationParams(),
                                                                                       this.getParsedInfoHandler());
        schemeValuesGenerator.generate();

        TranslatorGeneratorClientJaxrsInterfaceRest.LOGGER.info("Generated JAXRS (client) utility classes for the rest implementation");
    }

    @Override
    public void generatePOM(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorClientJaxrsInterfaceRest.LOGGER.info("Generating JAXRS (client) pom files for the rest interface project");

        // Generate the pom.xml file
        final IGenerator normalPom = new RestPOMClientJaxrsInterfaceGenerator(destDir,
                                                                              this.getGenerationParams(),
                                                                              this.getParsedInfoHandler(),
                                                                              ConstantsOutput.EXTENSION_POM);
        normalPom.generate();

        // Generate the pom file again but using the name that correspond to the pom stored in nexus / artifactory
        final IGenerator artifactoryPom = new RestPOMClientJaxrsInterfaceGenerator(destDir,
                                                                                   this.getGenerationParams(),
                                                                                   this.getParsedInfoHandler(),
                                                                                   this.getArtifactoryPomName());
        artifactoryPom.generate();

        TranslatorGeneratorClientJaxrsInterfaceRest.LOGGER.info("Generated JAXRS (client) pom files for the rest interface project");
    }

    @Override
    public void generateSpecificJavaFramework(final File destDir) throws APIRestGeneratorException
    {
        // It is not necessary to implement anything here
    }
}
