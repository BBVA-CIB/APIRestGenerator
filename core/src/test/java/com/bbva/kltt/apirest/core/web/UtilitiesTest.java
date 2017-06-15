package com.bbva.kltt.apirest.core.web;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class UtilitiesTest
{
	@Test
	public void invokeConstructor() throws Exception 
	{
		Whitebox.invokeConstructor(Utilities.class, new Object[0]) ;
		Assert.assertTrue(true) ;
	}
	
	@Test
	public void fullTest() throws APIRestGeneratorException 
	{
		Utilities.readFileContent(ConstantsTest.SRC_TEST_RESOURCES_PATH + File.separator + ConstantsTest.ANT_FILE) ;
	}
	
	@Test
	public void invalidFileReadContent() throws APIRestGeneratorException 
	{
		Utilities.readFileContent(ConstantsInput.SO_PATH_STRING_PREFIX + "/tmp/invalidFile") ;
	}
}
