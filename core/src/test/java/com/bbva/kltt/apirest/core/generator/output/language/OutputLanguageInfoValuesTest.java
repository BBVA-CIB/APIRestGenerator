package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageInfoValuesTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageInfoValues outputLanguageInfoValues = new OutputLanguageInfoValues(ParsedInfoHandlerTest.generateDummyParsedInfoHandler()) ;
		
		outputLanguageInfoValues.getContactEmail() ;
		outputLanguageInfoValues.getContactName() ;
		outputLanguageInfoValues.getContactUrl() ;
		outputLanguageInfoValues.getDescription() ;
		outputLanguageInfoValues.getTitle() ;
		outputLanguageInfoValues.getVersion() ;
	}
}
