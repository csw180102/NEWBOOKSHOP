package spring.mvc.bms.member.persistance;

import java.util.Map;

import spring.mvc.bms.member.vo.MemberVO;

public interface MemberDAO { //DB�����Ͽ� ���� �����ϴ� ��ü ����
	//
	public int idCheck(String id);
	//
	public int pwdCheck(Map<String, String> map);
	//ȸ�� ���̺� ���̵�� ��� �ҷ����� �۾�(SELECT * FROM member WHERE id=?)
	public int idPwdCheck(Map<String, String> map);
	//���������� �Է¹��� id�� �ߺ�Ȯ�� �۾�(SELECT * FROM member WHERE id=?)
	public int dupCheck(String id);
	//����ȸ������ (INSERT INTO member ...)
	public int insertMember(MemberVO vo);
	//ȸ��Ż�� (DELETE FROM member WHERE id=?)
	public int deleteMember(String id);
	//ȸ���������� (UPDATE ....)
	public int updateMember(MemberVO vo);

}
