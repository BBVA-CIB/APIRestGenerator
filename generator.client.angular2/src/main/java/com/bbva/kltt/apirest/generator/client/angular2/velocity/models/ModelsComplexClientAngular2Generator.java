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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportGeneratedRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportsClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.GeneratorBaseClientAngular2;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;

/**
 * Generator to create the class that represents a model from the specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ModelsComplexClientAngular2Generator extends GeneratorBaseClientAngular2
{
	/** Attribute - Class name */
	private final String className ;
	
	/** Attribute - Item complex object */
	private final ItemComplex itemComplex ;
	
    /**
     * Build the models generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler	with the parsed information handler
     * @param className		    with the class name
     * @param itemComplex	    with the item complex
     */
    public ModelsComplexClientAngular2Generator(final File baseDestDir,
    					                    final GenerationParameters generationParams,
    					                    final ParsedInfoHandler parsedInfoHandler,
    					                    final String className,
    					                    final ItemComplex itemComplex)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
        
        this.className	 = className ;
        this.itemComplex = itemComplex ;
    }
    
    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context      = new VelocityContext() ;
        
        // Class parameters
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports());
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getEntityName()) ;
        context.put(ConstantsOutput.VP_CLASS_DESCRIPTION, this.itemComplex.getDescription()) ;
        context.put(ConstantsOutput.VP_ATTRIBUTES, this.itemComplex.getItemsMap().values()) ;
        
        context.put(ConstantsOutputClientWeb.VP_WEB_RAND_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_RANDOM_VALUES_WEB) ;
        context.put(ConstantsOutputClientWeb.VP_WEB_PAR_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_PARSER_VALUES_WEB) ;
        
        // Macros
        context.put(ConstantsOutputClientAngular2.VP_A2_MACRO_COMMON,
        			this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE, ConstantsOutputClientAngular2.VP_A2_MACRO_COMMON)) ;
        
        context.put(ConstantsOutputClientAngular2.VP_A2_MACRO_MOD_COMPLEX,
        			this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputClientAngular2.VP_A2_MACRO_MOD_COMPLEX)) ;
        
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
     * @return additional imports
     */
    private List<ClassImportsClientAngular2> generateAdditionalImports()
    {
    	// Additional imports - Utilities
        final List<ClassImportsClientAngular2> additionalImports = this.generateAdditionalImportsModelRefIfExists() ;

    	// Additional imports - Utilities
        additionalImports.addAll(this.generateAdditionalImportsUtilities()) ;
        
        return additionalImports ;
    }
    
    /**
     * @return the additional imports - Referenced models if exists
     */
    private List<ClassImportsClientAngular2> generateAdditionalImportsModelRefIfExists()
    {
        final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
    	final String relativeDirModels 					   = ConstantsCommon.STRING_DOT ;
    	
        final Collection<Item> itemsOfThisModel			   = this.itemComplex.getItemsMap().values() ;
        for (final Item itemOfThisModel : itemsOfThisModel)
        {
        	final String modelName = this.getOutputLanguageItems().getModelNameIfExist(itemOfThisModel) ;
        	
        	if (modelName != null && !this.modelRefWasAddedPreviously(additionalImports, modelName))
        	{
        		additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirModels, modelName, this.getPackageUtilsAngular2().getProjectVersionSuffix())) ;
        	}
        }
        
        return additionalImports ;
    }
    
    /**
     * @param additionalImports with the additional imports
     * @param modelName			with the model name
     * @return true if this model was added previously
     */
    private boolean modelRefWasAddedPreviously(final List<ClassImportsClientAngular2> additionalImports, final String modelName)
    {
    	boolean found = false ;
    	
    	final Iterator<ClassImportsClientAngular2> iterator = additionalImports.iterator() ;
    	while (iterator.hasNext() && !found)
    	{
    		final ClassImportsClientAngular2 classImportsAngular2 = iterator.next() ;
    		found = classImportsAngular2.getLoadedDependencies().equals(modelName) ;
    	}
    	
		return found ;
	}
    
    /**
     * @return the additional imports - Utilities
     */
    private List<ClassImportsClientAngular2> generateAdditionalImportsUtilities()
    {
        final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;

        final String relativeDirUtils = ConstantsOutput.DIRECTORY_BACK_FOUR + ConstantsOutput.FOLDER_UTILS ;
        
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirUtils, ConstantsOutputClientWeb.CLASSNAME_RANDOM_VALUES_WEB)) ;
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirUtils, ConstantsOutputClientWeb.CLASSNAME_PARSER_VALUES_WEB)) ;

        return additionalImports ;
    }
    
    
}
