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

package com.bbva.kltt.apirest.generator.test.example.velocity.rest.impl.test;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;
import com.bbva.kltt.apirest.generator.test.example.velocity.GeneratorBaseTestExample;

/**
 * Generator to create the class that represents the rest handler
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RestHandlerTestExampleImplGeneratorT extends GeneratorBaseTestExample
{
    /**
     * Build the rest handler test generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for spring
     * @param parsedInfoHandler with the parsed information handler
     */
    public RestHandlerTestExampleImplGeneratorT(final File baseDestDir,
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
        context.put(ConstantsOutput.VP_R_HANDLER_CL_NAME, ConstantsOutput.CLASSNAME_REST_HANDLER +
                                                          this.getTitleCamelCase()) ;
        context.put(ConstantsOutput.VP_R_LISTE_T_CL_NAME, ConstantsOutputJava.CLASSNAME_REST_LISTENER_TES) ;
        
        // Java Templates
        context.put(ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS,
        			this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_TEMPLATES, ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS)) ;
        
        // Spring Templates
        context.put(ConstantsOutputTestExample.VP_SPR_TEMPL_REST_MET_H_IM_T,
        			this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputTestExample.VP_SPR_TEMPL_REST_MET_H_IM_T)) ;
        
        return context ;
    }

	@Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getRestHandlerTestPackage(this.getTranslatorType()) ;
    }
	
    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutputJava.CLASSNAME_REST_HANDLER_TEST ;
    }
}
