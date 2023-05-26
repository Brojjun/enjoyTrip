<template>
  <div class="col-12 login_wrap">
    <div class="col-12 col-center mw-530 pl30 pr30">
      <h1 class="col-12 pb37 mb40 login_title point0 border_point0">로그인</h1>
      <div class="col-12 mb27 login_con">
        <div class="col-12 mb20 form_style_0_con">
          <div class="col-12 form_style_0">
            <div class="col-12 col-md-0 label_box">
              <label for="loginId">아이디</label>
            </div>
            <div class="col-12 col-md-0 input_box">
              <div class="col-12 input_style_0_con">
                <input
                  type="text"
                  class="input_style_0"
                  id="loginId"
                  v-model="user.id"
                  placeholder="아이디를 입력해주세요"
                />
              </div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-12 col-md-0 label_box">
              <label for="loginPw">비밀번호</label>
            </div>
            <div class="col-12 col-md-0 input_box">
              <div class="col-12 input_style_0_con">
                <input
                  type="password"
                  class="input_style_0"
                  id="loginPw"
                  v-model="user.password"
                  ref="loginPw"
                  placeholder="비밀번호를 입력해주세요"
                  @keyup.enter="confirm"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="col-12 tr btn_style_0_con">
          <div class="btn_style_0" @click="confirm()">로그인</div>
        </div>
      </div>
      <ul class="col-12 login_addr_list_con">
        <li>
          아직 회원이 아니신가요?<br />
          회원가입을 하시면 보다 많은 콘텐츠를 이용하실 수 있습니다.
          <router-link :to="{ name: 'UserSignUp' }"><span>회원가입</span></router-link>
        </li>
        <li>
          아이디와 비밀번호를 잊어버리셨나요?<br />
          <router-link :to="{ name: 'UserFindId' }"><span>아이디 찾기</span></router-link>
          &nbsp;
          <router-link :to="{ name: 'UserFindPw' }"><span>비밀번호 찾기</span></router-link>
        </li>
      </ul>
    </div>
    <alert-popup
      :popupStatus="alertOpenStatus"
      :alertMsg="alertMsg"
      @closePopup="closePopup"
    ></alert-popup>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import AlertPopup from '../util/AlertPopup.vue';

const userStore = 'userStore';

export default {
  name: 'UserLogin',
  components: { AlertPopup },
  data() {
    return {
      alertOpenStatus: false,
      alertMsg: null,
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'isLoginError', 'userInfo']),
  },
  methods: {
    ...mapActions(userStore, ['userConfirm', 'getUserInfo']),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem('accessToken');
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: 'home' });
      } else {
        this.openPopup('로그인에 실패했습니다. 다시 확인해주세요');
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
