package spring.mvc.bms.board.persistance;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.bms.board.vo.AccountListVO;
import spring.mvc.bms.board.vo.BookBoardVO;
import spring.mvc.bms.board.vo.OrderListVO;
import spring.mvc.bms.board.vo.RefundListVO;
import spring.mvc.bms.board.vo.TotalPriceVO;
import spring.mvc.bms.board.vo.WishListVO;

@Repository
public class BookBoardDAOImpl implements BookBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public int selectAllStockCnt() {
			
		int totalCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		totalCnt = dao.selectAllStockCnt();
			
		return totalCnt;
	}
	

	
	@Override
	public ArrayList<BookBoardVO> selectAllStock(Map<String, Integer> map) {
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		ArrayList<BookBoardVO> bigVo = dao.selectAllStock(map);
		
		return bigVo;
	}

	@Override
	public BookBoardVO getStockDetail(int book_seq) {
		//book테이블에서 책 1개 레코드를 vo에 담는 작업을 해야함
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		BookBoardVO vo = dao.getStockDetail(book_seq);
		
		return vo;
	}
 
	@Override
	public int deleteStock(int book_seq) {
		
		int deleteCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		deleteCnt = dao.deleteStock(book_seq);
		
		return deleteCnt;
	}

	
	@Override
	public int insertStock(BookBoardVO vo) {
		
		int insertCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		insertCnt = dao.insertStock(vo);
		
		return insertCnt;
	}

	@Override
	public int updateStock(BookBoardVO vo) {
		
		int updateCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		updateCnt = dao.updateStock(vo);
		return updateCnt;
	}


	@Override
	public int insertWishlist(WishListVO vo) {
		
		int insertCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		insertCnt = dao.insertWishlist(vo);
		
		return insertCnt;
		
	}
	

	@Override
	public int selectAllWishListCnt(String sessionId) {
		
		int totalCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		totalCnt = dao.selectAllWishListCnt(sessionId);
			
		return totalCnt;
	}
	
	
	@Override
	public ArrayList<WishListVO> selectAllWishList(Map<String, Object> map) {
		
		int seenNumEnd = (Integer)map.get("seenNumEnd");
		int seenNumStart = (Integer)map.get("seenNumStart");
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		ArrayList<WishListVO> bigVo = new ArrayList<WishListVO>(seenNumEnd-seenNumStart+1);
		
		bigVo = dao.selectAllWishList(map); //이거 반환이 bigVo가 되야함
		
		return bigVo;

	}
	
	@Override
	public TotalPriceVO selectWishAmount(String sessionId) {
		
		TotalPriceVO vo1 = new TotalPriceVO();

		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		System.out.println("selectWishAmount()실행준비.."); 
		vo1 = dao.selectWishAmount(sessionId);
		
		return vo1;
	}


	@Override
	public int deleteWishList(int wishlist_seq) {
		
		int deleteCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		deleteCnt = dao.deleteWishList(wishlist_seq);
		
		return deleteCnt;
	}
	
	
	@Override
	public int selectAllOrderListCnt(Map<String, String> map1) {

		int totalCnt = 0;
		System.out.println("selectAllOrderListCnt() 진입...");
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		totalCnt = dao.selectAllOrderListCnt(map1);	
		System.out.println("dfdfdfdf");
		return totalCnt;
	}

	

	@Override
	public ArrayList<OrderListVO> selectAllOrderList(Map<String, Object> map) {
		
		System.out.println("selectAllOrderList() 진입.."); 
		int seenNumStart = (Integer)map.get("seenNumStart");
		int seenNumEnd = (Integer)map.get("seenNumEnd");
		ArrayList<OrderListVO> bigVo = new ArrayList<OrderListVO>(seenNumEnd - seenNumStart + 1);
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		bigVo = dao.selectAllOrderList(map);
		
		return bigVo;
	}
	
	@Override
	public TotalPriceVO selectOrderAmount(Map<String, String> map) {
		
		TotalPriceVO vo1 = new TotalPriceVO();
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		vo1 = dao.selectOrderAmount(map);
		
		return vo1;
	}
	

	@Override
	public int insertOrderList(Map<String, Object> map) {
		
		//새로운 buylist에 넣는 작업 하자
		int insertCnt = 0;

		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		insertCnt = dao.insertOrderList(map);

		return insertCnt;
	}


	

	@Override
	public int deleteOrderList(int buy_seq) {
		
		int deleteCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		deleteCnt = dao.deleteOrderList(buy_seq);
		System.out.println("deleteCnt 주문목록삭제여부 : " + deleteCnt);
		return deleteCnt;
	}
	
	@Override
	public int selectAllRefundListCnt(Map<String, Object> map) {
		
		int totalCnt = 0;

		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		totalCnt = dao.selectAllRefundListCnt(map);
			
		return totalCnt;
	}
	
	@Override
	public ArrayList<RefundListVO> selectAllRefundList(Map<String, Object > map) {
		
		int seenNumEnd = (Integer)map.get("seenNumEnd");
		int seenNumStart = (Integer)map.get("seenNumStart");
		
		ArrayList<RefundListVO> bigVo = new ArrayList<RefundListVO>(seenNumEnd-seenNumStart+1);
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		bigVo = dao.selectAllRefundList(map);
		
		return bigVo;
	}
	
	@Override
	public OrderListVO selectFinalOrderList(int buy_seq) {
		
		OrderListVO vo = new OrderListVO();

		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		vo = dao.selectFinalOrderList(buy_seq);
			
		return vo;
	}
	
	
	@Override
	public int insertRefundList(OrderListVO vo) {
		
		//일단 refund 테이블을 만든다
		int insertCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		insertCnt = dao.insertRefundList(vo);

		return insertCnt;
		
	}
	
	@Override
	public int deleteFinalOrderList(int buy_seq) {
		int deleteCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		deleteCnt = dao.deleteFinalOrderList(buy_seq);
		
		return deleteCnt;
		
	}
	
	@Override
	public int orderStock(int buy_seq) {
		int totalCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		totalCnt = dao.orderStock(buy_seq);
		return totalCnt;
	}
	
	@Override
	public int updateStockColumn(Map<String, Integer> map) {
		
		int updateCnt = 0;

		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		updateCnt = dao.updateStockColumn(map);
		
		return updateCnt;
	}
	
	@Override
	public OrderListVO selectOrderList(int buy_seq) {
		System.out.println("selectOrderList() 진입...");
		OrderListVO vo = new OrderListVO();

		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		vo = dao.selectOrderList(buy_seq);
		
		return vo;
	}
	

	@Override
	public int insertFinalAdminOrderList(OrderListVO vo) {
		System.out.println("insertFinalAdminOrderList() 진입...");
		int insertCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		insertCnt = dao.insertFinalAdminOrderList(vo);
		System.out.println("insertCnt : " + insertCnt);
		return insertCnt;
	}

	@Override
	public AccountListVO selectOneFinalOrderList(int buy_seq) {
		System.out.println("selectOneFinalOrderList() 진입...");
		AccountListVO vo = new AccountListVO();

		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		vo = dao.selectOneFinalOrderList(buy_seq);
			
		return vo;
		
	}
	
	@Override
	public int insertClosingAccountApprovalList(AccountListVO vo) {
		System.out.println("insertClosingAccountApprovalList() 진입...");
		int insertAccountCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		insertAccountCnt = dao.insertClosingAccountApprovalList(vo);
		
		return insertAccountCnt;
	}


	@Override
	public int selectAllFinalOrderListCnt(Map<String, Object> map) {
		
		int totalCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		totalCnt = dao.selectAllFinalOrderListCnt(map);
		return totalCnt;

	}

	@Override
	public ArrayList<OrderListVO> selectAllFinalOrderList(Map<String, Object> map) {
		
		
		int seenNumStart = (Integer)map.get("seenNumStart");
		int seenNumEnd = (Integer)map.get("seenNumEnd");
		ArrayList<OrderListVO> bigVo = new ArrayList<OrderListVO>(seenNumEnd - seenNumStart + 1);
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		bigVo = dao.selectAllFinalOrderList(map);
		
		return bigVo;

	}

	@Override
	public TotalPriceVO selectFinalAdminOrderAmount(Map<String, Object> map) {
		
		TotalPriceVO vo1 = new TotalPriceVO();
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		vo1 = dao.selectFinalAdminOrderAmount(map);
		
		return vo1;
	}

	
	@Override
	public int selectAllFinalRefundListCnt(Map<String, Object> map) {
		
		int totalCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		totalCnt = dao.selectAllFinalRefundListCnt(map);
		return totalCnt;
	}
	
	
	@Override
	public ArrayList<RefundListVO> selectAllFinalRefundList(Map<String, Object> map) {
		System.out.println("selectAllFinalRefundList() 진입...");
		int seenNumStart = (Integer)map.get("seenNumStart");
		int seenNumEnd = (Integer)map.get("seenNumEnd");
		ArrayList<RefundListVO> bigVo = new ArrayList<RefundListVO>(seenNumEnd - seenNumStart + 1);
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		bigVo = dao.selectAllFinalRefundList(map);
	
		return bigVo;
	}
	
	@Override
	public int refundStock(int refund_seq) {
		
		int totalCnt = 0;
		System.out.println("refundStock() 진입..");
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		totalCnt = dao.refundStock(refund_seq);
		return totalCnt;
	}
	
	@Override
	public int updateStockColumnPlus(Map<String, Integer> map) {
		System.out.println("환불할때 재고 더하기 진입");
		int updateCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		updateCnt = dao.updateStockColumnPlus(map);
		return updateCnt;
	}
	
	@Override
	public RefundListVO selectOneRefundList(int refund_seq) {
		
		RefundListVO vo = new RefundListVO();
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		vo = dao.selectOneRefundList(refund_seq);
		return vo;
		
	}
	
	@Override
	public int insertFinalRefundList(RefundListVO vo) {

		int insertCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		insertCnt = dao.insertFinalRefundList(vo);
		return insertCnt;
	}
	
	
	@Override
	public AccountListVO selectOneFinalRefundList(int refund_seq) {
		
		AccountListVO vo = new AccountListVO();
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		vo = dao.selectOneFinalRefundList(refund_seq);
		return vo;
	}

	
	@Override
	public int insertClosingAccountRefundList(AccountListVO vo) {
		
		int insertAccountCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		insertAccountCnt = dao.insertClosingAccountRefundList(vo);
		return insertAccountCnt;
	}
	
	@Override
	public int deleteRefundList(int refund_seq) {

		int deleteCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		deleteCnt = dao.deleteRefundList(refund_seq);
		return deleteCnt;
	}

	

	@Override
	public int selectAllClosingAccountListCnt() {
		
		int totalCnt = 0;
		
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		totalCnt = dao.selectAllClosingAccountListCnt();
		return totalCnt;
	}

	
	@Override
	public ArrayList<AccountListVO> selectAllClosingAccountList(Map<String, Object> map) {
		
		int seenNumStart = (Integer)map.get("seenNumStart");
		int seenNumEnd = (Integer)map.get("seenNumEnd");
		ArrayList<AccountListVO> bigVo = new ArrayList<AccountListVO>(seenNumEnd - seenNumStart +1);
		BookBoardDAO dao = sqlSession.getMapper(BookBoardDAO.class);
		bigVo = dao.selectAllClosingAccountList(map);
		
		return bigVo;
	}



/*

	@Override
	public ArrayList<AccountListVO> getMonthlySales() {
		
		
		ArrayList<AccountListVO> bigVo = new ArrayList<AccountListVO>(12);
		
		try {
			
			System.out.println("getMonthlySales() try문 진입...");
			conn = datasource.getConnection();

			for(int i=1; i<13; i++) {

				sql = "SELECT DISTINCT NVL((SELECT SUM(quantity*price) " + 
						"FROM ( " + 
						"SELECT * " + 
						"FROM closingaccount c, book b " + 
						"WHERE c.book_seq = b.book_seq " + 
						") " + 
						"WHERE TO_CHAR(ar_date, 'MM') = ? " + 
						"AND ar_check = 'inflow'),0) as inflow, " + 
						"NVL((SELECT SUM(quantity*price) " + 
						"FROM ( " + 
						"SELECT * " + 
						"FROM closingaccount c, book b " + 
						"WHERE c.book_seq = b.book_seq " + 
						") " + 
						"WHERE TO_CHAR(ar_date, 'MM') = ? " + 
						"AND ar_check = 'outflow'),0) as outflow " + 
						"FROM closingaccount c, book b " + 
						"WHERE c.book_seq = b.book_seq";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				pstmt.setInt(2, i);
				rs = pstmt.executeQuery();
				
					if(rs.next()) {
						
						
							do {
								
							AccountListVO vo = new AccountListVO();
							
							vo.setMonth_sales_inflow(rs.getInt(1));
							vo.setMonth_sales_outflow(rs.getInt(2));
							System.out.println(i+"월 inflow-outflow = " + (rs.getInt(1)-rs.getInt(2)));
							bigVo.add(vo);
							System.out.println("제일 큰 바구니 bigVo에 담긴 vo주소들 : " + bigVo);
							System.out.println(i+"번째 생성된 작은 바구니 vo 주소(12개/"+i+"번) : " + vo);
							}while(rs.next());
					}
				}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return bigVo;
	}

*/	
}
	
	

	
