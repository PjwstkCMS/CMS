<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/terminalListCtrl.js"></script>
        <h1>Terminal!</h1>
        <div ng-controller="TerminalListCtrl">
            <t:dataTable/>
        </div>
    </jsp:body>
</t:general>