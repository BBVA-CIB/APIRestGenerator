package com.bbva.kltt.apirest.core.parsed_info.common;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemFactoryTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemFactory itemFactory = ItemFactoryTest.generateDummyItemFactory() ;
		
		itemFactory.createNewArray(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
		itemFactory.createNewItemComplex(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
		itemFactory.createNewItemFile(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
										ConstantsTest.ALIAS,
										ConstantsTest.DESCRIPTION,
										ConstantsTest.REQUIRED,
										ConstantsTest.TYPE,
										ConstantsTest.FORMAT) ;
		// is Array item
		itemFactory.isArrayItem(null) ;
		itemFactory.isArrayItem(ConstantsTest.TYPE) ;
		itemFactory.isArrayItem(ConstantsInput.JSON_TYPE_ARRAY) ;
		
		// is Complex item
		itemFactory.isComplexItem(null) ;
		itemFactory.isComplexItem(ConstantsInput.JSON_TYPE_OBJECT) ;
		itemFactory.isComplexItem(ConstantsTest.TYPE) ;
		
		// is File item
		itemFactory.isFileItem(null) ;
		itemFactory.isFileItem(ConstantsCommon.TYPE_FILE) ;
		itemFactory.isFileItem(ConstantsTest.TYPE) ;
		
		// is Simple item
		itemFactory.isSimpleItem(null) ;
		itemFactory.isSimpleItem(ConstantsInput.JSON_TYPE_STRING) ;
		itemFactory.isSimpleItem(ConstantsInput.JSON_TYPE_BOOLEAN) ;
		itemFactory.isSimpleItem(ConstantsInput.JSON_TYPE_INTEGER) ;
		itemFactory.isSimpleItem(ConstantsInput.JSON_TYPE_NUMBER) ;
		itemFactory.isSimpleItem(ConstantsTest.TYPE) ;
		
		// Create item type String
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			null,
							  			ConstantsTest.FORMAT) ;
		
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			ConstantsInput.JSON_TYPE_STRING,
							  			ConstantsTest.FORMAT) ;
		
		// Create item type Boolean
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			ConstantsInput.JSON_TYPE_BOOLEAN,
							  			ConstantsTest.FORMAT) ;
		// Create item type Integer
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			ConstantsInput.JSON_TYPE_INTEGER,
							  			null) ;
		
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			ConstantsInput.JSON_TYPE_INTEGER,
							  			ConstantsInput.JSON_FORMAT_INT32) ;
		// Create item type Long
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			ConstantsInput.JSON_TYPE_INTEGER,
							  			ConstantsInput.JSON_FORMAT_INT64) ;
		// Create item type Float
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			ConstantsInput.JSON_TYPE_NUMBER,
							  			null) ;
		
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			ConstantsInput.JSON_TYPE_NUMBER,
							  			ConstantsInput.JSON_FORMAT_FLOAT) ;
		// Create item type Double
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			ConstantsInput.JSON_TYPE_NUMBER,
							  			ConstantsInput.JSON_FORMAT_DOUBLE) ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidItemRef() throws APIRestGeneratorException
	{
		final ItemFactory itemFactory = ItemFactoryTest.generateDummyItemFactory() ;
		
		itemFactory.createNewItemRef(ConstantsTest.ITEM_NAME,
									 ConstantsTest.ALIAS,
									 ConstantsTest.DESCRIPTION,
									 ConstantsTest.REQUIRED,
									 ConstantsTest.REFERENCE_DEFINITIONS) ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidType() throws APIRestGeneratorException
	{
		final ItemFactory itemFactory = ItemFactoryTest.generateDummyItemFactory() ;
		
		itemFactory.createNewSimpleItem(ConstantsTest.ITEM_NAME,
							  			ConstantsTest.ALIAS,
							  			ConstantsTest.DESCRIPTION,
							  			ConstantsTest.REQUIRED,
							  			"invalidType",
							  			"invalidFormat") ;		
	}
	
	/**
	 * @return a dummy item factory
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemFactory generateDummyItemFactory() throws APIRestGeneratorException
	{
		final Map<String, Item> mapStringItem = new HashMap<String, Item>() ;
		
		final ItemFactory itemFactory = new ItemFactory(mapStringItem) ;
		return itemFactory ;
	}
}
