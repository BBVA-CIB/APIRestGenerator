package com.bbva.kltt.apirest.core.ant;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyAntExecutor extends AbstractAntExecutor
{
    /**
     * @param generatorBuilderPath with the generator builder path
     * @param antFile              with the ant file
     * @param translatorType       with the translator type
     * @param fullPkgLauncherClass with the full package launcher class
     * @param parserType           with the parser type
     * @param deliverableFileName  with the deliverable file name
     */
    public MyAntExecutor(final String generatorBuilderPath,
                         final String antFile,
                         final String translatorType,
                         final String fullPkgLauncherClass,
                         final String parserType,
                         final String deliverableFileName)
    {
		super(generatorBuilderPath, antFile, translatorType, fullPkgLauncherClass, parserType, deliverableFileName) ;
	}

}
