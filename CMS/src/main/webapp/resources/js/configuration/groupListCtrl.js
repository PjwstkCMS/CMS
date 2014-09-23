function GroupListCtrl($scope, $http, saveEditDelete, pagination) {
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "groups";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.columns = {
        'name': "Nazwa",
    };
    $scope.editMode = false;
    $scope.selected = "";
    $scope.selected.privilegeKeyIds = "";

    $scope.get = saveEditDelete.get($http, '/CMS/groupList/groups.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/groupList/save/:object.htm', $scope);
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
            $scope.selected.privilegeKeyIds = object.privilegeKeyIds;
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
        saveEditDelete.remove($http, '/CMS/groupList/delete/:object.htm', $scope);
    };

    $scope.addKey = function() {
        if (!$scope.selectedGroupHasKey($scope.newKeyId)) {
            $scope.selected.privilegeKeyIds.push($scope.newKeyId);
        }
    };

    $scope.removeKey = function() {
        if ($scope.selectedGroupHasKey($scope.oldKeyId)) {
            var index = $scope.selected.privilegeKeyIds.indexOf($scope.oldKeyId);
            $scope.selected.privilegeKeyIds.splice(index, 1);
            $scope.oldKeyId = 0;
        }
    }

    $scope.groupHasKey = function(group, privKeyId) {
        for (var i = 0; i < group.privilegeKeyIds.length; i++) {
            if (group.privilegeKeyIds[i] == privKeyId) {
                return true;
            }
        }
        return false;
    }

    $scope.selectedGroupHasKey = function(privKeyId) {
        if ($scope.selected.privilegeKeyIds !== undefined) {
            for (var i = 0; i < $scope.selected.privilegeKeyIds.length; i++) {
                if ($scope.selected.privilegeKeyIds[i] == privKeyId) {
                    return true;
                }
            }
        }
        return false;
    }
    
    $scope.checkEditPrivileges = function() {
        return true;
    };
}