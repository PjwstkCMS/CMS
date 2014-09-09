var cmsModule = angular.module('cms', []);

cmsModule.factory('saveEditDelete', function() {
    return {
        save: function($http, link, $scope) {
            if ($scope.selected.id == null || $scope.selected.id < 1) {
                $scope[$scope.objectsName].push($scope.selected);
            }

            return $http.post(
                    link,
                    {object: $scope.selected}).success(function(returnId) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja zapisywania udana"
                
                if(returnId!=null) {
                    $scope.selected.id = returnId.id;
                }                
                $scope.selected = null;
                $scope.editMode = false;
            }).error(function(error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja zapisywania nie udana"
                alert(error);
            });
        },
        get: function($http, link, $scope) {
            return $http.get(link).success(function(returnData) {
                $scope.status = null;
                $scope.initData = returnData;
                $scope[$scope.objectsName] = returnData[$scope.objectsName];
                $scope.objects = returnData[$scope.objectsName];
                //alert(returnData.departmnets);
                return "Success";
            }).error(function(error) {
                $scope.status = "Błąd";
                return null;
            });
        },
        remove: function($http, link, $scope) {
            return $http.post(
                    link,
                    {object: $scope.selected}).success(function() {
                var index;
                for (var i = 0; i < $scope[$scope.objectsName].length; i++) {
                    if ($scope[$scope.objectsName][i].id == $scope.selected.id) {
                        index = i;
                    }
                }
                $scope[$scope.objectsName].splice(index, 1);
                $scope.selected = "";
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja usuwania udana"
            }).error(function(error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja zapisywania nie udana"
            });
        }
    };
});

cmsModule.factory('pagination', function() {
    return {
        indexOnPage: function($scope) {
            return function(index)
            {
                if (index <= $scope.pageMax && index >= $scope.pageMin) {
                    return true;
                } else {
                    return false;
                }
            };
        },
        pageMaxSmallerThenSize: function($scope) {
            return function() {
                //alert($scope[$scope.objectsName].length);
                if ($scope[$scope.objectsName].length <= 10) {
                    return false;
                }
                if ($scope.pageMax > $scope[$scope.objectsName].length) {
                    return false;
                } else {
                    return true;
                }
            };
        }
    };
});