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
            <%@include file="/jsps/universals/inclusions.jsp"%>

            <h1 align="center">
                Date Releases
            </h1>
        </header>
        <article id="content">
            <%--<%
                String message = request.getParameter("wrongUser");
                if (message != null && message.equals("true")){
            %>

            <div class="alert alert-warning">
                <strong>Warning!</strong> The User Name or the Password is incorrect. Please try again.
            </div>
            <%
                }
            %>--%>
            <h2 class="page-header" align="center">Please Login</h2>
            <form class="form-horizontal" action="/Authenticate" name="loginForm" method="post">
                <div align="center">
                    <div class="form-group">
                        <label class="col-md-4">User Name : </label>
                        <div class="col-md-4">
                            <input type="text" name="userName" class="form-control" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4">Password</label>
                        <div class="col-md-4">
                            <input type="password" name="password" class="form-control" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4 col-md-offset-2">
                            <input type="submit" name="btnSubmit" class="btn btn-primary btn-lg" value="Login">
                        </div>
                    </div>
                </div>
            </form>
        </article>
        <footer id="footer">
            <%@include file="/jsps/universals/footer.jsp"%>
        </footer>
    </body>
</html>
