package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageBaseTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageBase outputLanguageBase = new MyOutputLanguageBase(ParsedInfoHandlerTest.generateDummyParsedInfoHandler()) ;
		
		outputLanguageBase.getParsedInfoHandler() ;
	}
}
