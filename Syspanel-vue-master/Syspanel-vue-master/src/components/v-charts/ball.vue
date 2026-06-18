<template>
  <el-card class="ball" shadow="hover">
    <div class="flex justify-center items-center">
      <div class="wave-chart" ref="wavechart" :style="{ width: '140px', height: '140px' }"></div>
    </div>
  </el-card>
</template>

<script>
import * as echarts from 'echarts';

export default {
  props: {
    completionRate: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      wavechart: null
    };
  },
  mounted() {
    this.initChart();
  },
  methods: {
    initChart() {
      this.wavechart = echarts.init(this.$refs.wavechart);
      this.draw(this.completionRate);
      window.addEventListener('resize', () => {
        this.wavechart.resize();
      });
    },
    draw(completionRate) {
      const option = {
        title: {
          text: `{a|${completionRate.toFixed(2)}}%\n使用率`,
          textStyle: {
            rich: {
              a: {
                fontSize: 18,
              },
            },
            color: "#E2F8FF",
            fontSize: 16,
            lineHeight: 24,
            fontWeight: 400,
          },
          left: "center",
          top: "center",
        },
        series: [
          {
            type: "liquidFill",
            center: ["50%", "50%"],
            radius: "98%",
            data: [completionRate / 100],
            direction: "right",
            outline: {
              show: true,
              borderDistance: 15,
              itemStyle: {
                borderColor: {
                  type: "linear",
                  x: 0,
                  y: 0,
                  x2: 0,
                  y2: 1,
                  colorStops: [
                    { offset: 0, color: ["rgba(32, 128, 223,1)"] },
                    { offset: 0.5, color: ["rgba(62, 146, 226,0.7)"] },
                    { offset: 1, color: ["rgba(6, 11, 30, 0.5)"] },
                  ],
                  global: false,
                },
                borderWidth: 1,
                shadowOffsetX: 0,
                shadowOffsetY: 8,
                shadowColor: "#000000",
              },
            },
            itemStyle: {
              opacity: 0.8,
              shadowBlur: 0,
              color: {
                type: "linear",
                x: 0,
                y: 1,
                x2: 0,
                y2: 0,
                colorStops: [
                  { offset: 0, color: ["rgba(153, 102, 255, 1)"] },
                  { offset: 1, color: ["rgba(102, 217, 255, 1)"] },
                ],
                global: false,
              },
            },
            backgroundStyle: {
              color: {
                type: "radial",
                x: 0.5,
                y: 0.5,
                r: 0.5,
                colorStops: [
                  { offset: 0, color: ["rgba(13, 25, 38,1)"] },
                  { offset: 0.6, color: ["rgba(34, 64, 94, 1)"] },
                  { offset: 0.93, color: ["rgba(64, 127, 191, 1)"] },
                  { offset: 1, color: ["rgba(51, 254, 255, 0.8)"] },
                ],
                global: false,
              },
            },
            label: {
              show: false,
            },
          },
        ],
      };
      this.wavechart.setOption(option);
    },
  },
  watch: {
    completionRate(newVal) {
      if (this.wavechart) {
        this.draw(newVal);
      }
    },
  },
};
</script>
<!-- 卡片样式 -->
 <style scoped>
.ball{
  width: 80%;
  padding-right: 20px;
  margin: auto;
  border-radius: 10px;
  height: 80%;
}
.wave-chart-container {
  display: flex;
  align-items: center;
  
}
</style>