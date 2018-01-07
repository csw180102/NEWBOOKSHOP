package spring.mvc.bms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.mvc.bms.board.service.BookBoardService;

@Controller
public class BookBoardController {
	
	@Autowired
	BookBoardService Bservice;
	
	@RequestMapping("initmain")
	public String main(HttpServletRequest req, Model model) {
		System.out.println("showListPro() �۾� ȣ��...");
		
		Bservice.showListPro(req, model);
		return "MAIN";
	}
	
	
	@RequestMapping("booklist") //�մԿ� booklist(���ø���Ʈ ��ư�� ����)
	public String booklist(HttpServletRequest req, Model model) {
		System.out.println("�մԹ湮�� ù������ showListProTwo() �۾� ȣ��...");
		
		Bservice.showListProTwo(req, model);
		return "board/management/bookboard/jspForm/booklist";
	}	
	
	@RequestMapping("mainbookdetail")
	public String mainbookdetail(HttpServletRequest req, Model model) {
		System.out.println("showListMainDetailPro() �۾� ȣ��...");
		
		Bservice.showListMainDetailPro(req, model);
		return "board/management/bookboard/jspForm/mainbookdetail";
	}
	
	@RequestMapping("bookdetail")
	public String bookdetail(HttpServletRequest req, Model model) {
		System.out.println("showListMainDetailPro() �۾� ȣ��...");
		
		Bservice.showListDetailPro(req, model);
		return "board/management/bookboard/jspForm/bookdetail";
	}

///////////////////////////////���ø���Ʈ/////////////////////////////////	
	@RequestMapping("mywishlist")
	public String mywishlist(HttpServletRequest req, Model model) {
		System.out.println("mywishlist / showWishListPro() �۾� ����...");
		
		Bservice.showWishListPro(req, model);
		return "member/management/myCart/jspForm/myWishList";
	}
	
	@RequestMapping("wishlist")
	public String wishlist(HttpServletRequest req, Model model) {
		System.out.println("wishlist / wishListPro() �۾� ����...");
		
		Bservice.wishListPro(req, model);
		return "member/management/myCart/jspForm/myWishListForm";
	}
	
	@RequestMapping("wishlistPro")
	public String wishlistPro(HttpServletRequest req, Model model) {
		System.out.println("wishlistPro / wishListProTwo() �۾� ����...");
		
		Bservice.wishListProTwo(req, model);
		return "member/management/myCart/pro/insertwishlistPro";
	}
	
	@RequestMapping("deletewishlistPro")
	public String deletewishlistPro(HttpServletRequest req, Model model) {
		System.out.println("deletewishlistPro / wishListProTwo() �۾� ����...");
		
		Bservice.deleteWishListPro(req, model);
		return "member/management/myCart/pro/deletewishlistPro";
	}
	
/////////////////////////////////���Ÿ���Ʈ///////////////////////////////
	@RequestMapping("myorderlist")
	public String myorderlist(HttpServletRequest req, Model model) {
		
		Bservice.showOrderListPro(req, model);
		return "member/management/myCart/jspForm/myOrderList";
	}
	
	@RequestMapping("buybookPro")
	public String buybookPro(HttpServletRequest req, Model model) {
		
		Bservice.orderListPro(req, model);
		return "member/management/myCart/pro/insertorderlistPro";
	}
	
	@RequestMapping("deleteorderlistPro")
	public String deleteorderlistPro(HttpServletRequest req, Model model) {
		
		Bservice.deleteOrderListPro(req, model);
		return "member/management/myCart/pro/deleteorderlistPro";
	}
/////////////////////////////////�������������Ž���////////////////////////////////
	
	@RequestMapping("approveorder")
	public String approveorder(HttpServletRequest req, Model model) {
		
		Bservice.showOrderListPro(req, model);
		return "admin/management/order/jspForm/adminOrderListForm";
	} //�����ֹ������Ȳ ���� ��ư
	
	@RequestMapping("insertadminorderPro")
	public String insertadminorderPro(HttpServletRequest req, Model model) {
		
		Bservice.approveOrderPro(req, model);
		return "admin/management/order/pro/insertfinaladminorderPro";
	} //�����ֹ������Ȳ ���� ��ư (�������Ž��θ�� �̵�)
	
	@RequestMapping("finaladminorder")
	public String finaladminorder(HttpServletRequest req, Model model) {
		
		Bservice.showFinalAdminOrderList(req, model);
		return "admin/management/order/jspForm/finalAdminOrderList";
	} //�����ڰ� �մԱ���(buylist)������  �������Ž��θ��(finalbuylist) ���� ��ư
	
	@RequestMapping("rejectadminorderPro")
	public String rejectadminorderPro(HttpServletRequest req, Model model) {
		
		Bservice.deleteOrderListPro(req, model);
		return "admin/management/order/pro/deletefinaladminorderPro";
	} //�����ڰ� �մԱ��� �����ϴ� ��� �۾�
	
	
/////////////////////////////////ȯ�Ҹ���Ʈ///////////////////////////////
	@RequestMapping("myrefundlist")
	public String myrefundlist(HttpServletRequest req, Model model) {
		
		Bservice.showRefundListPro(req, model);
		return "member/management/myCart/jspForm/myRefundList";
	}
	
	@RequestMapping("refundbookPro")
	public String refundbookPro(HttpServletRequest req, Model model) {
		
		Bservice.refundPro(req, model);
		return "member/management/myCart/pro/insertrefundlistPro";
	}
	
	@RequestMapping("myfinalrefundlist")
	public String myfinalrefundlist(HttpServletRequest req, Model model) {
		
		Bservice.showFinalRefundListPro(req, model);
		return "admin/management/order/jspForm/finalAdminRefundList";
	} //�����ڰ� ������ ȯ�Ҹ���� �մ��� ���Բ�
	
	@RequestMapping("refundrequest")
	public String refundrequest(HttpServletRequest req, Model model) {
		
		Bservice.showRefundListPro(req, model);
		return "admin/management/order/jspForm/adminRefundListForm";
	}
	
	@RequestMapping("refundrequestPro")
	public String refundrequestPro(HttpServletRequest req, Model model) {
		
		Bservice.approveRefundPro(req, model);  //���� �̰� �ȵ�
		return "admin/management/order/pro/insertfinaladminrefundPro";
	}
	
	@RequestMapping("finalrefundrequest")
	public String finalrefundrequest(HttpServletRequest req, Model model) {
		
		Bservice.showFinalRefundListPro(req, model);
		return "admin/management/order/jspForm/finalAdminRefundList";
	}
	
	@RequestMapping("closingaccount")
	public String closingaccount(HttpServletRequest req, Model model) {
		
		Bservice.showClosingAccountListPro(req, model);
		return "admin/management/accounting/jspForm/closingAccountList";
	}
	
	@RequestMapping("monthlysales")  //�̰� ����sql�� forEach�ʿ�
	public String monthlysales(HttpServletRequest req, Model model) {
		
		//Bservice.showMonthlySales(req, model);
		return "admin/management/accounting/jspForm/closingAccount";
	}
}
