<template>
  <div class="col-12 col-md-3 card_style_0">
    <div @click="moveView(`${contentId}`)" class="col-12 card_style_0_inner">
      <div class="col-12 img_box">
        <img :src="firstImage" :alt="title" />
        <div class="col-12 dim"></div>
        <div
          class="col-0 bookmark_icon"
          @click.stop="updateFavorite(`${contentId}`, `${favorite}`)"
        >
          <img
            :src="
              itemFavorite == 1
                ? this.$resource.commonRes.img('64x64_bookmark_black_full_icon.png')
                : this.$resource.commonRes.img('64x64_bookmark_grey_empty_icon.png')
            "
            alt="북마크 아이콘"
          />
        </div>
      </div>
      <div class="col-12 text_box">
        <div class="col-12 title">{{ title }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

const favoriteStore = 'favoriteStore';
const userStore = 'userStore';

export default {
  name: 'AttractionListItem',
  props: {
    addr1: String,
    addr2: String,
    contentId: Number,
    contentTypeId: Number,
    firstImage: String,
    firstImage2: String,
    gugunCode: Number,
    sidoCode: Number,
    title: String,
    zipCode: String,
    favorite: Number,
  },
  data() {
    return {
      itemFavorite: this.favorite,
    };
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'userInfo']),
  },
  methods: {
    ...mapActions(favoriteStore, ['userUpdateFavorite']),
    updateFavorite(attractionId, flag) {
      if (this.isLogin) {
        let param = { userNo: this.userInfo.no, attractionId: attractionId, flag: flag };
        this.userUpdateFavorite(param);
        this.itemFavorite = Math.abs(this.itemFavorite - 1);
        this.$emit('emitUpdateFavorite');
      } else {
        alert('비로그인 상태에서는 즐겨찾기 상태 변경이 불가능합니다');
      }
    },
    moveView(attractionId) {
      this.$router.push(`/attraction/view/${attractionId}`);
    },
  },
};
</script>
