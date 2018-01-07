<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}freeboard.css">
<html>
<body>
	<c:if test="${insertCnt == 1}">
		<script type="text/javascript">
			alert("글쓰기 성공!");
			window.location="freeboard";
		</script>
	</c:if>
	<c:if test="${insertCnt != 1 }">
		<script type="text/javascript">
			alert("글쓰기 실패!");
			window.history.back();
		</script>
	</c:if>
</body>
</html>