<template>
  <section class="col-12">
    <article class="col-12 col-center mw-1230 pl30 pr30">
      <route-search-bar @emitSearchParam="userGetSearchList"></route-search-bar>
      <div class="col-12 mb20 tr btn_style_0_con">
        <router-link :to="{ name: 'RouteWrite' }" class="btn_style_0 type_2"
          >플래너 만들기</router-link
        >
      </div>
      <div class="col-12 card_style_0_con">
        <route-list-item
          v-for="(route, index) in routes"
          :key="index + '1'"
          v-bind="route"
        ></route-list-item>
      </div>
      <nav class="col-12 mt30 pagination_style_0_con">
        <ul class="pagination_style_0">
          <li v-if="showPrevGroup" class="paging_btn_single_page to_first">
            <a href="javascript:void(0)" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li
            v-for="(pageNumber, index) in pageNumbers"
            :key="index + '1'"
            :class="[page === pageNumber ? 'paging_btn_page selected point0' : 'paging_btn_page']"
          >
            <a href="javascript:void(0)" @click="moveToPage(pageNumber)">{{ pageNumber }}</a>
          </li>
          <li v-show="showNextGroup" class="paging_btn_single_page to_last">
            <a v-if="showNextGroup" @click="moveToNextGroup" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </article>
    <alert-popup
      :popupStatus="alertOpenStatus"
      :alertMsg="alertMsg"
      @closePopup="closePopup"
    ></alert-popup>
  </section>
</template>

<script>
import { getList } from '@/api/route';
import RouteSearchBar from './RouteSearchBar.vue';
import RouteListItem from './RouteListItem.vue';
import AlertPopup from '../../util/AlertPopup.vue';

export default {
  name: 'RouteList',
  components: {
    RouteSearchBar,
    RouteListItem,
    AlertPopup,
  },
  data() {
    return {
      alertOpenStatus: false,
      alertMsg: null,
      page: 1,
      totalCnt: 0,
      perPage: 12,
      searchType: null,
      searchText: null,
      routes: [],
      pageNumbers: [],
    };
  },
  computed: {
    showNextGroup() {
      return this.page + 5 < Math.ceil(this.totalCnt / this.perPage);
    },
    showPrevGroup() {
      return this.page - 4 > 1;
    },
  },
  methods: {
    async userGetList() {
      let param = {
        page: this.page,
        perPage: this.perPage,
        searchType: this.searchType,
        searchText: this.searchText,
      };
      await getList(
        param,
        ({ data }) => {
          this.routes = [];
          data.list.forEach((route) => {
            this.routes.push({
              no: route.no,
              title: route.title,
              firstImage: null,
              writerName: route.writerName,
            });
          });
          this.totalCnt = data.totalCnt;
        },
        (error) => {
          console.log(error);
          alert('에러로 실패했습니다. 관리자에 문의해주세요.');
        }
      );
    },
    moveToPrevGroup() {
      const prevPage = Math.max(this.page - 5, 1);
      this.page = prevPage;
      this.userGetList();
    },
    moveToNextGroup() {
      const lastPage = Math.ceil(this.totalCnt / this.perPage);
      const nextPage = Math.min(this.page + 5, lastPage);
      this.page = nextPage;
      this.userGetList();
    },
    moveToPage(pageNum) {
      const lastPage = Math.ceil(this.totalCnt / this.perPage);
      if (pageNum >= 1 && pageNum <= lastPage) {
        this.page = pageNum;
        this.userGetList();
      }
    },
    userGetSearchList(param) {
      this.searchType = param.searchType;
      this.searchText = param.searchText;
      if (this.searchType == null && this.searchText != null) {
        this.openPopup('검색어 종류를 선택해주세요');
        return;
      }

      this.userGetList();
    },
    openPopup(msg) {
      this.alertOpenStatus = true;
      this.alertMsg = msg;
    },
    closePopup() {
      this.alertOpenStatus = false;
      this.alertMsg = null;
    },
  },
  created() {
    //즐겨찾기 확인
    if (this.isLogin) {
      this.userGetFavorites(this.userInfo.no);
    }
    this.userGetList();
  },
};
</script>
