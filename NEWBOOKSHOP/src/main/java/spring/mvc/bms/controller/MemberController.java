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
		System.out.println("Mservice.loginPro() �۾� ����...");
		
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
		System.out.println("Mservice.logoutPro() �۾� ����...");
		Mservice.logoutPro(req, model);
		System.out.println("Mservice.logoutPro() �۾� �Ϸ�...");
		return "index"; //��� �������µ� index.jsp���� MAIN.jsp�� �����ϴµ� ��θ� ��..
	}
	
	@RequestMapping("join")
	public String join(HttpServletRequest req, Model model) {
		System.out.println("ȸ������ ������ ����... ");
		return "member/myJoin/join";
	}
	
	@RequestMapping("dupPro")
	public String dupPro(HttpServletRequest req, Model model) {
		System.out.println("ȸ�����Խ� ���̵� �ߺ����� Ȯ�� ����...");
		Mservice.dupPro(req, model);
		return "member/myJoin/dupCheckId";
	}
	
	@RequestMapping("joinPro")
	public String joinPro(HttpServletRequest req, Model model) {
		System.out.println("ȸ������ �� ä��� ����, DB insert �۾� ����...");
		Mservice.joinPro(req, model);
		return "member/myJoin/joinPro";
	}
	
	@RequestMapping("mypage")
	public String myPage(HttpServletRequest req, Model model) {
		System.out.println("myPage.jsp ����...");
		return "member/management/myPage";
	}
	
	@RequestMapping("deleteForm")
	public String deleteForm(HttpServletRequest req, Model model) {
		System.out.println("deleteForm.jsp ����...");
		return "member/management/myInfo/jspForm/leaveForm";
	}
	
	@RequestMapping("deletePro")
	public String deletePro(HttpServletRequest req, Model model) {
		System.out.println("ȸ��Ż�� DB delete �۾� ����...");
		Mservice.deletePro(req, model);
		return "member/management/myInfo/pro/deletePro";
	}
	
	@RequestMapping("modifyForm")
	public String modifyForm() {
		System.out.println("modifyForm.jsp ����...");
		return "member/management/myInfo/jspForm/modifyForm";
	}
	
	@RequestMapping("modifyPro")
	public String modifyPro(HttpServletRequest req, Model model) {
		System.out.println("ȸ������ ���� DB update �۾� ����...");
		Mservice.modifyPro(req, model);
		return "member/management/myInfo/pro/modifyPro";
	}
	
	
	@RequestMapping("top")
	public String top(HttpServletRequest req, Model model) {
		System.out.println("guestMain.jsp ����...");
		Bservice.showListPro(req, model);
		return "member/guestMain";
	}
	
	
}
