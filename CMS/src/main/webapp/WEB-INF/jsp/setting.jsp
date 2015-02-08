<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/systemConfigCtrl.js"></script>
        <div ng-controller="SystemConfigCtrl">
            <t:dataTable/>
            <t:editTable map="editValues" object="selected"/>
            <input type="button" ng-click="save()" value="ZAPISZ">
            <input type="button" ng-click="defaultSettings()" value="Default settings">
        </div>
    </jsp:body>
</t:general>