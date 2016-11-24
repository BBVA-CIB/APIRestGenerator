/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var downloadSpecService = angular.module('downloadSpecService', ['codeEditorService']) ;
downloadSpecService.factory('downloadSpecService', function (codeEditorService)
{
	var instance = {} ;
	
    instance.download = function(fileName)
	{
		var specificationContent = codeEditorService.getFullCodeText() ;
		var blob 				 = new Blob([specificationContent]) ;
		
		saveAs(blob, fileName) ;
	} ;
		
    return instance ;
});