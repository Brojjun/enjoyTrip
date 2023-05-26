<template>
  <nav class="col-12">
    <ul class="col-12 gnb_con">
      <li>
        <router-link :to="{ name: 'Route' }">플래너</router-link>
      </li>
      <li>
        <router-link :to="{ name: 'Attraction' }">여행지 검색</router-link>
      </li>
      <li>
        <router-link :to="{ name: 'noticeList' }">공지사항</router-link>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'TheHeaderGnb',
};
</script>
