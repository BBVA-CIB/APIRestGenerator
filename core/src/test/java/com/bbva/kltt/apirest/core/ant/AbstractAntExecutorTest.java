package com.bbva.kltt.apirest.core.ant;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class AbstractAntExecutorTest
{
	/** Constant - Full Package Launcher Class */
	private static final String FULL_PKG_LAUNCHER_CLASS = "fullPkgLauncherClass" ;
	
	/** Constant - Parser type */
	private static final String DELIVERABLE_FILE_NAME 	= "deliverableFileName" ;
	
	/** Constant - File Path */
	private static final String FILE_PATH 				= "filePath" ;
	
	/** Constant - Temporary directory */
	private static final String TEMPORARY_DIR 			= "temporaryDir" ;
	
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final MyAntExecutor myAntExecutor = new MyAntExecutor(ConstantsTest.SRC_TEST_RESOURCES_PATH,
															  ConstantsTest.ANT_FILE,
															  ConstantsTest.TRANSLATOR_TYPE,
															  FULL_PKG_LAUNCHER_CLASS,
															  ConstantsTest.PARSER_TYPE,
															  DELIVERABLE_FILE_NAME) ;
		
		myAntExecutor.executeAntTask(FILE_PATH, System.err, System.out, TEMPORARY_DIR) ;
	}
	
}
