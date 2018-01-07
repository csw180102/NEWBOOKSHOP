<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}stocklist.css">
<html>

<body>
	<c:if test="${updateCnt == 1}">
		<c:if test="${insertAccountCnt==1}">
			<script type="text/javascript">
				alert("환불 목록으로 이동 성공");
				window.location="refundrequest";
			</script>
		</c:if>
	</c:if>
	<c:if test="${updateCnt != 1 }">
		<script type="text/javascript">
			alert("환불목록이동 실패!(해당 책 자체가 재고목록에서 삭제되었을시");
			window.history.back();
		</script>
	</c:if>
</body>
</html>