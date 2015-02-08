<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/employeeListCtrl.js"></script>
           
        <div ng-controller="EmployeeListCtrl">            
            <t:dataTable/>
            <div ng-show="editMode">
                <t:editTable map="editValues" object="selected"/>
            </div>
            <div ng-init="archivable=true" ng-hide="selected && selected.id">
                <t:jsonOperations/>
            </div>
        </div>
    </jsp:body>
</t:general>
