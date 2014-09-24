function CustomerListCtrl($scope, $http, saveEditDelete, pagination) {

    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    $scope.selectedCompany = 'test';

    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.customers = "";
    $scope.privileges = "";
    $scope.editMode = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";

    $scope.objectsName = "customers";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'surname';
    $scope.attributes[2] = 'phone';
    $scope.attributes[3] = 'email';
    $scope.columns = {
        'name': "Imię",
        'surname': "Nazwisko",
        'phone': "Telefon",
        'email': "Email"
    };
    $scope.columnClasses = {
        'name': "klient-name",
        'surname': "klient-surname",
        'phone': "klient-phone",
        'email': "klient-email"
    };

    $scope.get = saveEditDelete.get($http, '/CMS/customer/customers.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/customerSave/:object.htm', $scope);
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.customers = $scope.initData.customers;
            $scope.companies = $scope.initData.companies;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            $scope.selected = "";
            $scope.selectedCompany = "";
        } else {
            $scope.selected = object;
            for (var i = 0; i<$scope.companies.length; i++) {
                if($scope.companies[i].id == $scope.selected.companyId) {
                    $scope.selectedCompany = $scope.companies[i];
                }
            }
        }
    }

    $scope.edit = function() {
        $scope.editMode = true;
    };

    $scope.cancel = function() {
        $scope.editMode = false;
        $scope.selected = "";
    };

    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/customer/delete/:object.htm', $scope);
    };


}