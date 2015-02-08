<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/userListCtrl.js"></script>
        <div ng-controller="UserListCtrl">
            <t:dataTable/>
            <div ng-show="editMode">
                <t:editTable map="editValues" object="selected"/>
            </div>
            <hr/>
            <div style="height: 50px">
                <input ng-show="!editMode && !selected" type="button" ng-click="create()" value="NOWE">
                <input ng-show="editMode" type="button" ng-click="save()" value="ZAPISZ">
                <input ng-show="editMode" type="button" ng-click="cancel()" value="ANULUJ">
                <input ng-show="selected.id" type="button" ng-click="delete()" value="USUŃ"/>
                <input ng-show="selected.id" type="button" ng-click="resetPass()" value="Reset Password"/>
            </div>

            
            
            
        </div>
    </jsp:body>
</t:general>