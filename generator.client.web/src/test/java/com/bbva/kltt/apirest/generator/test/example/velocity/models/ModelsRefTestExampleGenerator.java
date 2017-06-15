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

package com.bbva.kltt.apirest.generator.test.example.velocity.models;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;
import com.bbva.kltt.apirest.generator.test.example.velocity.GeneratorBaseTestExample;

import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * Generator to create the class that represents a complex model from the specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */	
public class ModelsRefTestExampleGenerator extends GeneratorBaseTestExample
{
	/** Attribute - Class name */
	private final String className ;
	
	/** Attribute - Item reference object */
	private final ItemRef itemRef ;
	
    /**
     * Build the models generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the JavaScript
     * @param parsedInfoHandler	with the parsed information handler
     * @param className		    with the class name
     * @param itemRef	        with the item reference
     */
    public ModelsRefTestExampleGenerator(final File baseDestDir,
    					  	            final GenerationParameters generationParams,
    					  	            final ParsedInfoHandler parsedInfoHandler,
                                        final String className,
    						            final ItemRef itemRef)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
        
        this.className = className ;
        this.itemRef   = itemRef ;
    }
    
    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext() ;
        
        // Class parameters
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getEntityName()) ;
        context.put(ConstantsOutput.VP_CLASS_DESCRIPTION, this.itemRef.getDescription()) ;
        context.put(ConstantsOutput.VP_ATTRIBUTE_REF, this.itemRef.getItemRef()) ;
        context.put(ConstantsOutputTestExample.VP_MODULE_NAME, this.getPackageUtilsJavascript().getModuleProjectName()) ;
        
        context.put(ConstantsOutputClientWeb.VP_WEB_RAND_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_RANDOM_VALUES_WEB) ;
        context.put(ConstantsOutputClientWeb.VP_WEB_PAR_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_PARSER_VALUES_WEB) ;
        
        return context ;
    }

	@Override
    protected String getOutputPackage()
    {
		// For the JavaScript generation it is not necessary any subfolder
        return ConstantsCommon.STRING_EMPTY ;
    }
	
    @Override
    protected String getOutputFileName()
    {
        return this.getEntityName() + this.getPackageUtilsJavascript().getProjectVersionSuffix() ;
    }
    
    @Override
    protected String getEntityName()
    {
    	return this.getOutputLanguageNaming().prefixClassName(this.className) ;
    }
}
