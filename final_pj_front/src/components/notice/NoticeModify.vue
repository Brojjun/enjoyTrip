<template>
  <div class="col-12 col-center mw-1230 pl30 pr30">
    <h1 class="col-12 mb27 tc page_title">글 수정</h1>
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
      <div class="col-12 form_style_0">
        <div class="col-12 col-md-0 label_box"></div>
        <div class="col-12 col-md-0 input_box">
          <input type="file" class="file-input" ref="files" multiple />
          <ul class="col-12 mt10">
            <li v-for="file in article.fileInfo" :key="file.fileNo">
              <div v-if="fileShow[file.fileNo]">
                {{ file.originalFile }} &nbsp;&nbsp;
                <a @click="deleteFile(file.fileNo)">x</a>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="col-12 tc btn_style_1_con">
      <a href="javascript:void(0)" @click="modifyNotice" class="btn_style_0 mr10">수정</a>
      <router-link class="btn_style_1" :to="{ name: 'noticeList' }">목록으로</router-link>
    </div>
  </div>
</template>

<script>
// import axios from 'axios';
import { getNotice, updateNotice } from '@/api/notice.js';
export default {
  name: 'NoticeModify',

  data() {
    return {
      no: 1,
      article: {},
      files: [],
      fileShow: {},
    };
  },

  methods: {
    deleteFile(no) {
      this.$set(this.fileShow, no, false);
    },
    modifyNotice() {
      let body = new FormData();

      body.append('title', this.article.title);
      body.append('contents', this.article.contents);

      let del = [];
      let live = [];
      for (const key in this.fileShow) {
        if (!this.fileShow[key]) del.push(key);
        else live.push(key);
      }

      body.append('delFile', del);
      body.append('liveFile', live);

      let tmp = this.$refs.files.files;
      for (let i = 0; i < tmp.length; i++) {
        body.append('files', tmp[i]);
      }

      updateNotice(this.no, body);
      this.$router.push({ name: 'noticeList' });
    },
  },
  async created() {
    this.no = this.$route.params.no;
    await getNotice(
      this.no,
      ({ data }) => {
        this.article = data;
      },
      ({ error }) => {
        console.dir(error);
      }
    );

    this.fileShow = {};
    if (this.article.fileInfo !== undefined) {
      for (let i = 0; i < this.article.fileInfo.length; i++) {
        this.$set(this.fileShow, this.article.file[i], true);
      }
    }
    console.dir(this.fileShow);
  },
};
</script>
