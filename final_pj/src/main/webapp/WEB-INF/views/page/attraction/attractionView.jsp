<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enjoy Trip 행사 상세</title>
<%@include file="../../import/importSub.jsp" %>
</head>
<body>
	<%@include file="../../page/inc/header.jsp" %>
	<main class="col-12">
      <div class="col-12 sub-header">
        <img src="${resourceThemeImg}/21-2.jpg" alt="" />
        <h1 class="sub-header-text">여행할 지역을 선택해주세요</h1>
        <div class="col-12 dim"></div>
      </div>
      <section class="col-12 main_contents_con">
        <article class="col-12 main_contents_1">
          <div class="col-12 col-center mw-1230 pl30 pr30">
            <div class="col-12 card_style_0_con">
            <c:forEach var="a" items="${attractions}">
              <div class="col-12 col-md-3 card_style_0">
                <div class="col-12 card_style_0_inner">
                  <div class="col-12 img_box">
                    <c:choose>
						<c:when test="${empty a.firstImage}">
							<img src="${resourceThemeImg}/1.jpg" alt="카드스타일" />
						</c:when>
						<c:otherwise>
							<img src="${a.firstImage}" alt="카드스타일" />
						</c:otherwise>
					</c:choose>
                    <p class="img_box_text"><a href="${root}/attraction?action=attractionList&sido=${a.sidoCode}">${a.title}</a></p>
                  </div>
                </div>
              </div>
              </c:forEach>
            </div>
          </div>
        </article>
      </section>
    </main>
    <%@include file="../../page/inc/footer.jsp" %>
</body>
</html>