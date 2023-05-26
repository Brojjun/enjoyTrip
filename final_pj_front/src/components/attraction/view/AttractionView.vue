<template>
  <div class="col-12">
    <div class="col-12 col-center mw-1230 pl30 pr30 attraction_view_wrap">
      <div class="col-12 attraction_view_title_con">
        <div class="col-0 attraction_view_title">
          <span>{{ attractionView.title }}</span>
          <div
            @click="updateFavorite"
            :class="[
              attractionView.favorite == 1 ? 'col-0 favorite_icon active' : 'col-0 favorite_icon',
            ]"
          >
            <img
              :src="this.$resource.commonRes.img('64x64_bookmark_black_full_icon.png')"
              alt="북마크 아이콘"
            />
            <img
              :src="this.$resource.commonRes.img('64x64_bookmark_grey_empty_icon.png')"
              alt="북마크 아이콘"
            />
          </div>
        </div>
      </div>
      <div class="col-12 mb27 attraction_info_con">조회수 : {{ attractionView.hits }}</div>
      <div class="col-12 mb37 attraction_view_contents_con">
        <div class="col-12 mb30 flex-as">
          <div class="col-12 col-md-6 mb20 mb-md-0 map_box">
            <kakao-map
              :markers="[
                { latitude: attractionView.latitude, longitude: attractionView.longitude },
              ]"
              :key="attractionView.latitude"
            ></kakao-map>
          </div>
          <div class="col-12 col-md-6 img_box">
            <img :src="attractionView.firstImage" alt="로고" />
          </div>
        </div>
        <ul class="col-12 attraction_view_contents">
          <li v-switch="attractionView.contentTypeId">
            <span class="label_box">분류</span>
            <span v-case="12">관광지</span>
            <span v-case="14">문화시설</span>
            <span v-case="15">축제공연행사</span>
            <span v-case="25">여행코스</span>
            <span v-case="28">레포츠</span>
            <span v-case="32">숙박</span>
            <span v-case="38">쇼핑</span>
            <span v-case="39">음식점</span>
          </li>
          <li>
            <span class="label_box">주소</span>
            <span
              >({{ attractionView.zipCode }}) {{ attractionView.addr1 }}
              {{ attractionView.addr2 }}</span
            >
          </li>
        </ul>
      </div>
      <div class="col-12 tc btn_style_1_con">
        <router-link :to="{ name: 'AttractionList' }" class="btn_style_1">목록으로</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import { updateHits, getView } from '@/api/attraction';
import KakaoMap from '@/components/util/KakaoMap.vue';

const userStore = 'userStore';
const favoriteStore = 'favoriteStore';

export default {
  components: { KakaoMap },
  name: 'AttractionView',
  data() {
    return {
      attractionView: {},
    };
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'userInfo']),
    ...mapState(favoriteStore, ['favorites']),
  },
  methods: {
    ...mapActions(favoriteStore, ['userGetFavorites', 'userUpdateFavorite']),
    async userGetView(attractionNo) {
      let visitedList = JSON.parse(sessionStorage.getItem('visitedAttraction'));

      //방문한적이 없는 attraction인 경우
      if (
        visitedList == null ||
        visitedList == '[]' ||
        visitedList == '' ||
        !visitedList.includes(this.$route.params.no)
      ) {
        await updateHits(
          attractionNo,
          ({ data }) => {
            if (data) {
              visitedList =
                sessionStorage.getItem('visitedAttraction') != null
                  ? JSON.parse(sessionStorage.getItem('visitedAttraction'))
                  : [];

              visitedList.push(attractionNo);
              sessionStorage.setItem('visitedAttraction', JSON.stringify(visitedList));
              console.log('조회수 업데이트 완료');
            }
          },
          (error) => {
            console.log(error);
            alert('에러로 실패했습니다. 관리자에 문의해주세요.');
          }
        );
      }

      await getView(
        attractionNo,
        ({ data }) => {
          if (data.result) {
            this.attractionView = data.data;
            this.attraction = data.data;
            if(this.favorites.length > 0 && this.favorites.indexOf(this.attraction.contentId) > -1){
              this.attractionView.favorite = 1;
            } else {
              this.attractionView.favorite = 0;
            }
          } else {
            alert('에러로 실패했습니다. 관리자에 문의해주세요.');
          }
        },
        (error) => {
          console.log(error);
          alert('에러로 실패했습니다. 관리자에 문의해주세요.');
        }
      );
    },
    updateFavorite() {
      if (this.isLogin) {
        let param = {
          userNo: this.userInfo.no,
          attractionId: this.attractionView.contentId,
          flag: this.attractionView.favorite,
        };
        this.userUpdateFavorite(param);
        this.$set(this.attraction, 'favorite', Math.abs(this.attraction.favorite - 1));
        if(this.attraction.favorite == 1){
          this.$refs.bookmark.classList.add('active');
        } else {
          this.$refs.bookmark.classList.remove('active');
        }
      } else {
        alert('비로그인 상태에서는 즐겨찾기 상태 변경이 불가능합니다');
      }
    },
  },
  created() {
    this.userGetView(this.$route.params.no);
    if (this.isLogin) {
      this.userGetFavorites(this.userInfo.no);
    }
  },
};
</script>
