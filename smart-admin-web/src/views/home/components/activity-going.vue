/* 签到管理*/ 
<template>
  <!-- Row 签到管理 start -->
  <Row :gutter="10">
    <!-- Col 左侧活动列表模块 start -->
    <Col :lg="5" :md="8">
      <Card class="warp-card" dis-hover style="position: relative">
        <div class="card-title" slot="title">进行中活动</div>
        <!-- Menu 活动列表 start -->
        <Menu
          :active-name="0"
          class="left role-list no-scrollbar"
          ref="sideMenu"
          style="height: 400px; overflow-y: scroll; width: 100%"
        >
          <!-- MenuItem 活动列表项 start -->
          <MenuItem
            :key="item.id"
            :name="index"
            @click.native="selectActivity(item, index)"
            v-for="(item, index) in activityList"
          >
            <Row>
              <Col span="24">
                <span class="role-name">
                  <Tooltip placement="right">
                    <span>{{ item.name }}</span>
                  </Tooltip>
                </span>
              </Col>
            </Row>
          </MenuItem>
          <!-- MenuItem 活动列表项 end -->
        </Menu>
        <!-- Menu 活动列表 end -->
      </Card>
    </Col>
    <!-- Col 左侧活动列表模块 end -->
    <!-- Col 功能列表 start -->
    <Col :lg="19" :md="16">
      <Card class="warp-card" dis-hover>
        <div class="card-title" slot="title">人员列表</div>
        <!--成员管理-->
        <SignList :activityId="activityId" v-if="displayTab == 3"></SignList>
      </Card>
    </Col>
    <!-- Col 功能列表 end -->
  </Row>
  <!-- Row 角色管理 end -->
</template>
<script>
import SignList from "./activity/sign-list";
import { activityApi } from "@/api/activity";

export default {
  name: "RoleManage",
  components: {
    SignList,
  },
  props: {},
  data() {
    return {
      activityList: {},
      activityId: 0,
      // 增加角色信息
      ruleDetail: {
        name: "",
        id: "",
        detail: "",
      },
      // 默认选中Menu标签为功能权限
      displayTab: 3,
      // 是否第一次请求数据
      isFirst: true,
    };
  },
  computed: {},
  watch: {},
  filters: {},
  created() {},
  mounted() {
    // 初始化加载数据
    this.getGoingActivities();
  },
  beforeCreate() {},
  beforeMount() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  activated() {},
  methods: {
    // 初始化加载数据方法
    async getGoingActivities() {
      try {
        let response = await activityApi.getGoingActivity();
        console.log(response);
        this.activityList = response["ongoingActivities"];
        console.log(this.activityList);
        if (this.activityList && this.activityList.length > 0) {
          this.activityId = this.activityList[0].id;
          if (this.isFirst) {
            this.$nextTick(() => {
              this.$refs.sideMenu.updateOpened();
              this.$refs.sideMenu.updateActiveName();
            });
            this.isFirst = false;
          }
        }
      } catch (e) {
        //TODO zhuoda sentry
        console.error(e);
      }
    },
    // 功能选择
    selectTab(position) {
      this.displayTab = position;
    },
    selectActivity(item, index) {
      this.activityId = item.id;
    },
  },
};
</script>
<style lang="less" scoped>
.role-list {
  line-height: 30px;
  padding: 10px 0;

  .role-name {
    position: relative;
  }

  &::after {
    display: none;
  }

  button {
    margin-left: 3px;
  }
}

.ivu-menu-item-active:not(.ivu-menu-submenu) {
  z-index: 0 !important;
}

.suspension-box {
  z-index: 999;
  padding: 0 8px;

  p {
    padding: 3px 0;
  }
}
</style>
