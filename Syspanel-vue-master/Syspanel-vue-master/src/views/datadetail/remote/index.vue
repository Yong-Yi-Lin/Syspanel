<template>
  <div class="remote-main">
    <el-row :gutter="20">
      <el-col :span="23">
        <el-card shadow="hover" style="width: 100%; margin-left: 25px; margin-top: 20px;" class="router_card">
          <el-radio-group v-model="radio1">
            <el-radio-button label="配置"></el-radio-button>
            <el-radio-button label="运行"></el-radio-button>
          </el-radio-group>
        </el-card>

        <div shadow="hover" style="width: 100%; margin-top: 20px; margin-bottom: 40px; margin-left: 25px;">
          <component :is="currentComponent" />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Set from './set/index.vue';
import Code from './code/index.vue';

export default {
  data() {
    return {
      radio1: '配置', 
      currentComponent: 'Set', 
    };
  },
  components: {
    Set,
    Code,
  },
  watch: {
    radio1(newVal) {
      if (newVal === '配置') {
        this.currentComponent = 'Set';
      } else if (newVal === '运行') {
        this.currentComponent = 'Code';
      }
    },
  },
};
</script>

<style >
.el-radio-button {
  background-color: #ffffff;
  color: #000000; /* 文字颜色 */
  border-radius: 2px;
}

.el-radio-button:hover {
  background-color: #ffffff; /* 鼠标悬停时背景颜色 */
}

.el-radio-button__inner {
  width: 120px;
  border-radius: 2px; /* 添加圆角 */
  background-color: #ffffff;/* 背景颜色 */
  color: #000000; /* 文字颜色 */
  border: #ffffff 2px solid;

}

.el-radio-button__inner:hover {
  background-color: #ffffff;/* 鼠标悬停时背景颜色 */
  color: #1e90ff;
}

.el-radio-button__orig-radio:checked + .el-radio-button__inner {
  color: #1e90ff;
  background-color: #ffffff;/* 背景颜色 */
  border: #1e90ff 2px solid;
}
.router_card .el-card__body {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0;
}
</style>