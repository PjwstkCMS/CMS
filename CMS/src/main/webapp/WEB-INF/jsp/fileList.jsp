<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/fileListCtrl.js"></script>
        
        <div ng-controller="FileListCtrl">
            <t:dataTable/>
        </div>
    </jsp:body>
</t:general>