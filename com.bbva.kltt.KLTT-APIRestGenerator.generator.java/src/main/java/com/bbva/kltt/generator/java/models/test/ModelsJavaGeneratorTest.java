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

package com.bbva.kltt.generator.java.models.test;

import com.bbva.kltt.generator.java.GeneratorBaseJavaModels;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsOutput;
import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * Generator to create the test classes for the models
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ModelsJavaGeneratorTest extends GeneratorBaseJavaModels
{
    /**
     * ClassName
     */
    private final String className;

    /**
     * Build the models generator test
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information handler
     * @param className         with the class name
     * @param generationPackage with the package of the generator module
     */
    public ModelsJavaGeneratorTest(final File baseDestDir,
                                   final GenerationParameters generationParams,
                                   final ParsedInfoHandler parsedInfoHandler,
                                   final String className,
                                   final String generationPackage)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, generationPackage);

        this.className = className;
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage());
        context.put(ConstantsOutput.VP_MODEL_PACKAGE, this.getPackageUtilsJava().getModelsPackage());
        context.put(ConstantsOutput.VP_MODEL_CLASSNAME, this.getOutputLanguageNaming().prefixClassName(this.className));
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName());

        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getModelsTestPackage();
    }

    @Override
    protected String getOutputFileName()
    {
        return this.getOutputLanguageNaming().prefixClassName(this.className) + "Test";
    }
}
