package spring.mvc.bms.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.bms.board.persistance.FreeBoardDAO;
import spring.mvc.bms.board.vo.FreeBoardVO;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Autowired
	private FreeBoardDAO dao;
	

	@Override
	public void showFreeboardListPro(HttpServletRequest req, Model model) {
		
		int pageSize = 20; //페이지당 글 갯수
		int pageBlock = 3; //화면당 맨밑에 뿌려진 페이지 갯수 
		String sessionId = (String) req.getSession().getAttribute("sessionId");

		
		
		//게시글 총갯수도 불러오는 DB문 만들어야되나
		int totalCnt = dao.getAllPostCnt();
		System.out.println("totalCnt(게시글 총갯수) : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);

		
		//*****************유일하게 전 작업페이지에서 넘긴 페이지값(이놈이 중요)*********************
		String pageNum = req.getParameter("pageNum"); //왜  페이지 번호를 string ?
			if(pageNum==null) {
				pageNum = "1";
			} //if조건문안에서는 정수형태는 null비교 를 못한다 그래서 마지막에 integer바꿨나보다
		model.addAttribute("pageNum", pageNum);
			
		int currentPage = Integer.parseInt(pageNum); //현재 페이지 값
		System.out.println("currentPage : " + currentPage);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp로 넘김 (현재 머물고 있는 페이지)
////////////////////////////////////////////////////////////////////////////////
		//출력되는 글번호 (오이거 헷깔림 중요)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		model.addAttribute("articleNum", articleNum);
		//총 페이지 갯수
		int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
		System.out.println("pageTotalCnt : " + pageTotalCnt);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp로 넘김 (총 페이지 갯수)
////////////////////////////////////////////////////////////////////////////////
		
		//참고로 seenNum은 없는 컬럼이기 때문에 범위가 넘어도 sql 오류 뜨고 그러지 않는다 
		//DB상의 rownum이다. 게시글 불러올때 DESC로 불러오고 rownum은 ASC로 붙였으므로
		//마지막 게시글의 rownum = 1 그 전 게시글은 rownum=2로 된다.
		int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
		int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
		System.out.println("seenNumStart : " + seenNumStart);
		System.out.println("seenNumEnd : " + seenNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp로 넘김 (출력하려는 seenNum 시작점)
		model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp로 넘김 (출력하려는 seenNum 마지막점)
////////////////////////////////////////////////////////////////////////////////
		
		//어느 경우에 필요한지.. 이거 있으나 마나 아닌가..?
		/*if(pageArticleEnd > totalCnt) {  
			pageArticleEnd = totalCnt;
		}*/
///////////////////////////////////////////////////////////////////////////////		
		model.addAttribute("totalCnt", totalCnt); //boardList.jsp로 넘김(게시글 총 갯수)
		model.addAttribute("pageBlock", pageBlock);
///////////////////////////////////////////////////////////////////////////////
		
		
		

//----------------------------------페이지 처리 관련 변수 만들기--------------------------------
		//글 10개씩 출력할떄 처럼 첫번쨰 글번호, 마지막 글번호 처럼  첫번째 페이지번호, 마지막 페이지 번호 필요
		int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
		if(currentPage%pageBlock==0) {
			pageNumStart -= pageBlock;
		} //이 방법이 제일 무난하네.. 요놈이 제일 핵심임..
		int pageNumEnd = pageNumStart + pageBlock - 1;
		if(pageNumEnd > pageTotalCnt) { //예를들어 pageBlock=3이고 제일 마지막 페이지가 [7][8]로 
			pageNumEnd = pageTotalCnt;  //끝나고 [9]가 없을 경우도 생각해줘야 한다. 또한 아얘 게시글이 없을때도
		}
		System.out.println("pageNumStart : " + pageNumStart);
		System.out.println("pageNumEnd : " + pageNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp로 넘김 (게시판 맨밑에 보이는 페이지 컨트롤창(?)의 첫번째 페이지 숫자)
		model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp로 넘김 (게시판 맨밑에 보이는 페이지 컨트롤창(?)의 마지막 페이지 숫자)
////////////////////////////////////////////////////////////////////////////////
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("seenNumStart", seenNumStart);
		map.put("seenNumEnd", seenNumEnd);
		
		
		//기존 게시글을 SELECT하여 불러오는 
		if(totalCnt > 0) { //게시글이 존재해야 아래 조건문 실행
			ArrayList<FreeBoardVO> bigVo = dao.getALLPostList(map);
			model.addAttribute("bigVo", bigVo); //총 70레코드를 담은 bigVo
		}
		
	}

	@Override
	public void showPostDetailPro(HttpServletRequest req, Model model) {
		
		int board_seq = Integer.parseInt(req.getParameter("board_seq"));
		System.out.println("board_seq : " + board_seq);
		int articleNum = Integer.parseInt(req.getParameter("articleNum"));
		System.out.println("게시글 번호  : " + articleNum);
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		System.out.println("페이지번호  : " + pageNum);
		
		//글제목을 누른순간 readCnt+1 이 됨 그게 freeboard의 readCnt칼럼에 저장.
		//그러면 그 저장된 컬럼을 아래 selectOnePost에서 뽑아쓰게됨
		int updateReadCnt = dao.plusReadCnt(board_seq);
		
		FreeBoardVO vo = dao.selectOnePost(board_seq);
		model.addAttribute("vo", vo);
		model.addAttribute("articleNum", articleNum);
		model.addAttribute("pageNum", pageNum);
		//req.setAttribute("readCnt", readCnt);
	}


	
	@Override //특정 id가 쓴 게시글 검색 서비스
	public void showMyPostPro(HttpServletRequest req, Model model) {
		
		int pageSize = 20; //페이지당 글 갯수
		int pageBlock = 3; //화면당 맨밑에 뿌려진 페이지 갯수
		//검색조건으로 입력한 아이디를 받아옴
		String searchId = (String)req.getParameter("searchId");
		System.out.println("searchId : " + searchId);
		//특정 id가 쓴 게시글 총 갯수 세기
		int totalCnt = dao.getAllPostCntById(searchId);
		System.out.println("totalCnt(누군가쓴글 총갯수) : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);

		
		//*****************유일하게 전 작업페이지에서 넘긴 페이지값(이놈이 중요)*********************
		String pageNum = req.getParameter("pageNum"); //왜  페이지 번호를 string ?
			if(pageNum==null) {
				pageNum = "1";
			} //if조건문안에서는 정수형태는 null비교 를 못한다 그래서 마지막에 integer바꿨나보다
			model.addAttribute("pageNum", pageNum);
			
		int currentPage = Integer.parseInt(pageNum); //현재 페이지 값
		System.out.println("currentPage : " + currentPage);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp로 넘김 (현재 머물고 있는 페이지)
////////////////////////////////////////////////////////////////////////////////
		//출력되는 글번호 (오이거 헷깔림 중요)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		req.setAttribute("articleNum", articleNum);
		//총 페이지 갯수
		int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
		System.out.println("pageTotalCnt : " + pageTotalCnt);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp로 넘김 (총 페이지 갯수)
////////////////////////////////////////////////////////////////////////////////
		
		//참고로 seenNum은 없는 컬럼이기 때문에 범위가 넘어도 sql 오류 뜨고 그러지 않는다 
		//DB상의 rownum이다. 게시글 불러올때 DESC로 불러오고 rownum은 ASC로 붙였으므로
		//마지막 게시글의 rownum = 1 그 전 게시글은 rownum=2로 된다.
		int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
		int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
		System.out.println("seenNumStart : " + seenNumStart);
		System.out.println("seenNumEnd : " + seenNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp로 넘김 (출력하려는 seenNum 시작점)
		model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp로 넘김 (출력하려는 seenNum 마지막점)
////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////		
		model.addAttribute("totalCnt", totalCnt); //boardList.jsp로 넘김(게시글 총 갯수)
		model.addAttribute("pageBlock", pageBlock);
///////////////////////////////////////////////////////////////////////////////
		
		
		

//----------------------------------페이지 처리 관련 변수 만들기--------------------------------
		//글 10개씩 출력할떄 처럼 첫번쨰 글번호, 마지막 글번호 처럼  첫번째 페이지번호, 마지막 페이지 번호 필요
		int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
		if(currentPage%pageBlock==0) {
			pageNumStart -= pageBlock;
		} //이 방법이 제일 무난하네.. 요놈이 제일 핵심임..
		int pageNumEnd = pageNumStart + pageBlock - 1;
		if(pageNumEnd > pageTotalCnt) { //예를들어 pageBlock=3이고 제일 마지막 페이지가 [7][8]로 
			pageNumEnd = pageTotalCnt;  //끝나고 [9]가 없을 경우도 생각해줘야 한다. 또한 아얘 게시글이 없을때도
		}
		System.out.println("pageNumStart : " + pageNumStart);
		System.out.println("pageNumEnd : " + pageNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp로 넘김 (게시판 맨밑에 보이는 페이지 컨트롤창(?)의 첫번째 페이지 숫자)
		model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp로 넘김 (게시판 맨밑에 보이는 페이지 컨트롤창(?)의 마지막 페이지 숫자)
////////////////////////////////////////////////////////////////////////////////
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seenNumStart", seenNumStart);
		map.put("seenNumEnd", seenNumEnd);
		map.put("searchId", searchId);

		if(totalCnt > 0) { //게시글이 존재해야 아래 조건문 실행
			ArrayList<FreeBoardVO> bigVo = dao.getALLPostListById(map);
			model.addAttribute("bigVo", bigVo);
		}
			
	}


	@Override
	public void updateMyPostPro(HttpServletRequest req, Model model) {
		
		int board_seq = Integer.parseInt(req.getParameter("board_seq"));
		//int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		FreeBoardVO vo = dao.selectOnePost(board_seq);
		model.addAttribute("vo", vo);
		
		
	}

	@Override
	public void updateMyPostProTwo(HttpServletRequest req, Model model) {
		
		int board_seq = Integer.parseInt(req.getParameter("board_seq"));
		//int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String subject = (String) req.getParameter("subject");
		String content = (String) req.getParameter("content");
		String ip = (String) req.getParameter("ip");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_seq", board_seq);
		map.put("subject", subject);
		map.put("content", content);
		map.put("ip", ip);
		
		int updateCnt = dao.updateOnePost(map);
		System.out.println("updateCnt(게시글 수정 여부) : " + updateCnt);
		model.addAttribute("updateCnt", updateCnt);
	}
	
	@Override
	public void deleteMyPostPro(HttpServletRequest req, Model model) {
		
		//where절 조건때문에 board_seq 받야아함
		int board_seq = Integer.parseInt(req.getParameter("board_seq"));
		
		int deleteCnt = dao.deleteOnePost(board_seq);
		model.addAttribute("deleteCnt", deleteCnt);
	}
	

	@Override
	public void writeNewPostPro(HttpServletRequest req, Model model) {
		

		//글쓰기 폼에서 받아온 내용을 vo에 담음
		FreeBoardVO vo = new FreeBoardVO();
		vo.setBoard_seq(Integer.parseInt(req.getParameter("board_seq")));
		System.out.println("글번호 : " + Integer.parseInt(req.getParameter("board_seq")));
		vo.setRef_level(Integer.parseInt(req.getParameter("ref_level")));
		vo.setRef_step(Integer.parseInt(req.getParameter("ref_step")));
		vo.setRef(Integer.parseInt(req.getParameter("ref")));
		vo.setId((String) req.getParameter("id"));
		vo.setSubject((String) req.getParameter("subject"));
		vo.setContent((String) req.getParameter("content"));
		vo.setIp(req.getRemoteAddr());
		
	    int insertCnt = dao.insertNewPost(vo);
	    model.addAttribute("insertCnt", insertCnt);
		
	}

	

	

	
	
}
