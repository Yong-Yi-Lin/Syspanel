<template>
  <div>
    <el-dialog
      title="数据详情"
      :visible.sync="dialogVisible"
      width="750px"
      top="5vh"
    >
      <div class="dialog-main">
        <el-row class="dialog-label">
          <el-col :span="6">创建时间</el-col>
          <el-col :span="6">诈骗时间</el-col>
          <el-col :span="6">预警等级</el-col>
          <el-col :span="6">诈骗类型</el-col>
        </el-row>
        <el-row class="dialog-value">
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
        </el-row>
        <el-row class="dialog-label">
          <el-col :span="6">诈骗域名</el-col>
          <el-col :span="6">诈骗地址所属国家</el-col>
          <el-col :span="6">诈骗网站IP</el-col>
          <el-col :span="6">诈骗网站端口</el-col>
        </el-row>
        <el-row class="dialog-value">
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
        </el-row>
        <el-row class="dialog-label">
          <el-col :span="6">受害人IP</el-col>
          <el-col :span="6">受害人端口</el-col>
          <el-col :span="6">受害人IP归属地</el-col>
          <el-col :span="6">用户名</el-col>
        </el-row>
        <el-row class="dialog-value">
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
        </el-row>
        <el-row class="dialog-label">
          <el-col :span="6">地址</el-col>
          <el-col :span="6">身份证号</el-col>
          <el-col :span="6">身份证号归属地</el-col>
          <el-col :span="6">电话号码</el-col>
        </el-row>
        <el-row class="dialog-value">
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
        </el-row>
        <el-row class="dialog-label">
          <el-col :span="6">电话号归属地</el-col>
          <el-col :span="6">银行卡号</el-col>
          <el-col :span="6">通话时长</el-col>
          <el-col :span="6">诈骗金额</el-col>
        </el-row>
        <el-row class="dialog-value">
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
        </el-row>
        <el-row class="dialog-label">
          <el-col :span="6">消息可信度</el-col>
          <el-col :span="6">推送单位</el-col>
          <el-col :span="6">推送次数</el-col>
        </el-row>
        <el-row class="dialog-value">
          <el-col :span="6">12432412</el-col>
          <el-col :span="6">121212</el-col>
          <el-col :span="6">121212</el-col>
        </el-row>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'DataEdit',
  data() {
    return {
      dialogVisible: false,
    }
  },
  methods: {
    // 显示弹框
    showDialog() {
      this.dialogVisible = true
    },
  },
}
</script>

<style lang="less" scoped>
.dialog-main {
  text-align: center;
}
.dialog-label {
  background-color: #ebebeb;
  line-height: 45px;
}
.dialog-value {
  display: flex;
  align-items: center;
  min-height: 45px;
}
</style>