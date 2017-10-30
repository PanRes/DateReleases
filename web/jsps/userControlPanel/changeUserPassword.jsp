<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 30/10/2017
  Time: 4:50 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Change Password</title>
    </head>
    <body>
        <%@ include file="/jsps/universals/header.jsp"%>
        <div class="container text-center">
            <h4>${user} Change Password</h4>
            <form class="form-horizontal" method="post" action="/ChangePasswordServlet">
                <div class="row form-group">
                    <div class="col-lg-2 col-lg-offset-3">
                        Old Password:
                    </div>
                    <div class="col-lg-4">
                        <input type="password" name="oldPassword" class="form-control" required>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-2 col-lg-offset-3">
                        New Password:
                    </div>
                    <div class="col-lg-4">
                        <input type="password" name="newPassword" id="newPassword"
                               class="form-control" required>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-2 col-lg-offset-3">
                        Verify New Password:
                    </div>
                    <div class="col-lg-4">
                        <input type="password" name="newPasswordVerify" id="newPasswordVerify"
                               class="form-control" required>
                    </div>
                </div>
            </form>
        </div>

        <%@include file="/jsps/universals/footer.jsp"%>
    </body>
</html>

<script type="application/javascript">
    $("#newPasswordVerify").change(function(){
       if ($("#newPasswordVerify").value() != $("#newPassword").value()){
           console.log("inside if");
           alert("Both new password must be the same!");
       }
    });
</script>
