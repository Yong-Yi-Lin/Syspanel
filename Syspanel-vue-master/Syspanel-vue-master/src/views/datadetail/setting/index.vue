<template>
  <div class="set-main">
    <el-card>
      <div slot="header" style=" padding-left: 20px; padding-top:15px">
        <div style="display: flex; align-items: center;">
          <div style="width: 5px; height: 15px; background-color: #007bff; border-radius: 5px; margin-right: 8px;"></div>
          <span style="font-weight: bold; font-size: 24px;">监控设置</span>
        </div>
      </div>

      <el-form label-position="left" label-width="160px">
        <el-row>
          <el-col :span="1"><br /></el-col>
          <el-col :span="12">
            <el-form-item label="监控状态" prop="monitorStatus">
              <el-switch v-model="status">
              </el-switch>
            </el-form-item>
            <!-- 保存数据量 -->
            <el-form-item label="保存数据量（条）" prop="monitorStoreDays">
              <el-input placeholder="请输入数据量" disabled v-model="input1">
                <template #append>
                  <el-button icon="el-icon-date" @click.native="OnchangeStoreDays">

                  </el-button>
                </template>

              </el-input>
            </el-form-item>
            <!-- 数据量抽屉 -->
            <el-form-item v-show="timeVisible">
              <el-drawer title="设置保存数据量（条）" :visible.sync="timeVisible" :modal-append-to-body="false"
                :wrapperClosable="false" custom-class="demo-drawer">
                <el-form ref="drawerForm" :model="drawerForm" label-width="60px"
                  style="display: flex; flex-direction: column; height: 100%;">
                  <el-form-item label="数量" prop="days" style="padding-left: 20px;">
                    <el-input placeholder="请输入要设置预警数据量" v-model="drawerForm.number" :required="true"
                      style="width: 70%;"></el-input>
                  </el-form-item>
                  <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                  <div style="flex: 1;"></div>
                  <div class="drawer-buttons">
                    <el-button type="primary" @click="saveNumber">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form-item>
            <!-- 采集间隔 -->
            <el-form-item label="预警采集间隔(秒)" prop="monitorInterval">
              <el-input placeholder="请输入预警时间间隔" disabled v-model="input2">
                <template #append>
                  <el-button icon="el-icon-time" @click.native="OnchangeInterval">
                  </el-button>
                </template>
              </el-input>
            </el-form-item>
            <!-- 采集抽屉 -->
            <el-form-item v-show="IntervalVisible">
              <el-drawer title="设置预警监控采集时间" :visible.sync="IntervalVisible" :modal-append-to-body="false"
                :wrapperClosable="false" custom-class="demo-drawer">
                <el-form ref="drawerForm" :model="drawerForm" label-width="120px"
                  style="display: flex; flex-direction: column; height: 100%;">
                  <el-form-item label="设置采集时间" prop="time" style="padding-left: 20px;">
                    <el-input placeholder="请输入要设置采集时间范围" v-model="drawerForm.interval" :required="true"
                      style="width: 70%;"></el-input>
                  </el-form-item>
                  <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                  <div style="flex: 1;"></div>
                  <div class="drawer-buttons">
                    <el-button type="primary" @click="saveInterval">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form-item>
            <!-- cpu预警 -->
            <el-form-item label="cpu预警设置范围">
              <el-input placeholder="请输入预警范围" v-model="input3" disabled>
                <template #append>
                  <el-button icon="el-icon-cpu" @click.native="onChangesetcpu">

                  </el-button>
                </template>
              </el-input>
            </el-form-item>
            <!-- cpu抽屉 -->
            <el-form-item v-show="CPUVisible">
              <el-drawer title="设置CPU预警范围" :visible.sync="CPUVisible" :modal-append-to-body="false"
                :wrapperClosable="false" custom-class="demo-drawer">
                <el-form ref="drawerForm" :model="drawerForm" label-width="120px"
                  style="display: flex; flex-direction: column; height: 100%;">
                  <el-form-item label="设置警告范围" prop="cpumin" style="padding-left: 20px; margin-bottom:20px;">
                    <el-input placeholder="请输入要设置警告范围" v-model="drawerForm.cpumin" :required="true"
                      style="width: 70%;"></el-input>
                  </el-form-item>

                  <el-form-item label="设置危险范围" prop="cpumin" style="padding-left: 20px;">
                    <el-input placeholder="请输入要设置警告范围" v-model="drawerForm.cpumax" :required="true"
                      style="width: 70%;"></el-input>
                  </el-form-item>
                  <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                  <div style="flex: 1;"></div>
                  <div class="drawer-buttons">
                    <el-button type="primary" @click="savecpu">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form-item>
            <!-- 内存预警 -->
            <el-form-item label="内存预警设置范围">
              <el-input placeholder="请输入预警范围" v-model="input4" @input="onInputChange" disabled>
                <template #append>
                  <el-button icon="el-icon-cpu" @click.native="onChangesetmemory">

                  </el-button>
                </template>
              </el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" icon="el-icon-link" @click="save">保存</el-button>
              <el-button @click="onClean" icon="el-icon-setting">清空监控记录</el-button>
            </el-form-item>
            <!-- 内存抽屉 -->
            <el-form-item v-show="memoryVisible">
              <el-drawer title="设置内存预警范围时间" :visible.sync="memoryVisible" :modal-append-to-body="false"
                :wrapperClosable="false" custom-class="demo-drawer">
                <el-form ref="drawerForm" :model="drawerForm" label-width="120px"
                  style="display: flex; flex-direction: column; height: 100%;">
                  <el-form-item label="设置警告范围" prop="cpumin" style="padding-left: 20px; margin-bottom:20px;">
                    <el-input placeholder="请输入要设置警告范围" v-model="drawerForm.memorymin" :required="true"
                      style="width: 70%;"></el-input>
                  </el-form-item>

                  <el-form-item label="设置危险范围" prop="cpumin" style="padding-left: 20px;">
                    <el-input placeholder="请输入要设置警告范围" v-model="drawerForm.memorymax" :required="true"
                      style="width: 70%;"></el-input>
                  </el-form-item>
                  <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                  <div style="flex: 1;"></div>
                  <div class="drawer-buttons">
                    <el-button type="primary" @click="savememory">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { warningHandling } from '../../../api/Res/post'
import { saveMonitorSetting } from '../../../api/Res/post'
import { getSetting } from '../../../api/Res/get'
export default {
  data() {
    return {
      // 初始化设置
      status: true,//状态
      timeVisible: false,
      IntervalVisible: false,
      CPUVisible: false,
      memoryVisible: false,
      form: {
        // ... 其他数据
      },
      drawerForm: {
        number: '7',// 抽屉中表单的数据模型
        interval: '5',
        cpumin: '10',
        cpumax: '20',
        memorymin: '80',
        memorymax: '90',
      },
      input1: '7', // 添加 input1 属性
      input2: '5',
      input3: '60-70',
      input4: '80-90',
    };
  },
  mounted() {
    this.updateSetting();
  },
  beforeDestroy() {
    if (this.intervalId) {
      clearInterval(this.intervalId); // 清除定时器
    }
  },
  methods: {
    onClean() {
      this.$confirm('清除操作不可回滚, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '清除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消清除'
        });
      });
    },

    // 打开时间设置抽屉
    OnchangeStoreDays() {
      this.timeVisible = true;
    },
    // 关闭抽屉
    closeDrawer() {
      this.timeVisible = false;
      this.IntervalVisible = false;
      this.CPUVisible = false;
      this.memoryVisible = false;
      this.$message({
        type: 'info',
        message: '已取消'
      });
    },
    // 保存数量设置
    saveNumber() {
      // 实现保存时间的逻辑
      console.log('保存的数量为:', this.drawerForm.number);
      // 保存到drawerForm的time属性中
      this.input1 = this.drawerForm.number;
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '天数已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.timeVisible = false;
    },

    // 采集间隔
    OnchangeInterval() {
      this.IntervalVisible = true;
    },
    // 保存采集抽屉
    saveInterval() {
      // 实现保存时间的逻辑
      console.log('保存的采集时间为:', this.drawerForm.interval);
      // 保存到drawerForm的time属性中
      this.input2 = this.drawerForm.interval;
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '预警范围已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.IntervalVisible = false;
    },
    // cpu预警
    onChangesetcpu() {
      this.CPUVisible = true;
    },
    savecpu() {
      // 实现保存时间的逻辑
      console.log('保存的警告值为时间为:', this.drawerForm.cpumin);
      console.log('保存的危险值为时间为:', this.drawerForm.cpumax);
      // 检查输入是否为有效数字
      if (!isNaN(this.drawerForm.cpumin) && !isNaN(this.drawerForm.cpumax)) {
        // 将cpumin和cpumax的值拼接成字符串 "cpumin-cpumax"
        this.input3 = `${this.drawerForm.cpumin}-${this.drawerForm.cpumax}`;
        // 显示保存成功的提示
        this.$notify({
          title: '成功',
          message: '预警范围已成功保存',
          type: 'success',
          duration: 3000, // 提示框显示时间，单位毫秒
        });
        // 保存后关闭抽屉
        this.CPUVisible = false;
      } else {
        // 如果输入不是有效数字，显示错误提示
        this.$notify({
          title: '错误',
          message: '请输入有效的数字',
          type: 'error',
          duration: 3000, // 提示框显示时间，单位毫秒
        });
      }
    },
    // 内存预警
    onChangesetmemory() {
      this.memoryVisible = true;
    },
    savememory() {
      // 实现保存时间的逻辑
      console.log('保存的警告值为:', this.drawerForm.memorymin);
      console.log('保存的危险值为:', this.drawerForm.memorymax);
      // 检查输入是否为有效数字
      if (!isNaN(this.drawerForm.memorymin) && !isNaN(this.drawerForm.memorymax)) {
        // 将memorymin和memorymax的值拼接成字符串 "memorymin-memorymax"
        this.input4 = `${this.drawerForm.memorymin}-${this.drawerForm.memorymax}`;
        // 显示保存成功的提示
        this.$notify({
          title: '成功',
          message: '预警范围已成功保存',
          type: 'success',
          duration: 3000, // 提示框显示时间，单位毫秒
        });
        // 保存后关闭抽屉
        this.memoryVisible = false;
      } else {
        // 如果输入不是有效数字，显示错误提示
        this.$notify({
          title: '错误',
          message: '请输入有效的数字',
          type: 'error',
          duration: 3000, // 提示框显示时间，单位毫秒
        });
      }
    },
    onInputChange() {
      // 当 input4 的值发生变化时，更新 input4 的值
      this.input4 = `${this.drawerForm.memorymin}-${this.drawerForm.memorymax}`;
    },
    save() {
      saveMonitorSetting(this.status, this.drawerForm).then(result => {
        if (result.data.code === 2000) {
          this.$notify({
            title: '成功',
            message: '监控设置保存成功',
            type: 'success',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        } else {
          this.$notify({
            title: '失败',
            message: '监控设置保存失败',
            type: 'success',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        }
      })
      this.$notify({
        title: '成功',
        message: '监控设置成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      })
    },

    sendWarningRequest() {
      warningHandling(this.drawerForm).then(result => {
        if (result.data.code === 2000) {
        if(result.data.message.includes("已发送预警通知")){
          this.$notify({
            title: '预警通知',
            message: '系统发送告警通知，请到邮箱中查看详情！',
            type: 'success',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        }else{
          this.$notify({
            title: '预警通知',
            message: '系统发送告警通知异常，请检查！',
            type: 'success',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        }
        } else {
          //   this.$notify({
          //   title: '预警通知',
          //   message: '预警响应异常',
          //   type: 'success',
          //   duration: 3000, // 提示框显示时间，单位毫秒
          // });
          console.log("预警响应中")
        }
      })
    },
    updateSetting() {
      getSetting().then(result => {
        if (result.data !== null) {
          // alert(result.status);
          this.status = result.status;
          this.drawerForm = result.data;
          if (this.status) {
            // alert("hhhhhh");
            this.sendWarningRequest()
            this.interval = setInterval(this.sendWarningRequest, this.drawerForm.interval * 1000);
          }
          this.input1 = this.drawerForm.number;
          this.input2 = this.drawerForm.interval;
          this.input3 = `${this.drawerForm.cpumin}-${this.drawerForm.cpumax}`;
          this.input4 = `${this.drawerForm.memorymin}-${this.drawerForm.memorymax}`;
        } else {
          console.log("响应失败")
        }
      })
    }
  }
};
</script>

<style>
.set-main {
  background-color: #fff;
  border: 1px solid rgb(236, 226, 226);
  width: 95%;
  margin: 25px;
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