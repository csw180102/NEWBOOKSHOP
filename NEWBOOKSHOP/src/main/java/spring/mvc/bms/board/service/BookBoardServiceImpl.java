package spring.mvc.bms.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.bms.board.persistance.BookBoardDAO;
import spring.mvc.bms.board.vo.AccountListVO;
import spring.mvc.bms.board.vo.BookBoardVO;
import spring.mvc.bms.board.vo.OrderListVO;
import spring.mvc.bms.board.vo.RefundListVO;
import spring.mvc.bms.board.vo.TotalPriceVO;
import spring.mvc.bms.board.vo.WishListVO;



@Service
public class BookBoardServiceImpl implements BookBoardService {
	
	@Autowired
	private BookBoardDAO dao;
	
	@Override
	public void showListPro(HttpServletRequest req, Model model) {
		
		int pageSize = 24; 
		int pageBlock = 3; 
		System.out.println("showListPro()작업 진입..");
		int totalCnt = dao.selectAllStockCnt();
		System.out.println("책 재고 갯수 : " + totalCnt);
		
		String pageNum = req.getParameter("pageNum");
		
			if(pageNum==null) {
				pageNum = "1";
			}
		
		model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
		int currentPage = Integer.parseInt(pageNum); //현재 페이지 값
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp로 넘김 (현재 머물고 있는 페이지)
////////////////////////////////////////////////////////////////////////////////
		//출력되는 글번호 (오이거 헷깔림 중요)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		model.addAttribute("articleNum", articleNum);
		model.addAttribute("seq", 0);
		//총 페이지 갯수
		int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp로 넘김 (총 페이지 갯수)
////////////////////////////////////////////////////////////////////////////////
		int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
		int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp로 넘김 (출력하려는 seenNum 시작점)
		model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp로 넘김 (출력하려는 seenNum 마지막점)
////////////////////////////////////////////////////////////////////////////////
		
		//어느 경우에 필요한지.. 이거 있으나 마나 아닌가..?
		/*if(pageArticleEnd > totalCnt) {  
			pageArticleEnd = totalCnt;
		}*/
///////////////////////////////////////////////////////////////////////////////		
		req.setAttribute("totalCnt", totalCnt); //boardList.jsp로 넘김(게시글 총 갯수)
		req.setAttribute("pageBlock", pageBlock);
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
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp로 넘김 (게시판 맨밑에 보이는 페이지 컨트롤창(?)의 첫번째 페이지 숫자)
		model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp로 넘김 (게시판 맨밑에 보이는 페이지 컨트롤창(?)의 마지막 페이지 숫자)
////////////////////////////////////////////////////////////////////////////////
		
		//기존 게시글을 SELECT하여 불러오는 
		if(totalCnt > 0) { 
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("seenNumStart", seenNumStart);
			map.put("seenNumEnd", seenNumEnd);
			
			
			ArrayList<BookBoardVO> bigVo = dao.selectAllStock(map);
			model.addAttribute("bigVo", bigVo); 
		}
	}


	@Override
	public void showListProTwo(HttpServletRequest req, Model model) {
		
		int pageSize = 6; //페이지당 글 갯수
		int pageBlock = 3; //화면당 맨밑에 뿌려진 페이지 갯수

		//게시글 총갯수도 불러오는 DB문 만들어야되나
		int totalCnt = dao.selectAllStockCnt();
		System.out.println("totalCnt : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		//*****************유일하게 전 작업페이지에서 넘긴 페이지값(이놈이 중요)*********************
		String pageNum = req.getParameter("pageNum"); //왜  페이지 번호를 string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if조건문안에서는 정수형태는 null비교 를 못한다 그래서 마지막에 integer바꿨나보다
//////////////////////////////////////////////////////////////////////////////
		System.out.println("pageNum : " + pageNum);
		model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
		int currentPage = Integer.parseInt(pageNum); //현재 페이지 값
		System.out.println("currentPage : " + currentPage);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp로 넘김 (현재 머물고 있는 페이지)
////////////////////////////////////////////////////////////////////////////////
		//출력되는 글번호 (오이거 헷깔림 중요)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		model.addAttribute("articleNum", articleNum);
		model.addAttribute("seq", 0);
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
		
		
		
		//기존 게시글을 SELECT하여 불러오는 
		if(totalCnt > 0) { 
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("seenNumStart", seenNumStart);
			map.put("seenNumEnd", seenNumEnd);
			
			ArrayList<BookBoardVO> bigVo = dao.selectAllStock(map);
			model.addAttribute("bigVo", bigVo); 
		}
		System.out.println("------------------------------");
		
	}
	
	/*	
	@Override
	public void showMainPro(HttpServletRequest req, HttpServletResponse res) {
		

		//게시글 총갯수도 불러오는 DB문 만들어야되나
		int totalCnt = BookBoardDAOImpl.getInstance().selectAllStockCnt();
		System.out.println("totalCnt : " + totalCnt);
		req.setAttribute("totalCnt", totalCnt);
		int seenNumStart = 1;
		//aticleNum이랑 
		
		if(totalCnt > 0) { //pageNum이 바뀌면 seenNum도 바뀌는데 나는 그냥 1~totalCnt까지 배열길이를 선언한다
			ArrayList<BookBoardVO> bigVo = BookBoardDAOImpl.getInstance().selectAllStockInArrayList(seenNumStart, totalCnt);
			req.setAttribute("bigVo", bigVo); 
		}
		
	}
	*/
	
	@Override
	public void showListMainDetailPro(HttpServletRequest req, Model model) {
		
		System.out.println("showListMainDetailPro 진입");
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("book_seq : " + book_seq);

		//상세 책 정보 가져오는 DAO 호출
		BookBoardVO vo = dao.getStockDetail(book_seq);
		model.addAttribute("vo", vo);
		
	}
	
	@Override
	public void showListDetailPro(HttpServletRequest req, Model model) {
		
		//필요한 값들??
		System.out.println("showListDetailPro()작업 진입...");
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("book_seq : " + book_seq);
		int articleNum = Integer.parseInt(req.getParameter("articleNum"));
		System.out.println("articleNum : " + articleNum);
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		System.out.println("pageNum : " + pageNum);
		//상세 책 정보 가져오는 DAO 호출
		BookBoardVO vo = dao.getStockDetail(book_seq);
		model.addAttribute("vo", vo);
		model.addAttribute("articleNum", articleNum);
		model.addAttribute("pageNum", pageNum);
	}

	
	@Override
	public void listDeletePro(HttpServletRequest req, Model model) {
		
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("book_seq 잘 받음 : " + book_seq);

/////////////////////////////하나만 선택해서 삭제할때////////////////////////////
		
		int deleteCnt = dao.deleteStock(book_seq);
		model.addAttribute("deleteCnt", deleteCnt);

	}


	@Override
	public void listInsertPro(MultipartHttpServletRequest req, Model model) {

		MultipartFile file = req.getFile("image");
		int maxSize = 10 * 1024 * 1024;
		String saveDir = req.getRealPath("/resources/images/books/");
		String realDir = "C:\\Dev\\workspace\\NEWBOOKSHOP\\src\\main\\webapp\\resources\\images\\books\\";
		String encType = "UTF-8";

		try {
			
			file.transferTo(new File(saveDir+file.getOriginalFilename()));
            
            FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());
			
			
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();
			
			BookBoardVO vo = new BookBoardVO();
			//폼에서 넘겨 받은 값들.. MR.XX
			
			vo.setImage(file.getOriginalFilename());
			System.out.println(file.getOriginalFilename());
			vo.setIsbn(req.getParameter("isbn"));
			System.out.println("isbn : " + req.getParameter("isbn"));
			vo.setBook_name(req.getParameter("book_name"));
			System.out.println("book_name : " + req.getParameter("book_name") );
			vo.setAuthor(req.getParameter("author"));
			System.out.println("author : " + req.getParameter("author") );
			vo.setPublisher(req.getParameter("publisher"));
			System.out.println("publisher : " + req.getParameter("publisher") );
			vo.setDomeforei(req.getParameter("domeforei"));
			System.out.println("domeforei : " + req.getParameter("domeforei") );
			vo.setNewold(req.getParameter("newold"));
			System.out.println("newold : " + req.getParameter("newold") );
			vo.setPrice(Integer.parseInt(req.getParameter("price")));
			System.out.println("price : " + Integer.parseInt(req.getParameter("price")) );
			vo.setStock(Integer.parseInt(req.getParameter("stock")));
			System.out.println("stock : " + Integer.parseInt(req.getParameter("stock")) );
			vo.setContent(req.getParameter("content"));
			System.out.println("content : " + req.getParameter("content") );

			
			//vo바구니를 putStock으로 넘긴다
			int insertCnt = dao.insertStock(vo);
			model.addAttribute("insertCnt", insertCnt);
			System.out.println("insertCnt 재고추가여부 : " + insertCnt);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void listUpdatePro(MultipartHttpServletRequest req, Model model) {
		
		MultipartFile file = req.getFile("image");
		int maxSize = 10 * 1024 * 1024;
		String saveDir = req.getRealPath("/resources/images/books/");
		String realDir = "C:\\Dev\\workspace\\NEWBOOKSHOP\\src\\main\\webapp\\resources\\images\\books\\";
		String encType = "UTF-8";

		try {
			
			file.transferTo(new File(saveDir+file.getOriginalFilename()));
            
            FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());
			
			
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();
			
			BookBoardVO vo = new BookBoardVO();
			//폼에서 넘겨 받은 값들.. MR.XX
			
			vo.setImage(file.getOriginalFilename());
			System.out.println("image : " + file.getOriginalFilename());
			vo.setIsbn(req.getParameter("isbn"));
			System.out.println("isbn : " + req.getParameter("isbn"));
			vo.setBook_name(req.getParameter("book_name"));
			System.out.println("book_name : " + req.getParameter("book_name") );
			vo.setAuthor(req.getParameter("author"));
			System.out.println("author : " + req.getParameter("author") );
			vo.setPublisher(req.getParameter("publisher"));
			System.out.println("publisher : " + req.getParameter("publisher") );
			vo.setDomeforei(req.getParameter("domeforei"));
			System.out.println("domeforei : " + req.getParameter("domeforei") );
			vo.setNewold(req.getParameter("newold"));
			System.out.println("newold : " + req.getParameter("newold") );
			vo.setPrice(Integer.parseInt(req.getParameter("price")));
			System.out.println("price : " + Integer.parseInt(req.getParameter("price")) );
			vo.setStock(Integer.parseInt(req.getParameter("stock")));
			System.out.println("stock : " + Integer.parseInt(req.getParameter("stock")) );
			vo.setContent(req.getParameter("content"));
			System.out.println("content : " + req.getParameter("content") );
			vo.setBook_seq(Integer.parseInt(req.getParameter("book_seq")));
			
			
			//vo바구니를 putStock으로 넘긴다
			int updateCnt = dao.updateStock(vo);
			model.addAttribute("updateCnt", updateCnt);
			System.out.println("updateCnt 재고수정여부 : " + updateCnt);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}




	@Override
	public void wishListPro(HttpServletRequest req, Model model) {

		int book_seq = Integer.parseInt(req.getParameter("book_seq"));

		BookBoardVO vo = dao.getStockDetail(book_seq);
		model.addAttribute("vo", vo);
	}


	@Override
	public void wishListProTwo(HttpServletRequest req, Model model) {
		
		//폼으로 부터 넘겨받은 값들
		
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("1 : " + book_seq);
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		System.out.println("2 : " + quantity);
		String id = (String) req.getSession().getAttribute("sessionId");
		System.out.println("3 : " + id);
		String book_name = req.getParameter("book_name");
		System.out.println("4 : " + book_name);
		String author = req.getParameter("author");
		System.out.println("5 : " + author);
		String publisher = req.getParameter("publisher");
		System.out.println("6 : " + publisher);
		int price = Integer.parseInt(req.getParameter("price"));
		System.out.println("7 : " + price);
		
		WishListVO vo = new WishListVO();
		
		vo.setBook_seq(book_seq);
		vo.setId(id);
		vo.setBook_name(book_name);
		vo.setAuthor(author);
		vo.setPublisher(publisher);
		vo.setPrice(price);
		vo.setQuantity(quantity);

		int insertCnt = dao.insertWishlist(vo);
		
		model.addAttribute("insertCnt", insertCnt);
		
		
	}


	@Override
	public void showWishListPro(HttpServletRequest req, Model model) {
		
		//현재 위시리스트 테이블은 만들어져있는 상태다
		//테이블의 각각의 컬럼 값을 select로 하나하나 꺼내서(rs.next) vo에 담은다음 최종적으로 bigVo에 담아서 
		//myWishList.jsp로 넘긴다(jstl이용forEach)
		
		int pageSize = 10; //페이지당 글 갯수
		int pageBlock = 3; //화면당 맨밑에 뿌려진 페이지 갯수 
		
		String sessionId = (String) req.getSession().getAttribute("sessionId");
		//게시글 총갯수도 불러오는 DB문 만들어야되나
		int totalCnt = dao.selectAllWishListCnt(sessionId);
		System.out.println("totalCnt : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		//*****************유일하게 전 작업페이지에서 넘긴 페이지값(이놈이 중요)*********************
		String pageNum = req.getParameter("pageNum"); //왜  페이지 번호를 string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if조건문안에서는 정수형태는 null비교 를 못한다 그래서 마지막에 integer바꿨나보다
//////////////////////////////////////////////////////////////////////////////
		req.setAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
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
		map.put("sessionId", sessionId);
		
		//기존 게시글을 SELECT하여 불러오는 
		if(totalCnt > 0) { 
			ArrayList<WishListVO> bigVo = dao.selectAllWishList(map);
			model.addAttribute("bigVo", bigVo);
			TotalPriceVO vo1 = dao.selectWishAmount(sessionId);
			model.addAttribute("vo1", vo1);
		}
		
	}



	@Override
	public void deleteWishListPro(HttpServletRequest req, Model model) {
		
		int wishlist_seq = Integer.parseInt(req.getParameter("wishlist_seq"));
		int deleteCnt = dao.deleteWishList(wishlist_seq);

		model.addAttribute("deleteCnt", deleteCnt);
	}
	
	
	@Override
	public void showOrderListPro(HttpServletRequest req, Model model) {
		
		//BookBoardDAOImpl.getInstance().selectBuyList();

		//테이블의 각각의 컬럼 값을 select로 하나하나 꺼내서(rs.next) vo에 담은다음 최종적으로 bigVo에 담아서 
		//myOrderList.jsp로 넘긴다(jstl이용forEach)
		
		int pageSize = 10; //페이지당 글 갯수
		int pageBlock = 3; //화면당 맨밑에 뿌려진 페이지 갯수 
		String sessionId = (String) req.getSession().getAttribute("sessionId");
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("sessionId", sessionId);
		
		
		//게시글 총갯수도 불러오는 DB문 만들어야되나
		System.out.println("selectAllOrderListcnt() 실행 준비...");
		int totalCnt = dao.selectAllOrderListCnt(map1);
		System.out.println("(구매목록 총갯수)totalCnt : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		//*****************유일하게 전 작업페이지에서 넘긴 페이지값(이놈이 중요)*********************
		String pageNum = req.getParameter("pageNum"); //왜  페이지 번호를 string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if조건문안에서는 정수형태는 null비교 를 못한다 그래서 마지막에 integer바꿨나보다
//////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
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
		map.put("sessionId", sessionId);
		
		//기존 게시글을 SELECT하여 불러오는 
		if(totalCnt > 0) { 
			ArrayList<OrderListVO> bigVo = dao.selectAllOrderList(map);
			model.addAttribute("bigVo", bigVo);
			TotalPriceVO vo1 = dao.selectOrderAmount(map1);
			model.addAttribute("vo1", vo1);
		}
	
	}
	
	
	@Override
	public void orderListPro(HttpServletRequest req, Model model) {
		
		//myWishList.jsp의 메뉴로부터 넘어온 book_seq, id(이건 세션), quantity를 받는다
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		int wishlist_seq = Integer.parseInt(req.getParameter("wishlist_seq"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String sessionId = (String) req.getSession().getAttribute("sessionId");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("book_seq", book_seq);
		map.put("quantity", quantity);
		map.put("sessionId", sessionId);
		
		int insertCnt = dao.insertOrderList(map);
		model.addAttribute("insertCnt", insertCnt);
		
		//위시리스트 삭제하는 서비스
		int deleteCnt = dao.deleteWishList(wishlist_seq);
		model.addAttribute("deleteCnt", deleteCnt);
	}




	@Override
	public void deleteOrderListPro(HttpServletRequest req, Model model) {
		
		int buy_seq = Integer.parseInt(req.getParameter("buy_seq"));
		int deleteCnt = dao.deleteOrderList(buy_seq);
		model.addAttribute("deleteCnt", deleteCnt);
	}
	
	@Override
	public void showRefundListPro(HttpServletRequest req, Model model) {
		
		//bigVo 뿌려서 보여주면 됨 
		int pageSize = 10; //페이지당 글 갯수
		int pageBlock = 3; //화면당 맨밑에 뿌려진 페이지 갯수 
		String sessionId = (String) req.getSession().getAttribute("sessionId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		
		//게시글 총갯수도 불러오는 DB문 만들어야되나
		int totalCnt = dao.selectAllRefundListCnt(map);
		System.out.println("(환불목록 총갯수)totalCnt : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		//*****************유일하게 전 작업페이지에서 넘긴 페이지값(이놈이 중요)*********************
		String pageNum = req.getParameter("pageNum"); //왜  페이지 번호를 string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if조건문안에서는 정수형태는 null비교 를 못한다 그래서 마지막에 integer바꿨나보다
//////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
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
		
		map.put("seenNumStart", seenNumStart);
		map.put("seenNumEnd", seenNumEnd);
		
		
		//기존 게시글을 SELECT하여 불러오는 
		if(totalCnt > 0) { 
			ArrayList<RefundListVO> bigVo = dao.selectAllRefundList(map);
			model.addAttribute("bigVo", bigVo); 
		}
	
	}
	
	
	@Override
	public void refundPro(HttpServletRequest req, Model model) {
		//구매목록에서 환불요청과 동시에 환불목록에 insert 후 구매목록에서 delete
		int buy_seq = Integer.parseInt(req.getParameter("buy_seq"));
		System.out.println("buy_seq : " + buy_seq);
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("book_seq : " + book_seq);
		

		OrderListVO vo = dao.selectFinalOrderList(buy_seq);

		
		int insertCnt = dao.insertRefundList(vo);
		System.out.println("insertCnt(환불목록추가) " + insertCnt);
		model.addAttribute("insertCnt", insertCnt);
		
		//2. 환불요청을 했으니 최종 구매 관리자 승인 form에서 삭제를 해야한다...finalAdminOrderList.jsp
		dao.deleteFinalOrderList(buy_seq);
			
	}
	
	@Override
	public void approveOrderPro(HttpServletRequest req, Model model) {
		
		int buy_seq = Integer.parseInt(req.getParameter("buy_seq"));
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("approveOrderPro() 진입...");
		System.out.println(buy_seq);
		System.out.println(book_seq);

					
		int AfterOrderStock = dao.orderStock(buy_seq);
		System.out.println("주문수량 : " + AfterOrderStock);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("AfterOrderStock", AfterOrderStock);
		map.put("book_seq", book_seq);
		
		
		
		

		//최종 승인을 하기 위함 재고 떨기
		if(AfterOrderStock >= 0) {
			
			int updateCnt = dao.updateStockColumn(map);
			System.out.println("updateCnt(책 재고 수정여부) : " + updateCnt);
			model.addAttribute("updateCnt", updateCnt);
			
			//buylist에서 레코드1개 select 한다
			OrderListVO vo = dao.selectOrderList(buy_seq);
			//"승인"클릭한 buylist정보를 finalbuylist로 insert 한다.(finalbuylist 테이블 생성완료)
			int insertCnt = dao.insertFinalAdminOrderList(vo);
			model.addAttribute("insertCnt", insertCnt);
			
			
			AccountListVO vo1 = dao.selectOneFinalOrderList(buy_seq);
			
			int insertAccountCnt = dao.insertClosingAccountApprovalList(vo1);
			model.addAttribute("insertAccountCnt", insertAccountCnt);
			
			int deleteCnt = dao.deleteOrderList(buy_seq);
			model.addAttribute("deleteCnt", deleteCnt);
		} else {
			model.addAttribute("updateCnt", 0);
		}

	}

	@Override
	public void showFinalAdminOrderList(HttpServletRequest req, Model model) {
		
		//bigVo 뿌려서 보여주면 됨 
				int pageSize = 10; //페이지당 글 갯수
				int pageBlock = 3; //화면당 맨밑에 뿌려진 페이지 갯수 
				
				String sessionId = (String) req.getSession().getAttribute("sessionId");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sessionId", sessionId);
				int totalCnt = dao.selectAllFinalOrderListCnt(map);
				System.out.println("totalCnt : " + totalCnt);
				System.out.println("pageSize : " + pageSize);
				System.out.println("pageBlock : " + pageBlock);
				
				
				//*****************유일하게 전 작업페이지에서 넘긴 페이지값(이놈이 중요)*********************
				String pageNum = req.getParameter("pageNum"); //왜  페이지 번호를 string ?
				
					if(pageNum==null) {
						pageNum = "1";
					} //if조건문안에서는 정수형태는 null비교 를 못한다 그래서 마지막에 integer바꿨나보다
		//////////////////////////////////////////////////////////////////////////////
					model.addAttribute("pageNum", pageNum);
		////////////////////////////////////////////////////////////////////////////
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
				map.put("seenNumStart", seenNumStart);
				map.put("seenNumEnd", seenNumEnd);

				
				//기존 게시글을 SELECT하여 불러오는 
				if(totalCnt > 0) { 
					ArrayList<OrderListVO> bigVo = dao.selectAllFinalOrderList(map);
					model.addAttribute("bigVo", bigVo);
					TotalPriceVO vo1 = dao.selectFinalAdminOrderAmount(map);
					model.addAttribute("vo1", vo1);
					
					
				}
							
				
	}				
	
	
	
	@Override
	public void showFinalRefundListPro(HttpServletRequest req, Model model) {
		
		//bigVo 뿌려서 보여주면 됨 
				int pageSize = 10; //페이지당 글 갯수
				int pageBlock = 3; //화면당 맨밑에 뿌려진 페이지 갯수 
				
				String sessionId = (String) req.getSession().getAttribute("sessionId");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sessionId", sessionId);
				
				int totalCnt = dao.selectAllFinalRefundListCnt(map);
				System.out.println("totalCnt (최종환불목록 갯수): " + totalCnt);
				System.out.println("pageSize : " + pageSize);
				System.out.println("pageBlock : " + pageBlock);
				
				
				//*****************유일하게 전 작업페이지에서 넘긴 페이지값(이놈이 중요)*********************
				String pageNum = req.getParameter("pageNum"); //왜  페이지 번호를 string ?
				
					if(pageNum==null) {
						pageNum = "1";
					} //if조건문안에서는 정수형태는 null비교 를 못한다 그래서 마지막에 integer바꿨나보다
		//////////////////////////////////////////////////////////////////////////////
					model.addAttribute("pageNum", pageNum);
		////////////////////////////////////////////////////////////////////////////
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
				
				
				
				map.put("seenNumStart", seenNumStart);
				map.put("seenNumEnd", seenNumEnd);
				
				
				if(totalCnt > 0) { 
					ArrayList<RefundListVO> bigVo = dao.selectAllFinalRefundList(map);
					model.addAttribute("bigVo", bigVo);
								
				}

	}

	
	@Override
	public void approveRefundPro(HttpServletRequest req, Model model) {
		
		int refund_seq = Integer.parseInt(req.getParameter("refund_seq"));
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("refund_seq : " + refund_seq);
		System.out.println("book_seq : " + book_seq);
		
		
		
		System.out.println("updateStockColumnPlus() 진입 준비..");
		
		//book, refund조인한거 재고 + 환불재고  int뽑고 
		//위 내용을 update하는 걸로 2개로 나눔
		
		int afterRefundStock = dao.refundStock(refund_seq);
		System.out.println("환불갯수 : " + afterRefundStock);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("afterRefundStock", afterRefundStock);
		map.put("book_seq", book_seq);
		
		
		//환불이 안되는 경우는? 책이 존재하지 않을때는 환불이 안되는데 그걸 어케 조건달지?
		//그냥 손님이 산책이 있는경우는 재고삭제하지 말자 지우려면 cascade이런거 달아야됨
		
		if(afterRefundStock >= 0) {
			
			int updateCnt = dao.updateStockColumnPlus(map);  //이게 안되네...
			model.addAttribute("updateCnt", updateCnt);
			System.out.println("updateCnt(책 재고 수정여부) : " + updateCnt);
			
			//하나의 refund를 finalrefund로 보낼 준비
			RefundListVO vo = dao.selectOneRefundList(refund_seq);
			int insertCnt = dao.insertFinalRefundList(vo);
			model.addAttribute("insertCnt", insertCnt); //finalrefund에 넣기 성공
			
			AccountListVO vo1 = dao.selectOneFinalRefundList(refund_seq);
			int insertAccountCnt = dao.insertClosingAccountRefundList(vo1);
			model.addAttribute("insertAccountCnt", insertAccountCnt);
			
			
			int deleteCnt = dao.deleteRefundList(refund_seq);
			System.out.println("deleteCnt(환불승인껀 테이블에서 지우기) : " + deleteCnt);
			model.addAttribute("deleteCnt", deleteCnt);
			//최종환불승인목록 만들자
			
		} else {
			model.addAttribute("updateCnt", 0);
		}
			
		
		
	}

	@Override
	public void showClosingAccountListPro(HttpServletRequest req, Model model) {
		
		//bigVo 뿌려서 보여주면 됨 
		int pageSize = 10; //페이지당 글 갯수
		int pageBlock = 3; //화면당 맨밑에 뿌려진 페이지 갯수 

		//게시글 총갯수도 불러오는 DB문 만들어야되나
		int totalCnt = dao.selectAllClosingAccountListCnt();
		System.out.println("totalCnt (매출기록 갯수) : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		
		String pageNum = req.getParameter("pageNum"); //왜  페이지 번호를 string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if조건문안에서는 정수형태는 null비교 를 못한다 그래서 마지막에 integer바꿨나보다
//////////////////////////////////////////////////////////////////////////////
			model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
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
		//기존 게시글을 SELECT하여 불러오는 
		if(totalCnt > 0) { 
			ArrayList<AccountListVO> bigVo = dao.selectAllClosingAccountList(map);
			model.addAttribute("bigVo", bigVo);	
		}
	}

/*
	@Override
	public void showMonthlySales(HttpServletRequest req, Model model) {
		
		ArrayList<AccountListVO> vo = dao.getMonthlySales();
		req.setAttribute("bigVo", vo);
		req.setAttribute("month", 0);
		
	}
*/

	


	
		
}
