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
import java.util.ArrayList;
import java.util.List;

import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportGeneratedRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportsClientAngular2;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportExternalRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.GeneratorBaseClientAngular2;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleTesterClientAngular2Generator extends GeneratorBaseClientAngular2
{
    /**
     * Build the launcher examples generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleTesterClientAngular2Generator(final File baseDestDir,
                                            final GenerationParameters generationParams,
                                            final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler);
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context      = new VelocityContext();
        
        final String projectTitleCamelCase = this.getTitleCamelCase() ;

        // Parameters
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports(projectTitleCamelCase));
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getEntityName()) ;
        context.put(ConstantsOutput.VP_R_HANDLER_CL_NAME, ConstantsOutput.CLASSNAME_REST_HANDLER + projectTitleCamelCase) ;
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutputClientWeb.CLASSNAME_REST_LISTENER_WEB + projectTitleCamelCase) ;
        context.put(ConstantsOutputClientAngular2.VP_TEMPLATE_EXAMPLE_REF, this.getTemplatePath(projectTitleCamelCase)) ;
        context.put(ConstantsOutputClientWeb.VP_WEB_RAND_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_RANDOM_VALUES_WEB) ;
        context.put(ConstantsOutput.VP_SCHEMES_VAL_CL_NAME, ConstantsOutput.CLASSNAME_SCHEMES_VALUES);

        // Templates
        context.put(ConstantsOutputClientAngular2.VP_A2_TEMPL_EXA_TEST_MET,
                	this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputClientAngular2.VP_A2_TEMPL_EXA_TEST_MET));

        // Macros
        context.put(ConstantsOutputClientAngular2.VP_A2_MACRO_COMMON,
                	this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE, ConstantsOutputClientAngular2.VP_A2_MACRO_COMMON));

        context.put(ConstantsOutputClientAngular2.VP_A2_MACRO_EXAMPLE_TEST,
                	this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputClientAngular2.VP_A2_MACRO_EXAMPLE_TEST));

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
    	return ConstantsOutputClientWeb.EXAMPLE_TESTER_WEB + this.getTitleCamelCase() ;
    }
    
    /**
     * @param projectTitleCamelCase with the project title as camel case
     * @return the template import for the example
     */
    private String getTemplatePath(final String projectTitleCamelCase)
    {
        final String examplePackage = this.getPackageUtilsAngular2().getExamplesPackage(this.getTranslatorType()) ;
        final String exampleFolder	= FilesUtility.convertPackageToSlashesPath(examplePackage) ;
    	
    	return ConstantsCommon.STRING_DOT 						   		+ ConstantsCommon.STRING_SLASH +
    		   exampleFolder 											+ ConstantsCommon.STRING_SLASH +
    		   ConstantsOutputClientAngular2.TEMPLATE_EXAMPLE	    	   		+ projectTitleCamelCase		   +
    		   this.getPackageUtilsAngular2().getProjectVersionSuffix() + ConstantsCommon.STRING_DOT   +
    		   ConstantsOutput.EXTENSION_HTML ;
    }
    
    /**
     * @param projectTitleCamelCase with the project title as camel case
     * @return additional imports
     */
    private List<ClassImportsClientAngular2> generateAdditionalImports(final String projectTitleCamelCase)
    {
    	// Add the models
        final List<ClassImportsClientAngular2> additionalImports = this.generateAdditionalImportsModels(projectTitleCamelCase) ;

        // Add the Controller dependencies
        additionalImports.addAll(this.generateAdditionalImportsRestControllers(projectTitleCamelCase)) ;
        
        // Add the Utility classes
        additionalImports.addAll(this.generateAdditionalImportsUtilities()) ;
        
        // Add the External dependencies
        additionalImports.addAll(this.generateAdditionalImportsExternal()) ;

        return additionalImports ;
    }
    
	/**
	 * @param projectTitleCamelCase with the project title as camel case
     * @return the additional imports - models
     */
    private List<ClassImportsClientAngular2> generateAdditionalImportsModels(final String projectTitleCamelCase)
    {
    	final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
    	final String relativeDirModels 					   = ConstantsOutput.DIRECTORY_BACK_ONE + ConstantsOutput.FOLDER_MODEL ;
    	
    	final List<String> modelNamesList = this.generateModelNamesList() ;
    	for (final String modelName : modelNamesList)
    	{
    		final String fullModelName = modelName + projectTitleCamelCase ;
    		additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirModels, fullModelName, this.getPackageUtilsAngular2().getProjectVersionSuffix())) ;
    	}
    	
    	return additionalImports ;
    }
    
    /**
     * @param projectTitleCamelCase with the project title as camel case
     * @return the additional imports - rest controllers
     */
    private List<ClassImportsClientAngular2> generateAdditionalImportsRestControllers(final String projectTitleCamelCase)
    {
    	final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
    	final String relativeDirControllers				   = ConstantsOutput.DIRECTORY_BACK_ONE + ConstantsOutput.FOLDER_REST ;
    	
        // Add the Rest Listener
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirControllers,
        													   	  ConstantsOutputClientWeb.CLASSNAME_REST_LISTENER_WEB + projectTitleCamelCase,
        													   	  this.getPackageUtilsAngular2().getProjectVersionSuffix())) ;
        
        // Add the Rest Listener
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirControllers,
        													   	  ConstantsOutput.CLASSNAME_REST_HANDLER + projectTitleCamelCase,
        													   	  this.getPackageUtilsAngular2().getProjectVersionSuffix())) ;
    	
    	return additionalImports ;
    }
    
	/**
     * @return the additional imports - models
     */
    private List<ClassImportsClientAngular2> generateAdditionalImportsUtilities()
    {
    	final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
    	
        final String relativeDirUtils = ConstantsOutput.DIRECTORY_BACK_FOUR + ConstantsOutput.FOLDER_UTILS ;

        additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirUtils, ConstantsOutput.CLASSNAME_SCHEMES_VALUES)) ;
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirUtils, ConstantsOutputClientWeb.CLASSNAME_RANDOM_VALUES_WEB)) ;
        
    	return additionalImports ;
	}
    
    /**
     * @return the additional imports - external
     */
    private List<ClassImportsClientAngular2> generateAdditionalImportsExternal()
    {
    	final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
    	
    	// Angular Core - Component
        
    	final List<String> angularCoreLoadedDependencies = new ArrayList<String>() ;
    	angularCoreLoadedDependencies.add(ConstantsOutputClientAngular2.D_2_EXT_LIB_ANG_COR) ;
    
    	additionalImports.add(new ClassImportExternalRefClientAngular2(angularCoreLoadedDependencies, ConstantsOutputClientAngular2.R_EXT_LIB_ANG_COR)) ;
    
    	return additionalImports ;
    }
}
