app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .success(function(){
            })
            .error(function(){
            });
    }
}]);


app.controller('myProfileCtrl', ['$scope', '$log','fileUpload', '$window', '$http', '$location', function($scope, $log, fileUpload, $window, $http, $location) {



    //$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|file|ftp|blob):|data:image\//);

    if(sessionStorage.UserId) {
        // $scope.IsVisibleBeforeLogin = false;
        //$scope.IsVisibleAfterLogin = true;
        // var userId = sessionStorage.UserId;
        //console.log(userId);

        var json={
            userId:sessionStorage.UserId
        }

        //$http.post("http://192.168.0.51:8081/Propozal/profile/",json)
        $http.post('js/profile.json')
            .success(function(data) {
                $scope.UserDetail = data.entity.records[0];
                $scope.image=data.entity.records[0].image;
                console.log($scope.UserDetail);
            });
    }
    console.log("///////");
    console.log($scope.image);
    console.log("///////");
    // $scope.getImage = function(data){
    //     return 'data:image/jpeg;base64,' + data;
    // }

    //var file=$scope.myFile;
    //var uploadUrl="http://192.168.0.51:8081/Propozal/uploadedImages";

    //fileUpload.uploadFileToUrl(file, uploadUrl);

    $scope.uploadFile = function(){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = "http://192.168.0.51:8081/Propozal/image/";
        fileUpload.uploadFileToUrl(file, uploadUrl);
    };

}]);
