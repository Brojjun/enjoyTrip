<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enjoy Trip 지역 선택</title>
<%@include file="../../import/importSub.jsp" %>
</head>
<body>
	<%@include file="../inc/header.jsp" %>
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
            <c:forEach var="s" items="${sidos}">
              <div class="col-12 col-md-3 card_style_0">
                <div class="col-12 card_style_0_inner">
                  <div class="col-12 img_box">
                    <img src="${s.sidoImg}" alt="카드스타일" />
                    <p class="img_box_text"><a href="${root}/attraction/attractionList?sido=${s.sidoCode}&pageNo=1">${s.sidoName}</a></p>
                  </div>
                </div>
              </div>
              </c:forEach>
            </div>
          </div>
        </article>
      </section>
    </main>
	<%@include file="../inc/footer.jsp" %>
</body>
</html>