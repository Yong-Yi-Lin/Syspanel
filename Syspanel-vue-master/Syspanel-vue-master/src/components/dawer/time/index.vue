<template>
       <el-drawer
      title="设置监控时间"
      :visible.sync="timeVisible"
      :modal-append-to-body="false"
      :wrapperClosable="false"
      :withHeader="true"
    >
      <el-form ref="drawerForm" :model="drawerForm" label-width="120px">
        <el-form-item label="时间" prop="time">
          <el-input placeholder="请输入要设置的时间范围" v-model="drawerForm.time"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveTime">保存</el-button>
          <el-button @click="closeDrawer">取消</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
</template>
<script>
export default {
  data() {
    return {
      input1: '', // 输入框模型
      timeVisible: false, // 控制抽屉组件的显示与隐藏
      form: {
        // 表单数据模型
      },
      drawerForm: {
        time: '' // 抽屉中表单的数据模型
      }
    };
  },
  methods: {
    
    closeDrawer() {
      // 点击取消按钮关闭抽屉
      this.timeVisible = false;
    },
    saveTime() {
      // 保存时间的逻辑，可以根据需要自行扩展
      console.log('保存的时间为:', this.drawerForm.time);
      // 保存后关闭抽屉
      this.timeVisible = false;
    },
    // 其他方法，比如表单验证等
  }
};
</script>