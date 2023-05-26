<template>
  <main class="col-12">
    <div class="col-12 sub-header">
      <img :src="this.$resource.themeRes.img('11.jpg')" alt="" />
      <h1 class="sub-header-text">마이페이지</h1>
      <div class="col-12 dim"></div>
    </div>
    <div class="col-12 col-center mw-1230 pl30 pr30">
      <div class="col-12 tab_style_0_wrap">
        <div class="col-12 tab_style_0_con">
          <div
            class="col-4 tab_style_0"
            v-for="(tab, index) in tabs"
            :class="{ active: currentTab === index }"
            @click="currentTab = index"
            :key="index + '1'"
          >
            <div class="col-12">{{ tab }}</div>
          </div>
        </div>
        <div class="col-12 tab_style_0_contents_con">
          <user-info-update v-show="currentTab == 0"></user-info-update>
          <user-favorite v-show="currentTab == 1"></user-favorite>
          <user-my-planner v-show="currentTab == 2"></user-my-planner>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import UserInfoUpdate from './contents/UserInfoUpdate.vue';
import UserFavorite from './contents/UserFavorite.vue';
import UserMyPlanner from './contents/UserMyPlanner.vue';

export default {
  components: { UserInfoUpdate, UserFavorite, UserMyPlanner },
  name: 'UserMyPage',
  data() {
    return {
      currentTab: 0,
      tabs: ['회원정보수정', '즐겨찾기 여행지', '마이플래너'],
    };
  },
};
</script>
