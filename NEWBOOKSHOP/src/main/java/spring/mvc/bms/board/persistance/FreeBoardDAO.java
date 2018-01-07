package spring.mvc.bms.board.persistance;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.bms.board.vo.FreeBoardVO;

public interface FreeBoardDAO {
	//freeboard테이블의 모든 레코드를 큰바구니에 담기
	public ArrayList<FreeBoardVO> getALLPostList(Map<String, Integer> map);
	//게시글 총 갯수 구하기
	public int getAllPostCnt();
	//게시글 1레코드 vo에 넣기 select
	public FreeBoardVO selectOnePost(int board_seq);
	//조회수 올리기
	public int plusReadCnt(int board_seq);
	//특정id가 쓴글 bigVo에 담기
	public ArrayList<FreeBoardVO> getALLPostListById(Map<String, Object> map);
	//특정id가 쓴글의 총 갯수
	public int getAllPostCntById(String searchId);
	//게시글 수정 내용 update
	public int updateOnePost(Map<String, Object> map);
	//게시글 삭제 delete 하기
	public int deleteOnePost(int board_seq);
	//새로운 게시글 insert 하기
	public int insertNewPost(FreeBoardVO vo);
	//게시글 ref 등 업뎃
	public int updateRef(Map<String, Integer> map);
	
}
