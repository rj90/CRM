/**
 * Created by Rav on 2016-01-23.
 */
function LoginController($scope, $rootScope, $location, $cookieStore, $state,
                         $stateParams, UserService) {

    $scope.rememberMe = false;
    $rootScope.isAutenticated = false;

    $scope.login = function() {
        console.log('IN')

        UserService.authenticate({username: $scope.username, password: $scope.password}, function(authenticationResult) {

            if(authenticationResult.status == 401){ // Invalid login or password
                $rootScope.loginErr = true;
                return;
            }

            var authToken = authenticationResult.data.token;

            $rootScope.authToken = authToken;
            $cookieStore.put('authToken', authToken);
            UserService.getUser(function(response) {
                $rootScope.user = response.data;
                $rootScope.isAutenticated = true;
                $rootScope.appInfo = response.data.appInfo;
                //$location.path($rootScope.homePath);
            });
        });
    };
};