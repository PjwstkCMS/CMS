function CustomerListCtrl($scope, $http, saveEditDelete, pagination) {

    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    $scope.selectedCompany = 'test';
    $scope.selectedCompanyAddress = "";

    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.customers = "";
    $scope.privileges = "";
    $scope.editMode = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";

    $scope.objectsName = "customers";
    $scope.attributes = [];
    $scope.attributes[0] = 'forename';
    $scope.attributes[1] = 'surname';
    $scope.attributes[2] = 'phone';
    $scope.attributes[3] = 'email';
    $scope.columns = {
        'forename': "Imie",
        'surname': "Nazwisko",
        'phone': "Telefon",
        'email': "Email"
    };
    
    $scope.contractAttributes = [];
    $scope.contractAttributes[0] = 'id';
    $scope.contractAttributes[1] = 'employeeId';
    $scope.contractAttributes[2] = 'startDate';
    $scope.contractAttributes[3] = 'closeDate';
    $scope.contractAttributes[4] = 'finalisationDate';
    $scope.contractAttributes[5] = 'description';
    $scope.contractAttributes[6] = 'price';
    $scope.contractColumns= {
        'id': "Id umowy",
        'employeeId': "Pracownik",
        'startDate': "Data rozpoczęcia",
        'closeDate': "Planowana data zakończenia",
        'finalisationDate': "Data zakończenia",
        'description': "Opis",
        'price': "Cena"
    };
    $scope.contractColumnClasses = {
        'id' : "kontrakt-id",
        'employeeId': "kontrakt-employeeId",
        'startDate': "kontrakt-startDate",
        'closeDate': "kontrakt-closeDate",
        'finalisationDate': "kontrakt-finalisationDate",
        'description': "kontrakt-description",
        'price': "kontrakt-price"
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
            $scope.contracts = $scope.initData.contracts;
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
                    $scope.selectedCompanyAddress = $scope.selectedCompany.addresses[0];
                }
            }
        }
    }
    
    $scope.checkCustomerId = function(con){
        if(con.customerId == $scope.selected.id) {
            return true;
        }
        return false;
    };

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