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

package com.bbva.kltt.generator.clientJaxrs.java.client.jaxrs.rest.interfaces.utils;

import com.bbva.kltt.generator.clientJaxrs.java.client.jaxrs.GeneratorBaseClientJaxrs;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsOutput;
import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * Generator to create the class that represents the scheme values generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class SchemesValuesClientJaxrsGenerator extends GeneratorBaseClientJaxrs
{
    /**
     * Build the scheme values class generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for jax-rs
     * @param parsedInfoHandler with the parsed information handler
     */
    public SchemesValuesClientJaxrsGenerator(final File baseDestDir,
                                             final GenerationParameters generationParams,
                                             final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
    }
    
    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext() ;
        
        // Parameters
        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage()) ;
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName()) ;
        
        return context ;
    }

	@Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getRestHandlerUtilsPackage(this.getTranslatorType()) ;
    }
	
    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutput.CLASSNAME_SCHEMES_VALUES ;
    }
}
