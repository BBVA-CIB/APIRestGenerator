package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguagePathsTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguagePaths outputLanguagePaths = new OutputLanguagePaths(ParsedInfoHandlerTest.generateDummyParsedInfoHandler()) ;
		
		outputLanguagePaths.getPathValues() ;
	}
}
