<template>
  <div class="col-12 mb30 search_bar_con has_input">
    <div class="col-12 col-md-3 select_style_0_con">
      <select class="col-12 select_style_0" v-model="searchType" @change="changeType">
        <option value="" selected>선택하세요</option>
        <option value="1">작성자</option>
        <option value="2">제목</option>
      </select>
    </div>
    <div class="col-12 col-md-9 input_style_0_con">
      <input
        type="text"
        class="input_style_0"
        v-model="searchText"
        ref="searchText"
        placeholder="검색어를 입력해주세요"
      />
    </div>
    <div class="col-12 ml-md-10 col-md-0 search_btn_con">
      <button class="col-0 ml5 fr search_btn" @click="getSearchList(false)">초기화</button>
      <button class="col-0 fr search_btn search bg_point0" @click="getSearchList(true)">
        <img :src="this.$resource.commonRes.img('19x19_white_search_icon.png')" alt="돋보기" />
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PlannerSearchBar',
  data() {
    return {
      searchType: null,
      searchText: null,
    };
  },
  methods: {
    changeType() {
      this.searchText = null;
    },
    getSearchList(status) {
      if (!status) {
        this.searchType = null;
        this.searchText = null;
      }
      let param = { searchType: this.searchType, searchText: this.searchText };
      this.$emit('emitSearchParam', param);
    },
  },
};
</script>
