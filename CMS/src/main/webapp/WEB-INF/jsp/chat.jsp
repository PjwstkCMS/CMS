<div ng-controller="ChatListCtrl">        
    <input type="text" ng-model="messageFilter"/>
    <p ng-hide="msg.booleanRead" ng-repeat="msg in messages| filter:messageFilter">
        {{msg.timestamp}}<br/>
        {{msg.from}} napisa?:<br/>
        {{msg.content}}
        <input ng-click="markAsRead(msg)" ng-disabled="readButtonEnabled" value="Przeczytane" type="button" />        
    </p>
    {{readButtonEnabled}}
</div>
