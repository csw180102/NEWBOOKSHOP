<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}bookboard.css">
<html>
<body>
	<h2><center>${vo.book_name}</center></h2>
	<table align="center">
		<tr>
			<th style="width:150px"> 글번호 </th>
			<td style="width:150px"> ${articleNum} </td>
			<th style="width:150px"> 책이름 </th>
			<td style="width:150px"> ${vo.book_name} </td>
		</tr>
		<tr>
			<th style="width:150px"> 지은이 </th>
			<td style="width:150px"> ${vo.author} </td>
			<th style="width:150px"> 출판사 </th>
			<td style="width:150px"> ${vo.publisher}</td>
		</tr>
		<tr>
			<th style="width:150px"> 가격 </th>
			<td style="width:150px"> ${vo.price} </td>
			<th style="width:150px"> 수량 </th>
			<td style="width:150px"> ${vo.stock} </td>
		</tr>
		<tr>
			<th > 책소개 </th>
			<td colspan="3"> ${vo.content} </td>
		</tr>
		<tr>
			<th colspan="4"><!-- onclick으로 이동할땐 window.location -->
				<input class="inputButton" type="button" value="목록보기" onclick="window.location='booklist?pageNum=${pageNum}'">
				<input class="inputButton" type="button" value="위시리스트" onclick="window.location='wishlist?book_seq=${vo.book_seq}'">
			</th>
		</tr>
	</table>
</body>
</html>