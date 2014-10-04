<%-- 
    Document   : customer
    Created on : 2014-08-30, 14:58:55
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/customerListCtrl.js"></script>
        <h1>Customer!</h1>
        <h3>${server}</h3>
        <div ng-controller="CustomerListCtrl">            
            <t:dataTable/>
            <div id="companyTable" ng-show="selected && !editMode">
                <h3>
                    Dane firmy:
                </h3>
                <table>
                    <tr>
                        <td>
                            {{selectedCompany.name}}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            {{selectedCompanyAddress.city}} {{selectedCompanyAddress.streetName}}
                        </td>
                        <td>
                            {{selectedCompanyAddress.streetNumber}}/m.{{selectedCompanyAddress.apartmentNumber}}
                        </td>
                        <td>
                            {{selectedCompanyAddress.postalCode}} {{selectedCompanyAddress.country}}
                        </td>
                    </tr>
                </table>
                <h3>
                    Aktualne umowy:
                </h3>
                <%-- <table>
                    <tr>
                        <th> 
                            # 
                        </th>
                        <th ng-repeat="cattr in contractAttributes" ng-hide="cattr.substring(0, 1) == '%'">
                            <a ng-click="$parent.orderColumn = cattr;
                            $parent.reverse = !$parent.reverse">{{$parent.columnDescription(cattr)}}</a>
                        </th>   
                    </tr>
                    <tbody>
                        <tr ng-class="{selectedTableRow: con == contractSelector}" ng-repeat="con in contracts" ng-show="checkCustomerId(con)">
                                <td class="numer">
                                    {{$index + 1}}
                                </td>
                                <td ng-repeat="cattr in contractAttributes" ng-click="$parent.contractSelect(con)">
                                    {{con[cattr]}}
                                </td>
                        </tr>
                    </tbody>
                </table>--%>
                <select ng-model="contractSelector" ng-options="con.id
                        for con in selectedContracts"></select><br>
                <table>
                    <tr>
                        <th ng-repeat="catr in contractAttributes">
                            {{$parent.columnDescription(catr)}}
                        </th>   
                    </tr>
                    <tbody>
                        <tr>
                            <td ng-repeat="attr in contractAttributes">
                                {{contractSelector[attr]}}
                            </td>
                        </tr>
                    </tbody>
                </table>
                <%--<div ng-show="contractSelector">
                    <t:editTable map="contractValues" object="contractSelector"/>
                </div>--%>
                
            </div>
                <div ng-show="editMode">
                    <t:editTable map="editValues" object="selected"/>
                </div>
            
            <t:jsonOperations/>
        </div>
        </jsp:body>
    </t:general>
