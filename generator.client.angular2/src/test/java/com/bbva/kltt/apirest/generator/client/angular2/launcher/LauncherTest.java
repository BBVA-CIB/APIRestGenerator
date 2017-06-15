package com.bbva.kltt.apirest.generator.client.angular2.launcher;

import java.io.File;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

import org.junit.Assert ;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class LauncherTest
{
	/** Constant - YAML Test File */
	private static final String YAML_TEST_FILE		    	= "src" + File.separator + "test" + File.separator + "resources" + File.separator + "footballPlayers.yaml" ;
	
	/** Constant - Command Line Flag - YAML File Path */
	private static final String CMD_LINE_YAML_FILE_PATH 	= "-f " + YAML_TEST_FILE ;
	
	/** Constant - Command Line Flag - Codegen Output Directory */
	private static final String CMD_LINE_CODEGEN_OUTPUT_DIR = "-o target" ;
	
	/** Constant - Command Line Flag - Parser type */
	private static final String CMD_LINE_PARSER_TYPE	    = "-p swagger" ;
	
	@Test
	public void fullTest()
	{
		try
		{
			String[] commandLine = new String[]{CMD_LINE_YAML_FILE_PATH, CMD_LINE_CODEGEN_OUTPUT_DIR, CMD_LINE_PARSER_TYPE} ;
			Launcher.main(commandLine) ;
		}
		catch (APIRestGeneratorException apiRestGeneratorExc)
		{
			Assert.fail(apiRestGeneratorExc.getMessage()) ;
		}
	}
}
