<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/privilegeKeyListCtrl.js"></script>
        <h1>Employee!</h1>        
        <div ng-controller="PrivilegeKeyListCtrl">
            <t:dataTable/>
        </div>        
    </jsp:body>
</t:general>