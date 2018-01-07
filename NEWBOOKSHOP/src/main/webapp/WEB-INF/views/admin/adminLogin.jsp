<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../PATH.jsp" %>
<script src="${comJs}commonScript.js"></script>
<link type="text/css" rel="stylesheet" href="${comCss}adminLogin.css">
<html>
<% //그냥 url로 따로 관리자 로그인 페이지로 시작하게 만들어보자 cnt가 필요 
	String strId = (String)request.getSession().getAttribute("sessionId"); //session이라는 데이터저장소 접근
	int cnt = (Integer)request.getAttribute("cnt");
	System.out.println("세션ID : " + strId + "/" + cnt);
	
	if(strId == null) { //세션연결이 없는 상태 (계속 그자리에 머무름)
		if(cnt==-1) {
			out.println("<script>alert('정보가 일치하지 않습니다!');</script>");
		} else if(cnt==0) {
			out.println("<script>alert('관리자 아이디가 다릅니다!');</script>");
		} else if(cnt==2) {
			out.println("<script>alert('관리자 로그인 화면 입니다!');</script>");
		}
%>
<body onload="idFocus();">
	<form action="adminloginPro" method="post" name="loginForm" onsubmit="return blankCheck();">
	  <div id="wrap">
	   <h1 class="member"> 관리자 로그인 </h1>
	   <div class="form">
	    <div class="form2">
	     <div class="form3">
	      <label for="label_id">아이디</label>
	      <input type="text" id="label_id" name="id">
	      <div class="clear"></div>
	      <label for="label_pwd">비밀번호</label>
	      <input type="password" id="label_pwd" name="pwd">
	     </div>
	     <input type="submit" value="로그인하기">
	    </div>
	   </div>
	  </div>
	 </form>
</body>

<%
	} else { //세션연결이 있는 상태(adminMain.jsp로 이동)
%>
		<script type="text/javascript">
			alert("관리자님 반갑습니다!");
		</script>
<%
	}
%>

</html>