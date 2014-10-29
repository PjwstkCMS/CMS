<%-- 
    Document   : dataTable
    Created on : 2014-09-09, 17:59:47
    Author     : Konrad
--%>

<%@tag description="Tabela z danymi" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<script type="text/javascript">
    var cSpeed = 2;
            var cWidth = 24;
            var cHeight = 24;
            var cTotalFrames = 32;
            var cFrameWidth = 24;
            var cImageSrc = '/CMS/resources/images/sprites.gif';
            var cImageTimeout = false;
            var cIndex = 0;
            var cXpos = 0;
            var cPreloaderTimeout = false;
            var SECONDS_BETWEEN_FRAMES = 0;
            function startAnimation(){

            document.getElementById('loaderImage').style.backgroundImage = 'url(' + cImageSrc + ')';
                    document.getElementById('loaderImage').style.width = cWidth + 'px';
                    document.getElementById('loaderImage').style.height = cHeight + 'px';
                    //FPS = Math.round(100/(maxSpeed+2-speed));
                    FPS = Math.round(100 / cSpeed);
                    SECONDS_BETWEEN_FRAMES = 1 / FPS;
                    cPreloaderTimeout = setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES / 1000);
            }

    function continueAnimation(){

    cXpos += cFrameWidth;
            //increase the index so we know which frame of our animation we are currently on
            cIndex += 1;
            //if our cIndex is higher than our total number of frames, we're at the end and should restart
            if (cIndex >= cTotalFrames) {
    cXpos = 0;
            cIndex = 0;
    }

    if (document.getElementById('loaderImage'))
            document.getElementById('loaderImage').style.backgroundPosition = ( - cXpos) + 'px 0';
            cPreloaderTimeout = setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES * 1000);
    }

    function stopAnimation(){//stops animation
    clearTimeout(cPreloaderTimeout);
            cPreloaderTimeout = false;
    }

    function imageLoader(s, fun)//Pre-loads the sprites image
    {
    clearTimeout(cImageTimeout);
            cImageTimeout = 0;
            genImage = new Image();
            genImage.onload = function (){cImageTimeout = setTimeout(fun, 0)};
            genImage.onerror = new Function('alert(\'Could not load the image\')');
            genImage.src = s;
    }

    //The following code starts the animation
    new imageLoader(cImageSrc, 'startAnimation()');</script>

<%-- any content can be specified here e.g.: --%>
<style>
    .selectedTableRow {
        background-color: lightblue;
    }
    th {
        width: {{100/attributes.length}}%;
    }
    table {
        width: 100%;
    }
</style>
<input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>
<table>
    <tr>

        <th>
            #
        </th>
        <th ng-repeat="attr in attributes" ng-hide="attr.substring(0, 1) == '%'"
            class = "{{columnClasses[attr]}}">
            <a ng-click="$parent.orderColumn = attr;
                        $parent.reverse = !$parent.reverse">{{$parent.columnDescription(attr)}}</a>
        </th>   

    </tr>
    <tbody ng-repeat="obj in objects| filter:searchText | orderBy:orderColumn:reverse" 
            ng-show="indexOnPage($index)">
        <tr ng-class="{selectedTableRow: obj == selected}" >
            <td class="numer">
                {{$index + 1}}
            </td>
            <td ng-repeat="attr in attributes" ng-click="$parent.select(obj)">
                {{obj[attr]}}
            </td>
        </tr>
        <tr ng-show="obj == selected">
            <td>
                <t:innerPage/>
            </td>
        </tr>
    </tbody>

</table>
<div class="ladowanie" align="center">
    <span ng-show="status != null"><div id="loaderImage"></div>ładowanie danych...</span>
    <span ng-show="status == 'Błąd'">błąd podczas ładowania danych...</span>

</div>
<div class="pageMax">
    <input ng-show="pageMin > 0" type="button" class="wstecz-button" ng-click="pageMax = pageMax - 15;
                pageMin = pageMin - 10" value="WSTECZ"/>
    <input ng-show="checkMax()" type="button" class="dalej-button" ng-click="pageMax = pageMax + 15;
                pageMin = pageMin + 10" value="DALEJ"/>

</div>
<div class="pageMax-tekst">
    wyświetlane wpisy<br>
    <span style="font-weight:700;float: right;">{{pageMin + 1}}-{{pageMax + 1}}</span>
</div>