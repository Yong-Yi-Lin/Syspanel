<template>
  <div>
    <el-card class="log-main">
      <div slot="header">
        <div style="display: flex; align-items: center;">
          <div style="width: 5px; height: 15px; background-color: #007bff; border-radius: 5px; margin-right: 8px;"></div>
          <span style="font-weight: bold; font-size: 32px;">日志</span>
        </div>
      </div>
      <el-form ref="form" :model="form" label-width="100px" style="margin-top: 10px; width: 80%;" label-position="top">

        <el-form-item label="日志标题" class="log-form-item" :error="titleError" prop="name">
          <el-input v-model="form.title" style="width: 350px;" :maxlength="20" show-word-limit></el-input>
          <div slot="error" class="error">{{ titleErrorMsg }}</div>
        </el-form-item>

        <el-form-item label="日志时间" class="log-form-item">
          <el-col :span="11">
            <el-date-picker v-model="form.createdAt" type="datetime" placeholder="选择日期时间"
              :picker-options="pickerOptions"></el-date-picker>
          </el-col>
        </el-form-item>

        <el-form-item label="日志信息" class="log-form-item">
          <el-checkbox-group v-model="form.content" @change="handleCheckboxChange">
            <el-checkbox label="巡检"></el-checkbox>
            <el-checkbox label="值班"></el-checkbox>
            <el-checkbox label="故障"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-form-item label="日志内容" class="log-form-item" :error="contentError" prop="desc">
          <el-input type="textarea" v-model="form.logInfo" :maxlength="800" show-word-limit></el-input>
          <div slot="error" class="error">{{ contentErrorMsg }}</div>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button @click="resetForm">清空</el-button>
        </el-form-item>

      </el-form>
    </el-card>
  </div>
</template>

<script>
import { addLog } from '../../../api/Res/post'
export default {
  data() {
    return {
      form: {
        title: '',
        createdAt: '',
        content: [],
        logInfo: ''
      },
      titleError: '', // Initialize with empty string
      contentError: '', // Initialize with empty string
      pickerOptions: {
        shortcuts: [
          { text: '今天', onClick: picker => { picker.$emit('pick', new Date()); } },
          { text: '昨天', onClick: picker => { let date = new Date(); date.setTime(date.getTime() - 3600 * 1000 * 24); picker.$emit('pick', date); } },
          { text: '一周前', onClick: picker => { let date = new Date(); date.setTime(date.getTime() - 3600 * 1000 * 24 * 7); picker.$emit('pick', date); } }
        ]
      },
      titleErrorMsg: '',
      contentErrorMsg: ''
    };
  },
  methods: {
    handleCheckboxChange(value) {
      if (value.length > 1) {
        this.$message.warning('只能选择一个日志信息');
        this.form.content = [value.pop()];
      }
    },
    validateTitle() {
      if (this.form.title.length < 1) {
        this.titleError = '日志标题必须填写';
        this.$alert('日志标题必须填写')
      } else {
        this.titleError = '';
      }
    },
    validateContent() {
      const contentLength = this.form.logInfo.length;
      if (contentLength < 10) {
        this.contentError = '日志内容至少需要10个字';
        this.$alert('日志内容至少需要10个字')
      } else if (contentLength > 800) {
        this.contentError = '日志内容不能超过800个字';
      } else {
        this.contentError = '';
      }
    },
    onSubmit() {
      this.validateTitle();
      console.log(this.form);
      this.validateContent();
      if (!this.titleError && !this.contentError) {
        this.$confirm('是否确实提交该日志内容', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let date = new Date(this.form.createdAt);

          // 获取年、月、日、时、分、秒  
          let year = date.getFullYear();
          let month = String(date.getMonth() + 1).padStart(2, '0'); // 月份是从0开始的，所以要加1  
          let day = String(date.getDate()).padStart(2, '0');
          let hours = String(date.getHours()).padStart(2, '0');
          let minutes = String(date.getMinutes()).padStart(2, '0');
          let seconds = String(date.getSeconds()).padStart(2, '0');


          // 构建并返回目标格式的字符串  
          let newDate = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
          this.form.createdAt = newDate;
          this.form.content = this.form.content[0];
          console.log(this.form);

          // addLog(this.form);
          addLog(this.form).then(response => {
            console.log(response);
            if (response.data.code === 2000) {
              // alert(response.data);
              // alert("登记成功！");
              this.$message({
                type: 'success',
                message: '提交成功!'
              });
             
              this.form. title='',
              this.form.createdAt='',
              this.form.content= [],
              this.form.logInfo= ''
            } else if (response.data.message === '登记失败') {
              // alert("登记失败，请稍后重试");
              this.$message({
                type: 'success',
                message: '登记失败，请稍后重试!'
              });
            }
          })

          // console.log('submit!');
          // this.$message({
          //   type: 'success',
          //   message: '提交成功!'
          // });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
      }
    },
    resetForm() {
      this.$refs.form.resetFields();
      this.$message({
        type: 'success',
        message: '已清除'
      });
      this.form. title='',
              this.form.createdAt='',
              this.form.content= [],
              this.form.logInfo= ''
    }
  }
};
</script>

<style scoped>
.log-main {
  margin-top: 20px;
  padding-top: 15px;
  margin-left: 20px;
  margin-bottom: 80px;
  width: 95%;
  border: 1px solid #cdcdcd;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.log-form-item {
  margin-bottom: 15px;
}
</style>