<%@tag description="EditTable" pageEncoding="UTF-8" dynamic-attributes="atr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<div class="wszystkie-pola">
<div ng-show="checkTable(${atr.map}, 'email')">
    {{columnDescription('email')}}: <input type="text" required="required" ng-pattern="/^[.A-Za-z0-9-_]+@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/" ng-model="${atr.object}.email"/>
</div>
<div ng-show="checkTable(${atr.map},'forename')">
    {{columnDescription('forename')}}: <input type="text" maxlength="21" required="required" ng-pattern="/^[A-Z]+[a-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" ng-model="${atr.object}.forename"/>
</div>
<div ng-show="checkTable(${atr.map},'surname')">
    {{columnDescription('surname')}}: <input type="text" required="required" ng-pattern="/^[A-Z]+[a-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+(-[A-Z]+[a-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+){0,1}$/" ng-model="${atr.object}.surname"/>
</div>
<div ng-show="checkTable(${atr.map},'name')">
    {{columnDescription('name')}}: <input type="text" required="required" ng-model="${atr.object}.name"/>
</div>
<div ng-show="checkTable(${atr.map},'phone')">
    {{columnDescription('phone')}}: <input type="text" ng-pattern="/^[0-9]+$/" maxlength="9" required="required" ng-model="${atr.object}.phone"/>
</div>
<div ng-show="checkTable(${atr.map},'pesel')">
    {{columnDescription('pesel')}}: <input type="text" ng-pattern="/^[0-9]+$/" required="required"  ng-minlength="11" maxlength="11" ng-model="${atr.object}.pesel"/>
</div>
<div ng-show="checkTable(${atr.map},'country')">
    {{columnDescription('country')}}: <input type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="${atr.object}.country"/>
</div>
<div ng-show="checkTable(${atr.map},'city')">
    {{columnDescription('city')}}: <input type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="${atr.object}.city"/>
</div>
<div ng-show="checkTable(${atr.map},'streetName')">
    {{columnDescription('streetName')}}: <input type="text" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="${atr.object}.streetName"/>
</div>
<div ng-show="checkTable(${atr.map},'streetNumber')">
    {{columnDescription('streetNumber')}}: <input type="text" ng-pattern="/^[0-9]+[a-z]{0,1}$/" required="required" ng-model="${atr.object}.streetNumber"/>
</div>
<div ng-show="checkTable(${atr.map},'apartmentNumber')">
    {{columnDescription('apartmentNumber')}}: <input type="text" ng-pattern="/^[0-9]+$/" required="required" ng-model="${atr.object}.apartmentNumber"/>    
</div>
<div ng-show="checkTable(${atr.map},'postalCode')">
    {{columnDescription('postalCode')}}: <input type="text" maxlength="6" ng-pattern="/^[0-9]{2}-[0-9]{3}$/" required="required" ng-model="${atr.object}.postalCode"/>    
</div>
<div ng-show="checkTable(${atr.map},'description')">
    {{columnDescription('description')}}: <input required="required" maxlength="200" type="text" ng-model="${atr.object}.description"/> 
</div>
<div ng-show="checkTable(${atr.map},'value')">
    {{columnDescription('value')}}: <input required="required" maxlength="200" type="text" ng-model="${atr.object}.value"/> 
</div>
<div ng-show="checkTable(${atr.map},'price')">
    {{columnDescription('price')}}: <input required="required" maxlength="45" ng-pattern="/^[0-9]+$/" type="text" ng-model="${atr.object}.price"/> 
</div>
<div ng-show="checkTable(${atr.map},'salary')">
    {{columnDescription('salary')}}: <input required="required" maxlength="45" ng-pattern="/^[0-9]+$/" type="text" ng-model="${atr.object}.salary"/> 
</div>
<div ng-show="checkTable(${atr.map},'startDate')">
    {{columnDescription('startDate')}}: <input ng-Readonly="true" type="text" show-button-bar="false" datepicker-popup="dd-MM-yyyy" ng-model="${atr.object}.startDate" max="${atr.object}.closeDate" ng-required="true" />
</div>
<div ng-show="checkTable(${atr.map},'closeDate')">
    {{columnDescription('closeDate')}}: <input ng-Readonly="true" type="text" show-button-bar="false" datepicker-popup="dd-MM-yyyy" ng-model="${atr.object}.closeDate" min="${atr.object}.startDate" ng-required="true" />
</div>
<div ng-show="checkTable(${atr.map},'finalisationDate')">
    {{columnDescription('finalisationDate')}}: <input ng-Readonly="true" type="text" show-button-bar="false" datepicker-popup="dd-MM-yyyy" ng-model="${atr.object}.finalisationDate" min="${atr.object}.startDate" ng-required="true" />
</div>
<div ng-show="checkTable(${atr.map},'dateTo')">
    {{columnDescription('dateTo')}}: <input ng-Readonly="true" type="text" show-button-bar="false" datepicker-popup="dd-MM-yyyy" ng-model="${atr.object}.dateTo" min="${atr.object}.dateFrom" ng-required="true" />
</div>
<div ng-show="checkTable(${atr.map},'dateFrom')">
    {{columnDescription('dateFrom')}}: <input ng-Readonly="true" type="text" show-button-bar="false" datepicker-popup="dd-MM-yyyy" ng-model="${atr.object}.dateFrom" max="${atr.object}.dateTo" ng-required="true" /> 
</div>
<div ng-show="checkTable(${atr.map},'login')">
    {{columnDescription('login')}}: <input required="required" type="text" ng-model="${atr.object}.login"/> 
</div>
<div ng-show="checkTable(${atr.map},'password')">
    {{columnDescription('password')}}: <input required="required" type="text" ng-model="${atr.object}.password"/> 
</div>
<div ng-show="checkTable(${atr.map},'employeIdNum')">
    {{columnDescription('employeIdNum')}}: <input required="required" type="text" ng-model="${atr.object}.employeeId" ng-change="selectAction('employeeIdNum')"/> 
</div>
<div ng-show="checkTable(${atr.map},'mac')">
    {{columnDescription('mac')}}: <input required="required" ng-pattern="/^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$/" maxlength="17" type="text" ng-model="${atr.object}.mac"/> 
</div>

<div ng-show="checkTable(${atr.map},'id')">
    {{columnDescription('id')}}: <input required="required" disabled="true" type="text" ng-model="${atr.object}.id"/> 
</div>
<div ng-show="checkTable(${atr.map},'cardId')">
    {{columnDescription('cardId')}}: <input required="required" type="text" ng-model="${atr.object}.cardId"/> 
</div>
<div ng-show="checkTable(${atr.map},'customerId')">
    {{columnDescription('customerId')}}: 
    <select required="required" ng-model="${atr.object}.customerId" ng-change="selectAction('customerId')">
        <option ng-repeat="cus in customers" value="{{cus.id}}"  ng-selected="${atr.object}.customerId == cus.id">{{cus.forename}} {{cus.surname}}</option>
    </select>
</div>
<div ng-show="checkTable(${atr.map},'employeeId')">
    {{columnDescription('employeeId')}}: 
    <select required="required" ng-model="${atr.object}.employeeId" ng-change="selectAction('employeeId')">
        <option ng-repeat="emp in employees" value="{{emp.id}}"  ng-selected="${atr.object}.employeeId == emp.id">{{emp.forename}} {{emp.surname}}</option>
    </select>
</div>
<div ng-show="checkTable(${atr.map},'managerId')">
    {{columnDescription('managerId')}}: 
    <select required="required" ng-model="${atr.object}.managerId" ng-change="selectAction('managerId')">
        <option ng-repeat="emp in employees" value="{{emp.id}}"  ng-selected="${atr.object}.managerId == emp.id">{{emp.forename}} {{emp.surname}}</option>
    </select>
</div>
<div ng-show="checkTable(${atr.map},'dictId')">
    {{columnDescription('dictId')}}: 
    <select required="required" ng-model="${atr.object}.dictId" ng-change="selectAction('dictId')">
        <option ng-repeat="dict in dictionaries" value="{{dict.id}}"  ng-selected="${atr.object}.dictId == dict.id">{{dict.description}}</option>
    </select>
</div>
<div ng-show="checkTable(${atr.map},'dictTypeId')">
    {{columnDescription('dictTypeId')}}: 
    <select required="required" ng-model="${atr.object}.dictTypeId">
        <option ng-repeat="dict in dictTypes" value="{{dict.id}}"  ng-selected="${atr.object}.dictTypeId == dict.id">{{dict.description}}</option>
    </select>
</div>
<div ng-show="checkTable(${atr.map},'companyId')">
    {{columnDescription('companyId')}}: 
    <select required="required" ng-model="${atr.object}.companyId">
        <option ng-repeat="comp in companies" value="{{comp.id}}"  ng-selected="${atr.object}.companyId == comp.id">{{comp.name}}</option>
    </select>
</div>
<div ng-show="checkTable(${atr.map},'departmentId')">
    {{columnDescription('departmentId')}}: 
    <select required="required" ng-model="${atr.object}.departmentId" ng-change="selectAction('departmentId')">
        <option ng-repeat="dep in departments" value="{{dep.id}}"  ng-selected="${atr.object}.departmentId == dep.id">{{dep.name}}</option>
    </select>
</div>
<div ng-show="checkTable(${atr.map},'positionId')">
    {{columnDescription('positionId')}}: 
    <select required="required" ng-model="${atr.object}.positionId" ng-change="selectAction('positionId')">
        <option ng-repeat="pos in positions" value="{{pos.id}}"  ng-selected="${atr.object}.positionId == pos.id">{{pos.name}}</option>
    </select>
</div>
<div ng-show="checkTable(${atr.map},'groupId')">
    {{columnDescription('groupId')}}: 
    <select required="required" ng-model="${atr.object}.groupId" ng-change="selectAction('groupId')">
        <option ng-repeat="gr in groups" value="{{gr.id}}"  ng-selected="${atr.object}.groupId == gr.id">{{gr.name}}</option>
    </select>
</div>
</div>