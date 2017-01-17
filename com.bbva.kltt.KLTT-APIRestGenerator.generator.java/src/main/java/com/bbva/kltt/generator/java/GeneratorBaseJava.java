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

package com.bbva.kltt.generator.java;

import com.bbva.kltt.core.generator.GeneratorBase;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Base methods for Java generation
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBaseJava extends GeneratorBase
{
    /**
     * Attribute - Package utility - Java
     */
    private final PackageUtilsJava packageUtilsJava;

    /**
     * Build the generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information handler
     * @param generationPackage with the package of the generator module
     */
    public GeneratorBaseJava(final File baseDestDir,
                             final GenerationParameters generationParams,
                             final ParsedInfoHandler parsedInfoHandler,
                             final String generationPackage)
    {
        super(baseDestDir, generationParams, parsedInfoHandler, generationPackage);

        this.packageUtilsJava = new PackageUtilsJava(this.getOutputLanguageGeneratorParams().getBUnit(),
                                                     parsedInfoHandler.getInfoValues().getTitle());
    }

    /**
     * @return the packageUtilsJava
     */
    public PackageUtilsJava getPackageUtilsJava()
    {
        return this.packageUtilsJava;
    }

    @Override
    protected String getOutputExtension()
    {
        return ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_JAVA;
    }

    /**
     * Generate additional imports for models
     *
     * @return the model imports
     */
    protected List<String> generateImportsModel()
    {
        final List<String> modelImports = new ArrayList<String>();

        // Import all the necessary definitions used in the paths
        for (final String complexName : this.getOutputLanguageItems().getDefinitionsListNames())
        {
            modelImports.add(this.getPackageUtilsJava().getModelsPackage() + ConstantsCommon.STRING_DOT + this.getOutputLanguageNaming().prefixClassName(complexName));
        }

        return modelImports;
    }

    /**
     * @param translatorType with the translator type
     * @return the additional import - rest handler interface
     */
    protected String generateImportRestHandlerInterface(final String translatorType)
    {
        return this.getPackageUtilsJava().getRestHandlerInterfacesPackage(translatorType) +
               ConstantsCommon.STRING_DOT                                                 +
               ConstantsOutput.INTERFACE_NAME_REST_HANDLER                                +
               this.getTitleCamelCase();
    }

    /**
     * @param translatorType with the translator type
     * @return the additional import - listener interface
     */
    protected String generateImportListenerInterface(final String translatorType)
    {
        return this.getPackageUtilsJava().getRestHandlerInterfacesPackage(translatorType) +
               ConstantsCommon.STRING_DOT                                                 +
               ConstantsOutput.INTERFACE_NAME_REST_LISTENER                               +
               this.getTitleCamelCase();
    }

    /**
     * @param translatorType with the translator type
     * @return the additional import - handler implementation
     */
    protected String generateImportHandlerImpl(final String translatorType)
    {
        return this.getPackageUtilsJava().getRestHandlerImplPackage(translatorType) +
               ConstantsCommon.STRING_DOT                                           +
               ConstantsOutput.CLASSNAME_REST_HANDLER                               +
               this.getTitleCamelCase();
    }

    /**
     * @param translatorType with the translator type
     * @return the additional import - example listener
     */
    protected String generateImportExampleListener(final String translatorType)
    {
        return this.getPackageUtilsJava().getExamplesPackage(translatorType) +
               ConstantsCommon.STRING_DOT                                    +
               ConstantsOutput.CLASSNAME_EXAMPLE_LISTENER                    +
               this.getTitleCamelCase();
    }

    /**
     * Given the name of the template it returns the relative path of the template file
     * <p>
     * It will look for the template on the same package the generator is located.
     *
     * @param relativeFolder     with the relative folder
     * @param templateName       with the name of the template, without extension.
     * @return the real path for the template
     */
    protected String getTemplateCommonJavaResourceName(final String relativeFolder, final String templateName)
    {
        // Firstly, find the template, it should be in the same package than the generation class
        String templatePackage 	= GeneratorBaseJava.class.getPackage().getName();

        // Now convert the package to template file location
        return this.getRealPath(templatePackage.replace(ConstantsCommon.STRING_DOT, ConstantsCommon.STRING_SLASH),
                                relativeFolder +
                                templateName   + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_VELOCITY, "") ;
    }
}
