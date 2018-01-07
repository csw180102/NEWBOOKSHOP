<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<%@ page import="java.net.InetAddress" %>
<link type="text/css" rel="stylesheet" href="${comCss}freeboard.css">
<%
	String sessionId = (String) request.getSession().getAttribute("sessionId");

%>
<html>
<body>
<h2><center>새글쓰기 / 페이지 번호 : ${pageNum}</center></h2>

	<form action="insertpostPro" method="post" name="writeform">
	 <!-- 이건 만들어 놓으면 별다른 조작없이 submit됨 -->
	 <input type="hidden" name="board_seq" value="${board_seq}">
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="ref_step" value="${ref_step}">
	<input type="hidden" name="ref_level" value="${ref_level}">
		<table align="center">
			<tr>
				<th>작성자id</th>
				<td>
					<input class="input" type="text" name="id" maxlength="20" value="${sessionId}" readonly>
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
				<th colspan="2">
					<input class="inputButton" type="submit" value="게시글 등록">
					<input class="inputButton" type="reset" value="등록취소" 
					onclick="window.location='freeboard?pageNum=${pageNum}'">
				</th>
			</tr>
		</table>
	</form>
</body>
</html>