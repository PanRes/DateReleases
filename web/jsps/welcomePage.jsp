<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 3/11/2017
  Time: 11:53 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="${param.language}">
    <head>
        <title>Welcome</title>
    </head>
    <body>

        <%@include file="universals/header.jsp"%>
        <jsp:useBean id="user" class="gr.pr.datereleases.models.UsersModel"/>
        <c:set var="user" value='<%=session.getAttribute("user")%>'/>
        <div class="container text-center">
            <c:choose>
                <c:when test="${user != null}">
                    <div class="row">
                        <p><h2><fmt:message key="welcome.page.title"/> </h2></p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <p><h2><fmt:message key="welcome.page.title"/></h2></p>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="row">
                <p>
                    In this site you can find the release dates for many series,
                    add new series and dates and add to your favorites.
                </p>
            </div>
            <c:if test="${user == null}">
                <div class="row">
                    <p>
                        <a href="login" class="btn btn-success">
                            <i class="fa fa-sign-in"></i>
                            Log In
                        </a>
                        <a href="signUp" class="btn btn-primary">
                            <i class="fa fa-user-plus"></i>
                            Sign Up
                        </a>
                    </p>
                </div>
            </c:if>
        </div>
        <%@include file="universals/footer.jsp"%>
    </body>
</html>
