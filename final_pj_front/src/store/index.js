import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
// module import
import userStore from '@/store/modules/theme/userStore';
import locationStore from '@/store/modules/theme/locationStore';
import favoriteStore from '@/store/modules/theme/favoriteStore';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    userStore,
    locationStore,
    favoriteStore,
  },
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
