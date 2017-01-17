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

package com.bbva.kltt.generator.clientTypescript.typescript.example;

import com.bbva.kltt.generator.clientTypescript.typescript.GeneratorBaseTypescript;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.generator.clientTypescript.util.ConstantsOutputTypescript;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleLauncherTypescriptGenerator extends GeneratorBaseTypescript
{
    /**
     * Build the launcher examples generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the TypeScript
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleLauncherTypescriptGenerator(final File baseDestDir,
                                              final GenerationParameters generationParams,
                                              final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler);
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        final String javascriptExtension = ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_JS;
        final String cssExtension = ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_CSS;

        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateMandatoryImportsJS(javascriptExtension));
        context.put(ConstantsOutputTypescript.VP_TS_EXAMP_IMPORTS_JS, this.generateExampleImportsJS(javascriptExtension));
        context.put(ConstantsOutputTypescript.VP_TS_EXAMP_IMPORTS_CSS, this.generateExampleImportsCSS(cssExtension));

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
        return ConstantsOutputTypescript.INDEX_PAGE_TS;
    }

    /**
     * We have to redefine the output extension because it is not a TypeScript file.
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
        final String esPromiseImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutputTypescript.TYPESCRIPT_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutputTypescript.JS_EXT_LIBS_ES6_PROMISE,
                                                                ConstantsOutputTypescript.JS_EXT_LIBS_ES6_PROMISE + javascriptExtension);
        mandatoryImports.add(esPromiseImport);

        final String jqueryImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                             ConstantsOutputTypescript.TYPESCRIPT_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                             ConstantsOutputTypescript.JS_EXT_LIBS_JQUERY,
                                                             ConstantsOutputTypescript.JS_EXT_LIBS_JQUERY + javascriptExtension);
        mandatoryImports.add(jqueryImport);

        final String rxAllImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                            ConstantsOutputTypescript.TYPESCRIPT_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                            ConstantsOutputTypescript.JS_EXT_LIBS_RX_ALL,
                                                            ConstantsOutputTypescript.JS_EXT_LIBS_RX_ALL + javascriptExtension);
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
        final String randomValuesImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                   ConstantsOutputTypescript.TYPESCRIPT_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                   ConstantsOutput.FOLDER_UTILS,
                                                                   ConstantsOutputTypescript.CLASSNAME_RANDOM_VALUES_TS + javascriptExtension);
        mandatoryImports.add(randomValuesImport);

        // Import the parser values generator
        final String parserValuesImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                   ConstantsOutputTypescript.TYPESCRIPT_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                   ConstantsOutput.FOLDER_UTILS,
                                                                   ConstantsOutputTypescript.CLASSNAME_PARSER_VALUES_TS + javascriptExtension);
        mandatoryImports.add(parserValuesImport);

        // Import the scheme values generator
        final String schemeValuesImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                   ConstantsOutputTypescript.TYPESCRIPT_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                   ConstantsOutput.FOLDER_UTILS,
                                                                   ConstantsOutput.CLASSNAME_SCHEMES_VALUES + javascriptExtension);
        mandatoryImports.add(schemeValuesImport);

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
            final String modelImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutputTypescript.TYPESCRIPT_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutput.FOLDER_MODEL,
                                                                this.getOutputLanguageNaming().prefixClassName(complexName) + javascriptExtension);
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
        final String restHandlerImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER +
                                                                    ConstantsCommon.STRING_SLASH +
                                                                    ConstantsOutputTypescript.TYPESCRIPT_GENERATED_FOLDER +
                                                                    ConstantsCommon.STRING_SLASH +
                                                                    ConstantsOutputTypescript.TYPESCRIPT_FOLDER_REST,
                                                                  ConstantsOutput.CLASSNAME_REST_HANDLER +
                                                                    this.getTitleCamelCase() + javascriptExtension);
        mandatoryImports.add(restHandlerImport);

        // RestListener import
        final String restListenerImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER +
                                                                    ConstantsCommon.STRING_SLASH +
                                                                    ConstantsOutputTypescript.TYPESCRIPT_GENERATED_FOLDER +
                                                                    ConstantsCommon.STRING_SLASH +
                                                                    ConstantsOutputTypescript.TYPESCRIPT_FOLDER_REST,
                                                                   ConstantsOutputTypescript.CLASSNAME_REST_LISTENER_TS +
                                                                    this.getTitleCamelCase() + javascriptExtension);
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
        final String jquery = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                       ConstantsOutputTypescript.TYPESCRIPT_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                       ConstantsOutputTypescript.JS_EXT_LIBS_JQUERY,
                                                       ConstantsOutputTypescript.JS_EXT_LIBS_JQUERY + javascriptExtension);
        angularJsExampleImports.add(jquery);

        // Import Angular Animate
        final String bootstrap = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                          ConstantsOutputTypescript.TYPESCRIPT_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                          ConstantsOutputTypescript.JS_BOOTSTRAP,
                                                          ConstantsOutputTypescript.JS_BOOTSTRAP + javascriptExtension);
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
        final String testerImport = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                             ConstantsOutputTypescript.TYPESCRIPT_EXAMPLE_FOLDER,
                                                             ConstantsOutputTypescript.EXAMPLE_TESTER_TS + javascriptExtension);
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
        final String bootstrapCSS = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_CSS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                             ConstantsOutputTypescript.TYPESCRIPT_EXTERNAL_FOLDER + ConstantsCommon.STRING_SLASH +
                                                             ConstantsOutputTypescript.CSS_BOOSTRAP,
                                                             ConstantsOutputTypescript.CSS_BOOSTRAP + cssExtension);
        exampleImportsCSS.add(bootstrapCSS);

        // Tester css fonts
        final String exampleCSSFonts = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_CSS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                                ConstantsOutputTypescript.TYPESCRIPT_EXAMPLE_FOLDER,
                                                                ConstantsOutputTypescript.CSS_EXAMPLE_FONTS + cssExtension);
        exampleImportsCSS.add(exampleCSSFonts);

        // Tester css
        final String exampleCSS = this.getFileResourceName(ConstantsOutputTypescript.TYPESCRIPT_CSS_MAIN_FOLDER + ConstantsCommon.STRING_SLASH +
                                                           ConstantsOutputTypescript.TYPESCRIPT_EXAMPLE_FOLDER,
                                                           ConstantsOutputTypescript.CSS_EXAMPLE + cssExtension);
        exampleImportsCSS.add(exampleCSS);

        return exampleImportsCSS;
    }
}
