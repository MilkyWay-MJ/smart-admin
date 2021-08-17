<template>
  <div>
    <Form ref="form" :rules="formValidate" :label-width="100" :model="form">
      <FormItem label="活动名称" prop="name">
        <Input v-model="form.name" />
      </FormItem>
      <FormItem label="活动地点" prop="location">
        <Input v-model="form.location" style="width: 268px" readonly />
        <Button @click="showMapForm" icon="md-add" type="primary"
          >选择地点</Button
        >
      </FormItem>
      <FormItem label="经度值" prop="longitude" hidden>
        <Input v-model="form.longitude" />
      </FormItem>
      <FormItem label="纬度值" prop="latitude" hidden>
        <Input v-model="form.latitude" />
      </FormItem>
      <FormItem label="开始时间" prop="startTime">
        <DatePicker
          type="datetime"
          format="yyyy-MM-dd HH:mm"
          style="width: 368px"
          @on-change="getStartTime"
          v-model="form.startTime"
        />
      </FormItem>
      <FormItem label="结束时间" prop="endTime">
        <DatePicker
          type="datetime"
          format="yyyy-MM-dd HH:mm"
          style="width: 368px"
          @on-change="getEndTime"
          v-model="form.endTime"
        />
      </FormItem>
      <FormItem label="签到范围(米)" prop="signRadius">
        <InputNumber 
          :min="1" 
          :max="1000000"
          :precision="0"
          style="width: 368px"
          v-model="form.signRadius" 
        />
      </FormItem>
      <FormItem label="签到截止时间" prop="validTime">
        <DatePicker
          type="datetime"
          format="yyyy-MM-dd HH:mm"
          style="width: 368px"
          @on-change="getValidTime"
          v-model="form.validTime"
        />
      </FormItem>
      <FormItem label="活动简介" prop="brief">
        <Input
          :autosize="{ minRows: 2, maxRows: 5 }"
          type="textarea"
          v-model="form.brief"
        />
      </FormItem>
    </Form>
    <Row class="code-row-bg" justify="end" type="flex">
      <Button @click="cancel" style="margin-right: 10px">取消</Button>
      <Button @click="save" type="primary">保存 </Button>
    </Row>

    <!-------地图选点 begin------->
    <Modal title="选择地点" v-model="chooseLocation.show" width="800">
      <Form :label-width="80">
        <FormItem label="地址" prop="location">
          <Input v-model="form.location" style="width: 560px" />
          <Button type="primary" icon="ios-search" @click="searchLocation">搜索</Button>
        </FormItem>
        <Row index="">
          <Col span="12">
            <FormItem label="经度" prop="longitude">
              <Input v-model="form.longitude" style="width: 250px" readonly/>
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem label="纬度" prop="latitude">
              <Input v-model="form.latitude" style="width: 250px" readonly/>
              </FormItem>
          </Col>
        </Row>

        <TencentMap ref="child" v-on:getLocationParam="getLocationParam"/>
      </Form>
    </Modal>
    <!-------地图选点 end------->
  </div>
</template>
<script>
import { activityApi } from "@/api/activity";
import TencentMap from "@/views/support/tencent-map/map";
export default {
  name: "CodeReviewListForm",
  components: {
    TencentMap,
  },
  props: {
    //是否为更新表单
    isUpdate: {
      type: Boolean,
      default: true,
    },
    //更新的表单数据对象
    updateData: {
      type: Object,
    },
  },
  data() {
    return {
      //地图选位置
      chooseLocation: {
        show: false,
        isUpdate: false,
        updateData: null,
      },
      //表单数据
      form: {
        // 活动名称
        name: null,
        // 活动地点
        location: null,
        // 开始时间
        startTime: null,
        // 结束时间
        endTime: null,
        // 签到截止时间
        validTime: null,
        // 签到范围(米)
        signRadius: 500,
        // 活动简介
        brief: null,
        // 经度值
        longitude: null,
        // 纬度值
        latitude: null,
       
      },

      // 接收子组件的传参
      receive:null,

      //表单验证
      formValidate: {
        // 活动名称
        name: [{ required: true, message: "请输入活动名称", trigger: "blur" }],
        // 活动地点
        location: [
          { required: true, message: "请输入活动举办地点", trigger: "blur" },
        ],
      
        // 开始时间
        // startTime:[{ required: true, message: '请输入开始时间', trigger: 'blur' }],
        // // 结束时间
        // endTime:[{ message: '请输入结束时间', trigger: 'blur' }],
        // // 签到截止时间
        // validTime:[{  message: '请输入签到截止时间', trigger: 'blur' }],
        // // 活动简介
        // brief:[{  message: '请输入活动简介', trigger: 'blur' }],
      },
    };
  },
  watch: {
    updateData: function (newValue, oldValue) {
      this.$refs["form"].resetFields();
      if (this.isUpdate) {
        for (let k in this.form) {
          this.$set(this.form, k, newValue[k]);
        }
        this.$set(this.form, "id", newValue["id"]);
      }
    },
    isUpdate: function (newValue, oldValue) {
      if (!newValue) {
        this.resetForm();
        this.$refs["form"].resetFields();
      }
    },
  },

  created() {},
  mounted() {},

  methods: {
    cancel() {
      this.$emit("on-form-close");
    },

    save() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.isUpdate) {
            this.update();
          } else {
            this.add();
          }
        } else {
          this.$Message.error("参数验证错误，请仔细填写表单数据!");
        }
      });
    },

    // 获取开始时间、结束时间、签到截止时间
    getStartTime(time) {
      this.form.startTime = time + ":00";
    },
    getEndTime(time) {
      this.form.endTime = time + ":00";
    },
    getValidTime(time) {
      this.form.validTime = time + ":00";
    },

    // 父组件调用子组件的getLoc（搜索某一地点名）方法
    searchLocation() {
      this.$refs.child.getLoc(this.form.location);
    },

    // 父组件接收子组件的传值
    getLocationParam(val) {
      this.receive = val;
      if (this.receive.search == 1) {
        this.form.latitude = this.receive.data[0].location.lat;
        this.form.longitude = this.receive.data[0].location.lng;  
      } else {
        console.log(this.receive);
        if(this.receive.status == 0) {
          this.form.latitude = this.receive.result.location.lat;
          this.form.longitude = this.receive.result.location.lng; 
          this.form.location = this.receive.result.formatted_addresses.recommend;
        } 
      }
    },

    //显示地图
    showMapForm() {
      this.chooseLocation.isUpdate = false;
      this.chooseLocation.show = true;
    },
    resetForm() {
      this.form = {
        // 活动名称
        name: null,
        // 活动地点
        location: null,
        // 开始时间
        startTime: null,
        // 结束时间
        endTime: null,
        // 签到截止时间
        validTime: null,
        // 签到范围(米)
        signRadius: 500,
        // 活动简介
        brief: null,
        // 经度值
        longitude: null,
        // 纬度值
        latitude: null,
      };
      this.$refs["form"].resetFields();
    },
    async add() {
      this.$Spin.show();

      let res = await activityApi.addActivity(this.form);
      // debugger;
      this.$Message.success(res.msg);
      this.$Spin.hide();
      this.resetForm();
      this.$emit("on-form-close");
    },
    async update() {
      this.$Spin.show();
      let res = await activityApi.updateActivity(this.form);
      this.$Message.success(res.msg);
      this.$Spin.hide();
      this.resetForm();
      this.$emit("on-form-close");
    },
  },
};
</script>