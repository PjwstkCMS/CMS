function EmploymentListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "simple";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.employees = "";
    $scope.privileges = "";
    $scope.editMode = false;
    $scope.newRecord = false;
    
    $scope.objectsName = "employments";
    $scope.attributes = [];
    $scope.attributes[0] = 'id';
    $scope.attributes[1] = 'dateFrom';
    $scope.attributes[2] = 'dateTo';
    $scope.attributes[3] = 'dictId';
    $scope.attributes[4] = 'employeeId';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'dateFrom', 1:true};
    $scope.editValues[2] = {0:'dateTo', 1:true};
    $scope.editValues[3] = {0:'dictId', 1:true};
    $scope.editValues[4] = {0:'employeeId', 1:true};
        
    $scope.get = saveEditDelete.get($http, '/CMS/employment/employments.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/employment/save/:object.htm', $scope);
        
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.employees = $scope.initData.employees;
            $scope.employments = $scope.initData.employments;
            $scope.dictionaries = $scope.initData.dictionaries;
            
            $scope.privileges = $scope.initData.privileges;
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
            saveEditDelete.saveOldData($scope, object);
            $scope.selected = object;
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
        $scope.selected = {'id':"",'dateFrom':"","dateTo":"","dictId":"","employeeId":""};
        $scope.editMode = true;
        $scope.newRecord = true;
    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/employment/delete/:object.htm', $scope);
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
    
}