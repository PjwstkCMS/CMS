function PrivilegeKeyListCtrl($scope, $http, saveEditDelete, pagination) {
    
    
    $scope.bla = "bla";
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "privilegeKeys";
    $scope.attributes = [];
    $scope.attributes[0] = 'code';
    $scope.attributes[1] = 'description';
    $scope.columns = {
        'description' : "Opis" ,
        'code' : "Kod"
    };
    $scope.editMode = false;
    $scope.editValue = "Edytuj";
    $scope.selected = null;

    $scope.get = saveEditDelete.get($http, '/CMS/privilegeKeyList/privKeys.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/privilegeKeyList/save/:object.htm', $scope);
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.privilegeKeys = $scope.initData.privilegeKeys;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            $scope.selected = "";
        } else {
            $scope.selected = object;
        }
    }

    $scope.edit = function() {
        $scope.editMode = true;
    };

    $scope.cancel = function() {
        $scope.editMode = false;
        $scope.selected = "";
    };

    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/privilegeKeyList/delete/:object.htm', $scope);
    };
    
    $scope.checkEditPrivileges = function() {
        return true;
    };
}