package com.bbva.kltt.apirest.core.generator;

import org.junit.Test;

import com.bbva.kltt.apirest.core.launcher.GenerationParametersTest;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorGlobalLanguageTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final GeneratorGlobalLanguage generatorGlobalLanguage = GeneratorGlobalLanguageTest.generateDummyGeneratorGlobalLanguage() ;
		
		generatorGlobalLanguage.getGenerationPackage() ;
		generatorGlobalLanguage.getGenerationParams() ;
		generatorGlobalLanguage.getParsedInfoHandler() ;
		generatorGlobalLanguage.generate(ConstantsTest.TRANSLATOR_TYPE) ;
	}
	
	/**
	 * @return a dummy Generator global language
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static GeneratorGlobalLanguage generateDummyGeneratorGlobalLanguage() throws APIRestGeneratorException
	{
		// New instance of GeneratorGlobalLanguage
		final GeneratorGlobalLanguage generatorGlobalLanguage = new MyGeneratorGlobalLanguage(GenerationParametersTest.generateDummyGenerationParameters(),
																				  			  ParsedInfoHandlerTest.generateDummyParsedInfoHandler(),
																				  			  ConstantsTest.GENERATION_PACKAGE) ;
		// Return the new instance of GeneratorGlobalLanguage
		return generatorGlobalLanguage ;
	}
}
