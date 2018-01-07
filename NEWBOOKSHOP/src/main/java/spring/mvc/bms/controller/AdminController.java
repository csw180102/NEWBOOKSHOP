package spring.mvc.bms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.bms.board.service.BookBoardService;
import spring.mvc.bms.member.service.MemberService;

@Controller
public class AdminController {
	
	@Autowired
	private MemberService Mservice;
	@Autowired
	private BookBoardService Bservice;
	
	@RequestMapping("admin")
	public String admin(HttpServletRequest req, Model model) {
		System.out.println("admin / adminLogin.jsp 이동...");
		model.addAttribute("cnt", 2);
		return "admin/adminLogin";
	}
	
	@RequestMapping("adminloginPro")
	public String adminloginPro(HttpServletRequest req, Model model) {
		System.out.println("adminloginPro / loginPro() 작업 진입...");
		
		Mservice.loginPro(req, model); //왜 값이 안넘거자ㅣ 갑자기 
		if(req.getSession().getAttribute("sessionId").equals("host")) {
			Bservice.showListPro(req, model);
			return "admin/adminMain"; //세션연결되면 
		}

		return "admin/adminLogin"; //세션연결안되면
	}
	
	@RequestMapping("adminPage")
	public String adminPage(HttpServletRequest req, Model model) {
		System.out.println("adminPage / adminPage.jsp 진입...");
		
		return "admin/management/adminPage";
	}
	
	@RequestMapping("admintop")
	public String admintop(HttpServletRequest req, Model model) {
		System.out.println("admintop / showListPro() 작업 진입..."); 
		
		Bservice.showListPro(req, model);
		return "admin/adminMain";
	}
	
	@RequestMapping("adminlogout")
	public String adminlogout(HttpServletRequest req, Model model) {
		System.out.println("adminlogout / logoutPro() 작업 진입..."); 
		
		Mservice.logoutPro(req, model);
		return "index";
	}
	
	//////////////////////////////// 관리자 재고관리///////////////////////////////////
	@RequestMapping("stocklist") // 관리자 booklist(추가,수정,삭제버튼 존재)
	public String adminbooklist(HttpServletRequest req, Model model) {
		System.out.println("관리자 showListProTwo() 작업 호출...");

		Bservice.showListProTwo(req, model);
		return "admin/management/stock/jspForm/stocklist";
	}

	@RequestMapping("stockdetail")
	public String stockdetail(HttpServletRequest req, Model model) {
		System.out.println("showListDetailPro() 작업 호출...");

		Bservice.showListDetailPro(req, model);
		return "admin/management/stock/jspForm/stockdetail";
	}

	@RequestMapping("deletestock")
	public String deletestock(HttpServletRequest req, Model model) {
		System.out.println("showListDetailPro() 작업 호출...");

		Bservice.listDeletePro(req, model);
		return "admin/management/stock/pro/deletestockPro";
	}

	@RequestMapping("addstock")
	public String addstock(HttpServletRequest req, Model model) {
		System.out.println("insertstockform.jsp 재고추가 폼 이동 ...");

		return "admin/management/stock/jspForm/insertstockform";
	}

	 @RequestMapping("insertstockPro") 
	 public String insertstockPro(MultipartHttpServletRequest req, Model model) {
		System.out.println("insertstockform.jsp 재고추가 폼 이동 ...");
	 
	 	Bservice.listInsertPro(req, model); 
	 	return "admin/management/stock/pro/insertstockPro"; 
	 }
	 
	 @RequestMapping("updatestock")
	 public String updatestock(HttpServletRequest req, Model model) {
		 
		 Bservice.showListDetailPro(req, model);
		 return "admin/management/stock/jspForm/updatestockform";
	 }
	 
	 @RequestMapping("updatestockPro")
	 public String updatestockPro(MultipartHttpServletRequest req, Model model) {
		 
		 Bservice.listUpdatePro(req, model);
		 return "admin/management/stock/pro/updatestockPro";
	 }
	
	///////////////////////////////주문관리//////////////////////////////
	
	 
	
}
