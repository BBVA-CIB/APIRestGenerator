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
import java.util.ArrayList;
import java.util.List;

import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.java.velocity.GeneratorBaseJavaModels;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * Generator to create the class that represents a complex model from the specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ModelsRefJavaGenerator extends GeneratorBaseJavaModels
{
    /**
     * Attribute - Class name
     */
    private final String className;

    /**
     * Attribute - Item reference object
     */
    private final ItemRef itemRef;

    /**
     * Build the models generator (complex type)
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information handler
     * @param className         with the class name
     * @param itemRef           with the item reference
     */
    public ModelsRefJavaGenerator(final File baseDestDir,
                                  final GenerationParameters generationParams,
                                  final ParsedInfoHandler parsedInfoHandler,
                                  final String className,
                                  final ItemRef itemRef)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;

        this.className = className ;
        this.itemRef = itemRef ;
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        // Class parameters
        context.put(ConstantsOutput.VP_PACKAGE_NAME,        this.getOutputPackage());
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS,  this.generateAdditionalImports());
        context.put(ConstantsOutput.VP_CLASS_DESCRIPTION,   this.itemRef.getDescription());
        context.put(ConstantsOutput.VP_CLASS_NAME,          this.getOutputFileName());
        context.put(ConstantsOutput.VP_GEN_MODEL_CL_NAME,   ConstantsOutputJava.INTERFACE_N_IGENERATED_MODEL +
                                                            this.getTitleCamelCase());
        context.put(ConstantsOutput.VP_ATTRIBUTE_REF,       this.itemRef.getItemRef());
        context.put(ConstantsOutput.VP_JACKSON_MAP_CL_NAME, ConstantsOutputJava.CLASSNAME_JACKSON_MAPPER);

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

        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_JACKSON_MAPPER));
        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_GENERATION_VIEW));
        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_RANDOM_UTILS +
                                                                               this.getTitleCamelCase()));

        return additionalImports;
    }

    @Override
    protected String getOutputFileName()
    {
        return this.getOutputLanguageNaming().prefixClassName(this.className);
    }
}
