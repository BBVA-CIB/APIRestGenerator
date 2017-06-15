package com.bbva.kltt.apirest.core.generator.output.language;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyOutputLanguageConsumesProduces extends OutputLanguageConsumesProduces
{
	
	
	/**
	 * Public constructor
	 * @param parsedInfoHandler    with the parsed information handler
	 * @param outputLangSeparators with the output language separators
	 */
	public MyOutputLanguageConsumesProduces(final ParsedInfoHandler parsedInfoHandler, final IOutputLanguageSeparators outputLangSeparators)
	{
		super(parsedInfoHandler, outputLangSeparators) ;
	}

	@Override
	public String generateInitialConsumesOrProducesString()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String generateFinalConsumesOrProducesString()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}
}
