package com.bbva.kltt.apirest.generator.client.angular2.util.class_imports;

import java.util.List;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ClassImportExternalRefClientAngular2 implements ClassImportsClientAngular2
{
	/** Attribute - Loaded dependencies */
	private final List<String> loadedDependencies ;
	
	/** Attribute - Library name */
	private final String libraryName ;
	
	/**
	 * Public constructor
	 * @param loadedDependencies with the loaded dependencies
	 * @param libraryName		 with the library name
	 */
	public ClassImportExternalRefClientAngular2(final List<String> loadedDependencies, final String libraryName)
	{
		this.loadedDependencies = loadedDependencies ;
		this.libraryName 		= libraryName ;
	}
	
	/**
	 * @return the loaded dependency as the loaded dependencies
	 */
	@Override
	public String getLoadedDependencies()
	{
		final StringBuilder outcome = new StringBuilder() ;
		
		for (final String loadedDependency : this.loadedDependencies)
		{
			if (!outcome.toString().isEmpty())
			{
				outcome.append(ConstantsCommon.STRING_COMMA) ;
				outcome.append(ConstantsCommon.STRING_BLANK) ;
			}
			
			outcome.append(loadedDependency) ;
		}
		
		return outcome.toString() ;
	}

	/**
	 * @return the library reference as library name
	 */
	@Override
	public String getLibraryReference()
	{
		return this.libraryName ;
	}
}
