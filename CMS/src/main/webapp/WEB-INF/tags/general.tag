<%-- 
    Document   : gen
    Created on : 2014-08-30, 14:40:07
    Author     : Macha
--%>
<%@tag import="pl.edu.pjwstk.cms.utils.Utils"%>
<%@tag import="pl.edu.pjwstk.cms.dto.UserDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>

<html >
    <head>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular.min.js"></script> 
        
        <script src="/CMS/resources/js/services.js"></script>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HR System</title>        
    </head>
    <body ng-app="cms">
        <input type="text" ng-model="test"/>
        <h1>
            ${user.name}            
            {{test}}
        </h1>
        <ul>
            <li id="nav14">Zarządzanie zasobami</li>
                <a href="/CMS/loginPage.htm"><li id="nav0">Login</li></a>
                <a href="/CMS/home.htm"><li id="nav0">home</li></a>
                <a href="/CMS/contract.htm"><li id="nav0">Contract</li></a>
                <a href="/CMS/company.htm"><li id="nav0">Company</li></a>
                <a href="/CMS/customer.htm"><li id="nav0">Customer</li></a>
                <a href="/CMS/department.htm"><li id="nav0">Department</li></a>
                <a href="/CMS/employee.htm"><li id="nav0">employee</li></a>
                <a href="/CMS/fileList.htm"><li id="nav0">fileList</li></a>
                <a href="/CMS/position.htm"><li id="nav0">position</li></a>
                <a href="/CMS/report.htm"><li id="nav0">report</li></a>
                <a href="/CMS/terminal.htm"><li id="nav0">terminal</li></a>
                <br/>
            <li id="nav14">Konfiguracja</li>
                <a href="/CMS/dictionaryList.htm"><li id="nav0">dictionaryList</li></a>
                <a href="/CMS/userList.htm"><li id="nav0">userList</li></a>
                <a href="/CMS/setting.htm"><li id="nav0">setting</li></a>
                <a href="/CMS/manageFile.htm"><li id="nav0">fileManage</li></a>
                <a href="/CMS/groupList.htm"><li id="nav0">groupList</li></a>
                <a href="/CMS/key.htm"><li id="nav0">key</li></a>
        </ul>
            

        <div class="content-table">
            <jsp:doBody/>
        </div>

    </body>
</html>
