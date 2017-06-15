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

package com.bbva.kltt.apirest.core.web;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsAnt;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.bbva.kltt.apirest.core.util.FilesUtility;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class Utilities
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(Utilities.class) ;
	
    /** Atomic Long - Temporary Directory - Suffix */
    private static AtomicLong TEMP_FILE_SUFFIX = new AtomicLong() ;
    
	/**
	 * Private constructor
	 */
	private Utilities()
	{
		 // Empty constructor
	}
	
	/**
	 * @param generatorBuilderPath with the generator builder path
	 * @return a temporary output directory for the generation
	 */
	public static String generateTemporaryOutputDirectory(final String generatorBuilderPath)
	{
	    boolean existFolder = true;
	    String fullPathDirectory = generatorBuilderPath + File.separator + ConstantsAnt.BASE_GEN_FOLDERS
                                   + ConstantsAnt.TEMP_DIRECTORY_PREFIX;

	    while (existFolder)
        {
            existFolder = FilesUtility.existDirectory(fullPathDirectory + TEMP_FILE_SUFFIX.incrementAndGet());
        }

		return ConstantsAnt.TEMP_DIRECTORY_PREFIX + TEMP_FILE_SUFFIX.get();
	}
	
	/**
	 * Read the file content
	 * @param filePath with the file path
	 * @return the file content as byte array
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static byte[] readFileContent(final String filePath) throws APIRestGeneratorException
	{
		Path path = null ;
		
        if (filePath.toLowerCase().startsWith(ConstantsInput.SO_PATH_STRING_PREFIX))
        {
        	path = Paths.get(URI.create(filePath)) ;
        }
        else
        {
        	path = Paths.get(filePath, new String[0]) ;
        }
		
        byte[] fileContent = null ;
        
		if (Files.exists(path, new LinkOption[0]))
		{
			try 
			{
				fileContent = FileUtils.readFileToByteArray(path.toFile()) ;
			} 
			catch (IOException ioException)
			{
		    	final String errorString = "IOException when reading the file '" + filePath + "': " + ioException ;
		    	
		    	Utilities.LOGGER.error(errorString, ioException) ;
		    	throw new APIRestGeneratorException(errorString, ioException) ;
			}
		}
		
		return fileContent ;
	}
}
