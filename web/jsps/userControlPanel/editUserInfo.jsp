<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 1/11/2017
  Time: 4:37 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit Info</title>
    </head>
    <body>
        <%@include file="/jsps/universals/header.jsp"%>
        <div class="container">
            <div class="col-lg-3 text-center">
                
                <div class="row">
                    <c:choose>
                        <c:when test="${user.imageUrl == null}">
                            <img id="profileImg" src="/contentFiles/imgs/user-200.png" class="thumbnail">
                        </c:when>
                        <c:otherwise>
                            <img id="profileImg" src="${user.imageUrl}">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="row text-center">

                </div>
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
