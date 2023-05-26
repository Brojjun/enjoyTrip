<template>
  <ul class="col-0 mt16 my_page_con">
    <li v-if="!isLogin">
      <router-link :to="{name : 'UserLogin'}">로그인</router-link>
    </li>
    <li v-if="!isLogin">
      <router-link :to="{name : 'UserSignUp'}">회원가입</router-link>
    </li>
    <li v-if="isLogin">
      <router-link :to="{name : 'UserLogout'}">로그아웃</router-link>
    </li>
    <li v-if="isLogin">
      <router-link :to="{name : 'UserMyPage'}">마이페이지</router-link>
    </li>
  </ul>
</template>

<script>
import { mapState } from 'vuex';

const userStore = 'userStore';

export default {
  name: 'TheHeaderUtil',
  computed: {
    ...mapState(userStore, ['isLogin']),
  },
};
</script>
