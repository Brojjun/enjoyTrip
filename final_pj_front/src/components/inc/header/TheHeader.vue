<template>
  <header>
    <div class="col-12 col-center mw-1230 pl30 pr30 header_con" ref="originHeader">
      <div class="col-0 left_box">
        <a href="/" class="col-0 logo_con">
          <img :src="this.$resource.themeRes.img('logo.png')" alt="로고" />
        </a>
        <The-header-util></The-header-util>
      </div>
      <div class="col-0 right_box">
        <the-header-gnb></the-header-gnb>
        <div class="col-0 hamburger_con" @click="openClickGnb()">
          <span></span>
        </div>
      </div>
    </div>
    <div class="col-12 click_header_wrap" ref="clickHeader">
      <div class="col-12 col-center mw-930 pl30 pr30 click_header_con">
        <div class="col-12 mb30 top_con">
          <a href="/" class="col-0 logo_con">
            <img :src="this.$resource.themeRes.img('logo-white.png')" alt="로고" />
          </a>
          <div class="col-0">
            <ul class="col-0 my_page_con mr20 mt7">
              <li>
                <a href="javascript:void(0)" onclick="login.openLoginPopup()">로그인</a>
              </li>
              <li>
                <a href="javascript:void(0)" onclick="signUp.openSignUpPopup()">회원가입</a>
              </li>
            </ul>
            <a href="javascript:void(0)" class="col-0 popup_close_btn" @click="closeClickGnb()">닫기 버튼</a
            >
          </div>
        </div>
        <div class="col-12 bottom_con">
          <the-header-gnb></the-header-gnb>
        </div>
      </div>
    </div>
    <div class="col-12 click_header_dim" ref="clickHeaderDim"></div>
  </header>
</template>

<script>
import TheHeaderUtil from './item/Util.vue';
import TheHeaderGnb from './item/Gnb.vue';

export default {
  name: 'TheHeader',
  components: {
    TheHeaderUtil,
    TheHeaderGnb,
  },
  methods : {
      //클릭 헤더 열기
      openClickGnb() {
          const clickGnb = this.$refs.clickHeader;

          if( !clickGnb.classList.contains('default') ) clickGnb.classList.add('default');

          this.$refs.clickHeaderDim.classList.add('open');
          clickGnb.classList.add('active');
      },
      //클릭 헤더 닫기
      closeClickGnb() {
        this.$refs.clickHeader.classList.remove('active');
        this.$refs.clickHeaderDim.classList.remove('open');
      },
  }

};
</script>

<style scope>
@import '@/assets/resource/theme/css/theme.resource.header.css';
</style>
