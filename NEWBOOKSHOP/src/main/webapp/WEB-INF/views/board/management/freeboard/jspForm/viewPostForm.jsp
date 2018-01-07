<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}bookboard.css">
<html>
<body>
	<h2><center>게시글 상세 페이지</center></h2>
		<table align="center">
		<tr>
			<th style="width:150px"> 글번호/실제DB상 board_seq </th>
			<td style="width:150px"> ${articleNum} / ${vo.board_seq }</td>
			<th style="width:150px"> 조회수 </th>
			<td style="width:150px"> ${vo.readCnt} </td>
		</tr>
		<tr>
			<th style="width:150px"> 작성자 </th>
			<td style="width:150px"> ${vo.id} </td>
			<th style="width:150px"> 작성일 </th>
			<td style="width:150px">
				<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${vo.reg_date}"/>
			</td>
		</tr>
		<tr>
			<th > 글제목 </th>
			<td colspan="3"> ${vo.subject} </td>
		</tr>
		<tr>
			<th > 내용 </th>
			<td colspan="3"> ${vo.content} </td>
		</tr>
		<tr>
			<th > ip주소 </th>
			<td colspan="3"> ${vo.ip} </td>
		</tr>
		<tr>
			<th colspan="4"><!-- onclick으로 이동할땐 window.location -->
				<input class="inputButton" type="button" value="수정" 
				onclick="window.location='updatepostform?board_seq=${vo.board_seq}'"> <!-- 여기서  pageNum은 수정마치고 돌아갈 페이지임  -->
				
				<input class="inputButton" type="button" value="삭제" 
				onclick="window.location='deletepostPro?board_seq=${vo.board_seq}'">
				
				<input class="inputButton" type="button" value="리플"
				onclick="window.location='writepostform?board_seq=${vo.board_seq}&ref=${vo.ref}&ref_level=${vo.ref_level}&ref_step=${vo.ref_step}'">
				
				<input class="inputButton" type="button" value="목록보기" 
				onclick="window.location='freeboard?pageNum=${pageNum}'">
			</th>
		</tr>
	</table>
</body>
</html>