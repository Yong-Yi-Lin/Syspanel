<template>
  <div>
    <div ref="memoryEcharts" id="main-f"></div>
  </div>
</template>
      <!-- second -->
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
      memory_usage: 0,
      current_time: 0,
      showTime: [],
      showValue1: [],
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
      this.memoryEcharts = echarts.init(this.$refs.memoryEcharts)
      this.option = {
        title: {
          left: '3%',
          top: '5%',
          text: "内存利用率",//标题文本，支持使用 \n 换行。
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          align: 'right',//文字在前图标在后
          left: '3%',
          top: '15%',
          data: ['内存利用率']
        },
        grid: {
          top: '30%',
          left: '5%',
          right: '5%',
          bottom: '5%',
          containLabel: true
        },
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
          // data: ['7.8', '7.9', '7.10', '7.11', '7.12', '7.13', '7.14']
          data: this.showTime
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: "内存利用率%",
          // data: [60, 80, 100, 120, 200, 170, 600],
          data: this.showValue1,
          type: 'line',
          // smooth: true,
          symbol: "none",
          // areaStyle: {
          //   color: '#344CE9',
          //   opacity: 0.5
          // }，
          areaStyle: {
            //显示区域颜色---渐变效果
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [{
                offset: 0, color: ' #344CE9' // 0% 处的颜色
              }, {
                offset: 1, color: '#ffffff' // 100% 处的颜色
              }],
              global: false // 缺省为 false
            }
          },
          itemStyle: {
            color: ' #344CE9', //改变折线点的颜色
            lineStyle: {
              color: 'rgb(255,96,64)' //改变折线颜色
            }

          },
        }]
      };
      this.memoryEcharts.setOption(this.option);
    },
    fetchData() {
      getSystemInfoCri().then(res => {
        if (res.data.code === 2000) {
          this.all_data = res.data.data;
          // console.log(this.all_data);
          this.memory_usage = this.all_data.memoryUtilization;
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
      }

      this.showTime.push(this.current_time);
      this.showValue1.push(this.memory_usage);
      // alert(this.showTime);
      this.option && this.memoryEcharts.setOption(this.option);
      // this.memoryEcharts.setOption(this.option);
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
      
<style >
#main-f {
  width: 100%;
   height:390px;
  margin: auto;
}
</style>