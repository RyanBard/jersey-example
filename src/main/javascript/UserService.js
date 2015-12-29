(function (angular) {
    'use strict';

    var module = angular.module(
        'ryan.user.service',
        []
    );

    module.factory(
        'UserService',
        [
            function () {
                var service = {};

                service.testIt = function () {
                    return 'the service works';
                };

                return service;
            }
        ]
    );

}(angular));
