package spring.mvc.bms.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MemberService { //ȸ�� ��� ó�� ����
	//�α��� ó��
	public void loginPro(HttpServletRequest req, Model model);
	//�α׾ƿ� ó��
	public void logoutPro(HttpServletRequest req, Model model);
	//ȸ�� id �ߺ� ���� ó��
	public void dupPro(HttpServletRequest req, Model model);
	//���ο� ȸ�� �������� ó��
	public void joinPro(HttpServletRequest req, Model model);
	//Ż�� ó��
	public void deletePro(HttpServletRequest req, Model model);	
	//ȸ������ ���� ó��
	public void modifyPro(HttpServletRequest req, Model model);

}

