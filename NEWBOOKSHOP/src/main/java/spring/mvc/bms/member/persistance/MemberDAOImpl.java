package spring.mvc.bms.member.persistance;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.bms.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int idCheck(String id) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		int cnt = dao.idCheck(id);
		return cnt;
	}
	
	@Override
	public int pwdCheck(Map<String, String> map) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		int cnt = dao.pwdCheck(map);
		
		return cnt;
	}
	
	@Override
	public int idPwdCheck(Map<String, String> map) { 
		
		int cnt=0;
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		cnt = dao.idCheck(map.get("strId"));
		System.out.println("아이디 존재여부--idCheck() : " + cnt);
		
		if(cnt==1) {
			
			int idPwdChkCnt = dao.pwdCheck(map);
			System.out.println("아이디/비번 일치여부--pwdCheck() : " + idPwdChkCnt);
			if(idPwdChkCnt==1) {
				cnt = 1;
			} else {
				cnt = -1;
			}
		} else {
			cnt = 0;
		}
			
		return cnt;
	}

	@Override
	public int dupCheck(String id) {

		int cnt=0;
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		cnt = dao.dupCheck(id);
			
		return cnt;
	}


	@Override
	public int insertMember(MemberVO vo) {
		
		int insertCnt = 0;
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		insertCnt = dao.insertMember(vo);
		
		return insertCnt;
	}


	@Override
	public int deleteMember(String id) {
		
		int deleteCnt = 0;
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		deleteCnt = dao.deleteMember(id);
		
		return deleteCnt;
	}


	@Override
	public int updateMember(MemberVO vo) {
		
		int updateCnt=0;
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		updateCnt = dao.updateMember(vo);
		
		return updateCnt;
	}


}
