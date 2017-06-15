package com.bbva.kltt.apirest.core.util;

import org.junit.Test;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PackageUtilsTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final PackageUtils packageUtils = new MyPackageUtils(ConstantsTest.BUSINESS_UNIT, ConstantsTest.PROJECT_TITLE) ;
		
		packageUtils.getExamplesPackage(ConstantsTest.TRANSLATOR_TYPE) ;
		packageUtils.getHandlersPackage(ConstantsTest.TRANSLATOR_TYPE) ;
		packageUtils.getModelsExceptionPackage() ;
		packageUtils.getModelsPackage() ;
		
		packageUtils.getProjectTitle() ;
		packageUtils.getBasePackageRoot() ;
		packageUtils.getBusinessUnit() ;
	}
}
