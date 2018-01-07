<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<script src="${comJs}managementScript.js"></script>
<html>
<%
	int updateCnt = (Integer)request.getAttribute("updateCnt");

	if(updateCnt==1) {
%>
		<script type="text/javascript">
			parent.document.location.reload();
			alert("정보수정완료!");
		</script>
<%
	} else {
%>
		<script type="text/javascript">
			errorAlert(updateError);
			window.history.back();
		</script>
<%	
	}
%>
<body>
	
</body>
</html>