<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}stocklist.css">

<html>
<body>
	<c:if test="${deleteCnt == 1}">
		<script type="text/javascript">
			//완료 되었으면 알럴 , stocklist로 백
			alert("구매 거절 성공!");
			window.location="finaladminorder";
		</script>
	</c:if>
	<c:if test="${deleteCnt != 1 }">
		<script type="text/javascript">
			alert("구매 거절 실패!");
			window.history.back();
		</script>
	</c:if>
</body>
</html>