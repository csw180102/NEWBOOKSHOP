<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<script src="${comJs}managementScript.js"></script>
<link type="text/css" rel="stylesheet" href="${comCss}join.css">
<html>
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

<script type="text/javascript">
window.onload = function() {
	modifyFocus();
	initialSeq();
}
</script>
<body>
	<h2>회원 정보 수정 페이지</h2>
	<form action="modifyPro.member" method="post" name="modifyform" onsubmit="return modifyCheck();">
		<table>
			<tr>
				<th colspan="2">수정하려는 정보를 입력하세요</th>
			</tr>
			<tr>
				<th> * 아이디</th>
				<td>
					<input class="input" type="text" name="id" value="" maxlength="20" style="background-color: #e2e2e2;" readOnly>
				</td>																					
			</tr>
			<tr>
				<th> * 비밀번호</th>
				<td>
					<input class="input" type="password" name="pwd" maxlength="10">
				</td>
			</tr>
			<tr>
				<th> * 비밀번호 확인</th>
				<td>
					<input class="input" type="password" name="repwd" maxlength="10" >
				</td>
			</tr>
			<tr>
				<th> * 이름</th>
				<td>
					<input class="input" type="text" name="name" maxlength="10" style="background-color: #e2e2e2;" readOnly>
				</td>
			</tr>
			<tr>
				<th> * 주민번호</th>
				<td>
					<input class="input" type="text" name="jumin1" maxlength="6" style="width:50px; background-color: #e2e2e2;" readOnly onkeyup="nextJumin1();">
					-
					<input class="input" type="text" name="jumin2" maxlength="7" style="width:60px; background-color: #e2e2e2;" readOnly onkeyup="nextJumin2();">
				</td>
			</tr>
			<tr>
				<th> * 핸드폰번호</th>
				<td>
					<input class="input" type="text" name="hp1" maxlength="3" style="width:30px" onkeyup="nextHp1();">
					-
					<input class="input" type="text" name="hp2" maxlength="4" style="width:40px" onkeyup="nextHp2();">
					-
					<input class="input" type="text" name="hp3" maxlength="4" style="width:40px" onkeyup="nextHp3();">
				</td>
			</tr>
			<tr>
				<th> * 이메일</th>
				<td>
					<input class="input" type="text" name="email1" maxlength="10" style="width:65px">
					@
					<input class="input" type="text" name="email2" maxlength="10" style="width:65px">
					<select class="input" name="email3" onchange="selectEmail3_Modify(this.options[this.selectedIndex].value);">
						<option value="0">직접입력</option>
						<option value="gmail.com">구글</option>
						<option value="daum.net">다음</option>
						<option value="nate.com">네이트</option>
						<option value="naver.com">네이버</option>
					</select>
				</td>
			</tr>
			<tr>
				<th> * 생년월일</th>
				<td>
					<select id="label_birthYear" name="birthYear" onchange="selectMonth();">
					</select>
					<label for="label_birthMonth">년</label>
					<select id="label_birthMonth" name="birthMonth" onchange="selectDay();"> 
					</select>
					<label for="label_birthDay">월</label>
					<select id="label_birthDay" name="birthDay">
					</select>
					<label for="">일</label>
				</td>
			</tr>
			<tr>
				<th> * 주소</th>
				<td>
					<select id="label_city" name="city" onchange="selectCity();">
						<option value="">도시</option>
						<option value="서울시">서울시</option>
						<option value="경기도">경기도</option>
						<option value="대전광역시">대전광역시</option>
						<option value="대구광역시">대구광역시</option>
						<option value="부산광역시">부산광역시</option>
						<option value="울산광역시">울산광역시</option>
					</select>
					<select id="label_gu" name="gu">
						<option value="">군/구</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="inputButton" type="submit" value="수정완료">
					<input class="inputButton" type="reset" value="다시 작성">
					<input class="inputButton" type="button" value="수정취소" onclick="parent.document.location.reload();">
				</td>
			</tr>>
		</table>
	</form>
</body>
</html>