<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/employeeListCtrl.js"></script>
        <h1>Employee!</h1>        
        <div ng-controller="EmployeeListCtrl">            
            <t:dataTable/>
            <div ng-show="editMode && newRecord">
                <t:editTable map="editValues" object="selected"/>
            </div>
        </div>
    </jsp:body>
</t:general>
