package com.bbva.kltt.apirest.core.generator.output.parameters;

import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyOutputParameter extends OutputParameter
{
	/**
	 * Public constructor
	 * @param outputLangSeparators with the output language separators
	 * @param parametersList 	   with the parameters list
	 */
	public MyOutputParameter(Item item, String description, String type, String name)
	{
		super(item, description, type, name) ;
	}

	@Override
	public String getOutputTypeName()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getParameterHeader()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getParameterCall()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getParameterHeaderRest()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getParameterTest()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}
}
