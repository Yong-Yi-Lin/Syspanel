<template>
  <div class="style">
    <!-- 主面板 -->
    <el-card>
      <div slot="header">
        <div style="display: flex; align-items: center;">
          <div style="width: 5px; height: 15px; background-color: #007bff; border-radius: 5px; margin-right: 8px;"></div>
          <span style="font-weight: bold; font-size: 24px;">面板设置</span>
        </div>
      </div>
      <el-form label-position="left" label-width="100px" class="right-margin">
        <el-row :class="styleFrom">
          <el-col :xs="24" :sm="20" :md="15" :lg="12" :xl="12">
      
            <!-- 用户名设置项 -->
            <el-form-item :label="'面板用户'" prop="userName">
              <el-input disabled v-model="form.userName">
                <template #append>
                  <el-button @click.native="onChangeUserName" icon="el-icon-setting">
                    设置
                  </el-button>
                </template>
              </el-input>
            </el-form-item>
            
            <!-- 修改用户名抽屉 -->
            <el-form-item v-show="usernameVisible">
              <el-drawer
                title="修改用户名"
                :visible.sync="usernameVisible"
                :modal-append-to-body="false"
                :wrapperClosable="false"
                custom-class="demo-drawer"
              >
                <el-form ref="S_Form" 
                :model="S_Form" 
                label-width="60px"
                style="display: flex;
                flex-direction: column; 
                height: 100%;">
                  <el-form-item label="用户名" prop="name" style="padding-left: 20px;">
                    <el-input placeholder="请输入修改的用户名"
                      v-model="S_Form.username"
                      @blur="nameHandleBlur" 
                      :required="true"
                    
                      style="width: 70%;"></el-input>
                  </el-form-item>
                   <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                   <div style="flex: 1;"></div>

                  <div class="drawer-buttons">
                    <el-button type="primary" @click="savename">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form-item>
            
            <!-- 密码设置项 -->
            <el-form-item :label="'面板密码'" prop="password">
              <el-input type="password" disabled v-model="form.password" >
                <template #append>
                  <el-button @click.native="onChangePassword" icon="el-icon-setting">
                    设置
                  </el-button>
                </template>
              </el-input>
            </el-form-item>
            <!-- 修改密码抽屉 -->
            <el-form-item v-show="passwordVisible">
              <el-drawer
                title="修改密码"
                :visible.sync="passwordVisible"
                :modal-append-to-body="false"
                :wrapperClosable="false"
                custom-class="demo-drawer"
              >
                <el-form ref="S_Form" 
                :model="S_Form" 
                label-width="60px"
                style="display: flex;
                flex-direction: column; 
                height: 100%;">
                  <el-form-item label="密码" prop="password" style="padding-left: 20px;">
                    <el-input 
                    show-password placeholder="请输入密码"
                      v-model="S_Form.password" 
                      :required="true"
                   
                      style="width: 70%;"></el-input>
                  </el-form-item>
                   <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                   <div style="flex: 1;"></div>
                  <div class="drawer-buttons">
                    <el-button type="primary" @click="savepassword">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form-item>
                   <!-- 邮箱设置项 -->
                   <el-form-item :label="'用户邮箱'" prop="password">
              <el-input  disabled v-model="form.Email">
                <template #append>
                  <el-button @click.native="onChangeEmail" icon="el-icon-setting">
                    设置
                  </el-button>
                </template>
              </el-input>
            </el-form-item>
            <!-- 邮箱抽屉 -->
            <el-form-item v-show="EmailVisible">
              <el-drawer
                title="修改邮箱"
                :visible.sync="EmailVisible"
                :modal-append-to-body="false"
                :wrapperClosable="false"
                custom-class="demo-drawer"
              >
              <el-form ref="S_Form" 
                :model="S_Form" 
                label-width="60px"
                style="display: flex;
                flex-direction: column; 
                height: 100%;">
                  <el-form-item label="邮箱" prop="email" style="padding-left: 20px;">
                    <el-input placeholder="请输入修改邮箱号"
                      @blur="emailHandleBlur"
                      v-model="S_Form.email" 
                      :required="true"
                      :rules="[{ required: true, message: '请输入邮箱', trigger: 'blur' }]"
                      style="width: 70%;"></el-input>
                  </el-form-item>
                   <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                   <div style="flex: 1;"></div>
                  <div class="drawer-buttons">
                    <el-button type="primary" @click="saveEmail">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form-item>
            <!-- 超时时间设置项 -->
            <el-form-item :label="'超时时间'" prop="passtime">
              <el-input disabled v-model="form.passtime">
                <template #append>
                  <el-button @click.native="onChangepasstime" icon="el-icon-setting">
                    设置
                  </el-button>
                </template>
              </el-input>
              <span class="input-help" style="color: grey; font-size: 12px;">如果登录超过设置时间，面板将自动退出登录（单位：分钟）</span>
            </el-form-item>
            <!-- 超时抽屉 -->
            <el-form-item v-show="passtimeVisible">
              <el-drawer
                title="修改时间"
                :visible.sync="passtimeVisible"
                :modal-append-to-body="false"
                :wrapperClosable="false"
                custom-class="demo-drawer"
              >
              <el-form ref="S_Form" 
                :model="S_Form" 
                label-width="80px"
                style="display: flex;
                flex-direction: column; 
                height: 100%;">
                  <el-form-item label="退出时间" prop="password" style="padding-left: 20px;">
                    <el-input placeholder="请输入修改时间（秒）"
                      v-model="S_Form.passtime" 
                      :required="true"
                     :rules="[{ required: true, message: '不得为空', trigger: 'blur' }]"
                      style="width: 80%;"></el-input>
                  </el-form-item>
                   <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                   <div style="flex: 1;"></div>
                  <div class="drawer-buttons">
                    <el-button type="primary" @click="savepasstime">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form-item>
            <!-- 默认网卡设置项 -->
            <!-- <el-form-item :label="'默认网卡'" prop="net">
              <el-input disabled v-model="form.net">
                <template #append>
                  <el-button @click.native="onChangenet" icon="el-icon-setting">
                    设置
                  </el-button>
                </template>
              </el-input>
            </el-form-item> -->
            
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import {getInfo} from '../../api/Res/get'
import {updatename} from '../../api/Res/post'
import {updatepassword} from '../../api/Res/post'
import {updateemail} from '../../api/Res/post'
import {updatetimeout} from '../../api/Res/post'
import { isExistByName } from '../../api/Res/get'
import { isExistByEmail } from '../../api/Res/get'

export default {
  data() {
    return {
      // 初始化设置
      usernameVisible: false,
      passwordVisible:false,
      EmailVisible:false,
      passtimeVisible:false,
      form: {
        userName: 'rue',
        password: '123456',
        Email:'1605853577@qq.com',
        passtime: '60',
        net: '所有',
        title: 'open', // 这里的 title 变量没有在模板中使用，可能是暂未实现的功能
      },
      
      styleFrom: 'style-from', // 添加 styleFrom 属性定义
      // 抽屉表单数据
      S_Form: {
        username: 'rue',
        password:'123456',
        email:'165853577@qq.com',
        passtime:'60',
      },
     
    };
  },
  mounted(){
    this.getUserInfo()
  },
  methods: {
    getUserInfo(){
      getInfo().then((result) => {
        console.log(result);
          this.form.userName = result.username
          // this.dataTotal = result.totalCount
          this.form.password=result.password
          this.form.Email=result.email
          this.form.passtime = result.passtime
          this.S_Form.username = result.username
          this.S_Form.password = result.password
          this.S_Form.email = result.email
          this.S_Form.passtime = result.passtime
        })

    },
    // 关闭抽屉
    closeDrawer() {
      this.usernameVisible = false;
      this.passwordVisible=false,
      this.EmailVisible=false,
      this.passtimeVisible=false,
      this.$message({
        type: 'info',
        message: '已取消',
      });
    },
    nameHandleBlur(){
      console.log(this.S_Form.username);
      isExistByName(this.S_Form.username).then((response) => {
      if (response.status === 200 || response.data.success) { 
        console.log("查询的结果是"+response.data.message);
      }
      if(response.data.message==="用户名存在"){
        this.$message.error(response.data.message+"，请重新输入");
        this.S_Form.username=''
      }
    })
    },
    emailHandleBlur(){
      console.log(this.S_Form.email);
      isExistByEmail(this.S_Form.email).then((response) => {
      if (response.status === 200 || response.data.success) { 
        console.log("查询的结果是"+response.data.message);
      }
      if(response.data.message==="邮箱已存在"){
        this.$message.error(response.data.message+"，请重新输入");
        this.S_Form.email=''
      }
    })
    },
    // 修改用户名
    onChangeUserName() {
      this.usernameVisible = true;
    },
    // 保存用户名设置
    savename() {
      if(!this.S_Form.username){
          this.$message.error("邮箱为空，无法修改");
          return;
        }
      updatename(this.S_Form);
      // console.log('保存的用户名为:', this.S_Form.username);
      this.form.userName = this.S_Form.username; // 将新用户名保存到主面板的数据中
      this.$notify({
        title: '成功',
        message: '用户名已成功保存',
        type: 'success',
        duration: 3000,
      });
      this.usernameVisible = false; // 保存后关闭抽屉
    },
    
    // 密码修改
    onChangePassword() {
      // 处理密码变更
      this.passwordVisible=true;
    },
      // 保存密码设置
      savepassword() {
      updatepassword(this.S_Form);

      console.log('保存的密码为:', this.S_Form.password);
      this.form.password = this.S_Form.password; // 将新密码保存到主面板的数据中
      this.$notify({
        title: '成功',
        message: '密码已成功保存',
        type: 'success',
        duration: 3000,
      });
      this.passwordVisible = false; // 保存后关闭抽屉
    },
    // 邮箱修改
    onChangeEmail(){
      this.EmailVisible=true;
    }, // 保存邮箱设置
      saveEmail() {
        if(!this.S_Form.email){
          this.$message.error("邮箱为空，无法修改");
          return;
        }
        updateemail(this.S_Form);
      console.log('保存的邮箱为:', this.S_Form.email);
      this.form.Email = this.S_Form.email; // 将新邮箱号保存到主面板的数据中
      this.$notify({
        title: '成功',
        message: '邮箱已成功保存',
        type: 'success',
        duration: 3000,
      });
      this.EmailVisible = false; // 保存后关闭抽屉
    },

    onChangepasstime() {
      // 处理超时时间变更
      this.passtimeVisible=true;
    },
     // 保存超时设置
     savepasstime() {
      // updatetimeout
      if(!this.S_Form.passtime){
          this.$message.error("邮箱为空，无法修改");
          return;
        }
        updatetimeout(this.S_Form);
      console.log('保存的邮箱为:', this.S_Form.passtime);
      this.form.passtime = this.S_Form.passtime; // 将新邮箱号保存到主面板的数据中
      this.$notify({
        title: '成功',
        message: '超时时间已成功保存',
        type: 'success',
        duration: 3000,
      });
      this.passtimeVisible = false; // 保存后关闭抽屉
    },
    onChangenet() {
      // 处理网卡设置变更
    },
  },
};
</script>

<style>
.style {
  background-color: #ffffff;
  margin: 20px auto; /* 保持上下20px间距，水平居中 */
  width: 95%;
  border: 1px solid #cdcdcd;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}


.title-style {
  padding-left: 20px;
  font-size: 24px; /* 样式化标题文本 */
  font-weight: bold; /* 可选的，增加标题粗细 */
  color: #333; /* 可选的，设置标题颜色 */
}

/* .demo-drawer {
  background-color: #f0f2f5;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.drawer-buttons {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  margin-top: 480px;
}
.drawer-buttons button {
  flex: 1; /* 按钮占据一半的空间 */

  .demo-drawer {

border-radius: 4px;
box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.drawer-buttons {
display: flex;
justify-content: space-between;
padding: 10px;

}

.drawer-buttons button {
flex: 1;
/* 按钮占据一半的空间 */
}

.el-drawer__header {
color: #040404;
font-size: 22px;
}

.demo-drawer .el-drawer__body {
display: flex;
flex-direction: column;
padding-bottom: 25px;
/* Add padding to the bottom of the drawer body */
}
</style>