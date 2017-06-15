package com.bbva.kltt.apirest.generator.client.web.generator;

import com.bbva.kltt.apirest.core.generator.TranslatorGeneratorBase;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorBaseClientWeb extends TranslatorGeneratorBase
{
	/**
	 * Constructor of the class
	 * @param translatorType    with the translator type
	 * @param generationParams  with the generation parameters
	 * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
	 * @param generationPackage with the generation package
	 */
	public TranslatorGeneratorBaseClientWeb(final String translatorType,
								      final GenerationParameters generationParams,
								      final ParsedInfoHandler parsedInfoHandler,
								      final String generationPackage)
	{
		super(translatorType, generationParams, parsedInfoHandler, generationPackage) ;
	}

	@Override
	public String getGeneratorInfoProjectsChildren()
	{
		final StringBuilder outcome    = new StringBuilder() ;
		
		final String generatorJavaInfo = FilesUtility.loadFileContentFromClasspath(ConstantsOutputClientWeb.PROJECT_NAME +
																			       ConstantsCommon.STRING_DOT      + ConstantsOutput.EXTENSION_PROPERTIES) ;
		outcome.append(generatorJavaInfo) ;
		outcome.append(ConstantsCommon.STRING_CARRIAGE_RETURN) ;
		
		final String childreProjectsInfoJava = this.getGeneratorInfoProjectsChildrenWeb() ;
		outcome.append(childreProjectsInfoJava) ;
		
		return outcome.toString() ;
	}
	
	/**
	 * @return the generator info projects children for web projects
	 */
	public abstract String getGeneratorInfoProjectsChildrenWeb() ;
}
