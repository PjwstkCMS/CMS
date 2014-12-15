function ChatListCtrl($scope, $http, $interval) {

    $scope.messages = "";
    $scope.test = "aaaa";

    var var_1 = $interval(function () {
        //$scope.test += "1";
        $http.get("/CMS/messages/get.htm").success(function (returnData) {
            $scope.messages = returnData.messages;
            //alert(returnData.messages);
        });
    }, 1000);

    $scope.markAsRead = function (msg) {
        msg.read = "0";
        msg.booleanRead = false;
        $http.post(
                "/CMS/messages/:message.htm",
                {message: msg}).success(function (returnId) {            
        })
    };



}
