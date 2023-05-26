<template>
  <div class="col-12 col-center mw-1230 pl30 pr30">
    <h1 class="col-12 mb27 tc page_title">글쓰기</h1>
    <div class="col-12 form_style_0_con">
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box">
          <label for="subject">제목</label>
        </div>
        <div class="col-12 col-md-0 input_box">
          <input
            id="subject"
            class="input_style_0"
            name="subject"
            v-model="article.title"
            placeholder="제목을 입력해주세요"
          />
        </div>
      </div>
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box">
          <span>내용</span>
        </div>
        <div class="col-12 col-md-0 input_box">
          <textarea
            v-model="article.contents"
            id="content"
            name="content"
            cols="50"
            placeholder="내용을 입력해주세요"
            class="textarea_style_0"
          ></textarea>
        </div>
      </div>
      <!-- 파일 업로드 -->
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box">
          <label for="files">파일 첨부</label>
        </div>
        <div class="col-12 col-md-0 input_box">
          <input id="files" type="file" class="file-input" ref="files" multiple />
        </div>
      </div>
    </div>
    <div class="col-12 tc btn_style_1_con">
      <a href="javascript:void(0)" @click="writeNotice" class="btn_style_0 mr10">등록</a>
      <router-link class="btn_style_1" :to="{ name: 'noticeList' }">목록으로</router-link>
    </div>
  </div>
</template>

<script>
import { writeNotice } from '@/api/notice';
import { mapState } from 'vuex';
export default {
  name: 'NoticeWrite',

  data() {
    return {
      article: {
        title: '',
        contents: '',
        files: [],
      },
    };
  },

  computed: {
    ...mapState('userStore', ['isLogin', 'userInfo']),
  },

  methods: {
    async writeNotice() {
      const body = new FormData();

      body.append('title', this.article.title);
      body.append('contents', this.article.contents);
      body.append('No', this.userInfo.no);
      let tmp = this.$refs.files.files;

      for (let i = 0; i < tmp.length; i++) {
        body.append('files', tmp[i]);
      }

      await writeNotice(body);
      this.$router.push({ name: 'noticeList' });
    },
  },
};
</script>
