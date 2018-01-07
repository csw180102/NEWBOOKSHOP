<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}stocklist.css">
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
<script src="${comJs}commonScript.js"></script>
<html>
<body> <!-- 재고 추가입력다하고 insertstockPro.book으로 진입한다 -->

	<form action="insertstockPro" method="post" name="insertform" enctype="multipart/form-data">

		<table align="center">
		<tr>
			<th style="width:150px"> 이미지삽입 </th>
			<td style="width:150px"> 
				<input class="inputButton" type="file" name="image">
			</td>
			<th style="width:150px"> 책이름 </th>
			<td style="width:150px"> <input type="text" name="book_name"> </td>
		</tr>
		<tr>
			<th style="width:150px"> 분류 </th>
			<td style="width:150px"> 
				<select class="input" name="isbn">
					<option value="">선택</option> 
					<option value="소설">소설</option>
					<option value="인문">인문</option>
					<option value="사회">사회</option>
					<option value="과학">과학</option>
					<option value="역사">역사</option>
					<option value="예술">예술</option>
					<option value="종교">종교</option>
					<option value="경제">경제</option>
				</select>
			</td>
		</tr>
		<tr>
			<th style="width:150px"> 국내/해외 </th>
			<td style="width:150px"> 
				<select class="input" name="domeforei">
					<option value="">선택</option> 
					<option value="국내">국내</option>		
					<option value="해외">해외</option>
				</select>
			<th style="width:150px"> 신간/구간 </th>
			<td style="width:150px"> 
				<select class="input" name="newold"> 
					<option value="">선택</option> 
					<option value="신간">신간</option>		
					<option value="구간">구간</option>
				</select>
			</td>
		<tr>
			<th style="width:150px"> 저자 </th>
			<td style="width:150px"> <input type="text" name="author"> </td>
			<th style="width:150px"> 출판사 </th>
			<td style="width:150px"> <input type="text" name="publisher"> </td>
		</tr>
		<tr>
			<th style="width:150px"> 가격 </th>
			<td style="width:150px"> <input type="text" name="price"> </td>
			<th style="width:150px"> 재고량 </th>
			<td style="width:150px"> <input type="text" name="stock"> </td>
		</tr>
		<tr>
			<th > 책소개 </th>
			<td colspan="3"> <textarea rows="5" cols="65" name="content">내용 적기</textarea> </td>
		</tr>
		<tr>
			<th colspan="4"><!-- onclick으로 이동할땐 window.location -->
				<input class="inputButton" type="submit" value="추가하기">
			</th>
		</tr>
	</table>
	</form>
</body>
</html>