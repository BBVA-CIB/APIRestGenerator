package com.bbva.kltt.apirest.generator.client.angular2.velocity.example.config;

import java.io.File;

import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.PackageUtilsClientAngular2;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.generator.client.web.velocity.GeneratorBaseClientWeb;

/**
 * Generate the main configuration for the Angular2 example
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleSystemJsConfigClientAngular2Generator2 extends GeneratorBaseClientWeb
{
    /**
     * Attribute - Package utility - Angular2
     */
    private final PackageUtilsClientAngular2 packageUtilsAngular2 ;

    /**
     * Build the generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler with the parsed information handler
     */
    public ExampleSystemJsConfigClientAngular2Generator2(final File baseDestDir,
                                 				   final GenerationParameters generationParams,
                                 				   final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, ConstantsOutput.EXTENSION_JAVASCRIPT) ;

        this.packageUtilsAngular2 = new PackageUtilsClientAngular2(this.getOutputLanguageGeneratorParams().getBUnit(),
															 parsedInfoHandler.getInfoValues().getTitle(),
															 parsedInfoHandler.getInfoValues().getVersion()) ;
    }
    
    @Override
    public VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext() ;

        final String examplePackage = this.packageUtilsAngular2.getExamplesPackage(this.getTranslatorType()) ;
        final String exampleFolder	= FilesUtility.convertPackageToSlashesPath(examplePackage) ;
                
        context.put(ConstantsOutputClientAngular2.VP_MAIN_JS_EXTENSION_REF, ConstantsOutput.DIRECTORY_BACK_ONE 	   + 
        															  exampleFolder 			 			   + ConstantsCommon.STRING_SLASH +
        															  ConstantsOutputClientAngular2.MAIN_CFG_EXAMPLE + this.getTitleCamelCase()     +
        															  this.packageUtilsAngular2.getProjectVersionSuffix()				  	  +
        															  ConstantsCommon.STRING_DOT   			   + ConstantsOutput.EXTENSION_JAVASCRIPT) ;
        return context ;
    }

    @Override
    public IOutputLanguageItems getOutputLanguageItems()
    {
    	// It is not necessary
        return null ;
    }

    @Override
    public IOutputLanguageParameters getOutputLanguageParameters()
    {
    	// It is not necessary
    	return null ;
    }

    @Override
    public IOutputLanguageConsumesProduces getOutputLanguageConsProd()
    {
    	// It is not necessary
    	return null ;
    }
    
    /**
     * @return the translator type name
     */
    public String getTranslatorType()
    {
        return ConstantsOutputClientAngular2.MODULE_NAME ;
    }
	
    @Override
    protected String getOutputPackage()
    {
    	// This file is in the main folder
        return null ;
    }

    @Override
    protected String getOutputFileName()
    {
        return this.getEntityName() ;
    }
    
    @Override
    protected String getEntityName()
    {
    	return ConstantsOutputClientAngular2.SYSTEMJS_CFG_EXAMPLE ;
    }
}
