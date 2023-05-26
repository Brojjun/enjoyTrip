<template>
  <div v-if="openStatus" class="col-12 popup_style_1_wrap">
    <div class="col-12 popup_con">
      <div class="col-12 popup_inner">
        <div class="col-12 mb27 tc popup_title" v-html="msg"></div>
        <div class="col-12 tc popup_style_1_btn_con">
          <div class="col-12 bg_point0 popup_style_1_btn" @click="close">확인</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AlertPopup',
  props: {
    alertMsg: String,
    popupStatus: Boolean,
  },
  data() {
    return {
      msg: this.alertMsg,
      openStatus: this.popupStatus,
    };
  },
  methods: {
    close() {
      this.openStatus = false;
      this.msg = null;
      this.$emit('closePopup');
    },
  },
  watch: {
    popupStatus: function () {
      this.msg = this.alertMsg;
      this.openStatus = this.popupStatus;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.popup_style_1_wrap {
  z-index: 10001;
  position: fixed;
  overflow: hidden;
  height: 100vh;
  overflow-y: auto;
  top: 0 !important;
  left: 0 !important;
  margin-left: 0 !important;
  margin-top: 0 !important;
  background-color: rgba(0, 0, 0, 0.6);
}
.popup_style_1_wrap .popup_con {
  max-width: 440px;
  display: flex;
  height: auto;
  float: none;
  margin: 0 auto;
  align-items: center;
  min-height: 100%;
}
.popup_style_1_wrap .popup_inner {
  border-radius: 8px;
  background-color: #fff;
  padding-top: 32px;
}
.popup_style_1_wrap .popup_title {
  font-size: 25px;
  font-size: 2.5rem;
  line-height: 31px;
  line-height: 3.1rem;
  font-weight: 500;
  letter-spacing: -0.02em;
  padding-left: 20px;
  padding-right: 20px;
  word-break: keep-all;
}
.popup_style_1_btn_con .popup_style_1_btn {
  padding-top: 7px;
  padding-bottom: 7px;
  cursor: pointer;
  font-size: 17px;
  font-size: 1.7rem;
  line-height: 23px;
  line-height: 2.3rem;
  font-weight: 500;
  letter-spacing: -0.02em;
  color: #fff;
  border-bottom-left-radius: 8px;
  border-bottom-right-radius: 8px;
}
</style>
