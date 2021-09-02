<template>
  <!--div tab切换成员列表部分 start-->
  <div>
    <!--Table 表格列表 start-->
    <Table
      :columns="columns"
      :data="tableData"
      :loading="isShowTablesLoading"
      @on-selection-change="selectChange"
      border
      ref="selection"
    ></Table>
    <!--Table 表格列表 end-->
    <Row class="page" justify="end" type="flex">
      <Col order="2" span="24" style="text-align: right; margin-top: 20px">
        <Page
          :current="currentPage"
          :page-size="pageSize"
          :total="total"
          @on-change="changePage"
          show-elevator
        ></Page>
      </Col>
    </Row>
  </div>
  <!--div tab切换成员列表部分 end-->
</template>
<script>
import { roleApi } from "@/api/role";
import { activityApi } from "@/api/activity";

export default {
  name: "RoleList",
  components: {},
  props: {
    // 活动id
    activityId: {
      type: Number,
      required: true,
      validator: (value) => {
        return value >= 0;
      },
    },
  },
  // 数据
  data() {
    return {
      currentPage: 1,
      isShowTablesLoading: false,
      columns: [
        {
          type: "selection",
          width: 60,
          align: "center",
        },
        {
          title: "姓名",
          key: "name",
        },
        {
          title: "手机号",
          key: "phone",
        },
        {
          title: "签到时间",
          key: "signTime",
        },
      ],
      signData: {
        // orderby:'',
        id: "",
        pageNum: 1,
        pageSize: 10,
        sort: "",
      },
      // 表格数据
      tableData: [],
      total: 0,
      pageSize: 0,
    };
  },
  computed: {},
  watch: {
    activityId(val) {
      if (val) {
        this.signData.id = this.activityId;
        this.getUsersByActivityId(this.signData);
      }
    },
  },
  filters: {},
  created() {},
  mounted() {
    this.signData.id = this.activityId;
    this.getUsersByActivityId(this.signData);
  },
  beforeCreate() {},
  beforeMount() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  activated() {},
  methods: {
    // 分页改变获取数据方法
    // 分页器
    changePage(number) {
      console.log(number);
      let object = {};
      object.id = this.activityId;
      this.currentPage = number;
      object.pageNum = number;
      console.log(this.pageSize);
      object.pageSize = this.pageSize;// todo z
      object.sort = "";
      this.getUsersByActivityId(object);
    },
    // 获取活动id对应的签到人员列表方法
    async getUsersByActivityId(param) {
      this.isShowTablesLoading = true;
      try {
        let response = await activityApi.getUsersByActivityId(param);
        console.log(response);
        this.roleList = response.data;
        this.total = response.data.total;
        this.pageSize = response.data.pageSize;
        this.tableData = this.roleList.records;
      } catch (e) {
        //TODO zhuoda sentry
        console.error(e);
      } finally {
        this.isShowTablesLoading = false;
      }
    },

    // 获取角色id对应的成员列表方法
    // async getListEmployee(param) {
    //   this.isShowTablesLoading = true;
    //   try {
    //     let response = await roleApi.getListEmployee(param);
    //     console.log(response);
    //     this.roleList = response.data;
    //     this.total = response.data.total;
    //     this.pageSize = response.data.pageSize;
    //     this.tableData = this.roleList.list;
    //     await this.getListAllEmployee();
    //   } catch (e) {
    //     //TODO zhuoda sentry
    //     console.error(e);
    //   } finally {
    //     this.isShowTablesLoading = false;
    //   }
    // },
    // 获取角色id对应的全部成员列表方法
    // async getListAllEmployee() {
    //   this.$isShowTablesLoading = true;
    //   try {
    //     let response = await roleApi.getAllListEmployee(this.roleId);
    //     let list = response.data;
    //     this.prepAddEmployees = [];
    //     for (let i = 0; i < list.length; i++) {
    //       let object = {};
    //       object.manageName = list[i].actualName;
    //       object.manageId = list[i].id;
    //       this.prepAddEmployees.push(object);
    //     }
    //   } catch (e) {
    //     //TODO zhuoda sentry
    //     console.error(e);
    //   } finally {
    //     this.isShowTablesLoading = false;
    //   }
    // },
    // 选项框多选移除
    selectChange(selection) {
      this.deleteIds = [];
      for (let i = 0; i < selection.length; i++) {
        this.deleteIds.push(selection[i].id);
      }
      console.log(this.deleteIds);
    },
  },
};
</script>

<style lang="less" scoped >
.shuttle-box {
  position: relative;
  .box {
    border: 1px solid #f0f0f0;
    border-radius: 10px;
    height: 330px;
  }

  .title {
    padding: 10px 0;
    background: #426783;
    font-size: 14px;
    color: #fff;
    text-align: center;
  }
}
</style>
