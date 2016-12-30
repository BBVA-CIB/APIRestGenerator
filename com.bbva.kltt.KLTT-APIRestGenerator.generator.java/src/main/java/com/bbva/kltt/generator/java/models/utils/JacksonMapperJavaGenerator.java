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

package com.bbva.kltt.generator.java.models.utils;

import com.bbva.kltt.generator.java.GeneratorBaseJavaModels;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsOutput;
import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * Generator to create the class that represents a model from the specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class JacksonMapperJavaGenerator extends GeneratorBaseJavaModels
{
    /**
     * Build the jackson mapper generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information handler
     * @param generationPackage with the package of the generator module
     */
    public JacksonMapperJavaGenerator(final File baseDestDir,
                                      final GenerationParameters generationParams,
                                      final ParsedInfoHandler parsedInfoHandler,
                                      final String generationPackage)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, generationPackage);
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage());
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName());

        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getModelsUtilsPackage();
    }

    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutput.CLASSNAME_JACKSON_MAPPER;
    }
}