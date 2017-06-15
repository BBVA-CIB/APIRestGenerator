package com.bbva.kltt.apirest.core.generator.output.language;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyOutputLanguageItems extends OutputLanguageItems
{
	/**
	 * Public constructor
	 * @param parsedInfoHandler with the parsed information handler
	 */
	public MyOutputLanguageItems(final ParsedInfoHandler parsedInfoHandler)
	{
		super(parsedInfoHandler) ;
	}

	@Override
	public String typeOutputArray()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputSimpleBoolean(final boolean isArrayBaseType)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputSimpleInteger(final boolean isArrayBaseType)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputSimpleLong(final boolean isArrayBaseType)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputSimpleFloat(final boolean isArrayBaseType)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputSimpleDouble(final boolean isArrayBaseType)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputSimpleString(final boolean isArrayBaseType)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputItemRef(final ItemRef itemRef)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputItemComplex(final ItemComplex itemComplex)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputItemFile()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getTypeOutputDefault()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}
}
