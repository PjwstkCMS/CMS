<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/groupListCtrl.js"></script>
  
        <div ng-controller="GroupListCtrl">
            <t:dataTable/>
            <div ng-show="editMode">
                <t:editTable map="editValues" object="selected"/>
            </div>
            <t:jsonOperations/>
            <table>
                <tr>
                    <th ng-repeat="privA in privilegeAttributes" ng-hide="privA.substring(0, 1) == '%'">
                        {{$parent.columnDescription(privA)}}
                    </th>   
                </tr>
                <tbody>
                    <tr ng-repeat="priv in privilegeKeys">
                            <td ng-repeat="privA in privilegeAttributes">
                                {{priv[privA]}}
                            </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:general>