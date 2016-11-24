/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var angularInstance = angular.module('GeneratorApp', ['ngMaterial',
													  'ngMessages',
													  'availableExamplesBasicService',
													  'availableExamplesComplexService',
													  'examplesLoaderService',
													  'generatorService',
													  'installedModules',
													  'loggerGenerationService',
													  'newFileService',
													  'downloadSpecService',
													  'commonIssuesService',
													  'luegg.directives']) ;

// Getting generator address at booting Angularjs
(function() {

    fetchData().then(bootstrapApplication);

    function fetchData() {
        var initInjector = angular.injector(["ng"]);
        var $http = initInjector.get("$http");

        return $http.get("getGeneratorAddress").then(function(response) {
            angularInstance.constant("generatorAddress", response.data);
        }, function(errorResponse) {
            angularInstance.constant("generatorAddress", "");
        });
    }

    function bootstrapApplication() {
        angular.element(document).ready(function() {
            angular.bootstrap(document, ["GeneratorApp"]);
        });
    }
}());


angularInstance.directive('myenterkey',
						  function ()
						  {
						    return function (scope, element, attrs)
						    {
						        element.bind("keydown keypress keyup",
						        			 function (event)
						        			 {
						        				if(event.which === 13)
						        				{
						        					scope.$apply(function ()
						        					{
									                    scope.$eval(attrs.myenterkey);
									                });

									                event.preventDefault();
									            }
						        			 }
						        			);
						    };
						  }
);

/**
 * This directive is useful to set the height of the code editor
 */
angularInstance.directive('setheight', function($window)
{
  return {
    link: function(scope, element, attrs)
    {
        element.css('height', ($window.innerHeight - 150) + "px") ;
    }
  }
});

/**
 * Main controller
 */
angularInstance.controller('GeneratorCtrl', function GeneratorCtrl(availableExamplesBasicService,
																   availableExamplesComplexService,
																   examplesLoaderService,
																   generatorService,
																   installedModulesService,
																   loggerGenerationService,
																   newFileService,
																   downloadSpecService,
																   commonIssuesService,
																   $scope,
																   $mdDialog,
																   $mdMedia,
																   $window,
																   generatorAddress)
{
	$scope.fileName 		 = "Untitled.yaml" ;
	$scope.loggingGeneration = "" ;
	$scope.installedModules  = {};

	$scope.initialization = function(exampleSubfolder, exampleFileName)
	{
		examplesLoaderService.initialization(exampleSubfolder, exampleFileName) ;
		$scope.fileName = exampleFileName ;

		loggerGenerationService.initialization($scope, generatorAddress) ;

	} ;

	$scope.newSpecification = function(language)
	{
		$scope.fileName = "Untitled." + language ;
		newFileService.initialization($scope.fileName, language) ;
	}

	$scope.openExamplesBasic = function(indexEvent)
	{
		$scope.openExamples(indexEvent, availableExamplesBasicService.get()) ;
	}

	$scope.openExamplesComplex = function(indexEvent)
	{
		$scope.openExamples(indexEvent, availableExamplesComplexService.get()) ;
	}

	$scope.openExamples = function(indexEvent, availableExamples)
	{
		var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen ;
		$mdDialog.show({
			controller: ExampleLoaderDialogController,
			templateUrl: 'dialogs/file/exampleLoader.tmpl.html',
			parent: angular.element(document.body),
			targetEvent: indexEvent,
			clickOutsideToClose:true,
			fullscreen: useFullScreen,
			resolve:
			{
				availableExamples: function ()
				{
        			return availableExamples ;
    			},

    			examplesLoaderService: function()
    			{
    				return examplesLoaderService ;
    			}
    		}
		})
	    .then(function(exampleSelected)
	    {
			$scope.fileName = exampleSelected ;
	    }) ;
	}

	$scope.downloadSpecification = function(indexEvent)
	{
		downloadSpecService.download($scope.fileName) ;
	}

	$scope.generateCode = function(indexEvent, typeGeneration)
	{
		$scope.loggingGeneration = "" ;
		generatorService.generateLanguage(generatorAddress, loggerGenerationService.getUserId(), $scope.fileName, typeGeneration) ;
		$scope.openLogs(indexEvent) ;
	}

	$scope.checkInstalledModules = function() {
	    installedModulesService.getInstalledModules(generatorAddress)
	        .then(
	        /* success function */
            function(result) {
                var item = null;
                $scope.installedModules.server = [];
                $scope.installedModules.client = [];

                for (var i=0; i < result.length; i++)
                {
                    item = result[i];
                    if (item.typeModule == "server")
                    {
                        $scope.installedModules.server.push(item);
                    } else
                    {
                        $scope.installedModules.client.push(item);
                    }
                }

                $scope.installedModules.server.sort(function(a, b){
                                                       if(a.displayName < b.displayName) return -1;
                                                       if(a.displayName > b.displayName) return 1;
                                                       return 0;
                                                   })

                $scope.installedModules.client.sort(function(a, b){
                                                       if(a.displayName < b.displayName) return -1;
                                                       if(a.displayName > b.displayName) return 1;
                                                       return 0;
                                                   })

            },
            /* error function */
            function(result) {
                console.log("Failed to get installed modules, result is " + result);
            });
	}

	$scope.openLogs = function(indexEvent)
	{
		var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen ;
		$mdDialog.show({
			controller: LoggingGenerationDialogController,
			templateUrl: 'dialogs/main/loggingGeneration.tmpl.html',
			targetEvent: indexEvent,
			locals: {parent: $scope},
			clickOutsideToClose:true,
			fullscreen: useFullScreen
		}) ;
	}

	$scope.openNewTabSwaggerSpec = function(indexEvent)
	{
		$window.open('http://swagger.io/specification/', '_blank') ;
	}

	$scope.openHelpCommonIssues = function(indexEvent)
	{
		var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen ;
		$mdDialog.show({
			controller: CommonIssuesDialogController,
			templateUrl: 'dialogs/help/commonIssues.tmpl.html',
			targetEvent: indexEvent,
			locals: {commonIssues: commonIssuesService.get()},
			clickOutsideToClose: true,
			fullscreen: useFullScreen
		}) ;
	}

} ) ;

function ExampleLoaderDialogController($scope, $mdDialog, availableExamples, examplesLoaderService) 
{
	var halfExampleLength	 = Math.floor(availableExamples.length / 2) ;
	$scope.exampleSubfolder	 = availableExamples[halfExampleLength].subfolder ;
	$scope.exampleSelected   = availableExamples[halfExampleLength].fileName ;
  	$scope.availableExamples = availableExamples ;
	
	$scope.open = function ()
	{
		examplesLoaderService.initialization($scope.exampleSubfolder, $scope.exampleSelected) ;
		$mdDialog.hide($scope.exampleSelected) ;
  	};

	$scope.cancel = function ()
	{
    	$mdDialog.cancel() ;
  	};
}

function LoggingGenerationDialogController($scope, $mdDialog, parent) 
{
	$scope.parent = parent ;

	$scope.close = function ()
	{
    	$mdDialog.cancel() ;
  	};
  	
  	$scope.openHelpCommonIssues = function (indexEvent)
	{
    	$mdDialog.cancel() ;
    	parent.openHelpCommonIssues(indexEvent) ;
  	};
}

function CommonIssuesDialogController($scope, $mdDialog, commonIssues)
{
	$scope.commonIssues = commonIssues ;

	$scope.close = function ()
	{
    	$mdDialog.cancel() ;
  	};
}