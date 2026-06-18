<template>
  <div class="remote">
    <el-card class="remotecard">
      <template v-slot:header>
        <div style="display: flex; align-items: center;">
          <div style="width: 5px; height: 15px; background-color: #007bff; border-radius: 5px; margin-right: 8px;"></div>
          <span style="font-weight: bold; font-size:24px;">连接配置</span>
        </div>
      </template>
      <el-form label-position="left" label-width="80px">
        <el-form-item label="端口">
          <el-input v-model="form.port" placeholder="指定 SSH 服务监听的端口号，默认为 22。" style="width: fit-content;" disabled>
            <template #append>
              <el-button icon="el-icon-aim" @click.native="Onchangeport"></el-button>
            </template>
          </el-input>
          <span class="tip" style="color: #999; font-size: 12px; padding-left: 20px;">指定了 SSH 服务的监听端口号，默认为 22。</span>
        </el-form-item>
        <!-- 端口抽屉 -->
        <el-form-item v-show="portVisible">
          <el-drawer title="设置端口号" :visible.sync="portVisible" :modal-append-to-body="false" :wrapperClosable="false"
            custom-class="demo-drawer">
            <el-form ref="setForm" :model="setForm" label-width="60px"
              style="display: flex; flex-direction: column; height: 100%;">
              <el-form-item label="端口号" prop="days" style="padding-left: 20px;">
                <el-input placeholder="请输入要设置的端口号" v-model="setForm.port" :required="true" style="width: 70%;"></el-input>
              </el-form-item>
              <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
              <div style="flex: 1;"></div>
              <div class="drawer-buttons">
                <el-button type="primary" @click="saveport">保存</el-button>
                <el-button @click="closeDrawer">取消</el-button>
              </div>
            </el-form>
          </el-drawer>
        </el-form-item>
        <el-form-item label="监听地址">
          <el-input v-model="form.listenAddress" placeholder="指定监听的 IPv4 地址。" style="width: fit-content;" disabled>
            <template #append>
              <el-button icon="el-icon-headset" @click.native="Onchangeaddress"></el-button>
            </template>
          </el-input>
          <span class="tip" style="color: #999; font-size: 12px; padding-left: 20px;">指定监听的 IPv4 地址。</span>
        </el-form-item>
        <!-- 监听抽屉 -->
        <el-form-item v-show="addressVisible">
          <el-drawer title="设置监控地址" :visible.sync="addressVisible" :modal-append-to-body="false" :wrapperClosable="false"
            custom-class="demo-drawer">
            <el-form ref="setForm" :model="setForm" label-width="80px"
              style="display: flex; flex-direction: column; height: 100%;">
              <el-form-item label="监听地址" prop="days" style="padding-left: 20px;">
                <el-input placeholder="请输入要设置IPV4" v-model="setForm.address" :required="true"
                  style="width: 70%;"></el-input>
              </el-form-item>
              <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
              <div style="flex: 1;"></div>
              <div class="drawer-buttons">
                <el-button type="primary" @click="saveaddress">保存</el-button>
                <el-button @click="closeDrawer">取消</el-button>
              </div>
            </el-form>
          </el-drawer>
        </el-form-item>
        <el-form-item label="root用户">
          <el-input v-model="form.RootLogin" placeholder="输入 root 名。" style="width: fit-content;" disabled>
            <template #append>
              <el-button icon="el-icon-user" @click.native="Onchangeroot"></el-button>
            </template>
          </el-input>
          <span class="tip" style="color: #999; font-size: 12px; padding-left: 20px;">指定 root 用户登录方式。</span>
        </el-form-item>
        <!-- root抽屉 -->
        <el-form-item v-show="rootVisible">
          <el-drawer title="设置root" :visible.sync="rootVisible" :modal-append-to-body="false" :wrapperClosable="false"
            custom-class="demo-drawer">
            <el-form ref="setForm" :model="setForm" label-width="60px"
              style="display: flex; flex-direction: column; height: 100%;">
              <el-form-item label="root" prop="days" style="padding-left: 20px;">
                <el-input placeholder="请输入root用户名" v-model="setForm.root" :required="true" style="width: 70%;"></el-input>
              </el-form-item>
              <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
              <div style="flex: 1;"></div>
              <div class="drawer-buttons">
                <el-button type="primary" @click="saveroot">保存</el-button>
                <el-button @click="closeDrawer">取消</el-button>
              </div>
            </el-form>
          </el-drawer>
        </el-form-item>
        <el-form-item label="密码认证">
          <el-input v-model="form.PasswordAuth" placeholder="输入 root 的密码" style="width: fit-content;" disabled>
            <template #append>
              <el-button icon="el-icon-unlock" @click.native="Onchangepassword"></el-button>
            </template>
          </el-input>
          <span class="tip" style="color: #999; font-size: 12px; padding-left: 20px;">设置密码认证。</span>
        </el-form-item>
        <!-- 密码抽屉 -->
        <el-form-item v-show="passwordVisible">
          <el-drawer title="设置密码" :visible.sync="passwordVisible" :modal-append-to-body="false" :wrapperClosable="false"
            custom-class="demo-drawer">
            <el-form ref="setForm" :model="setForm" label-width="60px"
              style="display: flex; flex-direction: column; height: 100%;">
              <el-form-item label="密码" prop="days" style="padding-left: 20px;">
                <el-input placeholder="请输入root用户的密码" v-model="setForm.password" :required="true"
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
      </el-form>
      <div class="card-footer" style="display: flex; justify-content: flex-end; margin-top: 10px;">
        <el-button type="primary" icon="el-icon-link" @click="checkConnection">连接</el-button>
      </div>
    </el-card>
  </div>
</template>
<script>
import { formData } from '../../../../api/glaodata';
import { isLoginSSH } from '../../../../api/Res/post'

export default {
  data() {
    return {
      form: formData.form,
      setForm: formData.setForm,
      portVisible: formData.portVisible,
      addressVisible: formData.addressVisible,
      rootVisible: formData.rootVisible,
      passwordVisible: formData.passwordVisible,

      isConnected : false,
      // form: {
      //   port: 22, // 端口
      //   listenAddress: "0.0.0.0", // IPv4
      //   RootLogin: '', // 允许SSH登录
      //   PasswordAuth: '', // 启用密码认证
      // },
      // setForm:{
      //   port:'22',
      //   address:'',
      //   root:'',
      //   password:'',
      // },
      // portVisible:false,
      // addressVisible:false,
      // rootVisible:false,
      // passwordVisible:false,
    }
  },

  methods: {
    // 关闭抽屉
    closeDrawer() {
      this.portVisible = false,
        this.addressVisible = false,
        this.rootVisible = false,
        this.passwordVisible = false,
        this.$message({
          type: 'info',
          message: '已取消'
        });
    },
    // 打开端口抽屉
    Onchangeport() {
      this.portVisible = true;
    },
    // 保存端口
    saveport() {
      // 实现保存时间的逻辑
      console.log('保存的端口为:', this.setForm.port);
      // 保存到drawerForm的time属性中
      this.form.port = this.setForm.port;
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.portVisible = false;
    },
    // 打开监听抽屉
    Onchangeaddress() {
      this.addressVisible = true;
    },
    // 保存端口
    saveaddress() {
      // 实现保存时间的逻辑
      console.log('保存的监听地址为:', this.setForm.address);
      // 保存到drawerForm的time属性中
      this.form.listenAddress = this.setForm.address;
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.addressVisible = false;
    },
    // 打开root抽屉
    Onchangeroot() {
      this.rootVisible = true;
    },
    // 保存端口
    saveroot() {
      // 实现保存时间的逻辑
      console.log('保存的root为:', this.setForm.root);
      // 保存到drawerForm的time属性中
      this.form.RootLogin = this.setForm.root;
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.rootVisible = false;
    },

    // 打开密码抽屉
    Onchangepassword() {
      this.passwordVisible = true;
    },
    // 保存密码
    savepassword() {
      // 实现保存时间的逻辑
      console.log('保存的密码为:', this.setForm.password);
      // 保存到drawerForm的time属性中
      this.form.PasswordAuth = this.setForm.password;
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.passwordVisible = false;
    },

    
    // 连接按钮
    checkConnection() {
      // 假设连接是不可用的
      // 这里添加你的连接检查逻辑
      // 例如，你可以使用一个模拟的 API 请求来检查连接
      isLoginSSH(this.form).then(result => {
        if (result.data.code === 2000) {
          this.isConnected = true;
        } else {
          alert(result.data.message);
        }
        this.showConnectionStatus();
      })
      
    },
    showConnectionStatus() {
      // 根据连接状态显示信息
      if (this.isConnected) {
        this.$alert('连接成功！', '连接状态', {
          confirmButtonText: '确定',
        });
      } else {
        this.$alert('连接失败', '连接状态', {
          confirmButtonText: '确定',
        });
      }
    }

  }
};
</script>
<style >
.remote {
  margin: auto;
  width: 100%;
  margin-bottom: 80px;
}

.remotecard {
  margin-top: 20px;
}

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