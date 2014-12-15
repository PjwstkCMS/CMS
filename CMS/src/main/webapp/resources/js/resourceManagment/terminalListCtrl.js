function TerminalListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "simple";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.companies = "";
    $scope.privileges = "";
    $scope.editMode = false;
    $scope.newRecord = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";
    
    $scope.objectsName = "terminals";
    $scope.attributes = [];
    $scope.attributes[0] = 'description';
    $scope.attributes[1] = 'mac';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'description',1:true};
    $scope.editValues[2] = {0:'mac',1:true};
        
    $scope.get = saveEditDelete.get($http, '/CMS/terminal/terminals.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/terminal/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.terminals = $scope.initData.terminals;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
            $scope.editMode = false;
            $scope.newRecord = false;
        } else {
            $scope.selected = object;
            saveEditDelete.saveOldData($scope,object);
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
            'id': "", 'description':""
        };
        $scope.editMode = true;
        $scope.newRecord = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/terminal/delete/:object.htm', $scope);
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
}