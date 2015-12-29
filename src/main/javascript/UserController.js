(function (angular) {
    'use strict';

    var module = angular.module(
        'ryan.user.controller',
        [
            'ryan.user.directive',
            'ryan.user.resource',
            'ryan.user.service'
        ]
    );

    module.controller(
        'UserController',
        [
            '$scope',
            'UserService',
            'User',
            function (
                $scope,
                UserService,
                User
            ) {
                $scope.controllerTest = 'the controller works';
                $scope.domainModel = User.query();
                $scope.serviceTest = UserService.testIt();
            }
        ]
    );

}(angular));
