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

package com.bbva.kltt.generator.java.top;

import com.bbva.kltt.core.generator.GeneratorBase;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.util.ConstantsOutputJava;
import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * Generate the Maven templates for all the generated modules
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MvnInstallFileWindowsGenerator extends GeneratorBase
{
    /**
     * Translator type
     */
    private final String translatorType;

    /**
     * Build the Maven templates generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param translatorType    with the translator type
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed info handler
     * @param generationPackage with the package of the generator module
     */
    public MvnInstallFileWindowsGenerator(final File baseDestDir,
                                          final String translatorType,
                                          final GenerationParameters generationParams,
                                          final ParsedInfoHandler parsedInfoHandler,
                                          final String generationPackage)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, generationPackage);

        this.translatorType = translatorType;
    }

    @Override
    public VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        context.put(ConstantsOutputJava.VP_POM_PROJ_ARTIFACT_ID, this.getOutputLanguageGeneratorParams().getBUnitUpperCase() +
                    ConstantsCommon.STRING_HYPHEN +
                    this.getOutputLanguageInfoValues().getTitle());

        context.put(ConstantsOutputJava.VP_POM_PROJ_VERSION, this.getOutputLanguageInfoValues().getVersion());

        context.put(ConstantsOutputJava.VP_POM_PROJ_TRANSLATOR_TYPE, this.translatorType);

        // Java Macros
        context.put(ConstantsOutputJava.VP_JAVA_MACRO_COMMON_MVN_INS,
                    this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE + ConstantsOutputJava.COMMON_JAVA_DIR_MACROS,
                                                 ConstantsOutputJava.VP_JAVA_MACRO_COMMON_MVN_INS));

        return context;
    }

    @Override
    public IOutputLanguageItems getOutputLanguageItems()
    {
        // It is not necessary to be implemented
        return null;
    }

    @Override
    public IOutputLanguageParameters getOutputLanguageParameters()
    {
        // It is not necessary to be implemented
        return null;
    }

    @Override
    public IOutputLanguageConsumesProduces getOutputLanguageConsProd()
    {
        // It is not necessary to be implemented
        return null;
    }

    @Override
    protected String getOutputPackage()
    {
        // Don't use output package, this file will be generated directly in the given destination directory
        return null;
    }

    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutputJava.MVN_INSTALL_FILE_NAME;
    }

    @Override
    protected String getOutputExtension()
    {
        return ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_CMD;
    }
}
