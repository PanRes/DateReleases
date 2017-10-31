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
            <c:choose>
                <c:when test="${success == 'success'}">
                    <div class="alert alert-success">
                        Successfully Changed Password
                    </div>
                </c:when>
                <c:when test="${success == 'wrongPassword'}">
                    <div class="alert alert-danger">
                        <strong>The old Password given was wrong!</strong> Please Try again
                    </div>
                </c:when>
                <c:when test="${success == 'differentPasswords'}">
                    <div class="alert alert-warning">
                        <strong>The new Passwords did not match,</strong> Please Try again
                    </div>
                </c:when>
            </c:choose>
            <form class="form-horizontal" method="post" action="/ChangePasswordServlet">
                <div class="row form-group">
                    <div class="col-lg-2 col-lg-offset-3 vCenter">
                        Old Password:
                    </div>
                    <div class="col-lg-4">
                        <input type="password" name="oldPassword" class="form-control" required>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-2 col-lg-offset-3 vCenter">
                        New Password:
                    </div>
                    <div class="col-lg-4">
                        <input type="password" name="newPassword" id="newPassword"
                               class="form-control" required>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-2 col-lg-offset-3 vCenter vCenter">
                        Verify New Password:
                    </div>
                    <div class="col-lg-4">
                        <input type="password" name="newPasswordVerify" id="newPasswordVerify"
                               class="form-control" required>
                    </div>
                    <div class="col-lg-3">
                       <div class="pull-left">
                            <small class="text-red" id="verifyMessage"></small>
                       </div>
                    </div>
                </div>
                <div class="row form-group">
                    <input type="submit" class="btn btn-success btn-lg disabled"
                           id="btnChangePassword" value="Change Password" disabled>
                </div>
            </form>
        </div>

        <%@include file="/jsps/universals/footer.jsp"%>
    </body>
</html>

<script type="application/javascript">
   /* $("#newPasswordVerify").change(function(){
       if ($("#newPasswordVerify").value() != $("#newPassword").value()){
           console.log("inside if");
           alert("Both new password must be the same!");
       }
    });*/

    $("#newPasswordVerify").change(function () {
        var verifyMessage = "New Passwords must be the same"
        if ($("#newPassword").val() != "") {
            if($("#newPasswordVerify").val() != $("#newPassword").val()){
                document.getElementById("verifyMessage").innerHTML = verifyMessage;
                $("#btnChangePassword").addClass("disabled");
                $("#btnChangePassword").prop("disabled",true);
            }
            else {
                document.getElementById("verifyMessage").innerHTML = "";
                $("#btnChangePassword").removeClass("disabled");
                $("#btnChangePassword").prop("disabled",false);
            }
        }
    });

    $("#newPassword").change(function () {
        var verifyMessage = "New Passwords must be the same"
        if ($("#newPasswordVerify").val() != "") {
            if($("#newPasswordVerify").val() != $("#newPassword").val()){
                document.getElementById("verifyMessage").innerHTML = verifyMessage;
                $("#btnChangePassword").addClass("disabled");
                $("#btnChangePassword").prop("disabled",true);
            }
            else {
                document.getElementById("verifyMessage").innerHTML = "";
                $("#btnChangePassword").removeClass("disabled");
                $("#btnChangePassword").prop("disabled",false);
            }
        }
    });
</script>
