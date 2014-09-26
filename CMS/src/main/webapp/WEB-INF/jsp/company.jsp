<%-- 
    Document   : contract
    Created on : 2014-08-30, 14:58:34
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/companyListCtrl.js"></script>
        <h1>Company!</h1>
        <h3>${server}</h3>
        <div ng-controller="CompanyListCtrl">
            <t:dataTable/>
            <div id="companyTable" ng-show="selected">
                <h3>
                    Adresy Firmy:
                </h3>
                <table>
                    <tr>
                        <th> 
                            # 
                        </th>
                        <th ng-repeat="adatr in addressAttributes" ng-hide="adatr.substring(0, 1) == '%'" class = "{{addressColumnClasses[adatr]}}">
                            <a ng-click="$parent.orderColumn = adatr;
                            $parent.reverse = !$parent.reverse">{{$parent.addressColumns[adatr]}}</a>
                        </th>   
                    </tr>
                    <tbody>
                        <tr ng-repeat="add in selected.addresses">
                                <td class="numer">
                                    {{$index + 1}}
                                </td>
                                <td ng-repeat="adatr in addressAttributes">
                                    {{add[adatr]}}
                                </td>
                        </tr>
                    </tbody>
                </table>
                
            </div>
            
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>