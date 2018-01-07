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
		System.out.println("showListPro()�۾� ����..");
		int totalCnt = dao.selectAllStockCnt();
		System.out.println("å ��� ���� : " + totalCnt);
		
		String pageNum = req.getParameter("pageNum");
		
			if(pageNum==null) {
				pageNum = "1";
			}
		
		model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
		int currentPage = Integer.parseInt(pageNum); //���� ������ ��
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp�� �ѱ� (���� �ӹ��� �ִ� ������)
////////////////////////////////////////////////////////////////////////////////
		//��µǴ� �۹�ȣ (���̰� ��� �߿�)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		model.addAttribute("articleNum", articleNum);
		model.addAttribute("seq", 0);
		//�� ������ ����
		int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp�� �ѱ� (�� ������ ����)
////////////////////////////////////////////////////////////////////////////////
		int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
		int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ������)
		model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ��������)
////////////////////////////////////////////////////////////////////////////////
		
		//��� ��쿡 �ʿ�����.. �̰� ������ ���� �ƴѰ�..?
		/*if(pageArticleEnd > totalCnt) {  
			pageArticleEnd = totalCnt;
		}*/
///////////////////////////////////////////////////////////////////////////////		
		req.setAttribute("totalCnt", totalCnt); //boardList.jsp�� �ѱ�(�Խñ� �� ����)
		req.setAttribute("pageBlock", pageBlock);
///////////////////////////////////////////////////////////////////////////////


//----------------------------------������ ó�� ���� ���� �����--------------------------------
		//�� 10���� ����ҋ� ó�� ù���� �۹�ȣ, ������ �۹�ȣ ó��  ù��° ��������ȣ, ������ ������ ��ȣ �ʿ�
		int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
		if(currentPage%pageBlock==0) {
			pageNumStart -= pageBlock;
		} //�� ����� ���� �����ϳ�.. ����� ���� �ٽ���..
		int pageNumEnd = pageNumStart + pageBlock - 1;
		if(pageNumEnd > pageTotalCnt) { //������� pageBlock=3�̰� ���� ������ �������� [7][8]�� 
			pageNumEnd = pageTotalCnt;  //������ [9]�� ���� ��쵵 ��������� �Ѵ�. ���� �ƾ� �Խñ��� ��������
		}
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ù��° ������ ����)
		model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ������ ������ ����)
////////////////////////////////////////////////////////////////////////////////
		
		//���� �Խñ��� SELECT�Ͽ� �ҷ����� 
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
		
		int pageSize = 6; //�������� �� ����
		int pageBlock = 3; //ȭ��� �ǹؿ� �ѷ��� ������ ����

		//�Խñ� �Ѱ����� �ҷ����� DB�� �����ߵǳ�
		int totalCnt = dao.selectAllStockCnt();
		System.out.println("totalCnt : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		//*****************�����ϰ� �� �۾����������� �ѱ� ��������(�̳��� �߿�)*********************
		String pageNum = req.getParameter("pageNum"); //��  ������ ��ȣ�� string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if���ǹ��ȿ����� �������´� null�� �� ���Ѵ� �׷��� �������� integer�ٲ峪����
//////////////////////////////////////////////////////////////////////////////
		System.out.println("pageNum : " + pageNum);
		model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
		int currentPage = Integer.parseInt(pageNum); //���� ������ ��
		System.out.println("currentPage : " + currentPage);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp�� �ѱ� (���� �ӹ��� �ִ� ������)
////////////////////////////////////////////////////////////////////////////////
		//��µǴ� �۹�ȣ (���̰� ��� �߿�)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		model.addAttribute("articleNum", articleNum);
		model.addAttribute("seq", 0);
		//�� ������ ����
		int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
		System.out.println("pageTotalCnt : " + pageTotalCnt);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp�� �ѱ� (�� ������ ����)
////////////////////////////////////////////////////////////////////////////////
		
		//����� seenNum�� ���� �÷��̱� ������ ������ �Ѿ sql ���� �߰� �׷��� �ʴ´� 
		//DB���� rownum�̴�. �Խñ� �ҷ��ö� DESC�� �ҷ����� rownum�� ASC�� �ٿ����Ƿ�
		//������ �Խñ��� rownum = 1 �� �� �Խñ��� rownum=2�� �ȴ�.
		int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
		int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
		System.out.println("seenNumStart : " + seenNumStart);
		System.out.println("seenNumEnd : " + seenNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ������)
		model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ��������)
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////		
		model.addAttribute("totalCnt", totalCnt); //boardList.jsp�� �ѱ�(�Խñ� �� ����)
		model.addAttribute("pageBlock", pageBlock);
///////////////////////////////////////////////////////////////////////////////


//----------------------------------������ ó�� ���� ���� �����--------------------------------
		//�� 10���� ����ҋ� ó�� ù���� �۹�ȣ, ������ �۹�ȣ ó��  ù��° ��������ȣ, ������ ������ ��ȣ �ʿ�
		int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
		if(currentPage%pageBlock==0) {
			pageNumStart -= pageBlock;
		} //�� ����� ���� �����ϳ�.. ����� ���� �ٽ���..
		int pageNumEnd = pageNumStart + pageBlock - 1;
		if(pageNumEnd > pageTotalCnt) { //������� pageBlock=3�̰� ���� ������ �������� [7][8]�� 
			pageNumEnd = pageTotalCnt;  //������ [9]�� ���� ��쵵 ��������� �Ѵ�. ���� �ƾ� �Խñ��� ��������
		}
		System.out.println("pageNumStart : " + pageNumStart);
		System.out.println("pageNumEnd : " + pageNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ù��° ������ ����)
		model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ������ ������ ����)
////////////////////////////////////////////////////////////////////////////////
		
		
		
		//���� �Խñ��� SELECT�Ͽ� �ҷ����� 
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
		

		//�Խñ� �Ѱ����� �ҷ����� DB�� �����ߵǳ�
		int totalCnt = BookBoardDAOImpl.getInstance().selectAllStockCnt();
		System.out.println("totalCnt : " + totalCnt);
		req.setAttribute("totalCnt", totalCnt);
		int seenNumStart = 1;
		//aticleNum�̶� 
		
		if(totalCnt > 0) { //pageNum�� �ٲ�� seenNum�� �ٲ�µ� ���� �׳� 1~totalCnt���� �迭���̸� �����Ѵ�
			ArrayList<BookBoardVO> bigVo = BookBoardDAOImpl.getInstance().selectAllStockInArrayList(seenNumStart, totalCnt);
			req.setAttribute("bigVo", bigVo); 
		}
		
	}
	*/
	
	@Override
	public void showListMainDetailPro(HttpServletRequest req, Model model) {
		
		System.out.println("showListMainDetailPro ����");
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("book_seq : " + book_seq);

		//�� å ���� �������� DAO ȣ��
		BookBoardVO vo = dao.getStockDetail(book_seq);
		model.addAttribute("vo", vo);
		
	}
	
	@Override
	public void showListDetailPro(HttpServletRequest req, Model model) {
		
		//�ʿ��� ����??
		System.out.println("showListDetailPro()�۾� ����...");
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("book_seq : " + book_seq);
		int articleNum = Integer.parseInt(req.getParameter("articleNum"));
		System.out.println("articleNum : " + articleNum);
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		System.out.println("pageNum : " + pageNum);
		//�� å ���� �������� DAO ȣ��
		BookBoardVO vo = dao.getStockDetail(book_seq);
		model.addAttribute("vo", vo);
		model.addAttribute("articleNum", articleNum);
		model.addAttribute("pageNum", pageNum);
	}

	
	@Override
	public void listDeletePro(HttpServletRequest req, Model model) {
		
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("book_seq �� ���� : " + book_seq);

/////////////////////////////�ϳ��� �����ؼ� �����Ҷ�////////////////////////////
		
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
			//������ �Ѱ� ���� ����.. MR.XX
			
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

			
			//vo�ٱ��ϸ� putStock���� �ѱ��
			int insertCnt = dao.insertStock(vo);
			model.addAttribute("insertCnt", insertCnt);
			System.out.println("insertCnt ����߰����� : " + insertCnt);
			
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
			//������ �Ѱ� ���� ����.. MR.XX
			
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
			
			
			//vo�ٱ��ϸ� putStock���� �ѱ��
			int updateCnt = dao.updateStock(vo);
			model.addAttribute("updateCnt", updateCnt);
			System.out.println("updateCnt ���������� : " + updateCnt);
			
			
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
		
		//������ ���� �Ѱܹ��� ����
		
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
		
		//���� ���ø���Ʈ ���̺��� ��������ִ� ���´�
		//���̺��� ������ �÷� ���� select�� �ϳ��ϳ� ������(rs.next) vo�� �������� ���������� bigVo�� ��Ƽ� 
		//myWishList.jsp�� �ѱ��(jstl�̿�forEach)
		
		int pageSize = 10; //�������� �� ����
		int pageBlock = 3; //ȭ��� �ǹؿ� �ѷ��� ������ ���� 
		
		String sessionId = (String) req.getSession().getAttribute("sessionId");
		//�Խñ� �Ѱ����� �ҷ����� DB�� �����ߵǳ�
		int totalCnt = dao.selectAllWishListCnt(sessionId);
		System.out.println("totalCnt : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		//*****************�����ϰ� �� �۾����������� �ѱ� ��������(�̳��� �߿�)*********************
		String pageNum = req.getParameter("pageNum"); //��  ������ ��ȣ�� string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if���ǹ��ȿ����� �������´� null�� �� ���Ѵ� �׷��� �������� integer�ٲ峪����
//////////////////////////////////////////////////////////////////////////////
		req.setAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
		int currentPage = Integer.parseInt(pageNum); //���� ������ ��
		System.out.println("currentPage : " + currentPage);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp�� �ѱ� (���� �ӹ��� �ִ� ������)
////////////////////////////////////////////////////////////////////////////////
		//��µǴ� �۹�ȣ (���̰� ��� �߿�)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		model.addAttribute("articleNum", articleNum);
		//�� ������ ����
		int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
		System.out.println("pageTotalCnt : " + pageTotalCnt);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp�� �ѱ� (�� ������ ����)
////////////////////////////////////////////////////////////////////////////////
		
		//����� seenNum�� ���� �÷��̱� ������ ������ �Ѿ sql ���� �߰� �׷��� �ʴ´� 
		//DB���� rownum�̴�. �Խñ� �ҷ��ö� DESC�� �ҷ����� rownum�� ASC�� �ٿ����Ƿ�
		//������ �Խñ��� rownum = 1 �� �� �Խñ��� rownum=2�� �ȴ�.
		int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
		int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
		System.out.println("seenNumStart : " + seenNumStart);
		System.out.println("seenNumEnd : " + seenNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ������)
		model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ��������)
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////		
		model.addAttribute("totalCnt", totalCnt); //boardList.jsp�� �ѱ�(�Խñ� �� ����)
		model.addAttribute("pageBlock", pageBlock);
///////////////////////////////////////////////////////////////////////////////


//----------------------------------������ ó�� ���� ���� �����--------------------------------
		//�� 10���� ����ҋ� ó�� ù���� �۹�ȣ, ������ �۹�ȣ ó��  ù��° ��������ȣ, ������ ������ ��ȣ �ʿ�
		int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
		if(currentPage%pageBlock==0) {
			pageNumStart -= pageBlock;
		} //�� ����� ���� �����ϳ�.. ����� ���� �ٽ���..
		int pageNumEnd = pageNumStart + pageBlock - 1;
		if(pageNumEnd > pageTotalCnt) { //������� pageBlock=3�̰� ���� ������ �������� [7][8]�� 
			pageNumEnd = pageTotalCnt;  //������ [9]�� ���� ��쵵 ��������� �Ѵ�. ���� �ƾ� �Խñ��� ��������
		}
		System.out.println("pageNumStart : " + pageNumStart);
		System.out.println("pageNumEnd : " + pageNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ù��° ������ ����)
		model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ������ ������ ����)
////////////////////////////////////////////////////////////////////////////////
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seenNumStart", seenNumStart);
		map.put("seenNumEnd", seenNumEnd);
		map.put("sessionId", sessionId);
		
		//���� �Խñ��� SELECT�Ͽ� �ҷ����� 
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

		//���̺��� ������ �÷� ���� select�� �ϳ��ϳ� ������(rs.next) vo�� �������� ���������� bigVo�� ��Ƽ� 
		//myOrderList.jsp�� �ѱ��(jstl�̿�forEach)
		
		int pageSize = 10; //�������� �� ����
		int pageBlock = 3; //ȭ��� �ǹؿ� �ѷ��� ������ ���� 
		String sessionId = (String) req.getSession().getAttribute("sessionId");
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("sessionId", sessionId);
		
		
		//�Խñ� �Ѱ����� �ҷ����� DB�� �����ߵǳ�
		System.out.println("selectAllOrderListcnt() ���� �غ�...");
		int totalCnt = dao.selectAllOrderListCnt(map1);
		System.out.println("(���Ÿ�� �Ѱ���)totalCnt : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		//*****************�����ϰ� �� �۾����������� �ѱ� ��������(�̳��� �߿�)*********************
		String pageNum = req.getParameter("pageNum"); //��  ������ ��ȣ�� string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if���ǹ��ȿ����� �������´� null�� �� ���Ѵ� �׷��� �������� integer�ٲ峪����
//////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
		int currentPage = Integer.parseInt(pageNum); //���� ������ ��
		System.out.println("currentPage : " + currentPage);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp�� �ѱ� (���� �ӹ��� �ִ� ������)
////////////////////////////////////////////////////////////////////////////////
		//��µǴ� �۹�ȣ (���̰� ��� �߿�)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		model.addAttribute("articleNum", articleNum);
		//�� ������ ����
		int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
		System.out.println("pageTotalCnt : " + pageTotalCnt);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp�� �ѱ� (�� ������ ����)
////////////////////////////////////////////////////////////////////////////////
		
		//����� seenNum�� ���� �÷��̱� ������ ������ �Ѿ sql ���� �߰� �׷��� �ʴ´� 
		//DB���� rownum�̴�. �Խñ� �ҷ��ö� DESC�� �ҷ����� rownum�� ASC�� �ٿ����Ƿ�
		//������ �Խñ��� rownum = 1 �� �� �Խñ��� rownum=2�� �ȴ�.
		int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
		int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
		System.out.println("seenNumStart : " + seenNumStart);
		System.out.println("seenNumEnd : " + seenNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ������)
		model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ��������)
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////		
		model.addAttribute("totalCnt", totalCnt); //boardList.jsp�� �ѱ�(�Խñ� �� ����)
		model.addAttribute("pageBlock", pageBlock);
///////////////////////////////////////////////////////////////////////////////


//----------------------------------������ ó�� ���� ���� �����--------------------------------
		//�� 10���� ����ҋ� ó�� ù���� �۹�ȣ, ������ �۹�ȣ ó��  ù��° ��������ȣ, ������ ������ ��ȣ �ʿ�
		int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
		if(currentPage%pageBlock==0) {
			pageNumStart -= pageBlock;
		} //�� ����� ���� �����ϳ�.. ����� ���� �ٽ���..
		int pageNumEnd = pageNumStart + pageBlock - 1;
		if(pageNumEnd > pageTotalCnt) { //������� pageBlock=3�̰� ���� ������ �������� [7][8]�� 
			pageNumEnd = pageTotalCnt;  //������ [9]�� ���� ��쵵 ��������� �Ѵ�. ���� �ƾ� �Խñ��� ��������
		}
		System.out.println("pageNumStart : " + pageNumStart);
		System.out.println("pageNumEnd : " + pageNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ù��° ������ ����)
		model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ������ ������ ����)
////////////////////////////////////////////////////////////////////////////////
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seenNumStart", seenNumStart);
		map.put("seenNumEnd", seenNumEnd);
		map.put("sessionId", sessionId);
		
		//���� �Խñ��� SELECT�Ͽ� �ҷ����� 
		if(totalCnt > 0) { 
			ArrayList<OrderListVO> bigVo = dao.selectAllOrderList(map);
			model.addAttribute("bigVo", bigVo);
			TotalPriceVO vo1 = dao.selectOrderAmount(map1);
			model.addAttribute("vo1", vo1);
		}
	
	}
	
	
	@Override
	public void orderListPro(HttpServletRequest req, Model model) {
		
		//myWishList.jsp�� �޴��κ��� �Ѿ�� book_seq, id(�̰� ����), quantity�� �޴´�
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
		
		//���ø���Ʈ �����ϴ� ����
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
		
		//bigVo �ѷ��� �����ָ� �� 
		int pageSize = 10; //�������� �� ����
		int pageBlock = 3; //ȭ��� �ǹؿ� �ѷ��� ������ ���� 
		String sessionId = (String) req.getSession().getAttribute("sessionId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		
		//�Խñ� �Ѱ����� �ҷ����� DB�� �����ߵǳ�
		int totalCnt = dao.selectAllRefundListCnt(map);
		System.out.println("(ȯ�Ҹ�� �Ѱ���)totalCnt : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		//*****************�����ϰ� �� �۾����������� �ѱ� ��������(�̳��� �߿�)*********************
		String pageNum = req.getParameter("pageNum"); //��  ������ ��ȣ�� string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if���ǹ��ȿ����� �������´� null�� �� ���Ѵ� �׷��� �������� integer�ٲ峪����
//////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
		int currentPage = Integer.parseInt(pageNum); //���� ������ ��
		System.out.println("currentPage : " + currentPage);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp�� �ѱ� (���� �ӹ��� �ִ� ������)
////////////////////////////////////////////////////////////////////////////////
		//��µǴ� �۹�ȣ (���̰� ��� �߿�)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		model.addAttribute("articleNum", articleNum);
		//�� ������ ����
		int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
		System.out.println("pageTotalCnt : " + pageTotalCnt);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp�� �ѱ� (�� ������ ����)
////////////////////////////////////////////////////////////////////////////////
		
		//����� seenNum�� ���� �÷��̱� ������ ������ �Ѿ sql ���� �߰� �׷��� �ʴ´� 
		//DB���� rownum�̴�. �Խñ� �ҷ��ö� DESC�� �ҷ����� rownum�� ASC�� �ٿ����Ƿ�
		//������ �Խñ��� rownum = 1 �� �� �Խñ��� rownum=2�� �ȴ�.
		int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
		int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
		System.out.println("seenNumStart : " + seenNumStart);
		System.out.println("seenNumEnd : " + seenNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ������)
		model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ��������)
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////		
		model.addAttribute("totalCnt", totalCnt); //boardList.jsp�� �ѱ�(�Խñ� �� ����)
		model.addAttribute("pageBlock", pageBlock);
///////////////////////////////////////////////////////////////////////////////


//----------------------------------������ ó�� ���� ���� �����--------------------------------
		//�� 10���� ����ҋ� ó�� ù���� �۹�ȣ, ������ �۹�ȣ ó��  ù��° ��������ȣ, ������ ������ ��ȣ �ʿ�
		int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
		if(currentPage%pageBlock==0) {
			pageNumStart -= pageBlock;
		} //�� ����� ���� �����ϳ�.. ����� ���� �ٽ���..
		int pageNumEnd = pageNumStart + pageBlock - 1;
		if(pageNumEnd > pageTotalCnt) { //������� pageBlock=3�̰� ���� ������ �������� [7][8]�� 
			pageNumEnd = pageTotalCnt;  //������ [9]�� ���� ��쵵 ��������� �Ѵ�. ���� �ƾ� �Խñ��� ��������
		}
		System.out.println("pageNumStart : " + pageNumStart);
		System.out.println("pageNumEnd : " + pageNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ù��° ������ ����)
		model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ������ ������ ����)
////////////////////////////////////////////////////////////////////////////////
		
		map.put("seenNumStart", seenNumStart);
		map.put("seenNumEnd", seenNumEnd);
		
		
		//���� �Խñ��� SELECT�Ͽ� �ҷ����� 
		if(totalCnt > 0) { 
			ArrayList<RefundListVO> bigVo = dao.selectAllRefundList(map);
			model.addAttribute("bigVo", bigVo); 
		}
	
	}
	
	
	@Override
	public void refundPro(HttpServletRequest req, Model model) {
		//���Ÿ�Ͽ��� ȯ�ҿ�û�� ���ÿ� ȯ�Ҹ�Ͽ� insert �� ���Ÿ�Ͽ��� delete
		int buy_seq = Integer.parseInt(req.getParameter("buy_seq"));
		System.out.println("buy_seq : " + buy_seq);
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("book_seq : " + book_seq);
		

		OrderListVO vo = dao.selectFinalOrderList(buy_seq);

		
		int insertCnt = dao.insertRefundList(vo);
		System.out.println("insertCnt(ȯ�Ҹ���߰�) " + insertCnt);
		model.addAttribute("insertCnt", insertCnt);
		
		//2. ȯ�ҿ�û�� ������ ���� ���� ������ ���� form���� ������ �ؾ��Ѵ�...finalAdminOrderList.jsp
		dao.deleteFinalOrderList(buy_seq);
			
	}
	
	@Override
	public void approveOrderPro(HttpServletRequest req, Model model) {
		
		int buy_seq = Integer.parseInt(req.getParameter("buy_seq"));
		int book_seq = Integer.parseInt(req.getParameter("book_seq"));
		System.out.println("approveOrderPro() ����...");
		System.out.println(buy_seq);
		System.out.println(book_seq);

					
		int AfterOrderStock = dao.orderStock(buy_seq);
		System.out.println("�ֹ����� : " + AfterOrderStock);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("AfterOrderStock", AfterOrderStock);
		map.put("book_seq", book_seq);
		
		
		
		

		//���� ������ �ϱ� ���� ��� ����
		if(AfterOrderStock >= 0) {
			
			int updateCnt = dao.updateStockColumn(map);
			System.out.println("updateCnt(å ��� ��������) : " + updateCnt);
			model.addAttribute("updateCnt", updateCnt);
			
			//buylist���� ���ڵ�1�� select �Ѵ�
			OrderListVO vo = dao.selectOrderList(buy_seq);
			//"����"Ŭ���� buylist������ finalbuylist�� insert �Ѵ�.(finalbuylist ���̺� �����Ϸ�)
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
		
		//bigVo �ѷ��� �����ָ� �� 
				int pageSize = 10; //�������� �� ����
				int pageBlock = 3; //ȭ��� �ǹؿ� �ѷ��� ������ ���� 
				
				String sessionId = (String) req.getSession().getAttribute("sessionId");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sessionId", sessionId);
				int totalCnt = dao.selectAllFinalOrderListCnt(map);
				System.out.println("totalCnt : " + totalCnt);
				System.out.println("pageSize : " + pageSize);
				System.out.println("pageBlock : " + pageBlock);
				
				
				//*****************�����ϰ� �� �۾����������� �ѱ� ��������(�̳��� �߿�)*********************
				String pageNum = req.getParameter("pageNum"); //��  ������ ��ȣ�� string ?
				
					if(pageNum==null) {
						pageNum = "1";
					} //if���ǹ��ȿ����� �������´� null�� �� ���Ѵ� �׷��� �������� integer�ٲ峪����
		//////////////////////////////////////////////////////////////////////////////
					model.addAttribute("pageNum", pageNum);
		////////////////////////////////////////////////////////////////////////////
				int currentPage = Integer.parseInt(pageNum); //���� ������ ��
				System.out.println("currentPage : " + currentPage);
		////////////////////////////////////////////////////////////////////////////////
				model.addAttribute("currntePage", currentPage); //boardList.jsp�� �ѱ� (���� �ӹ��� �ִ� ������)
		////////////////////////////////////////////////////////////////////////////////
				//��µǴ� �۹�ȣ (���̰� ��� �߿�)
				int articleNum = totalCnt - (currentPage-1)*pageSize;
				model.addAttribute("articleNum", articleNum);
				//�� ������ ����
				int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
				System.out.println("pageTotalCnt : " + pageTotalCnt);
		////////////////////////////////////////////////////////////////////////////////
				model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp�� �ѱ� (�� ������ ����)
		////////////////////////////////////////////////////////////////////////////////
				
				//����� seenNum�� ���� �÷��̱� ������ ������ �Ѿ sql ���� �߰� �׷��� �ʴ´� 
				//DB���� rownum�̴�. �Խñ� �ҷ��ö� DESC�� �ҷ����� rownum�� ASC�� �ٿ����Ƿ�
				//������ �Խñ��� rownum = 1 �� �� �Խñ��� rownum=2�� �ȴ�.
				int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
				int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
				System.out.println("seenNumStart : " + seenNumStart);
				System.out.println("seenNumEnd : " + seenNumEnd);
		////////////////////////////////////////////////////////////////////////////////
				model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ������)
				model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ��������)
		////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////		
				model.addAttribute("totalCnt", totalCnt); //boardList.jsp�� �ѱ�(�Խñ� �� ����)
				model.addAttribute("pageBlock", pageBlock);
		///////////////////////////////////////////////////////////////////////////////


		//----------------------------------������ ó�� ���� ���� �����--------------------------------
				//�� 10���� ����ҋ� ó�� ù���� �۹�ȣ, ������ �۹�ȣ ó��  ù��° ��������ȣ, ������ ������ ��ȣ �ʿ�
				int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
				if(currentPage%pageBlock==0) {
					pageNumStart -= pageBlock;
				} //�� ����� ���� �����ϳ�.. ����� ���� �ٽ���..
				int pageNumEnd = pageNumStart + pageBlock - 1;
				if(pageNumEnd > pageTotalCnt) { //������� pageBlock=3�̰� ���� ������ �������� [7][8]�� 
					pageNumEnd = pageTotalCnt;  //������ [9]�� ���� ��쵵 ��������� �Ѵ�. ���� �ƾ� �Խñ��� ��������
				}
				System.out.println("pageNumStart : " + pageNumStart);
				System.out.println("pageNumEnd : " + pageNumEnd);
		////////////////////////////////////////////////////////////////////////////////
				model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ù��° ������ ����)
				model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ������ ������ ����)
		////////////////////////////////////////////////////////////////////////////////
				map.put("seenNumStart", seenNumStart);
				map.put("seenNumEnd", seenNumEnd);

				
				//���� �Խñ��� SELECT�Ͽ� �ҷ����� 
				if(totalCnt > 0) { 
					ArrayList<OrderListVO> bigVo = dao.selectAllFinalOrderList(map);
					model.addAttribute("bigVo", bigVo);
					TotalPriceVO vo1 = dao.selectFinalAdminOrderAmount(map);
					model.addAttribute("vo1", vo1);
					
					
				}
							
				
	}				
	
	
	
	@Override
	public void showFinalRefundListPro(HttpServletRequest req, Model model) {
		
		//bigVo �ѷ��� �����ָ� �� 
				int pageSize = 10; //�������� �� ����
				int pageBlock = 3; //ȭ��� �ǹؿ� �ѷ��� ������ ���� 
				
				String sessionId = (String) req.getSession().getAttribute("sessionId");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sessionId", sessionId);
				
				int totalCnt = dao.selectAllFinalRefundListCnt(map);
				System.out.println("totalCnt (����ȯ�Ҹ�� ����): " + totalCnt);
				System.out.println("pageSize : " + pageSize);
				System.out.println("pageBlock : " + pageBlock);
				
				
				//*****************�����ϰ� �� �۾����������� �ѱ� ��������(�̳��� �߿�)*********************
				String pageNum = req.getParameter("pageNum"); //��  ������ ��ȣ�� string ?
				
					if(pageNum==null) {
						pageNum = "1";
					} //if���ǹ��ȿ����� �������´� null�� �� ���Ѵ� �׷��� �������� integer�ٲ峪����
		//////////////////////////////////////////////////////////////////////////////
					model.addAttribute("pageNum", pageNum);
		////////////////////////////////////////////////////////////////////////////
				int currentPage = Integer.parseInt(pageNum); //���� ������ ��
				System.out.println("currentPage : " + currentPage);
		////////////////////////////////////////////////////////////////////////////////
				model.addAttribute("currntePage", currentPage); //boardList.jsp�� �ѱ� (���� �ӹ��� �ִ� ������)
		////////////////////////////////////////////////////////////////////////////////
				//��µǴ� �۹�ȣ (���̰� ��� �߿�)
				int articleNum = totalCnt - (currentPage-1)*pageSize;
				model.addAttribute("articleNum", articleNum);
				//�� ������ ����
				int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
				System.out.println("pageTotalCnt : " + pageTotalCnt);
		////////////////////////////////////////////////////////////////////////////////
				model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp�� �ѱ� (�� ������ ����)
		////////////////////////////////////////////////////////////////////////////////
				
				//����� seenNum�� ���� �÷��̱� ������ ������ �Ѿ sql ���� �߰� �׷��� �ʴ´� 
				//DB���� rownum�̴�. �Խñ� �ҷ��ö� DESC�� �ҷ����� rownum�� ASC�� �ٿ����Ƿ�
				//������ �Խñ��� rownum = 1 �� �� �Խñ��� rownum=2�� �ȴ�.
				int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
				int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
				System.out.println("seenNumStart : " + seenNumStart);
				System.out.println("seenNumEnd : " + seenNumEnd);
		////////////////////////////////////////////////////////////////////////////////
				model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ������)
				model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ��������)
		////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////		
				model.addAttribute("totalCnt", totalCnt); //boardList.jsp�� �ѱ�(�Խñ� �� ����)
				model.addAttribute("pageBlock", pageBlock);
		///////////////////////////////////////////////////////////////////////////////


		//----------------------------------������ ó�� ���� ���� �����--------------------------------
				//�� 10���� ����ҋ� ó�� ù���� �۹�ȣ, ������ �۹�ȣ ó��  ù��° ��������ȣ, ������ ������ ��ȣ �ʿ�
				int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
				if(currentPage%pageBlock==0) {
					pageNumStart -= pageBlock;
				} //�� ����� ���� �����ϳ�.. ����� ���� �ٽ���..
				int pageNumEnd = pageNumStart + pageBlock - 1;
				if(pageNumEnd > pageTotalCnt) { //������� pageBlock=3�̰� ���� ������ �������� [7][8]�� 
					pageNumEnd = pageTotalCnt;  //������ [9]�� ���� ��쵵 ��������� �Ѵ�. ���� �ƾ� �Խñ��� ��������
				}
				System.out.println("pageNumStart : " + pageNumStart);
				System.out.println("pageNumEnd : " + pageNumEnd);
		////////////////////////////////////////////////////////////////////////////////
				model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ù��° ������ ����)
				model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ������ ������ ����)
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
		
		
		
		System.out.println("updateStockColumnPlus() ���� �غ�..");
		
		//book, refund�����Ѱ� ��� + ȯ�����  int�̰� 
		//�� ������ update�ϴ� �ɷ� 2���� ����
		
		int afterRefundStock = dao.refundStock(refund_seq);
		System.out.println("ȯ�Ұ��� : " + afterRefundStock);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("afterRefundStock", afterRefundStock);
		map.put("book_seq", book_seq);
		
		
		//ȯ���� �ȵǴ� ����? å�� �������� �������� ȯ���� �ȵǴµ� �װ� ���� ���Ǵ���?
		//�׳� �մ��� ��å�� �ִ°��� ���������� ���� ������� cascade�̷��� �޾ƾߵ�
		
		if(afterRefundStock >= 0) {
			
			int updateCnt = dao.updateStockColumnPlus(map);  //�̰� �ȵǳ�...
			model.addAttribute("updateCnt", updateCnt);
			System.out.println("updateCnt(å ��� ��������) : " + updateCnt);
			
			//�ϳ��� refund�� finalrefund�� ���� �غ�
			RefundListVO vo = dao.selectOneRefundList(refund_seq);
			int insertCnt = dao.insertFinalRefundList(vo);
			model.addAttribute("insertCnt", insertCnt); //finalrefund�� �ֱ� ����
			
			AccountListVO vo1 = dao.selectOneFinalRefundList(refund_seq);
			int insertAccountCnt = dao.insertClosingAccountRefundList(vo1);
			model.addAttribute("insertAccountCnt", insertAccountCnt);
			
			
			int deleteCnt = dao.deleteRefundList(refund_seq);
			System.out.println("deleteCnt(ȯ�ҽ��β� ���̺��� �����) : " + deleteCnt);
			model.addAttribute("deleteCnt", deleteCnt);
			//����ȯ�ҽ��θ�� ������
			
		} else {
			model.addAttribute("updateCnt", 0);
		}
			
		
		
	}

	@Override
	public void showClosingAccountListPro(HttpServletRequest req, Model model) {
		
		//bigVo �ѷ��� �����ָ� �� 
		int pageSize = 10; //�������� �� ����
		int pageBlock = 3; //ȭ��� �ǹؿ� �ѷ��� ������ ���� 

		//�Խñ� �Ѱ����� �ҷ����� DB�� �����ߵǳ�
		int totalCnt = dao.selectAllClosingAccountListCnt();
		System.out.println("totalCnt (������ ����) : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);
		
		
		
		String pageNum = req.getParameter("pageNum"); //��  ������ ��ȣ�� string ?
		
			if(pageNum==null) {
				pageNum = "1";
			} //if���ǹ��ȿ����� �������´� null�� �� ���Ѵ� �׷��� �������� integer�ٲ峪����
//////////////////////////////////////////////////////////////////////////////
			model.addAttribute("pageNum", pageNum);
////////////////////////////////////////////////////////////////////////////
		int currentPage = Integer.parseInt(pageNum); //���� ������ ��
		System.out.println("currentPage : " + currentPage);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp�� �ѱ� (���� �ӹ��� �ִ� ������)
////////////////////////////////////////////////////////////////////////////////
		//��µǴ� �۹�ȣ (���̰� ��� �߿�)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		model.addAttribute("articleNum", articleNum);
		//�� ������ ����
		int pageTotalCnt = (totalCnt/pageSize) +((totalCnt%pageSize != 0) ? 1:0);
		System.out.println("pageTotalCnt : " + pageTotalCnt);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageTotalCnt", pageTotalCnt); //boardList.jsp�� �ѱ� (�� ������ ����)
////////////////////////////////////////////////////////////////////////////////
		
		//����� seenNum�� ���� �÷��̱� ������ ������ �Ѿ sql ���� �߰� �׷��� �ʴ´� 
		//DB���� rownum�̴�. �Խñ� �ҷ��ö� DESC�� �ҷ����� rownum�� ASC�� �ٿ����Ƿ�
		//������ �Խñ��� rownum = 1 �� �� �Խñ��� rownum=2�� �ȴ�.
		int seenNumStart = (currentPage - 1)*pageSize + 1; //seenNum 
		int seenNumEnd = seenNumStart + pageSize - 1; //seenNum
		System.out.println("seenNumStart : " + seenNumStart);
		System.out.println("seenNumEnd : " + seenNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("seenNumStart", seenNumStart); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ������)
		model.addAttribute("seenNumEnd", seenNumEnd); //boardList.jsp�� �ѱ� (����Ϸ��� seenNum ��������)
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////		
		model.addAttribute("totalCnt", totalCnt); //boardList.jsp�� �ѱ�(�Խñ� �� ����)
		model.addAttribute("pageBlock", pageBlock);
///////////////////////////////////////////////////////////////////////////////


//----------------------------------������ ó�� ���� ���� �����--------------------------------
		//�� 10���� ����ҋ� ó�� ù���� �۹�ȣ, ������ �۹�ȣ ó��  ù��° ��������ȣ, ������ ������ ��ȣ �ʿ�
		int pageNumStart = (currentPage/pageBlock) * pageBlock + 1;
		if(currentPage%pageBlock==0) {
			pageNumStart -= pageBlock;
		} //�� ����� ���� �����ϳ�.. ����� ���� �ٽ���..
		int pageNumEnd = pageNumStart + pageBlock - 1;
		if(pageNumEnd > pageTotalCnt) { //������� pageBlock=3�̰� ���� ������ �������� [7][8]�� 
			pageNumEnd = pageTotalCnt;  //������ [9]�� ���� ��쵵 ��������� �Ѵ�. ���� �ƾ� �Խñ��� ��������
		}
		System.out.println("pageNumStart : " + pageNumStart);
		System.out.println("pageNumEnd : " + pageNumEnd);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("pageNumStart", pageNumStart); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ù��° ������ ����)
		model.addAttribute("pageNumEnd", pageNumEnd); //boardList.jsp�� �ѱ� (�Խ��� �ǹؿ� ���̴� ������ ��Ʈ��â(?)�� ������ ������ ����)
////////////////////////////////////////////////////////////////////////////////
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seenNumStart", seenNumStart);
		map.put("seenNumEnd", seenNumEnd);
		//���� �Խñ��� SELECT�Ͽ� �ҷ����� 
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
