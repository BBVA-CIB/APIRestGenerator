package com.bbva.kltt.apirest.core.parsed_info.parameters;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.DefinitionsTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParametersGlobalTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ParametersGlobal parametersGlobal = ParametersGlobalTest.generateDummyParametersGlobal() ;

		parametersGlobal.addParameter(ConstantsTest.ITEM_NAME, ParameterQueryTest.generateDummyParameterQuery()) ;
		parametersGlobal.addParameterBody(ParameterBodyTest.generateDummyParameterBody()) ;
		
		parametersGlobal.getConsumes() ;
		parametersGlobal.getItemFactory() ;
		parametersGlobal.getParameterBody() ;
		
		parametersGlobal.getParameterFormData(ConstantsTest.ITEM_NAME) ;
		parametersGlobal.getParameterHeader(ConstantsTest.ITEM_NAME) ;
		parametersGlobal.getParameterPath(ConstantsTest.ITEM_NAME) ;
		parametersGlobal.getParameterQuery(ConstantsTest.ITEM_NAME) ;
		
		parametersGlobal.hasAnyParameterBody() ;
		parametersGlobal.hasAnyParameterFormData() ;
		parametersGlobal.hasAnyParameterHeader() ;
		parametersGlobal.hasAnyParameterPath() ;
		parametersGlobal.hasAnyParameterQuery() ;
	}
	
	/**
	 * @return a dummy parameters global
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ParametersGlobal generateDummyParametersGlobal() throws APIRestGeneratorException
	{
		return new ParametersGlobal(DefinitionsTest.generateDummyDefinitions(), ConstantsTest.CONSUMES_APP_JSON) ;
	}
}
