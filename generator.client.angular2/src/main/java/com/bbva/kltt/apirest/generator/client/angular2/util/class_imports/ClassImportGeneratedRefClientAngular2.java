package com.bbva.kltt.apirest.generator.client.angular2.util.class_imports;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ClassImportGeneratedRefClientAngular2 implements ClassImportsClientAngular2
{
	/** Attribute - Loaded dependency */
	private final String loadedDependency ;
	
	/** Attribute - Full relative directory path */
	private String fullRelativeDirectory ;
	
	/**
	 * Private constructor
	 * @param loadedDependency with the loaded dependency
	 */
	private ClassImportGeneratedRefClientAngular2(final String loadedDependency)
	{
		this.loadedDependency = loadedDependency ;
	}
	
	/**
	 * Public constructor
	 * @param relativeDirectoryPath with the relative directory path
	 * @param loadedDependency				with the loaded dependency
	 */
	public ClassImportGeneratedRefClientAngular2(final String relativeDirectoryPath, final String loadedDependency)
	{
		this(loadedDependency) ;
		this.fullRelativeDirectory = relativeDirectoryPath + ConstantsCommon.STRING_SLASH + this.loadedDependency ;
	}
	
	/**
	 * Public constructor
	 * @param relativeDirectoryPath with the relative directory path
	 * @param loadedDependency		with the loaded dependency
	 * @param suffixVersion			with the suffix version
	 */
	public ClassImportGeneratedRefClientAngular2(final String relativeDirectoryPath, final String loadedDependency, final String suffixVersion)
	{
		this(loadedDependency) ;
		this.fullRelativeDirectory = relativeDirectoryPath + ConstantsCommon.STRING_SLASH + this.loadedDependency + suffixVersion ;
	}
	
	/**
	 * @return the loaded dependency as the class name
	 */
	@Override
	public String getLoadedDependencies()
	{
		return this.loadedDependency ;
	}

	/**
	 * @return the library reference as the full relative directory
	 */
	@Override
	public String getLibraryReference()
	{
		return this.fullRelativeDirectory ;
	}
}
