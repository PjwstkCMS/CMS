<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/reportPrintCtrl.js"></script>
        <div ng-init="reportDownload = true" ng-controller="ReportPrintCtrl">
            <div class="top-right">
                <t:dataTable/>
            </div>
            <div ng-show="selected.formCode" ng-include="selected.formCode">
            </div>
            <div ng-show="selected.printCode">
                <form action="{{selected.printCode}}.htm" method="GET">            
                    <input type="submit" value="Pobierz"/>
                </form>
            </div>
    </div>
</jsp:body>
</t:general>