<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<%@include file="../../import/importSub.jsp" %>
</head>
<body>
	<%@include file="../inc/header.jsp" %>
	<main class="col-12">
		<div class="col-12 sub-header">
			<img src="${resourceThemeImg}/11.jpg" alt="" />
			<h1 class="sub-header-text">공지사항</h1>
			<div class="col-12 dim"></div>
		</div>
		<article class="col-12 col-center mw-1230 pb70">
			<div class="col-12 table_style_0_con">
				<div class="col-12">
					<table class="col-12 table_style_0">
						<colgroup>
							<col width="80px"/>
							<col width="auto"/>
							<col width="120px"/>
							<col width="120px"/>
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${resultList}">
								<tr>
									<td>1</td>
									<td><a href="${root}/notice/view?${list.no}">${list.title}</a></td>
									<td>${list.memberName}</td>
									<td>${list.registDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<c:if test="${count != 0}">
					<nav class="col-12 mt30 pagination_style_0_con">
					  <ul class="pagination_style_0">
					    <li class="<c:if test="${prev eq false}">disabled</c:if> paging_btn_single_page to_first">
					      <a href="<c:if test="${prev eq true}">list?pageNo=${beginPage - 1}</c:if>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    		  
						<c:forEach var="i" begin="${beginPage}" end="${endPage}">
						    <c:choose>
						    	<c:when test="${i eq pageNo}">
								    <li class="paging_btn_page selected"><a href="#1">${i}</a></li>
						    	</c:when>
						    	<c:otherwise>
								    <li class="paging_btn_page"><a href="list?pageNo=${i}">${i}</a></li>
						    	</c:otherwise>
						    </c:choose>
						</c:forEach>
						
					    <li class="<c:if test="${next eq false}">disabled</c:if>  paging_btn_single_page to_last">
					      <a href="<c:if test="${next eq true}">list?pageNo=${endPage + 1}</c:if>" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>	
					    	    
					  </ul>
					</nav>
				</c:if>
			</div>
		</article>
	</main>
	<%@include file="../inc/footer.jsp" %>
</body>
</html>