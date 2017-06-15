package com.bbva.kltt.apirest.core.generator.output.language;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyOutputLanguageSeparators implements IOutputLanguageSeparators
{
	@Override
	public String generateSeparatorValues()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String generateStringDelimiter()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}
}
