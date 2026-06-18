<template>
  <div class="find clearfix">
    <Headers />
    <div class="find-wrap">
      <el-row type="flex" justify="center">
        <el-form ref="findForm" :model="form" :rules="rules" status-icon label-width="100px" class="find-form">
          <h3>找回密码</h3>
          <hr>
          <el-form-item prop="email" label="邮箱">
            <template #label>
              <span><i class="el-icon-message"></i> 邮箱</span>
            </template>
            <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="verificationCode" label="验证码">
            <template #label>
              <span><i class="el-icon-chat-round"></i> 验证码</span>
            </template>
            <div style="display: flex;">
              <el-input v-model="form.verificationCode" placeholder="请输入验证码"></el-input>
              <el-button size="mini" class="sendcode" @click="toSendmail()">获取验证码</el-button>
            </div>
          </el-form-item>
          <el-form-item prop="newPassword" label="新密码">
            <template #label>
              <span><i class="el-icon-lock"></i> 新密码</span>
            </template>
            <el-input v-model="form.newPassword" show-password placeholder="请输入新密码"></el-input>
          </el-form-item>
          <el-form-item prop="confirmPassword" label="确认密码">
            <template #label>
              <span><i class="el-icon-lock"></i> 确认密码</span>
            </template>
            <el-input v-model="form.confirmPassword" show-password placeholder="请再次输入密码"></el-input>
          </el-form-item>
          <el-form-item class="res-bt">
            <el-button type="primary" icon @click="doFind()">重置密码</el-button>
          </el-form-item>
          <div class="link-group">
            <router-link to="/" class="rightlink">返回登录</router-link>
          </div>
        </el-form>
      </el-row>
    </div>
  </div>
</template>
  
<script>
import Headers from '../../components/icon/Header.vue';
import { sendmail } from '../../api/Res/post';
import { checkIsExpired } from '../../api/Res/post';
import { resetPwd } from '../../api/Res/post';

export default {
  name: 'find',
  components: {
    Headers
  },
  data() {
    return {
      form: {
        email: '',
        verificationCode: '',
        newPassword: '',
        confirmPassword: ''
      },
      rules: {
        email: [{ required: true, message: '请输入邮箱号', trigger: 'blur' }],
        verificationCode: [{ required: true, message: '请输入验证码', trigger: 'blur' }],
        newPassword: [{ required: true, message: '请输入新密码', trigger: 'blur' }],
        confirmPassword: [{ required: true, message: '请输入再输一次密码', trigger: 'blur' }]
      }
    }
  },
  created() {

  },
  methods: {

    toSendmail() {
      if (!this.form.email) {
        this.$message.error("请输入邮箱！");
      } else {
        const emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!emailReg.test(this.form.email)) {
          this.$message.error("请输入有效的邮箱！");
          return;
        }
      }
      checkIsExpired(this.form.email).then(Response => {
        if (Response.data.code !== 2000) {
          // alert("验证码仍有效，请稍后再试！");
          this.$message.error("验证码仍有效，请稍后再试！");
        } else {
          // alert("请求失败，请重试");
          sendmail(this.form.email).then(Response => {
            this.$message.success(Response.data.message);
            // alert(Response.data.message);
            // sendMailResult = Response.data.code;
          });
        }
      })
      // return sendMailResult;
    },
    doFind() {
      // 在这里编写 doFind 方法的逻辑
      // 校验表单
      if (!this.form.email) {
        this.$message.error("请输入邮箱！");
      } else {
        const emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!emailReg.test(this.form.email)) {
          this.$message.error("请输入有效的邮箱！");
          return;
        }
      }
      // 判断密码
      if (!this.form.newPassword) {
        this.$message.error("请输入密码");
        return;
      } else if (this.form.newPassword !== this.form.confirmPassword) {
        this.$message.error("输入的密码不一致");
      }
      // 判断验证码
      if (!this.form.verificationCode) {
        this.$message.error("验证码不能为空");
      }

      const formData = new FormData();
      // formData.append("userName",this.user.username);
      formData.append("password", this.form.confirmPassword);
      console.log(this.form.password);
      formData.append("email", this.form.email);
      formData.append("verificationCode", this.form.verificationCode);
      resetPwd(formData).then(Response => {
        if (Response.data.code === 2000) {
          // alert("密码更新成功！");
          this.$message.success("密码更新成功！");
        } else {
          // alert("密码更新失败：" + Response.data.message);
          this.$message.error("密码更新失败：" + Response.data.message);
        }
      })

    }
  }
}
</script>
<style scoped>
.find {
  width: 100%;
  height: 100vh;
  background: url("../../assets/images/menu-bg.png") no-repeat;
  background-color: #94b9f535;
  background-size: cover;
  overflow: hidden;
}

.find-wrap {
  border-radius: 3%;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0.1, 0.2);
  width: 500px;
  height: 520px;
  margin: 100px auto;
  overflow: hidden;
  padding-top: 10px;
  line-height: 40px;
}

.find-form {
  width: 450px;
  height: 636px;
  margin: -9px auto;
  padding-top: 20px;
}

h3 {
  color: #39bceff5;
  font-size: 36px;
  padding-left: 165px;
}

hr {
  background-color: #444;
  margin: 20px auto;
}

a {
  text-decoration: none;
  color: #aaa;
  font-size: 15px;
}

a:hover {
  color: rgb(247, 7, 7);
}

.el-button.res-bt {
  width: auto;
  margin-left: -180px;
  margin-top: 10px;
}

.res-bt {
  margin-left: 70px;
}

.rightlink {
  float: right;
  /* 右浮动，使其放在右侧 */
  margin-right: 10px;
  /* 设置右侧间距 */
}

.link-group {
  display: flex;
  float: right;
  /* justify-content: space-between;  */
  margin-top: 10px;
  /* 调整与登录按钮的间距 */
}

.sendcode {
  width: 150px;
  height: 40px;
  float: right;
}</style>