package spring.mvc.bms.member.persistance;

import java.util.Map;

import spring.mvc.bms.member.vo.MemberVO;

public interface MemberDAO { //DB접근하여 쿼리 수행하는 객체 모음
	//
	public int idCheck(String id);
	//
	public int pwdCheck(Map<String, String> map);
	//회원 테이블 아이디와 비번 불러오는 작업(SELECT * FROM member WHERE id=?)
	public int idPwdCheck(Map<String, String> map);
	//가입폼에서 입력받은 id의 중복확인 작업(SELECT * FROM member WHERE id=?)
	public int dupCheck(String id);
	//뉴비회원정보 (INSERT INTO member ...)
	public int insertMember(MemberVO vo);
	//회원탈퇴 (DELETE FROM member WHERE id=?)
	public int deleteMember(String id);
	//회원수정정보 (UPDATE ....)
	public int updateMember(MemberVO vo);

}
