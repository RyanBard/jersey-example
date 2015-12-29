(function (angular) {
    'use strict';

    var module = angular.module(
        'ryan.user.resource',
        [
            'ngResource'
        ]
    );

    module.factory(
        'User',
        [
            '$resource',
            function ($resource) {
                return $resource(
                    'api/users/:id',
                    {
                        id: '@id'
                    }
                );
            }
        ]
    );

}(angular));
