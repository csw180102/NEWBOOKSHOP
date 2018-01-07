<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}adminPage.css">
<html>
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
    <div id="jb-container">
    
    <!-- 머리통 제목 부분 -->
      <div id="jb-header">
        <h4>▶관리자 <%= request.getSession().getAttribute("sessionId") %>님 환영합니다</h4>
        	<input type="button" value="홈으로" onclick="window.location='admintop'">
        	<input type="button" value="로그아웃" onclick="window.location='adminlogout'">
        	<input type="button" value="재고리스트" onclick="document.getElementById('contentiframe').src='stocklist'">
        	<input type="button" value="회원게시판보러가기" onclick="document.getElementById('contentiframe').src='freeboard'">
        <table border="1">
        	<tr>
        		<th>재고관련</th>
		        <td>
		        	<input type="button" value="추가" onclick="document.getElementById('contentiframe').src='addstock'">
		        </td>
		        <th>주문관련</th>
		        <td>
		        	<input type="button" value="현재주문목록현황" onclick="document.getElementById('contentiframe').src='approveorder'">
		        </td>
		        <td>
		        	<input type="button" value="최종구매승인목록" onclick="document.getElementById('contentiframe').src='finaladminorder'">
		        </td>
		        <td>
		        	<input type="button" value="현재환불요청목록현황" onclick="document.getElementById('contentiframe').src='refundrequest'">
		        </td>
		        <td>
		        	<input type="button" value="최종환불승인목록" onclick="document.getElementById('contentiframe').src='finalrefundrequest'">
		        </td>
		     </tr>
		     <tr>
		        <th>결산관련</th>
		        <td>
		        	<input type="button" value="총결산목록보기" onclick="document.getElementById('contentiframe').src='closingaccount'">
		        </td>
		        <td>
		        	<input type="button" value="월별매출" onclick="document.getElementById('contentiframe').src='monthlysales'">
		        </td>
        	</tr>
        </table>
      </div>
      
    <!-- 컨텐츠 보이는 부분 --> 
      <div id="jb-content">
      	<iframe name="iframe" id="contentiframe" src="/bms/admin/management/iframeAdmin.jsp" width="1080" height="1500" frameborder="1" scrolling="no"></iframe>
      </div>
      
    <!-- 
      <div id="jb-sidebar">
        <h2>관리자 BMS 메뉴</h2>
        <ul>
          <li>재고관리
          	<ul>
          		<li><a href="stocklist.book" target="iframe">목록</a></li>
          		<li>추가</li>
          		<li>수정</li>
          		<li>삭제</li>
          	</ul>
          </li>
          <li>주문관리
          	<ul>
          		<li>주문목록</li>
          		<li>주문승인(주문목록에서 승인한것)</li>
          		<li>환불요청(주문승인목록에서 삭제한것)</li>
          	</ul>
          </li>
          <li>회원관리
          	<ul>
          		<li>회원목록</li>
          		<li>공사중..</li>
          		<li>공사중..</li>
          	</ul>
          </li>
        </ul>
      </div>
      -->
    <!-- 푸터 부분 -->  
      <div id="jb-footer">
        <p>Copyright</p>
      </div>
    </div>
  </body>
</html>