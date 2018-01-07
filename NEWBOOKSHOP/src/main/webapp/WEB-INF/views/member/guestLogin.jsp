<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../PATH.jsp" %>
<script src="${comJs}commonScript.js"></script>
<script src="${comJs}memberScript.js"></script>
<link type="text/css" rel="stylesheet" href="${comCss}guestLogin.css">
<html>
<%
	String strId = (String)request.getSession().getAttribute("sessionId"); //session이라는 데이터저장소 접근
	int cnt = (Integer)request.getAttribute("cnt");
	System.out.println(strId);
	System.out.println(cnt);
	if(strId == null) { //세션연결이 없는 상태 (계속 그자리에 머무름)
		System.out.println("세션연결 null 상태");
		if(cnt == -1) {
			System.out.println("정보 불일치 들어옴");
			out.println("<script>alert('정보가 일치하지 않습니다!');</script>");
		} else if(cnt == 0) {
			out.println("<script>alert('아이디가 존재하지 않습니다!');</script>");
		} else if(cnt == 2) {
			out.println("<script>alert('처음오셨죠?! 반갑습니다 !');</script>");
		}
%>
세션 아이디 : <%= strId %>
		<body onload ="idFocus();">
	<form action="loginPro" method="post" name="loginForm" onsubmit="return blankCheck();">
	  <div id="wrap">
	   <h1 class="member">회원 로그인</h1>  <!-- 아맞다 중복확인해야지 ㅋㅋ -->
	   <div class="form">
	    <div class="form2">
	     <div class="form3">
	      <label for="label_id">아이디</label>
	      <input type="text" id="label_id" name="id"> <!-- 속성 "id"=값 --> 
	      <div class="clear"></div>
	      <label for="label_pwd">비밀번호</label>
	      <input type="password" id="label_pwd" name="pwd"> <!-- 속성"pwd"=값 -->
	     </div>
	     <input type="submit" value="로그인하기">
	     <div class="clear"></div>
	     <div class="form4">
	      <div class="clear"></div>									<!-- 새창을 열고 servlet 진입 -->
	      <label><input type="button" value="회원가입" onclick="openJoin();"></label>
	      <label><input type="button" value="아이디/비밀번호 찾기"></label>
	     </div>
	    </div>
	   </div>
	  </div>
	 </form>
</body>
		
<%
	} else { //세션연결이 있는 상태(guestMain.jsp로 이동)
%>
		<script type="text/javascript">
			alert(<%= strId %> + "님 반갑습니다!");
		</script>
<%
	}
%>
</html>