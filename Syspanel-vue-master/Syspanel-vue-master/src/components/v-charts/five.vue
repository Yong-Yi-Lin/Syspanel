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
      usedMemory: 60,
      freeMemory: 40,
      // current_time: 0,
      // showTime: [],
      // value_use:0,
      // value_free:0,
      // showValue1: [],
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
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: '内存使用情况',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 30,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: this.usedMemory, name: '使用内存GB', itemStyle: { color: '#75bedc' } },
              { value: this.freeMemory, name: '剩余内存GB', itemStyle: { color: '#fc8251' } },
            ]
          }
        ]
      };
      this.memoryEcharts.setOption(this.option);
    },
    fetchData() {
      getSystemInfoCri().then(res => {
        if (res.data.code === 2000) {
          this.all_data = res.data.data;
          // console.log(this.all_data);
          this.usedMemory = this.all_data.usedMemory;
          this.freeMemory = this.all_data.freeMemory;
          // this.current_time = this.all_data.currentTime;
          this.updateCharts();
        } else {
          alert(res.data.message);
        }
      })
    },
    updateCharts() {
      // 更新图表数据
      // if (this.showTime.length >= 4) {
      //   this.showTime.shift();
      //   this.showValue1.shift();
      // }

      // this.showTime.push(this.current_time);
      // this.showValue1.push(this.memory_usage);
      // this.option && this.memoryEcharts.setOption(this.option);
      this.option.series[0].data[0].value = this.usedMemory;
      this.option.series[0].data[1].value = this.freeMemory;
      this.memoryEcharts.setOption(this.option);
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
  height:400px;
  margin: auto;
}
</style>