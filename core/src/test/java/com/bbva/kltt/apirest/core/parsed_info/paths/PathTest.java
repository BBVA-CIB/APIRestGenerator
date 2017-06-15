package com.bbva.kltt.apirest.core.parsed_info.paths;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		final Path path = PathTest.generateDummyPath() ;
		
		path.generateParameterBody(ConstantsTest.PATH_OPERATION) ;
		path.generateParametersFormDataMap(ConstantsTest.PATH_OPERATION) ;
		path.generateParametersHeaderMap(ConstantsTest.PATH_OPERATION) ;
		path.generateParametersPathMap(ConstantsTest.PATH_OPERATION) ;
		path.generateParametersQueryMap(ConstantsTest.PATH_OPERATION) ;
		
		path.generatePathValuesList() ;
		
		path.getAllOperationIds() ;
		
		path.getConsumes(ConstantsTest.PATH_OPERATION) ;
		path.getOperationId(ConstantsTest.PATH_OPERATION) ;
		
		path.getOutboundServerExceptionsList(ConstantsTest.PATH_OPERATION) ;
		path.getOutboundServerExceptionsMap(new HashMap<String, List<Response>>()) ;
		
		path.getOutboundServerItemType(ConstantsTest.PATH_OPERATION) ;
		
		path.getParametersPath() ;
		
		path.getPathOpDelete() ;
		path.getPathOpGet() ;
		path.getPathOpHead() ;
		path.getPathOpOptions() ;
		path.getPathOpPatch() ;
		path.getPathOpPost() ;
		path.getPathOpPut() ;
		
		path.getPathValue() ;
		
		path.getProduces(ConstantsTest.PATH_OPERATION) ;
		path.getSchemes(ConstantsTest.PATH_OPERATION) ;
		
		path.hasParametersPath() ;
		
		path.hasAnyOperationConsumes(ConstantsTest.PATH_OPERATION) ;
		path.hasAnyOperationId(ConstantsTest.PATH_OPERATION) ;
		path.hasAnyOperationProduces(ConstantsTest.PATH_OPERATION) ;
		
		path.hasAnyParameterBody(ConstantsTest.PATH_OPERATION) ;
		path.hasAnyParameterFormData(ConstantsTest.PATH_OPERATION) ;
		path.hasAnyParameterHeader(ConstantsTest.PATH_OPERATION) ;
		path.hasAnyParameterPath(ConstantsTest.PATH_OPERATION) ;
		path.hasAnyParameterQuery(ConstantsTest.PATH_OPERATION) ;
		
		path.hasAnyScheme(ConstantsTest.PATH_OPERATION) ;
	}
	
	/**
	 * @return a dummy Path
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static Path generateDummyPath() throws APIRestGeneratorException
	{
		return new Path(ConstantsTest.PATH_VALUE) ;
	}
}
