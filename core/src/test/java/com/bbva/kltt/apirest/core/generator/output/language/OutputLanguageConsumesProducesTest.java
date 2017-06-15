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
public class OutputLanguageConsumesProducesTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageConsumesProduces outputLanguageConsumesProduces = new MyOutputLanguageConsumesProduces(ParsedInfoHandlerTest.generateDummyParsedInfoHandler(),
									   				 							  								   new MyOutputLanguageSeparators()) ;
		outputLanguageConsumesProduces.generateFinalConsumesOrProducesString() ;
		outputLanguageConsumesProduces.generateInboundServerContentType() ;
		outputLanguageConsumesProduces.generateInboundServerContentType(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		outputLanguageConsumesProduces.generateInitialConsumesOrProducesString() ;
		outputLanguageConsumesProduces.generateOutboundServerContentType() ;
		outputLanguageConsumesProduces.generateOutboundServerContentType(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		outputLanguageConsumesProduces.getParsedInfoHandler() ;
	}
}
