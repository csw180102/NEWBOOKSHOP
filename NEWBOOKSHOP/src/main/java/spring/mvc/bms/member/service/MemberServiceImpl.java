package spring.mvc.bms.member.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.bms.member.persistance.MemberDAO;
import spring.mvc.bms.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService { //������ ����
	
	@Autowired
	private MemberDAO dao;
	
	@Override
	public void loginPro(HttpServletRequest req, Model model) { 
		
		int cnt = 0;
		String strId = req.getParameter("id");
		String strPwd = req.getParameter("pwd");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		
		cnt = dao.idPwdCheck(map);
		
		if(cnt==1) {
			req.getSession().setAttribute("sessionId", strId);
		} 
		System.out.println("id/pwd ���� cnt : " + cnt);
		model.addAttribute("cnt", cnt);
	}


	@Override
	public void logoutPro(HttpServletRequest req, Model model) {
		req.getSession().setAttribute("sessionId", null);
		//cnt��?
	}

	@Override
	public void dupPro(HttpServletRequest req, Model model) {
		
		int cnt = 0;
		String strId = req.getParameter("id"); //join.jsp���� ���� "id"
		cnt = dao.dupCheck(strId);
		
		model.addAttribute("strId", strId);
		model.addAttribute("cnt", cnt);
		
	}
	
	@Override
	public void joinPro(HttpServletRequest req, Model model) {
		
		//join���� �޾ƿ� ������ vo�� �ִ´�
		MemberVO vo = new MemberVO();
		
		vo.setId(req.getParameter("id"));
		vo.setPwd(req.getParameter("pwd"));
		vo.setName(req.getParameter("name"));
		
		if(req.getParameter("jumin1")!=null && req.getParameter("jumin2")!=null) {
			String jumin = req.getParameter("jumin1")+"-"+req.getParameter("jumin2");
			vo.setJumin(jumin);
		}
		
		
		if(req.getParameter("hp1")!=null && 
				req.getParameter("hp2")!=null &&
				req.getParameter("hp3")!=null) {
			String hp = req.getParameter("hp1")+"-"+req.getParameter("hp2")+"-"+req.getParameter("hp3");
			vo.setHp(hp);
		}
		if(req.getParameter("email1")!=null && req.getParameter("email2")!=null) {
			String email = req.getParameter("email1")+"@"+req.getParameter("email2");
			vo.setEmail(email);
		}
		
		if(req.getParameter("birthYear")!=null &&
				req.getParameter("birthMonth")!=null &&
				req.getParameter("birthDay")!=null) {
			
			String birth = req.getParameter("birthYear")+"-"+req.getParameter("birthMonth")+"-"+
			req.getParameter("birthDay");
			
			vo.setBirth(birth);
		}
		
		if(req.getParameter("city")!=null && req.getParameter("gu")!=null) {
			
			String address = req.getParameter("city")+" "+req.getParameter("gu");
			vo.setAddress(address);
		}
		
		////�ϴ� ���� vo �ٱ��Ͽ� ����... �̰�  MemberDAOImpl.getInstance().insert... ����
		//����������.
		int insertCnt = dao.insertMember(vo);
		
		model.addAttribute("insertCnt", insertCnt);
		
	}
	

	@Override
	public void deletePro(HttpServletRequest req, Model model) {
		//deleteForm.jsp���� submit���� ���, ���ǰ� �Ѱ� ����(���̵�� ���ǿ��� ���������)
		int deleteCnt = 0;
		String strId = (String)req.getSession().getAttribute("sessionId");
		String strPwd = req.getParameter("pwd");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		//���̵� ��� ��ġ ���߳�...
		int checkCnt = dao.idPwdCheck(map);
		
		//�� ������ MemberDAOImpl�� ������ delete������ ������ ���� �����
		if(checkCnt==1) {
			deleteCnt = dao.deleteMember(strId);
		} else {
			deleteCnt = 0;
		}
		req.getSession().setAttribute("sessionId", null);
		model.addAttribute("deleteCnt", deleteCnt);
	}



	@Override
	public void modifyPro(HttpServletRequest req, Model model) {
		//modifyForm.jsp�κ��� ������������ �����;��Ѵ�. ���ʿ��� submit���� ������ getPa... 
		//���� ������ vo�� �����Ѵ�
		MemberVO vo = new MemberVO();
		vo.setPwd(req.getParameter("pwd"));
		
		if(req.getParameter("hp1")!=null && 
				req.getParameter("hp2")!=null &&
				req.getParameter("hp3")!=null) {
			String hp = req.getParameter("hp1")+"-"+req.getParameter("hp2")+"-"+req.getParameter("hp3");
			vo.setHp(hp);
		}
		if(req.getParameter("email1")!=null && req.getParameter("email2")!=null) {
			String email = req.getParameter("email1")+"@"+req.getParameter("email2");
			vo.setEmail(email); //������ �����ҋ� ��ĭ�̸� �Է��϶�� �ߴϱ�,, not null �ɼ� ���� �ʳ�?
		}
		
		if(req.getParameter("birthYear")!=null &&
				req.getParameter("birthMonth")!=null &&
				req.getParameter("birthDay")!=null) {
			
			String birth = req.getParameter("birthYear")+"-"+req.getParameter("birthMonth")+"-"+
			req.getParameter("birthDay");
			
			vo.setBirth(birth);
		}
		
		if(req.getParameter("city")!=null && req.getParameter("gu")!=null) {
			
			String address = req.getParameter("city")+" "+req.getParameter("gu");
			vo.setAddress(address);
		}
		
		int updateCnt = dao.updateMember(vo);
		
		model.addAttribute("updateCnt", updateCnt);
		
	}


}
