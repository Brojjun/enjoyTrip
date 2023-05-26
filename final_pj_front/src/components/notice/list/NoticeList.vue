<template>
  <article class="col-12 col-center mw-1230 pb70">
    <div class="col-12 table_style_0_con">
      <div class="col-12">
        <table class="col-12 table_style_0">
          <colgroup>
            <col width="80px" />
            <col width="auto" />
            <col width="120px" />
            <col width="100px" />
            <col width="100px" />
            <col width="150px" />
          </colgroup>
          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>조회수</th>
              <th>좋아요</th>
              <th>등록일</th>
            </tr>
          </thead>
          <tbody>
            <notice-list-item
              v-for="article in articles"
              :key="article.no"
              :article="article"
              v-bind="article"
            ></notice-list-item>
          </tbody>
        </table>
      </div>
    </div>
    <nav class="col-12 pl30 pr30 mt30 pagination_style_0_con">
      <ul class="pagination_style_0">
        <li
          v-show="showPrevGroup"
          class="paging_btn_single_page to_first"
          @click="moveToPrevGroup"
        ></li>

        <li
          v-for="(pageNumber, index) in displayedPageNumbers"
          :key="index + '1'"
          :class="[page === pageNumber ? 'paging_btn_page selected point0' : 'paging_btn_page']"
        >
          <a href="javascript:void(0)" @click="moveToPage(pageNumber)">{{ pageNumber }}</a>
        </li>

        <li
          v-show="showNextGroup"
          class="paging_btn_single_page to_last"
          @click="moveToNextGroup"
        ></li>
      </ul>

      <div v-if="isLogin === true">
        <div v-if="userInfo.isAdmin === 1">
          <router-link :to="{ name: 'noticeWrite' }" class="col-0 write_icon bg_point0">
            <span>글쓰기</span>
          </router-link>
        </div>
      </div>
    </nav>
  </article>
</template>

<script>
import NoticeListItem from './NoticeListItem.vue';
import { mapState } from 'vuex';
import { getCommentCnt, getNoticeList } from '@/api/notice';

export default {
  name: 'NoticeList',
  components: {
    NoticeListItem,
  },
  data() {
    return {
      articles: [],
      page: 1,
      max_article: 0,
      articlePerPage: 10,
    };
  },

  async created() {
    await getCommentCnt(
      ({ data }) => {
        this.max_article = data;
      },
      ({ error }) => {
        console.dir(error);
      }
    );
    await this.getArticle();
  },
  async mounted() {},

  methods: {
    moveToPrevGroup() {
      const nextPage = Math.max(this.page - 5, 1);
      this.page = nextPage;
      this.getArticle();
    },
    moveToNextGroup() {
      const lastPage = Math.ceil(this.max_article / this.articlePerPage);
      const nextPage = Math.min(this.page + 5, lastPage);
      this.page = nextPage;
      this.getArticle();
    },
    moveToPage(pageNumber) {
      const lastPage = Math.ceil(this.max_article / this.articlePerPage);
      if (pageNumber >= 1 && pageNumber <= lastPage) {
        this.page = pageNumber;
        this.getArticle();
      }
    },
    async getArticle() {
      await getNoticeList(
        this.page,
        ({ data }) => {
          this.articles = data;
        },
        ({ error }) => {
          console.dir(error);
        }
      );
    },
  },
  computed: {
    ...mapState('userStore', ['isLogin', 'userInfo']),
    displayedPageNumbers() {
      let pageNumbers = [];
      const lastPage = Math.ceil(this.max_article / this.articlePerPage);
      const startPage = Math.max(Math.floor((this.page - 1) / 5) * 5 + 1, 1);
      const endPage = Math.min(startPage + 4, lastPage);
      for (let i = startPage; i <= endPage; i++) {
        pageNumbers.push(i);
      }
      return pageNumbers;
    },
    showNextGroup() {
      return (
        Math.floor((this.page - 1) / 5) * 5 + 5 < Math.ceil(this.max_article / this.articlePerPage)
      );
    },
    showPrevGroup() {
      return Math.floor((this.page - 1) / 5) * 5 > 1;
    },
  },
};
</script>

<style lang="scss" scoped></style>
