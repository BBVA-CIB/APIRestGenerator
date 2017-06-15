package com.bbva.kltt.apirest.core.generator;

import java.io.File;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyTranslatorGeneratorBase extends TranslatorGeneratorBase {

	/**
	 * Constructor of the class
	 * @param translatorType    with the translator type
	 * @param generationParams  with the generation parameters
	 * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
	 * @param generationPackage with the generation package
	 */
	public MyTranslatorGeneratorBase(final String translatorType, 
									 final GenerationParameters generationParams,
									 final ParsedInfoHandler parsedInfoHandler,
									 final String generationPackage)
	{
		super(translatorType, generationParams, parsedInfoHandler, generationPackage) ;
	}

	@Override
	public File getSourceDir()
	{
		return new File("target") ;
	}

	@Override
	public File getResourceDir()
	{
		return new File("target") ;
	}

	@Override
	public String getGeneratorInfoProjectsChildren()
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public void initializeFileDirectories() throws APIRestGeneratorException
	{
		// Nothing to do
	}

	@Override
	public void generateSpecific() throws APIRestGeneratorException
	{
		// Nothing to do
	}
}
