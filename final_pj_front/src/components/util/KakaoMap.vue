<template>
  <div class="col-12" id="map"></div>
</template>

<script>
export default {
  name: 'KakaoMap',
  props: {
    markers: Array,
  },
  data() {
    return {
      map: null,
      positions: this.markers,
    };
  },
  watch: {
    markers() {
      this.positions = this.markers;
    },
  },
  setup() {},
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  unmounted() {},
  methods: {
    loadScript() {
      const script = document.createElement('script');
      script.src =
        '//dapi.kakao.com/v2/maps/sdk.js?appkey=fda225afafa599b8b194328b462a7ab2&autoload=false';
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    loadMap() {
      const container = document.getElementById('map');
      const options = {
        center: new window.kakao.maps.LatLng(
          this.positions[0].latitude,
          this.positions[0].longitude
        ),
        level: 5,
      };

      this.map = new window.kakao.maps.Map(container, options);

      this.loadMakers();
    },
    loadMakers() {
      let imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';
      let imageSize = new window.kakao.maps.Size(24, 35);
      let markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);

      for (let i = 0; i < this.positions.length; i++) {
        const markerPos = new window.kakao.maps.LatLng(
          this.positions[i].latitude,
          this.positions[i].longitude
        );

        new window.kakao.maps.Marker({
          map: this.map,
          position: markerPos,
          image: markerImage,
        });
      }

      if (this.markers.length > 1) {
        this.loadLine();
      }
    },
    loadLine() {
      for (let i = 1; i < this.positions.length; i++) {
        let linePath = [
          new window.kakao.maps.LatLng(
            this.positions[i - 1].latitude,
            this.positions[i - 1].longitude
          ),
          new window.kakao.maps.LatLng(this.positions[i].latitude, this.positions[i].longitude),
        ];

        let lineLine = new window.kakao.maps.Polyline({
          map: this.map,
          path: linePath,
          strokeWeight: 3,
          strokeColor: '#db4040',
          strokeOpacity: 1,
          strokeStyle: 'solid',
        });

        let distance = Math.round(lineLine.getLength());
        this.displayCircleDot(
          new window.kakao.maps.LatLng(this.positions[i].latitude, this.positions[i].longitude),
          distance
        );
      }
    },
    displayCircleDot(position, distance) {
      if (distance > 0) {
        const distanceOverlay = new window.kakao.maps.CustomOverlay({
          position: position,
          yAnchor: 1,
          zIndex: 2,
        });
        distanceOverlay.setMap(this.map);
      }
    },
  },
};
</script>

<style scoped>
#map {
  height: 100%;
}
</style>
