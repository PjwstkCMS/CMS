<%-- 
    Document   : jsonOperations
    Created on : 2013-10-28, 17:19:08
    Author     : Sergio
--%>

<%@tag description="EditTable" pageEncoding="UTF-8" dynamic-attributes="atr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<hr/>
    <table>
            <tr ng-repeat="col in ${atr.map} ">
                <td ng-show="col[0] == 'email'">
                    {{columnDescription(col[0])}}: <input type="text" required="required" ng-pattern="/^[.A-Za-z0-9-_]+@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/" ng-model="${atr.object}.email"/>
                </td>
                <td ng-show="col[0] == 'forename'">
                    {{columnDescription(col[0])}}: <input type="text" maxlength="21" required="required" ng-pattern="/^[A-Z]+[a-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" ng-model="${atr.object}.forename"/>
                </td>
                <td ng-show="col[0] == 'surname'">
                    {{columnDescription(col[0])}}: <input type="text" required="required" ng-pattern="/^[A-Z]+[a-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+(-[A-Z]+[a-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+){0,1}$/" ng-model="${atr.object}.surname"/>
                </td>
                <td ng-show="col[0] == 'name'">
                    {{columnDescription(col[0])}}: <input type="text" required="required" ng-model="${atr.object}.name"/>
                </td>
                <td ng-show="col[0] == 'phone'">
                    {{columnDescription(col[0])}}: <input type="text" ng-pattern="/^[0-9]+$/" maxlength="9" required="required" ng-model="${atr.object}.phone"/>
                </td>
                <td ng-show="col[0] == 'pesel'">
                    {{columnDescription(col[0])}}: <input type="text" ng-pattern="/^[0-9]+$/" required="required"  ng-minlength="11" maxlength="11" ng-model="${atr.object}.pesel"/>
                </td>
                <td ng-show="col[0] == 'country'">
                    {{columnDescription(col[0])}}: <input type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="${atr.object}.country"/>
                </td>
                <td ng-show="col[0] == 'city'">
                    {{columnDescription(col[0])}}: <input type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="${atr.object}.city"/>
                </td>
                <td ng-show="col[0] == 'streetName'">
                    {{columnDescription(col[0])}}: <input type="text" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="${atr.object}.streetName"/>
                </td>
                <td ng-show="col[0] == 'streetNumber'">
                    {{columnDescription(col[0])}}: <input type="text" ng-pattern="/^[0-9]+[a-z]{0,1}$/" required="required" ng-model="${atr.object}.streetNumber"/>
                </td>
                <td ng-show="col[0] == 'apartmentNumber'">
                    {{columnDescription(col[0])}}: <input type="text" ng-pattern="/^[0-9]+$/" required="required" ng-model="${atr.object}.apartmentNumber"/>    
                </td>
                <td ng-show="col[0] == 'postalCode'">
                    {{columnDescription(col[0])}}: <input type="text" maxlength="6" ng-pattern="/^[0-9]{2}-[0-9]{3}$/" required="required" ng-model="${atr.object}.postalCode"/>    
                </td>
                <td ng-show="col[0] == 'description'">
                    {{columnDescription(col[0])}}: <input required="required" maxlength="200" type="text" ng-model="${atr.object}.description"/> 
                </td>
                <td ng-show="col[0] == 'value'">
                    {{columnDescription(col[0])}}: <input required="required" maxlength="200" type="text" ng-model="${atr.object}.value"/> 
                </td>
                <td ng-show="col[0] == 'price'">
                    {{columnDescription(col[0])}}: <input required="required" maxlength="45" ng-pattern="/^[0-9]+$/" type="text" ng-model="${atr.object}.price"/> 
                </td>
                <td ng-show="col[0] == 'salary'">
                    {{columnDescription(col[0])}}: <input required="required" maxlength="45" ng-pattern="/^[0-9]+$/" type="text" ng-model="${atr.object}.salary"/> 
                </td>
                <td ng-show="col[0] == 'startDate'">
                    {{columnDescription(col[0])}}: <input required="required" ng-pattern="/^((19|20)[0-9]{2}[\-/](0[1-9]|1[0-2])[\-/](0[1-9]|[12][0-9]|3[01]))*$/" type="text" ng-model="${atr.object}.startDate"/> 
                </td>
                <td ng-show="col[0] == 'closeDate'">
                    {{columnDescription(col[0])}}: <input required="required" ng-pattern="/^((19|20)[0-9]{2}[\-/](0[1-9]|1[0-2])[\-/](0[1-9]|[12][0-9]|3[01]))*$/" type="text" ng-model="${atr.object}.closeDate"/> 
                </td>
                <td ng-show="col[0] == 'finalisationDate'">
                    {{columnDescription(col[0])}}: <input required="required" ng-pattern="/^((19|20)[0-9]{2}[\-/](0[1-9]|1[0-2])[\-/](0[1-9]|[12][0-9]|3[01]))*$/" type="text" ng-model="${atr.object}.finalisationDate"/> 
                </td>
                <td ng-show="col[0] == 'dateTo'">
                    {{columnDescription(col[0])}}: <input required="required" ng-pattern="/^((19|20)[0-9]{2}[\-/](0[1-9]|1[0-2])[\-/](0[1-9]|[12][0-9]|3[01]))*$/" type="text" ng-model="${atr.object}.dateTo"/> 
                </td>
                <td ng-show="col[0] == 'dateFrom'">
                    {{columnDescription(col[0])}}: <input required="required" ng-pattern="/^((19|20)[0-9]{2}[\-/](0[1-9]|1[0-2])[\-/](0[1-9]|[12][0-9]|3[01]))*$/" type="text" ng-model="${atr.object}.dateFrom"/> 
                </td>
                
                <td ng-show="col[0] == 'id'">
                    {{columnDescription(col[0])}}: <input required="required" disabled="true" type="text" ng-model="${atr.object}.id"/> 
                </td>
                <td ng-show="col[0] == 'cardId'">
                    {{columnDescription(col[0])}}: <input required="required" type="text" ng-model="${atr.object}.cardId"/> 
                </td>
                <td ng-show="col[0] == 'customerId'">
                    {{columnDescription(col[0])}}: 
                    <select required="required" ng-model="${atr.object}.customerId">
                        <option ng-repeat="cus in customers" value="{{cus.id}}"  ng-selected="${atr.object}.customerId == cus.id">{{cus.forename}} {{cus.surname}}</option>
                    </select>
                </td>
                <td ng-show="col[0] == 'employeeId'">
                    {{columnDescription(col[0])}}: 
                    <select required="required" ng-model="${atr.object}.employeeId">
                        <option ng-repeat="emp in employees" value="{{emp.id}}"  ng-selected="${atr.object}.employeeId == emp.id">{{emp.forename}} {{emp.surname}}</option>
                    </select>
                </td>
                <td ng-show="col[0] == 'managerId'">
                    {{columnDescription(col[0])}}: 
                    <select required="required" ng-model="${atr.object}.managerId">
                        <option ng-repeat="emp in employees" value="{{emp.id}}"  ng-selected="${atr.object}.managerId == emp.id">{{emp.forename}} {{emp.surname}}</option>
                    </select>
                </td>
                <td ng-show="col[0] == 'dictId'">
                    {{columnDescription(col[0])}}: 
                    <select required="required" ng-model="${atr.object}.dictId">
                        <option ng-repeat="dict in dictionaries" value="{{dict.id}}"  ng-selected="${atr.object}.dictId == dict.id">{{dict.description}}</option>
                    </select>
                </td>
                <td ng-show="col[0] == 'dictTypeId'">
                    {{columnDescription(col[0])}}: 
                    <select required="required" ng-model="${atr.object}.dictTypeId">
                        <option ng-repeat="dict in dictTypes" value="{{dict.id}}"  ng-selected="${atr.object}.dictTypeId == dict.id">{{dict.description}}</option>
                    </select>
                </td>
                <td ng-show="col[0] == 'companyId'">
                    {{columnDescription(col[0])}}: 
                    <select required="required" ng-model="${atr.object}.companyId">
                        <option ng-repeat="comp in companies" value="{{comp.id}}"  ng-selected="${atr.object}.companyId == comp.id">{{comp.name}}</option>
                    </select>
                </td>
                <td ng-show="col[0] == 'departmentId'">
                    {{columnDescription(col[0])}}: 
                    <select required="required" ng-model="${atr.object}.departmentId">
                        <option ng-repeat="dep in departments" value="{{dep.id}}"  ng-selected="${atr.object}.departmentId == dep.id">{{dep.name}}</option>
                    </select>
                </td>
                <td ng-show="col[0] == 'positionId'">
                    {{columnDescription(col[0])}}: 
                    <select required="required" ng-model="${atr.object}.positionId">
                        <option ng-repeat="pos in positions" value="{{pos.id}}"  ng-selected="${atr.object}.positionId == pos.id">{{pos.name}}</option>
                    </select>
                </td>
            </tr>
    </table>