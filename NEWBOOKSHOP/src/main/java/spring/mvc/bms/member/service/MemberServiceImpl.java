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
public class MemberServiceImpl implements MemberService { //껍데기 모음
	
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
		System.out.println("id/pwd 최종 cnt : " + cnt);
		model.addAttribute("cnt", cnt);
	}


	@Override
	public void logoutPro(HttpServletRequest req, Model model) {
		req.getSession().setAttribute("sessionId", null);
		//cnt는?
	}

	@Override
	public void dupPro(HttpServletRequest req, Model model) {
		
		int cnt = 0;
		String strId = req.getParameter("id"); //join.jsp에서 받은 "id"
		cnt = dao.dupCheck(strId);
		
		model.addAttribute("strId", strId);
		model.addAttribute("cnt", cnt);
		
	}
	
	@Override
	public void joinPro(HttpServletRequest req, Model model) {
		
		//join에서 받아온 값들을 vo에 넣는다
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
		
		////일단 전부 vo 바구니에 담음... 이걸  MemberDAOImpl.getInstance().insert... 만들어서
		//보내버린다.
		int insertCnt = dao.insertMember(vo);
		
		model.addAttribute("insertCnt", insertCnt);
		
	}
	

	@Override
	public void deletePro(HttpServletRequest req, Model model) {
		//deleteForm.jsp에서 submit으로 비번, 동의값 넘겨 받음(아이디는 세션에서 가져오면됨)
		int deleteCnt = 0;
		String strId = (String)req.getSession().getAttribute("sessionId");
		String strPwd = req.getParameter("pwd");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		//아이디 비번 일치 안했녜...
		int checkCnt = dao.idPwdCheck(map);
		
		//위 값들을 MemberDAOImpl로 보내서 delete쿼리문 실행할 서비스 만든다
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
		//modifyForm.jsp로부터 수정정보들을 가져와야한다. 저쪽에선 submit으로 보내니 getPa... 
		//받은 정보를 vo에 저장한다
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
			vo.setEmail(email); //어차피 가입할떄 빈칸이면 입력하라고 뜨니까,, not null 될순 없지 않나?
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
