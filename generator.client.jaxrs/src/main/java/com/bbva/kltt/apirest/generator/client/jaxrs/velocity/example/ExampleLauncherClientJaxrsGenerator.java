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

package com.bbva.kltt.apirest.generator.client.jaxrs.velocity.example;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.generator.client.jaxrs.util.ConstantsOutputClientJaxrs;
import com.bbva.kltt.apirest.generator.client.jaxrs.velocity.GeneratorBaseClientJaxrs;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.List;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleLauncherClientJaxrsGenerator extends GeneratorBaseClientJaxrs
{
    /**
     * Build the launcher examples generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleLauncherClientJaxrsGenerator(final File baseDestDir,
                                               final GenerationParameters generationParams,
                                               final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler);
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        // Parameters
        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage());
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports());
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName());
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME,
        			ConstantsOutputJava.INTERFACE_NAME_REST_LISTENER + this.getTitleCamelCase());
        context.put(ConstantsOutput.VP_R_HANDLER_INTERFACE_NAME,
        			ConstantsOutputJava.INTERFACE_NAME_REST_HANDLER + this.getTitleCamelCase());
        context.put(ConstantsOutput.VP_R_HANDLER_CL_NAME,
                    ConstantsOutput.CLASSNAME_REST_HANDLER + this.getTitleCamelCase());
        context.put(ConstantsOutput.VP_LIST_EXAMP_CL_NAME,
        			ConstantsOutputJava.CLASSNAME_EXAMPLE_LISTENER + this.getTitleCamelCase());
        context.put(ConstantsOutput.VP_SCHEMES_VAL_CL_NAME,
                    ConstantsOutput.CLASSNAME_SCHEMES_VALUES + this.getTitleCamelCase());
        context.put(ConstantsOutputClientJaxrs.VP_R_REST_HANDLER_VARIABLE,
                    ConstantsOutputClientJaxrs.VP_REST_HANDLER + this.getTitleCamelCase());
        context.put(ConstantsOutputClientJaxrs.VP_R_REST_LISTENER_VARIABLE,
                    ConstantsOutputClientJaxrs.VP_REST_LISTENER + this.getTitleCamelCase());
        context.put(ConstantsOutputJava.VP_RANDOM_UTILS_CL_NAME,
        			ConstantsOutputJava.CLASSNAME_RANDOM_UTILS + this.getTitleCamelCase());

        // Java Templates
        context.put(ConstantsOutputJava.VP_JAVA_MACRO_COMMON,
                    this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_MACROS, ConstantsOutputJava.VP_JAVA_MACRO_COMMON));

        context.put(ConstantsOutputJava.VP_JAVA_RANDOM_GENERAT_METH,
                    this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_MACROS, ConstantsOutputJava.VP_JAVA_RANDOM_GENERAT_METH));

        context.put(ConstantsOutputClientJaxrs.VP_CLI_JAXRS_EXAMPLE_TMP,
                    this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputClientJaxrs.VP_CLI_JAXRS_EXAMPLE_TMP));

        context.put(ConstantsOutputClientJaxrs.VP_CLI_JAXRS_EXAMPLE_MACROS,
                this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputClientJaxrs.VP_CLI_JAXRS_EXAMPLE_MACROS));

        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getExamplesPackage(this.getTranslatorType());
    }

    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutputJava.CLASSNAME_EXAMPLE_LAUNCHER;
    }

    /**
     * @return the additional imports
     */
    private List<String> generateAdditionalImports()
    {
        // Model imports
        final List<String> additionalImports = this.generateImportsModel();

        // Listener interface - import
        additionalImports.add(this.generateImportListenerInterface(this.getTranslatorType()));

        // Rest Handler interface - import
        additionalImports.add(this.generateImportRestHandlerInterface(this.getTranslatorType()));

        // Rest Handler implementation - import
        additionalImports.add(this.generateImportHandlerImpl(this.getTranslatorType()));

        // Rest Handler - utilities - imports
        additionalImports.add(this.generateImportRestHandlerUtils());

        // Model - Util - Random utils
        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_RANDOM_UTILS +
                                                                               this.getTitleCamelCase()));

        return additionalImports;
    }
}
