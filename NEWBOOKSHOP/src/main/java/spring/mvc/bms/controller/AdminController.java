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
		System.out.println("admin / adminLogin.jsp �̵�...");
		model.addAttribute("cnt", 2);
		return "admin/adminLogin";
	}
	
	@RequestMapping("adminloginPro")
	public String adminloginPro(HttpServletRequest req, Model model) {
		System.out.println("adminloginPro / loginPro() �۾� ����...");
		
		Mservice.loginPro(req, model); //�� ���� �ȳѰ��ڤ� ���ڱ� 
		if(req.getSession().getAttribute("sessionId").equals("host")) {
			Bservice.showListPro(req, model);
			return "admin/adminMain"; //���ǿ���Ǹ� 
		}

		return "admin/adminLogin"; //���ǿ���ȵǸ�
	}
	
	@RequestMapping("adminPage")
	public String adminPage(HttpServletRequest req, Model model) {
		System.out.println("adminPage / adminPage.jsp ����...");
		
		return "admin/management/adminPage";
	}
	
	@RequestMapping("admintop")
	public String admintop(HttpServletRequest req, Model model) {
		System.out.println("admintop / showListPro() �۾� ����..."); 
		
		Bservice.showListPro(req, model);
		return "admin/adminMain";
	}
	
	@RequestMapping("adminlogout")
	public String adminlogout(HttpServletRequest req, Model model) {
		System.out.println("adminlogout / logoutPro() �۾� ����..."); 
		
		Mservice.logoutPro(req, model);
		return "index";
	}
	
	//////////////////////////////// ������ ������///////////////////////////////////
	@RequestMapping("stocklist") // ������ booklist(�߰�,����,������ư ����)
	public String adminbooklist(HttpServletRequest req, Model model) {
		System.out.println("������ showListProTwo() �۾� ȣ��...");

		Bservice.showListProTwo(req, model);
		return "admin/management/stock/jspForm/stocklist";
	}

	@RequestMapping("stockdetail")
	public String stockdetail(HttpServletRequest req, Model model) {
		System.out.println("showListDetailPro() �۾� ȣ��...");

		Bservice.showListDetailPro(req, model);
		return "admin/management/stock/jspForm/stockdetail";
	}

	@RequestMapping("deletestock")
	public String deletestock(HttpServletRequest req, Model model) {
		System.out.println("showListDetailPro() �۾� ȣ��...");

		Bservice.listDeletePro(req, model);
		return "admin/management/stock/pro/deletestockPro";
	}

	@RequestMapping("addstock")
	public String addstock(HttpServletRequest req, Model model) {
		System.out.println("insertstockform.jsp ����߰� �� �̵� ...");

		return "admin/management/stock/jspForm/insertstockform";
	}

	 @RequestMapping("insertstockPro") 
	 public String insertstockPro(MultipartHttpServletRequest req, Model model) {
		System.out.println("insertstockform.jsp ����߰� �� �̵� ...");
	 
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
	
	///////////////////////////////�ֹ�����//////////////////////////////
	
	 
	
}
