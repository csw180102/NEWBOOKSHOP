<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}stocklist.css">

<html>
<body>
	<c:if test="${updateCnt >= 1}">
		<c:if test="${insertCnt == 1 && deleteCnt == 1}">
			<c:if test="${insertAccountCnt == 1}">
				<script type="text/javascript">
					//완료 되었으면 알럴 , stocklist로 백
					alert("최종 승인 성공!(최종결산테이블에도 insert성공)");
					window.location="finaladminorder";
				</script>
			</c:if>
		</c:if>
	</c:if>
	<c:if test="${updateCnt < 1}">
		<script type="text/javascript">
			alert("재고가 모자릅니다! 최종 승인 실패!");
			window.history.back();
		</script>
	</c:if>
</body>
</html>