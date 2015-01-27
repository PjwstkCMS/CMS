<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div id="czat" ng-controller="ChatListCtrl">
    
    
       
        <input ng-show="showFilter" type="text" placeholder="nazwa użytkownika" ng-model="messageFilter"/>
        
    
    
    <div class="czat-wiadomosci">
        <div ng-hide="msg.booleanRead && !showRead" ng-repeat="msg in messages| filter:messageFilter">
            
            <h1>{{msg.from}} napisał:</h1>
            <p class="msg-time">{{msg.timestamp}}</p>
            <p class="msg-content">{{msg.content}}</p>
           
        
        
        
        
       <input ng-hide="msg.booleanRead" ng-click="markAsRead(msg)" ng-disabled="readButtonEnabled" value="Przeczytane" type="button" />  
        {{readButtonEnabled}}
    
    </div>

    <input id="stay-logged" type="checkbox" ng-init="showFilter = true" ng-model="showRead"/>
        <label for="stay-logged"><span></span>Archiwalne</label>
</div>
