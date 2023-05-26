export default {
  install(Vue) {
    Vue.prototype.$resource = Setting;
  },
};

const Setting = {
  commonRes: {
    img: (imgName) => {
      return require('@/assets/resource/common/img/' + imgName);
    },
    css: (cssName) => {
      return require('@/assets/resource/common/css/' + cssName);
    },
    js: (jsName) => {
      return require('@/assets/resource/common/js/' + jsName);
    },
  },
  themeRes: {
    img: (imgName) => {
      return require('@/assets/resource/theme/img/' + imgName);
    },
    css: (cssName) => {
      return require('@/assets/resource/theme/css/' + cssName);
    },
    js: (jsName) => {
      return require('@/assets/resource/theme/js/' + jsName);
    },
  },
};
