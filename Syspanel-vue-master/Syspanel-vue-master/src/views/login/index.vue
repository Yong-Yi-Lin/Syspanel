<template>

  <div class="login" clearfix>

    <div class="custom-header">
  <div class="logo-container">
    <img :src="require('@/assets/svg/logo.svg')" alt="Logo" class="fixed-svg" />
    <img :src="require('@/assets/svg/syspanel.svg')" alt="Logo" class="fixed-svgs" />
  </div>
  <div class="icon-container">
  </div>
  
</div>

    <div class="login-wrap">
   
      <el-row type="flex" justify="center">
        <el-form ref="loginForm" :model="user" :rules="rules" status-icon label-width="100px" class="loginform">
          <h2>登录</h2>
          <hr>
          <el-form-item prop="username" label="用户名">
      <template #label>
        <span><i class="el-icon-user"></i> 用户名</span>
      </template>
      <el-input v-model="user.username" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item prop="password" label="密码">
      <template #label>
        <span><i class="el-icon-lock"></i> 密码</span>
      </template>
      <el-input v-model="user.password" show-password placeholder="请输入密码"></el-input>
    </el-form-item>
          <el-form-item class="button">
            <el-button type="primary"  @click="doLogin()" class="l-bt">登 录</el-button>
          </el-form-item>
        
          <div class="link-group">  
          <router-link to="/find" class="rightlink">找回密码</router-link>  
          <router-link to="/register" class="rightlink">注册账号</router-link>  
        </div>
        
        </el-form>
      </el-row>
    </div>
  </div>
</template>
 
<script>
import {LoginCheckUser} from "../../api/Res/post"
export default {
  name: "login",
  data() {
      return {
    user: {
      username: "",
      password: ""
    },
    rules: {
      // 在这里定义规则对象
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' }
      ]
    }
  };
},
  created() {},
  methods: {
    doLogin() {
      if (!this.user.username) {
        this.$message.error("请输入用户名！");
        return;
      } else if (!this.user.password) {
        this.$message.error("请输入密码！");
        return;
      } else {
        //校验用户名和密码是否正确;
        // this.$router.push({ path: "/personal" });
        const formData = new FormData();
        formData.append("userName",this.user.username);
        formData.append("password",this.user.password);

        console.log(this.user.username);
        console.log(formData.get("userName"));

        LoginCheckUser(formData).then(response => {
          console.log(response);
          if (response.data.code === 2000) { 
        // console.log("查询的结果是"+response.data.message);
        this.$message.success("登录成功");
        this.$router.push({ path: "/home"});
      }else if(response.data.code!== 2000){
        this.$message.error(response.data.message);
      }
        })
      
      }
    }
  }
};
</script>
 

<style >
.login {
  width: 100%;
  height: 100vh;
  background: url("../../assets/1panel-login-bg.png") no-repeat;
  background-color: #d5dfef;
  
  background-size: cover;
  overflow: hidden;
}
.login-wrap {
/* border: 1px solid;  */
border-radius: 3%;
background-color: #fff; 
box-shadow: 0 2px 4px rgba(0, 0, 0.1, 0.2);
width: 500px;
height: 400px;
margin: 215px auto;
overflow: hidden;
padding-top: 10px;
line-height: 40px;

}
#password {
  margin-bottom: 5px;
}
h2 {
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
  color: #111111;
  font-size: 15px;
}
a:hover {
  color: rgb(247, 7, 7);
}
.el-button.l-bt {
  width: 80%;
  margin-left: -20px;
  padding-top: 10px;
}
.loginform {
width: 400px; /* 设置表单宽度 */
/* border: 1px solid black; */
height: 400px;

padding: 20px;  

}

.rightlink {
float: right; /* 右浮动，使其放在右侧 */
margin-right: 10px; /* 设置右侧间距 */
}
.button{
margin: auto;
border-radius: 5px; 
padding-left: 50px;
width: auto;
}
.link-group {  
display: flex;  
float: right;
/* justify-content: space-between;   */
margin-top: 10px; /* 调整与登录按钮的间距 */  
}  
</style>
   <!-- 头部 -->
   <style >  
   .custom-header {  
     display: flex;  
     justify-content: space-between;  
     align-items: center;  
     height: 60px; /* 设置你想要的 header 高度 */  
     background-color: #f5f7fa56; /* 设置背景色 */  
     position: fixed; /* 使 header 固定在顶部 */  
     top: 0;  
     left: 0;  
     right: 0;  
     z-index: 1000; /* 确保 header 在其他内容之上 */  
     padding: 0 15px; /* 添加内边距以避免内容紧贴边缘 */  
     box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */  
   }  
     
   .logo-container, .icon-container {  
     display: flex;  
     align-items: center;  
   }  
     
      
   .fixed-svg {   
     height: 65px; /* 设置 SVG 的高度 */  
     width: 40px; /* 保持 SVG 的宽高比 */  
   }  
   .fixed-svgs {   
     height: 50px; /* 设置 SVG 的高度 */  
     width: auto; /* 保持 SVG 的宽高比 */  
   }   
   </style>
   <style scoped>
    body{
     margin:0;
     padding:0;
   }
  </style>