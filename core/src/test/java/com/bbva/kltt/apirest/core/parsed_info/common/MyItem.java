package com.bbva.kltt.apirest.core.parsed_info.common;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyItem extends Item
{
	/**
	 * Public constructor
	 * @param name 		  with the name
	 * @param alias		  with the alias name
	 * @param description with the description
	 * @param required	  true if the item is required
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public MyItem(final String name, final String alias, final String description, final String required) throws APIRestGeneratorException
	{
		super(name, alias, description, required) ;
	}

	@Override
	public String getType()
	{
		return ConstantsTest.TYPE ;
	}

	@Override
	public String getClassName()
	{
		return ConstantsTest.CLASS_NAME ;
	}
}
