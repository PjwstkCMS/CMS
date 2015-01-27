<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<div ng-controller="ChatListCtrl">        
    <input type="text" ng-model="messageFilter"/>
    Pokaż przecztane: <input type="checkbox" ng-model="showRead"/>
        <p ng-hide="msg.booleanRead && !showRead" ng-repeat="msg in messages| filter:messageFilter">
        {{msg.timestamp}}<br/>
        {{msg.from}} napisał:<br/>
        {{msg.content}}
        <input ng-hide="msg.booleanRead" ng-click="markAsRead(msg)" ng-disabled="readButtonEnabled" value="Przeczytane" type="button" />        
    </p>
    {{readButtonEnabled}}
</div>
