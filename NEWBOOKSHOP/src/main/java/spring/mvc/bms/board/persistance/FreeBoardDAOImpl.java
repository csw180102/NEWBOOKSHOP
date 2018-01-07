package spring.mvc.bms.board.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.bms.board.vo.FreeBoardVO;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getAllPostCnt() {
		
		int cnt = 0;
				
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		cnt = dao.getAllPostCnt();
		
		return cnt;
	}
	
	@Override
	public ArrayList<FreeBoardVO> getALLPostList(Map<String, Integer> map) {
		
		int seenNumStart = map.get("seenNumStart");
		int seenNumEnd = map.get("seenNumEnd");
		ArrayList<FreeBoardVO> bigVo = new ArrayList<FreeBoardVO>(seenNumEnd - seenNumStart + 1);
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		bigVo = dao.getALLPostList(map);

		return bigVo;
	}



	@Override
	public FreeBoardVO selectOnePost(int board_seq) {
		
		FreeBoardVO vo = new FreeBoardVO();
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		vo = dao.selectOnePost(board_seq);
		
		return vo;
	}

	@Override
	public int plusReadCnt(int board_seq) {
		
		int updateReadCnt = 0;
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		updateReadCnt = dao.plusReadCnt(board_seq);
		return updateReadCnt;
	}

	
	@Override
	public int getAllPostCntById(String searchId) {
		
		int cnt = 0;
		
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		cnt = dao.getAllPostCntById(searchId);
		return cnt;
	}
	
	
	@Override //특정 id가 쓴 게시글 검색하여 bigVo에 담아서 freeboard.jsp로 쏴준다
	public ArrayList<FreeBoardVO> getALLPostListById(Map<String, Object> map) {
		
		int seenNumStart = (Integer)map.get("seenNumStart");
		int seenNumEnd = (Integer)map.get("seenNumEnd");
		ArrayList<FreeBoardVO> bigVo = new ArrayList<FreeBoardVO>(seenNumEnd - seenNumStart + 1);
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		bigVo = dao.getALLPostListById(map);
		
		return bigVo;

	}

	
	@Override
	public int updateOnePost(Map<String, Object> map) {

		int updateCnt = 0;
		
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		updateCnt = dao.updateOnePost(map);
		return updateCnt;
	}
	
	@Override
	public int deleteOnePost(int board_seq) {
		
		int deleteCnt = 0;
		
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		deleteCnt = dao.deleteOnePost(board_seq);
		return deleteCnt;
	}
	

	@Override
	public int updateRef(Map<String, Integer> map) {
		
		int updateCnt = 0;
		
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		updateCnt = dao.updateRef(map);
		
		return updateCnt;
	}
	
	@Override
	public int insertNewPost(FreeBoardVO vo) {
		System.out.println("ddddddddddddddddd");
		int insertCnt = 0;
		FreeBoardDAO dao = sqlSession.getMapper(FreeBoardDAO.class);
		int book_seq = vo.getBoard_seq();
		int ref = vo.getRef();
		int ref_level = vo.getRef_level();
		int ref_step = vo.getRef_step();
		
		if(book_seq==0) { //답글이 아니고 일반 글이라면
			//새로 만들어질 글의 book_seq값은 현재 freeboard테이블의 book_seq최대값 + 1 이 되어야함
			
			int maxBook_seq = dao.getAllPostCnt();
			
			if(maxBook_seq > 0) { //기존에 글이 있을때 
				ref = maxBook_seq + 1;
			} else { //진짜 썡 새글일때
				ref = 1;
			}
			
				ref_level = 0;
				ref_step = 0;
		} else {
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("ref", ref);
			map.put("ref_step", ref_step);
			
			dao.updateRef(map);
			
			ref_step++;
			ref_level++;
		}
		
		vo.setRef(ref);
		vo.setRef_level(ref_level);
		vo.setRef_step(ref_step);
		
		insertCnt = dao.insertNewPost(vo);
		
		return insertCnt;
	}

	

	


}
