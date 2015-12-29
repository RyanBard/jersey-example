(function (angular) {
    'use strict';

    var module = angular.module(
        'ryan.user.directive',
        []
    );

    module.directive(
        'UserDirective',
        [
            function () {
                return {
                    restrict: 'AE',
                    scope: {},
                    transclude: false,
                    replace: false,
                    template: '<div ng-bind="test"></div>',
                    link: function (scope, element, attributes) {
                        scope.test = 'the directive works';
                    }
                };
            }
        ]
    );

}(angular));
