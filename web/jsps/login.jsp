<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 5/10/2017
  Time: 9:52 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <header>
            <c:if test="${user != null}">
                <c:redirect url="/mainMenu"/>
            </c:if>
            <%@include file="/jsps/universals/inclusions.jsp"%>

            <h1 align="center">
                Date Releases
            </h1>
        </header>
        <article id="content">
            <h2 class="page-header" align="center">Please Login</h2>
            <c:if test="${param.wrongUser == true}">
                <div class="alert alert-danger text-center">
                    <strong>Warning!</strong> The User Name or the Password is incorrect. Please try again.
                </div>
            </c:if>
            <form class="form-horizontal" action="/Authenticate" name="loginForm" method="post">
                <input type="hidden" class="form-control" name="page" value="${page}">
                <div class="text-center">
                    <div class="row form-group">
                        <div class="col-md-4">
                            <label class="pull-right">User Name : </label>
                        </div>
                        <div class="col-md-4">
                            <input type="text" name="userName" class="form-control" required="required"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-4">
                            <label class="pull-right">Password : </label>
                        </div>
                        <div class="col-md-4">
                            <input type="password" name="password" class="form-control" required="required">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="text-center">
                            <input type="submit" name="btnSubmit" class="btn btn-primary btn-lg" value="Login">
                        </div>
                    </div>
                </div>
            </form>
            <%@include file="/jsps/universals/footer.jsp"%>
    </body>
</html>
