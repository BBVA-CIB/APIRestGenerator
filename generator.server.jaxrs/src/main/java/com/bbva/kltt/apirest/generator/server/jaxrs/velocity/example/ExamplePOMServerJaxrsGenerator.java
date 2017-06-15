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

package com.bbva.kltt.apirest.generator.server.jaxrs.velocity.example;

import com.bbva.kltt.apirest.generator.server.jaxrs.velocity.POMServerJaxrsGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * Generator to create the POM of the generated specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExamplePOMServerJaxrsGenerator extends POMServerJaxrsGenerator
{
    /**
     * Build the translators for Example POM generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed info handler
     * @param pomFileName       with the name of the POM file to generate
     */
    public ExamplePOMServerJaxrsGenerator(final File baseDestDir,
                                          final GenerationParameters generationParams,
                                          final ParsedInfoHandler parsedInfoHandler,
                                          final String pomFileName)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, pomFileName);
    }

    @Override
    protected void addAdditionalContextParameters(final VelocityContext velocityContext)
    {
        velocityContext.put(ConstantsOutput.VP_MODEL_PACKAGE, this.getPackageUtilsJava().getModelsPackage());
        velocityContext.put(ConstantsOutput.VP_REST_PACKAGE, this.getPackageUtilsJava().getRestHandlerInterfacesPackage(this.getTranslatorType()));
    }
}
