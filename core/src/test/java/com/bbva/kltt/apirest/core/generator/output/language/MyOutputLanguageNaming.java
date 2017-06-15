package com.bbva.kltt.apirest.core.generator.output.language;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyOutputLanguageNaming implements IOutputLanguageNaming
{
	@Override
	public String prefixClassName(String complexTypeName)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String suffixMethodName(String attributeName)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String prefixAttributeName(String attributeName)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getUrlWithSlashes(String... httpTokens)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String getCamelCaseName(String name)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

}
