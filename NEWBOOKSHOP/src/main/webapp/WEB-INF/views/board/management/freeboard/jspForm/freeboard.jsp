<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../PATH.jsp" %>
<link type="text/css" rel="stylesheet" href="${comCss}bookboard.css">
<%
	if(request.getSession().getAttribute("sessionId")!=null) {
		out.println("세션 연결 상태...YES");
	} else {
%>		
		<script type="text/javascript">
		alert("로그인 하세요!");
		location.replace("/bms/main");
		</script>
<%
	}
%>
<html>
<body>
	<h2><center>회원 전용 게시판 / 페이지번호 : ${pageNum}</center></h2>
	<form action="searchpostbyid" method="post" name="searchform">
		<input type="text" name="searchId">
		<input type="submit" value="아이디로 게시글 검색">
	</form>
		<table style="width:1000px" align="center">
			<tr>
				<th colspan="6" align="center" style="height:25px">
					<input type="button" name="writepost" value="글쓰러가기" onclick="window.location='writepostform?pageNum=${pageNum}'">
				</th>
			</tr>
	<c:if test="${totalCnt > 0}"> <!-- 글이 존재할때 totalCnt > 0  -->
			<tr>
				<th style="width:15%">게시글순서/ref/step/level</th>
				<th style="width:25%">제목</th>
				<th style="width:10%">작성자</th>
				<th style="width:15%">작성일</th>
				<th style="width:5%">조회수</th>
				<th style="width:10%">IP</th>			
			</tr>
		<c:forEach var="vo1" items="${bigVo}">
			<tr>
				<td align="center"> <!-- 글번호 부분 -->
						${articleNum}/${vo1.ref}/${vo1.ref_step}/${vo1.ref_level}<c:set var="articleNum" value="${articleNum-1}" />
				</td>
				<td align="center"> <!-- 글제목 부분 -->
						<c:if test="${vo1.ref_level > 0}">
                 			<img src="${comImg}icon/re.gif" border="0">
               			</c:if>
					<a href="viewpostdetail?board_seq=${vo1.board_seq}&articleNum=${articleNum}&pageNum=${pageNum}">${vo1.subject}</a>
					<!-- 글제목 -->
						<!-- 추가 -->
						<c:if test="${vo1.ref_level>1 }"><!--  들여쓰기 > 1 -->
                  			<c:set var="wid" value="${(vo1.ref_level-1)*10 }"/>
                  			<img src="${comImg}icon/level.gif" border="0" width="${wid}" height="15">
               			</c:if>
               			
               			<!--  들여쓰기 > 0 : 답변글 -->
              			
              			 <!-- hot 이미지 -->
               			<c:if test="${vo1.readCnt >10}">
                 			<img src="${comImg}icon/hot.gif" border="0" valign="middle">
               			</c:if>
					<!-- 글제목 눌렀는데 다 똑같은 페이지만 나오면 곤란하다 -->
				</td>
				<td align="center"> <!-- 글쓴이 -->
						${vo1.id}
				</td>
				<td align="center"> <!-- 작성일 -->
						${vo1.reg_date}
				</td>
				<td align="center"> <!-- 조회수 --> <!-- contentForm에서 뒤로가기 눌렀을떄.. -->
						${vo1.readCnt}
				</td>
				<td align="center"> <!-- IP주소 -->
						${vo1.ip}
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<!------------------------------------------------------------------------->
	<c:if test="${totalCnt == 0}"> <!-- 글이 존재하지 않을때 -->
		<table style="width:1000px" align="center">
			<tr>
				<th style="width:15%">글번호</th>
				<th style="width:25%">제목</th>
				<th style="width:10%">작성자</th>
				<th style="width:15%">작성일</th>
				<th style="width:5%">조회수</th>
				<th style="width:10%">IP</th>			
			</tr>
			<tr>
				<th colspan="6" align="center" style="height:25px">
					방명록에 글이 없습니다! 당신이 첫번째 손님입니다!
				</th>
				
			</tr>
		</table>
	</c:if>	
	<!------------------------페이지 처리 부분-------------------------->
	<c:if test="${totalCnt > 0}">
		<table style="width:1000px" align="center">
			<tr>
				<th>
					<c:if test="${pageNumStart > pageBlock}">
						<a href="freeboard">[처음으로]</a>
						<a href="freeboard?pageNum=${pageNumStart-pageBlock}">[◀]</a>
					</c:if>	
					
					<!-- 복사한거라..다시 공부 -->
					<c:forEach var="i" begin="${pageNumStart}" end="${pageNumEnd}">
			    		<c:if test="${i == currentPage}">
			    			<span><b>[${i}]</b></span>
			    		</c:if>
			    		<c:if test="${i != currentPage}">
			    			<a href="freeboard?pageNum=${i}">[${i}]</a>
			    		</c:if>
		    		</c:forEach>
					
					
					<c:if test="${pageTotalCnt > pageNumEnd}">
						<a href="freeboard?pageNum=${pageNumStart + pageBlock}">[▶]</a>
						<a href="freeboard?pageNum=${pageTotalCnt}">[마지막으로]</a>
					</c:if>
				</th>
			</tr>
		</table>
	</c:if>
</body>
</html>