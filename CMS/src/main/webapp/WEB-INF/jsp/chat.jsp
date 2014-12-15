<div ng-controller="ChatListCtrl">    
    <p ng-hide="msg.booleanRead" ng-repeat="msg in messages">
        {{msg.timestamp}} {{msg.from_userid} napisa?:<br/>
        {{msg.content}}
        {{msg.read}}
        {{msg.booleanRead}}
        <input ng-click="markAsRead(msg)" value="Przeczytane" type="button"/>
    </p>
</div>
