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

package com.bbva.kltt.apirest.generator.java.velocity.models;

import java.io.File;

import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.java.velocity.GeneratorBaseJavaModels;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * Generator to create the common interface that all the generate component models implement
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class BaseInterfaceModelsJavaGenerator extends GeneratorBaseJavaModels
{
    /**
     * Build the base interface models generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param genParams         with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information
     */
    public BaseInterfaceModelsJavaGenerator(final File baseDestDir, final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, genParams, parsedInfoHandler) ;
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
        return this.getPackageUtilsJava().getModelsPackage();
    }

    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutputJava.INTERFACE_N_IGENERATED_MODEL + this.getTitleCamelCase();
    }
}
