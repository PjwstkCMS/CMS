<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/reportPrintCtrl.js"></script>
        <div ng-init="reportDownload = true" ng-controller="ReportPrintCtrl">
            <div class="top-right">
                <div class="more-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()" id="flip"></div>
                <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>

            </div>
            <t:dataTable/>
        </div>
    </jsp:body>
</t:general>