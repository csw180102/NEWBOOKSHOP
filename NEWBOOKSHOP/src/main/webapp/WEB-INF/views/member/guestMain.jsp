<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../PATH.jsp" %>
<script src="${comJs}commonScript.js"></script>
<script src="${comJs}memberScript.js"></script>
<link type="text/css" rel="stylesheet" href="${comCss}commonCss.css">
<html>

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

  <body onload="showBookInfo();">
  	<header>
  		<nav id="topMenu" > 
  			<ul>
  				<li><a class="menuLink" href="freeboard">회원게시판</a></li> 
  				<li><a class="menuLink" href="mypage">마이 페이지</a></li>
  				<li><a class="menuLink" href="logoutPro">로그아웃</a> 
  			</ul> 
  		</nav>
  	</header>
    <div id="jb-container">
      <div id="jb-content">
		<table>
			<c:if test="${totalCnt > 0}"> <!-- 글이 존재할때 totalCnt > 0  -->
				<!-- articleNum을 0~부터 증가시켜야 되는데.. -->
				<c:forEach var="vo" items="${bigVo}">
			<c:if test="${seq <= articleNum}"> <!-- seq=0 으로.. -->
							<c:set var="seq" value="${seq+1}" />		
							<c:if test="${seq%6!=0}">
								<td align="center"> <!-- 이미지 -->
									<a href="mainbookdetail?book_seq=${vo.book_seq}"><img src="${comImg}books/${vo.image}"></a>
								</td>
								
							</c:if>
								<!-- 6배수 되는 번째 책들  -->
							<c:if test="${seq!=0 && seq%6==0}">
									<td align="center"> <!-- 이미지 -->
										<a href="mainbookdetail?book_seq=${vo.book_seq}"><img src="${comImg}books/${vo.image}"></a>
									</td>
								<table>
									<tr>
									</tr>
								</table>
									
							</c:if>
			</c:if>
				</c:forEach>
			</c:if>

		</table>
    </div>
    </div>
    
   	<!------------------------페이지 처리 부분-------------------------->
	<c:if test="${totalCnt > 0}">
		<table style="width:1000px" align="center">
			<tr>
				<th>
					<c:if test="${pageNumStart > pageBlock}">
						<a href="main.book">[처음으로]</a>
						<a href="main.book?pageNum=${pageNumStart-pageBlock}">[◀]</a>
					</c:if>	
					
					<!-- 복사한거라..다시 공부 -->
					<c:forEach var="i" begin="${pageNumStart}" end="${pageNumEnd}">
			    		<c:if test="${i == currentPage}">
			    			<span><b>[${i}]</b></span>
			    		</c:if>
			    		<c:if test="${i != currentPage}">
			    			<a href="main.book?pageNum=${i}">[${i}]</a>
			    		</c:if>
		    		</c:forEach>
					
					
					<c:if test="${pageTotalCnt > pageNumEnd}">
						<a href="main.book?pageNum=${pageNumStart + pageBlock}">[▶]</a>
						<a href="main.book?pageNum=${pageTotalCnt}">[마지막으로]</a>
					</c:if>
				</th>
			</tr>
		</table>
	</c:if>

   
    
    
    
    <div id="jb-footer">
        <p>Copyright</p>
    </div>
  </body>
</html>