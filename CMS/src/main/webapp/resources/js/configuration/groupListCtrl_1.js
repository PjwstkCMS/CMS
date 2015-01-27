function GroupListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "GroupList";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "groups";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'name',1:true};
    
    $scope.privilegeAttributes = [];
    $scope.privilegeAttributes[0] = 'id';
    $scope.privilegeAttributes[1] = 'code';
    $scope.privilegeAttributes[2] = 'description';
    
    $scope.editMode = false;
    $scope.newRecord = false;
    $scope.selected = "";
    $scope.selected.privileges = "";

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
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
            $scope.newRecord = false;
        } else {
            saveEditDelete.saveOldData($scope,object);
            $scope.selected = object;
            $scope.newRecord = false;
            $scope.selected.privileges = object.privileges;
        }
    }

    $scope.edit = function() {
        $scope.editMode = true;
        $scope.newRecord = false;
    };

    $scope.cancel = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.editMode = false;
        $scope.newRecord = false;
        $scope.selected = "";
    };

    $scope.create = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.selected = {
            "id":"","name":"","privileges":[]
        };
        $scope.editMode = true;
        $scope.newRecord = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/groupList/delete/:object.htm', $scope);
    };

    $scope.addKey = function() {
        if (!$scope.selectedGroupHasKey($scope.newKey)) {
            key = {
                'id':"",'groupId':$scope.selected.id,'keyId':$scope.newKey
            };
            saveEditDelete.saveKey($http, '/CMS/privilege/save/:object.htm', $scope, key, $scope.selected.privileges);
        
            $scope.newKey = 0;
        }
    };

    $scope.removeKey = function() {
        if ($scope.selectedGroupHasKey($scope.oldKey)) {
            
            var index = -1;
                for (var i = 0; i < $scope.selected.privileges.length; i++) {
                    if ($scope.selected.privileges[i].keyId == $scope.oldKey) {
                        index = i;
                    }
                }
            key = {
                'id':$scope.selected.privileges[index].id,'groupId':$scope.selected.id,'keyId':$scope.oldKey
            };
            saveEditDelete.deleteKey($http, '/CMS/privilege/delete/:object.htm', $scope, key, $scope.selected.privileges);
            
            $scope.oldKey = 0;
        }
    };

    $scope.groupHasKey = function(group, privKeyId) {
        for (var i = 0; i < group.privilegeKeyIds.length; i++) {
            if (group.privilegeKeyIds[i] == privKeyId) {
                return true;
            }
        }
        return false;
    };

    $scope.selectedGroupHasKey = function(privKeyId) {
        if ($scope.selected.privileges !== undefined) {
            for (var i = 0; i < $scope.selected.privileges.length; i++) {
                if ($scope.selected.privileges[i].keyId == privKeyId) {
                    return true;
                }
            }
        }
        return false;
    };
    
    $scope.checkEditPrivileges = function() {
        return true;
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
    
    $scope.checkTable = function(table, divName){
        for (var i = 0; i < table.length; i++) {
            var temp = table[i];
            if (temp[0] === divName) {
                return true;
            }
        }
        return false;
    };
}