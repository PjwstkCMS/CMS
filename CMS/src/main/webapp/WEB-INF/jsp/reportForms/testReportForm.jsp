<%-- 
    Document   : testReportForm
    Created on : 2013-11-20, 11:43:51
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="reportPrint.htm">
    <input type="text" ng-show="false" name="id" value="10"/>
    <input class="pole-field1" type="text" name="msg1" value="Pole 1"/>
    <input class="pole-field" type="text" name="msg2" value="Pole 2"/>
    <input class="pole-field" type="text" name="msg3" value="Pole 3"/>
    <input class="raport-button" type="submit" value="POBIERZ"/>
</form>
