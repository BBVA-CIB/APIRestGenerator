package com.bbva.kltt.apirest.core.util;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyDependencyUtility extends AbstractDependencyUtility
{
    /**
     * Constructor for generator modules
     *
     * @param moduleType  with the type of the module
     * @param displayName with the name to display
     * @param moduleName  with the real name of the module
     */
	public MyDependencyUtility(final String moduleType, final String displayName, final String moduleName)
	{
		super(moduleType, displayName, moduleName) ;
	}
}
