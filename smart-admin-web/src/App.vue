
<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script type="text/javascript" src="http://map.qq.com/api/js?v=2.exp&key=L6ABZ-BIM65-36MIA-Q4CG5-N74OH-S7F46"></script>
<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script>
<script>
export default {
  name: 'App',
  created() {}
};
</script>

<style lang="less">
@import './main.less';
.size {
  width: 100%;
  height: 100%;
}
html,
body {
  .size;
  overflow: hidden;
  margin: 0;
  padding: 0;
}
#app {
  .size;
}
</style>
