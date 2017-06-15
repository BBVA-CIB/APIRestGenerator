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
public class TranslatorGeneratorBaseTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final TranslatorGeneratorBase translatorGeneratorBase = new MyTranslatorGeneratorBase(ConstantsTest.TRANSLATOR_TYPE,
																							  GenerationParametersTest.generateDummyGenerationParameters(),
																							  ParsedInfoHandlerTest.generateDummyParsedInfoHandler(),
																							  ConstantsTest.GENERATION_PACKAGE) ;
		translatorGeneratorBase.getGenerationPackage() ;
		translatorGeneratorBase.getGenerationParams() ;
		translatorGeneratorBase.getGeneratorInfoProjectsChildren() ;
		translatorGeneratorBase.getParsedInfoHandler() ;
		translatorGeneratorBase.getResourceDir() ;
		translatorGeneratorBase.getSourceDir() ;
		translatorGeneratorBase.getTranslatorType() ;
	}
}
