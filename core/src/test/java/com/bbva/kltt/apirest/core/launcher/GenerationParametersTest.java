package com.bbva.kltt.apirest.core.launcher;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GenerationParametersTest
{
	@Test
	public void fullTest()
	{
		final GenerationParameters generationParameters = GenerationParametersTest.generateDummyGenerationParameters() ;
		
		generationParameters.getCodeGenOutputDirectory() ;
		generationParameters.getParserType() ;
		generationParameters.getSpecificationFilePath() ;
	}

	/**
	 * @return a dummy generation parameters
	 */
	public static GenerationParameters generateDummyGenerationParameters()
	{
		final GenerationParameters generationParameters = new GenerationParameters() ;
		
		generationParameters.setCodeGenOutputDirectory(ConstantsTest.FULL_TEMPORARY_DIR) ;
		generationParameters.setParserType(ConstantsTest.PARSER_TYPE) ;
		generationParameters.setSpecificationFilePath(ConstantsTest.SPECIF_FILE_PATH) ;
		
		return generationParameters ;
	}
}
