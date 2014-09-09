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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HR System</title>
    </head>
    <body>
        <h1>
            ${user.name}
        </h1>
        <ul>
            <li id="nav14">Zarządzanie zasobami</li>
            <a href="/CMS/loginPage.htm"><li id="nav0">Login</li></a>
            <a href="/CMS/home.htm"><li id="nav0">home</li></a>
            <a href="/CMS/contract.htm"><li id="nav0">Contract</li></a>
            <a href="/CMS/customer.htm"><li id="nav0">Customer</li></a>
            <a href="/CMS/department.htm"><li id="nav0">Department</li></a>
            <a href="/CMS/dictionary.htm"><li id="nav0">dictionary</li></a>
            <a href="/CMS/employee.htm"><li id="nav0">employee</li></a>
            <a href="/CMS/file.htm"><li id="nav0">file</li></a>
            <a href="/CMS/manageFile.htm"><li id="nav0">fileManage</li></a>
            <a href="/CMS/group.htm"><li id="nav0">group</li></a>
            <a href="/CMS/key.htm"><li id="nav0">key</li></a>
            <a href="/CMS/position.htm"><li id="nav0">position</li></a>
            <a href="/CMS/raport.htm"><li id="nav0">raport</li></a>
            <a href="/CMS/setting.htm"><li id="nav0">setting</li></a>
            <a href="/CMS/terminal.htm"><li id="nav0">terminal</li></a>
            <a href="/CMS/user.htm"><li id="nav0">user</li></a>
        </ul>

        <div class="content-table">
            <jsp:doBody/>
        </div>

    </body>
</html>