<%--
  Created by IntelliJ IDEA.
  User: pressos
  Date: 6/10/2017
  Time: 11:40 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
	<title>Main Menu</title>
</head>
<body>
	<%@include file="/WEB-INF/view/universals/header.jsp"%>
	<div class="row text-center">
		<p>
			<a href="/series/schedule?series=allSeries" class="btn btn-default text-center">
				<spring:message code="series.all.schedule.view"/>
			</a>
		</p>


		<jsp:include page="/WEB-INF/view/utils/seriesUtil/series.jsp"/>
	</div>
	<%@include file="/WEB-INF/view/universals/footer.jsp"%>
</body>
</html>
