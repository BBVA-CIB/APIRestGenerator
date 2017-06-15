package com.bbva.kltt.apirest.generator.client.angular2.velocity.example.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportExternalRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportGeneratedRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportsClientAngular2;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.GeneratorBaseClientAngular2;

/**
 * Generate the main configuration for the Angular2 example
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleMainConfigClientAngular2Generator extends GeneratorBaseClientAngular2
{
    /**
     * Build the main configuration for the example
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleMainConfigClientAngular2Generator(final File baseDestDir,
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
        context.put(ConstantsOutputClientAngular2.VP_MODULE_CLASS_REF, ConstantsOutputClientAngular2.MODULE_CFG_EXAMPLE + projectTitleCamelCase) ;
        
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
    	return ConstantsOutputClientAngular2.MAIN_CFG_EXAMPLE + this.getTitleCamelCase() ;
    }
    
    /**
     * @param projectTitleCamelCase with the project title as camel case
     * @return additional imports
     */
    private List<ClassImportsClientAngular2> generateAdditionalImports(final String projectTitleCamelCase)
    {
        final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
        
        // Add the Module reference class
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(ConstantsCommon.STRING_DOT,
        														  ConstantsOutputClientAngular2.MODULE_CFG_EXAMPLE + projectTitleCamelCase,
        														  this.getPackageUtilsAngular2().getProjectVersionSuffix())) ;
        
        // Add the platformBrowserDynamic reference
        final List<String> platformBrowserDependencies 	   = new ArrayList<String>() ;
        platformBrowserDependencies.add(ConstantsOutputClientAngular2.D_1_EXT_LIB_ANG_COR_PT_BD) ;
        
        additionalImports.add(new ClassImportExternalRefClientAngular2(platformBrowserDependencies, ConstantsOutputClientAngular2.R_EXT_LIB_ANG_COR_PT_BD)) ;

        return additionalImports ;
    }
}
