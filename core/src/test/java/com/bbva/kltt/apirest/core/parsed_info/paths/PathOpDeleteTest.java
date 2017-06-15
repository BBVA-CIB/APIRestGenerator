package com.bbva.kltt.apirest.core.parsed_info.paths;

import java.util.HashMap;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.operations.OperationTest;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathOpDeleteTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		final PathOpDelete pathOpDelete = PathOpDeleteTest.generateDummyPathOpDelete() ;
		
		pathOpDelete.getPathOpType() ;
		pathOpDelete.getOperation() ;
		pathOpDelete.isEmpty() ;
		pathOpDelete.hasAnyScheme() ;
		pathOpDelete.hasAnyParameterBody() ;
		pathOpDelete.hasAnyParameterFormData() ;
		pathOpDelete.hasAnyParameterHeader() ;
		pathOpDelete.hasAnyParameterPath() ;
		pathOpDelete.hasAnyParameterQuery() ;
		pathOpDelete.getSchemes() ;
		pathOpDelete.generateParametersFormDataMap(new HashMap<String, Parameter>()) ;
		pathOpDelete.generateParametersHeaderMap(new HashMap<String, Parameter>()) ;
		pathOpDelete.generateParametersPathMap(new HashMap<String, Parameter>()) ;
		pathOpDelete.generateParametersQueryMap(new HashMap<String, Parameter>()) ;
		pathOpDelete.getOutboundServerItemType() ;
		pathOpDelete.hasAnyOperationConsumes() ;
		pathOpDelete.getConsumes() ;
		pathOpDelete.hasAnyOperationProduces() ;
		pathOpDelete.getProduces() ;
		pathOpDelete.hasAnyResponseExceptions() ;
		pathOpDelete.getResponseExceptions() ;
		pathOpDelete.getOutboundServerExceptionsList() ;
	}
	
	/**
	 * @return a dummy PathOpDelete
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static PathOpDelete generateDummyPathOpDelete() throws APIRestGeneratorException
	{
		final PathOpDelete pathOpDelete = new PathOpDelete() ;
		
		pathOpDelete.setOperation(OperationTest.generateDummyOperation()) ;
		
		return pathOpDelete ;
	}
}
