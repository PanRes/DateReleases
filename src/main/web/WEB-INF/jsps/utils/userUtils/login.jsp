<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 5/10/2017
  Time: 9:52 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <header>
            <c:if test="${user != null}">
                <c:redirect url="/"/>
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
                <div class="text-center">
                    <div class="row form-group">
                        <div class="input-group text-center col-lg-4 col-lg-offset-4">
                            <span class="input-group-addon"><i class="fa fa-user-circle-o fa-fw"></i></span>
                            <input type="text" name="userName" class="form-control" required
                                   placeholder="UserName or Email"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="input-group text-center col-lg-4 col-lg-offset-4">
                            <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                            <input type="password" name="password" class="form-control" required
                                    placeholder="Password">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="text-center col-lg-4 col-lg-offset-4">
                            <input type="submit" name="btnSubmit" class="btn btn-primary btn-lg" value="Login">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="text-center col-lg-4 col-lg-offset-4">
                            <a href="/signUp">
                                <fmt:message key="login.href.signUp"/>
                            </a>
                        </div>
                    </div>
                </div>
            </form>
            <%@include file="/jsps/universals/footer.jsp"%>
    </body>
</html>
