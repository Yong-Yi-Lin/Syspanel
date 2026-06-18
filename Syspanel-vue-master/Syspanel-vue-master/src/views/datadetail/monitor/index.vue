<template>
  <div class="monitor-main">
    <el-row :gutter="20">

      <el-col :span="24">
        <el-card shadow="hover" style="width: 100%; margin-top: 20px; margin:auto; border: solid 1px 	#1E90FF;">
          <div slot="header" class="card-content">
            <div style="display: flex; align-items: center;">
              <div style="width: 5px; height: 15px;  background-color: #007bff; border-radius: 5px; margin-right: 8px;">
              </div>
              <span style="font-weight: bold; font-size:24px">系统利用率</span>
            </div>

            <el-form :inline="true" class="demo-form-inline">
              <el-input placeholder="设置监控间隔" disabled v-model="inputtime" style="width: 200px;">
                <template #append>
                  <el-button icon="el-icon-timer" @click.native="Onchangetime">
                  </el-button>
                </template>
              </el-input>
              <!-- 负载抽屉 -->

              <el-drawer 
              title="设置监控间隔(秒)" 
              :visible.sync="timesVisible" :modal-append-to-body="false"
                :wrapperClosable="false" custom-class="demo-drawer">
                <el-form ref="monitorForm" 
                :model="monitorForm"
                 label-width="60px"
                  style="display: flex;
                   flex-direction: column; 
                   height: 100%;">
                  <el-form-item label="时间" prop="days" style="padding-left: 20px;">
                    <el-input placeholder="请输入要设置时间（S）" v-model="monitorForm.time" :required="true"
                      style="width: 70%;"></el-input>
                  </el-form-item>

                  <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                  <div style="flex: 1;"></div>

                  <div class="drawer-buttons">
                    <el-button type="primary" @click="saveTime">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form>


          </div>
          <!-- <First /> -->
          <First :intervalTime=inputtime></First>


        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover" style="width: 100%; margin-top:20px;border: solid 1px 	#1E90FF;">
          <div slot="header" class="card-content">
            <div style="display: flex; align-items: center;">
              <div style="width: 5px; height: 15px;  background-color: #007bff; border-radius: 5px; margin-right: 8px;">
              </div>
              <span style="font-weight: bold; font-size:24px">CPU</span>
            </div>

            <el-form :inline="true" class="demo-form-inline">
              <el-input placeholder="设置监控间隔" disabled v-model="inputcpu" style="width: 200px;">
                <template #append>
                  <el-button icon="el-icon-timer" @click.native="Onchangecpu">
                  </el-button>
                </template>
              </el-input>
              <!-- cpu抽屉 -->

              <el-drawer title="设置监控间隔(秒)" :visible.sync="cpuVisible" :modal-append-to-body="false"
                :wrapperClosable="false" custom-class="demo-drawer">
                <el-form ref="monitorForm" :model="monitorForm" label-width="60px"
                  style="display: flex; flex-direction: column; height: 100%;">
                  <el-form-item label="时间" prop="days" style="padding-left: 20px;">
                    <el-input placeholder="请输入要设置时间（S）" v-model="monitorForm.cpu" :required="true"
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
            </el-form>
          </div>
          <!-- <Second /> -->
          <Second :intervalTime=inputcpu></Second>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover" style="width: 100%; margin-top: 20px;border: solid 1px 	#1E90FF;">
          <div slot="header" class="card-content">
            <div style="display: flex; align-items: center;">
              <div style="width: 5px; height: 15px;  background-color: #007bff; border-radius: 5px; margin-right: 8px;">
              </div>
              <span style="font-weight: bold; font-size:24px">内存</span>
            </div>
            <el-button style="margin-right: 160px;margin-top: 10px;" icon="el-icon-refresh"
              @click="changeMemory">切换样式</el-button>
            <el-form :inline="true" class="demo-form-inline">
              <el-input placeholder="设置监控间隔" disabled v-model="inputmemory" style="width: 200px;">
                <template #append>
                  <el-button icon="el-icon-timer" @click.native="Onchangememory">
                  </el-button>
                </template>
              </el-input>
              <!-- 内存抽屉 -->

              <el-drawer title="设置监控间隔(秒)" :visible.sync="memoryVisible" :modal-append-to-body="false"
                :wrapperClosable="false" custom-class="demo-drawer">
                <el-form ref="monitorForm" :model="monitorForm" label-width="60px"
                  style="display: flex; flex-direction: column; height: 100%;">
                  <el-form-item label="时间" prop="days" style="padding-left: 20px;">
                    <el-input placeholder="请输入要设置时间（S）" v-model="monitorForm.memory" :required="true"
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
            </el-form>
          </div>

          <div v-if="this.isShow">
            <Six :intervalTime=inputmemory></Six>
          </div>
          <div v-else>
            <Five :intervalTime=inputmemory></Five>
          </div>

        </el-card>

      </el-col>
      <el-col :span="12">
        <el-card shadow="hover" style="width: 100%; margin-top: 20px;margin-bottom:60px;border: solid 1px 	#1E90FF;">
          <div slot="header" class="card-content">
            <div style="display: flex; align-items: center;">
              <div style="width: 5px; height: 15px;  background-color: #007bff; border-radius: 5px; margin-right: 8px;">
              </div>
              <span style="font-weight: bold; font-size:24px">磁盘IO</span>
            </div>

            <el-form :inline="true" class="demo-form-inline">
              <el-input placeholder="设置监控间隔" disabled v-model="inputdisk" style="width: 200px;">
                <template #append>
                  <el-button icon="el-icon-timer" @click.native="Onchangediskio">
                  </el-button>
                </template>
              </el-input>
              <!-- 磁盘IO抽屉 -->

              <el-drawer title="设置监控间隔(秒)" :visible.sync="diskVisible" :modal-append-to-body="false"
                :wrapperClosable="false" custom-class="demo-drawer">
                <el-form ref="monitorForm" :model="monitorForm" label-width="60px"
                  style="display: flex; flex-direction: column; height: 100%;">
                  <el-form-item label="时间" prop="days" style="padding-left: 20px;">
                    <el-input placeholder="请输入要设置时间（S）" v-model="monitorForm.disk" :required="true"
                      style="width: 70%;"></el-input>
                  </el-form-item>

                  <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                  <div style="flex: 1;"></div>

                  <div class="drawer-buttons">
                    <el-button type="primary" @click="savedisk">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form>
          </div>
          <!-- <Third /> -->
          <Third :intervalTime=inputdisk></Third>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover" style="width: 100%; margin-top: 20px; margin-bottom:60px;border: solid 1px 	#1E90FF;">
          <div slot="header" class="card-content">
            <div style="display: flex; align-items: center;">
              <div style="width: 5px; height: 15px;  background-color: #007bff; border-radius: 5px; margin-right: 8px;">
              </div>
              <span style="font-weight: bold; font-size:24px">网络IO</span>
            </div>
            <el-button style="margin-right: 160px; margin-top: 10px;" icon="el-icon-refresh"
              @click="changeNet">切换样式</el-button>

            <el-form :inline="true" class="demo-form-inline">
              <el-input placeholder="设置监控间隔" disabled v-model="inputnet" style="width: 200px;">
                <template #append>
                  <el-button icon="el-icon-timer" @click.native="Onchangenet">
                  </el-button>
                </template>
              </el-input>
              <!-- 网络IO抽屉 -->

              <el-drawer title="设置监控间隔(秒)" :visible.sync="netVisible" :modal-append-to-body="false"
                :wrapperClosable="false" custom-class="demo-drawer">
                <el-form ref="monitorForm" :model="monitorForm" label-width="60px"
                  style="display: flex; flex-direction: column; height: 100%;">
                  <el-form-item label="时间" prop="days" style="padding-left: 20px;">
                    <el-input placeholder="请输入要设置时间（S）" v-model="monitorForm.net" :required="true"
                      style="width: 70%;"></el-input>
                  </el-form-item>

                  <!-- 使用flex-grow: 1的div作为spacer，确保按钮位于底部 -->
                  <div style="flex: 1;"></div>

                  <div class="drawer-buttons">
                    <el-button type="primary" @click="savenet">保存</el-button>
                    <el-button @click="closeDrawer">取消</el-button>
                  </div>
                </el-form>
              </el-drawer>
            </el-form>
          </div>

          <!-- <Four /> -->
          <!-- <currentComponent2 :intervalTime=inputnet></currentComponent2> -->
          <div v-if="showPie">
            <netLine :intervalTime=inputnet></netLine>
          </div>
          <div v-else>
            <Four :intervalTime=inputnet></Four>
          </div>


        </el-card>

      </el-col>



    </el-row>

  </div>
</template>
<script>
import First from '../../../components/v-charts/first.vue'
import Second from '../../../components/v-charts/second.vue'
import Third from '../../../components/v-charts/third.vue'
import Four from '../../../components/v-charts/four.vue'
import Five from '../../../components/v-charts/five.vue'
import netLine from '../../../components/v-charts/netLine.vue';
import Six from '../../../components/v-charts/six.vue';
// import Notice from '../../../components/Notice/index.vue'
export default {
  components: {
    First,
    Second,
    Third,
    Four,
    Five,
    netLine,
    Six,
    // Notice
  },
  // 其他代码
  data() {
    return {
      // 初始化设置
      value1: '',
      timesVisible: false,
      netVisible: false,
      diskVisible: false,
      memoryVisible: false,
      cpuVisible: false,

      inputtime: '1 (S)',
      inputcpu: '1 (S)',
      inputmemory: '1 (S)',
      inputdisk: '1 (S)',
      inputnet: '1 (S)',
      // 抽屉数据
      monitorForm: {
        time: 1,
        cpu: 1,
        memory: 1,
        disk: 1,
        net: 1,
      },
      // currentComponent1: this.Five,
      // currentComponent2: this.Four
      // showFive: true,
      // showSix: false,
      // showFour: true,
      // showNetLine: false
      isShow:true,
      showPie:true
    };
  },
  mounted() {
    this.$notify({
      title: '提示',
      type: 'warning',
      message: '点击设置按钮修改监控间隔',
      duration: 1500
    });
  },
  methods: {
    changeMemory() {
      // this.currentComponent1 = this.components.Six;
      // this.showFive = false;
      // this.showSix = true;
      this.isShow = !this.isShow;
    },
    changeNet() {
      // this.currentComponent2 = this.components.netLine;
      // this.showFour = false;
      // this.showNetLine = true;
      this.showPie = !this.showPie;
    },
    //关闭抽屉
    closeDrawer() {
      this.timesVisible = false;
      this.cpuVisible = false;
      this.memoryVisible = false;
      this.diskVisible = false;
      this.netVisible = false;
      this.$message({
        type: 'info',
        message: '已取消'
      });
    },
    // 打开负载抽屉
    Onchangetime() {
      this.timesVisible = true;
    },
    // 保存时间设置
    saveTime() {
      // 保存到drawerForm的time属性中
      this.inputtime = this.monitorForm.time + '(S)';
      // alert(this.monitorForm.time)
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.timesVisible = false;
    },
    // cpu抽屉
    Onchangecpu() {
      this.cpuVisible = true;
    },
    savecpu() {
      // 保存到drawerForm的time属性中
      this.inputcpu = this.monitorForm.cpu + '(S)';
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.cpuVisible = false;
    },
    // 内存抽屉
    Onchangememory() {
      this.memoryVisible = true;
    },
    savememory() {
      // 保存到drawerForm的time属性中
      this.inputmemory = this.monitorForm.memory + '(S)';
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.memoryVisible = false;
    },
    // 磁盘抽屉
    Onchangediskio() {
      this.diskVisible = true;
    },
    savedisk() {
      // 保存到drawerForm的time属性中
      this.inputdisk = this.monitorForm.disk + '(S)';
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.diskVisible = false;
    },
    // 网络抽屉
    Onchangenet() {
      this.netVisible = true;
    },
    savenet() {
      // 保存到drawerForm的time属性中
      this.inputnet = this.monitorForm.net + '(S)';
      // 显示保存成功的提示
      this.$notify({
        title: '成功',
        message: '已成功保存',
        type: 'success',
        duration: 3000, // 提示框显示时间，单位毫秒
      });
      // 保存后关闭抽屉
      this.netVisible = false;
    }
  }
}
</script>
<style >
.monitor-main {
  padding: 20px 20px 0px 20px;
}

.card-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
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