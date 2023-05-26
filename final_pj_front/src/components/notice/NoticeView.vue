<template>
  <div class="col-12 col-center mw-1230 pl30 pr30 view_style_0_wrap">
    <div class="col-12 mb17 view_style_0_title">{{ article.title }}</div>
    <div class="col-12 mb34 view_style_0_info_list_con flex-jsb">
      <div>
        <div>
          <span>작성자</span>
          <span>{{ article.writerName }}</span>
        </div>
        <div>
          <span>조회수</span>
          <span>{{ article.hits }}</span>
        </div>
        <div>
          <span>추천</span>
          <span>{{ likeCnt }}</span>
        </div>
      </div>
      <div>
        <span>작성일</span>
        <span>{{ article.registDateStr }}</span>
      </div>
    </div>
    <div class="col-12 mb40 mb-md-70 view_style_0_contents">
      <div class="col-12 view_style_0_desc">
        {{ article.contents }}
      </div>
      <div v-if="article.fileData" class="col-12 view_style_0_file_info_con flex-as">
        <span>파일 : </span>
        <ul>
          <li
            v-for="file in article.fileInfo"
            :key="file.fileNo"
            @click="downLoad(file.fileNo, file.originalFile)"
          >
            {{ file.originalFile }}
          </li>
        </ul>
      </div>
    </div>
    <div class="col-12 mb20 btn_style_1_con" v-if="isLogin === true">
      <button class="btn_style_1 type_2" @click="likeChange(true)" v-if="likeButton === 1">
        글 추천 취소
      </button>
      <button class="btn_style_1 type_2" @click="likeChange(false)" v-if="likeButton === 0">
        글 추천
      </button>
    </div>
    <notice-comment :noticeNo="parseInt(article.no)"></notice-comment>
    <div class="col-12 tc view_style_0_btn_con">
      <router-link class="btn_style_1 mr5" :to="{ name: 'noticeList' }">목록</router-link>
      <span v-if="isLogin === true">
        <span v-if="userInfo.isAdmin === 1">
          <button @click="modifyNotice" class="btn_style_0 mr5">수정</button>
          <button @click="deleteNotice" class="btn_style_1 grey">삭제</button>
        </span>
      </span>
    </div>
  </div>
</template>

<script>
import NoticeComment from './comment/NoticeComment.vue';
import { mapState } from 'vuex';
import {
  getNotice,
  deleteNotice,
  addHit,
  downLoad,
  writeLike,
  deleteLike,
  getLikeCnt,
  getNoticeLike,
} from '@/api/notice';

export default {
  name: 'NoticeView',
  components: {
    NoticeComment,
  },
  data() {
    return {
      article: {},
      likeCnt: 0,
      likeButton: -1,
    };
  },

  async created() {
    this.article.no = this.$route.params.no;
    await getNotice(this.article.no, ({ data }) => {
      this.article = data;
    });
    await getLikeCnt(this.article.no, ({ data }) => {
      this.likeCnt = data;
    });
    await getNoticeLike(this.userInfo.no, this.article.no, ({ data }) => {
      this.likeButton = data;
    });
    this.userGetView();
  },

  computed: {
    ...mapState('userStore', ['isLogin', 'userInfo']),
  },
  methods: {
    async likeChange(flag) {
      let body = {
        userNo: this.userInfo.no,
        noticeNo: this.article.no,
      };
      if (flag == true) {
        await deleteLike(body.noticeNo, body.userNo, () => {
          this.$set(this, 'likeButton', 0);
          this.$set(this, 'likeCnt', this.likeCnt - 1);
        });
      } else {
        await writeLike(body, () => {
          this.$set(this, 'likeButton', 1);
          this.$set(this, 'likeCnt', this.likeCnt + 1);
        });
      }
    },
    async userGetView() {
      let visitedList = JSON.parse(sessionStorage.getItem('visitedNotice'));

      //방문한적이 없는 경우
      if (
        visitedList == null ||
        visitedList == '[]' ||
        visitedList == '' ||
        !visitedList.includes(parseInt(this.article.no))
      ) {
        await addHit(this.article.no);
        if (visitedList === null) visitedList = [];
        visitedList.push(this.article.no);
        sessionStorage.setItem('visitedNotice', JSON.stringify(visitedList));
      }
    },
    deleteNotice() {
      deleteNotice(this.article.no);

      this.$router.push({ name: 'noticeList' });
    },
    modifyNotice() {
      this.$router.push({ name: 'noticeModify', params: { no: this.article.no } });
    },
    downLoad(item, fileName) {
      const FileDownLoad = require('js-file-download');
      downLoad(item, ({ data }) => {
        FileDownLoad(data, fileName);
      });
    },
  },
};
</script>
