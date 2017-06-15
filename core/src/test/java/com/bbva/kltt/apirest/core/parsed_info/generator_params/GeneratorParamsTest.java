package com.bbva.kltt.apirest.core.parsed_info.generator_params;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorParamsTest
{
	@Test
	public void fullTest()
	{
		final GeneratorParams generatorParams = GeneratorParamsTest.generateDummyGeneratorParams() ;
		
		generatorParams.getBUnit() ;
		
		generatorParams.getOsgiParams().getArchitectureType() ;
		generatorParams.getOsgiParams().getCxfAddress() ;
		generatorParams.getOsgiParams().getCxfContext() ;
	}
	
	/**
	 * @return a dummy generator parameters
	 */
	public static GeneratorParams generateDummyGeneratorParams()
	{
		// New instance of GeneratorParams
		final GeneratorParams generatorParams = new GeneratorParams() ;
		
		generatorParams.setBUnit(ConstantsTest.BUSINESS_UNIT) ;
		generatorParams.addOsgiParams(ConstantsTest.ARCHITECTURE_TYPE, ConstantsTest.CXF_ADDRESS, ConstantsTest.CXF_CONTEXT) ;
		
		return generatorParams ;
	}
}
