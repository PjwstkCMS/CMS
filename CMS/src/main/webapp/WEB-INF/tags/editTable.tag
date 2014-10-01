<%-- 
    Document   : jsonOperations
    Created on : 2013-10-28, 17:19:08
    Author     : Sergio
--%>

<%@tag description="EditTable" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<hr/>
<div ng-show="editMode || selected">
    <table>
        <tr ng-repeat="col in attributes ">
                <td ng-show="col == 'email'">
                    {{columns[col]}}: <input  type="text" required="required" ng-pattern="/^[.A-Za-z0-9-_]+@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/" ng-model="selected.email"/>
                </td>
                <td ng-show="col == 'forename'">
                    {{columns[col]}}: <input  type="text" maxlength="21" required="required" ng-pattern="/^[A-Z]+[a-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" ng-model="selected.forename"/>
                </td>
                <td ng-show="col == 'surname'">
                    {{columns[col]}}: <input  type="text" required="required" ng-pattern="/^[A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+(-[A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+){0,1}$/" ng-model="selected.surname"/>
                </td>
                <td ng-show="col == 'name'">
                    {{columns[col]}}: <input class="firma-field" placeholder="Nazwa firmy..." name="firma" type="text" required="required" ng-model="selected.name"/>
                </td>
                <td ng-show="col == 'phone'">
                    {{columns[col]}}: <input type="text" ng-pattern="/^[0-9]+$/" maxlength="9" required="required" ng-model="selected.phone"/>
                </td>
                <td ng-show="col == 'country'">
                    {{columns[col]}}: <input type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.country"/>
                </td>
                <td ng-show="col == 'city'">
                    {{columns[col]}}: <input type="text" ng-pattern="/^[ A-Za-ząęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.city"/>
                </td>
                <td ng-show="col == 'streetName'">
                    {{columns[col]}}: <input type="text" ng-pattern="/^[ A-Za-z0-9ąęółżćźńśĄĘÓŁŻŹĆŚŃ]+$/" required="required" ng-model="selected.streetName"/>
                </td>
                <td ng-show="col == 'streetNumber'">
                    {{columns[col]}}: <input type="text" ng-pattern="/^[0-9]+[a-z]{0,1}$/" required="required" ng-model="selected.streetNumber"/>
                </td>
                <td ng-show="col == 'apartmentNumber'">
                    {{columns[col]}}: <input type="text" ng-pattern="/^[0-9]+$/" required="required" ng-model="selected.apartmentNumber"/>    
                </td>
                <td ng-show="col == 'postalCode'">
                    {{columns[col]}}: <input type="text" maxlength="6" ng-pattern="/^[0-9]{2}-[0-9]{3}$/" required="required" ng-model="selected.postalCode"/>    
                </td>
                <td ng-show="col == 'description'">
                    {{columns[col]}}: <input required="required" maxlength="200" type="text" ng-model="selected.description"/> 
                </td>
                <td ng-show="col == 'price'">
                    {{columns[col]}}: <input required="required" maxlength="45" ng-pattern="/^[0-9]+$/" type="text" ng-model="selected.price"/> 
                </td>
                <td ng-show="col == 'startDate'">
                    {{columns[col]}}: <input required="required" ng-pattern="/^((19|20)[0-9]{2}[\-/](0[1-9]|1[0-2])[\-/](0[1-9]|[12][0-9]|3[01]))*$/" type="text" ng-model="selected.startDate"/> 
                </td>
                <td ng-show="col == 'closeDate'">
                    {{columns[col]}}: <input required="required" ng-pattern="/^((19|20)[0-9]{2}[\-/](0[1-9]|1[0-2])[\-/](0[1-9]|[12][0-9]|3[01]))*$/" type="text" ng-model="selected.closeDate"/> 
                </td>
                <td ng-show="col == 'finalisationDate'">
                    {{columns[col]}}: <input required="required" ng-pattern="/^((19|20)[0-9]{2}[\-/](0[1-9]|1[0-2])[\-/](0[1-9]|[12][0-9]|3[01]))*$/" type="text" ng-model="selected.finalisationDate"/> 
                </td>
                
                <td ng-show="col == 'id'">
                    {{columns[col]}}: <input required="required" type="text" ng-model="selected.id"/> 
                </td>
                <td ng-show="col == 'customerId'">
                    {{columns[col]}}: <input required="required" type="text" ng-model="selected.customerId"/> 
                </td>
                <td ng-show="col == 'employeeId'">
                    {{columns[col]}}: <input required="required" type="text" ng-model="selected.employeeId"/> 
                </td>
        </tr>
    </table>
</div>
