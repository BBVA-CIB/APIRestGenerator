package com.bbva.kltt.apirest.core.util;

import org.junit.Test;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class AbstractDependencyUtilityTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final AbstractDependencyUtility abstractDependencyUtility = new MyDependencyUtility("moduleType", "displayName", "moduleName") ;
		
		abstractDependencyUtility.getDisplayName() ;
		abstractDependencyUtility.getModuleName() ;
		abstractDependencyUtility.getTypeModule() ;
		
		abstractDependencyUtility.toString() ;
		
		AbstractDependencyUtility.getDependencies("packageName") ;
		
	}
}
