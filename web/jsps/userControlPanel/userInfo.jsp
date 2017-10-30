<%@ page import="gr.pr.datereleases.hibernatetools.UserTools" %><%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 30/10/2017
  Time: 1:40 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Personal Info</title>
    </head>
    <body>
        <%@include file="/jsps/universals/header.jsp"%>
        <jsp:useBean id="user" class="gr.pr.datereleases.models.UsersModel"/>
        <c:set var="user" value='<%=UserTools.getUserById((int) session.getAttribute("userId"))%>'/>
        <div class="container">
            <div class="col-lg-3 text-center">
                <c:choose>
                    <c:when test="${user.imageUrl == null}">
                        <img src="/contentFiles/imgs/user-200.png" class="thumbnail">
                    </c:when>
                    <c:otherwise>
                        <img src="${user.imageUrl}">
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-lg-9">
                <div class="row col-lg-12">
                    <p>
                        <div class="col-lg-4">
                            User Name:
                        </div>
                        <div class="col-lg-8">
                            ${user.userName}
                        </div>
                    </p>
                </div>
                <div class="row col-lg-12">
                    <p>
                        <div class="col-lg-4">
                            Email:
                        </div>
                        <div class="col-lg-8">
                            ${user.email}
                        </div>
                    </p>
                </div>
                <div class="row col-lg-12">
                    <p>
                        <div class="col-lg-4">
                            First Name:
                        </div>
                        <div class="col-lg-8">
                            ${user.firstName}
                        </div>
                    </p>
                </div>
                <div class="row col-lg-12">
                   <p>
                        <div class="col-lg-4">
                            Middle Name:
                        </div>
                        <div class="col-lg-8">
                            ${user.middleName}
                        </div>
                   </p>
                </div>
                <div class="row col-lg-12">
                    <p>
                        <div class="col-lg-4">
                            Last Name:
                        </div>
                        <div class="col-lg-8">
                            ${user.lastName}
                        </div>
                    </p>
                </div>
            </div>
        </div>
        <%@include file="/jsps/universals/footer.jsp"%>
    </body>
</html>
