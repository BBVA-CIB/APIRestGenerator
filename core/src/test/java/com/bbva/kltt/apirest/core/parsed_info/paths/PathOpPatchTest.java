package com.bbva.kltt.apirest.core.parsed_info.paths;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.operations.OperationTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathOpPatchTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		final PathOpPatch pathOpPatch = PathOpPatchTest.generateDummyPathOpPatch() ;
		
		pathOpPatch.getPathOpType() ;
	}
	
	/**
	 * @return a dummy PathOpPatch
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static PathOpPatch generateDummyPathOpPatch() throws APIRestGeneratorException
	{
		final PathOpPatch pathOpPatch = new PathOpPatch() ;
		
		pathOpPatch.setOperation(OperationTest.generateDummyOperation()) ;
		
		return pathOpPatch ;
	}
}
