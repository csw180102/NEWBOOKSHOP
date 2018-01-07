package spring.mvc.bms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import spring.mvc.bms.board.service.BookBoardService;
import spring.mvc.bms.member.service.MemberService;



@Controller
public class MemberController {
	
	@Autowired
	private BookBoardService Bservice;
	@Autowired
	private MemberService Mservice;
	
	
	@RequestMapping("main")
	public String main(HttpServletRequest req, Model model) {
		
		model.addAttribute("cnt", 2);
		return "member/guestLogin";
	}
	
	@RequestMapping("loginPro")
	public String loginPro(HttpServletRequest req, Model model) {
		System.out.println("Mservice.loginPro() 작업 시작...");
		
		Mservice.loginPro(req, model);
		if(req.getSession().getAttribute("sessionId")!=null) {

			Bservice.showListPro(req, model);
			return "member/guestMain";
		} else {
			return "member/guestLogin";
		}
	}
	
	@RequestMapping("logoutPro")
	public String logoutPro(HttpServletRequest req, Model model) {
		System.out.println("Mservice.logoutPro() 작업 시작...");
		Mservice.logoutPro(req, model);
		System.out.println("Mservice.logoutPro() 작업 완료...");
		return "index"; //요거 오류나는데 index.jsp에서 MAIN.jsp로 가야하는데 경로를 모름..
	}
	
	@RequestMapping("join")
	public String join(HttpServletRequest req, Model model) {
		System.out.println("회원가입 페이지 진입... ");
		return "member/myJoin/join";
	}
	
	@RequestMapping("dupPro")
	public String dupPro(HttpServletRequest req, Model model) {
		System.out.println("회원가입시 아이디 중복여부 확인 진입...");
		Mservice.dupPro(req, model);
		return "member/myJoin/dupCheckId";
	}
	
	@RequestMapping("joinPro")
	public String joinPro(HttpServletRequest req, Model model) {
		System.out.println("회원가입 폼 채우고 전송, DB insert 작업 진입...");
		Mservice.joinPro(req, model);
		return "member/myJoin/joinPro";
	}
	
	@RequestMapping("mypage")
	public String myPage(HttpServletRequest req, Model model) {
		System.out.println("myPage.jsp 진입...");
		return "member/management/myPage";
	}
	
	@RequestMapping("deleteForm")
	public String deleteForm(HttpServletRequest req, Model model) {
		System.out.println("deleteForm.jsp 진입...");
		return "member/management/myInfo/jspForm/leaveForm";
	}
	
	@RequestMapping("deletePro")
	public String deletePro(HttpServletRequest req, Model model) {
		System.out.println("회원탈퇴 DB delete 작업 진입...");
		Mservice.deletePro(req, model);
		return "member/management/myInfo/pro/deletePro";
	}
	
	@RequestMapping("modifyForm")
	public String modifyForm() {
		System.out.println("modifyForm.jsp 진입...");
		return "member/management/myInfo/jspForm/modifyForm";
	}
	
	@RequestMapping("modifyPro")
	public String modifyPro(HttpServletRequest req, Model model) {
		System.out.println("회원정보 수정 DB update 작업 진입...");
		Mservice.modifyPro(req, model);
		return "member/management/myInfo/pro/modifyPro";
	}
	
	
	@RequestMapping("top")
	public String top(HttpServletRequest req, Model model) {
		System.out.println("guestMain.jsp 진입...");
		Bservice.showListPro(req, model);
		return "member/guestMain";
	}
	
	
}
