<template>
  <div ref="gaugeChart"
    style="width: 100px; height: 100px; background-color: #ffffff;   margin-bottom: 20px; margin-left: 20px;"></div>
</template>

<script>
import * as echarts from 'echarts'
import { getWarn_Number } from '../../api/Res/get'
// import {getDanger_Number} from '../../api/Res/get'

export default {
  data() {
    return {
      number: 20,
      option: {}
    }
  },
  mounted() {
    
    this.fetchData();
    this.interval = setInterval(this.fetchData, 1000);
  },
  methods: {
    drawChart() {
      this.gaugeChart = echarts.init(this.$refs.gaugeChart)
      this.option = {
        series: [
          {
            name: '警告',
            type: 'gauge',
            center: ['50%', '55% + 850px'], // 调整中心位置，使其向上移动 50px
            radius: '100%', // 调整半径为原始的一半
            min: 0,
            max: 100,
            itemStyle: {
              color: '#FF7F50',
              shadowColor: 'rgba(0,138,255,0.45)'
            },
            // 进度条
            progress: {
              show: true,
              width: 10, // 调整宽度为原始的一半
              roundCap: true
            },
            // 坐标轴线
            axisLine: {
              show: true,
              roundCap: true,
              lineStyle: {
                width: 10 // 调整线宽为原始的一半
              }
            },
            // 仪表盘指针
            pointer: {
              show: false
            },
            // 刻度标签
            axisLabel: {
              show: false
            },
            // 刻度
            axisTick: {
              show: false
            },
            // 分隔线
            splitLine: {
              show: false
            },
            title: {
              offsetCenter: [0, '20%'],
              fontSize: 10 // 调整字体大小为原始的一半
            },
            detail: {
              offsetCenter: [0, '-10%'],
              valueAnimation: true,
              textStyle: {
                fontSize: 15 // 调整字体大小为原始的一半
              },
              formatter: '{value}'
            },
            data: [
              {
                value: this.number,
                name: "警告"
              }
            ]
          }
        ]
      }
      this.option && this.gaugeChart.setOption(this.option);
    },
    fetchData() {
      getWarn_Number().then(res => {
        if (res.data.code === 2000) {
          // alert(res.data.data);
          this.number = res.data.data;
          // alert(this.number);
          // this.option && this.gaugeChart.setOption(this.option);
          this.drawChart();
        } else {
          // alert(res.data.message);
          this.drawChart();
        }
      })
    },
  }
}
</script>
