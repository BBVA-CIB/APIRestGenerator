package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemSimpleIntegerTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemSimpleInteger itemSimpleInteger = ItemSimpleIntegerTest.generateDummyItemSimpleInteger() ;
		
		itemSimpleInteger.isRequired() ;
		itemSimpleInteger.getAlias() ;
		itemSimpleInteger.getClassName() ;
		itemSimpleInteger.getDescription() ;
		itemSimpleInteger.getName() ;
		itemSimpleInteger.getType() ;
		
		itemSimpleInteger.setRequired(true) ;
	}
	
	/**
	 * @return a dummy item simple integer
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemSimpleInteger generateDummyItemSimpleInteger() throws APIRestGeneratorException
	{
		return new ItemSimpleInteger(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
}
