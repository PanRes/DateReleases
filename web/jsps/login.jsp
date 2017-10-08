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
            <!-- Latest compiled and minified CSS -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

            <!-- Optional theme -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

            <!-- Latest compiled and minified JavaScript -->
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

            <h1 align="center">
                Date Releases
            </h1>
            <%
                String user = (String) session.getAttribute("user");
                if (!(user == null)){
                    response.sendRedirect("/mainMenu");
                    return;
                }
            %>
        </header>
        <article>
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
        <footer>

        </footer>
    </body>
</html>
