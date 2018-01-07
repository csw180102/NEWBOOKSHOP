<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
<%
	int cnt = (Integer)request.getAttribute("deleteCnt");
	
	if(cnt>=1) {
		request.getSession().invalidate();
		
%>	
		<script type="text/javascript">
			alert("회원탈퇴 성공")
			parent.document.location.reload();
			
		</script>
<%		
	} else if(cnt<1) {
%>
		<script type="text/javascript">
			errorAlert(deleteError);
			window.history.back;
		</script>
<%
	}	
%>	
</body>
</html>