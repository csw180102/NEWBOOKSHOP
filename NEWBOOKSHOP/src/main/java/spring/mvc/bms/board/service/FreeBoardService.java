package spring.mvc.bms.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface FreeBoardService {
	//자유게시판 목록 뿌리기
	public void showFreeboardListPro(HttpServletRequest req, Model model);
	//자유게시판 글 보기
	public void showPostDetailPro(HttpServletRequest req, Model model);
	//특정 회원이 쓴 게시글 뿌리기
	public void showMyPostPro(HttpServletRequest req, Model model);
	//게시글 수정하는 서비스1(기존내용 writePostForm.jsp로 불러오기 및 이동)
	public void updateMyPostPro(HttpServletRequest req, Model model);
	//게시글 수정하는 서비스2(수정내용 freeboard테이블에 update)
	public void updateMyPostProTwo(HttpServletRequest req, Model model);
	//게시글 삭제 서비스
	public void deleteMyPostPro(HttpServletRequest req, Model model);
	//글쓰기 서비스
	public void writeNewPostPro(HttpServletRequest req, Model model);

}
