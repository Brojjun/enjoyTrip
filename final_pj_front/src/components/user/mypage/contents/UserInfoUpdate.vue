<template>
  <div class="col-12 tab_style_0_contents">
    <div class="col-12 mb27 tab_style_0_title">회원정보 수정</div>
    <div class="col-12 mb40 form_style_0_con">
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box">
          <label class="essential" for="userId">아이디</label>
        </div>
        <div class="col-12 col-md-0 input_box">
          <div class="col-12 input_style_0_con">
            <input type="text" id="userId" class="input_style_0" v-model="user.id" readonly />
          </div>
        </div>
      </div>
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box">
          <label class="essential" for="userName">이름</label>
        </div>
        <div class="col-12 col-md-0 input_box">
          <div class="col-12 input_style_0_con">
            <input type="text" id="userName" class="input_style_0" v-model="user.name" />
          </div>
        </div>
      </div>
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box">
          <label class="essential" for="userEmail0">이메일</label>
        </div>
        <div class="col-12 col-md-0 input_box type_email">
          <div class="col-12 input_style_0_con">
            <input type="text" id="userEmail0" v-model="user.email0" class="input_style_0" />
          </div>
          <div class="col-0 ml7 mr7 mt10 addr_text">@</div>
          <div class="col-12 input_style_0_con">
            <input
              type="text"
              id="userEmail1"
              v-model="user.email1"
              ref="userEmail1"
              placeholder="도메인을 입력해주세요"
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
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box">
          <span>관심 시·도</span>
        </div>
        <div class="col-12 col-md-0 input_box">
          <div class="col-12 select_style_0_con">
            <select class="col-12 mw-220 select_style_0" v-model="user.sido" @change="changeSido">
              <option v-for="(sido, index) in sidos" :key="index + '1'" :value="sido.value">
                {{ sido.text }}
              </option>
            </select>
          </div>
        </div>
      </div>
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box">
          <span>관심 구·군</span>
        </div>
        <div class="col-12 col-md-0 input_box">
          <div class="col-12 mw-220 select_style_0_con">
            <select class="col-12 select_style_0" v-model="user.gugun">
              <option v-for="(gugun, index) in guguns" :key="index + '1'" :value="gugun.value">
                {{ gugun.text }}
              </option>
            </select>
          </div>
        </div>
      </div>
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box">
          <span class="essential">생년월일</span>
        </div>
        <div class="col-12 col-md-0 input_box type_birth">
          <div class="col-12 select_style_0_con">
            <select class="col-12 select_style_0" v-model="user.birthYear">
              <option v-for="item in yearValue" :key="item" :value="item">{{ item }}</option>
            </select>
          </div>
          <div class="col-12 select_style_0_con">
            <select class="col-12 select_style_0" v-model="user.birthMonth" @change="changeMonth">
              <option v-for="item in monthValue" :key="item" :value="item">{{ item }}</option>
            </select>
          </div>
          <div class="col-12 select_style_0_con">
            <select class="col-12 select_style_0" v-model="user.birthDate" ref="signUpBirthDate">
              <option v-for="item in dateValue" :key="item" :value="item">{{ item }}</option>
            </select>
          </div>
        </div>
      </div>
    </div>
    <div class="col-12 tc">
      <button class="btn_style_0 mr5" @click="userUpdate">수정</button>
      <button class="btn_style_1" @click="goBack">뒤로 가기</button>
    </div>

    <alert-popup
      :popupStatus="alertOpenStatus"
      :alertMsg="alertMsg"
      @closePopup="closePopup"
    ></alert-popup>
  </div>
</template>

<script>
import { update } from '@/api/user';
import { mapState, mapGetters, mapActions, mapMutations } from 'vuex';
import AlertPopup from '../../../util/AlertPopup.vue';

const locationStore = 'locationStore';
const userStore = 'userStore';

export default {
  components: { AlertPopup },
  name: 'UserInfoUpdate',
  data() {
    return {
      alertOpenStatus: false,
      alertMsg: null,
      checkPasswordStatus: false,
      yearValue: [],
      monthValue: [],
      dateValue: [],
      user: {
        no: '',
        id: '',
        name: '',
        email0: '',
        email1: '',
        email2: '',
        sido: '',
        gugun: '',
        birthYear: '',
        birthMonth: '',
        birthDate: '',
      },
    };
  },
  computed: {
    ...mapState(locationStore, ['sidos', 'guguns']),
  },
  methods: {
    ...mapGetters(userStore, ['checkUserInfo']),
    ...mapMutations(userStore, ['SET_USER_INFO']),
    checkPassword() {
      if (this.user.password == null || this.user.password == '') {
        this.$refs.checkPasswordText.innerHTML = '';
        this.$refs.checkPasswordText.style.display = 'none';
        this.$refs.passwordInfoText.style.display = '';
        this.checkPasswordStatus = false;
        return;
      }

      const regex = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
      if (regex.test(this.user.password)) {
        this.$refs.checkPasswordText.innerHTML =
          '<span class="success fontweight600">사용 가능</span>한 비밀번호 입니다.';
        this.$refs.passwordInfoText.style.display = 'none';
        this.$refs.checkPasswordText.style.display = '';
        this.checkPasswordStatus = true;
      } else {
        this.$refs.checkPasswordText.innerHTML =
          '<span class="warning fontweight600">사용 불가능</span>한 비밀번호 입니다.';
        this.$refs.passwordInfoText.style.display = 'none';
        this.$refs.checkPasswordText.style.display = '';
        this.checkPasswordStatus = false;
      }
    },
    async userCheckId() {
      if (this.user.id == '') {
        this.openPopup('아이디를 입력해주세요');
        return;
      }
    },
    ...mapActions(locationStore, ['getSido', 'getGugun']),
    ...mapMutations(locationStore, ['CLEAR_SIDO_LIST', 'CLEAR_GUGUN_LIST']),
    //이메일 선택 시 직접입력이 아니면 바꿔주는 함수
    changeEmail1() {
      this.user.email1 = this.user.email2;
      if (this.user.email2 == '') {
        this.$refs.signUpEmail1.focus();
      }
    },
    changeMonth() {
      if (this.user.birthMonth == 2) {
        console.log('test');
      }
    },
    changeSido() {
      this.CLEAR_GUGUN_LIST();
      this.user.gugun = null;
      if (this.user.sido) this.getGugun(this.user.sido);
    },
    async userUpdate() {
      if (this.user.name == '' || this.user.name == null) {
        this.openPopup('이름을 입력해주세요');
        return;
      }

      if (
        this.user.email0 == '' ||
        this.user.email0 == null ||
        this.user.email1 == '' ||
        this.user.email1 == null
      ) {
        this.openPopup('이메일을 입력해주세요');
        return;
      }

      if (
        this.user.birthYear == '' ||
        this.user.birthYear == null ||
        this.user.birthMonth == '' ||
        this.user.birthMonth == null ||
        this.user.birthDate == '' ||
        this.user.birthDate == null
      ) {
        this.openPopup('생년월일을 선택해주세요');
        return;
      }

      await update(
        this.user,
        ({ data }) => {
          if (data == true) {
            this.SET_USER_INFO(this.user);
            this.openPopup('변경완료되었습니다');
          } else {
            this.openPopup('변경에 실패했습니다. 잠시 후 다시 시도해주세요');
          }
        },
        (error) => {
          console.log(error);
          alert('에러로 실패했습니다. 관리자에 문의해주세요.');
        }
      );
    },
    openPopup(msg) {
      this.alertOpenStatus = true;
      this.alertMsg = msg;
    },
    closePopup() {
      this.alertOpenStatus = false;
      this.alertMsg = null;
    },
    goBack() {
      this.$router.go(-1);
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

    this.user = this.checkUserInfo();
    this.user.email2 = this.user.email1;
    this.getSido();
    this.getGugun(this.user.sido);
  },
};
</script>
