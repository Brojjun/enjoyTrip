<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enjoy Trip 마이페이지</title>
<%@include file="../../import/importMypage.jsp" %>
</head>
<body>
	<%@include file="../inc/header.jsp" %>
	<main class="col-12">
	<div class="col-12 sub-header">
		<img src="${resourceThemeImg}/11.jpg" alt="" />
		<h1 class="sub-header-text">마이페이지</h1>
		<div class="col-12 dim"></div>
	</div>
	<div class="col-12 mypage_box">
		<div class="col-12 mypage_box_liked">
			<div class="mypage_category">저장 목록</div>
			<div class="col-12 liked_cards">
				<div class="col-12 col-center mw-1230 pl30 pr30">
					<div class="col-12 card_style_0_con">
						<c:forEach var="f" items="${favorites}">
							<div class="col-12 col-md-3 card_style_0">
								<div class="col-12 card_style_0_inner">
									<div class="col-12 img_box">
										<c:choose>
											<c:when test="${empty f.img}">
												<img src="${resourceThemeImg}/1.jpg" alt="카드스타일" />
											</c:when>
											<c:otherwise>
												<img src="${f.img}" alt="카드스타일" />
											</c:otherwise>
										</c:choose>
									</div>
									<div class="col-12 text_box">
										<div class="col-12 title">${f.title}</div>
										<div class="col-12 desc m30" style="margin-bottom: -30px">
											${f.sidoName} ${f.gugunName}</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="col-12 mypage-info-box">
			<div class="col-12 col-center">
				<div class="col-12 mypage_category">회원정보 변경</div>
			</div>
			<div class="col-12 col-center mw-1230 pl30 pr30 form_style_0_con">
				<input type="hidden" id="updateNo" readonly/>
				<div class="col-12 form_style_0">
					<div class="col-0 label_box">
						<label for="updateId">이름</label>
					</div>
					<div class="col-0 input_box">
						<div class="col-12 input_style_0_con">
							<input type="text" id="updateId" placeholder="이름을 입력해주세요" />
						</div>
					</div>
				</div>
				<div class="col-12 form_style_0">
					<div class="col-0 label_box">
						<label for="userPassword">비밀번호</label>
					</div>
					<div class="col-0 input_box">
						<div class="col-12 input_style_0_con">
							<input type="password" id="updatePassword"
								placeholder="비밀번호를 입력해주세요" />
						</div>
					</div>
					<div class="col-12 tc btn_style_0_con">
						<button type="button" class="btn_style_0" id="updateBtn">변경</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>
	<%@include file="../inc/footer.jsp" %>
</body>
<script>
	mypage.init(${userInfo.no});
</script>
</html>