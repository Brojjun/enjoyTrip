<template>
  <div id="app">
    <the-header></the-header>
    <router-view />
    <the-footer></the-footer>
  </div>
</template>

<style>
#app {
  position: relative;
  min-height: 100vh;
  padding-top: 91px;
  padding-bottom: 139px;
  overflow: hidden;
}

@import '@/assets/resource/theme/css/theme.common.css';
</style>

<script>
import TheHeader from './components/inc/header/TheHeader.vue';
import TheFooter from './components/inc/footer/TheFooter.vue';
export default {
  components: {
    TheHeader,
    TheFooter,
  },
};
</script>
