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
	<h3><a>---------closingAccountList.jsp-----------</a></h3>
<h2><center>총결산(최종승인, 최종환불 같이 있음 ar_check컬럼에서 비교됨)</center></h2>
		<table style="width:1000px" align="center">
			<tr>
				<th style="width:4%">결산번호</th>
				<th style="width:7%">구매번호</th>
				<th style="width:7%">환불번호</th>
				<th style="width:7%">회원ID</th>
				<th style="width:5%">책번호</th>
				<th style="width:5%">구매수량</th>
				<th style="width:4%">결산여부</th>
				<th style="width:7%">승인/환불일자</th>
			</tr>
	<c:if test="${totalCnt > 0}"> <!-- 글이 존재할때 totalCnt > 0  -->
		<c:forEach var="vo" items="${bigVo}">
			<tr>
				<td align="center"> <!-- 결산번호 -->
						${vo.close_seq}
				</td>
				<td align="center"> <!-- 구매번호 -->
						${vo.buy_seq}
				</td>
				<td align="center"> <!-- 회원ID -->
						${vo.refund_seq}
				</td>
				<td align="center"> <!-- 회원ID -->
						${vo.id}
				</td>
				<td align="center"> <!-- 책번호 -->
						${vo.book_seq}
				</td>
				<td align="center"> <!-- 주문수량 -->
						${vo.quantity}
				</td>
				<td align="center"> <!-- 결산여부 -->
						${vo.ar_check}
				</td>
				<td align="center"> <!-- 승인/환불일자 -->
						${vo.ar_date}
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
						<a href="closingaccount">[처음으로]</a>
						<a href="closingaccount?pageNum=${pageNumStart-pageBlock}">[◀]</a>
					</c:if>	
					
					<!-- 복사한거라..다시 공부 -->
					<c:forEach var="i" begin="${pageNumStart}" end="${pageNumEnd}">
			    		<c:if test="${i == currentPage}">
			    			<span><b>[${i}]</b></span>
			    		</c:if>
			    		<c:if test="${i != currentPage}">
			    			<a href="closingaccount?pageNum=${i}">[${i}]</a>
			    		</c:if>
		    		</c:forEach>
					
					
					<c:if test="${pageTotalCnt > pageNumEnd}">
						<a href="closingaccount?pageNum=${pageNumStart + pageBlock}">[▶]</a>
						<a href="closingaccount?pageNum=${pageTotalCnt}">[마지막으로]</a>
					</c:if>
				</th>
			</tr>
		</table>
	</c:if>
-----------------------------------
</body>
</html>