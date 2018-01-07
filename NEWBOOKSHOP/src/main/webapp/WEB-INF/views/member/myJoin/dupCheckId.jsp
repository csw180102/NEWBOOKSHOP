<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../PATH.jsp" %>
<script src="${comJs}memberScript.js"></script>
<link type="text/css" rel="stylesheet" href="${comCss}join.css">
<html>
<body>

	<c:if test="${cnt==1}">
	<form action="dupPro.member" method="post" name="confirmform" onsubmit="return dupAgain();">
	   <table>
			<tr>
				<th colspan="2">${strId}는 사용 할 수 없는 아이디 입니다</th>
			</tr>
			<tr>
				<th>아이디 : </th>
				<td><input class="input" type="text" name="id" maxlength="20" style="width:100px"></td>
			</tr>
			<tr>
				<th colspan="2">
				<input class="inputButton" type="submit" value="확인">
				<input class="inputButton" type="reset" value="취소" onclick="self.close();">
				</th>
			</tr>
		</table>
	</form>
	</c:if>

	<c:if test="${cnt!=1}">
		<table>
			<tr>
				<th colspan="2">사용 가능한 아이디 입니다</th>
			</tr>
			<tr>
				<th colspan="2">
				<input class="inputButton" type="submit" value="사용하기" onclick="setId('${strId}');">
				<input class="inputButton" type="reset" value="취소" onclick="self.close();">
				</th>
			</tr>
		</table>
	</c:if>
	
</body>
</html>