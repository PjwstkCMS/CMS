<%@tag description="Tabela z danymi" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>


<%-- any content can be specified here e.g.: --%>
<style>
    .selectedTableRow {
        background-color: lightblue;
    }

</style>

<!--<div class="table-container">
    <div class="heading">
        <div class="col">#</div>
        <div class="col" ng-repeat="attr in attributes" ng-hide="attr.substring(0, 1) == '%'" class = "{{columnClasses[attr]}}"><a ng-click="$parent.orderColumn = attr; $parent.reverse = !$parent.reverse">{{$parent.columnDescription(attr)}}</a></div>
    </div>
    
    <div class="table-row" ng-class="{selectedTableRow: obj == selected}" ng-repeat="obj in objects| filter:searchText | orderBy:orderColumn:reverse" ng-show="indexOnPage($index)">
        <div class="col">{{$index + 1}}</div>
        <div class="col" ng-repeat="attr in attributes" ng-click="$parent.select(obj)"><div ng-if="attr == 'startDate' || attr == 'closeDate' || attr == 'finalisationDate'
                     || attr == 'dateTo' || attr == 'dateFrom' "> {{obj[attr] | date:'dd-MM-yyyy'}} </div>
                <div ng-if=" attr != 'startDate' && attr != 'closeDate' && attr != 'finalisationDate'
                     && attr != 'dateTo' && attr != 'dateFrom' " > {{obj[attr]}} </div></div>
    </div>
<div class="table-row" ng-show="obj == selected">
        <t:innerPage/>
     
    </div>

</div>-->

<div>
    
<table class="data-table-table col-lg-12">
    
    <tr>
        <th>#</th>
        <th ng-repeat="attr in attributes" ng-hide="attr.substring(0, 1) == '%'" class = "{{columnClasses[attr]}}">
            <a ng-click="$parent.orderColumn = attr; $parent.reverse = !$parent.reverse">{{$parent.columnDescription(attr)}}</a>
        </th>
    </tr>
    
    <tbody ng-repeat="obj in objects| filter:searchText | orderBy:orderColumn:reverse" ng-show="indexOnPage($index)">
        
    <tr ng-class="{selectedTableRow: obj == selected}" >
        <td>
            {{$index + 1}}
        </td>
            
        <td ng-repeat="attr in attributes" ng-click="$parent.select(obj)">
            
            <div ng-if="attr == 'startDate' || attr == 'closeDate' || attr == 'finalisationDate' || attr == 'dateTo' || attr == 'dateFrom' "> {{obj[attr] | date:'dd-MM-yyyy'}} </div>
            <div ng-if=" attr != 'startDate' && attr != 'closeDate' && attr != 'finalisationDate' && attr != 'dateTo' && attr != 'dateFrom' " > {{obj[attr]}} </div>
            
        </td>
    </tr>
        
    <tr ng-show="obj == selected">
            
        <td colspan="8"><t:innerPage/></td>
            
    </tr>
                 
                
       
    </tbody>

</table>
        
</div>
<div class="ladowanie" align="center">
    <span ng-show="status != null"><div id="loaderImage"></div>ładowanie danych...</span>
    <span ng-show="status == 'Błąd'">błąd podczas ładowania danych...</span>

</div>
<div class="pageMax">
    <input ng-show="pageMin > 0" type="button" class="wstecz-button" ng-click="pageMax = pageMax - 15;
                pageMin = pageMin - 15" value="WSTECZ"/>
    <input ng-show="checkMax()" type="button" class="dalej-button" ng-click="pageMax = pageMax + 15;
                pageMin = pageMin + 15" value="DALEJ"/>

</div>
<div class="pageMax-tekst">
    wyświetlane wpisy<br>
    <span style="font-weight:700;float: right;">{{pageMin + 1}}-{{pageMax + 1}}</span>
</div>