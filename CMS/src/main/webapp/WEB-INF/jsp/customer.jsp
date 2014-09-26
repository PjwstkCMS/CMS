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
            <div id="companyTable" ng-show="selected">
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
                <table>
                    <tr>
                        <th> 
                            # 
                        </th>
                        <th ng-repeat="cattr in contractAttributes" ng-hide="cattr.substring(0, 1) == '%'" class = "{{contractColumnClasses[cattr]}}">
                            <a ng-click="$parent.orderColumn = cattr;
                            $parent.reverse = !$parent.reverse">{{$parent.contractColumns[cattr]}}</a>
                        </th>   
                    </tr>
                    <tbody>
                        <tr ng-repeat="con in contracts" ng-show="checkCustomerId(con)">
                                <td class="numer">
                                    {{$index + 1}}
                                </td>
                                <td ng-repeat="cattr in contractAttributes">
                                    {{con[cattr]}}
                                </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <div ng-show="editMode || selected">
                <table>
                    <tr>
                        <td>
                            Imię: <input type="text" ng-model="selected.name"/>
                        </td>
                        <td>
                            Nazwisko: <input type="text" ng-model="selected.surname"/>
                        </td>
                        <td>
                            Email: <input type="text" ng-model="selected.email"/>
                        </td>
                        <td>
                            Telefon: <input type="text" ng-model="selected.phone"/>
                        </td>
                    </tr>
                </table>
            </div>
            <t:jsonOperations/>
        </div>
        </jsp:body>
    </t:general>
