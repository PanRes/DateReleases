<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 10:52 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<%
    String user = (String) session.getAttribute("user");
    if (user == null){
        response.sendRedirect("/");
        return;
    }
%>

<h1 align="center">
    Date Releases
</h1>
<div class="row page-header">
    <div class="col-lg-10">
        <div class="col-lg-5 d-inline">
            <h5><a href="/jsps/mainMenu.jsp">Main Menu</a></h5>
            <c:out value=" | "/>
            <h5><a href="/jsps/addDate.jsp">Add Date</a></h5>
        </div>
    </div>
    <div class="col-lg-2">
        <div class="col-lg-offset-5">
            <h5>Welcome <%=user%>, <a href="/jsps/logout.jsp">sign out?</a></h5>
        </div>
    </div>
</div>