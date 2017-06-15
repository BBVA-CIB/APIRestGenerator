package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final Item item = ItemTest.generateDummyItem() ;
		
		item.isRequired() ;
		item.getAlias() ;
		item.getClassName() ;
		item.getDescription() ;
		item.getName() ;
		item.getType() ;
		
		item.setRequired(true) ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidNameFormat() throws APIRestGeneratorException
	{
		new MyItem("$invalidName%", ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidNameReservedWord() throws APIRestGeneratorException
	{
		new MyItem("class", ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
	
	/**
	 * @return a dummy item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static Item generateDummyItem() throws APIRestGeneratorException
	{
		return new MyItem(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
}
