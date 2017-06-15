package com.bbva.kltt.apirest.core.parsed_info.operations;

import java.util.HashMap;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ResponsesTest;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersOperationTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OperationTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final Operation operation = OperationTest.generateDummyOperation() ;

		operation.generateParametersFormDataMap(new HashMap<String, Parameter>()) ;
		operation.generateParametersHeaderMap(new HashMap<String, Parameter>()) ;
		operation.generateParametersPathMap(new HashMap<String, Parameter>()) ;
		operation.generateParametersQueryMap(new HashMap<String, Parameter>()) ;
		
		operation.getConsumes() ;
		operation.getDescription() ;
		operation.getOperationId() ;
		operation.getOutboundServerItemType() ;
		operation.getParametersOperation() ;
		operation.getProduces() ;
		operation.getResponses() ;
		operation.getSchemes() ;
		
		operation.hasAnyParameterBody() ;
		operation.hasAnyParameterFormData() ;
		operation.hasAnyParameterHeader() ;
		operation.hasAnyParameterPath() ;
		operation.hasAnyParameterQuery() ;
		operation.hasAnyScheme() ;
		
		operation.isDeprecated() ;
		
		operation.setConsumes(ConstantsTest.CONSUMES_APP_JSON) ;
		operation.setDeprecated(ConstantsTest.DEPRECATED) ;
		operation.setDescription(ConstantsTest.DESCRIPTION) ;
		operation.setOperationId(ConstantsTest.OPERATION_ID) ;
		operation.setProduces(ConstantsTest.PRODUCES_APP_JSON) ;
		operation.setSchemes(ConstantsTest.SCHEMES) ;
	}
	
	/**
	 * @return a dummy Operation
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static Operation generateDummyOperation() throws APIRestGeneratorException
	{
		final Operation operation = new Operation(ResponsesTest.generateDummyResponses()) ;
		
		operation.setParametersOperation(ParametersOperationTest.generateDummyParametersOperation()) ;
		
		return operation ;
	}
}
