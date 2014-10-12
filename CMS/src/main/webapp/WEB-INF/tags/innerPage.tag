<%-- 
    Document   : jsonOperations
    Created on : 2013-10-28, 17:19:08
    Author     : Sergio
--%>

<%@tag description="InnerPage" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<div ng-if="page == 'simple'">
    <div ng-show="editMode">
        <t:editTable map="editValues" object="selected"/>
    </div>
   <t:jsonOperations/>
</div>

<div ng-if="page == 'Employee'">
    <div ng-show="selected && !newRecord">
        <table>
            <tr>
                <th>
                    Adresy
                </th>
                <th>
                    Zatrudnienia
                </th>
                <th>
                    Kontrakty
                </th>
            </tr>
            <tr>
                <td>
                    <select ng-model="addressSelector" ng-options="add.streetName+' '+add.streetNumber+' '+add.city
                        for add in selected.addresses"></select><br>
                    </td>
                <td>
                    <select ng-model="employmentSelector" ng-options="'ID: '+empl.id
                        for empl in selectedEmployments"></select><br>
                </td>
                <td>
                    <select ng-model="contractSelector" ng-options="'ID: '+con.id
                        for con in selectedContracts"></select><br>
                </td>
                </tr>
        </table>
        <input type="button" ng-click="addAddress()" value="ADD ADDRESS"/>
        <input type="button" ng-show="addressSelector" ng-click="editAddress()" value="EDIT ADDRESS"/>
        <input type="button" ng-click="removeKey()" value="DELETE ADDRESS"/>
        <div ng-show="addressSelector" >
            <table>
                <tr>
                    <th ng-repeat="adatr in addressAttributes">
                        {{$parent.columnDescription(adatr)}}
                    </th>   
                </tr>
                <tbody>
                    <tr>
                        <td ng-repeat="attr in addressAttributes">
                            <div ng-if="attr == 'dictId'"> 
                                <div ng-repeat="dict in dictionaries" ng-show="dict.id == addressSelector[attr]">
                                    {{dict.description}}
                                </div>
                            </div>
                            <div ng-if="attr != 'dictId'"> {{addressSelector[attr]}} </div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div ng-show="addressEdit">
                <t:editTable map="addressValues" object="addressSelector"/>
                <input type="button" ng-click="addKey()" value="Add"/>
                <input type="button" ng-click="cancelAddress()" value="Cancel"/>
             </div>    
        </div>
    </div>
    <div ng-show="editMode">
        <t:editTable map="editValues" object="selected"/>
    </div>
    <t:jsonOperations/>
</div>

<div ng-if="page == 'Company'">
    <div ng-show="selected && !newRecord">
        <h3>
            Adresy Firmy:
        </h3>
        <select ng-model="addressSelector" ng-options="add.streetName+' '+add.streetNumber+' '+add.city
            for add in selected.addresses"></select><br>
        <table>
            <tr>
                <th ng-repeat="adatr in addressAttributes">
                    {{$parent.columnDescription(adatr)}}
                </th>   
            </tr>
            <input type="button" ng-click="addAddress()" value="ADD"/>
            <input type="button" ng-show="addressSelector" ng-click="editAddress()" value="EDIT"/>
            <input type="button" ng-click="removeKey()" value="DELETE"/>
            <tbody>
                <tr>
                    <td ng-repeat="attr in addressAttributes">
                        <div ng-if="attr == 'dictId'"> 
                            <div ng-repeat="dict in dictionaries" ng-show="dict.id == addressSelector[attr]">
                                {{dict.description}}
                            </div>
                        </div>
                        <div ng-if="attr != 'dictId'"> {{addressSelector[attr]}} </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div ng-show="addressEdit">
            <t:editTable map="addressValues" object="addressSelector"/>
            <input type="button" ng-click="addKey()" value="Add"/>
            <input type="button" ng-click="cancelAddress()" value="Cancel"/>
        </div>
    </div>
    <div ng-show="editMode">
        <t:editTable map="editValues" object="selected"/>
        <input type="checkbox" ng-click="showContactPersonBox()" /> Dodać osobę kontaktową <br/>
        <div name="contactPersonBox" ng-show="contactPersonBoxShow">
            <t:editTable map="contactPersonValues" object="selected"/>
        </div>
    </div>
    <t:jsonOperations/>
</div>

<div ng-if="page == 'Customer'">
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

<div ng-if="page == 'Department'">
    <div id="companyTable" ng-show="selected && !editMode">
        <b>Adres:</b> {{selected.address.city}} {{selected.address.streetName}}
                      {{selected.address.streetNumber}}/m.{{selected.address.apartmentNumber}}
                      {{selected.address.postalCode}} {{selected.address.country}} {{selected.address.dictId}}
    </div>
    <div ng-show="editMode">
        <t:editTable map="editValues" object="selected"/>
        <t:editTable map="addressValues" object="selected.address"/>
    </div>
    <t:jsonOperations/>
</div>

<div ng-if="page == 'FileList'">
    <div ng-show="selected">
        <form action="fileList/download.htm">
            <input ng-hide="true" type="text" name="id" value="{{selected.id}}">
            <input class="pobierz-button" type="submit" value="Pobierz {{selected.name}}"/>
        </form>
    </div>  
</div>

<div ng-if="page == 'Position'">
    <div ng-show="selectedEmployees.length > 0">
        <h3>
            Pracownicy na tym stanowisku:
        </h3>
        <select ng-model="employeeSelector" ng-options="emp.forename+' '+emp.surname for emp in selectedEmployees"></select><br>
        <table>
            <tr>
                <th ng-repeat="adatr in employeeAttributes">
                    {{$parent.columnDescription(adatr)}}
                </th>   
            </tr>
            <tbody>
                <tr>
                    <td ng-repeat="attr in employeeAttributes">
                        <div ng-if="attr == 'departmentId'"> 
                            <div ng-repeat="dep in departments" ng-show="dep.id == employeeSelector[attr]">
                                {{dep.name}}
                            </div>
                        </div>
                        <div ng-if="attr != 'departmentId'"> {{employeeSelector[attr]}} </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div ng-show="editMode">
        <t:editTable map="editValues" object="selected"/>
    </div>
    <t:jsonOperations/>
</div>

<div ng-if="page == 'Report'">
    <div ng-show="selected.formCode" ng-include="selected.formCode">
    </div>
    <div ng-show="selected && !selected.formCode">
        <form action="reportPrint{{selected.id}}.htm">
            <input type="submit" value="Pobierz"/>
        </form>
    </div>
</div>

<div ng-if="page == 'GroupList'">
    <t:editTable/>
    <table class="genericTable">
        <tr ng-show="selected.id">
            <td>Klucze:
                <p ng-repeat="groupPrivKey in privilegeKeys" ng-show="selectedGroupHasKey(groupPrivKey.id)">{{groupPrivKey.code}}</p>
            </td>
            <td>Dodaj nowy klucz:
                <select ng-model="newKeyId">
                    <option ng-repeat="privKey in privilegeKeys" ng-hide="selectedGroupHasKey(privKey.id)" value="{{privKey.id}}">{{privKey.code}}</option> 
                </select>
                <input type="button" ng-click="addKey()" value="Dodaj"/>
                <br/>
                Usuń istniejący klucz:
                <select ng-model="oldKeyId">
                    <option ng-repeat="privKey in privilegeKeys" ng-show="selectedGroupHasKey(privKey.id)" value="{{privKey.id}}">{{privKey.code}}</option> 
                </select>
                <input type="button" ng-click="removeKey()" value="Usuń"/>
            </td>
        </tr>
    </table>
    <t:jsonOperations/>
</div>

<div ng-if="page == 'ManageFile'">
    <br/>
    <form action="manageFile/upload.htm" method="POST" enctype="multipart/form-data">
        Plik: <input type="file" name="file"/>
        Typ pliku: <select name="fileExt">
            <option ng-repeat="(key, value) in mimetypes" value="{{value}}">
                {{key}}
            </option>
        </select>
        <br/>
        Opis: <textarea style="width: 100%; height: 10%" name="description"> </textarea>        
        <br/>
        <input type="submit" value="Zapisz plik"/>
    </form>    
</div>

