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

package com.bbva.kltt.apirest.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to handle file and directories
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class FilesUtility
{
	/** Logger of the class */
	private static final Logger LOGGER = LoggerFactory.getLogger(FilesUtility.class) ;

	/** Private constructor to avoid instantiation */
	private FilesUtility()
	{
        // Nothing to do here
	}

	/**
	 * Helper method that creates the tree of directories if not created yet.
	 *
	 * For example if the package is: 'com.bbva.codegen' and the base directory is '/home/test'
	 * the directory tree to create would be: 'home/test/com/bbva/codegen'
	 *
	 * @param baseDirectory with a {@link File} that is a reference to base directory
	 * @param packageName 	with the name of the package in order to compose the full directory(baseDirectory+packageName), if null it will use only the base directory
	 * @return the path that has been created
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static String initializeOutputDirectory(final File baseDirectory, final String packageName) throws APIRestGeneratorException
	{
		// Get the full path first
		final String fullDirPath = FilesUtility.convertPackageToPath(baseDirectory.getAbsolutePath(), packageName);

		// Create the directory tree
		FilesUtility.createFullDirectoryTree(fullDirPath);

		// Return the path
		return fullDirPath;
	}

	/**
	 * Create package directories
	 * @param fullDirPath with the full directory path
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static void createFullDirectoryTree(final String fullDirPath) throws APIRestGeneratorException
	{
		final File folder = new File(fullDirPath);

		if (!folder.exists() && !folder.mkdirs())
		{
			final String errorString = "Error creating directory tree for dir: " + fullDirPath ;
			
			FilesUtility.LOGGER.error(errorString);
			throw new APIRestGeneratorException(errorString);
		}

		FilesUtility.LOGGER.info("Created directory structure {}", fullDirPath);
	}

    /**
     * Create package directories
     * @param folderDir with the folder directory
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static void createFullDirectoryTree(final File folderDir) throws APIRestGeneratorException
    {
        if (!folderDir.exists() && !folderDir.mkdirs())
        {
        	final String errorString = "Error creating directory tree for dir: " + folderDir ;
        	
        	FilesUtility.LOGGER.error(errorString);
            throw new APIRestGeneratorException(errorString);
        }

        FilesUtility.LOGGER.info("Created directory structure {}", folderDir.getAbsolutePath());
    }

	/**
	 * Converts the given package to a path structure with the right OS path separators.
	 * It will attach the created path of the provided base path.
	 *
	 * @param basePath    the base path of contact the package path to
	 * @param packageName the package name, if null it will just use the base path
	 *
	 * @return the base path concatenated with the name converted to path
	 */
	public static String convertPackageToPath(final String basePath, final String packageName)
	{
		String outcome = basePath ;
		
		if (packageName != null)
		{
			final StringBuilder stringBuilder = new StringBuilder() ;
			
			stringBuilder.append(basePath) ;
			stringBuilder.append(File.separator) ;
			stringBuilder.append(FilesUtility.convertPackageToPath(packageName)) ;
			
			outcome = stringBuilder.toString() ;
		}

		return outcome ;
	}

	/**
	 * Converts the given package to a path structure with the right OS path separators
	 *
	 * @param packageName the package name
	 *
	 * @return the name converted to path
	 */
	public static String convertPackageToPath(final String packageName)
	{
		return packageName.replace(ConstantsCommon.STRING_DOT, File.separator);
	}
	
	/**
	 * Converts the given package to a path structure with the slashes separators
	 *
	 * @param packageName the package name
	 *
	 * @return the name converted to path
	 */
	public static String convertPackageToSlashesPath(final String packageName)
	{
		return packageName.replace(ConstantsCommon.STRING_DOT, ConstantsCommon.STRING_SLASH) ;
	}
	
	/**
	 * This method is useful when the file name contains the extension
	 *
	 * @param outputDirectoryPath the output directory path where the file should be created
	 * @param fileName            the name of the file that is going to be created
	 * @return the created File
	 */
	public static File createOutputFile(final String outputDirectoryPath, final String fileName)
	{
		return FilesUtility.createOutputFile(outputDirectoryPath, fileName, ConstantsCommon.STRING_EMPTY) ;
	}

	/**
	 * This method creates a new file and returns it
	 *
	 * @param outputDirectoryPath the output directory path where the file should be created
	 * @param fileName            the name of the file that is going to be created
	 * @param fileExtension       the extension of the file that is going to be created
	 * @return the created File
	 */
	public static File createOutputFile(final String outputDirectoryPath, final String fileName, final String fileExtension)
	{
		return new File(outputDirectoryPath + File.separator + fileName + fileExtension);
	}
	
	/**
	 * Copy files using streams
	 * @param source	   				 with the source file
	 * @param destination  				 with the destination file
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static void copyFileUsingStream(final File source, final File destination) throws APIRestGeneratorException
	{
		try
		{
			FilesUtility.copyFileUsingStream(new FileInputStream(source), destination) ;
		}
		catch (FileNotFoundException fileNotFoundExc)
		{
			final String errorString = "FileNotFoundException while copying the following source file: " + source.getAbsolutePath() ;
        	
			FilesUtility.LOGGER.error(errorString, fileNotFoundExc) ;
            throw new APIRestGeneratorException(errorString, fileNotFoundExc) ;
		}
	}
	
	/**
	 * Copy files using streams
	 * @param inputStream 				 with the input stream
	 * @param destination 				 with the destination
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static void copyFileUsingStream(final InputStream inputStream, final File destination) throws APIRestGeneratorException
	{
	    OutputStream outputStream = null ;
	    
	    try 
	    {
	        outputStream = new FileOutputStream(destination) ;
	        byte[] buffer = new byte[ConstantsCommon.MAGIC_NUMBER_1024] ;
	        int length = -1 ;
	        while ((length = inputStream.read(buffer)) > 0)
	        {
				outputStream.write(buffer, 0, length);
	        }
	    }
	    catch (IOException ioException)
	    {
    		final String errorString = "IOException while copying the file to the following destination: " + destination.getAbsolutePath() ;
        	
    		FilesUtility.LOGGER.error(errorString, ioException) ;
            throw new APIRestGeneratorException(errorString, ioException) ;
		}
	    finally
	    {
	    	FilesUtility.closeStreams(inputStream, outputStream) ;
	    }
	}
	
	/**
	 * Close streams
	 * @param inputStream  				 with the input stream
	 * @param outputStream 				 with the output stream
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private static void closeStreams(final InputStream inputStream, final OutputStream outputStream) throws APIRestGeneratorException
	{
		if (inputStream != null)
    	{
    		try
    		{
				inputStream.close();
			}
    		catch (IOException ioException)
    		{
    			String errorString = "IOException while closing the input stream after copied the file" ;
            	
    			FilesUtility.LOGGER.error(errorString, ioException) ;
                throw new APIRestGeneratorException(errorString, ioException) ;
			}
    	}
    	
    	if (outputStream != null)
    	{
    		try
    		{
				outputStream.close();
			}
    		catch (IOException ioException)
    		{
				String errorString = "IOException while closing the output stream after copied the file" ;
            	
				FilesUtility.LOGGER.error(errorString, ioException) ;
                throw new APIRestGeneratorException(errorString, ioException) ;
			}
    	}
	}
	
	/**
	 * @param filePath with the file path
	 * @return the file name of the path
	 */
	public static String getFileNameOfPath(final String filePath)
	{
		final int lastIndexFileSep = filePath.lastIndexOf(File.separator) ;
		return filePath.substring(lastIndexFileSep + 1) ;
	}
	
	/**
	 * @param filePath with the file path
	 * @return the file name for the JSON representation
	 */
	public static String getFileNameForJson(final String filePath)
	{
		final String fileName  = FilesUtility.getFileNameOfPath(filePath) ;
		final int lastIndexExt = fileName.lastIndexOf(ConstantsCommon.STRING_DOT) ;
		return fileName.substring(0, lastIndexExt) + ConstantsCommon.STRING_HYPHEN + ConstantsCommon.EXTENSION_JSON + 
													 ConstantsCommon.STRING_DOT    + ConstantsCommon.EXTENSION_JSON ;
	}
	
	/**
	 * Load file content from the classpath
	 * @param location with the location
	 * @return the input stream
	 */
	public static String loadFileContentFromClasspath(final String location)
	{
		InputStream inputStream = FilesUtility.class.getResourceAsStream(location) ;
		
		if (inputStream == null)
		{
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(location) ;
		}
		
		if (inputStream == null)
		{
			inputStream = ClassLoader.getSystemResourceAsStream(location) ;
		}
		
		if (inputStream != null)
		{
			try
			{
				return IOUtils.toString(inputStream) ;
			}
			catch (IOException ioException)
			{
				final String errorString = "Could not read " + location + " from the classpath to get the content" ;
				
				FilesUtility.LOGGER.error(errorString, ioException) ;
				throw new RuntimeException(errorString, ioException) ;
			}
		}
		
		final String errorString = "Could not find " + location + " on the classpath to get the content" ;
		
		FilesUtility.LOGGER.error(errorString) ;
		throw new RuntimeException(errorString) ;
	}

    /**
     * Check if a directory exists
     *
     * @param directoryPath with the String of the full path
     * @return true if it exists
     */
    public static boolean existDirectory(final String directoryPath)
    {
        boolean isExists = false;
    	if (directoryPath != null)
        {
            File dir = new File(directoryPath);
            isExists = dir.exists();
        }

        return isExists;
    }
}
