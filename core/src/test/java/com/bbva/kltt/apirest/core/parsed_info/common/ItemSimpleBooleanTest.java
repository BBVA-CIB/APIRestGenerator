package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemSimpleBooleanTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemSimpleBoolean itemSimpleBoolean = ItemSimpleBooleanTest.generateDummyItemSimpleBoolean() ;
		
		itemSimpleBoolean.isRequired() ;
		itemSimpleBoolean.getAlias() ;
		itemSimpleBoolean.getClassName() ;
		itemSimpleBoolean.getDescription() ;
		itemSimpleBoolean.getName() ;
		itemSimpleBoolean.getType() ;
		
		itemSimpleBoolean.setRequired(true) ;
	}
	
	/**
	 * @return a dummy item simple boolean
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemSimpleBoolean generateDummyItemSimpleBoolean() throws APIRestGeneratorException
	{
		return new ItemSimpleBoolean(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
}
