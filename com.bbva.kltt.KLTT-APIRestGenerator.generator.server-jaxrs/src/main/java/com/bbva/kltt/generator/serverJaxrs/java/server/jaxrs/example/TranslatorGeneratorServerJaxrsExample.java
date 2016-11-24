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

package com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.example;

import com.bbva.kltt.core.generator.IGenerator;
import com.bbva.kltt.generator.java.TranslatorGeneratorJavaExampleBase;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.example.xml.ExampleContextOsgiXmlServerJaxrsGenerator;
import com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.example.xml.ExampleContextXmlServerJaxrsGenerator;
import com.bbva.kltt.generator.serverJaxrs.util.ConstantsOutputServerJaxrs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorServerJaxrsExample extends TranslatorGeneratorJavaExampleBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorServerJaxrsExample.class);

    /**
     * Creates the Spring Translator
     *
     * @param generationParams  with the generator parameters for spring
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorServerJaxrsExample(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(ConstantsOutputServerJaxrs.MODULE_NAME, generationParams, parsedInfoHandler, ConstantsOutputServerJaxrs.PACKAGE_VM_RESOURCES);
    }

    @Override
    public void generateExampleLauncher(final File destDir) throws APIRestGeneratorException
    {
        // It is not necessary to implement anything here because it is installed in OSGi
    }

    @Override
    public void generateExampleListener(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorServerJaxrsExample.LOGGER.info("Generating the JAXRS (server) example listener class");

        final IGenerator exampleListenerGenerator = new ExampleListenerServerJaxrsGenerator(destDir,
                                                                                            this.getGenerationParams(),
                                                                                            this.getParsedInfoHandler());
        exampleListenerGenerator.generate();

        TranslatorGeneratorServerJaxrsExample.LOGGER.info("Generated the JAXRS (server) example listener class");
    }

    @Override
    public void generateExamplePOM(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorServerJaxrsExample.LOGGER.info("Generating the JAXRS (server) pom files for the example");

        // Generate the pom.xml file
        final IGenerator gen = new ExamplePOMServerJaxrsGenerator(destDir,
                                                                  this.getGenerationParams(),
                                                                  this.getParsedInfoHandler(),
                                                                  ConstantsOutput.EXTENSION_POM);
        gen.generate();

        TranslatorGeneratorServerJaxrsExample.LOGGER.info("Generated the JAXRS (server) pom files for the example");
    }

    @Override
    public void generateSpecificJavaFramework(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorServerJaxrsExample.LOGGER.info("Generating JAXRS (server) xml files for the example");

        final IGenerator contextXmlGenerator = new ExampleContextOsgiXmlServerJaxrsGenerator(destDir,
                                                                                             this.getGenerationParams(),
                                                                                             this.getParsedInfoHandler());
        contextXmlGenerator.generate();

        final IGenerator contextOsgiXmlGenerator = new ExampleContextXmlServerJaxrsGenerator(destDir,
                                                                                             this.getGenerationParams(),
                                                                                             this.getParsedInfoHandler());
        contextOsgiXmlGenerator.generate();

        TranslatorGeneratorServerJaxrsExample.LOGGER.info("Generated JAXRS (server) xml files for the example");
    }
}
