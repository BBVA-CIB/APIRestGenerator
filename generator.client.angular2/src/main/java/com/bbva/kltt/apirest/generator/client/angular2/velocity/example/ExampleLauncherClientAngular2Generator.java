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

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.GeneratorBaseClientAngular2;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleLauncherClientAngular2Generator extends GeneratorBaseClientAngular2
{
    /**
     * Build the launcher examples generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleLauncherClientAngular2Generator(final File baseDestDir,
                                              final GenerationParameters generationParams,
                                              final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler);
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateMandatoryImportsJS()) ;
        context.put(ConstantsOutputClientWeb.VP_WEB_EXAMP_IMPORTS_CSS, this.generateMandatoryImportsCSS()) ;

        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return null;
    }
    
    @Override
    protected String getOutputFileName()
    {
        return this.getEntityName() ;
    }
    
    @Override
    protected String getEntityName()
    {
    	return ConstantsOutputClientWeb.INDEX_PAGE_WEB;
    }

    /**
     * We have to redefine the output extension because it is not a Angular2 file.
     * It is a HTML file.
     */
    @Override
    protected String getOutputExtension()
    {
        return ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_HTML;
    }

    /**
     * @return the mandatory imports (javascript)
     */
    private List<String> generateMandatoryImportsJS()
    {
        final List<String> mandatoryImports = new ArrayList<String>();

        // External libraries
        mandatoryImports.addAll(this.generateAdditionalImportsExternal()) ;

        return mandatoryImports;
    }

    /**
     * @return the additional utils imports (javascript)
     */
    private List<String> generateAdditionalImportsExternal()
    {
        final List<String> mandatoryImports = new ArrayList<String>();

        // Import the external libraries references
        final String rxJsImport 		    = ConstantsOutputClientAngular2.RX_JS_FOLDER + ConstantsOutputClientAngular2.RX_JS_FILE ;
        mandatoryImports.add(rxJsImport) ;
        
        final String jqueryImport 		    = ConstantsOutputClientAngular2.JQUERY_FOLDER + ConstantsOutputClientAngular2.JQUERY_FILE ;
        mandatoryImports.add(jqueryImport) ;
        
        final String coreJsShimImport 	    = ConstantsOutputClientAngular2.CORE_JS_SHIM_FOLDER + ConstantsOutputClientAngular2.CORE_JS_SHIM_FILE ;
        mandatoryImports.add(coreJsShimImport) ;
        
        final String zoneJsImport 		    = ConstantsOutputClientAngular2.ZONE_JS_FOLDER + ConstantsOutputClientAngular2.ZONE_JS_FILE ;
        mandatoryImports.add(zoneJsImport);
        
        final String reflectMetadataImport  = ConstantsOutputClientAngular2.REFLECTMETADATA_FOLDER + ConstantsOutputClientAngular2.REFLECTMETADATA_FILE ;
        mandatoryImports.add(reflectMetadataImport) ;
        
        final String systemJsImport 	    = ConstantsOutputClientAngular2.SYSTEMJS_FOLDER + ConstantsOutputClientAngular2.SYSTEMJS_FILE ;
        mandatoryImports.add(systemJsImport) ;
        
        return mandatoryImports;
    }
    
    /**
     * @return the additional utils imports (CSS)
     */
    private List<String> generateMandatoryImportsCSS()
    {
        final List<String> mandatoryImports = new ArrayList<String>();
        
        final String bootstrapImport 	    = ConstantsOutputClientAngular2.BOOTSTRAP_CSS_FOLDER + ConstantsOutputClientAngular2.BOOTSTRAP_CSS_FILE ;
        mandatoryImports.add(bootstrapImport) ;
        
        return mandatoryImports;
    }
    
    //<link href="" rel="stylesheet">
}
