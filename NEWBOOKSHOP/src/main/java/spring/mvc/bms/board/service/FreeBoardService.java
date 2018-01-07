package spring.mvc.bms.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface FreeBoardService {
	//�����Խ��� ��� �Ѹ���
	public void showFreeboardListPro(HttpServletRequest req, Model model);
	//�����Խ��� �� ����
	public void showPostDetailPro(HttpServletRequest req, Model model);
	//Ư�� ȸ���� �� �Խñ� �Ѹ���
	public void showMyPostPro(HttpServletRequest req, Model model);
	//�Խñ� �����ϴ� ����1(�������� writePostForm.jsp�� �ҷ����� �� �̵�)
	public void updateMyPostPro(HttpServletRequest req, Model model);
	//�Խñ� �����ϴ� ����2(�������� freeboard���̺� update)
	public void updateMyPostProTwo(HttpServletRequest req, Model model);
	//�Խñ� ���� ����
	public void deleteMyPostPro(HttpServletRequest req, Model model);
	//�۾��� ����
	public void writeNewPostPro(HttpServletRequest req, Model model);

}
