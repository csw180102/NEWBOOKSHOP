<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}stocklist.css">

<html>
<body>
	<c:if test="${insertCnt == 1}">
		<script type="text/javascript">
			//완료 되었으면 알럴 , stocklist로 백
			alert("재고 추가 성공!");
			window.location="stocklist";
		</script>
	</c:if>
	<c:if test="${insertCnt != 1 }">
		<script type="text/javascript">
			alert("재고 추가 실패!");
			window.history.back();
		</script>
	</c:if>
</body>
</html>