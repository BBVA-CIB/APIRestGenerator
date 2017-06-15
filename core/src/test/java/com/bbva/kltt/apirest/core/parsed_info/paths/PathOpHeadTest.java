package com.bbva.kltt.apirest.core.parsed_info.paths;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.operations.OperationTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathOpHeadTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		final PathOpHead pathOpHead = PathOpHeadTest.generateDummyPathOpHead() ;
		
		pathOpHead.getPathOpType() ;
	}
	
	/**
	 * @return a dummy PathOpHead
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static PathOpHead generateDummyPathOpHead() throws APIRestGeneratorException
	{
		final PathOpHead pathOpHead = new PathOpHead() ;
		
		pathOpHead.setOperation(OperationTest.generateDummyOperation()) ;
		
		return pathOpHead ;
	}
}
