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
public class ParametersOperationTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ParametersOperation parametersOperation = ParametersOperationTest.generateDummyParametersOperation() ;

		parametersOperation.addParameter(ParameterQueryTest.generateDummyParameterQuery()) ;
		
		parametersOperation.getConsumes() ;
		parametersOperation.getItemFactory() ;
		parametersOperation.getParameterBody() ;
		
		parametersOperation.getParameterFactory() ;
		
		parametersOperation.getParameterFormData(ConstantsTest.ITEM_NAME) ;
		parametersOperation.getParameterHeader(ConstantsTest.ITEM_NAME) ;
		parametersOperation.getParameterPath(ConstantsTest.ITEM_NAME) ;
		parametersOperation.getParameterQuery(ConstantsTest.ITEM_NAME) ;
		
//		parametersOperation.getReferenceParameter(ItemRefTest.generateDummyItemRef()) ;
//		parametersOperation.getReferenceParameter(ConstantsTest.REFERENCE_PARAMETER) ;
		
		parametersOperation.generateParametersFormDataMap() ;
		parametersOperation.generateParametersHeaderMap() ;
		parametersOperation.generateParametersPathMap() ;
		parametersOperation.generateParametersQueryMap() ;
		
		parametersOperation.hasAnyParameterBody() ;
		parametersOperation.hasAnyParameterFormData() ;
		parametersOperation.hasAnyParameterHeader() ;
		parametersOperation.hasAnyParameterPath() ;
		parametersOperation.hasAnyParameterQuery() ;
	}
	
	/**
	 * @return a dummy parameters operation
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ParametersOperation generateDummyParametersOperation() throws APIRestGeneratorException
	{
		return new ParametersOperation(DefinitionsTest.generateDummyDefinitions(), ConstantsTest.CONSUMES_APP_JSON) ;
	}
}
