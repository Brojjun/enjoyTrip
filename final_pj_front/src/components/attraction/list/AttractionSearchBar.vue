<template>
  <div class="col-12 mb40 search_bar_con">
    <div class="col-12 col-md-6 select_style_0_con">
      <select class="col-12 select_style_0" v-model="sidoCode" @change="searchChangeSido">
        <option v-for="(sido, index) in sidos" :key="index + '1'" :value="sido.value">
          {{ sido.text }}
        </option>
      </select>
    </div>
    <div class="col-12 col-md-6 select_style_0_con">
      <select class="col-12 select_style_0" v-model="gugunCode">
        <option v-for="(gugun, index) in guguns" :key="index + '1'" :value="gugun.value">
          {{ gugun.text }}
        </option>
      </select>
    </div>
    <div class="col-12 ml-md-10 col-md-0 search_btn_con">
      <button class="col-0 ml5 fr search_btn" @click="searchBtnEvent(false)">초기화</button>
      <button class="col-0 fr search_btn search bg_point0" @click="searchBtnEvent(true)">
        <img :src="this.$resource.commonRes.img('19x19_white_search_icon.png')" alt="돋보기" />
      </button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'AttractionSearchBar',
  props : {
    sidos : Array,
    guguns : Array,
  },
  data() {
    return {
      sidoCode : null,
      gugunCode : null,
    }
  }, 
  methods: {
    searchChangeSido() {
      this.gugunCode = null;
      if (this.sidoCode){
        this.$emit('emitChangeSido', this.sidoCode);
      }
    },
    searchBtnEvent(status){
      if(!status){
        this.sidoCode = null;
        this.gugunCode = null;
      }

      let params = {
        sidoCode : this.sidoCode,
        gugunCode : this.gugunCode,
        status : status,
      }
      this.$emit('emitSearchList', params);
    },
  },
};
</script>
