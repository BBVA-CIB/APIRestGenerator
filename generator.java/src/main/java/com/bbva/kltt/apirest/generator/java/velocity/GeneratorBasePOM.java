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

/*
 * AbstractPOMGenerator.java
 *
 */
package com.bbva.kltt.apirest.generator.java.velocity;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;

import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * Base class for POM generators.
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBasePOM extends GeneratorBaseJava
{
    /**
     * Translator type
     */
    private final String translatorType;
    /**
     * Extension
     */
    private final String extension;
    /**
     * POM File name
     */
    private final String pomFileName;

    /**
     * Builds a new Base POM Generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param translatorType    with the translator type
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed info handler
     * @param pomFileName       with the specific file name to use for the POM (without extension)
     */
    protected GeneratorBasePOM(final File baseDestDir,
                               final String translatorType,
                               final GenerationParameters generationParams,
                               final ParsedInfoHandler parsedInfoHandler,
                               final String pomFileName)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;

        this.translatorType = translatorType;
        this.pomFileName    = pomFileName;

        // Change the extension if the pom file is not pom.xml
        if (pomFileName.equals(ConstantsOutput.EXTENSION_POM))
        {
            this.extension = ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_XML;
        }
        else
        {
            this.extension = ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_POM;
        }
    }

    /**
     * Hook for method for subclasses. Insert the additional context parameters required for the POM generation.
     *
     * @param velocityContext with the velocity context
     */
    protected abstract void addAdditionalContextParameters(VelocityContext velocityContext);

    @Override
    public VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        context.put(ConstantsOutputJava.VP_POM_PROJ_GROUP_ID,
                    ConstantsOutput.PACKAGE_ROOT_PREFIX +
                    this.getOutputLanguageGeneratorParams().getBUnit() +
                    ConstantsOutput.PACKAGE_ROOT_SUFFIX);

        context.put(ConstantsOutputJava.VP_POM_PROJ_ARTIFACT_ID,
                    this.getOutputLanguageGeneratorParams().getBUnitUpperCase() +
                    ConstantsCommon.STRING_HYPHEN +
                    this.getOutputLanguageInfoValues().getTitle());

        context.put(ConstantsOutputJava.VP_POM_PROJ_VERSION,         this.getOutputLanguageInfoValues().getVersion());

        context.put(ConstantsOutputJava.VP_POM_PROJ_DESCRIPTION,     this.getOutputLanguageInfoValues().getDescription());

        context.put(ConstantsOutputJava.VP_POM_PROJ_TRANSLATOR_TYPE, this.translatorType);

        context.put(ConstantsOutputJava.VP_POM_PROJ_CONTACT_NAME,    this.getOutputLanguageInfoValues().getContactName());
        context.put(ConstantsOutputJava.VP_POM_PROJ_CONTACT_EMAIL,   this.getOutputLanguageInfoValues().getContactEmail());
        context.put(ConstantsOutputJava.VP_POM_PROJ_CONTACT_URL,     this.getOutputLanguageInfoValues().getContactUrl());

        this.addAdditionalContextParameters(context);

        return context;
    }

    /**
     * @return the translatorType
     */
    public String getTranslatorType()
    {
        return this.translatorType;
    }

    @Override
    public String getOutputFileName()
    {
        return this.pomFileName;
    }

    @Override
    public String getOutputExtension()
    {
        return this.extension;
    }

    @Override
    public String getOutputPackage()
    {
        // Don't use output package, all poms are generated directly in the given destination directory
        return null;
    }

    @Override
    public IOutputLanguageItems getOutputLanguageItems()
    {
        // This method is not used in this generation, then null
        return null;
    }

    @Override
    public IOutputLanguageParameters getOutputLanguageParameters()
    {
        // This method is not used in this generation, then null
        return null;
    }

    @Override
    public IOutputLanguageConsumesProduces getOutputLanguageConsProd()
    {
        // This method is not used in this generation, then null
        return null;
    }
}
