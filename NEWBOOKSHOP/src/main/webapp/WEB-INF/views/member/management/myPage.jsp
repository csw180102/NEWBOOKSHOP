<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}myPage.css">
<script src="${comJs}managementScript.js"></script>
<html>
<%
	
	if(request.getSession().getAttribute("sessionId")!=null) {
		out.println("세션 연결 상태...YES : " + request.getSession().getAttribute("sessionId"));
	} else {
%>		
		<script type="text/javascript">
			alert("로그인 하세요!");
			location.replace("/bms/main"); //바로 서블릿진입점으로 이동
		</script>
<%
	}
%>
  <body>
    <div id="jb-container">
    
    <!-- 머리통 제목 부분 -->
      <div id="jb-header">
        <h4>▶<%= request.getSession().getAttribute("sessionId") %>님 환영합니다</h4>
        	<input type="button" value="홈으로" onclick="window.location='top'">
        	<input type="button" value="로그아웃" onclick="window.location='logoutPro'">
        	<input type="button" value="책보러가기" onclick="document.getElementById('contentiframe').src='booklist'">
        	<input type="button" value="회원게시판보러가기" onclick="document.getElementById('contentiframe').src='freeboard'">
        <table border="1">
        	<tr>
        		<th>회원관련</th>
		        <td>
		        	<input type="button" value="정보수정" onclick="document.getElementById('contentiframe').src='modifyForm'">
		        </td>
		        <td>
		        	<input type="button" value="회원탈퇴" onclick="document.getElementById('contentiframe').src='deleteForm'">
		        </td>
		        <th>주문관련</th>
		        <td>
		        	<input type="button" value="위시리스트" onclick="document.getElementById('contentiframe').src='mywishlist'">
		        </td>
		        <td>
		        	<input type="button" value="구매목록" onclick="document.getElementById('contentiframe').src='myorderlist'">
		        </td>
		        <td>
		        	<input type="button" value="구매승인목록" onclick="document.getElementById('contentiframe').src='finaladminorder'">
		        </td>
		        <td>
		        	<input type="button" value="환불신청목록" onclick="document.getElementById('contentiframe').src='myrefundlist'">
		        </td>
		        <td>
		        	<input type="button" value="환불승인목록" onclick="document.getElementById('contentiframe').src='myfinalrefundlist'">
		        </td>
        	</tr>
        </table>
      </div>
      
    <!-- 컨텐츠 보이는 부분 -->
      <div id="jb-content">
      	<iframe name="iframe" id="contentiframe" src="${path_3}management/iframeGuest.jsp" width="1080" height="1500" frameborder="1" scrolling="no"></iframe>
      </div>
      
<!--     왼쪽 메뉴 부분
      <div id="jb-sidebar">
        <h2> 메뉴</h2>
        <ul>
          <li>개인정보관리
          	<ul>
          		<li><a href="modifyForm.member" target="iframe">정보 수정</a></li>
          		<li><a href="deleteForm.member" target="iframe">회원 탈퇴</a></li>
          	</ul>
          </li>
          <li>나의 주문정보
          	<ul>
          		<li><a href="mywishlist.book" target="iframe">위시리스트</a></li>
          		<li>구매목록</li>
          		<li>환불요청</li>
          	</ul>
          </li>
          <li>나의 게시물
          	<ul>
          		<li>자유게시판</li>
          		<li>질문게시판</li>
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