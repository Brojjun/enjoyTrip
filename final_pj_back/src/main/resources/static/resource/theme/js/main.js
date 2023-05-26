windowOnload(function () {
  const swiper = new Swiper('.swiper-container', {
    direction: 'horizontal',
    loop: true,
    pagination: {
      //페이징 사용자 설정
      el: '.main_pagination_con', //페이징 태그 클래스 설정
      type: 'bullets',
      clickable: true,
    },
    navigation: {
      nextEl: '.rolling_arrow.next', // 다음 버튼 클래스명
      prevEl: '.rolling_arrow.prev', // 이번 버튼 클래스명
    },
  });
});
