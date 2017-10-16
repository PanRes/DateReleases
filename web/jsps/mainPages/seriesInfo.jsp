<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 16/10/2017
  Time: 5:24 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:useBean id="series" scope="page" class="gr.pr.datereleases.models.SeriesModel"/>
        <title>${series.name} Info</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-4">
                <c:choose>
                    <c:when test="${series.imageUrl !=null}">
                        <img src="${series.imageUrl}" class="thumbnail text-center"/>
                    </c:when>
                    <c:otherwise>
                        <img src="/imgs/not-found.png" class="thumbnail text-center"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-lg-8 text-left">
                <div class="row">
                    <strong>SeriesName:</strong> ${series.name}
                </div>
                <div class="row">
                    <strong>First Aired:</strong>
                    <fmt:formatDate value="${series.dateStarted}" pattern="dd/MM/yyyy"/>
                </div>
                <div class="row">
                    <strong>Channel:</strong> ${series.channel}
                </div>
                <div class="row">
                    <c:choose>
                        <c:when test="${!series.ended}">
                                Still going!
                            </div>
                            <div class="row">
                                <strong>Next Episode:</strong> TBA
                        </c:when>
                        <c:otherwise>
                            Has Ended!
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>
