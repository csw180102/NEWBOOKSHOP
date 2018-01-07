/**
 * 
 */
var msg_pwd="비밀번호를 입력하세요";
var msg_pwdChk="비밀번호가 일치하지 않습니다";

var deleteError="회원탈퇴 실패. \n 확인 후 다시 시도하세요"; //int ctn로 판단
var updateError="회원정보 수정 실패. \n 확인 후 다시 시도하세요";
var passwdError="입력된 비밀번호가 일치하지 않습니다. \n 확인 후 다시 시도하세요";


function leaveFocus() {
	document.deleteform.pwd.focus();
}

function leaveCheck() {
	if(!document.deleteform.pwd.value) {
		alert(msg_pwd);
		document.eleteform.pwd.focus();
		return false;
	}
	
	if(!document.deleteform.anwser.value) {
		alert(msg_anwser);
		document.eleteform.anwser.focus();
		return false;
	}
}

function modifyFocus() {
	document.modifyform.pwd.focus();
}
function modifyCheck() {
	if(!document.modifyform.pwd.value) { //비번칸 비어있음
		alert(msg_pwd);
		document.modifyform.pwd.focus();
		return false;
	}
	
	if(!document.modifyform.repwd.value) { //비번확인칸 비어있음
		alert(msg_repwd);
		document.modifyform.repwd.focus();
		return false;
	}
	if(document.modifyform.repwd.value!=document.modifyform.pwd.value) { 
		alert(msg_pwdChk); //비번, 재확인칸 일치하지 않음
		document.modifyform.pwd.value="";
		document.modifyform.repwd.value="";
		document.modifyform.pwd.focus();
		return false;
	}
}

function selectEmail3_Modify(value) { //selectbox 인 email3의 옵션선택값을 email2의 input박스로 출력 
	document.modifyform.email2.value = value;	
}


function initialSeq() {
	
	var Years = new Array();
	for(var i=0; i<40; i++) {
		Years[i] = i + 1980; //30년 치 (1980~2009)
	}
	
	var month = document.getElementById("label_birthMonth");
	var year = document.getElementById("label_birthYear");
	var day = document.getElementById("label_birthDay");

	year.options[0] = new Option("YEAR", "");	
	month.options[0] = new Option("MONTH", "");
	day.options[0] = new Option("DAY", "");
	
	for(var i=1; i<=Years.length; i++) {
		year.options[i] = new Option(Years[i-1], Years[i-1]);
	}
}

function selectMonth() {  //------------onchange="selectMonth();"
	
	var Months = new Array();
	for(var i=0; i<12; i++) {
		Months[i] = i + 1; //12달 (1~12월)
	}
	
	var month = document.getElementById("label_birthMonth");
	var year = document.getElementById("label_birthYear");
	var day = document.getElementById("label_birthDay");
	
	//month.options는 초기화 안해도 됨
	
	if(year.options[year.selectedIndex].value) {
		for(var i=1; i<=Months.length; i++) {
			month.options[i] = new Option(Months[i-1], Months[i-1]);
		}
	}
}

function selectDay() {  //------------onchange="selectDay();"
	
	var Days = new Array();
	for(var i=0; i<31; i++) {
		Days[i] = i+1; //31일 치
	}
	
	var month = document.getElementById("label_birthMonth");
	var year = document.getElementById("label_birthYear");
	var day = document.getElementById("label_birthDay");
	
	//다른 달을 또다시 고를때는 day.options를 초기화 시켜야함
	//경우에 따라 29,30,31,30 선택되기 때문에 처음에 31일짜리 달 선택하면 배열길이가 다른 29,28,30일 짜리 다른달의 day.options이 다 씹힘
	for(var i=day.options.length; 0<i; i--) {
		day.options[i] = null;
	}
	//----------------------------------------------------------------------
	
	if(month.options[month.selectedIndex].value==1 ||
			month.options[month.selectedIndex].value==3 ||
			month.options[month.selectedIndex].value==5 || 
			month.options[month.selectedIndex].value==7 ||
			month.options[month.selectedIndex].value==8 ||
			month.options[month.selectedIndex].value==10 ||
			month.options[month.selectedIndex].value==12) {
		for(var i=1; i<=Days.length; i++) {
			day.options[i] = new Option(Days[i-1], Days[i-1]);
		}
	} else if(month.options[month.selectedIndex].value==4 ||
			month.options[month.selectedIndex].value==6 ||
			month.options[month.selectedIndex].value==9 ||
			month.options[month.selectedIndex].value==11) {
		for(var i=1; i<=Days.length-1; i++) {
			day.options[i] = new Option(Days[i-1], Days[i-1]);
		}
	} else if(month.options[month.selectedIndex].value==2) {
		if((year[year.selectedIndex].value%4)==0 && 
				(year[year.selectedIndex].value%100)!=0 || 
				(year[year.selectedIndex].value%400)==0) {
			alert("윤달 입니다");
			for(var i=1; i<=Days.length-2; i++) {
				day.options[i] = new Option(Days[i-1], Days[i-1]);
			}
		} else {
			for(var i=1; i<=Days.length-3; i++) {
				day.options[i] = new Option(Days[i-1], Days[i-1]);
			}
		}
	}
	
}

function selectCity() {
	var seoul = ["강남구","서초구","성북구","성동구","관악구","은평구","구로구"];
	var kyungi = ["시흥시", "안양시", "이천시","용인시","수원시","의정부시","여주시","양평"];
	var daejeon = ["대덕구","동구","서구","유성구","중구"];
	var daegu = ["북구","동구","서구","중구","수성구","달성군"];
	var busan = ["기장군", "금정구","해운대구"];
	var ulsan = ["남구","북구","중구","동구","울주군"];
	
	var abbCity = document.modifyform.city;
	var abbGu = document.modifyform.gu;

	
	for(var i=abbGu.options.length; 0<i; i--) {
		abbGu.options[i] = null;  
	}
	
	if(abbCity.value=="서울시") { //선택값이 서울 일때
		for(var i=1; i<=seoul.length; i++) { //서울 배열 길이만큼 돈다 그걸 새로운 객체Option에 옮김
			abbGu.options[i] = new Option(seoul[i-1], seoul[i-1]); //요건 외우고 그냥
		}
	} else if (abbCity.value=="경기도") {
		for(var i=1; i<=kyungi.length; i++) { //i=1부터 시작하는 이유 (구를 선택하시오 때문)
			abbGu.options[i] = new Option(kyungi[i-1], kyungi[i-1]); 
		}
	} else if (abbCity.value=="대전광역시") {
		for(var i=1; i<=daejeon.length; i++) {
			abbGu.options[i] = new Option(daejeon[i-1], daejeon[i-1]);
		}
	} else if (abbCity.value=="대구광역시") {
		for(var i=1; i<=daegu.length; i++) {
			abbGu.options[i] = new Option(daegu[i-1], daegu[i-1]); 
		}
	} else if (abbCity.value=="부산광역시") {
		for(var i=1; i<=busan.length; i++) { 
			abbGu.options[i] = new Option(busan[i-1], busan[i-1]); 
		}
	} else if (abbCity.value=="울산광역시") {
		for(var i=1; i<=ulsan.length; i++) { 
			abbGu.options[i] = new Option(ulsan[i-1], ulsan[i-1]); 
		}
	}

}

