package com.bbva.kltt.apirest.core.generator.output.language;

import java.util.ArrayList;
import java.util.List;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyOutputLanguageOperations implements IOutputLanguageOperations
{
	@Override
	public boolean hasAnyOperationId(String pathValue, String pathOperation)
	{
		return false ;
	}

	@Override
	public List<String> getAllOperationIds()
	{
		return new ArrayList<String>() ;
	}

	@Override
	public String getOperationId(String pathValue, String pathOperation)
	{
		return ConstantsCommon.STRING_EMPTY ;
	}

}
