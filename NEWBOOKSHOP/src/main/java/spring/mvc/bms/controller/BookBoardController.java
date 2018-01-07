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
		System.out.println("showListPro() 작업 호출...");
		
		Bservice.showListPro(req, model);
		return "MAIN";
	}
	
	
	@RequestMapping("booklist") //손님용 booklist(위시리스트 버튼만 존재)
	public String booklist(HttpServletRequest req, Model model) {
		System.out.println("손님방문시 첫페이지 showListProTwo() 작업 호출...");
		
		Bservice.showListProTwo(req, model);
		return "board/management/bookboard/jspForm/booklist";
	}	
	
	@RequestMapping("mainbookdetail")
	public String mainbookdetail(HttpServletRequest req, Model model) {
		System.out.println("showListMainDetailPro() 작업 호출...");
		
		Bservice.showListMainDetailPro(req, model);
		return "board/management/bookboard/jspForm/mainbookdetail";
	}
	
	@RequestMapping("bookdetail")
	public String bookdetail(HttpServletRequest req, Model model) {
		System.out.println("showListMainDetailPro() 작업 호출...");
		
		Bservice.showListDetailPro(req, model);
		return "board/management/bookboard/jspForm/bookdetail";
	}

///////////////////////////////위시리스트/////////////////////////////////	
	@RequestMapping("mywishlist")
	public String mywishlist(HttpServletRequest req, Model model) {
		System.out.println("mywishlist / showWishListPro() 작업 진입...");
		
		Bservice.showWishListPro(req, model);
		return "member/management/myCart/jspForm/myWishList";
	}
	
	@RequestMapping("wishlist")
	public String wishlist(HttpServletRequest req, Model model) {
		System.out.println("wishlist / wishListPro() 작업 진입...");
		
		Bservice.wishListPro(req, model);
		return "member/management/myCart/jspForm/myWishListForm";
	}
	
	@RequestMapping("wishlistPro")
	public String wishlistPro(HttpServletRequest req, Model model) {
		System.out.println("wishlistPro / wishListProTwo() 작업 진입...");
		
		Bservice.wishListProTwo(req, model);
		return "member/management/myCart/pro/insertwishlistPro";
	}
	
	@RequestMapping("deletewishlistPro")
	public String deletewishlistPro(HttpServletRequest req, Model model) {
		System.out.println("deletewishlistPro / wishListProTwo() 작업 진입...");
		
		Bservice.deleteWishListPro(req, model);
		return "member/management/myCart/pro/deletewishlistPro";
	}
	
/////////////////////////////////구매리스트///////////////////////////////
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
/////////////////////////////////관리자최종구매승인////////////////////////////////
	
	@RequestMapping("approveorder")
	public String approveorder(HttpServletRequest req, Model model) {
		
		Bservice.showOrderListPro(req, model);
		return "admin/management/order/jspForm/adminOrderListForm";
	} //현재주문목록현황 보기 버튼
	
	@RequestMapping("insertadminorderPro")
	public String insertadminorderPro(HttpServletRequest req, Model model) {
		
		Bservice.approveOrderPro(req, model);
		return "admin/management/order/pro/insertfinaladminorderPro";
	} //현재주문목록현황 승인 버튼 (최종구매승인목록 이동)
	
	@RequestMapping("finaladminorder")
	public String finaladminorder(HttpServletRequest req, Model model) {
		
		Bservice.showFinalAdminOrderList(req, model);
		return "admin/management/order/jspForm/finalAdminOrderList";
	} //관리자가 손님구매(buylist)승인후  최종구매승인목록(finalbuylist) 보기 버튼
	
	@RequestMapping("rejectadminorderPro")
	public String rejectadminorderPro(HttpServletRequest req, Model model) {
		
		Bservice.deleteOrderListPro(req, model);
		return "admin/management/order/pro/deletefinaladminorderPro";
	} //관리자가 손님구매 거절하는 경우 작업
	
	
/////////////////////////////////환불리스트///////////////////////////////
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
	} //관리자가 승인한 환불목록을 손님이 보게끔
	
	@RequestMapping("refundrequest")
	public String refundrequest(HttpServletRequest req, Model model) {
		
		Bservice.showRefundListPro(req, model);
		return "admin/management/order/jspForm/adminRefundListForm";
	}
	
	@RequestMapping("refundrequestPro")
	public String refundrequestPro(HttpServletRequest req, Model model) {
		
		Bservice.approveRefundPro(req, model);  //지금 이게 안됨
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
	
	@RequestMapping("monthlysales")  //이건 동적sql이 forEach필요
	public String monthlysales(HttpServletRequest req, Model model) {
		
		//Bservice.showMonthlySales(req, model);
		return "admin/management/accounting/jspForm/closingAccount";
	}
}
