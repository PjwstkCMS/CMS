<%-- 
    Document   : jsonOperations
    Created on : 2013-10-28, 17:19:08
    Author     : Sergio
--%>

<%@tag description="SaveEditDelete" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<input ng-show="!editMode && !selected" type="button" ng-click="create()" value="NOWE">
<input ng-show="editMode || selected" type="button" ng-click="save()" value="ZAPISZ">
<input ng-show="editMode || selected" type="button" ng-click="cancel()" value="ANULUJ">
<input ng-show="selected.id" type="button" ng-click="delete()" value="USUŃ"/>
