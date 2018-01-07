/**
 * 
 */
function idFocus() { //guestLogin.jsp화면 진입시 게스트용로그인 화면 ID칸에 커서 바로위치 
	document.loginForm.id.focus();
}						
//아이디칸 채우지 않고 submit 했을때 발생(id,pwd가 blank인지 체크)
function blankCheck() {
	/*System.out.println("blankCheck() 실행"); 여기는 자바스크립트임.. 옆에는 자바코드 쓰면 그냥 500에러남*/
	if(!document.loginForm.id.value) {
		alert("아이디를 입력해주세요!");
		document.loginForm.id.focus();
		return false;
	}
	if(!document.loginForm.pwd.value) {
		alert("비밀번호를 입력해주세요!");
		document.loginForm.pwd.focus();
		return false;
	}
}

