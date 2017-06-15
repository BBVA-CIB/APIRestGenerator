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

package com.bbva.kltt.apirest.generator.client.angular2.velocity.utils;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.GeneratorBaseClientAngular2;

/**
 * Generator to create the class that represents the common generated class exception
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class CommonExceptionClientAngular2Generator extends GeneratorBaseClientAngular2
{
    /**
     * Build the common exception generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler	with the parsed information handler
     */
    public CommonExceptionClientAngular2Generator(final File baseDestDir,
                                         	final GenerationParameters generationParams,
                                         	final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
    }
    
    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext() ;
        
        // Class parameters
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getEntityName()) ;
        
        return context ;
    }

	@Override
    protected String getOutputPackage()
    {
		return this.getPackageUtilsAngular2().getUtilsPackage() ;
    }
	
    @Override
    protected String getOutputFileName()
    {
        return this.getEntityName() ;
    }
    
    @Override
    protected String getEntityName()
    {
    	return ConstantsOutput.CLASSNAME_COMMON_EXCEPTION ;
    }
}

