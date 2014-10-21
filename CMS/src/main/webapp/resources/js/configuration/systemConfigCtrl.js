function SystemConfigCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "SystemConfig";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "systemConfigs";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'value';
    $scope.attributes[2] = 'description';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'value',1:true};
    
    $scope.editMode = false;
    $scope.editValue = "Edytuj";
    $scope.selected = null;

    $scope.get = saveEditDelete.get($http, '/CMS/systemConfig/configs.htm', $scope);
    $scope.loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/systemConfig/save/:object.htm', $scope);
    };

    $scope.loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            
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

    $scope.checkEditPrivileges = function() {
        return true;
    };
    
    $scope.defaultSettings = function() {
        $scope.get = saveEditDelete.get($http, '/CMS/systemConfig/defaultConfigs.htm', $scope);
        $scope.loadDataPromise = $scope.get;
        
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
}