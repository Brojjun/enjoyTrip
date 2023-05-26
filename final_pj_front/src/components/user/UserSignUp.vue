<template>
  <div class="col-12 pt99 pb50 login_wrap">
    <div class="col-12 col-center mw-830 pl30 pr30">
      <div class="col-12 tc pb37 mb40 login_title">회원가입</div>
      <div class="col-12 mb40 form_style_0_con">
        <div class="col-12 form_style_0">
          <div class="col-12 col-md-0 label_box">
            <label class="essential" for="signUpId">아이디</label>
          </div>
          <div class="col-12 col-md-0 input_box has_btn">
            <div class="col-12 input_style_0_con">
              <input
                type="text"
                id="signUpId"
                ref="signUpId"
                class="input_style_0"
                v-model="user.id"
                @keyup="changeId"
                @keyup.enter="userCheckId"
              />
            </div>
            <div class="col-0 btn_style_1_con">
              <button class="btn_style_1 type_2" @click="userCheckId">중복확인</button>
            </div>
          </div>
        </div>
        <div class="col-12 form_style_0">
          <div class="col-12 col-md-0 label_box">
            <label class="essential" for="signUpPw">비밀번호</label>
          </div>
          <div class="col-12 col-md-0 input_box">
            <div class="col-12 input_style_0_con">
              <input
                type="password"
                id="signUpPw"
                class="input_style_0"
                v-model="user.password"
                @keyup="checkPassword"
              />
            </div>
            <div class="col-12 mt12 addr_text" ref="checkPasswordText" style="display: none"></div>
            <div class="col-12 mt12 addr_text" ref="passwordInfoText">
              비밀번호는 영 대·소문자, 특수문자, 숫자 포함 8 ~ 16자 조합으로 만들어야 합니다.
            </div>
          </div>
        </div>
        <div class="col-12 form_style_0">
          <div class="col-12 col-md-0 label_box">
            <label class="essential" for="signUpName">이름</label>
          </div>
          <div class="col-12 col-md-0 input_box">
            <div class="col-12 input_style_0_con">
              <input
                type="text"
                id="signUpName"
                ref="signUpName"
                class="input_style_0"
                v-model="user.name"
              />
            </div>
          </div>
        </div>
        <div class="col-12 form_style_0">
          <div class="col-12 col-md-0 label_box">
            <label class="essential" for="signUpEmail0">이메일</label>
          </div>
          <div class="col-12 col-md-0 input_box type_email">
            <div class="col-12 input_style_0_con">
              <input
                type="text"
                id="signUpEmail0"
                v-model="user.email0"
                ref="signUpEmail0"
                class="input_style_0"
              />
            </div>
            <div class="col-0 ml7 mr7 mt10 addr_text">@</div>
            <div class="col-12 input_style_0_con">
              <input
                type="text"
                id="signUpEmail1"
                v-model="user.email1"
                ref="signUpEmail1"
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
                <option v-for="sido in sidos" :key="sido.value" :value="sido.value">
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
                <option v-for="gugun in guguns" :key="gugun.value" :value="gugun.value">
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
      <div class="col-12 tc btn_style_0_con">
        <div class="btn_style_0 mr5" @click="signUp">회원가입</div>
        <router-link class="btn_style_1 ml5" :to="{ name: 'home' }"> 취소 </router-link>
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
import { checkId, signUp } from '@/api/user';
import { mapState, mapActions, mapMutations } from 'vuex';
import AlertPopup from '../util/AlertPopup.vue';

const duplicateId = 'ssafy';
const locationStore = 'locationStore';

export default {
  components: { AlertPopup },
  name: 'UserSignUp',
  data() {
    return {
      alertOpenStatus: false,
      alertMsg: null,
      duplicatedIdVal: '',
      checkIdStatus: false,
      checkPasswordStatus: false,
      yearValue: [],
      monthValue: [],
      dateValue: [],
      user: {
        id: '',
        password: '',
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
    changeId() {
      if (this.duplicatedIdVal != this.user.id) {
        this.checkIdStatus = false;
      } else {
        this.checkIdStatus = true;
      }
    },
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

      await checkId(
        this.user.id,
        ({ data }) => {
          if (data) {
            this.duplicatedIdVal = this.user.id;
            this.checkIdStatus = true;
            this.openPopup('사용 가능한 아이디입니다');
          } else {
            this.duplicatedIdVal = duplicateId;
            this.checkIdStatus = false;
            this.openPopup('사용 불가능한 아이디입니다');
          }
        },
        (error) => {
          console.log(error);
          alert('에러로 실패했습니다. 관리자에 문의해주세요.');
        }
      );
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
    async signUp() {
      //아이디 체크
      if (this.user.id == '' || this.user.id == null) {
        this.openPopup('아이디를 입력해주세요');
        return;
      }
      if (!this.checkIdStatus) {
        this.openPopup('아이디 중복확인을 해주세요');
        return;
      }

      if (this.user.password == '' || this.user.password == null) {
        this.openPopup('비밀번호를 입력해주세요');
        return;
      }

      if (!this.checkPasswordStatus) {
        this.openPopup('비밀번호 양식이 틀렸습니다.');
        return;
      }

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

      await signUp(
        this.user,
        ({ data }) => {
          if (data) {
            alert('가입이 완료되었습니다.');
            this.$router.push({ name: 'UserLogin' });
          } else {
            alert('가입에 실패했습니다. 잠시 후 다시 시도해주세요.');
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
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();

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
