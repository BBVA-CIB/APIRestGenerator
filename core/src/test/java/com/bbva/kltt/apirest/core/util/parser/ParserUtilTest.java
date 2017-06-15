package com.bbva.kltt.apirest.core.util.parser;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParserUtilTest
{
	@Test
	public void invokeConstructor() throws Exception 
	{
		Whitebox.invokeConstructor(ParserUtil.class, new Object[0]);
		Assert.assertTrue(true);
	}
	
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		ParserUtil.deserializeIntoJson(ConstantsTest.SRC_TEST_RESOURCES_PATH + File.separator + "footballPlayers.yaml") ;
		ParserUtil.deserializeIntoJson(ConstantsTest.SRC_TEST_RESOURCES_PATH + File.separator + "footballPlayers.json") ;
		ParserUtil.generateSetStringFromString(new ArrayNode(new JsonNodeFactory(true))) ;
		ParserUtil.generateToStringFromSetString(new HashSet<String>(Arrays.asList("one", "two", "three"))) ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void generateExceptionRequiredField() throws APIRestGeneratorException
	{
		ParserUtil.generateExceptionRequiredField(ConstantsTest.NODE_NAME, ConstantsTest.FIELD_NAME) ;
	}
}
