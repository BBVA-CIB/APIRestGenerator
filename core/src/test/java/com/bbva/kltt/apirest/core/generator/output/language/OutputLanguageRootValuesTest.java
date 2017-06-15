package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageRootValuesTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageRootValues outputLanguageRootValues = 
									   new OutputLanguageRootValues(ParsedInfoHandlerTest.generateDummyParsedInfoHandler(),
																	OutputLanguageNamingTest.generateDummyOutputLanguageNaming(),
																	OutputLanguageGeneratorParamsTest.generateDummyOutputLanguageGeneratorParams()) ;
		
		outputLanguageRootValues.getBasePath() ;
		outputLanguageRootValues.getClientBasePath() ;
		outputLanguageRootValues.getHost() ;
		outputLanguageRootValues.getOneSchemeExample(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageRootValues.getOnlyPort() ;
		outputLanguageRootValues.getSchemes(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
	}
}
