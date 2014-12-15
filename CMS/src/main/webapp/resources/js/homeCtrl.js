function HomeCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "simple";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.tasks = "";
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

        
    $scope.get = saveEditDelete.get($http, 'getUserTasks.htm', $scope);
    var loadDataPromise = $scope.get;
    
    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.tasks = $scope.initData.tasks;
        } else {
            alert('err');
        }
    });
    
    $scope.done = function() {
        
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1;//January is 0!`

        var yyyy = today.getFullYear();
        if(dd<10){dd='0'+dd}
        if(mm<10){mm='0'+mm}
        
        $scope.selected.finalisationDate = yyyy+'-'+mm+'-'+dd;
        
        saveEditDelete.save($http, '/CMS/task/save/:object.htm', $scope);
        $scope.editMode = false;
    };
    
    $scope.undo = function() {
        
        $scope.selected.finalisationDate = "";
        
        saveEditDelete.save($http, '/CMS/task/save/:object.htm', $scope);
        $scope.editMode = false;
    };
    
    $scope.select = function(object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
        } else {
            $scope.selected = object;
            saveEditDelete.saveOldData($scope,object);
        }
    }

    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
}