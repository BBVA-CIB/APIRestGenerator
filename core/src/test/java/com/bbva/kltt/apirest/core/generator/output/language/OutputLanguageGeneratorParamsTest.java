package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageGeneratorParamsTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageGeneratorParams outputLanguageGeneratorParams = OutputLanguageGeneratorParamsTest.generateDummyOutputLanguageGeneratorParams() ;
		
		outputLanguageGeneratorParams.getBUnit() ;
		outputLanguageGeneratorParams.getBUnitUpperCase() ;
		outputLanguageGeneratorParams.getOsgiArchitectureType() ;
		outputLanguageGeneratorParams.getOsgiCxfAddress() ;
		outputLanguageGeneratorParams.getOsgiCxfContext() ;
	}
	
	/**
	 * @return a new instance of OutputLanguageGeneratorParams
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static OutputLanguageGeneratorParams generateDummyOutputLanguageGeneratorParams() throws APIRestGeneratorException
	{
		return new OutputLanguageGeneratorParams(ParsedInfoHandlerTest.generateDummyParsedInfoHandler()) ;
	}
}
