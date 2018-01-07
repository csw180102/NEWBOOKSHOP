<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}join.css">
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
<html>
<body>
<h2><center>위시리스트 폼</center></h2>
	<form action="wishlistPro?book_seq=${vo.book_seq}" method="post" name="wishlistform">
		<table style="width:800px" align="center">
			<tr>
				<th style="width:7%">책번호</th>
				<th style="width:10%">제목</th>
				<th style="width:5%">지은이</th>
				<th style="width:5%">출판사</th>
				<th style="width:5%">가격</th>
				<th style="width:4%">주문수량</th>
				<th style="width:5%">위시리스트 담기</th>
			</tr>
			<tr>
				<td align="center"> 
						<input class="class" type="text" name="book_seq" value="${vo.book_seq}" readonly>
				</td>
				<td align="center"> <!-- 책제목 -->
						<input class="class" type="text" name="book_name" value="${vo.book_name}" readonly>
				</td>
				<td align="center"> <!-- 지은이 -->
						<input class="class" type="text" name="author" value="${vo.author}" readonly>
				</td>
				<td align="center"> <!-- 출판사 -->
						<input class="class" type="text" name="publisher" value="${vo.publisher}" readonly>
				</td>
				<td align="center"> <!-- 단가 -->
						<input class="class" type="text" name="price" value="${vo.price}" readonly>
				</td>
				<td align="center"> 
						<input type="text" name="quantity" maxlength="5">
				</td>
				<td align="center"> 
						<input type="submit" value="담기" maxlength="5">
						<input class="inputbutton" type="button" value="책리스트로" onclick="window.location='booklist'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>