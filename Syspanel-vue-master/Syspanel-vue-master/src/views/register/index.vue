<template>
  <div class="register " clearfix>
    <div class="custom-header">
      <div class="logo-container">
        <img :src="require('@/assets/svg/logo.svg')" alt="Logo" class="fixed-svg" />
        <img :src="require('@/assets/svg/syspanel.svg')" alt="Logo" class="fixed-svgs" />
      </div>
      <div class="icon-container">


      </div>
    </div>
    <div class="register-wrap">
      <el-row type="flex" justify="center">
        <el-form ref="loginForm" :model="userForm" :rules="rules" status-icon label-width="100px" class="loginform">
          <h3>注册</h3>
          <hr>
          <el-form-item prop="username" label="用户名">
            <template #label>
              <span><i class="el-icon-user"></i> 用户名</span>
            </template>
            <el-input v-model="userForm.username" @blur="nameHandleBlur" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="email" label="邮箱">
            <template #label>
              <span><i class="el-icon-message"></i> 邮箱</span>
            </template>
            <el-input v-model="userForm.email" @blur="emailHandleBlur" placeholder="请输入邮箱"></el-input>
          </el-form-item>


          <el-form-item prop="verificationCode" label="验证码">
            <template #label>
              <span><i class="el-icon-chat-round"></i> 验证码</span>
            </template>
            <!-- 将 "请输入验证码" 输入框和 "获取验证码" 按钮放在同一行 -->
            <div style="display: flex;">
              <el-input v-model="userForm.verificationCode" placeholder="请输入验证码"></el-input>

              <el-button size="mini" class="sendcode" @click="doSendCode()">获取验证码</el-button>
            </div>
          </el-form-item>


          <el-form-item prop="password" label="设置密码">
            <template #label>
              <span><i class="el-icon-lock"></i> 设置密码</span>
            </template>
            <el-input v-model="userForm.password" show-password placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item prop="password-s" label="确认密码">
            <template #label>
              <span><i class="el-icon-lock"></i> 确认密码</span>
            </template>
            <el-input v-model="userForm.confirmPassword" show-password placeholder="请输入确认密码"></el-input>
          </el-form-item>
          <el-form-item prop="hasPermission" label="拥有权限">
            <el-checkbox v-model="userForm.hasPermission" @change="handlePermissionSelection">是</el-checkbox>
            <el-checkbox v-model="userForm.noPermission" @change="handlePermissionSelection">否</el-checkbox>
            <el-input v-if="userForm.hasPermission" v-model="userForm.permissionCode" placeholder="请输入权限码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon @click="doRegister()" class="registerbt">注册账号</el-button>
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
import { checkIsExpired } from "../../api/Res/post"
import { sendmail } from "../../api/Res/post";
import { register } from "../../api/Res/post";
import { isExistByName } from '../../api/Res/get'
import { isExistByEmail } from '../../api/Res/get'

export default {
  name: "register",
  data() {
    return {
      userForm: {
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
        verificationCode: '',
        hasPermission: false, // 默认未选择权限
        permissionCode: "",
        // hasPermission: false,
        noPermission: false,
        // permissionCode: "",
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名(限制6个字符)', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请再次输入密码', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱号', trigger: 'blur' }
        ],
        verificationCode: [
          {
            required: true, message: '请输入验证码', trigger: 'blur'
          }
        ]
      }
    };
  },
  created() {
  },
  computed: {
    usernameLength() {
      return this.userForm.username.length;
    },
    isUsernameLengthValid() {
      return this.usernameLength >= 6;
    },
  },
  methods: {
    doSendCode() {
      if (!this.userForm.email) {
        this.$message.error("请输入邮箱！");
        return;
      } else {
        const emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!emailReg.test(this.userForm.email)) {
          this.$message.error("请输入有效的邮箱！");
          return;
        }
      }

      console.log(this.userForm.email);
      console.log(this.userForm.email);

      checkIsExpired(this.userForm.email).then(Response => {
        if (Response.data.code !== 2000) {
          // alert("验证码仍有效，请稍后再试！");
          this.$message.error("验证码仍有效，请稍后再试！");
        } else {
          // alert("请求失败，请重试");
          sendmail(this.userForm.email).then(Res => {
            // alert(Res.data.message);
            this.$message({
                type: 'success',
                message: Res.data.message
              });
          })
        }
      })
    },

    doRegister() {
      // formData.append("")
      if (!this.userForm.username) {
        this.$message.error("请输入用户名！");
        return;
      }
      if (!this.isUsernameLengthValid) {
        this.$message.error("用户名长度必须至少为6个字符！");
        return;
      }

      if (!this.userForm.email) {
        this.$message.error("请输入邮箱！");
        return;
      } else {
        const emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!emailReg.test(this.userForm.email)) {
          this.$message.error("请输入有效的邮箱！");
          return;
        }
      }

      const formData = new FormData();
      formData.append("userName", this.userForm.username);
      formData.append("password", this.userForm.password);
      formData.append("email", this.userForm.email);
      formData.append("verificationCode", this.userForm.verificationCode);

      if (!this.userForm.password) {
        this.$message.error("请输入密码！");
        return;
      } else if (this.userForm.password !== this.userForm.confirmPassword) {
        this.$message.error("输入的密码不一致！");
        return;
      }

      // 如果选择了拥有权限，验证权限码
      if (this.userForm.hasPermission) {
        if (this.userForm.permissionCode !== "admin") {
          this.$message.error("权限码不正确！");
          // formData.append("role","guest");
          return;
        } else {
          formData.append("role", "admin");
        }
      } else {
        formData.append("role", "guest");
      }


      register(formData).then(Response => {
        if (Response.data.code !== 2000) {
          this.$message.error(Response.data.message);
          // alert(Response.data.message);
        } else {
          // alert("注册成功！");
          this.$message.success("注册成功！");
        }
      })
    },

    handlePermissionSelection() {
      if (this.user.hasPermission) {
        this.user.noPermission = false;
      } else if (this.user.noPermission) {
        this.user.hasPermission = false;
        // alert("默认为普通用户");
        this.$message.success("默认为普通用户");
      }
    },

    validatePermission() {
      if (this.user.hasPermission) {
        if (this.user.permissionCode !== "admin") {
          this.$message.error("权限码不正确");
        } else {
          this.$message.success("权限码正确,注册为管理员");
        }
      }
    },
    nameHandleBlur() {
      console.log(this.userForm.username);
      isExistByName(this.userForm.username).then((response) => {
        if (response.status === 200 || response.data.success) {
          console.log("查询的结果是" + response.data.message);
        }
        if (response.data.message === "用户名存在") {
          this.$message.error(response.data.message + "，请重新输入");
          this.userForm.username = ''
        }
      })
    },
    emailHandleBlur() {
      console.log(this.userForm.email);
      isExistByEmail(this.userForm.email).then((response) => {
        if (response.status === 200 || response.data.success) {
          console.log("查询的结果是" + response.data.message);
        }
        if (response.data.message === "邮箱已存在") {
          this.$message.error(response.data.message + "，请重新输入");
          this.userForm.email = ''
        }
      })
    },
  }

}
</script>
   

<style >
.register {
  width: 100%;
  height: 100vh;
  background: url("../../assets/images/menu-bg.png") no-repeat;
  background-color: #94b9f535;

  background-size: cover;
  overflow: hidden;
}

.register-wrap {
  /* border: 1px solid; */
  border-radius: 3%;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0.1, 0.2);
  width: 500px;
  height: 650px;
  margin: 70px auto;
  overflow: hidden;
  padding-top: 5px;
  line-height: 40px;

}

#password {
  margin-bottom: 5px;
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

.el-button.registerbt {
  width: auto;
  /* 保持宽度 */
  margin-left: 50px;

}

.r-form {
  width: 450px;
  height: 900px;
  margin: -9px auto;
  padding-top: 20px;

}

.rightlink {
  float: right;
  /* 右浮动，使其放在右侧 */
  /* margin-right: 10px; */
  padding-bottom: 20px;
  /* 设置右侧间距 */
}

.link-group {
  display: flex;
  float: right;
  /* justify-content: space-between;  */
  /* 调整与登录按钮的间距 */
}

.el-checkbox.rcheck {
  padding-right: 78px;
  margin: auto;
}
</style>
<!-- 头部 -->
<style scoped> .custom-header {
   display: flex;
   justify-content: space-between;
   align-items: center;
   height: 60px;
   /* 设置你想要的 header 高度 */
   background-color: #f5f7fa56;
   /* 设置背景色 */
   position: fixed;
   /* 使 header 固定在顶部 */
   top: 0;
   left: 0;
   right: 0;
   z-index: 1000;
   /* 确保 header 在其他内容之上 */
   padding: 0 15px;
   /* 添加内边距以避免内容紧贴边缘 */
   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
   /* 添加阴影效果 */
 }

 .logo-container,
 .icon-container {
   display: flex;
   align-items: center;
 }

 .fixed-svg {
   height: 65px;
   /* 设置 SVG 的高度 */
   width: 40px;
   /* 保持 SVG 的宽高比 */
 }

 .fixed-svgs {
   height: 50px;
   /* 设置 SVG 的高度 */
   width: auto;
   /* 保持 SVG 的宽高比 */
 }
</style>
<style scoped>
body {
  margin: 0;
  padding: 0;
}
</style>