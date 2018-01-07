package spring.mvc.bms.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MemberService { //회원 잡다 처리 서비스
	//로그인 처리
	public void loginPro(HttpServletRequest req, Model model);
	//로그아웃 처리
	public void logoutPro(HttpServletRequest req, Model model);
	//회원 id 중복 여부 처리
	public void dupPro(HttpServletRequest req, Model model);
	//새로운 회원 가입정보 처리
	public void joinPro(HttpServletRequest req, Model model);
	//탈퇴 처리
	public void deletePro(HttpServletRequest req, Model model);	
	//회원정보 수정 처리
	public void modifyPro(HttpServletRequest req, Model model);

}

