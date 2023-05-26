<template>
  <div class="col-12 login_wrap">
    <div class="col-12 col-center mw-530 pl30 pr30">
      <h1 class="col-12 pb37 mb40 login_title point0 border_point0">비밀번호 찾기</h1>
      <div class="col-12 login_con">
        <div class="col-12 mb20 form_style_0_con">
          <div class="col-12 form_style_0">
            <div class="col-12 col-md-0 label_box">
              <label for="findPwId">아이디</label>
            </div>
            <div class="col-12 col-md-0 input_box">
              <div class="col-12 input_style_0_con">
                <input
                  type="text"
                  class="input_style_0"
                  id="findPwId"
                  v-model="user.id"
                  placeholder="아이디를 입력해주세요"
                />
              </div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-12 col-md-0 label_box">
              <label for="findPwName">이름</label>
            </div>
            <div class="col-12 col-md-0 input_box">
              <div class="col-12 input_style_0_con">
                <input
                  type="text"
                  class="input_style_0"
                  id="findPwName"
                  v-model="user.name"
                  placeholder="이름을 입력해주세요"
                />
              </div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-12 col-md-0 label_box">
              <label for="email0">이메일</label>
            </div>
            <div class="col-12 col-md-0 input_box type_email">
              <div class="col-12 input_style_0_con">
                <input
                  type="text"
                  id="email0"
                  v-model="user.email0"
                  class="input_style_0"
                />
              </div>
              <div class="col-0 ml7 mr7 mt10 addr_text">@</div>
              <div class="col-12 input_style_0_con">
                <input
                  type="text"
                  id="email1"
                  v-model="user.email1"
                  ref="email1"
                  placeholder="도메인 입력"
                  class="input_style_0"
                />
              </div>
              <div class="col-12 select_style_0_con">
                <select class="col-12 select_style_0" v-model="user.email2" @change="changeEmail1">
                  <option value="naver.com">naver.com</option>
                  <option value="gmail.com">gmail.com</option>
                  <option value="daum.net">daum.net</option>
                  <option value="kakao.com">kakao.com</option>
                  <option value="" selected>직접 입력</option>
                </select>
              </div>
            </div>
          </div>
        </div>
        <div class="col-12 tr btn_style_0_con">
          <router-link :to="{ name: 'UserLogin' }" class="btn_style_1 mr10">뒤로 가기</router-link>
          <div class="btn_style_0" @click="userFindPw()">비밀번호 찾기</div>
        </div>
      </div>
    </div>
    <alert-popup
      :popupStatus="alertOpenStatus"
      :alertMsg="alertMsg"
      @closePopup="closePopup"
    ></alert-popup>
  </div>
</template>

<script>
import { findPw } from '@/api/user';
import AlertPopup from '../util/AlertPopup.vue';

export default {
  name: 'UserFindPw',
  components: { AlertPopup },
  data() {
    return {
      alertOpenStatus: false,
      alertMsg: null,
      user: {
        id : null,
        name: null,
        email0 : null,
        email1 : null,
        email2 : null,
      },
    };
  },
  methods: {
    async userFindPw() {
      await findPw(this.user, ({ data }) => {
        if (data) {
          this.openPopup('등록하신 이메일로 임시 비밀번호가 발송되었습니다');
        } else {
          this.openPopup('일치하는 정보가 없습니다. 다시 한번 확인해주세요');
        }
      });
    },
    //이메일 선택 시 직접입력이 아니면 바꿔주는 함수
    changeEmail1() {
      this.user.email1 = this.user.email2;
      if (this.user.email2 == '') {
        this.$refs.email1.focus();
      }
    },
    changeMonth() {
      if (this.user.birthMonth == 2) {
        console.log('test');
      }
    },
    openPopup(msg) {
      this.alertOpenStatus = true;
      this.alertMsg = msg;
    },
    closePopup() {
      this.alertOpenStatus = false;
      this.alertMsg = null;
    },
  },
};
</script>

<style scope>
@import '@/assets/resource/theme/css/theme.resource.login.css';
</style>