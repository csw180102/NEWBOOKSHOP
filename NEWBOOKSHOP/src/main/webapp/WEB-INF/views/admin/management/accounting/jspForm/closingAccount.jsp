<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<%
	if(request.getSession().getAttribute("sessionId")!=null) {
		out.println("세션 연결 상태...YES");
	} else {
%>		
		<script type="text/javascript">
		alert("접근할 수 없습니다 로그인 후 이용해주세요!");
		location.replace("/bms/main");
		</script>
<%
	}
%>
<html>
<body>
	<table border="1">
			<tr>
				<th colspan="2">월별 순이익</th>
			</tr>
	<c:forEach var="vo" items="${bigVo}">
	<c:set var="month" value="${month+1}" />
		<c:if test="${month<13}">
			<tr>
				<th>${month}월</th>
				<td>${vo.month_sales_inflow - vo.month_sales_outflow}</td>
			</tr>
		</c:if>
	</c:forEach>
	</table>
</body>
</html>