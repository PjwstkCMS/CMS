<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<table class="filterTable">
    <tr>
        <th colspan="2" ng-click="showFilter = !showFilter">
            Filtrowanie danych
        </th>
    </tr>
    <tr ng-show="showFilter">
        <td>
            Wyszukaj: <input type="text" ng-model="searchText"/>
        </td>
    </tr>
</table>