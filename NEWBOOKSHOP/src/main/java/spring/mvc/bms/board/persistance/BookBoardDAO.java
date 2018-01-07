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
	//���θ޴����� �ѷ��� å������ ArrayList�� �ٱ��Ͼȴ�� �׳� ���
	//public ArrayList<BookBoardVO> selectAllStockInArrayList(int start, int end);
	//å ���, ����� select�ؼ� �����ٱ���, ū�ٱ��Ϸ� ��Ƴѱ�� �۾�
	public ArrayList<BookBoardVO> selectAllStock(Map<String, Integer> map);
	//�� å�� ���� select�ؼ� ���ϱ�
	public int selectAllStockCnt();
	//å�� �������� select �׸��� vo�� �޾Ƽ� ���������� �ѱ�� �۾�
	public BookBoardVO getStockDetail(int book_seq);
	//üũ�� å delete �ϴ� �۾�
	public int deleteStock(int book_seq);
	//��� insert �۾�
	public int insertStock(BookBoardVO vo);
	//��� update �۾�
	public int updateStock(BookBoardVO vo);
	//���ο� ���ø���Ʈ ���̺� ��� �۾�
	public int insertWishlist(WishListVO vo);
	//���ø���Ʈ ���� ��� ���� select�ؼ� �����ٱ���, ū�ٱ��Ϸ� ��� �۾�
	public ArrayList<WishListVO> selectAllWishList(Map<String, Object> map);
	//���ø���Ʈ �� ���� select�ؼ� ���ϱ�
	public int selectAllWishListCnt(String sessionId);
	//���ø���Ʈ ���հ� ���ϱ� select
	public TotalPriceVO selectWishAmount(String id);
	//���ø���Ʈ �����ϱ� delete
	public int deleteWishList(int wishlist_seq);
	//���Ÿ�� ����Ʈ�� insert�ϱ�
	public int insertOrderList(Map<String, Object> map);
	//���Ÿ�� select �Ѹ��� �۾�
	public ArrayList<OrderListVO> selectAllOrderList(Map<String, Object > map);
	//���Ÿ�� �� ���� ���ϱ�
	public int selectAllOrderListCnt(Map<String, String> map1);
	//���Ÿ�� ���հ� ���ϱ� select
	public TotalPriceVO selectOrderAmount(Map<String, String> map);
	//���Ÿ�� �����ϱ�
	public int deleteOrderList(int buy_seq);
	//ȯ�Ҹ���Ʈ �� ���� ���ϱ�
	public int selectAllRefundListCnt(Map<String, Object > map);
	//ȯ�Ҹ���Ʈ select �Ѹ��� �۾�
	public ArrayList<RefundListVO> selectAllRefundList(Map<String, Object > map);
	//�������Ÿ�� ���ϴ� �۾�
	public OrderListVO selectFinalOrderList(int buy_seq);
	//ȯ�Ҹ���Ʈ�� ���Ÿ���� ���ڵ� insert �۾�
	public int insertRefundList(OrderListVO vo);
	//�������Ÿ�� �����ϴ� �۾�
	public int deleteFinalOrderList(int buy_seq);
	
	//�ֹ����� ���� ���ϱ�
	public int orderStock(int buy_seq);
	//�������Ž��ν� �����
	public int updateStockColumn(Map<String, Integer > map);
	//�������θ�Ͽ� �� buylist 1�� ���ڵ� select
	public OrderListVO selectOrderList(int buy_seq);
	//���������ڱ��Ž��� ��� �� insert
	public int insertFinalAdminOrderList(OrderListVO vo);
	//finalbuylist���� ���ڵ�1�� ��������
	public AccountListVO selectOneFinalOrderList(int buy_seq);
	//���� ���ڵ带 closingaccount ���̺� �ִµ� ar_check�÷��� '���'�� �ֱ�
	public int insertClosingAccountApprovalList(AccountListVO vo);
	//������ �������Ž��θ�� �Ѱ���
	public int selectAllFinalOrderListCnt(Map<String, Object> map);
	//������ �������Ž��θ�� ���� �Ѹ���
	public ArrayList<OrderListVO> selectAllFinalOrderList(Map<String, Object> map);
	//������ �������Ž��θ�� �Ѿ�
	public TotalPriceVO selectFinalAdminOrderAmount(Map<String, Object> map);	
	
	
	//�������Ž��θ�� 
	public int selectAllFinalRefundListCnt(Map<String, Object> map);
	//
	public ArrayList<RefundListVO> selectAllFinalRefundList(Map<String, Object> map);

	

	
	//ȯ����� ���ϱ�
	public int refundStock(int refund_seq);
	//ȯ�ҽ� ��� ���ϱ�
	public int updateStockColumnPlus(Map<String, Integer> map);
	//ȯ�����̺��� 1�� ���ڵ� ��������
	public RefundListVO selectOneRefundList(int refund_seq);
	//����ȯ�ҽ��ο� insert
	public int insertFinalRefundList(RefundListVO vo);	
	//����ȯ�Ҹ���Ʈ���� 1�� ���ڵ� ��������
	public AccountListVO selectOneFinalRefundList(int refund_seq);
	//���� ���ڵ带 ����������̺� �ֱ�
	public int selectAllClosingAccountListCnt();
	//���� ���ڵ带 closingaccount ���̺� �ִµ� ar_check�÷��� 'ȯ��'�� �ֱ�
	public int insertClosingAccountRefundList(AccountListVO vo);
	//�մ� ȯ�Ҹ�� ����
	public int deleteRefundList(int refund_seq);

	
	//�Ѱ�� ��� ���ڵ带 BigVo�� ��´�
	public ArrayList<AccountListVO> selectAllClosingAccountList(Map<String, Object> map);

	//select where ���� inflow
//	public ArrayList<AccountListVO> getMonthlySales();

}
