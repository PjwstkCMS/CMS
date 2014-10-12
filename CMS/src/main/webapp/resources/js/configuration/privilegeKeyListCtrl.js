function PrivilegeKeyListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    
    $scope.page = "simple";
    
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
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
        } else {
            saveEditDelete.saveOldData($scope,object);
            $scope.selected = object;
        }
    }

    $scope.edit = function() {
        $scope.editMode = true;
    };

    $scope.cancel = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.editMode = false;
        $scope.selected = "";
    };

    $scope.create = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.selected = "";
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/privilegeKeyList/delete/:object.htm', $scope);
    };
    
    $scope.checkEditPrivileges = function() {
        return true;
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
}