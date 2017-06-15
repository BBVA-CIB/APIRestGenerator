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

package com.bbva.kltt.apirest.generator.test.example.velocity.example;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;
import com.bbva.kltt.apirest.generator.test.example.velocity.GeneratorBaseTestExample;

import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleLauncherTestExampleGenerator extends GeneratorBaseTestExample
{
    /**
     * Build the launcher examples generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the JavaScript
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleLauncherTestExampleGenerator(final File baseDestDir,
                                              final GenerationParameters generationParams,
                                              final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler);
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        final String javascriptExtension = ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_JAVASCRIPT;
        final String cssExtension = ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_CSS;

        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateMandatoryImportsJS(javascriptExtension));
        context.put(ConstantsOutputClientWeb.VP_WEB_EXAMP_IMPORTS_JS, this.generateExampleImportsJS(javascriptExtension));
        context.put(ConstantsOutputClientWeb.VP_WEB_EXAMP_IMPORTS_CSS, this.generateExampleImportsCSS(cssExtension));

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
     * We have to redefine the output extension because it is not a JavaScript file.
     * It is a HTML file.
     */
    @Override
    protected String getOutputExtension()
    {
        return ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_HTML;
    }

    /**
     * @param javascriptExtension with the javascript extension
     * @return the mandatory imports (javascript)
     */
    private List<String> generateMandatoryImportsJS(final String javascriptExtension)
    {
        final List<String> mandatoryImports = new ArrayList<String>();

        // External libraries
        mandatoryImports.addAll(this.generateAdditionalImportsExternal(javascriptExtension));
        // Utility libraries
        mandatoryImports.addAll(this.generateAdditionalImportsUtils(javascriptExtension));
        // Model libraries
        mandatoryImports.addAll(this.generateAdditionalImportsModel(javascriptExtension));
        // Rest libraries
        mandatoryImports.addAll(this.generateAdditionalImportsRest(javascriptExtension));

        return mandatoryImports;
    }

    /**
     * @param javascriptExtension with the javascript extension
     * @return the additional utils imports (javascript)
     */
    private List<String> generateAdditionalImportsExternal(final String javascriptExtension)
    {
        final List<String> mandatoryImports = new ArrayList<String>();

        // Import the external libraries references
        final String esPromiseImport = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER  + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutputClientWeb.WEB_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutputClientWeb.WEB_EXT_LIBS_ES6_PROMISE,
                                                                ConstantsOutputClientWeb.WEB_EXT_LIBS_ES6_PROMISE + javascriptExtension);
        mandatoryImports.add(esPromiseImport);

        final String jqueryImport = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER  + ConstantsCommon.STRING_SLASH +
                                                             ConstantsOutputClientWeb.WEB_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                             ConstantsOutputClientWeb.WEB_EXT_LIBS_JQUERY,
                                                             ConstantsOutputClientWeb.WEB_EXT_LIBS_JQUERY + javascriptExtension);
        mandatoryImports.add(jqueryImport);

        final String rxAllImport = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER  + ConstantsCommon.STRING_SLASH +
                                                            ConstantsOutputClientWeb.WEB_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                            ConstantsOutputClientWeb.WEB_EXT_LIBS_RX_ALL,
                                                            ConstantsOutputClientWeb.WEB_EXT_LIBS_RX_ALL + javascriptExtension);
        mandatoryImports.add(rxAllImport);

        return mandatoryImports;
    }

    /**
     * @param javascriptExtension with the javascript extension
     * @return the additional utils imports (javascript)
     */
    private List<String> generateAdditionalImportsUtils(final String javascriptExtension)
    {
        final List<String> mandatoryImports = new ArrayList<String>();

        // Import the random values generator example
        final String randomValuesImport    = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER   + ConstantsCommon.STRING_SLASH +
                                                                   	  ConstantsOutputClientWeb.WEB_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                      ConstantsOutput.FOLDER_UTILS,
                                                                      ConstantsOutputClientWeb.CLASSNAME_RANDOM_VALUES_WEB + javascriptExtension);
        mandatoryImports.add(randomValuesImport);

        // Import the parser values generator
        final String parserValuesImport    = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER   + ConstantsCommon.STRING_SLASH +
                                                                      ConstantsOutputClientWeb.WEB_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                      ConstantsOutput.FOLDER_UTILS,
                                                                      ConstantsOutputClientWeb.CLASSNAME_PARSER_VALUES_WEB + javascriptExtension);
        mandatoryImports.add(parserValuesImport);

        // Import the scheme values generator
        final String schemeValuesImport    = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                   ConstantsOutputClientWeb.WEB_GENERATED_FOLDER  + ConstantsCommon.STRING_SLASH +
                                                                   ConstantsOutput.FOLDER_UTILS,
                                                                   ConstantsOutput.CLASSNAME_SCHEMES_VALUES + javascriptExtension);
        mandatoryImports.add(schemeValuesImport);
        
        // Import the common exception generator
        final String commonExceptionImport = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER   + ConstantsCommon.STRING_SLASH +
                                                                   	  ConstantsOutputClientWeb.WEB_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                   	  ConstantsOutput.FOLDER_UTILS,
                                                                   	  ConstantsOutput.CLASSNAME_COMMON_EXCEPTION + javascriptExtension);
        mandatoryImports.add(commonExceptionImport) ;

        return mandatoryImports;
    }

    /**
     * @param javascriptExtension with the javascript extension
     * @return the additional model imports (javascript)
     */
    private List<String> generateAdditionalImportsModel(final String javascriptExtension)
    {
        final List<String> mandatoryImports = new ArrayList<String>();

        // Import all the necessary definitions used in the paths
        for (final String complexName : this.getOutputLanguageItems().getDefinitionsListNames())
        {
            final String modelImport = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER   + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutputClientWeb.WEB_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutput.FOLDER_MODEL,
                                                                this.getOutputLanguageNaming().prefixClassName(complexName) + 
                                                                this.getPackageUtilsJavascript().getProjectVersionSuffix()  + 
                                                                javascriptExtension) ;
            mandatoryImports.add(modelImport);
        }

        return mandatoryImports;
    }

    /**
     * @param javascriptExtension with the javascript extension
     * @return the additional rest imports (javascript)
     */
    private List<String> generateAdditionalImportsRest(final String javascriptExtension)
    {
        final List<String> mandatoryImports = new ArrayList<String>();

        // RestHandler import
        final String restHandlerImport = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER   +
                                                                  ConstantsCommon.STRING_SLASH 			  +
                                                                  ConstantsOutputClientWeb.WEB_GENERATED_FOLDER +
                                                                  ConstantsCommon.STRING_SLASH 			  +
                                                                  ConstantsOutputClientWeb.WEB_FOLDER_REST,
                                                                  ConstantsOutput.CLASSNAME_REST_HANDLER 			  		 +
                                                                  this.getPackageUtilsJavascript().getProjectVersionSuffix() +
                                                                  javascriptExtension) ;
        mandatoryImports.add(restHandlerImport);

        // RestListener import
        final String restListenerImport = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER   +
                                                                   ConstantsCommon.STRING_SLASH 		   +
                                                                   ConstantsOutputClientWeb.WEB_GENERATED_FOLDER +
                                                                   ConstantsCommon.STRING_SLASH 		   +
                                                                   ConstantsOutputClientWeb.WEB_FOLDER_REST,
                                                                   ConstantsOutputClientWeb.CLASSNAME_REST_LISTENER_WEB      		  +
                                                                   this.getPackageUtilsJavascript().getProjectVersionSuffix() +
                                                                   javascriptExtension) ;
        mandatoryImports.add(restListenerImport);

        return mandatoryImports;
    }

    /**
     * @param javascriptExtension with the javascript extension
     * @return the example imports (javascript)
     */
    private List<String> generateExampleImportsJS(final String javascriptExtension)
    {
        final List<String> exampleImports = new ArrayList<String>();

        // External libraries
        exampleImports.addAll(this.generateExternalLibrariesExampleImports(javascriptExtension));

        // Tester libraries
        exampleImports.addAll(this.generateAdditionalImportsTester(javascriptExtension));

        return exampleImports;
    }

    /**
     * @param javascriptExtension with the javascript extension
     * @return the AngularJS example imports (javascript)
     */
    private List<String> generateExternalLibrariesExampleImports(final String javascriptExtension)
    {
        final List<String> angularJsExampleImports = new ArrayList<String>();

        // Import Angular Core
        final String jquery = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER  + ConstantsCommon.STRING_SLASH +
                                                       ConstantsOutputClientWeb.WEB_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                       ConstantsOutputClientWeb.WEB_EXT_LIBS_JQUERY,
                                                       ConstantsOutputClientWeb.WEB_EXT_LIBS_JQUERY + javascriptExtension);
        angularJsExampleImports.add(jquery);

        // Import Angular Animate
        final String bootstrap = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER  + ConstantsCommon.STRING_SLASH +
                                                          ConstantsOutputClientWeb.WEB_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                          ConstantsOutputClientWeb.WEB_BOOTSTRAP,
                                                          ConstantsOutputClientWeb.WEB_BOOTSTRAP + javascriptExtension);
        angularJsExampleImports.add(bootstrap);

        return angularJsExampleImports;
    }

    /**
     * @param javascriptExtension with the javascript extension
     * @return the additional tester imports (javascript)
     */
    private List<String> generateAdditionalImportsTester(final String javascriptExtension)
    {
        final List<String> exampleImportsTester = new ArrayList<String>();

        // Tester import
        final String testerImport = this.getFileResourceName(ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER +
        													 ConstantsCommon.STRING_SLASH 		   +
                                                             ConstantsOutputClientWeb.WEB_EXAMPLE_FOLDER,
                                                             ConstantsOutputClientWeb.EXAMPLE_TESTER_WEB 	 			 		+
                                                             this.getPackageUtilsJavascript().getProjectVersionSuffix() +
                                                             javascriptExtension) ;
        exampleImportsTester.add(testerImport);

        return exampleImportsTester;
    }

    /**
     * @param cssExtension with the css extension
     * @return the additional tester imports (css)
     */
    private Object generateExampleImportsCSS(final String cssExtension)
    {
        final List<String> exampleImportsCSS = new ArrayList<String>();

        // Tester bootstrapCSS
        final String bootstrapCSS = this.getFileResourceName(ConstantsOutputClientWeb.WEB_CSS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                             ConstantsOutputClientWeb.WEB_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                             ConstantsOutputClientWeb.CSS_BOOSTRAP,
                                                             ConstantsOutputClientWeb.CSS_BOOSTRAP + cssExtension);
        exampleImportsCSS.add(bootstrapCSS);

        // Tester css fonts
        final String exampleCSSFonts = this.getFileResourceName(ConstantsOutputClientWeb.WEB_CSS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutputClientWeb.WEB_EXAMPLE_FOLDER,
                                                                ConstantsOutputClientWeb.CSS_EXAMPLE_FONTS + cssExtension);
        exampleImportsCSS.add(exampleCSSFonts);

        // Tester css
        final String exampleCSS = this.getFileResourceName(ConstantsOutputClientWeb.WEB_CSS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                           ConstantsOutputClientWeb.WEB_EXAMPLE_FOLDER,
                                                           ConstantsOutputClientWeb.CSS_EXAMPLE + cssExtension);
        exampleImportsCSS.add(exampleCSS);

        return exampleImportsCSS;
    }
}
