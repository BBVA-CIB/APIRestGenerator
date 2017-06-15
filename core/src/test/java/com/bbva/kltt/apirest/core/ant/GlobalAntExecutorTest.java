package com.bbva.kltt.apirest.core.ant;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GlobalAntExecutorTest
{
	@Test(expected=APIRestGeneratorException.class)
	public void launchAntExecutorTest() throws APIRestGeneratorException
	{
		final GlobalAntExecutor globalAntExecutor = new GlobalAntExecutor(ConstantsTest.PARSER_TYPE, ConstantsTest.TRANSLATOR_TYPE) ;
		globalAntExecutor.launchAntExecutor(ConstantsTest.GENERATOR_BUILDER_PATH,
											ConstantsTest.SPECIF_FILE_PATH,
											"deliverableFileName",
											ConstantsTest.TEMPORARY_DIR,
											System.err,
											System.out) ;
	}
	
	@Test
	public void generateFullTemporaryDirTest() throws APIRestGeneratorException
	{
		final GlobalAntExecutor globalAntExecutor = new GlobalAntExecutor(ConstantsTest.PARSER_TYPE, ConstantsTest.TRANSLATOR_TYPE) ;
		globalAntExecutor.generateFullTemporaryDir(ConstantsTest.GENERATOR_BUILDER_PATH, ConstantsTest.TEMPORARY_DIR) ;
	}
	
	@Test
	public void generateTemporaryOutputDirectoryTest() throws APIRestGeneratorException
	{
		final GlobalAntExecutor globalAntExecutor = new GlobalAntExecutor(ConstantsTest.PARSER_TYPE, ConstantsTest.TRANSLATOR_TYPE) ;
		globalAntExecutor.generateTemporaryOutputDirectory(ConstantsTest.GENERATOR_BUILDER_PATH) ;
	}
	
	@Test
	public void getDeliverableFileNameTest() throws APIRestGeneratorException
	{
		final GlobalAntExecutor globalAntExecutor = new GlobalAntExecutor(ConstantsTest.PARSER_TYPE, ConstantsTest.TRANSLATOR_TYPE) ;
		globalAntExecutor.getDeliverableFileName(ConstantsTest.SPECIF_FILE_PATH, ConstantsTest.TRANSLATOR_TYPE) ;
	}
	
	@Test
	public void removeFullTemporaryDirectoryTest() throws APIRestGeneratorException
	{
		final GlobalAntExecutor globalAntExecutor = new GlobalAntExecutor(ConstantsTest.PARSER_TYPE, ConstantsTest.TRANSLATOR_TYPE) ;
		globalAntExecutor.removeFullTemporaryDirectory(ConstantsTest.FULL_TEMPORARY_DIR) ;
	}
	
	@Test
	public void validateFileExtensionTest() throws APIRestGeneratorException
	{
		final GlobalAntExecutor globalAntExecutor = new GlobalAntExecutor(ConstantsTest.PARSER_TYPE, ConstantsTest.TRANSLATOR_TYPE) ;
		globalAntExecutor.validateFileExtension(ConstantsTest.SPECIF_FILE_PATH) ;
	}
}
