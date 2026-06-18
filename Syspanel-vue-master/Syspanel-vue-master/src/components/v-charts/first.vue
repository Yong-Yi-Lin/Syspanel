<template>
  <div ref="lineChart"
    :style="{ width: '1200px', height: '400px', backgroundColor: '#ffffff', padding: '0px', borderRadius: '20px' }"></div>
</template>
<!-- first -->

<script>
import * as echarts from 'echarts'
import { getSystemInfoCri } from '../../api/Res/get'

export default {
  props: {
    intervalTime: {
      type: String,
      required: true
    },
  },
  data() {
    return {
      all_data: {},
      cpu_usage: 0,
      // data_use: 0,
      memory_usage: 0,
      disk_usage: 0,
      current_time: 0,
      showTime: [],
      showValue1: [],
      showValue2: [],
      showValue3: [],
      // myChart1: null,
      // myChart2: null,
      // myChart3: null,
      // option1: {},
      // option2: {},
      // option3: {}
      option: {},
      lineChart: null,
      match: 1
    }
  },
  mounted() {
    this.drawChart();
    this.IntervalFetchData();
    // this.fetchData();
    // alert(this.completionRate);
    // this.interval = setInterval(this.fetchData, this.completionRate*1000);
  },
  methods: {
    drawChart() {
      this.lineChart = echarts.init(this.$refs.lineChart)
      this.option = {
        title: {
          text: '系统利用率总览'
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
          data: ['cpu利用率%', '内存利用率%', '磁盘利用率%']
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
          name: '时间',
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
            name: 'cpu利用率%',
            type: 'line',
            // smooth: true, // 平滑曲线显示
            // data: [120, 132, 101, 134, 190, 230, 210, 201, 234, 290, 230, 210]
            data: this.showValue1
          },
          {
            name: '内存利用率%',
            type: 'line',
            // smooth: true,
            // data: [100, 82, 91, 54, 90, 76, 110, 81, 104, 90, 130, 110]
            data: this.showValue2
          },
          {
            name: '磁盘利用率%',
            type: 'line',
            stack: '总量',
            // smooth: true,
            // data: [10, 22, 21, 14, 19, 13, 20, 11, 34, 29, 20, 10]
            data: this.showValue3
          }
        ]
      }
      this.option && this.lineChart.setOption(this.option);

    },
    fetchData() {
      getSystemInfoCri().then(res => {
        if (res.data.code === 2000) {
          this.all_data = res.data.data;
          //   console.log(this.all_data);
          this.cpu_usage = this.all_data.cpuUtilization;
          this.memory_usage = this.all_data.memoryUtilization;
          this.disk_usage = this.all_data.diskUtilization;
          this.current_time = this.all_data.currentTime;
          this.updateCharts();
        } else {
          alert(res.data.message);
        }
      })
    },
    updateCharts() {
      // 更新图表数据
      if (this.showTime.length >= 7) {
        this.showTime.shift();
        this.showValue1.shift();
        this.showValue2.shift();
        this.showValue3.shift();
      }

      this.showTime.push(this.current_time);
      this.showValue1.push(this.cpu_usage);
      this.showValue2.push(this.memory_usage);
      this.showValue3.push(this.disk_usage);

      // 更新图表选项
      // this.myChart1.setOption(this.option1);
      // this.myChart2.setOption(this.option2);
      // this.myChart3.setOption(this.option3);
      this.option && this.lineChart.setOption(this.option);
    },

    IntervalFetchData() {
      this.intervalId = setInterval(() => {
        this.fetchData();
        // this.completionRate = Number(this.completionRate);
        // console.log(this.completionRate*1000+typeof(this.completionRate))

        this.match = this.intervalTime.match(/\d/);
        // console.log(this.match*1000+typeof(this.match))


        // 如果需要停止定时器，可以在某个条件满足时返回
        // 如果不需要停止，可以移除这个条件
        // if () {
        //   clearTimeout(intervalId);
        //   timeoutId = null; // 清除定时器引用，以便垃圾回收
        // } else {
        //   repeatFetchData(); // 重新设置定时器
        // }
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