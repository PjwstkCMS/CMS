<%-- 
    Document   : dataTable
    Created on : 2014-09-09, 17:59:47
    Author     : Konrad
--%>

<%@tag description="Tabela z danymi" pageEncoding="UTF-8"%>

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

</style>
<script>


</script>


</div>
<table class="genericTable">
    <tr class="table-header">

        <th class="numer-header">
            #
        </th>
        <th ng-repeat="attr in attributes" ng-hide="attr.substring(0, 1) == '%'"
            class = "{{columnClasses[attr]}}">
            <a ng-click="$parent.orderColumn = attr;
                        $parent.reverse = !$parent.reverse">{{$parent.columns[attr]}}</a>
        </th>   

    </tr>
    <tbody>
        <tr ng-class="{selectedTableRow: obj == selected}" ng-repeat="obj in objects| filter:searchText | orderBy:orderColumn:reverse" 
            ng-show="indexOnPage($index)">
            <td class="numer">
                {{$index + 1}}
            </td>
            <td ng-repeat="attr in attributes" ng-click="$parent.select(obj)">
                {{obj[attr]}}
            </td>
        </tr>
    </tbody>

</table>
<div class="ladowanie" align="center">
    <span ng-show="status != null"><div id="loaderImage"></div>ładowanie danych...</span>
    <span ng-show="status == 'Błąd'">błąd podczas ładowania danych...</span>

</div>
<div class="footer" ng-show="objects.length > 0">



    <a href="#addNew"><input type="button" class="dodaj-button" ng-show="(!selected && !editMode) && checkEditPrivileges()" ng-click="create()"  value="DODAJ"></a>
    <a href="#addNew"><input type="button" class="edytuj-button" ng-show="selected && !editMode && checkEditPrivileges()" ng-click="edit()" href="#addNew" value="EDYTUJ"></a>
    <input type="button" class="zapisz-button" ng-show="editMode && checkEditPrivileges()" ng-click="save()" value="ZAPISZ">
    <input type="button" class="anuluj-button" ng-show="editMode && checkEditPrivileges()" ng-click="cancel()" value="ANULUJ">
    <input type="button" class="wyswietl-button" ng-show="displayPage && selected && !editMode" onclick="location.href ='{{displayPageName}}/{{selected.id}}.htm'" value="WYŚWIETL">
    <input type="button" class="usun-button" ng-show="selected.id != undefined && !editMode && checkEditPrivileges()" ng-click="delete()" value="USUŃ">
    <div class="komunikat-operacji" ng-show="showOperationMessage">

        {{operationMessage}}  
    </div>
    <div ng-show="fileListDownload && selected">
        <form action="fileList/download.htm">
            <input ng-hide="true" type="text" name="id" value="{{selected.id}}">
            <input class="pobierz-button" type="submit" value="Pobierz {{selected.name}}"/>
        </form>
    </div>
    <div ng-show="configFileListDownload && selected">
        <form action="reportList/download.htm">
            <input ng-show="false" type="text" value="{{selected.id}}" name="id"/>
            <input class="pobierz-button" type="submit" value="Pobierz {{selected.name}}"/>
        </form>
    </div>
    <div ng-show="reportDownload && selected">
        <div ng-include="selected.formCode">

        </div>
    </div>
    <div ng-show="fileListUpload">
        <form action="fileListUpload/upload.htm" method="POST" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <select name="fileExt">
                <option ng-repeat="(key, value) in mimetypes" value="{{value}}">
                    {{key}}
                </option>
            </select>
            <textarea  name="description"></textarea>      
            <input type="submit" value="Zapisz plik"/>
        </form>
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

</div>
