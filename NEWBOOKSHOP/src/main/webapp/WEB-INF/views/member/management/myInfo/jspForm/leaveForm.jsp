<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}join.css">
<script src="${comJs}managementScript.js"></script>
<html>
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
<body onload="leaveFocus();">
	<form action="deletePro" method="post" name="deleteform" onsubmit="return leaveCheck();">
		<table>
			<tr>
				<th colspan="2">탈퇴하기시 전에 확인한번!</th>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input class="input" type="password" name="pwd" maxlength="20"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="inputButton" type="submit" value="전송">
					<input class="inputButton" type="reset" value="취소" onclick="parent.document.location.reload();">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>