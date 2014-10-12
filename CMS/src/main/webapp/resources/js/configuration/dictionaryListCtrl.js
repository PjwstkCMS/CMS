function DictionaryListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "simple";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    $scope.status = "Ładowanie danych";
    
    $scope.objectsName = "dicts";
    $scope.attributes = [];
    $scope.attributes[0] = 'value';
    $scope.attributes[1] = 'description';
    $scope.attributes[2] = 'dictTypeId';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'value',1:true};
    $scope.editValues[2] = {0:'description',1:true};
    $scope.editValues[3] = {0:'dictTypeId',1:true};
    
    $scope.editMode = false;
    $scope.selected = null;

    $scope.get = saveEditDelete.get($http, '/CMS/dictionaryList/dictTypes.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/dictionaryList/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.dictTypes = $scope.initData.dictTypes;
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
        $scope.selected = {
            'id': "", 'value':"", 'dictTypeId':"", 'description':""
        };
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/dictionaryList/delete/:object.htm', $scope);
    };
    
    $scope.checkEditPrivileges = function() {
        return true;
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
}