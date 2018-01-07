package spring.mvc.bms.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BookBoardService {
	//손님에겐 책 목록, 관리자에겐 책 재고 목록 보여주는 (마이페이지/관리자페이지에서 뿌려주는 서비스)
	public void showListPro(HttpServletRequest req, Model model);
	//손님에겐 책 목록, 관리자에겐 책 재고 목록 보여주는 (메인페이지에 뿌려주는 서비스)
	public void showListProTwo(HttpServletRequest req, Model model);
	//메인화면 책들 상세정보 뜨게하기
	public void showListMainDetailPro(HttpServletRequest req, Model model);
	//책제목 누르면 상세페이지 뜨게하기
	public void showListDetailPro(HttpServletRequest req, Model model);
	//체크박스 선택되면 delete 하는 작업
	public void listDeletePro(HttpServletRequest req, Model model);
	//재고 추가 작업
	public void listInsertPro(MultipartHttpServletRequest req, Model model);
	//재고 수정 작업
	public void listUpdatePro(MultipartHttpServletRequest req, Model model);
	//손님 위시리스트에 데이터 추가 작업
	public void wishListPro(HttpServletRequest req, Model model);
	//손님 위시리스트에 데이터 추가 작업2
	public void wishListProTwo(HttpServletRequest req, Model model);
	//손님 위시리스트 보여주기 작업
	public void showWishListPro(HttpServletRequest req, Model model);
	//위시리스트 삭제하기
	public void deleteWishListPro(HttpServletRequest req, Model model);
	//구매목록 보여주기 작업
	public void showOrderListPro(HttpServletRequest req, Model model);
	//구매목록에 데이터 추가 작업
	public void orderListPro(HttpServletRequest req, Model model);
	//구매목록 삭제 작업
	public void deleteOrderListPro(HttpServletRequest req, Model model);
	//환불신청목록 보여주기 작업
	public void showRefundListPro(HttpServletRequest req, Model model);
	//환불목록에 데이터 추가 작업
	public void refundPro(HttpServletRequest req, Model model);
	//고객 주문목록을 관리자의 최종주문승인목록으로 옮기는 작업
	public void approveOrderPro(HttpServletRequest req, Model model);
	
	//손님이 자기주문 볼때 관리자 최종주문승인목록 뿌리기
	public void showFinalAdminOrderList(HttpServletRequest req, Model model);
	

	//관리자 최종환불승인목록 보기
	public void showFinalRefundListPro(HttpServletRequest req, Model model);
	//손님 환불요청 승인 작업
	public void approveRefundPro(HttpServletRequest req, Model model);
	
	//총결산 내용 쫙 뿌려주기 작업
	public void showClosingAccountListPro(HttpServletRequest req, Model model);
	//월별 inflow 보여주기
//	public void showMonthlySales(HttpServletRequest req, Model model);
	
	
}
