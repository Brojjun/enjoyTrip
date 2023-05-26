<template>
  <main class="col-12">
    <div class="col-12 sub-header">
      <img :src="this.$resource.themeRes.img('21-2.jpg')" alt="" />
      <h1 class="sub-header-text">플래너</h1>
      <div class="col-12 dim"></div>
    </div>
    <router-view></router-view>
  </main>
</template>

<script>
export default {
  name: 'AppPlanner',
};
</script>

<style scope>
@import '@/assets/resource/theme/css/theme.layout.sub.css';
@import '@/assets/resource/theme/css/theme.page.sub.css';
</style>
