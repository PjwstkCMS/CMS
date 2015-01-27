<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/departmentListCtrl.js"></script>
        
        <div ng-controller="DepartmentListCtrl">
            <t:dataTable/>
            <div ng-show="editMode && newRecord">
                <t:editTable map="editValues" object="selected"/>
                <t:editTable map="addressValues" object="selected.address"/>
            </div>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>