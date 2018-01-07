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
<h4><a>------------myWishList.jsp------------</a></h4>
<h2><center>위시리스트 목록 ----총결제예정금액 : ${vo1.wish_amount}</center></h2>
		<table style="width:1000px" align="center">
			<tr>
				<th style="width:4%">작업</th>
				<th style="width:4%">위시리스트번호</th>
				<th style="width:4%">책번호</th>
				<th style="width:6%">제목</th>
				<th style="width:4%">지은이</th>
				<th style="width:4%">출판사</th>
				<th style="width:4%">가격</th>
				<th style="width:4%">사고싶은 수량</th>
			</tr>
	<c:if test="${totalCnt > 0}"> <!-- 글이 존재할때 totalCnt > 0  -->
		<c:forEach var="vo" items="${bigVo}">
			<tr>
				<td align="center" style="width:3%" > 
					<input class="inputbutton" type="button" name="chk_buy" value="바로구매" onclick="window.location='buybookPro?book_seq=${vo.book_seq}&quantity=${vo.quantity}&wishlist_seq=${vo.wishlist_seq}'">
					<input class="inputbutton" type="button" name="chk_delete" value="삭제" maxlength="3" onclick="window.location='deletewishlistPro?wishlist_seq=${vo.wishlist_seq}'">
				</td>
				<td align="center"> 
						위시리스트ID : ${vo.wishlist_seq}
				</td>
				<td align="center"> 
						책ID : ${vo.book_seq}
				</td>
				<td align="center"> <!-- 책제목 -->
						${vo.book_name}
				</td>
				<td align="center"> <!-- 지은이 -->
						${vo.author}
				</td>
				<td align="center"> <!-- 출판사 -->
						${vo.publisher}
				</td>
				<td align="center"> <!-- 단가 -->
						${vo.price}
				</td>
				<td align="center"> <!-- 사고싶은 수량 -->
					<input type="text" name="quantity" value="${vo.quantity}">
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<!------------------------------------------------------------------------->
	<c:if test="${totalCnt == 0}"> <!-- 글이 존재하지 않을때 -->
		
			<tr>
				<th colspan="11" align="center" style="height:25px">
					현재 재고가 없습니다! 관리자에게 문의하세요!
				</th>
				
			</tr>

	</c:if>	
	<!-- 관리자 재고 부분 (번호옆에 체크박스 만들기) -->
	
	
	<!------------------------페이지 처리 부분-------------------------->
	<c:if test="${totalCnt > 0}">
		<table style="width:1000px" align="center">
			<tr>
				<th>
					<c:if test="${pageNumStart > pageBlock}">
						<a href="mywishlist">[처음으로]</a>
						<a href="mywishlist?pageNum=${pageNumStart-pageBlock}">[◀]</a>
					</c:if>	
					
					<!-- 복사한거라..다시 공부 -->
					<c:forEach var="i" begin="${pageNumStart}" end="${pageNumEnd}">
			    		<c:if test="${i == currentPage}">
			    			<span><b>[${i}]</b></span>
			    		</c:if>
			    		<c:if test="${i != currentPage}">
			    			<a href="mywishlist?pageNum=${i}">[${i}]</a>
			    		</c:if>
		    		</c:forEach>
					
					
					<c:if test="${pageTotalCnt > pageNumEnd}">
						<a href="mywishlist?pageNum=${pageNumStart + pageBlock}">[▶]</a>
						<a href="mywishlist?pageNum=${pageTotalCnt}">[마지막으로]</a>
					</c:if>
				</th>
			</tr>
		</table>
	</c:if>
-----------------------------------
</body>
</html>