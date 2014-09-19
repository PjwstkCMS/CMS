function ManageFileCtrl($scope, $http, saveEditDelete, pagination) {
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
    $scope.columns = {
      'description' : "Opis",
      'name' : "Nazwa",
      'mimeType' : "Rodzaj pliku"
    };
    $scope.selected = "";
    $scope.reports = "";
    $scope.objectsName = "reports";
    
    $scope.mimetypes = new Object();    
    $scope.mimetypes["Excel"] = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";           
    $scope.mimetypes["Word"] = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    $scope.mimetypes["TXT"] = "text/plain";
    $scope.mimetypes["PDF"] = "application/pdf";
    $scope.get = saveEditDelete.get($http, '/CMS/fileListUpload/reports.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/fileListUpload/save/:object.htm', $scope);
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
            $scope.selected = "";
        } else {
            $scope.selected = report;
        }
    }

    $scope.edit = function() {
        $scope.editMode = true;
    };

    $scope.cancel = function() {
        $scope.editMode = false;
    };

    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };


}