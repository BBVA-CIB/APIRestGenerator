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

package com.bbva.kltt.apirest.generator.java.velocity.models.exceptions ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.java.velocity.GeneratorBaseJavaExceptions;

/**
 * Generator to create the class that represents a model exception from the specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class CustomExceptionJavaGenerator extends GeneratorBaseJavaExceptions
{
    /**
     * Attribute - Class name
     */
    private final String className ;

    /**
     * Attribute - Response object
     */
    private final Response response ;

    /**
     * Build the models generator (complex type)
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information handler
     * @param operationId       with the operation identifier
     * @param response      	with the response
     */
    public CustomExceptionJavaGenerator(final File baseDestDir,
                                    final GenerationParameters generationParams,
                                    final ParsedInfoHandler parsedInfoHandler,
                                    final String operationId,
                                    final Response response)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;

        this.className = this.getOutputLanguageExceptions().getCustomExceptionAsClassName(operationId, response) ;
        this.response  = response ;
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext() ;

        // Class parameters
        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage()) ;
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports(this.response.getItem())) ;
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName()) ;
        context.put(ConstantsOutputJava.VP_INTERFACE_GEN_EXC_NAME, ConstantsOutputJava.INTERFACE_N_IGENERATED_EXC + this.getTitleCamelCase()) ;
        context.put(ConstantsOutput.VP_RESP_EXCEPTION, this.response) ;
        context.put(ConstantsOutput.VP_RESP_EXCEPTION_ATTRIBUTE, this.response.getItem()) ;
        context.put(ConstantsOutput.VP_JACKSON_MAP_CL_NAME, ConstantsOutputJava.CLASSNAME_JACKSON_MAPPER) ;

        return context ;
    }

    @Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getModelsExceptionPackage() ;
    }
    
    /**
     * @param item with the item
     * @return additional imports
     */
    private List<String> generateAdditionalImports(final Item item)
    {
        final List<String> additionalImports = new ArrayList<String>() ;

        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_JACKSON_MAPPER)) ;
        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_GENERATION_VIEW)) ;
        
        final String modelName = this.getOutputLanguageItems().getModelNameIfExist(item) ;
        if (modelName != null && !modelName.isEmpty())
        {
        	additionalImports.add(this.getPackageUtilsJava().getModelsPackage() + ConstantsCommon.STRING_DOT + modelName) ;
        }
        
        return additionalImports;
    }
    
    @Override
    protected String getOutputFileName()
    {
        return this.className ;
    }
}
