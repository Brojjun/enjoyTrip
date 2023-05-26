<template>
  <div class="col-12 login_wrap">
    <div class="col-12 col-center mw-530 pl30 pr30">
      <h1 class="col-12 pb37 mb40 login_title point0 border_point0">아이디 찾기</h1>
      <div class="col-12 login_con">
        <div class="col-12 mb20 form_style_0_con">
          <div class="col-12 form_style_0">
            <div class="col-12 col-md-0 label_box">
              <label for="findIdName">이름</label>
            </div>
            <div class="col-12 col-md-0 input_box">
              <div class="col-12 input_style_0_con">
                <input
                  type="text"
                  class="input_style_0"
                  id="findIdName"
                  v-model="user.name"
                  placeholder="이름을 입력해주세요"
                />
              </div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-12 col-md-0 label_box">
              <span>생년월일</span>
            </div>
            <div class="col-12 col-md-0 input_box type_birth">
              <div class="col-12 select_style_0_con">
                <select class="col-12 select_style_0" v-model="user.birthYear">
                  <option v-for="item in yearValue" :key="item" :value="item">{{ item }}</option>
                </select>
              </div>
              <div class="col-12 select_style_0_con">
                <select
                  class="col-12 select_style_0"
                  v-model="user.birthMonth"
                  @change="changeMonth"
                >
                  <option v-for="item in monthValue" :key="item" :value="item">{{ item }}</option>
                </select>
              </div>
              <div class="col-12 select_style_0_con">
                <select
                  class="col-12 select_style_0"
                  v-model="user.birthDate"
                  ref="signUpBirthDate"
                >
                  <option v-for="item in dateValue" :key="item" :value="item">{{ item }}</option>
                </select>
              </div>
            </div>
          </div>
        </div>
        <div class="col-12 tr btn_style_0_con">
          <router-link :to="{ name: 'UserLogin' }" class="btn_style_1 mr10">뒤로 가기</router-link>
          <div class="btn_style_0" @click="userFindId()">아이디 찾기</div>
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
import { findId } from '@/api/user';
import AlertPopup from '../util/AlertPopup.vue';

export default {
  name: 'UserFindId',
  components: { AlertPopup },
  data() {
    return {
      alertOpenStatus: false,
      alertMsg: null,
      yearValue: [],
      monthValue: [],
      dateValue: [],
      user: {
        name: null,
        birthYear: null,
        birthMonth: null,
        birthDate: null,
      },
    };
  },
  methods: {
    async userFindId() {
      await findId(this.user, ({ data }) => {
        if (data.result) {
          this.openPopup( `아이디는 <span class="point0 fontweight600">${data.data}</span>입니다`);
        } else {
          this.openPopup('일치하는 아이디가 없습니다. 다시 한번 확인해주세요');
        }
      });
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
  created() {
    //날짜 설정
    //년 설정
    const startYear = 1930;
    const endYear = 2023;
    for (let i = startYear; i < endYear; i++) {
      this.yearValue.push(i);
    }
    //월 설정
    for (let i = 1; i < 13; i++) {
      this.monthValue.push(i);
    }
    //일 설정
    for (let i = 1; i < 32; i++) {
      this.dateValue.push(i);
    }
  },
};
</script>

<style scope>
@import '@/assets/resource/theme/css/theme.resource.login.css';
</style>
