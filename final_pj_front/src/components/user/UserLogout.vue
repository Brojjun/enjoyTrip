<template>
  <div>
    로그아웃중입니다.
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

const userStore = 'userStore';

export default {
  name: 'UserLogout',
  computed: {
    ...mapState(userStore, ['isLogin', 'isLoginError', 'userInfo']),
  },
  methods: {
    ...mapActions(userStore, ['userLogout']),
    async logout() {
      await this.userLogout(this.userInfo.no);
      if(!this.isLogin){
        this.$router.push({ name: 'home' });
      }
    },
  },
  created() {
    this.logout();
  },
};
</script>

<style scope>
@import '@/assets/resource/theme/css/theme.resource.header.css';
</style>
