<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/userListCtrl.js"></script>
        <h1>User!</h1>
        <div ng-controller="UserListCtrl">
            <t:dataTable/>
            <input ng-show="selected.id" type="button" ng-click="delete()" value="USUŃ"/>
        </div>
    </jsp:body>
</t:general>