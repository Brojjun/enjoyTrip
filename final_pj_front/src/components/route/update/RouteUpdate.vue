<template>
  <div class="col-12">
    <div class="col-12 col-center mw-1230 pl30 pr30 route_view_wrap">
      <div class="col-12 route_view_title_con">
        <div class="col-0 route_view_title">
          <span>{{ routeView.title }}</span>
        </div>
      </div>
      <div class="col-12 mb27 route_info_con">조회수 : {{ routeView.hits }}</div>
      <div class="col-12 mb37 route_view_contents_con">
        <div class="col-12 mb30 flex-as">
          <div class="col-12 col-md-8 mb20 mb-md-0 map_box pr-md-20">
            <kakao-map
              :markers="routeView.orderInfo"
              :key="routeView.orderInfo[0].latitude"
            ></kakao-map>
          </div>
          <ul class="col-12 col-md-4 route_order_info_list_con">
            <li v-for="(order, index) in routeView.orderInfo" :key="index + '1'">
              <div class="col-4 img_box">
                <img :src="order.firstImage" alt="로고" />
              </div>
              <div class="col-8 text_box">
                <div class="col-12 route_order_info_list_title">
                  {{ index + 1 }}. {{ order.title }}
                </div>
                <ul class="col-12 route_order_desc">
                  <li v-switch="order.contentTypeId">
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
                    <span>({{ order.zipCode }}) {{ order.addr1 }} {{ order.addr2 }}</span>
                  </li>
                </ul>
              </div>
            </li>
          </ul>
        </div>
        <ul class="col-12 attraction_view_contents">
          <li>
            <span class="label_box">작성자</span>
            <span>{{ routeView.writerName }}</span>
          </li>
          <li>
            <span class="label_box">설명</span>
            <span>{{ routeView.contents }}</span>
          </li>
        </ul>
      </div>
      <div class="col-12 tc btn_style_1_con">
        <router-link :to="{ name: 'RouteList' }" class="btn_style_1">목록으로</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { updateHits, getView } from '@/api/route';
import KakaoMap from '@/components/util/KakaoMap.vue';

export default {
  components: { KakaoMap },
  name: 'RouteView',
  data() {
    return {
      routeView: {
        orderInfo: [
          {
            latitude: null,
            longitude: null,
          },
        ],
      },
    };
  },
  methods: {
    async userGetView(no) {
      let visitedList = JSON.parse(sessionStorage.getItem('visitedRoute'));

      //방문한적이 없는 attraction인 경우
      if (
        visitedList == null ||
        visitedList == '[]' ||
        visitedList == '' ||
        !visitedList.includes(this.$route.params.no)
      ) {
        await updateHits(
          no,
          ({ data }) => {
            if (data) {
              visitedList =
                sessionStorage.getItem('visitedRoute') != null
                  ? JSON.parse(sessionStorage.getItem('visitedRoute'))
                  : [];

              visitedList.push(no);
              sessionStorage.setItem('visitedRoute', JSON.stringify(visitedList));
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
        no,
        ({ data }) => {
          if (data.result) {
            this.routeView = data.data;
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
  },
  created() {
    this.userGetView(this.$route.params.no);
  },
};
</script>
