function TaskListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "simple";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.tasks = "";
    $scope.privileges = "";
    $scope.editMode = false;
    $scope.newRecord = false;
    
    $scope.objectsName = "tasks";
    $scope.attributes = [];
    $scope.attributes[0] = 'id';
    $scope.attributes[1] = 'employee';
    $scope.attributes[2] = 'startDate';
    $scope.attributes[3] = 'closeDate';
    $scope.attributes[4] = 'finalisationDate';
    $scope.attributes[5] = 'description';
    $scope.attributes[6] = 'dict';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'employeeId', 1:false};
    $scope.editValues[2] = {0:'startDate', 1:true};
    $scope.editValues[3] = {0:'closeDate', 1:true};
    $scope.editValues[4] = {0:'description', 1:true};
    $scope.editValues[5] = {0:'dictId', 1:true};

        
    $scope.get = saveEditDelete.get($http, '/CMS/task/tasks.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność danych: "+columnDesc.get($scope.editValues[i][0]));
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/task/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.tasks = $scope.initData.tasks;
            $scope.dictionaries = $scope.initData.dictionaries;
            $scope.employees = $scope.initData.employees;
            $scope.privileges = $scope.initData.privileges;
            $scope.managerId = $scope.initData.managerId;
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
        asd = $scope.managerId;
        $scope.selected = {
            'id': "", 'employeeId':"",managerId:asd,'startDate':"",'closeDate':"",
            'finalisationDate':"",'description':"",'dictId':"", employee:"",dict:""
        };
        $scope.newRecord = true;
        $scope.editMode = true;
    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/task/delete/:object.htm', $scope);
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
    
    $scope.selectAction = function(obj) {
        if(obj == 'employeeId'){
            var index;
            for (var i = 0; i < $scope.employees.length; i++) {
                if ($scope.employees[i].id == $scope.selected.employeeId) {
                    index = i;
                }
            }
            $scope.selected.employee = $scope.employees[index].forename + ' ' + $scope.employees[index].surname;
        }
        if(obj == 'dictId'){
            var index;
            for (var i = 0; i < $scope.dictionaries.length; i++) {
                if ($scope.dictionaries[i].id == $scope.selected.dictId) {
                    index = i;
                }
            }
            $scope.selected.dict = $scope.dictionaries[index].description;
        }
    };
}