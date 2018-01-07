<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../PATH.jsp" %>
<script src="${comJs}memberScript.js"></script>
<link type="text/css" rel="stylesheet" href="${comCss}join.css">
<html>
<body>
<%
	int cnt = (Integer)request.getAttribute("insertCnt");
	
	if(cnt==1) {
%>
		<script type="text/javascript">
			alert("회원가입에 성공하였습니다! 로그인화면으로 돌아갑니다!");
			opener.document.location.reload();
			self.close();



		</script>
<%		
	} else {
%>
		<script type="text/javascript">
			errorAlert(insertError);
		</script>
<%
	}	
%>	
</body>
</html>