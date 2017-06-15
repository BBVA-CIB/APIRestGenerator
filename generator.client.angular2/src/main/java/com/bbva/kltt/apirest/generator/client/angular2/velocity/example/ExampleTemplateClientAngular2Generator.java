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

package com.bbva.kltt.apirest.generator.client.angular2.velocity.example;

import java.io.File;

import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.GeneratorBaseClientAngular2;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleTemplateClientAngular2Generator extends GeneratorBaseClientAngular2
{
    /**
     * Build the template (html) example generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleTemplateClientAngular2Generator(final File baseDestDir,
                                            final GenerationParameters generationParams,
                                            final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext() ;

        // Macros
        
        context.put(ConstantsOutputClientAngular2.VP_A2_MACRO_EXAMPLE_TEMPL,
            		this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputClientAngular2.VP_A2_MACRO_EXAMPLE_TEMPL)) ;
        
        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsAngular2().getExamplesPackage(this.getTranslatorType()) ;
    }
    
    @Override
    protected String getOutputFileName()
    {
        return this.getEntityName() + this.getPackageUtilsAngular2().getProjectVersionSuffix() ;
    }
    
    @Override
    protected String getEntityName()
    {
    	return ConstantsOutputClientAngular2.TEMPLATE_EXAMPLE + this.getTitleCamelCase() ;
    }

    /**
     * We have to redefine the output extension because it is not a Angular2 file.
     * It is a HTML file.
     */
    @Override
    protected String getOutputExtension()
    {
        return ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_HTML ;
    }
}
