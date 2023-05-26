<template>
  <div class="col-12">
    <div class="col-12 col-center mw-1230 pl30 pr30">
      <div class="col-12 mb30 flex-as">
        <div class="col-12 col-md-8 mb20 mb-md-0 map_box pr-md-20">
          <kakao-map
            :markers="routeParams.orderInfo"
            :key="routeParams.orderInfo[0].latitude"
          ></kakao-map>
        </div>
        <div class="col-12 col-md-4 route_order_info_insert_con">
          <div class="col-12 attraction_search_bar_con"></div>
          <div class="col-12 card_style_0_con">
            <div class="col-6 card_style_0">
              <div class="col-12 card_style_0_inner">
                <div class="col-12 img_box">
                  <img src="" alt=""/>
                  <div class="col-12 dim"></div>
                </div>
                <div class="col-12 text_box">
                  <div class="col-12 title">타이틀</div>
                  <div class="col-12 info">주소</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 mb40 form_style_0_con">
        <div class="col-12 form_style_0">
          <div class="col-12 col-md-0 label_box">
            <label for="title">제목</label>
          </div>
          <div class="col-12 col-md-0 input_box">
            <div class="col-12 input_style_0_con">
              <input
                type="text"
                class="input_style_0"
                id="title"
                ref="title"
                v-model="routeParams.title"
              />
            </div>
          </div>
        </div>
        <div class="col-12 form_style_0">
          <div class="col-12 col-md-0 label_box">
            <label for="contents">설명</label>
          </div>
          <div class="col-12 col-md-0 input_box">
            <div class="col-12 input_style_0_con">
              <input
                type="text"
                class="input_style_0"
                id="contents"
                ref="contents"
                v-model="routeParams.contents"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 tc btn_style_1_con">
        <a href="javascript:void(0)" @click="userInsert" class="btn_style_0 mr10">등록</a>
        <router-link :to="{ name: 'RouteList' }" class="btn_style_1">목록으로</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { insert } from '@/api/route';
import { mapState } from 'vuex';
import KakaoMap from '@/components/util/KakaoMap.vue';

const userStore = 'userStore';

export default {
  components: { KakaoMap },
  name: 'RouteView',
  data() {
    return {
      routeParams: {
        title: null,
        contents: null,
        orderInfo: [
          {
            latitude: 0,
            longitude: 0,
          },
        ],
      },
    };
  },
  computed: {
    ...mapState(userStore, ['isLogin']),
  },
  methods: {
    async userInsert() {
      await insert(this.routeParams, ({ data }) => {
        if (data) {
          this.$router.push({ name: 'RouteList' });
        }
      });
    },
  },
  created() {},
};
</script>
