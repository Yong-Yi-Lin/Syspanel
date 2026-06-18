
<template>
  <div class="codehome">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card style="width: 100%;">
          <template v-slot:header>
            <div style="display: flex; align-items: center;">
              <div style="width: 5px; height: 15px; background-color: #007bff; border-radius: 5px; margin-right: 8px;">
              </div>
              <span style="font-weight: bold; font-size:24px;">运行代码</span>
            </div>
          </template>
          <!-- 代码编辑器文本域，带有行号 -->
          <div class="code-editor-wrapper">
            <textarea class="code-editor" v-model="code"></textarea>
            <pre class="line-numbers">{{ code.split('\n').map((_, i) => i + 1).join('\n') }}</pre>
          </div>
          <!-- 运行代码按钮 -->
          <el-button type="primary" @click="runCode">点击运行</el-button>
          <!-- 清空代码按钮 -->
          <el-button @click="clearCode">清空</el-button>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card style="width: 100%;">
          <template v-slot:header>
            <div style="display: flex; align-items: center;">
              <div style="width: 5px; height: 15px; background-color: #007bff; border-radius: 5px; margin-right: 8px;">
              </div>
              <span style="font-weight: bold; font-size:24px;">浏览窗口</span>
            </div>
          </template>
          <!-- 预览区域显示结果 -->
          <div class="preview-pane">
            <iframe ref="previewFrame" class="preview-frame"></iframe>
          </div>
        </el-card>
      </el-col>
      <!-- 停止连接 -->
      <div>
        <!-- 悬浮球 -->
        <div class="floating-button" @click="isCardVisible = !isCardVisible">
          <i class="el-icon-s-platform"></i>
        </div>

        <!-- 展开卡片面板 -->
        <el-col v-show="isCardVisible" :span="6" class="card-container">
          <el-card class="card">
            <!-- 卡片内容 -->
            <div class="card-content">
              <!-- 图标和文本 -->
              <span class="card-icon">
                <i class="el-icon-s-platform"></i>
              </span>
              <div class="card-info">
                <p class="hostname">宿主机名</p>
                <p class="hostname-value">{{ hostname }}</p>
              </div>

            </div>
            <div class="card-actions">
              <el-button @click="stopHost" type="danger" block icon="el-icon-folder-delete">
                断开连接
              </el-button>
            </div>
          </el-card>
        </el-col>
      </div>
    </el-row>
  </div>
</template>
  
<script>
import { codeData } from '../../../../api/glaodata';
import { formData } from '../../../../api/glaodata';
import { sshToCmd } from '../../../../api/Res/post'
import { disconnectSSH } from '../../../../api/Res/post'
export default {
  data() {
    return {
      isCardVisible: false,
      form: formData.form,
      code: codeData.code,
      result: "初始化数据",
      hostname: "暂无连接主机"
      // code: '请输入命令' // 用户输入的代码
    };
  },
  mounted() {
    // 在 iframe 加载完成后插入初始化数据
    this.$nextTick(() => {
      const iframe = this.$refs.previewFrame;
      const iframeDocument = iframe.contentDocument;
      iframeDocument.open();
      iframeDocument.write('<p style="color:blue;font-size:14px;">#' + this.result + '</p>');
      iframeDocument.close();
      this.getHostName();
    });
  },
  methods: {
    getHostName() {
      let cmd = 'hostname';
      sshToCmd(this.form, cmd).then(resp => {
        if (resp.data.code === 2000) {
          this.hostname = resp.data.data;
        }
      })
    },
    runCode() {
      const iframe = this.$refs.previewFrame;
      const iframeDocument = iframe.contentDocument;

      // 清空 iframe 中的旧内容
      iframeDocument.open();
      iframeDocument.close();

      // 在 iframe 中插入用户输入的代码
      iframeDocument.open();

      //远程发送cmd
      sshToCmd(this.form, this.code).then(resp => {
        if (resp.data.code === 2000) {
          this.result = resp.data.data;
          this.$notify({
            title: '成功',
            message: '命令执行成功',
            type: 'success',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        } else {
          this.$notify({
            title: '失败',
            message: '命令执行失败',
            type: 'warning',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        }
        iframeDocument.write(this.result);
      })
      iframeDocument.close();
    },
    clearCode() {
      this.code = ''; // 清空编辑器中的代码

      // 同时清空预览 iframe 中的内容
      const iframe = this.$refs.previewFrame;
      const iframeDocument = iframe.contentDocument;
      iframeDocument.open();
      iframeDocument.close();
    },
    // 断开连接
    stopHost() {
      disconnectSSH(this.form).then(resp => {
        if (resp.data.code === 2000) {
          this.$notify({
            title: '提示',
            message: '断开成功',
            type: 'success',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        } else {
          this.$notify({
            title: '提示',
            message: '没有连接主机无法实现断开',
            type: 'warning',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        }
      })

    }
  }
};
</script>
  
<style scoped>
.codehome {
  padding: 20px;
}

.code-editor-wrapper {
  position: relative;
  width: 100%;
  /* Adjusted to 100% for better responsiveness */
  height: 300px;
}

.code-editor {
  position: absolute;
  top: 0;
  left: 50px;
  /* Leave space for the line numbers */
  width: 85%;
  height: 90%;
  color: aqua;
  border: 1px solid #ccc;
  font-family: 'Courier New', Courier, monospace;
  background: black;
  font-size: 14px;
  padding: 10px;
  resize: none;
}

.line-numbers {
  position: absolute;
  top: -13px;
  left: 0;
  width: 30px;
  height: 90%;
  border: 1px solid #ccc;
  border-right: none;
  background-color: #f5f5f5;
  padding: 10px;
  text-align: right;
  overflow: hidden;
  pointer-events: none;
}

.preview-pane {
  width: 100%;
  height: 340px;
}

.preview-frame {
  width: 100%;
  height: 100%;
  border: none;
  background: rgb(201, 233, 241);
  border: 1px solid rgb(201, 201, 202);
  color: white;
}


.el-button {
  margin-right: 10px;
}

/* .status {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px; 
}

.hostname {
  font-size: 24px; 
  font-weight: bolder; 
}
.hostname-value {
  font-size: 16px; 
  color: grey;
} */
</style>
<style scoped>
.floating-button {
  position: fixed;
  bottom: 50px;
  right: 20px;
  background-color: #409eff;
  color: white;
  border-radius: 50%;
  width: 60px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  cursor: pointer;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

.card-container {
  position: absolute;
  bottom: -180px;
  /* Adjust this to position the card appropriately */
  right: 20px;
  z-index: 999;
}

.card {
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #ffffff;
  /* padding: 20px; */
}

.card-content {
  display: flex;
  align-items: center;
}

.card-icon {
  font-size: 56px;
  color: #409eff;
  margin-top: -10px;
  margin-right: 15px;
}

.card-info {
  flex: 1;
}

.hostname {
  color: #333;
  font-weight: bolder;
  font-size: 20px;
  margin: 0;
}

.hostname-value {
  color: #666;
  font-size: 18px;
  margin-top: 5px;
}

.card-actions {
  text-align: center;
  margin-top: 10px;
  font-size: 16px;
}

.action-button {
  border-radius: 8px;
  font-size: 16px;
  width: 100%;
}

/* 调整按钮图标大小 */
.action-button .el-icon-folder-delete {
  font-size: 28px;
  /* 设置图标大小 */
}
</style>