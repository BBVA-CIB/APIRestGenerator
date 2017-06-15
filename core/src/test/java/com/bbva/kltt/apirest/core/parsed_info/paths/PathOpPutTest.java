package com.bbva.kltt.apirest.core.parsed_info.paths;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.operations.OperationTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathOpPutTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		final PathOpPut pathOpPut = PathOpPutTest.generateDummyPathOpPut() ;
		
		pathOpPut.getPathOpType() ;
	}
	
	/**
	 * @return a dummy PathOpPut
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static PathOpPut generateDummyPathOpPut() throws APIRestGeneratorException
	{
		final PathOpPut pathOpPut = new PathOpPut() ;
		
		pathOpPut.setOperation(OperationTest.generateDummyOperation()) ;
		
		return pathOpPut ;
	}
}
