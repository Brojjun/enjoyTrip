<template>
  <main class="col-12">
    <main-visual></main-visual>
  </main>
</template>

<script>
import { mapState } from 'vuex';
import MainVisual from '@/components/main/MainVisual.vue';

const userStore = 'userStore';

export default {
  name: 'UserMain',
  components: {
    MainVisual,
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'userInfo']),
  },
  methods: {},
  created() {
    //즐겨찾기 확인
    if (this.isLogin) {
      this.userGetFavorites(this.userInfo.no);
    }
  },
};
</script>

<style scope>
@import '@/assets/resource/theme/css/theme.layout.main.css';
@import '@/assets/resource/theme/css/theme.page.main.css';
</style>
