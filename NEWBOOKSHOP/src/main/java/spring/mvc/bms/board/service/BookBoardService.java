package spring.mvc.bms.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BookBoardService {
	//�մԿ��� å ���, �����ڿ��� å ��� ��� �����ִ� (����������/���������������� �ѷ��ִ� ����)
	public void showListPro(HttpServletRequest req, Model model);
	//�մԿ��� å ���, �����ڿ��� å ��� ��� �����ִ� (������������ �ѷ��ִ� ����)
	public void showListProTwo(HttpServletRequest req, Model model);
	//����ȭ�� å�� ������ �߰��ϱ�
	public void showListMainDetailPro(HttpServletRequest req, Model model);
	//å���� ������ �������� �߰��ϱ�
	public void showListDetailPro(HttpServletRequest req, Model model);
	//üũ�ڽ� ���õǸ� delete �ϴ� �۾�
	public void listDeletePro(HttpServletRequest req, Model model);
	//��� �߰� �۾�
	public void listInsertPro(MultipartHttpServletRequest req, Model model);
	//��� ���� �۾�
	public void listUpdatePro(MultipartHttpServletRequest req, Model model);
	//�մ� ���ø���Ʈ�� ������ �߰� �۾�
	public void wishListPro(HttpServletRequest req, Model model);
	//�մ� ���ø���Ʈ�� ������ �߰� �۾�2
	public void wishListProTwo(HttpServletRequest req, Model model);
	//�մ� ���ø���Ʈ �����ֱ� �۾�
	public void showWishListPro(HttpServletRequest req, Model model);
	//���ø���Ʈ �����ϱ�
	public void deleteWishListPro(HttpServletRequest req, Model model);
	//���Ÿ�� �����ֱ� �۾�
	public void showOrderListPro(HttpServletRequest req, Model model);
	//���Ÿ�Ͽ� ������ �߰� �۾�
	public void orderListPro(HttpServletRequest req, Model model);
	//���Ÿ�� ���� �۾�
	public void deleteOrderListPro(HttpServletRequest req, Model model);
	//ȯ�ҽ�û��� �����ֱ� �۾�
	public void showRefundListPro(HttpServletRequest req, Model model);
	//ȯ�Ҹ�Ͽ� ������ �߰� �۾�
	public void refundPro(HttpServletRequest req, Model model);
	//�� �ֹ������ �������� �����ֹ����θ������ �ű�� �۾�
	public void approveOrderPro(HttpServletRequest req, Model model);
	
	//�մ��� �ڱ��ֹ� ���� ������ �����ֹ����θ�� �Ѹ���
	public void showFinalAdminOrderList(HttpServletRequest req, Model model);
	

	//������ ����ȯ�ҽ��θ�� ����
	public void showFinalRefundListPro(HttpServletRequest req, Model model);
	//�մ� ȯ�ҿ�û ���� �۾�
	public void approveRefundPro(HttpServletRequest req, Model model);
	
	//�Ѱ�� ���� �� �ѷ��ֱ� �۾�
	public void showClosingAccountListPro(HttpServletRequest req, Model model);
	//���� inflow �����ֱ�
//	public void showMonthlySales(HttpServletRequest req, Model model);
	
	
}
