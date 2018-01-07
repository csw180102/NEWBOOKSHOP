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
<html>
<body>
<h3><a>----------adminOrderListForm.jsp-----------</a></h3>
<h2><center>구매 신청 목록----총금액 : ${vo1.order_amount}</center></h2>
		<table style="width:1000px" align="center">
			<tr>
				<th style="width:7%">작업</th>
				<th style="width:7%">구매번호</th>
				<th style="width:10%">회원ID</th>
				<th style="width:5%">책번호</th>
				<th style="width:5%">구매날짜</th>
				<th style="width:10%">주문수량</th>
			</tr>
	<c:if test="${totalCnt > 0}"> <!-- 글이 존재할때 totalCnt > 0  -->
		<c:forEach var="vo" items="${bigVo}">
			<tr>
				<td align="center" style="width:3%" > <!-- 주문승인/거절 --> 
					<input class="inputbutton" type="button" name="chk_approve" value="승인" onclick="window.location='insertadminorderPro?buy_seq=${vo.buy_seq}&book_seq=${vo.book_seq}'">
					<input class="inputbutton" type="button" name="chk_reject" value="거절" onclick="window.location='rejectadminorderPro?buy_seq=${vo.buy_seq}'">
				</td>
				<td align="center"> <!-- 구매번호 -->
						${vo.buy_seq}
				</td>
				<td align="center"> <!-- 회원ID -->
						${vo.id}
				</td>
				<td align="center"> <!-- 책번호 -->
						${vo.book_seq}
				</td>
				<td align="center"> <!-- 구매날짜 -->
						${vo.buy_date}
				</td>
				<td align="center"> <!-- 주문수량 -->
						${vo.quantity}
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
		</table>
	</c:if>	
	<!-- 관리자 재고 부분 (번호옆에 체크박스 만들기) -->
	
	
	<!------------------------페이지 처리 부분-------------------------->
	<c:if test="${totalCnt > 0}">
		<table style="width:1000px" align="center">
			<tr>
				<th>
					<c:if test="${pageNumStart > pageBlock}">
						<a href="approveorder">[처음으로]</a>
						<a href="approveorder?pageNum=${pageNumStart-pageBlock}">[◀]</a>
					</c:if>	
					
					<!-- 복사한거라..다시 공부 -->
					<c:forEach var="i" begin="${pageNumStart}" end="${pageNumEnd}">
			    		<c:if test="${i == currentPage}">
			    			<span><b>[${i}]</b></span>
			    		</c:if>
			    		<c:if test="${i != currentPage}">
			    			<a href="approveorder?pageNum=${i}">[${i}]</a>
			    		</c:if>
		    		</c:forEach>
					
					
					<c:if test="${pageTotalCnt > pageNumEnd}">
						<a href="approveorder?pageNum=${pageNumStart + pageBlock}">[▶]</a>
						<a href="approveorder?pageNum=${pageTotalCnt}">[마지막으로]</a>
					</c:if>
				</th>
			</tr>
		</table>
	</c:if>
-----------------------------------
</body>
</html>