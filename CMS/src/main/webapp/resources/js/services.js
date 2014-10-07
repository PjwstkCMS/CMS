var cmsModule = angular.module('cms', []);

cmsModule.factory('saveEditDelete', function() {
    return {
        save: function($http, link, $scope) {
            if ($scope.selected.id == null || $scope.selected.id < 1) {
                $scope[$scope.objectsName].push($scope.selected);
            }

            return $http.post(
                    link,
                    {object: $scope.selected}).success(function(returnId) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja zapisywania udana"
                
                if(returnId!=null) {
                    $scope.selected.id = returnId.id;
                    if(returnId.contactPersonId!=null){
                        $scope.selected.contactPersonId = returnId.contactPersonId;
                    }
                    if(returnId.addressId!=null){
                        $scope.selected.address.id = returnId.addressId;
                        $scope.selected.addressId = returnId.addressId;
                    }
                }                
                $scope.selected = null;
                $scope.editMode = false;
            }).error(function(error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja zapisywania nie udana"
                alert(error);
            });
        },
        get: function($http, link, $scope) {
            return $http.get(link).success(function(returnData) {
                $scope.status = null;
                $scope.initData = returnData;
                $scope[$scope.objectsName] = returnData[$scope.objectsName];
                $scope.objects = returnData[$scope.objectsName];
                //alert(returnData.privilegeKeys[0].id);
                return "Success";
            }).error(function(error) {
                $scope.status = "Błąd";
                return null;
            });
        },
        remove: function($http, link, $scope) {
            return $http.post(
                    link,
                    {object: $scope.selected}).success(function() {
                var index;
                for (var i = 0; i < $scope[$scope.objectsName].length; i++) {
                    if ($scope[$scope.objectsName][i].id == $scope.selected.id) {
                        index = i;
                    }
                }
                $scope[$scope.objectsName].splice(index, 1);
                $scope.selected = "";
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja usuwania udana"
            }).error(function(error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja zapisywania nie udana"
            });
        },
        saveAddress: function($http, link, $scope) {
            if ($scope.addressSelector.id == null || $scope.addressSelector.id < 1) {
                $scope.selected.addresses.push($scope.addressSelector);
            }

            return $http.post(
                    link,
                    {object: $scope.addressSelector}).success(function(returnId) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Adres zapisany"
                
                if(returnId!=null) {
                    $scope.addressSelector.id = returnId.id;
                }                
                $scope.addressSelector = null;
                $scope.editMode = false;
            }).error(function(error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Błąd przy dodawaniu adresu"
                alert(error);
            });
        },
        removeAddress: function($http, link, $scope) {
            return $http.post(
                    link,
                    {object: $scope.addressSelector}).success(function() {
                var index;
                for (var i = 0; i < $scope.selected.addresses.length; i++) {
                    if ($scope.selected.addresses.id == $scope.addressSelector.id) {
                        index = i;
                    }
                }
                $scope.selected.addresses.splice(index, 1);
                $scope.addressSelector = "";
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Adres usunięty"
            }).error(function(error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Błąd przy usuwaniu adresu"
            });
        }
    };
});

cmsModule.factory('pagination', function() {
    return {
        indexOnPage: function($scope) {
            return function(index)
            {
                if (index <= $scope.pageMax && index >= $scope.pageMin) {
                    return true;
                } else {
                    return false;
                }
            };
        },
        pageMaxSmallerThenSize: function($scope) {
            return function() {
                //alert($scope[$scope.objectsName].length);
                if ($scope[$scope.objectsName].length <= 10) {
                    return false;
                }
                if ($scope.pageMax > $scope[$scope.objectsName].length) {
                    return false;
                } else {
                    return true;
                }
            };
        }
    };
});

cmsModule.factory('columnDesc', function() {
    return {
        get:function(object){
            descriptions = {
                'id': "ID",
                'employeeId': "Pracownik",
                'customerId' : "Klient",
                'managerId' : "Manager",
                'companyId' : "Firma",
                'departmentId' : "Departement",
                'cardId' : "Numer karty",
                'dictTypeId' : "Rodzaj słownika",
                'dictId' : "Dodatkowy Opis",
                'positionId' : "Stanowisko",
                
                'forename': "Imie",
                'surname': "Nazwisko",
                'phone': "Telefon",
                'email': "Email",
                'pesel' : "Pesel",
                'groupName' : "Grupa",
                'login' : "Login",
                'salary' : "Pensja",
                
                'name' : "Nazwa",
                'description': "Opis",
                'price': "Cena",
                'mimeType' : "Rodzaj pliku",
                'value' : "Wartość",
                'code' : "Kod",
                
                'startDate': "Data rozpoczęcia",
                'closeDate': "Planowana data zakończenia",
                'finalisationDate': "Data zakończenia",
                
                'country': "Kraj",
                'city': "Miasto",
                'streetName': "Ulica",
                'streetNumber': "Numer",
                'apartmentNumber': "Lokal",
                'postalCode': "Kod"
                               
            };
            
            for (desc in descriptions) {  
                if(desc == object) {
                    return descriptions[desc];
                }                
            }
            return "N/A";
        }
    };
});