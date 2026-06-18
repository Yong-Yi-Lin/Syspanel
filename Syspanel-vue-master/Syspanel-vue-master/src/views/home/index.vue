<template>
  <div class="home">
    <!-- <div class="page-content">首页</div> -->
    <div class="home-main">
      <el-row :gutter="20">
        <el-col :span="14">
          <el-card shadow="hover" style="width: 115%; border: solid 1px 	#1E90FF;">
            <template v-slot:header>
              <div style="display: flex; align-items: center;">
                <div style="width: 5px; height: 15px; background-color: #007bff; border-radius: 5px; margin-right: 8px;">
                </div>
                <span style="font-weight: bold; font-size: 24px;">监控概览</span>
                <button class="showmain" @click="showmain">点击返回主图</button>
              </div>
            </template>
            <div class="h-overview">
              <el-row>
                <el-col :span="6" align="center" @click.native="showCPU">
                  <span>CPU</span>
                  <Ball :completionRate="websiteCompletionRate"></Ball>
                </el-col>
                <el-col :span="6" align="center" @click.native="showdatabase">
                  <span>磁盘</span>
                  <Ball :completionRate="databaseCompletionRate"></Ball>
                </el-col>
                <el-col :span="6" align="center" @click.native="showmemory">
                  <span>内存</span>
                  <Ball :completionRate="tasksCompletionRate"></Ball>
                </el-col>
                <el-col :span="6" align="center" @click.native="showapps">
                  <span>网络</span>
                  <Ball :completionRate="appsCompletionRate"></Ball>
                </el-col>
              </el-row>
            </div>
          </el-card>

          <router-link to="/datadetail/monitor">

            <el-card shadow="hover"
              style="width: 115%; margin-top: 20px; margin-bottom:40px; border: solid 1px 	#1E90FF;">

              <template v-slot:header>
                <div style="display: flex; align-items: center;">
                  <div
                    style="width: 5px; height: 15px;  background-color: #007bff; border-radius: 5px; margin-right: 8px;">
                  </div>
                  <span style="font-weight: bold; font-size:24px">监控详情</span>
                </div>
              </template>
              <div class="detail">
                <!-- <First/> -->
                <!-- <Index/> -->
                <!-- 动态展示组件 -->
                <component :is="currentComponent">
                </component>
              </div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="10">
          <el-card shadow="hover" style="width: 80%; float: right; border: solid 1px 	#1E90FF;">
            <template v-slot:header>
              <div style="display: flex; align-items: center;">
                <div style="width: 5px; height: 15px;  background-color: #007bff; border-radius: 5px; margin-right: 8px;">
                </div>
                <span style="font-weight: bold; font-size:24px">主机信息概览</span>
              </div>

            </template>
            <el-row>

              <!-- <router-link to="/process">
              <el-col :span="11" style="float: right;"  >
            
                    <Num></Num>
              </el-col>
            </router-link>

              <router-link to="/remote">
              <el-col :span="11"  style="float: left;">
            
                    <Con></Con>                               
              </el-col>
            </router-link> -->
              <el-scrollbar>
                <el-descriptions :column="1" class="h-systemInfo">
                  <el-descriptions-item class-name="system-content" label="主机名" class="custom-label">
                    <span class="system-contents" v-bind:title="hostInfo.hostName">{{ hostInfo.hostName }}</span>
                  </el-descriptions-item>
                  <el-descriptions-item class-name="system-content" label="操作系统名称">
                    <span class="system-contents" v-bind:title="hostInfo.osName">{{ hostInfo.osName
                    }}</span>
                  </el-descriptions-item>
                  <el-descriptions-item class-name="system-content" label="操作系统版本">
                    <span class="system-contents" v-bind:title="hostInfo.osVersion">{{ hostInfo.osVersion
                    }}</span>
                  </el-descriptions-item>
                  <el-descriptions-item class-name="system-content" label="操作系统类型">
                    <span class="system-contents" v-bind:title="hostInfo.osArch">{{ hostInfo.osArch }}</span>
                  </el-descriptions-item>
                  <el-descriptions-item class-name="system-content" label="系统IP地址">
                    <span class="system-contents" v-bind:title="hostInfo.ipAddress">{{ hostInfo.ipAddress }}</span>
                  </el-descriptions-item>
                  <el-descriptions-item class-name="system-content" label="处理器核心数">
                    <span class="system-contents" v-bind:title="hostInfo.coresNumber">{{ hostInfo.coresNumber }}</span>
                  </el-descriptions-item>
                </el-descriptions>
              </el-scrollbar>

            </el-row>
          </el-card>

          <router-link to="/info/warnlog">
            <el-card shadow="hover" style="width: 80%; float: right;margin-top: 20px; border: solid 1px 	#1E90FF;">
              <!-- 警告概览 -->
              <template v-slot:header>
                <div style="display: flex; align-items: center;">
                  <div
                    style="width: 5px; height: 15px;  background-color: #007bff; border-radius: 5px; margin-right: 8px;">
                  </div>
                  <span style="font-weight: bold; font-size:24px">警告概览</span>
                </div>

              </template>
              <el-col :span="11" style="float: right; ">
                <Danger />
              </el-col>
              <el-col :span="11" style="float: left; ">
                <Warns />
              </el-col>

            </el-card>
          </router-link>
          <router-link to="/info/personallog">
            <el-card shadow="hover"
              style="width: 80%; float: right; margin-top: 20px;border: solid 1px 	#1E90FF;margin-bottom: 40px;">
              <!-- 消息通知 -->
              <template v-slot:header>
                <div style="display: flex; align-items: center;">
                  <div
                    style="width: 5px; height: 15px;  background-color: #007bff; border-radius: 5px; margin-right: 8px;">
                  </div>
                  <span style="font-weight: bold; font-size:24px">日志概览</span>
                </div>
              </template>
              <Notice></Notice>
            </el-card>
          </router-link>

        </el-col>
      </el-row>
    </div>

  </div>
</template>
<script scope>
import Index from '../../components/v-charts/index.vue';
import second from "../../components/v-charts/second.vue";
import third from "../../components/v-charts/third.vue";
import netLine from '../../components/v-charts/netLine.vue';
import Six from '../../components/v-charts/six.vue';

import { getSystemInfoCri } from "../../api/Res/get";
import {getHostInfo} from "../../api/Res/get"

export default {
  data() {
    return {
      hostInfo: {
        hostName: 'rue',
        osName: 'win11家庭版',
        osVersion: ' 20',
        osArch: ' x86_64',
        ipAddress: '192.168.10.7 ',
        coresNumber: ' 16 ',
      },
      websiteCompletionRate: 80,
      databaseCompletionRate: 70,
      tasksCompletionRate: 90,
      appsCompletionRate: 85,
      currentComponent: Index,
    };
  },
  components: {
    Index,
    second,
    third,
    netLine,
    Six
  },
  mounted() {
    this.$notify({
                title: '提示',
                type: 'warning',
                message: '点击组件可以进入详细页面和不同概览',
                duration: 1500,
                dangerouslyUseHTMLString: true,
                customClass:'notify-success',
              });

    this.getHostData();
    this.dynamicData();
    this.interval = setInterval(this.dynamicData, this.completionRate*1000);
   
  },
  methods: {
    // 点击cpu
    showCPU() {
      this.currentComponent = 'second';
    },
    // 点击磁盘
    showdatabase() {
      this.currentComponent = 'third';
    },
    // 点击内存
    showmemory() {
      this.currentComponent = 'Six';
    },
    // 点击网络
    showapps() {
      this.currentComponent = 'netLine';
    },
    // 返回原点
    showmain() {
      this.currentComponent = 'Index';
    },
    dynamicData() {
      let data;
      getSystemInfoCri().then(resp => {
        if (resp.data.code === 2000) {
          // console.log(resp.data.data);
          data = resp.data.data
          this.websiteCompletionRate = data.cpuUtilization
          this.databaseCompletionRate = data.diskUtilization
          this.tasksCompletionRate = data.memoryUtilization
          this.appsCompletionRate = (data.networkDataSent/data.networkDataReceived)*100
        } else {
          alert("响应失败");
        }
      })
    },
    getHostData(){
      getHostInfo().then(resp => {
        if(resp.data.code === 2000){
          // alert(resp.data.data);
          this.hostInfo = resp.data.data;
        }else{
          alert("获取主机信息失败");
        }
      })
    }
  }
};
</script>

<style scope >
.home-main {
  margin: 10px;
  padding: 10px;
  box-sizing: border-box;
  overflow: auto;
}

.card-content {
  width: 100%;
  display: inline-block;
}

.song-lyrics {
  font-size: 18px;
  text-align: center;
  margin-top: 35px;
}

.el-card__body {
  padding: 20px;
}

.circle-progress-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;

}


.circle-progress-item {
  text-align: center;
}

.circle {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.circle-name {
  margin-top: 10px;
  color: rgb(123, 125, 125);
  font-size: 12px;
}

.h-overview {
  text-align: center;

  span:first-child {
    font-size: 14px;
    color: rgb(92, 92, 92);
  }
}

.conuts {
  span {
    font-size: 16px;
    color: rgb(21, 50, 239);
    font-weight: 500;
    line-height: 32px;
    cursor: pointer;
  }
}
</style>
<style scoped>
.h-systemInfo {
  margin-left: 18px;
  height: 216px;
}

@-moz-document url-prefix() {
  .h-systemInfo {
    height: auto;
  }
}

.system-label {
  font-weight: 400 !important;
  font-size: 14px !important;
  color: #1f2329;
}

.system-content {
  font-size: 13px !important;
}
</style>

<style scoped>
.demo-form-inline {
  float: right;

}
.detail{
    height: 410px;
  }
</style>



<style scoped>
.chart-container {
  width: 600px;
  height: 300px;
  margin: 0 auto;
  border: 1px solid #e5e5e5;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>


  
<style scope >  
.button-with-count {
    display: inline-flex;
    flex-direction: column;
    align-items: center;
    margin-right: 10px;
    border: 1px solid #cdcdcd;
    border-radius: 5px;
    /* 圆角 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    /* 阴影效果 */
    transition: all 0.3s ease;
    /* 平滑过渡效果 */
    margin: 50px;
    width: 20%;
    height: 80px;
    /* 根据需要调整按钮之间的间距 */
  }

  .custom-button {
    border: 1px solid #ccc;
    /* 添加边框 */
    border-radius: 4px;
    /* 可选：添加边框圆角 */
    margin-top: 10px;
  }

  .count {
    margin-top: 5px;
    /* 调整计数与按钮之间的间距 */
    font-size: 14px;
    /* 根据需要调整字体大小 */
  }

  .router-link {
    text-decoration: none;
    /* Remove underline */
    color: inherit;
    /* Inherit text color */
  }

  .showmain {
    float: right;
    font-size: 18px;
    font-weight: bold;
    color: #007bff;
    background-color: #fff;
    border: blue solid 3px;
    border-radius: 5px;
    margin-left: 540px;
    padding: 10px 20px;
    /* 添加内边距 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    /* 添加阴影效果 */
    transition: background-color 0.3s;
    /* 添加背景颜色过渡效果 */

  }

  .showmain:hover {
    border: rgb(255, 0, 0) solid 2px;
    /* 鼠标悬停时的背景颜色 */
  }

  .h-systemInfo .el-descriptions-item__label {

    color: black;

  }
 .el-notification__title {
    font-weight: 700;
    font-size: 16px;
    color: #303133;
    margin: 0px;
    /* margin-right: 128px; */
    padding-left: 0px;
}
</style>
