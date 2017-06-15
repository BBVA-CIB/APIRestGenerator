package com.bbva.kltt.apirest.core.parsed_info ;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.generator_params.GeneratorParamsTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParsedInfoTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ParsedInfo parsedInfo = ParsedInfoTest.generateDummyParsedInfo() ;
		
		parsedInfo.getDefinitions() ;
		parsedInfo.getGeneratorParams() ;
		parsedInfo.getInfoValues() ;
		parsedInfo.getParametersGlobal() ;
		parsedInfo.getPaths() ;
		parsedInfo.getResponses() ;
		parsedInfo.getRootValues() ;
		
		parsedInfo.toString() ;
	}
	
	/**
	 * @return a dummy parsed info
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ParsedInfo generateDummyParsedInfo() throws APIRestGeneratorException
	{
		final ParsedInfo parsedInfo = new ParsedInfo() ;
		
		parsedInfo.setRootValues(RootValuesTest.generateDummyRootValues()) ;
		parsedInfo.setInfoValues(InfoValuesTest.generateDummyInfoValues()) ;
		parsedInfo.setGeneratorParams(GeneratorParamsTest.generateDummyGeneratorParams()) ;
		
		parsedInfo.setDefinitions(DefinitionsTest.generateDummyDefinitions()) ;
		parsedInfo.setResponses(ResponsesTest.generateDummyResponses()) ;
		
		return parsedInfo ;
	}

}
