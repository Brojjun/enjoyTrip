<template>
  <tr>
    <td>{{ no }}</td>
    <td class="tl title">
      <router-link :to="'/notice/view/' + no">{{ title }}</router-link>
    </td>
    <td>{{ writerName }}</td>
    <td>{{ hits }}</td>
    <td>{{ likeCnt }}</td>
    <td>{{ registDateStr }}</td>
  </tr>
</template>

<script>
export default {
  name: 'NoticeListItem',
  props: {
    no: Number,
    title: String,
    writerName: String,
    hits: Number,
    likeCnt: Number,
    registDateStr: String,
  },
};
</script>

<style lang="scss" scoped></style>
