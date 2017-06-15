package com.bbva.kltt.apirest.core.parsed_info.paths;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.operations.OperationTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathOpPostTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		final PathOpPost pathOpPost = PathOpPostTest.generateDummyPathOpPost() ;
		
		pathOpPost.getPathOpType() ;
	}
	
	/**
	 * @return a dummy PathOpPost
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static PathOpPost generateDummyPathOpPost() throws APIRestGeneratorException
	{
		final PathOpPost pathOpPost = new PathOpPost() ;
		
		pathOpPost.setOperation(OperationTest.generateDummyOperation()) ;
		
		return pathOpPost ;
	}
}
