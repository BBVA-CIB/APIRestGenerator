package com.bbva.kltt.apirest.core.parsed_info.paths;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.DefinitionsTest;
import com.bbva.kltt.apirest.core.parsed_info.ResponsesTest;
import com.bbva.kltt.apirest.core.parsed_info.RootValuesTest;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersGlobalTest;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathsTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		final Paths paths = PathsTest.generateDummyPaths() ;
		
		paths.generateParameterBody(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.generateParametersFormDataMap(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.generateParametersHeaderMap(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.generateParametersPathMap(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.generateParametersQueryMap(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		paths.getAllOperationIds() ;
		
		paths.getConsumes(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		paths.getGlobalDefinitions() ;
		paths.getGlobalResponses() ;
		
		paths.getOperationId(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		paths.getOutboundServerExceptionsList(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.getOutboundServerExceptionsMap(new HashMap<String, List<Response>>());
		
		paths.getOutboundServerItemType(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		paths.getParametersGlobal() ;
		
		paths.getPathsMap() ;
		
		paths.getProduces(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		paths.getRootValues() ;
		
		paths.getSchemes(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		paths.hasAnyScheme(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.hasAnyParameterBody(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.hasAnyParameterFormData(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.hasAnyParameterHeader(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.hasAnyParameterPath(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.hasAnyParameterQuery(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.hasAnyOperationId(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.hasAnyOperationConsumes(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		paths.hasAnyOperationProduces(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
	}
	
	/**
	 * @return a dummy Paths
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static Paths generateDummyPaths() throws APIRestGeneratorException
	{
		final Paths paths = new Paths(RootValuesTest.generateDummyRootValues(),
									  DefinitionsTest.generateDummyDefinitions(),
									  ParametersGlobalTest.generateDummyParametersGlobal(), 
									  ResponsesTest.generateDummyResponses()) ;
		
		paths.getPathsMap().put(ConstantsTest.PATH_VALUE, PathTest.generateDummyPath()) ;
		
		return paths ;
	}
}
