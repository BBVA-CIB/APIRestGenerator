/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var InstalledModules = angular.module('installedModules', []) ;
InstalledModules.factory('installedModulesService', function($http, $q) {


    function InstalledModulesService() {

        var self = this;

        self.getInstalledModules = function(generatorAddress) {

            var deferred = $q.defer();

            $http.get(generatorAddress + 'api/getGenerateModules')
            .success(function(response) {
                deferred.resolve(response);
            })
            .error(function(response) {
                deferred.reject(response);
            });


            //    Now return the promise.
            return deferred.promise;
        };
    }

    return new InstalledModulesService();
});