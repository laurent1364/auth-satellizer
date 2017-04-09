/**
 * Created by LaurentF on 07/04/2017.
 */

var app = angular.module('DemoApp', ['ui.router', 'satellizer', 'toastr']);

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {


    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'partials/home.tpl.html'
        })
        .state('secret', {
            url: '/secret',
            templateUrl: 'partials/secret.tpl.html',
            controller: 'SecretCtrl',
            data: {requiredLogin: true}
        })
        .state('login', {
            url: '/login',
            templateUrl: 'partials/login.tpl.html',
            controller: 'LoginSignupCtrl'
        });

    $urlRouterProvider.otherwise('/home');
}]);
app.config(['$authProvider', function ($authProvider) {
    $authProvider.httpInterceptor = false;
    $authProvider.baseUrl = 'http://localhost:8080';
    $authProvider.loginUrl = '/auth/login';
    $authProvider.signupUrl = '/auth/signup';
    $authProvider.tokenPrefix = 'demo';
    $authProvider.tokenName = 'token';
    $authProvider.tokenHeader = 'Authorization';
    $authProvider.tokenType = 'Bearer';

    $authProvider.google({
        clientId: '355068530979-v0s8fmc69hmi0ko0eek1e1p9b6ahq19f.apps.googleusercontent.com'
    });


    /*// Facebook
     $authProvider.facebook({
     clientId: '413108255566242',
     name: 'facebook',
     url: '/auth/facebook',
     authorizationEndpoint: 'https://www.facebook.com/v2.5/dialog/oauth',
     redirectUri: location.origin + '/',
     requiredUrlParams: ['display', 'scope'],
     scope: ['email'],
     scopeDelimiter: ',',
     display: 'popup',
     oauthType: '2.0',
     popupOptions: { width: 580, height: 400 }
     });*/
}]);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
/*
    $httpProvider.interceptors.push(['$q', '$localStorage', function ($q, $localStorage) {
        return {
            'request': function (config) {
                config.headers = config.headers || {};
                if ($localStorage.token) {
                    config.headers.Authorization = 'X-Auth- ' + $localStorage.token;
                }
                return config;
            },
            'responseError': function (response) {
                if (response.status === 401 || response.status === 403) {
                    /!*---- DO SOMETHING ---*!/

                }
                return $q.reject(response);
            }
        };
    }]);*/
}]);

app.run(function ($rootScope, $state, $auth) {


    $rootScope.$on('$stateChangeStart',
        function (event, toState) {
            var requiredLogin = false;
            if (toState.data && toState.data.requiredLogin)
                requiredLogin = true;

            if (requiredLogin && !$auth.isAuthenticated()) {
                event.preventDefault();
                $state.go('login');
            }
        });
});


app.controller('LoginSignupCtrl', function ($scope, $auth, $state, toastr) {

    /*    $scope.signUp = function () {
     $auth
     .signup({email: $scope.email, password: $scope.password})
     .then(function (response) {
     $auth.setToken(response);
     $state.go('secret');
     })
     .catch(function (response) {
     toastr.error(
     'Error!',
     {closeButton: true}
     );
     })
     };*/

    $scope.login = function () {
        $auth
            .login({username: $scope.email, password: $scope.password})
            .then(function (response) {
                $auth.setToken(response);
                $state.go('secret');
            })
            .catch(function (response) {
                toastr.error(
                    'Email or password not correct!',
                    {closeButton: true}
                );
            })
    };

    $scope.auth = function (provider) {
        $auth.authenticate(provider)
            .then(function (response) {
                console.debug("success", response);
                $state.go('secret');
            })
            .catch(function (response) {
                console.debug("catch", response);
            })
    }
});

app.controller('SecretCtrl', function ($scope, $state, $auth, $http) {
    $scope.logout = function () {
        $auth.logout();
        $state.go("home");
    };

    var req = {
        method: 'GET',
        url: 'http://localhost:8080/users',
        headers: {
            'Content-Type':'application/json',
            'Bearer': $auth.getToken()
        }
    }
    getUserInfo();

    function getUserInfo() {
        $http(req)
            .then(function (response) {
                $scope.users = response.data;
            })
            .catch(function (response) {
                console.log("getUserInfo error", response);
            })
    }
});