<template>
  <div ref="dangerChart"
    style="width: 100px; height: 100px; background-color: #ffffff;   margin-bottom: 20px; margin-left: 20px;"></div>
</template>


<script>
import * as echarts from 'echarts'
{/* import { getWarn_Number } from '../../api/Res/get' */ }
import { getDanger_Number } from '../../api/Res/get'

export default {
  data() {
    return {
      number: 30,
      option: {}
    }
  },
  mounted() {
    this.fetchData();
    this.interval = setInterval(this.fetchData, 1000);
  },
  methods: {
    drawChart() {
      this.dangerChart = echarts.init(this.$refs.dangerChart)
      this.option = {
        series: [
          {
            name: '危险',
            type: 'gauge',
            center: ['50%', '55% + 850px'],
            radius: '100%', // 调整半径为原始的一半
            min: 0,
            max: 100,
            itemStyle: {
              color: '#FF0000',
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
              { value: this.number, name: "危险" }
            ]
          }
        ]
      }
      this.option && this.dangerChart.setOption(this.option);
    },
    fetchData() {
      getDanger_Number().then(res => {
        if (res.data.code === 2000) {
          // alert(res.data.data);
          this.number = res.data.data;
          // alert(this.number);
          // this.option && this.gaugeChart.setOption(this.option);
          this.drawChart();
        } else {
          // alert(res.data.message);
          this.number = 30;
          this.drawChart();
        }
      })
    },
  }
}
</script>
