<template>
  <div class="col-12 mb30 comment_style_0_con">
    <!-- 댓글 입력 -->
    <div v-if="isLogin" class="col-12 mw-400 comment_style_0_input_con">
      <div class="col-12 mb7 comment_style_0_writer_name">{{ userName }}</div>
      <div class="col-12 input_box">
        <input
          id="subject"
          v-model="Nowcomment.contents"
          placeholder="내용을 입력해주세요"
          @keydown.enter="userWriteComment"
          class="input_style_0 type_2"
        />
        <button @click="userWriteComment" class="btn_style_0 type_2">댓글</button>
      </div>
    </div>
    <!-- 댓글 보기 -->

    <ul class="col-12 comment_style_0_list_con">
      <notice-comment-list-item
        v-for="comment in comments"
        :key="comment.commentNo"
        v-bind="comment"
        @emitUpdateReComment="userGetCommentList"
      ></notice-comment-list-item>
    </ul>
  </div>
</template>

<script>
import NoticeCommentListItem from './NoticeCommentListItem.vue';
import { getCommentList, writeComment } from '@/api/notice';
import { mapState } from 'vuex';

export default {
  name: 'NoticeComment',
  components: {
    NoticeCommentListItem,
  },
  props: {
    noticeNo: { type: Number, required: true },
  },
  data() {
    return {
      comments: [],
      Nowcomment: {
        contents: '',
        userNo: -1,
        noticeNo: this.noticeNo,
        recomment: -1,
      },
      userName: '',
    };
  },
  computed: {
    ...mapState('userStore', ['isLogin', 'userInfo']),
  },
  methods: {
    async userGetCommentList() {
      await getCommentList(
        this.noticeNo,
        ({ data }) => {
          this.comments = [];
          for (let i = 0; i < data.length; i++) {
            if (data[i].recomment == -1) {
              data[i].noticeNo = this.noticeNo;
              data[i].recomments = [];
              data[i].recommentsCnt = 0;
              this.comments.push(data[i]); //댓글인 경우
            } else {
              const recomment = this.comments.find(
                (comment) => comment.commentNo === data[i].recomment
              );

              recomment.recomments.push(data[i]);
              recomment.recommentsCnt++;
            }
          }
        },
        (error) => {
          console.log(error);
          alert('에러로 실패했습니다. 관리자에 문의해주세요.');
        }
      );
    },
    async userWriteComment() {
      await writeComment(
        this.Nowcomment,
        ({ data }) => {
          console.dir(data);
          this.Nowcomment.contents = '';
          this.userGetCommentList();
        },
        (error) => {
          console.dir(error);
          alert('에러가 발생했습니다. 관리자에 문의해주세요');
        }
      );
    },
    deleteComment() {
      //delNum
      //axios.put('http://192.168.212.61/api/noticeCom ment/delete/' + delNum);
      this.display();
    },
  },
  created() {
    this.userGetCommentList();
    if (this.isLogin) {
      this.Nowcomment.userNo = this.userInfo.no;
      this.userName = this.userInfo.name;
      console.log(this.Nowcomment.userNo);
    }
  },
};
</script>
