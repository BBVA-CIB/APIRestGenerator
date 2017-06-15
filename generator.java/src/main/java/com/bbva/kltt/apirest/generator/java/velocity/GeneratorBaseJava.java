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

package com.bbva.kltt.apirest.generator.java.velocity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bbva.kltt.apirest.core.generator.GeneratorBase;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.java.util.PackageUtilsJava;

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
     */
    public GeneratorBaseJava(final File baseDestDir, final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;

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
     * Generate additional imports for exceptions
     *
     * @return the model imports
     */
    protected List<String> generateImportsExceptions()
    {
        final List<String> exceptionsImports = new ArrayList<String>() ;

        final String packagePrefix = this.getPackageUtilsJava().getModelsExceptionPackage() + ConstantsCommon.STRING_DOT ;
        
        // Add the custom exceptions
        final Map<String, List<Response>> serverResponseExceptionsMap = this.getOutputLanguageExceptions().getOutboundServerExceptionsMap() ;
        for (final Entry<String, List<Response>> entry : serverResponseExceptionsMap.entrySet())
        {
            final String operationId 		            = entry.getKey() ;
            final List<Response> exceptionResponsesList = entry.getValue() ;
            
            for (final Response exceptionResponse : exceptionResponsesList)
            {
            	final String exceptionClassName = this.getOutputLanguageExceptions().getCustomExceptionAsClassName(operationId, exceptionResponse) ;
            	exceptionsImports.add(packagePrefix + exceptionClassName) ;
            }
        }
        
        // Add the generated exception
        exceptionsImports.add(this.generateImportExceptionCommonException()) ;

        return exceptionsImports ;
    }
    
    /**
     * @return the import exception with the CommonException class
     */
    protected String generateImportExceptionCommonException()
    {
    	return this.getPackageUtilsJava().getModelsExceptionPackage() + ConstantsCommon.STRING_DOT +
    		   this.getOutputLanguageExceptions().getCommonExceptionClassName() ;
    }

    /**
     * @param translatorType with the translator type
     * @return the additional import - rest handler interface
     */
    protected String generateImportRestHandlerInterface(final String translatorType)
    {
        return this.getPackageUtilsJava().getRestHandlerInterfacesPackage(translatorType) +
               ConstantsCommon.STRING_DOT                                                 +
               ConstantsOutputJava.INTERFACE_NAME_REST_HANDLER                            +
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
               ConstantsOutputJava.INTERFACE_NAME_REST_LISTENER                           +
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
               ConstantsOutputJava.CLASSNAME_EXAMPLE_LISTENER                +
               this.getTitleCamelCase();
    }

    /**
     * Given the name of the resource it returns the relative path of the resource file
     * <p>
     * It will look for the resource on the same package the generator is located.
     *
     * @param relativeFolder     with the relative folder
     * @param resourceName       with the resource name
     * @return the real path for the resource (template or macro)
     */
    protected String getCommonJavaResourcePath(final String relativeFolder, final String resourceName)
    {
        // Firstly, find the resource, it should be in the same package than the generation class
        String resourcePackage = GeneratorBaseJava.class.getPackage().getName();

        // Now convert the package to resource file location
        return this.getRealPath(resourcePackage.replace(ConstantsCommon.STRING_DOT, ConstantsCommon.STRING_SLASH),
                                relativeFolder +
                                resourceName   + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_VELOCITY) ;
    }
}
