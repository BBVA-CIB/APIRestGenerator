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

package com.bbva.kltt.apirest.generator.client.angular2.velocity.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportGeneratedRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportsClientAngular2;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.GeneratorBaseClientAngular2;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;

/**
 * Generator to create the class that represents a complex model from the specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ModelsRefClientAngular2Generator extends GeneratorBaseClientAngular2
{
	/** Attribute - Class name */
	private final String className ;
	
	/** Attribute - Item reference object */
	private final ItemRef itemRef ;
	
    /**
     * Build the models generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler	with the parsed information handler
     * @param className		    with the class name
     * @param itemRef	        with the item reference
     */
    public ModelsRefClientAngular2Generator(final File baseDestDir,
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

        final String originalRefClass = this.itemRef.getItemRef() ;
        final String itemRefCamelCase = originalRefClass + this.getTitleCamelCase() ;
        
        // Class parameters
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports(itemRefCamelCase));
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getEntityName()) ;
        context.put(ConstantsOutput.VP_CLASS_DESCRIPTION, this.itemRef.getDescription()) ;
        context.put(ConstantsOutput.VP_ATTRIBUTE_REF, itemRefCamelCase) ;
        context.put(ConstantsOutputClientWeb.VP_WEB_ORIGINAL_REF_CL_NAME, originalRefClass) ;
        
        context.put(ConstantsOutputClientWeb.VP_WEB_RAND_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_RANDOM_VALUES_WEB) ;
        context.put(ConstantsOutputClientWeb.VP_WEB_PAR_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_PARSER_VALUES_WEB) ;
        
        return context ;
    }

	@Override
    protected String getOutputPackage()
    {
		return this.getPackageUtilsAngular2().getModelsPackage(this.getTranslatorType()) ;
    }
	
    @Override
    protected String getOutputFileName()
    {
        return this.getEntityName() + this.getPackageUtilsAngular2().getProjectVersionSuffix() ;
    }
    
    @Override
    protected String getEntityName()
    {
    	return this.getOutputLanguageNaming().prefixClassName(this.className) + this.getTitleCamelCase() ;
    }
    
    /**
     * @param itemRefCamelCase with the item reference as camel case
     * @return additional imports
     */
    private List<ClassImportsClientAngular2> generateAdditionalImports(final String itemRefCamelCase)
    {
        final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;

        additionalImports.add(new ClassImportGeneratedRefClientAngular2(ConstantsCommon.STRING_DOT,
        													   	  this.getOutputLanguageNaming().prefixClassName(itemRefCamelCase),
        													   	  this.getPackageUtilsAngular2().getProjectVersionSuffix())) ;
        return additionalImports ;
    }
}
