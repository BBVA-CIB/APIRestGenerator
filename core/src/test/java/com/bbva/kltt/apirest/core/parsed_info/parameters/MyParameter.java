package com.bbva.kltt.apirest.core.parsed_info.parameters;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyParameter extends Parameter
{
	/**
	 * Public constructor
	 * @param name 		   with the name
	 * @param alias		   with the alias name
	 * @param description  with the description
	 * @param required	   true if the item is required
	 * @param type		   with the type
	 * @param autoInjected true if the item is auto injected
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public MyParameter(final String name, final String alias, final String description, final String required, final String type, final String autoInjected)
		   throws APIRestGeneratorException
	{
		super(name, alias, description, required, type, autoInjected) ;
	}

	@Override
	public void validate() throws APIRestGeneratorException
	{
		// Nothing to do
	}

	@Override
	public String getClassName()
	{
		return ConstantsTest.CLASS_NAME ;
	}
}
