<template>
    <el-card class="my-card" shadow="hover"  v-on:click="onCon">
      <template v-slot:header>
        <span>远程管理</span>
      </template>
      <div ref="myChart" class="my-chart"></div>
    </el-card>
  </template>
  
  <script>
  import * as echarts from 'echarts';
  
  export default {
    mounted() {
      this.initChart();
    },
    methods: {
      onCon: function(){
        this.$router.push('/');
      },
      initChart() {
        let chartContainer = this.$refs.myChart;
        let myChart = echarts.init(chartContainer);
  
        myChart.setOption({
          title: {
            text: '153',
            left: 'center',
            top: '43%',
           
          },
          tooltip: {
            trigger: 'item',
            textStyle: {
              fontSize: 12
            }
          },
          legend: {
            icon: 'circle',
            bottom: '10%',
            left: 'right',
            itemWidth: 10,
            itemHeight: 10,
            textStyle: {
              fontSize: 12
            }
          },
          series: [
            {
              name: '远程管理',
              type: 'pie',
              radius: ['50%', '85%'],
              label: {
                show: true,
                position: 'top',
                formatter: '{d}%\n\n{c}',
                rich: {
                  name: {
                    fontSize: 12,
                    color: '#fff',
                    backgroundColor: '#333',
                    padding: [2, 4],
                    borderRadius: 5
                  }
                }
              },
              labelLine: {
                show:true,
                length:6,
                length2: 30,
              },
              itemStyle: {
                normal: {
                  color: function (params) {
                    var colorList = ['#4FC3F7', '#00C853'];
                    return colorList[params.dataIndex];
                  }
                }
              },
              data: [
                { name: '手机', value: 18 },
                { name: '电脑', value: 135 },
              ]
            }
          ]
        });
      }
    }
  };
  </script>
  
  <style scoped>
  .my-card {
    width: 100%; /* 根据需要调整宽度 */
    height: 100%;
    border-radius: 20px;
    padding: 0px;
  }
  
  .my-chart {
    top:0;
    height: 200px; /* 调整高度以适应您的需求 */
    border-radius: 20px; /* 添加边框半径 */
  }
  </style>
  