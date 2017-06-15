package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemArrayTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemArray itemArray = ItemArrayTest.generateDummyItemArray() ;
		
		itemArray.calculateBaseType() ;
		
		itemArray.isRequired() ;
		itemArray.getAlias() ;
		itemArray.getClassName() ;
		itemArray.getDescription() ;
		itemArray.getName() ;
		itemArray.getType() ;
		
		itemArray.setRequired(true) ;
	}
	
	/**
	 * @return a dummy item array
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemArray generateDummyItemArray() throws APIRestGeneratorException
	{
		final ItemArray itemArray = new ItemArray(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
		
		itemArray.setItemArray(ItemSimpleStringTest.generateDummyItemSimpleString()) ;
		
		return itemArray ;
	}
}
