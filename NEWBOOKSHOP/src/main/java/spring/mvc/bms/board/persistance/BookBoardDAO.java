package spring.mvc.bms.board.persistance;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.bms.board.vo.AccountListVO;
import spring.mvc.bms.board.vo.BookBoardVO;
import spring.mvc.bms.board.vo.OrderListVO;
import spring.mvc.bms.board.vo.RefundListVO;
import spring.mvc.bms.board.vo.TotalPriceVO;
import spring.mvc.bms.board.vo.WishListVO;

public interface BookBoardDAO {
	//메인메뉴에서 뿌려줄 책정보를 ArrayList에 바구니안담고 그냥 담기
	//public ArrayList<BookBoardVO> selectAllStockInArrayList(int start, int end);
	//책 재고, 목록을 select해서 작은바구니, 큰바구니로 담아넘기는 작업
	public ArrayList<BookBoardVO> selectAllStock(Map<String, Integer> map);
	//총 책의 갯수 select해서 구하기
	public int selectAllStockCnt();
	//책의 상세페이지 select 그리고 vo로 받아서 뷰페이지로 넘기는 작업
	public BookBoardVO getStockDetail(int book_seq);
	//체크된 책 delete 하는 작업
	public int deleteStock(int book_seq);
	//재고 insert 작업
	public int insertStock(BookBoardVO vo);
	//재고 update 작업
	public int updateStock(BookBoardVO vo);
	//새로운 위시리스트 테이블에 담는 작업
	public int insertWishlist(WishListVO vo);
	//위시리스트 내용 모든 것을 select해서 작은바구니, 큰바구니로 담는 작업
	public ArrayList<WishListVO> selectAllWishList(Map<String, Object> map);
	//위시리스트 총 갯수 select해서 구하기
	public int selectAllWishListCnt(String sessionId);
	//위시리스트 총합계 구하기 select
	public TotalPriceVO selectWishAmount(String id);
	//위시리스트 삭제하기 delete
	public int deleteWishList(int wishlist_seq);
	//구매목록 리스트에 insert하기
	public int insertOrderList(Map<String, Object> map);
	//구매목록 select 뿌리기 작업
	public ArrayList<OrderListVO> selectAllOrderList(Map<String, Object > map);
	//구매목록 총 갯수 구하기
	public int selectAllOrderListCnt(Map<String, String> map1);
	//구매목록 총합계 구하기 select
	public TotalPriceVO selectOrderAmount(Map<String, String> map);
	//구매목록 삭제하기
	public int deleteOrderList(int buy_seq);
	//환불리스트 총 갯수 구하기
	public int selectAllRefundListCnt(Map<String, Object > map);
	//환불리스트 select 뿌리기 작업
	public ArrayList<RefundListVO> selectAllRefundList(Map<String, Object > map);
	//최종구매목록 구하는 작업
	public OrderListVO selectFinalOrderList(int buy_seq);
	//환불리스트에 구매목록의 레코드 insert 작업
	public int insertRefundList(OrderListVO vo);
	//최종구매목록 삭제하는 작업
	public int deleteFinalOrderList(int buy_seq);
	
	//주문수량 갯수 구하기
	public int orderStock(int buy_seq);
	//최종구매승인시 재고떨기
	public int updateStockColumn(Map<String, Integer > map);
	//최종승인목록에 들어갈 buylist 1개 레코드 select
	public OrderListVO selectOrderList(int buy_seq);
	//최종관리자구매승인 목록 값 insert
	public int insertFinalAdminOrderList(OrderListVO vo);
	//finalbuylist에서 레코드1개 가져오기
	public AccountListVO selectOneFinalOrderList(int buy_seq);
	//위의 레코드를 closingaccount 테이블에 넣는데 ar_check컬럼에 '결산'도 넣기
	public int insertClosingAccountApprovalList(AccountListVO vo);
	//관리자 최종구매승인목록 총갯수
	public int selectAllFinalOrderListCnt(Map<String, Object> map);
	//관리자 최종구매승인목록 전부 뿌리기
	public ArrayList<OrderListVO> selectAllFinalOrderList(Map<String, Object> map);
	//관리자 최종구매승인목록 총액
	public TotalPriceVO selectFinalAdminOrderAmount(Map<String, Object> map);	
	
	
	//최종구매승인목록 
	public int selectAllFinalRefundListCnt(Map<String, Object> map);
	//
	public ArrayList<RefundListVO> selectAllFinalRefundList(Map<String, Object> map);

	

	
	//환불재고 구하기
	public int refundStock(int refund_seq);
	//환불시 재고 더하기
	public int updateStockColumnPlus(Map<String, Integer> map);
	//환불테이블에서 1개 레코드 가져오기
	public RefundListVO selectOneRefundList(int refund_seq);
	//최종환불승인에 insert
	public int insertFinalRefundList(RefundListVO vo);	
	//최종환불리스트에서 1개 레코드 가져오기
	public AccountListVO selectOneFinalRefundList(int refund_seq);
	//위의 레코드를 최종결산테이블에 넣기
	public int selectAllClosingAccountListCnt();
	//위의 레코드를 closingaccount 테이블에 넣는데 ar_check컬럼에 '환불'도 넣기
	public int insertClosingAccountRefundList(AccountListVO vo);
	//손님 환불목록 삭제
	public int deleteRefundList(int refund_seq);

	
	//총결산 모든 레코드를 BigVo에 담는다
	public ArrayList<AccountListVO> selectAllClosingAccountList(Map<String, Object> map);

	//select where 월별 inflow
//	public ArrayList<AccountListVO> getMonthlySales();

}
