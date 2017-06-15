/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var availableExamplesComplexService = angular.module('availableExamplesComplexService', []) ;
availableExamplesComplexService.factory('availableExamplesComplexService', function ()
{
	var instance    	 = {} ;
	
	instance.get = function()
	{
		return [
			        {subfolder: 'complex', fileName: 'footballPlayers.yaml'},
			        {subfolder: 'complex', fileName: 'footballPlayers_osgi.yaml'},
		        	{subfolder: 'complex', fileName: 'heroku-pets.yaml'},
		        	{subfolder: 'complex', fileName: 'heroku-pets_osgi.yaml'},
		        	{subfolder: 'complex', fileName: 'instagram.yaml'},
		        	{subfolder: 'complex', fileName: 'instagram_osgi.yaml'},
			 		{subfolder: 'complex', fileName: 'petstore_simple.yaml'},
			 		{subfolder: 'complex', fileName: 'petstore_simple_osgi.yaml'},
			 		{subfolder: 'complex', fileName: 'specExample_osgi.yaml'},
			 		{subfolder: 'complex', fileName: 'specExample.yaml'},
			 		{subfolder: 'complex', fileName: 'twitter.yaml'},
			 		{subfolder: 'complex', fileName: 'twitter_osgi.yaml'},
			 		{subfolder: 'complex', fileName: 'uber.yaml'},
			 		{subfolder: 'complex', fileName: 'uber_osgi.yaml'}
		       ] ;
	} ;
	
	return instance ;
}) ;