<template>
  <li>
    <div class="col-12 comment_style_0_list_title">
      <span>{{ writerName }}</span>
      <span>({{ registDate }})</span>
      <a
        v-if="userNo == NowReComment.userNo"
        href="javascript:void(0)"
        @click="userDeleteComment(commentNo, -1)"
        class="delete_icon"
        >삭제 버튼</a
      >
    </div>
    <div class="col-12 comment_style_0_list_desc">
      {{ contents }}
    </div>
    <div class="col-12 comment_style_0_list_util_con">
      <a @click="changeRecommentView()">{{ recommentsCnt }}개</a>
      <a @click="changeWriteRecomment()" v-if="isLogin">답글</a>
    </div>
    <div v-if="this.writeRecommentShow" class="col-12 mw-400 pl33 comment_style_0_input_con">
      <div class="col-12 input_box">
        <input
          v-model="NowReComment.contents"
          @keydown.enter="userWriteComment()"
          class="input_style_0 type_2"
        />
        <button @click="userWriteComment()" class="btn_style_0 type_2">답글</button>
      </div>
    </div>
    <ul v-if="this.recommentShow" class="col-12 comment_style_0_list_con">
      <li v-for="recom in recomments" :key="recom.commentNo">
        <div class="col-12 comment_style_0_list_title">
          <span>{{ recom.writerName }}</span>
          <span>({{ recom.registDate }})</span>
          <a
            v-if="recom.userNo == NowReComment.userNo"
            href="javascript:void(0)"
            @click="userDeleteComment(recom.commentNo, recom.recomment)"
            class="delete_icon"
            >삭제 버튼</a
          >
        </div>
        <div class="col-12 comment_style_0_list_desc">
          {{ recom.contents }}
        </div>
      </li>
    </ul>
  </li>
</template>

<script>
import { writeComment, deleteComment } from '@/api/notice';
import { mapState } from 'vuex';

const userStore = 'userStore';

export default {
  name: 'NoticeCommentListItem',
  props: {
    noticeNo: Number,
    commentNo: Number,
    userNo: Number,
    writerName: String,
    registDate: String,
    contents: String,
    recomments: Array,
    recommentsCnt: Number,
  },
  data() {
    return {
      recommentShow: false,
      writeRecommentShow: false,
      NowReComment: {
        contents: '',
        userNo: -1,
        noticeNo: this.noticeNo,
        recomment: this.commentNo,
      },
    };
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'userInfo']),
  },
  methods: {
    async userWriteComment() {
      await writeComment(
        this.NowReComment,
        ({ data }) => {
          console.dir(data);
          this.NowReComment.contents = '';
          this.$emit('emitUpdateReComment');
        },
        (error) => {
          console.dir(error);
          alert('에러가 발생했습니다. 관리자에 문의해주세요');
        }
      );
    },
    changeRecommentView() {
      this.recommentShow = !this.recommentShow;
    },
    changeWriteRecomment() {
      this.writeRecommentShow = !this.writeRecommentShow;
      this.NowReComment.contents = '';
    },
    async userDeleteComment(no, recommentNo) {
      let param = { commentNo: no, recomment: recommentNo };
      console.dir(param);
      await deleteComment(
        param,
        ({ data }) => {
          console.dir(data);
          this.$emit('emitUpdateReComment');
        },
        (error) => {
          console.dir(error);
          alert('에러가 발생했습니다. 관리자에 문의해주세요');
        }
      );
    },
  },
  created() {
    //로그인 되어있는 경우
    if (this.isLogin) {
      this.NowReComment.userNo = this.userInfo.no;
    }
  },
};
</script>
