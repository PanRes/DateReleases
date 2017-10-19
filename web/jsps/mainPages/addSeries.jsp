<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 18/10/2017
  Time: 5:08 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add New Series</title>
    </head>
    <body>
        <%@include file="/jsps/universals/header.jsp"%>
        <c:choose>
            <c:when test="${successCreation == 'success'}">
                <div class="alert alert-success text-center">
                    <h4>Series successfully Created</h4>
                </div>
            </c:when>
            <c:when test="${successCreation == 'exists'}">
                <div class="alert alert-warning text-center">
                    <h4>Series Already Exists</h4>
                </div>
            </c:when>
            <c:when test="${successCreation == 'fail'}">
                <div class="alert alert-danger text-center">
                    <h4>Series failed to be Created</h4>
                </div>
            </c:when>
        </c:choose>

        <form action="/AddSeriesServlet" method="post" name="frmAddSeries" enctype="multipart/form-data">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <h5>Series Name:</h5>
                    </div>
                    <div class="col-lg-9 form-group">
                        <input type="text" name="seriesName" id="seriesName" class="form-control" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3">
                        <h5>Series Premiere:</h5>
                    </div>
                    <div class="col-lg-9 form-group">
                        <input type="date" name="seriesPremiere" class="form-control">
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3">
                        <h5>Series Channel:</h5>
                    </div>
                    <div class="col-lg-9 form-group">
                        <input type="text" name="seriesChannel" class="form-control">
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3">
                        <h5>Ended?:</h5>
                    </div>
                    <div class="col-lg-9 form-group">
                        <label class="radio-inline">
                            <input type="radio" name="seriesEnded" id="radioEnded" value="0" required> Series has ended
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="seriesEnded" id="radioGoing" value="1" required> Series still going
                        </label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3">
                        <h5>Series Image:</h5>
                    </div>
                    <div class="col-lg-9">
                        <label class="btn btn-warning">
                            <span class="glyphicon glyphicon-upload"></span>
                            Choose Image for <small id="seriesNamebtn">new Series</small>
                            <input type="file" name="imgUrl" id="imgUrl" accept="image/*">
                        </label>
                        <small id="fileName"></small>
                    </div>
                </div>
                <div class="row text-center">
                    <input type="submit" class="btn btn-success btn-lg" value="Save Changes">
                </div>
            </div>
        </form>
        <%@include file="/jsps/universals/footer.jsp"%>
    </body>
</html>

<script type="application/javascript">
    $("#seriesName").change(function () {
        document.getElementById("seriesNamebtn").innerHTML = this.value();
    });

    $("#imgUrl").change(function () {
        var fileName = this.value;
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length);
        document.getElementById("fileName").innerHTML = fileName;
    });
</script>
