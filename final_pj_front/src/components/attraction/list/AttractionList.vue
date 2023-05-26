<template>
  <section class="col-12">
    <article class="col-12 col-center mw-1230 pl30 pr30">
      <attraction-search-bar
        :sidos="sidos"
        :guguns="guguns"
        @emitChangeSido="changeSido"
        @emitSearchList="selectSearchList"
      ></attraction-search-bar>
      <div class="col-12 card_style_0_con">
        <attraction-list-item
          v-for="(attraction, index) in attractions"
          :key="index + '1'"
          v-bind="attraction"
          @emitUpdateFavorite="checkFavorites"
        ></attraction-list-item>
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
  </section>
</template>

<script>
import { getList } from '@/api/attraction';

import { mapState, mapMutations, mapActions } from 'vuex';

import AttractionSearchBar from './AttractionSearchBar.vue';
import AttractionListItem from './AttractionListItem.vue';

const userStore = 'userStore';
const locationStore = 'locationStore';
const favoriteStore = 'favoriteStore';

export default {
  name: 'AttractionList',
  components: {
    AttractionSearchBar,
    AttractionListItem,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      page: 1,
      maxArticle: 0,
      perPage: 12,
      attractions: [],
      pageNumbers: [],
    };
  },
  computed: {
    ...mapState(locationStore, ['sidos', 'guguns']),
    ...mapState(userStore, ['isLogin', 'userInfo']),
    ...mapState(favoriteStore, ['favorites']),
  },
  methods: {
    ...mapActions(locationStore, ['getSido', 'getGugun']),
    ...mapActions(favoriteStore, ['userGetFavorites']),
    ...mapMutations(locationStore, ['CLEAR_GUGUN_LIST']),
    async userGetList() {
      let param = {
        sido: this.sidoCode,
        gugun: this.gugunCode,
        page: this.page,
        perPage: this.perPage,
      };
      await getList(
        param,
        ({ data }) => {
          this.maxArticle = data.totalCnt;
          this.attractions = [];
          data.list.forEach((attraction) => {
            this.attractions.push({
              addr1: attraction.addr1,
              addr2: attraction.addr2,
              contentId: attraction.contentId,
              contentTypeId: attraction.contentTypeId,
              firstImage: attraction.firstImage,
              firstImage2: attraction.firstImage2,
              gugunCode: attraction.gugunCode,
              sidoCode: attraction.sidoCode,
              title: attraction.title,
              zipCode: attraction.zipCode,
              latitude: attraction.latitude,
              longitude: attraction.longitude,
              mlevel: attraction.mlevel,
              favorite: 0,
            });
          });
          this.setPageNumbers();
          this.checkFavorites();
        },
        (error) => {
          console.log(error);
          alert('에러로 실패했습니다. 관리자에 문의해주세요.');
        }
      );
    },
    setPageNumbers() {
      this.pageNumbers = [];

      const lastPage = Math.ceil(this.maxArticle / this.perPage);
      const startPage = Math.max(Math.floor((this.page - 1) / 5) * 5 + 1, 1);
      const endPage = Math.min(startPage + 4, lastPage);
      for (let i = startPage; i <= endPage; i++) {
        this.pageNumbers.push(i);
      }
    },
    moveToPrevGroup() {
      const prevPage = Math.max(this.page - 5, 1);
      this.page = prevPage;
      this.userGetList();
    },
    moveToNextGroup() {
      const lastPage = Math.ceil(this.maxArticle / this.perPage);
      const nextPage = Math.min(this.page + 5, lastPage);
      this.page = nextPage;
      this.userGetList();
    },
    moveToPage(pageNum) {
      const lastPage = Math.ceil(this.maxArticle / this.articlePerPage);
      if (pageNum >= 1 && pageNum <= lastPage) {
        this.page = pageNum;
        this.userGetList();
      }
    },
    showNextGroup() {
      return this.page + 5 < Math.ceil(this.maxArticle / this.perPage);
    },
    showPrevGroup() {
      Math.floor((this.page - 1) / 5) * 5 > 1;
    },
    changeSido(sidoCode) {
      this.sidoCode = sidoCode;
      this.gugunCode = null;
      this.getGugun(sidoCode);
    },
    selectSearchList(param) {
      this.sidoCode = param.sidoCode;
      this.gugunCode = param.gugunCode;

      //초기화 버튼인 경우
      if (!param.status) {
        this.page = 1;
        this.CLEAR_GUGUN_LIST();
      }

      this.userGetList();
    },
    checkFavorites() {
      if (this.favorites.length > 0) {
        this.attractions.map((item) => {
          if (this.favorites.indexOf(item.contentId) > -1) {
            item.favorite = 1;
          } else {
            item.favorite = 0;
          }
        });
      }
    },
  },
  created() {
    this.getSido();
    //즐겨찾기 확인
    if (this.isLogin) {
      this.userGetFavorites(this.userInfo.no);
    }

    this.userGetList();
  },
};
</script>
