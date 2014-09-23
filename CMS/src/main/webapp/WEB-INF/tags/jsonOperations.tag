<%-- 
    Document   : jsonOperations
    Created on : 2013-10-28, 17:19:08
    Author     : Sergio
--%>

<%@tag description="SaveEditDelete" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<div ng-show="!editMode && !selected">
    <input type="button" ng-click="create()" value="NOWE">
</div>
<div ng-show="editMode || selected">
    <input type="button" ng-click="save()" value="ZAPISZ">
    <input type="button" ng-click="cancel()" value="ANULUJ">
</div>
