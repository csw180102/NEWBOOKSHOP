<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}freeboard.css">
<html>
<body>
	<h2><center>게시글 수정 페이지</center></h2>

	<form action="updatepostPro.bo?board_seq=${vo.board_seq}" method="post" name="writeform">
	<input type="hidden" name="num" value="${vo.board_seq}"> <!-- 이건 만들어 놓으면 별다른 조작없이 submit됨 -->
	<input type="hidden" name="ref" value="${vo.ref}">
	<input type="hidden" name="ref_step" value="${vo.ref_step}">
	<input type="hidden" name="ref_level" value="${vo.ref_level}">
		<table align="center">
			<tr>
				<th>게시물id</th>
				<td>
					<input class="input" type="text" name="board_seq" maxlength="20" value="${vo.board_seq}" disabled>
				</td>
			</tr>
			<tr>
				<th>작성자id</th>
				<td>
					<input class="input" type="text" name="id" maxlength="20" value="${vo.id}" disabled>
				</td>
			</tr>
			<tr>
				<th>제  목</th>
				<td>
					<input class="input" type="text" name="subject" maxlength="50" style="width:270px">
				</td>
			</tr>
			<tr>
				<th>내  용</th>
				<td>
					<textarea class="input" rows="10" cols="40" name="content" maxlength="200" style="width:270px"></textarea>
				</td>
			</tr>
			<tr>
				<th>등록날짜</th>
				<td>
					<input class="input" type="text" name="reg_date" maxlength="50" style="width:270px" value="${vo.reg_date}" disabled>
				</td>
			</tr>
			<tr>
				<th>ip주소</th>
				<td>
					<input class="input" type="text" name="ip" maxlength="50" style="width:270px" value="${vo.ip}">
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="inputButton" type="submit" value="수정하기">
					<input class="inputButton" type="reset" value="수정취소" 
					onclick="window.location='freeboard.bo'">
				</th>
			</tr>
		</table>
	</form>
</body>
</html>