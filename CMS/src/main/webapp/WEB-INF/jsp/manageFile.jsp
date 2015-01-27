<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/manageFileCtrl.js"></script>
       
        <div ng-controller="ManageFileCtrl">
            <t:dataTable/>
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
    </jsp:body>
</t:general>