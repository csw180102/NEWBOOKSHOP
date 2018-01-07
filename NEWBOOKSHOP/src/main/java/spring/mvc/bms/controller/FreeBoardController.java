package spring.mvc.bms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.bms.board.service.FreeBoardService;

@Controller
public class FreeBoardController {

	@Autowired
	private FreeBoardService Fservice;
	
	@RequestMapping("freeboard")
	public String freeboard(HttpServletRequest req, Model model) {
		
		Fservice.showFreeboardListPro(req, model);
		return "board/management/freeboard/jspForm/freeboard";
	}
	
	@RequestMapping("viewpostdetail")
	public String viewpostdetail(HttpServletRequest req, Model model) {
		
		Fservice.showPostDetailPro(req, model);
		return "board/management/freeboard/jspForm/viewPostForm";
	}

	@RequestMapping("searchpostbyid")
	public String searchpostbyid(HttpServletRequest req, Model model) {
		
		Fservice.showMyPostPro(req, model);
		return "board/management/freeboard/jspForm/freeboard";
	}
	
	@RequestMapping("updatepostform")
	public String updatepostform(HttpServletRequest req, Model model) {
		
		Fservice.updateMyPostPro(req, model);
		return "board/management/freeboard/jspForm/updatePostForm";
	}
	
	@RequestMapping("updatepostPro")
	public String updatepostPro(HttpServletRequest req, Model model) {
		
		Fservice.updateMyPostProTwo(req, model);
		return "board/management/freeboard/pro/updatePostPro";
	}
	
	@RequestMapping("deletepostPro")
	public String deletepostPro(HttpServletRequest req, Model model) {
		
		Fservice.deleteMyPostPro(req, model);
		return "board/management/freeboard/pro/deletePostPro";
	}
	
	
//////////////////////////////////////////////////////�亯���ε� �̰� �� ���߿�����
	@RequestMapping("writepostform")
	public String writepostform(HttpServletRequest req, Model model) {
		
		// ������ ���
		int board_seq = 0; // �亯���� �ƴ����� ���� �޾Ƴ��°�
		int ref = 1; // ���� �亯��
		int ref_step = 0; // ���� �亯���� ���� �亯
		int ref_level = 0; // ���� �亯���� ���ʴ亯�� ���ʴ亯
		// ���� 3, 2, 3, 4 �̶�� 3��° �Խñ��� 2���� �亯�� �޸� 3��° �亯�� �޸� 4���� �亯 �̷��ǹ�..
		
		// �亯���� ���
		if (req.getParameter("board_seq") != null) { // req.getParameter("num")!=null
			
			board_seq = Integer.parseInt(req.getParameter("board_seq")); // get��� ������ �ޱ� / �����Ϸ��� �� ��ȣ
			ref = Integer.parseInt(req.getParameter("ref"));
			ref_step = Integer.parseInt(req.getParameter("ref_step"));
			ref_level = Integer.parseInt(req.getParameter("ref_level"));
		} 

		model.addAttribute("board_seq", board_seq);
		model.addAttribute("ref", ref);
		model.addAttribute("ref_step", ref_step);
		model.addAttribute("ref_level", ref_level);
		
		
		System.out.println("board_seq : " + board_seq);
		System.out.println("ref : " + ref);
		System.out.println("ref_step : " + ref_step);
		System.out.println("ref_level : " + ref_level);
		
		return "board/management/freeboard/jspForm/writePostForm";
	}
	
	@RequestMapping("insertpostPro")
	public String insertpostPro(HttpServletRequest req, Model model) {
		
		Fservice.writeNewPostPro(req, model);
		return "board/management/freeboard/pro/insertPostPro";
	}
}
