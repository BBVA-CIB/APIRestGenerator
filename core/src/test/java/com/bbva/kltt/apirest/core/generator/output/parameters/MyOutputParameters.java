package com.bbva.kltt.apirest.core.generator.output.parameters;

import java.util.List;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageSeparators;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyOutputParameters extends OutputParameters {

	/**
	 * Public constructor
	 * @param outputLangSeparators with the output language separators
	 * @param parametersList 	   with the parameters list
	 */
	public MyOutputParameters(final IOutputLanguageSeparators outputLangSeparators, final List<OutputParameter> parametersList)
	{
		super(outputLangSeparators, parametersList) ;
	}

	@Override
	protected void getParametersDescriptionSpecific(List<String> currentOutcome, OutputParameter outputParameter)
	{
		// Nothing to do
	}

	@Override
	protected void getParametersHeaderSpecific(StringBuilder currentOutcome, OutputParameter outputParameter)
	{
		// Nothing to do
	}

	@Override
	protected void getParametersCallSpecific(StringBuilder currentOutcome, OutputParameter outputParameter)
	{
		// Nothing to do
	}
}
