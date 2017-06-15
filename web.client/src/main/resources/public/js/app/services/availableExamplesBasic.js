/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var availableExamplesBasicService = angular.module('availableExamplesBasicService', []) ;
availableExamplesBasicService.factory('availableExamplesBasicService', function ()
{
	var instance    	 = {} ;
	
	instance.get = function()
	{
		return [
		        	{subfolder: 'basic', fileName: 'arrays_osgi.yaml'},
		        	{subfolder: 'basic', fileName: 'arrays.yaml'},
		        	{subfolder: 'basic', fileName: 'echo.yaml' },
		        	{subfolder: 'basic', fileName: 'echo_osgi.yaml'},
		        	{subfolder: 'basic', fileName: 'exceptions.yaml' },
		        	{subfolder: 'basic', fileName: 'exceptions_osgi.yaml'},
		        	{subfolder: 'basic', fileName: 'formData.yaml'},
			 		{subfolder: 'basic', fileName: 'minimal.yaml'},
			 		{subfolder: 'basic', fileName: 'minimal_osgi.yaml'},
			 		{subfolder: 'basic', fileName: 'head.yaml'},
			 		{subfolder: 'basic', fileName: 'head_osgi.yaml'},
		       ] ;
	} ;
	
	return instance ;
}) ;