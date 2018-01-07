<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<html>
<body>
	<c:if test="${deleteCnt >= 1}">
		<script type="text/javascript">
			//완료 되었으면 알럴 , stocklist로 백
			alert("구매목록 삭제 성공!");
			window.location="myorderlist";
		</script>
	</c:if>
	<c:if test="${deleteCnt < 1 }">
		<script type="text/javascript">
			alert("구매목록 삭제 실패!");
			window.location="booklist";
		</script>
	</c:if>
</body>
</html>
