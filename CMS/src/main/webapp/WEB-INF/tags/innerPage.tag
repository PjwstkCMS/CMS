<%@tag description="InnerPage" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>


<div ng-if="page == 'Employee'">
   
    <div ng-show="selected && !newRecord">
        <div>
            <a ng-class="{aclass: selectedSubpage == 'empData'}" ng-click="attributesArray = '';
                selectedSubpage = 'empData'">
                Dane osobowe
            </a>
            <a ng-class="{aclass: selectedSubpage == 'address'}" ng-click="attributesArray = addressAttributes;
                selectedSubpage = 'address';
                selectedValues = 'addressValues'">
                Adresy
            </a>
            <a ng-class="{aclass: selectedSubpage == 'employment'}" ng-click="attributesArray = employmentAttributes;
                selectedSubpage = 'employment';
                selectedValues = 'employmentValues'">
                Zatrudnienia
            </a>
            <a ng-class="{aclass: selectedSubpage == 'contract'}" ng-click="attributesArray = contractAttributes;
                selectedSubpage = 'contract';
                selectedValues = 'contractValues'">
                Kontrakty
            </a>
        </div>
        <div ng-show="selectedSubpage == 'empData'">
            <div ng-show="editMode">
                <t:editTable map="editValues" object="selected"/>
            </div>
            <t:jsonOperations/> 
        </div>        
        <div ng-show="selectedSubpage != 'empData'">
            <select ng-init="$parent.$parent.selector.address = null" ng-show="selectedSubpage == 'address'" ng-model="$parent.$parent.selector.address" 
                    ng-options="add.streetName+' '+add.streetNumber+' '+add.city for add in selected.addresses"></select>
            <select ng-init="$parent.$parent.selector.employment = null" ng-show="selectedSubpage == 'employment'" ng-model="$parent.$parent.selector.employment" ng-options="empl.id for empl in selectedEmployments"></select>
            <select ng-init="$parent.$parent.selector.contract = null" ng-show="selectedSubpage == 'contract'" ng-model="$parent.$parent.selector.contract" ng-options="con.id for con in selectedContracts"></select><br>
            <input type="button" ng-click="addNewElement(selectedSubpage)" value="ADD"/>
            <input type="button" ng-show="$parent.$parent.selector[selectedSubpage]" ng-click="showEdit()" value="EDIT"/>
            <input type="button" ng-click="removeElement(selectedSubpage, $parent.$parent.selector[selectedSubpage])" value="DELETE"/>
            <div ng-show="$parent.$parent.selector[selectedSubpage]">
                <table>
                    <tr>
                        <th ng-repeat="adatr in attributesArray">
                            {{$parent.$parent.columnDescription(adatr)}}
                        </th>   
                    </tr>

                    <tr>
                        <td ng-repeat="attr in attributesArray">                            
                            <div ng-if="attr != 'dictId'">{{$parent.$parent.$parent.$parent.selector[selectedSubpage][attr]}}</div>
                            <div ng-if="attr == 'dictId'"> 
                                <div ng-repeat="dict in dictionaries" ng-show="dict.id == $parent.$parent.$parent.$parent.$parent.selector[selectedSubpage][attr]">
                                    {{dict.description}}
                                </div>
                            </div>
                        </td>
                    </tr>

                </table>
            </div>
            <div ng-show="editSubElement">         
                <div ng-show="selectedSubpage == 'address'">
                    <t:editTable map="addressValues" object="$parent.$parent.selector.address"/>
                </div>
                <div ng-show="selectedSubpage == 'employment'">
                    <t:editTable map="employmentValues" object="$parent.$parent.selector[selectedSubpage]"/>
                </div>
                <div ng-show="selectedSubpage == 'contract'">
                    <t:editTable map="contractValues" object="$parent.$parent.selector[selectedSubpage]"/>
                </div>
                <input type="button" ng-click="addElement(selectedSubpage, $parent.$parent.selector[selectedSubpage])" value="Add"/>
                <input type="button" ng-click="cancelElement()" value="Cancel"/>
            </div> 
        </div>
    </div>
</div>    

<div ng-if="page == 'Company'">
    <div ng-show="selected && !newRecord">
        <h3>
            Adresy Firmy:
        </h3>
        <select  ng-model="$parent.addressSelector" ng-options="add.streetName+' '+add.streetNumber+' '+add.city
                 for add in selected.addresses">
        </select><br>

        <table style="width:100%;">
            <tr>
                <th ng-repeat="adatr in addressAttributes">
                    {{columnDescription(adatr)}}
                </th>   
            </tr>
            <input type="button" ng-show="!addressEdit" ng-click="addAddress()" value="ADD"/>
            <input type="button" ng-show="addressSelector && !addressEdit" ng-click="editAddress()" value="EDIT"/>
            <input type="button" ng-show="addressSelector && !addressEdit" ng-click="removeKey()" value="DELETE"/>

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

        </table>
        <div ng-show="addressEdit">
            <t:editTable map="addressValues" object="addressSelector"/>
            <input type="button" ng-click="addKey()" value="Add"/>
            <input type="button" ng-click="cancelAddress()" value="Cancel"/>
        </div>
    </div>
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
        <table style="width:100%;">
            <tr>
                <th ng-repeat="catr in contractAttributes">
                    {{$parent.columnDescription(catr)}}
                </th>   
            </tr>

            <tr ng-repeat="con in selectedContracts">
                <td ng-repeat="atr in contractAttributes">
                    {{con[atr]}}
                </td>
            </tr>

        </table>

    </div>
</div>

<div ng-if="page == 'Department'">
    <div id="companyTable" ng-show="selected && !editMode">
        <b>Adres:</b> {{selected.address.city}} {{selected.address.streetName}}
        {{selected.address.streetNumber}}/m.{{selected.address.apartmentNumber}}
        {{selected.address.postalCode}} {{selected.address.country}} {{selected.address.dictId}}
    </div>
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
        <table>
            <tr>
                <th ng-repeat="adatr in employeeAttributes">
                    {{$parent.columnDescription(adatr)}}
                </th>   
            </tr>
            <tr ng-repeat="con in selectedEmployees">
                <td ng-repeat="attr in employeeAttributes">
                    <div ng-if="attr == 'departmentId'"> 
                        <div ng-repeat="dep in departments" ng-show="dep.id == con[attr]">
                            {{dep.name}}
                        </div>
                    </div>
                    <div ng-if="attr != 'departmentId'"> {{con[attr]}} </div>
                </td>
            </tr>
        </table>
    </div>
</div>


<div ng-if="page == 'GroupList'">
    <table class="genericTable">
        <tr ng-show="selected.id">
            <td>Klucze:
                <p ng-repeat="groupPrivKey in privilegeKeys" ng-show="selectedGroupHasKey(groupPrivKey.id)">{{groupPrivKey.code}}</p>
            </td>
            <td>Dodaj nowy klucz:
                <select ng-model="$parent.$parent.newKey">
                    <option ng-repeat="privKey in privilegeKeys" ng-hide="selectedGroupHasKey(privKey.id)" value="{{privKey.id}}">{{privKey.code}}</option> 
                </select>
                <input type="button" ng-click="addKey()" value="Dodaj"/>
                <br/>
                UsuÅ istniejÄcy klucz:
                <select ng-model="$parent.$parent.oldKey">
                    <option ng-repeat="privKey in privilegeKeys" ng-show="selectedGroupHasKey(privKey.id)" value="{{privKey.id}}">{{privKey.code}}</option> 
                </select>
                <input type="button" ng-click="removeKey()" value="UsuÅ"/>
            </td>
        </tr>
    </table>
</div>

<div ng-if="page == 'DictionaryList'">
    <div ng-show="selected && !newRecord">
        <h3>
            Słowniki: {{$parent.dictionarySelector}}
        </h3>
        <select ng-model="$parent.dictionarySelector" ng-options="dictT.description
                for dictT in selected.dictionaries"></select><br>
        <table>
            <tr>
                <th ng-repeat="dictAtr in dictionaryAttributes">
                    {{$parent.columnDescription(dictAtr)}}
                </th>   
            </tr>
            <input type="button" ng-show="!dictionaryEdit" ng-click="addDictionary()" value="ADD"/>
            <input type="button" ng-show="dictionarySelector && !dictionaryEdit" ng-click="editDictionary()" value="EDIT"/>
            <input type="button" ng-show="dictionarySelector && !dictionaryEdit" ng-click="removeKey()" value="DELETE"/>

            <tr>
                <td ng-repeat="dictAtr in dictionaryAttributes">
                    {{$parent.dictionarySelector[dictAtr]}}
                </td>
            </tr>

        </table>
        <div ng-show="dictionaryEdit">
            <t:editTable map="dictionaryValues" object="dictionarySelector"/>
            <input type="button" ng-click="addKey()" value="Add"/>
            <input type="button" ng-click="cancelDictionary()" value="Cancel"/>
        </div>
    </div>
</div>

<div ng-if="page == 'UserList'">
    <div id="companyTable" ng-show="selected && !editMode">
        <table>
            <tr>
                <th ng-repeat="adatr in additionalAttributes">
                    {{$parent.columnDescription(adatr)}}
                </th>   
            </tr>
            <tbody ng-repeat="emp in employees" ng-show="emp.id == selected.employeeId">
                <tr>
                    <td ng-repeat="attr in additionalAttributes">
                        {{emp[attr]}}
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
