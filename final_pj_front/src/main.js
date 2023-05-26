import '@/assets/resource/common/css/import.css';

import Vue from 'vue';
import vSwitch from 'v-switch-case';
import App from './App.vue';
import router from './router';
import store from './store';
import Setting from '@/assets/Setting';

Vue.config.productionTip = false;
Vue.use(vSwitch);
Vue.use(Setting);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
