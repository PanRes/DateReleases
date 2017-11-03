<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %>
<%@ page import="gr.pr.datereleases.hibernatetools.SeriesEpisodesTools" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PR
  Date: 09-Oct-17
  Time: 9:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>${pageContext.request.servletPath == 'edit' ? "Edit" : "Add"} Date for Episode</title>
    </head>
    <body>
        <%@include file="/jsps/universals/header.jsp"%>

        <c:choose>
            <c:when test="${pageContext.request.servletPath == '/editSeriesDate'}">
                <h3 class="text-center"><u>Edit episode release date</u></h3>
                <c:choose>
                    <c:when test="${param.success == true}">
                        <div class="alert alert-success text-center">
                            Successfully Edited Date!
                        </div>
                    </c:when>
                    <c:when test="${param.success == false}">
                        <div class="alert alert-danger text-center">
                            Failed to Edit Date!
                        </div>
                    </c:when>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h3 class="text-center"><u>Add single episode release date or add upload xlsx file to add multiple</u></h3>
                <c:choose>
                    <c:when test="${param.success == true}">
                        <div class="alert alert-success text-center">
                            Successfully Added Date!
                        </div>
                    </c:when>
                    <c:when test="${param.success == false}">
                        <div class="alert alert-danger text-center">
                            Failed to Add Date!
                        </div>
                    </c:when>
                </c:choose>
            </c:otherwise>
        </c:choose>
        </div>
        <div class="col-lg-12 container text-center">
            <div class="row">
                <div class="col-lg-3">
                    <h4>Series</h4>
                </div>
                <div class="col-lg-1">
                    <h4>Season</h4>
                </div>
                <div class="col-lg-1">
                    <h4>Episode</h4>
                </div>
                <div class="col-lg-2">
                    <h4>Date</h4>
                </div>
                <div class="col-lg-4">
                    <h4>Notes</h4>
                </div>
                <div class="col-lg-1">
                    <h4>Submit</h4>
                </div>
            </div>
            <c:choose>
                <c:when test="${pageContext.request.servletPath == '/editSeriesDate'}">
                    <%@include file="/jsps/utils/seriesUtil/editSeriesDate.jsp"%>
                </c:when>
                <c:otherwise>
                    <jsp:include page="/jsps/utils/seriesUtil/addSeriesDateForms.jsp"/>
                </c:otherwise>
            </c:choose>
        </div>
        <%@include file="/jsps/universals/footer.jsp"%>
    </body>
</html>
