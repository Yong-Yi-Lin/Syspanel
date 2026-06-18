
<template>
  <div>
    <el-card class="log-main">
      <div slot="header">
        <div style="display: flex; align-items: center;">
          <div style="width: 5px; height: 15px; background-color: #007bff; border-radius: 5px; margin-right: 8px;"></div>
          <span style="font-weight: bold; font-size: 32px;">用户反馈</span>
        </div>
      </div>
      <el-form ref="form" :model="form" label-width="100px" style="margin-top: 10px; width: 80%; " label-position="top">
        <el-form-item label="用户名（可匿名）">
          <el-input style="width: 350px;" v-model="form.username" :maxlength="10" show-word-limit>></el-input>
        </el-form-item>
        <el-form-item label="反馈时间" label-position="left">
          <el-col :span="11">
            <el-date-picker v-model="form.time" type="datetime" placeholder="选择日期时间" :picker-options="pickerOptions">
            </el-date-picker>
          </el-col>
        </el-form-item>


        <el-form-item label="对Syspanel的更新频率是否满意？" label-width="250px">
          <el-checkbox-group v-model="form.satisfaction">
            <el-checkbox label="满意" name="satisfaction"></el-checkbox>
            <el-checkbox label="还行" name="satisfaction"></el-checkbox>
            <el-checkbox label="不太满意" name="satisfaction"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-form-item label="反馈类型">
          <el-checkbox-group v-model="form.type">
            <el-checkbox label="系统" name="type"></el-checkbox>
            <el-checkbox label="体验感" name="type"></el-checkbox>
            <el-checkbox label="其他" name="type"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-form-item label="内容">
          <el-input type="textarea" v-model="form.info" :maxlength="800" show-word-limit>
            ></el-input>
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
// import axios from 'axios'; 
import { addUserLog } from '../../../api/Res/post'
export default {
  data() {
    return {
      form: {
        username: '',
        // region: '',
        time: '',
        // date2: '',
        delivery: false,
        type: [],
        satisfaction: [],
        // resource: '',
        info: ''
      },
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
      value1: '',
      value2: '',
      value3: ''
    };
  },
  methods: {

    onSubmit() {
      this.$confirm('是否确实提交该反馈', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        //   alert(this.form.satisfaction[0]);
        console.log(this.form);
        if (this.form.satisfaction) {
          this.form.satisfaction = this.form.satisfaction[0];
        }
        if (this.form.type) {
          this.form.type = this.form.type[0];
        }
        // if(this.form.name)
        addUserLog(this.form).then((response) => {
          if (response.status === 200 || response.data.success) {
            // console.log("查询的结果是"+response.data.message);
            this.$message.success("提交成功！");
            this.form. username='',
              this.form.time='',
              this.form.type= [],
              this.form.satisfaction= [],
              this.form.info= ''
          } else if (response.data.error) {
            this.$message.error(response.data.message);
          }
        })
        // 这里可以添加提交表单的逻辑
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    resetForm() {
      // 清空内容

      this.$refs.form.resetFields();

      this.$message({
        type: 'success',
        message: '已清除'
      });
       this.form. username='',
              this.form.time='',
              this.form.type= [],
              this.form.satisfaction= [],
              this.form.info= ''
            
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
  /* 阴影效果 */
}
</style>
