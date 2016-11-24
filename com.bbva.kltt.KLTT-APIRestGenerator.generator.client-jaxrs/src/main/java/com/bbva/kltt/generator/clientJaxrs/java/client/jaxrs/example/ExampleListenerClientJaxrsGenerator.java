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

package com.bbva.kltt.generator.clientJaxrs.java.client.jaxrs.example;

import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.util.ConstantsOutputJava;
import com.bbva.kltt.generator.clientJaxrs.java.client.jaxrs.GeneratorBaseClientJaxrs;
import com.bbva.kltt.generator.clientJaxrs.util.ConstantsOutputClientJaxrs;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.List;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleListenerClientJaxrsGenerator extends GeneratorBaseClientJaxrs
{
    /**
     * Build the launcher examples generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleListenerClientJaxrsGenerator(final File baseDestDir,
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
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutput.INTERFACE_NAME_REST_LISTENER);

        // Java Templates
        context.put(ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS,
                    this.getTemplateCommonJavaResourceName(ConstantsOutputJava.COMMON_JAVA_DIR_TEMPLATES,
                                                 ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS));

        // Jaxrs Client Templates
        context.put(ConstantsOutputClientJaxrs.VP_CLI_JAXRS_T_EXAM_MET_H_T,
                    this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputClientJaxrs.VP_CLI_JAXRS_T_EXAM_MET_H_T));

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
        return ConstantsOutput.CLASSNAME_EXAMPLE_LISTENER;
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

        return additionalImports;
    }
}
