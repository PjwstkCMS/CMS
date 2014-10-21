function DictionaryListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "DictionaryList";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    $scope.status = "Ładowanie danych";
    
    $scope.objectsName = "dictTypes";
    $scope.attributes = [];
    $scope.attributes[0] = 'value';
    $scope.attributes[1] = 'description';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'value',1:true};
    $scope.editValues[2] = {0:'description',1:true};
    
    $scope.dictionaryAttributes = [];
    $scope.dictionaryAttributes[0] = 'value';
    $scope.dictionaryAttributes[1] = 'description';
    
    $scope.dictionaryValues = [];
    $scope.dictionaryValues[0] = {0:'id', 1:false};
    $scope.dictionaryValues[1] = {0:'value',1:true};
    $scope.dictionaryValues[2] = {0:'description',1:true};
    
    $scope.editMode = false;
    $scope.selected = null;
    $scope.dictionarySelector = "";
    $scope.dictionaryEdit = false;
    $scope.newRecord = false;

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
            
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
            $scope.dictionarySelector = null;
            $scope.dictionaryEdit = false;
            $scope.editMode = false;
            $scope.newRecord = false;
        } else {
            saveEditDelete.saveOldData($scope,object);
            $scope.dictionarySelector = null;
            $scope.selected = object;
            $scope.dictionaryEdit = false;
            $scope.newRecord = false;
        }
    }

    $scope.edit = function() {
        $scope.editMode = true;
        $scope.newRecord = false;
    };

    $scope.cancel = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.editMode = false;
        $scope.selected = "";
        $scope.newRecord = false;
    };

    $scope.create = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.selected = {
            'id': "", 'value':"", 'description':"", 'dictionaries':[]
        };
        $scope.editMode = true;
        $scope.newRecord = true;

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
    
    $scope.addDictionary = function(){
        $scope.dictionarySelector = {
            'id': "", 'value':"", 'description':"",'dictTypeId':$scope.selected.id
        };
        $scope.dictionaryEdit = true;
    };
    
    $scope.editDictionary = function(){
        if($scope.dictionarySelector != ""){
            $scope.dictionaryEdit = true;
        }
    };
    
    $scope.cancelDictionary = function(){
        $scope.dictionaryEdit = false;
        if($scope.dictionarySelector.id == ""){
            $scope.dictionarySelector = null;
        }
    };
    
    $scope.addKey = function() {
        $scope.dictionarySelector.dictTypeId = $scope.selected.id;
        for (var i = 0; i<$scope.dictionaryValues.length; i++) {  
            if($scope.dictionaryValues[i][1] && $scope.dictionarySelector[$scope.dictionaryValues[i][0]] == null){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.saveKey($http, '/CMS/dictionary/save/:object.htm', $scope, $scope.dictionarySelector, $scope.selected.dictionaries);
        if (!$scope.selectedGroupHasKey($scope.dictionarySelector)) {
            $scope.selected.dictionaries.push($scope.dictionarySelector);
        }
        $scope.dictionaryEdit = false;
    };

    $scope.removeKey = function() {
        if($scope.dictionarySelector !== undefined){
            saveEditDelete.deleteKey($http, '/CMS/dictionary/delete/:object.htm', $scope, $scope.dictionarySelector, $scope.selected.dictionaries);
            
            $scope.dictionarySelector = "";
            $scope.dictionaryEdit = false;
        }
    };
    
    $scope.selectedGroupHasKey = function(privKeyId) {
        if ($scope.selected.dictionaries !== undefined) {
            for (var i = 0; i < $scope.selected.dictionaries.length; i++) {
                if ($scope.selected.dictionaries[i] == privKeyId) {
                    return true;
                }
            }
        }
        return false;
    };
}