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
		
		int pageSize = 20; //�������� �� ����
		int pageBlock = 3; //ȭ��� �ǹؿ� �ѷ��� ������ ���� 
		String sessionId = (String) req.getSession().getAttribute("sessionId");

		
		
		//�Խñ� �Ѱ����� �ҷ����� DB�� �����ߵǳ�
		int totalCnt = dao.getAllPostCnt();
		System.out.println("totalCnt(�Խñ� �Ѱ���) : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);

		
		//*****************�����ϰ� �� �۾����������� �ѱ� ��������(�̳��� �߿�)*********************
		String pageNum = req.getParameter("pageNum"); //��  ������ ��ȣ�� string ?
			if(pageNum==null) {
				pageNum = "1";
			} //if���ǹ��ȿ����� �������´� null�� �� ���Ѵ� �׷��� �������� integer�ٲ峪����
		model.addAttribute("pageNum", pageNum);
			
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
		
		//��� ��쿡 �ʿ�����.. �̰� ������ ���� �ƴѰ�..?
		/*if(pageArticleEnd > totalCnt) {  
			pageArticleEnd = totalCnt;
		}*/
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
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("seenNumStart", seenNumStart);
		map.put("seenNumEnd", seenNumEnd);
		
		
		//���� �Խñ��� SELECT�Ͽ� �ҷ����� 
		if(totalCnt > 0) { //�Խñ��� �����ؾ� �Ʒ� ���ǹ� ����
			ArrayList<FreeBoardVO> bigVo = dao.getALLPostList(map);
			model.addAttribute("bigVo", bigVo); //�� 70���ڵ带 ���� bigVo
		}
		
	}

	@Override
	public void showPostDetailPro(HttpServletRequest req, Model model) {
		
		int board_seq = Integer.parseInt(req.getParameter("board_seq"));
		System.out.println("board_seq : " + board_seq);
		int articleNum = Integer.parseInt(req.getParameter("articleNum"));
		System.out.println("�Խñ� ��ȣ  : " + articleNum);
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		System.out.println("��������ȣ  : " + pageNum);
		
		//�������� �������� readCnt+1 �� �� �װ� freeboard�� readCntĮ���� ����.
		//�׷��� �� ����� �÷��� �Ʒ� selectOnePost���� �̾ƾ��Ե�
		int updateReadCnt = dao.plusReadCnt(board_seq);
		
		FreeBoardVO vo = dao.selectOnePost(board_seq);
		model.addAttribute("vo", vo);
		model.addAttribute("articleNum", articleNum);
		model.addAttribute("pageNum", pageNum);
		//req.setAttribute("readCnt", readCnt);
	}


	
	@Override //Ư�� id�� �� �Խñ� �˻� ����
	public void showMyPostPro(HttpServletRequest req, Model model) {
		
		int pageSize = 20; //�������� �� ����
		int pageBlock = 3; //ȭ��� �ǹؿ� �ѷ��� ������ ����
		//�˻��������� �Է��� ���̵� �޾ƿ�
		String searchId = (String)req.getParameter("searchId");
		System.out.println("searchId : " + searchId);
		//Ư�� id�� �� �Խñ� �� ���� ����
		int totalCnt = dao.getAllPostCntById(searchId);
		System.out.println("totalCnt(���������� �Ѱ���) : " + totalCnt);
		System.out.println("pageSize : " + pageSize);
		System.out.println("pageBlock : " + pageBlock);

		
		//*****************�����ϰ� �� �۾����������� �ѱ� ��������(�̳��� �߿�)*********************
		String pageNum = req.getParameter("pageNum"); //��  ������ ��ȣ�� string ?
			if(pageNum==null) {
				pageNum = "1";
			} //if���ǹ��ȿ����� �������´� null�� �� ���Ѵ� �׷��� �������� integer�ٲ峪����
			model.addAttribute("pageNum", pageNum);
			
		int currentPage = Integer.parseInt(pageNum); //���� ������ ��
		System.out.println("currentPage : " + currentPage);
////////////////////////////////////////////////////////////////////////////////
		model.addAttribute("currntePage", currentPage); //boardList.jsp�� �ѱ� (���� �ӹ��� �ִ� ������)
////////////////////////////////////////////////////////////////////////////////
		//��µǴ� �۹�ȣ (���̰� ��� �߿�)
		int articleNum = totalCnt - (currentPage-1)*pageSize;
		req.setAttribute("articleNum", articleNum);
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
		map.put("searchId", searchId);

		if(totalCnt > 0) { //�Խñ��� �����ؾ� �Ʒ� ���ǹ� ����
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
		System.out.println("updateCnt(�Խñ� ���� ����) : " + updateCnt);
		model.addAttribute("updateCnt", updateCnt);
	}
	
	@Override
	public void deleteMyPostPro(HttpServletRequest req, Model model) {
		
		//where�� ���Ƕ����� board_seq �޾߾���
		int board_seq = Integer.parseInt(req.getParameter("board_seq"));
		
		int deleteCnt = dao.deleteOnePost(board_seq);
		model.addAttribute("deleteCnt", deleteCnt);
	}
	

	@Override
	public void writeNewPostPro(HttpServletRequest req, Model model) {
		

		//�۾��� ������ �޾ƿ� ������ vo�� ����
		FreeBoardVO vo = new FreeBoardVO();
		vo.setBoard_seq(Integer.parseInt(req.getParameter("board_seq")));
		System.out.println("�۹�ȣ : " + Integer.parseInt(req.getParameter("board_seq")));
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
