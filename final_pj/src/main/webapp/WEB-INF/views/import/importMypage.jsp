<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="resourceCommonCss" value="${pageContext.request.contextPath}/resource/common/css"/>
<c:set var="resourceCommonJs" value="${pageContext.request.contextPath}/resource/common/js"/>
<c:set var="resourceCommonImg" value="${pageContext.request.contextPath}/resource/common/img"/>
<c:set var="resourceThemeCss" value="${pageContext.request.contextPath}/resource/theme/css"/>
<c:set var="resourceThemeJs" value="${pageContext.request.contextPath}/resource/theme/js"/>
<c:set var="resourceThemeImg" value="${pageContext.request.contextPath}/resource/theme/img"/>

<link rel="icon" href="${resourceThemeImg}/favicon.ico" />

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
<link rel="stylesheet" href="${resourceCommonCss}/responsiveJM.min.css" />
<link rel="stylesheet" href="${resourceCommonCss}/common.css" />
<link rel="stylesheet" href="${resourceThemeCss}/theme.common.css" />
<link rel="stylesheet" href="${resourceThemeCss}/theme.resource.header.css" />
<link rel="stylesheet" href="${resourceThemeCss}/theme.resource.footer.css" />
<link rel="stylesheet" href="${resourceThemeCss}/theme.layout.sub.css" />
<link rel="stylesheet" href="${resourceThemeCss}/theme.page.sub.css" />
<link rel="stylesheet" href="${resourceThemeCss}/theme.page.mypage.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<script type="text/javascript" src="${resourceCommonJs}/common.js"></script>
<script type="text/javascript" src="${resourceCommonJs}/vanillaAjax.js"></script>
<script type="text/javascript" src="${resourceCommonJs}/windowLoad.js"></script>
<script type="text/javascript" src="${resourceCommonJs}/gnb.js"></script>
<script type="text/javascript" src="${resourceCommonJs}/PopupManager.js"></script>
<script type="text/javascript" src="${resourceThemeJs}/login.js"></script>
<script type="text/javascript" src="${resourceThemeJs}/logout.js"></script>
<script type="text/javascript" src="${resourceThemeJs}/signUp.js"></script>
<script type="text/javascript" src="${resourceThemeJs}/findPw.js"></script>
<script type="text/javascript" src="${resourceThemeJs}/mypage.js"></script>