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

package com.bbva.kltt.generator.java.models;

import com.bbva.kltt.generator.java.GeneratorBaseJavaModels;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.util.ConstantsOutputJava;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Generator to create the class that represents a complex model from the specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ModelsComplexJavaGenerator extends GeneratorBaseJavaModels
{
    /**
     * Attribute - Class name
     */
    private final String className;

    /**
     * Attribute - Item complex object
     */
    private final ItemComplex itemComplex;

    /**
     * Build the models generator (complex type)
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information handler
     * @param className         with the class name
     * @param itemComplex       with the item complex
     * @param generationPackage with the package of the generator module
     */
    public ModelsComplexJavaGenerator(final File baseDestDir,
                                      final GenerationParameters generationParams,
                                      final ParsedInfoHandler parsedInfoHandler,
                                      final String className,
                                      final ItemComplex itemComplex,
                                      final String generationPackage)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, generationPackage);

        this.className   = className;
        this.itemComplex = itemComplex;
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        // Class parameters
        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage());
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports());
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName());
        context.put(ConstantsOutput.VP_CLASS_DESCRIPTION, this.itemComplex.getDescription());
        context.put(ConstantsOutput.VP_ATTRIBUTES, this.itemComplex.getItemsMap().values());
        context.put(ConstantsOutput.VP_GEN_MODEL_CL_NAME,
                    ConstantsOutput.CLASSNAME_IGENERATED_MODEL + this.getTitleCamelCase());
        context.put(ConstantsOutput.VP_JACKSON_MAP_CL_NAME, ConstantsOutput.CLASSNAME_JACKSON_MAPPER);
        context.put(ConstantsOutputJava.VP_RANDOM_UTILS_CL_NAME,
                    ConstantsOutput.CLASSNAME_RANDOM_UTILS + this.getTitleCamelCase());

        // Java Templates
        context.put(ConstantsOutputJava.VP_JAVA_TEMPL_ATTR_DEFINITI,
                    this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputJava.VP_JAVA_TEMPL_ATTR_DEFINITI));

        context.put(ConstantsOutputJava.VP_JAVA_TEMPL_GET_SET,
                    this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputJava.VP_JAVA_TEMPL_GET_SET));

        context.put(ConstantsOutputJava.VP_JAVA_TEMPL_FILL_RANDOMLY,
                    this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputJava.VP_JAVA_TEMPL_FILL_RANDOMLY));

        // Java Macros
        context.put(ConstantsOutputJava.VP_JAVA_MACRO_COMMON,
                    this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE + ConstantsOutputJava.COMMON_JAVA_DIR_MACROS,
                                                 ConstantsOutputJava.VP_JAVA_MACRO_COMMON));

        context.put(ConstantsOutputJava.VP_JAVA_MACRO_FILL_RANDOMLY,
                    this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputJava.VP_JAVA_MACRO_FILL_RANDOMLY));

        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getModelsPackage();
    }

    private List<String> generateAdditionalImports()
    {
        final List<String> additionalImports = new ArrayList<String>();

        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutput.CLASSNAME_JACKSON_MAPPER));
        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutput.CLASSNAME_GENERATION_VIEW));
        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutput.CLASSNAME_RANDOM_UTILS +
                                                                               this.getTitleCamelCase()));

        return additionalImports;
    }

    @Override
    protected String getOutputFileName()
    {
        return this.getOutputLanguageNaming().prefixClassName(this.className);
    }
}
