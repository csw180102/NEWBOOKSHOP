package spring.mvc.bms.board.persistance;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.bms.board.vo.FreeBoardVO;

public interface FreeBoardDAO {
	//freeboard���̺��� ��� ���ڵ带 ū�ٱ��Ͽ� ���
	public ArrayList<FreeBoardVO> getALLPostList(Map<String, Integer> map);
	//�Խñ� �� ���� ���ϱ�
	public int getAllPostCnt();
	//�Խñ� 1���ڵ� vo�� �ֱ� select
	public FreeBoardVO selectOnePost(int board_seq);
	//��ȸ�� �ø���
	public int plusReadCnt(int board_seq);
	//Ư��id�� ���� bigVo�� ���
	public ArrayList<FreeBoardVO> getALLPostListById(Map<String, Object> map);
	//Ư��id�� ������ �� ����
	public int getAllPostCntById(String searchId);
	//�Խñ� ���� ���� update
	public int updateOnePost(Map<String, Object> map);
	//�Խñ� ���� delete �ϱ�
	public int deleteOnePost(int board_seq);
	//���ο� �Խñ� insert �ϱ�
	public int insertNewPost(FreeBoardVO vo);
	//�Խñ� ref �� ����
	public int updateRef(Map<String, Integer> map);
	
}
