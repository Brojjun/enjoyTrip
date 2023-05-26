<template>
  <section class="col-12">
    <swiper :options="swiperOption" class="col-12 over-hidden swiper-container main_visual_wrap">
      <swiper-slide v-for="data in swiperData" class="col-12 main_visual_con" :key="data.dataNo">
        <div class="col-12 img_box"><img :src="data.imgPath" alt="메인비주얼이미지" /></div>
        <div class="col-12 text_box">
          <div class="col-12 title">{{ data.title }}</div>
          <div class="col-12 desc">{{ data.desc }}</div>
        </div>
        <div class="col-12 dim"></div>
      </swiper-slide>
    </swiper>
    <div class="col-12 tc main_pagination_con"></div>
    <div class="col-0 rolling_arrow prev"></div>
    <div class="col-0 rolling_arrow next"></div>
  </section>
</template>

<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';

export default {
  name: 'UserMainVisual',
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      swiperOption: {
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
      },
      swiperData: [],
    };
  },
  created() {
    this.swiperData = [
      {
        dataNo: 1,
        imgPath: this.$resource.themeRes.img('3.jpg'),
        title: '안녕하세요. Enjoy Trip에 오신 것을 환영합니다.',
        desc: '안녕하세요. Enjoy Trip에 오신 것을 환영합니다. 안녕하세요. Enjoy Trip에 오신 것을 환영합니다. 안녕하세요. Enjoy Trip에 오신 것을 환영합니다.',
      },
      {
        dataNo: 2,
        imgPath: this.$resource.themeRes.img('5.jpg'),
        title: '안녕하세요. Enjoy Trip에 오신 것을 환영합니다.',
        desc: '안녕하세요. Enjoy Trip에 오신 것을 환영합니다. 안녕하세요. Enjoy Trip에 오신 것을 환영합니다. 안녕하세요. Enjoy Trip에 오신 것을 환영합니다.',
      },
      {
        dataNo: 3,
        imgPath: this.$resource.themeRes.img('10.jpg'),
        title: '안녕하세요. Enjoy Trip에 오신 것을 환영합니다.',
        desc: '안녕하세요. Enjoy Trip에 오신 것을 환영합니다. 안녕하세요. Enjoy Trip에 오신 것을 환영합니다. 안녕하세요. Enjoy Trip에 오신 것을 환영합니다.',
      },
    ];
  },
};
</script>

<style></style>
