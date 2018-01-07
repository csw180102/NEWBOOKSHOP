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
<h3><a>---------adminRefundListForm.jsp(관리자가 환불 승인 하는 곳)----------</a></h3>
<h2><center>환불 신청 목록 </center></h2>
		<table style="width:1000px" align="center">
			<tr>
				<th style="width:7%">작업</th>
				<th style="width:7%">환불번호</th>
				<th style="width:10%">회원ID</th>
				<th style="width:5%">책번호</th>
				<th style="width:5%">구매수량</th>
				<th style="width:5%">환불날짜</th>
			</tr>
	<c:if test="${totalCnt > 0}"> <!-- 글이 존재할때 totalCnt > 0  -->
		<c:forEach var="vo" items="${bigVo}">
			<tr>
				<td align="center" style="width:3%" > <!-- 환불 승인 --> 
					<input class="inputbutton" type="button" name="chk_approval" value="승인" onclick="window.location='refundrequestPro?refund_seq=${vo.refund_seq}&book_seq=${vo.book_seq}'">
				</td>
				<td align="center"> <!-- 환불번호 -->
						${vo.refund_seq}
				</td>
				<td align="center"> <!-- 회원ID -->
						${vo.id}
				</td>
				<td align="center"> <!-- 책번호 -->
						${vo.book_seq}
				</td>
				<td align="center"> <!-- 구매수량 -->
						${vo.quantity}
				</td>
				<td align="center"> <!-- 환불날짜 -->
						${vo.refund_date}
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
						<a href="refundrequest">[처음으로]</a>
						<a href="refundrequest?pageNum=${pageNumStart-pageBlock}">[◀]</a>
					</c:if>	
					
					<!-- 복사한거라..다시 공부 -->
					<c:forEach var="i" begin="${pageNumStart}" end="${pageNumEnd}">
			    		<c:if test="${i == currentPage}">
			    			<span><b>[${i}]</b></span>
			    		</c:if>
			    		<c:if test="${i != currentPage}">
			    			<a href="refundrequest?pageNum=${i}">[${i}]</a>
			    		</c:if>
		    		</c:forEach>
					
					
					<c:if test="${pageTotalCnt > pageNumEnd}">
						<a href="refundrequest?pageNum=${pageNumStart + pageBlock}">[▶]</a>
						<a href="refundrequest?pageNum=${pageTotalCnt}">[마지막으로]</a>
					</c:if>
				</th>
			</tr>
		</table>
	</c:if>
-----------------------------------
</body>
</html>