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

package com.bbva.kltt.apirest.generator.java.velocity.models.exceptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.java.velocity.GeneratorBaseJavaExceptions;

/**
 * Generator to create the class that represents the common generated class exception
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class CommonExceptionJavaGenerator extends GeneratorBaseJavaExceptions
{
    /**
     * Build the generated class exception
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information handler
     */
    public CommonExceptionJavaGenerator(final File baseDestDir,
                                      		final GenerationParameters generationParams,
                                      		final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage());
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports());
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName());
        context.put(ConstantsOutputJava.VP_INTERFACE_GEN_EXC_NAME, ConstantsOutputJava.INTERFACE_N_IGENERATED_EXC + this.getTitleCamelCase()) ;
        context.put(ConstantsOutput.VP_JACKSON_MAP_CL_NAME, ConstantsOutputJava.CLASSNAME_JACKSON_MAPPER);

        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getModelsExceptionPackage() ;
    }

    @Override
    protected String getOutputFileName()
    {
        return this.getOutputLanguageExceptions().getCommonExceptionClassName() ;
    }
    
    /**
     * @return additional imports
     */
    private List<String> generateAdditionalImports()
    {
        final List<String> additionalImports = new ArrayList<String>() ;

        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_JACKSON_MAPPER));
        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_GENERATION_VIEW));

        return additionalImports;
    }
}

