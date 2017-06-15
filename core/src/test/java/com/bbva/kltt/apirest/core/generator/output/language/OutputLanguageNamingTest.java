package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageNamingTest
{
	/** Constant - Attribute name */
	private static final String ATTRIBUTE_NAME = "atributeName" ;
	
	/** Constant - One character */
	private static final String ONE_CHARACTER  = "A" ;
	
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageNaming outputLanguageNaming = OutputLanguageNamingTest.generateDummyOutputLanguageNaming() ;
		
		outputLanguageNaming.prefixClassName(ATTRIBUTE_NAME) ;
		outputLanguageNaming.getCamelCaseName(ATTRIBUTE_NAME) ;
		outputLanguageNaming.getUrlWithSlashes("url", "/with/", "slashes") ;
		outputLanguageNaming.prefixAttributeName(ATTRIBUTE_NAME) ;
		outputLanguageNaming.suffixMethodName(ATTRIBUTE_NAME) ;
		
		outputLanguageNaming.prefixAttributeName(ONE_CHARACTER) ;
		outputLanguageNaming.suffixMethodName(ONE_CHARACTER) ;
	}
	
	/**
	 * @return a new instance of OutputLanguageNaming
	 */
	public static OutputLanguageNaming generateDummyOutputLanguageNaming()
	{
		return new OutputLanguageNaming() ;
	}
}
