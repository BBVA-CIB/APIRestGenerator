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

package com.bbva.kltt.apirest.core.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsAnt;
import com.bbva.kltt.apirest.core.util.ConstantsLauncher;

import java.io.File;
import java.io.PrintStream;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class AbstractAntExecutor
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAntExecutor.class);

    /**
     * Attribute - Generator Builder Path
     */
    private final String generatorBuilderPath;

    /**
     * Attribute - Ant File
     */
    private final String antFile;

    /**
     * Attribute - Translator Type
     */
    private final String translatorType;

    /**
     * Attribute - Parser Type
     */
    private final String parserType;

    /**
     * Full package launcher class
     */
    private final String fullPkgLauncherClass;
    
    /**
     * Full package launcher class
     */
    private final String deliverableFileName ;

    /**
     * @param generatorBuilderPath with the generator builder path
     * @param antFile              with the ant file
     * @param translatorType       with the translator type
     * @param fullPkgLauncherClass with the full package launcher class
     * @param parserType           with the parser type
     * @param deliverableFileName  with the deliverable file name
     */
    public AbstractAntExecutor(final String generatorBuilderPath,
                               final String antFile,
                               final String translatorType,
                               final String fullPkgLauncherClass,
                               final String parserType,
                               final String deliverableFileName)
    {
        this.generatorBuilderPath = generatorBuilderPath;
        this.antFile              = antFile;
        this.translatorType       = translatorType;
        this.fullPkgLauncherClass = fullPkgLauncherClass;
        this.parserType           = parserType;
        this.deliverableFileName  = deliverableFileName ;
    }

    /**
     * @param filePath     with the file path
     * @param errorStream  with the error stream
     * @param outputStream with the output stream
     * @param temporaryDir with the temporary directory
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void executeAntTask(final String filePath,
                               final PrintStream errorStream,
                               final PrintStream outputStream,
                               final String temporaryDir) throws APIRestGeneratorException
    {
        final File buildFile  = new File(this.generatorBuilderPath + File.separator + this.antFile);
        final Project project = this.generateProject(filePath, errorStream, outputStream, temporaryDir, buildFile);

        try
        {
            project.fireBuildStarted();
            project.init();

            final ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
            project.addReference(ConstantsAnt.ANT_REF_PROJECT_HELPER, projectHelper);
            projectHelper.parse(project, buildFile);

            project.executeTarget(this.getGoalTask());
            project.fireBuildFinished(null);
        }
        catch (BuildException buildException)
        {
            project.fireBuildFinished(buildException);

            final String errorString = "BuildException while calling the ANT task with the following parameters: [antFile: " +
                                       this.antFile +
                                       ", filePath: " + filePath + "]";
            AbstractAntExecutor.LOGGER.error(errorString, buildException);
            throw new APIRestGeneratorException(errorString, buildException);
        }
    }

    /**
     * @param filePath     with the file path
     * @param errorStream  with the error stream
     * @param outputStream with the output stream
     * @param temporaryDir with the temporary directory
     * @param buildFile    with the build file
     * @return a new project for ant execution
     */
    private Project generateProject(final String filePath,
                                    final PrintStream errorStream,
                                    final PrintStream outputStream,
                                    final String temporaryDir,
                                    final File buildFile)
    {
        final Project project = new Project();

        project.setUserProperty(ConstantsAnt.ANT_FILE_PROPERTY,           buildFile.getAbsolutePath());
        project.setUserProperty(ConstantsAnt.ANT_PROP_FULL_PKG_LAUN_CL,   this.fullPkgLauncherClass);
        project.setUserProperty(ConstantsAnt.ANT_PROP_TRANSLATOR_TYPE,    this.translatorType);
        project.setUserProperty(ConstantsAnt.ANT_PROP_PARSER_TYPE,        this.parserType);
        project.setUserProperty(ConstantsAnt.ANT_PROP_DELIV_FILENAME,     this.deliverableFileName) ;

        project.setProperty(ConstantsLauncher.CODE_GEN_OUT_DIR_EXTE_FLAG, temporaryDir);
        project.setProperty(ConstantsLauncher.FILE_PATH_EXTE_FLAG,        filePath);

        project.addBuildListener(this.getConsoleLogger(errorStream, outputStream));

        return project;
    }

    /**
     * @return the goal task
     */
    private String getGoalTask()
    {
        String outcome = null;
        String operativeSystem = System.getProperty("os.name").toLowerCase();

        if (operativeSystem.indexOf(ConstantsAnt.OS_WINDOWS) >= 0)
        {
            outcome = ConstantsAnt.GOAL_TASK_WINDOWS;
        }
        else
        {
            outcome = ConstantsAnt.GOAL_TASK_LINUX;
        }

        return outcome;
    }

    /**
     * @param errorStream  with the error stream
     * @param outputStream with the output stream
     * @return a new console logger with the configured parameters
     */
    private DefaultLogger getConsoleLogger(final PrintStream errorStream, final PrintStream outputStream)
    {
        final DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(errorStream);
        consoleLogger.setOutputPrintStream(outputStream);
        consoleLogger.setMessageOutputLevel(ConstantsAnt.ANT_LEVEL_LOGGER);

        return consoleLogger;
    }
}
