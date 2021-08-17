<template>
  <div id="container">
  </div>
</template>
<script>
/* eslint-disable */
import { TMap, getLocation, getDescribe } from "@/router/module/support/TMap"; 

export default {
  name: "mapChild",
  data() {
    return {};
  },

  created() {
    let _this = this;
    TMap().then(qq => {
      //初始化中心点:经纬度及地图缩放大小，传入想要设置的值（龙光君御华府：108.380849, 22.821485）
      this.mapInit(108.380849, 22.821485, 16); 
    });
  },

  methods: {

    //父组件调用该函数，设置地点
    setLoc(lng, lat) {
      this.mapInit(lng, lat, 16);
    },

    //搜索某一地点名
    getLoc(ele) {
      getLocation({
          address: ele,
          region: '南宁'
        })
        .then(res => {
          // debugger
          let searchObj = res.data.data;
          searchObj.search = 1;
          // 子组件向父组件传值
          this.$emit("getLocationParam", searchObj);
          let _this = this;
          let resultData = res.data.data.data[0];
          if (res.data.data.status == 0) {
            this.mapInit(resultData.location.lng, resultData.location.lat, 16);
          }
        })
        .catch(error => {
          console.log(error);
        });
    },

    //根据传入的值渲染地图及传出经纬度和地名
    mapInit(lng,lat,zoom) {
      let _this = this
      var map = new qq.maps.Map(document.getElementById("container"), {
        // 地图的中心地理坐标。
        center: new qq.maps.LatLng(
          lat,
          lng
        ),
        zoom: zoom
      });
      var marker = new qq.maps.Marker({
        position: new qq.maps.LatLng(
          lat,
          lng
        ),
        map: map
      });
      qq.maps.event.addListener(map, "click", function(event) {
        marker.setMap(null);
      });
      qq.maps.event.addListener(map, "click", function(event) {
        let result = {
          status: 0,
          result: {
            location: {
              lng: event.latLng.getLng(),
              lat: event.latLng.getLat()
            }
          }
        };
        qq.maps.event.addListener(map, "click", function(event) {
          marker.setMap(null);
        });
        var marker = new qq.maps.Marker({
          position: event.latLng,
          map: map
        });

        _this
          getDescribe({
            location: event.latLng.getLat() + "," + event.latLng.getLng()
          })
          .then(res => {
            res.data.data.extra = 1;
            // 子组件向父组件传值
            _this.$emit("getLocationParam", res.data.data);
          })
          .catch(err => {
            this.$message({
              type: 'warning',
              message: '定位解析失败'
            })
          })
      });
    }
  },
};
</script>
<style>
#container {
  min-width: 600px;
  min-height: 400px;
}
</style>