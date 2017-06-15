package com.bbva.kltt.apirest.core.parsed_info.paths;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.operations.OperationTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathOpOptionsTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		final PathOpOptions pathOpOptions = PathOpOptionsTest.generateDummyPathOpOptions() ;
		
		pathOpOptions.getPathOpType() ;
	}
	
	/**
	 * @return a dummy PathOpOptions
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static PathOpOptions generateDummyPathOpOptions() throws APIRestGeneratorException
	{
		final PathOpOptions pathOpOptions = new PathOpOptions() ;
		
		pathOpOptions.setOperation(OperationTest.generateDummyOperation()) ;
		
		return pathOpOptions ;
	}
}
