<%@tag description="Tabela z danymi" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>


<%-- any content can be specified here e.g.: --%>


     
<table class="archive-table">
    
    <tr class="table-header">
        <th class="numer-header">#</th>
        <th ng-repeat="attr in attributes" ng-hide="attr.substring(0, 1) == '%'" class = "{{columnClasses[attr]}}">
            <a ng-click=
                "$parent.orderColumn = attr; 
                $parent.reverse = !$parent.reverse;
                $parent.selectedColumn = attr"
                >{{$parent.columnDescription(attr)}}
            <span ng-if="$parent.selectedColumn == attr && $parent.reverse" class="icon-arrow-down-ico"></span>
            <span ng-if="$parent.selectedColumn == attr && !$parent.reverse" class="icon-arrow-up-ico"></span>
            </a>
        </th>
    </tr>
    
    <tr ng-repeat="obj in objects| filter:searchText | orderBy:orderColumn:reverse" ng-if="indexOnPage($index)"
        
     ng-class="{selectedTableRow: obj == selected}" >
        <td class="numer">
            {{$index + 1}}
        </td>
            
        <td ng-repeat="attr in attributes" ng-click="$parent.select(obj)">
            
            <div ng-if="attr == 'startDate' || attr == 'closeDate' || attr == 'finalisationDate' || attr == 'dateTo' || attr == 'dateFrom' "> {{obj[attr] | date:'dd-MM-yyyy'}} </div>
            <div ng-if=" attr != 'startDate' && attr != 'closeDate' && attr != 'finalisationDate' && attr != 'dateTo' && attr != 'dateFrom' " > {{obj[attr]}} </div>            
        </td>
        
    </tr>

</table>
        
        
<div class="ladowanie" align="center">
    <span ng-show="status != null">Ładowanie danych...</span>
    <span ng-show="status == 'Błąd'">Błąd podczas ładowania danych...</span>

</div>
<div class="pageMax">
    <input ng-show="pageMin > 0" type="button" class="wstecz-button" ng-click="pageMax = pageMax - 15;
        pageMin = pageMin - 15" value="WSTECZ"/>
    <input ng-show="checkMax()" type="button" class="dalej-button" ng-click="pageMax = pageMax + 15;
        pageMin = pageMin + 15" value="DALEJ"/>

</div>
<div class="pageMax-tekst">
    wyświetlane wpisy {{pageMin + 1}}-{{pageMax + 1}}
</div>