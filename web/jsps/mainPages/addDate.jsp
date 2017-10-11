<%@ page import="gr.pr.datereleases.hibernatetools.SeriesTools" %>
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
        <title>Add Date for Episode</title>
    </head>
    <body>
        <header>
            <%@include file="/jsps/universals/header.jsp"%>
        </header>
        <article>
            <c:set var="success" value='<%=request.getParameter("success")%>'/>
            ${param.success}
            <%
                System.out.println(request.getAttribute("success"));
            %>
            <c:choose>
                <c:when test="${success == true}">
                    <div class="alert alert-success">
                        Successfully Added Date!
                    </div>
                </c:when>
                <c:when test="${success == false}">
                    <div class="alert alert-success">
                        Failed to Add Date!
                    </div>
                </c:when>
            </c:choose>
            </div>
            <div class="col-lg-12 container text-center">
                <div class="row">
                    <div class="col-lg-5">
                        <h4>Series</h4>
                    </div>
                    <div class="col-lg-2">
                        <h4>Season</h4>
                    </div>
                    <div class="col-lg-2">
                        <h4>Episode</h4>
                    </div>
                    <div class="col-lg-3">
                        <h4>Date</h4>
                    </div>
                </div>
                <form action="/AddDateServlet" method="post" name="frmAddDate">
                    <div class="row">
                        <div class="form-group col-lg-5">
                            <select name="seriesId" required="required">
                                <c:forEach var="series" items="<%=SeriesTools.getAllSeries()%>">
                                    <option value="${series.seriesId}">${series.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-lg-2">
                            <input type="number" min="1" name="season" required="required">
                        </div>
                        <div class="form-group col-lg-2">
                            <input type="number" min="0" name="episode" required="required">
                        </div>
                        <div class="form-group col-lg-3">
                            <input type="date" name="date" class="date" required="required">
                        </div>
                    </div>
                    <div class="row">
                        <input type="submit" class="btn btn-success" value="Submit Date" align="center">
                    </div>
                </form>
            </div>
        </article>
    </body>
</html>
