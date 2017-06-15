package com.bbva.kltt.apirest.generator.client.angular2.velocity.example.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportExternalRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportGeneratedRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportsClientAngular2;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.GeneratorBaseClientAngular2;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;

/**
 * Generate the module configuration for the Angular2 example
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleModuleConfigClientAngular2Generator extends GeneratorBaseClientAngular2
{
    /**
     * Build the module configuration for the example
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleModuleConfigClientAngular2Generator(final File baseDestDir,
                                                final GenerationParameters generationParams,
                                                final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
    }
    
    @Override
    public VelocityContext createVelocityContext()
    {
        final VelocityContext context 	   = new VelocityContext() ;
        
        final String projectTitleCamelCase = this.getTitleCamelCase() ;

        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports(projectTitleCamelCase)) ;
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getEntityName()) ;
        context.put(ConstantsOutputClientWeb.VP_WEB_EXAMP_TESTER_CL_NAME, ConstantsOutputClientWeb.EXAMPLE_TESTER_WEB + projectTitleCamelCase) ;
        
        return context ;
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
    	return ConstantsOutputClientAngular2.MODULE_CFG_EXAMPLE + this.getTitleCamelCase() ;
    }
    
    /**
     * @param projectTitleCamelCase with the project title as camel case
     * @return additional imports
     */
    private List<ClassImportsClientAngular2> generateAdditionalImports(final String projectTitleCamelCase)
    {
        final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
        
        // Add the ExampleTester reference class
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(ConstantsCommon.STRING_DOT,
        														  ConstantsOutputClientWeb.EXAMPLE_TESTER_WEB + projectTitleCamelCase,
        														  this.getPackageUtilsAngular2().getProjectVersionSuffix())) ;
        
        // Add the platformBrowser reference
        final List<String> platformBrowserDependencies 	   = new ArrayList<String>() ;
        platformBrowserDependencies.add(ConstantsOutputClientAngular2.D_1_EXT_LIB_ANG_COR_PT_B) ;
        
        additionalImports.add(new ClassImportExternalRefClientAngular2(platformBrowserDependencies, ConstantsOutputClientAngular2.R_EXT_LIB_ANG_COR_PT_B)) ;
        
        // Add the NgModule reference
        final List<String> ngModuleDependencies 	   	   = new ArrayList<String>() ;
        ngModuleDependencies.add(ConstantsOutputClientAngular2.D_3_EXT_LIB_ANG_COR) ;
        
        additionalImports.add(new ClassImportExternalRefClientAngular2(ngModuleDependencies, ConstantsOutputClientAngular2.R_EXT_LIB_ANG_COR)) ;

        return additionalImports ;
    }
}
