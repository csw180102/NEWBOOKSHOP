<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}stocklist.css">

<%
	if(request.getSession().getAttribute("sessionId")==null ||
		!request.getSession().getAttribute("sessionId").equals("host")) {
		request.getSession().setAttribute("sessionId", null);
%>		<script type="text/javascript">
		alert("접근 할 수 없습니다! 로그인 하세요!");
		location.replace("/bms/main"); //바로 서블릿진입점으로 이동
		</script>
<%
	}
%>
<html>
<body>
<h2><center>재고 리스트</center></h2>
		<table style="width:1000px" align="center">
			<tr>
				<th style="width:2%">작업선택</th>
				<th style="width:6%">이미지</th>
				<th style="width:7%">게시물번호</th>
				<th style="width:5%">장르</th>
				<th style="width:10%">제목</th>
				<th style="width:5%">지은이</th>
				<th style="width:5%">출판사</th>
				<th style="width:5%">국내/해외</th>
				<th style="width:5%">신간/구간</th>
				<th style="width:5%">가격</th>
				<th style="width:5%">재고량</th>
				<th style="width:10%">입고일</th>
			</tr>
	<c:if test="${totalCnt > 0}"> <!-- 글이 존재할때 totalCnt > 0  -->
		<c:forEach var="vo" items="${bigVo}">
			<tr>
				<td align="center" style="width:3%" > 
					<input type="button" name="chk_stock" value="재고삭제" onclick="window.location='deletestock?book_seq=${vo.book_seq}'">
					<input type="button" name="chk_stock" value="재고수정" onclick="window.location='updatestock?book_seq=${vo.book_seq}&articleNum=${articleNum}&pageNum=${pageNum}'">
					<input type="button" name="chk_stock" value="재고추가" onclick="window.location='addstock'">
				</td>
				<td align="center"> <!-- 이미지 -->
					<c:if test="${vo.image != null }">
						<img src="${comImg}books/${vo.image}">
					</c:if>
					<c:if test="${vo.image == null }">
						<img src="${comImg}books/noimage.png">
					</c:if>
				</td>
				<td align="center"> <!-- 글번호 부분 -->
						${articleNum} <c:set var="articleNum" value="${articleNum-1}" />
						/책ID : ${vo.book_seq}
				</td>
				<td align="center"> <!-- 분류 음 장르? -->
						${vo.isbn}
				</td>
				<td align="center"> <!-- 책제목 -->
						<a href="stockdetail?book_seq=${vo.book_seq}&pageNum=${pageNum}&articleNum=${articleNum+1}">${vo.book_name}</a>
				</td>
				<td align="center"> <!-- 지은이 -->
						${vo.author}
				</td>
				<td align="center"> <!-- 출판사 -->
						${vo.publisher}
				</td>
				<td align="center"> <!-- 국내/해외구분 -->
						${vo.domeforei}
				</td>
				<td align="center"> <!-- 신간/구간구분-->
						${vo.newold}
				</td>
				<td align="center"> <!-- 단가 -->
						${vo.price}
				</td>
				<td align="center"> <!-- 재고량 -->
						${vo.stock}
				</td>
				<td align="center"> <!-- 입고일 -->
						${vo.receiving_day}
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<!------------------------------------------------------------------------->
	<c:if test="${totalCnt == 0}"> <!-- 글이 존재하지 않을때 -->
		<tr>
			<th colspan="13" align="center" style="height:25px">
				현재 재고가 없습니다! 관리자에게 문의하세요!
			</th>
		</tr>
	</table>
	</c:if>	
	<!-- 관리자 재고 부분 (번호옆에 체크박스 만들기) -->
	
	
	<!------------------------페이지 처리 부분-------------------------->
	<c:if test="${totalCnt > 0}">
		<table style="width:1000px" align="center">
			<tr>
				<th>
					<c:if test="${pageNumStart > pageBlock}">
						<a href="stocklist">[처음으로]</a>
						<a href="stocklist?pageNum=${pageNumStart-pageBlock}">[◀]</a>
					</c:if>	
					
					<!-- 복사한거라..다시 공부 -->
					<c:forEach var="i" begin="${pageNumStart}" end="${pageNumEnd}">
			    		<c:if test="${i == currentPage}">
			    			<span><b>[${i}]</b></span>
			    		</c:if>
			    		<c:if test="${i != currentPage}">
			    			<a href="stocklist?pageNum=${i}">[${i}]</a>
			    		</c:if>
		    		</c:forEach>
					
					
					<c:if test="${pageTotalCnt > pageNumEnd}">
						<a href="stocklist?pageNum=${pageNumStart + pageBlock}">[▶]</a>
						<a href="stocklist?pageNum=${pageTotalCnt}">[마지막으로]</a>
					</c:if>
				</th>
			</tr>
		</table>
	</c:if>
-----------------------------------
</body>
</html>