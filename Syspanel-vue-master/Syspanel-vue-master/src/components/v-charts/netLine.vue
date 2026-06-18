<template>
  
    <!--echarts的容器-->
    <div ref="netEcharts" id="main-four" style="width: 100%;height: 420px;background:#fff"></div>
 
</template>
  
<script>
import * as echarts from 'echarts'
import { getSystemInfoCri } from '../../api/Res/get'
// #344CE9
export default {
  // name: "memoryEcharts",
  props: {
    intervalTime: {
      type: String,
      required: true
    },
  },
  data() {
    return {
      all_data: {},
      networkDataReceived: 0,
      networkDataSent: 0,
      current_time: 0,
      showTime: [],
      showValue1: [],
      showValue2: [],
      option: {},
      memoryEcharts: null,
      match: 1
    }
  },
  mounted() {
    this.drawChart();
    // this.fetchData();
    // this.interval = setInterval(this.fetchData, 1000);
    this.IntervalFetchData();
  },
  methods: {
    drawChart() {
      this.netEcharts = echarts.init(this.$refs.netEcharts)
      this.option = {
        title: {
          text: '网络接发情况'
        },
        // 提示框
        tooltip: {
          trigger: 'axis'
        },
        // 图例
        legend: {
          icon: 'circle',
          left: 'center',
          top: 0,
          data: ['网络接收', '网络发送']
        },
        grid: {
          left: '3%',
          right: '3%',
          bottom: '3%',
          containLabel: true
        },
        // 工具栏
        toolbox: {
          show: true,
          feature: {

            dataView: { readOnly: false },
            magicType: { type: ['line', 'bar'] },
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          // data: ['01：00', '02：00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00']
          data: this.showTime
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '网络接收bytes',
            type: 'line',
            // smooth: true, // 平滑曲线显示
            // data: [120, 132, 101, 134, 190, 230, 210, 201, 234, 290, 230, 210]
            data: this.showValue1,
            areaStyle: {
              //显示区域颜色---渐变效果
              color: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                  offset: 0, color: 'rgb(255,200,213)' // 0% 处的颜色
                }, {
                  offset: 1, color: '#ffffff' // 100% 处的颜色
                }],
                global: false // 缺省为 false
              }
            },
            itemStyle: {
              color: 'rgb(255,96,64)', //改变折线点的颜色
              lineStyle: {
                color: 'rgb(255,96,64)' //改变折线颜色
              }
            },
          },
          {
            name: '网络发送bytes',
            type: 'line',
            // smooth: true,
            // data: [100, 82, 91, 54, 90, 76, 110, 81, 104, 90, 130, 110]
            data: this.showValue2,
            areaStyle: {
              //显示区域颜色---渐变效果
              color: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                  offset: 0, color: 'rgb(255,200,213)' // 0% 处的颜色
                }, {
                  offset: 1, color: '#ffffff' // 100% 处的颜色
                }],
                global: false // 缺省为 false
              }
            },
            itemStyle: {
              color: 'rgb(255,96,64)', //改变折线点的颜色
              lineStyle: {
                color: 'rgb(255,96,64)' //改变折线颜色
              }
            },

          }
        ]
      }
      this.netEcharts.setOption(this.option);
    },
    fetchData() {
      getSystemInfoCri().then(res => {
        if (res.data.code === 2000) {
          this.all_data = res.data.data;
          // console.log(this.all_data);
          this.networkDataReceived = this.all_data.networkDataReceived;
          this.networkDataSent = this.all_data.networkDataSent;
          this.current_time = this.all_data.currentTime;
          this.updateCharts();
        } else {
          alert(res.data.message);
        }
      })
    },
    updateCharts() {
      // 更新图表数据
      if (this.showTime.length >= 4) {
        this.showTime.shift();
        this.showValue1.shift();
        this.showValue2.shift();
      }

      this.showTime.push(this.current_time);
      this.showValue1.push(this.networkDataReceived);
      this.showValue2.push(this.networkDataSent);
      // alert(this.showTime);
      this.option && this.netEcharts.setOption(this.option);
      // this.netEcharts.setOption(this.option);
    },
    IntervalFetchData() {
      this.intervalId = setInterval(() => {
        this.fetchData();
        this.match = this.intervalTime.match(/\d+/);
        // console.log(this.match * 1000 + typeof (this.match))
      }, this.match * 1000);
    }
  },
  watch: {
    match() {
      // 当 completionRate 变化时，停止当前的定时器
      if (this.intervalId) {
        clearInterval(this.intervalId);
      }
      // 根据新的 completionRate 值重新设置定时器
      this.IntervalFetchData();
    }
  }
}
</script>
     
<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
.main-four{
  width: 600px;
  height: 300px;
  margin: auto;
}
</style>