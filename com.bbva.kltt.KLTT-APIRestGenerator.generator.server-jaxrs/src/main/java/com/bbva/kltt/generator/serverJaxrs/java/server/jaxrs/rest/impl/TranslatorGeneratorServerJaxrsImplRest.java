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

package com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.rest.impl;

import com.bbva.kltt.core.generator.IGenerator;
import com.bbva.kltt.generator.java.TranslatorGeneratorJavaRestImplBase;
import com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.rest.impl.test.RestHandlerServerJaxrsImplGeneratorTest;
import com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.rest.impl.test.RestListenerServerJaxrsImplGeneratorTest;
import com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.rest.impl.xml.RestContextOsgiXmlServerJaxrsImplGenerator;
import com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.rest.impl.xml.RestContextXmlServerJaxrsImplGenerator;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.generator.serverJaxrs.util.ConstantsOutputServerJaxrs;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorServerJaxrsImplRest extends TranslatorGeneratorJavaRestImplBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorServerJaxrsImplRest.class);

    /**
     * Creates the JAX-RS Rest Translator
     *
     * @param generationParams  with the generator parameters for jaxrs
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorServerJaxrsImplRest(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(ConstantsOutputServerJaxrs.MODULE_NAME, generationParams, parsedInfoHandler, ConstantsOutputServerJaxrs.PACKAGE_VM_RESOURCES);
    }

    @Override
    public void generateRestHandler(final File destDir) throws APIRestGeneratorException
    {
        // Generate the rest handler class
        TranslatorGeneratorServerJaxrsImplRest.LOGGER.info("Generating JAXRS (server) Rest handler implementation");

        // Rest Handler Generator - Implementation
        final IGenerator restHandlerImplementGenerator = new RestHandlerServerJaxrsImplGenerator(destDir,
                                                                                                 this.getGenerationParams(),
                                                                                                 this.getParsedInfoHandler());
        restHandlerImplementGenerator.generate();

        TranslatorGeneratorServerJaxrsImplRest.LOGGER.info("Generated JAXRS (server) Rest handler implementation");
    }

    @Override
    public void generateRestHandlerTests(final File destDir) throws APIRestGeneratorException
    {
        // Generate the rest handler test class
        TranslatorGeneratorServerJaxrsImplRest.LOGGER.info("Generating JAXRS (server) Rest handler test classes");

        // Rest Handler Listener test class
        final IGenerator restListenerGeneratorTest = new RestListenerServerJaxrsImplGeneratorTest(destDir,
                                                                                                  this.getGenerationParams(),
                                                                                                  this.getParsedInfoHandler());
        restListenerGeneratorTest.generate();

        // Rest Handler test Generator
        final IGenerator restHandlerGeneratorTest = new RestHandlerServerJaxrsImplGeneratorTest(destDir,
                                                                                                this.getGenerationParams(),
                                                                                                this.getParsedInfoHandler());
        restHandlerGeneratorTest.generate();

        TranslatorGeneratorServerJaxrsImplRest.LOGGER.info("Generated JAXRS (server) Rest handler test classes");
    }

    @Override
    public void generatePOM(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorServerJaxrsImplRest.LOGGER.info("Generating JAXRS (server) pom files for the rest implementation");

        // Generate the pom.xml file
        final IGenerator normalPom = new RestPOMServerJaxrsImplGenerator(destDir,
                                                                         this.getGenerationParams(),
                                                                         this.getParsedInfoHandler(),
                                                                         ConstantsOutput.EXTENSION_POM);
        normalPom.generate();

        // Generate the pom file again but using the name that correspond to the pom stored in nexus / artifactory
        final IGenerator artifactoryPom = new RestPOMServerJaxrsImplGenerator(destDir,
                                                                              this.getGenerationParams(),
                                                                              this.getParsedInfoHandler(),
                                                                              this.getArtifactoryPomName());
        artifactoryPom.generate();

        TranslatorGeneratorServerJaxrsImplRest.LOGGER.info("Generated JAXRS (server) pom files for the rest implementation");
    }

    @Override
    public void generateSpecificJavaFramework(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorServerJaxrsImplRest.LOGGER.info("Generating JAXRS (server) xml files for Rest");

        final IGenerator contextXmlGenerator = new RestContextXmlServerJaxrsImplGenerator(destDir,
                                                                                          this.getGenerationParams(),
                                                                                          this.getParsedInfoHandler());
        contextXmlGenerator.generate();

        final IGenerator contextOsgiXmlGenerator = new RestContextOsgiXmlServerJaxrsImplGenerator(destDir,
                                                                                                  this.getGenerationParams(),
                                                                                                  this.getParsedInfoHandler());
        contextOsgiXmlGenerator.generate();

        TranslatorGeneratorServerJaxrsImplRest.LOGGER.info("Generated JAXRS (server) xml files for Rest");
    }
}
