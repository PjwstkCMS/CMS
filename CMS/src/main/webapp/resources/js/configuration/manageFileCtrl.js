function ManageFileCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.fileListUpload = true;
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'description';
    $scope.attributes[2] = 'mimeType';
    $scope.attributes[3] = 'id';
    $scope.columns = {
      'description' : "Opis",
      'name' : "Nazwa",
      'mimeType' : "Rodzaj pliku",
      'id' : "ID"
    };
    $scope.selected = "";
    $scope.reports = "";
    $scope.objectsName = "reports";
    
    $scope.mimetypes = new Object();    
    $scope.mimetypes["Excel"] = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";           
    $scope.mimetypes["Word"] = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    $scope.mimetypes["TXT"] = "text/plain";
    $scope.mimetypes["PDF"] = "application/pdf";
    $scope.mimetypes["RTF"] = "application/rtf";
    $scope.get = saveEditDelete.get($http, '/CMS/manageFile/reports.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/manageFile/save/:object.htm', $scope);
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.reports = $scope.initData.reports;
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
            saveEditDelete.saveOldData($scope,report);
            $scope.selected = report;
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