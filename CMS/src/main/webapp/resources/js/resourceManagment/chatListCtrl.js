function ChatListCtrl($scope, $http, $interval) {

    $scope.messages = "";
    $scope.users = "";
    $scope.test = "aaaa";

    var var_1 = $interval(function () {
        //$scope.test += "1";
        $http.get("/CMS/messages/get.htm").success(function (returnData) {
            $scope.messages = returnData.messages;
            $scope.users = returnData.users;
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
    
    $scope.getUserName = function(msg) {
      
        for (var i = 0; i<$scope.users.length; i++) {
            if($scope.users[i].id == msg.from_userid) {
                return $scope.users[i].login;
            }
        }
    };



}
