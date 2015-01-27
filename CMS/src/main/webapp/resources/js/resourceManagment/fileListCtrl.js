function FileListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    
    $scope.page = "FileList";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "files";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'description';
    $scope.attributes[2] = 'mimeType';
    $scope.columns = {
      'name' : "Nazwa"  ,
      'description' : "Opis",
      'mimeType' : "Rodzaj pliku"
    };
    $scope.columnClasses = {
        'name' : "file-name",
        'surname': "file-opis",
        'phone': "file-type"
    };
    $scope.selected = "";
    $scope.files = "";
    $scope.get = saveEditDelete.get($http, '/CMS/fileList/files.htm', $scope);
    var loadDataPromise = $scope.get;

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.files = $scope.initData.files;
        } else {
            $scope.status = "Błąd:";
            alert('err');
        }
    });

    $scope.select = function(report) {
        if ($scope.selected == report) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
        } else {
            $scope.selected = report;
            saveEditDelete.saveOldData($scope,report);
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