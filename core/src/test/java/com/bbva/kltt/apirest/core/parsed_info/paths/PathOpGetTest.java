package com.bbva.kltt.apirest.core.parsed_info.paths;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.operations.OperationTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathOpGetTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		final PathOpGet pathOpGet = PathOpGetTest.generateDummyPathOpGet() ;
		
		pathOpGet.getPathOpType() ;
	}
	
	/**
	 * @return a dummy PathOpGet
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static PathOpGet generateDummyPathOpGet() throws APIRestGeneratorException
	{
		final PathOpGet pathOpGet = new PathOpGet() ;
		
		pathOpGet.setOperation(OperationTest.generateDummyOperation()) ;
		
		return pathOpGet ;
	}
}
