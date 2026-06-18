<template>
    <div id="ball" style="width: 1500px; height: 500px">
      Hello vue!
    </div>
  </template>
  
  <script>
  import * as echarts from 'echarts';
  import $ from 'jquery'; // Import jQuery here
  
  export default {
    name: "suanfa",
    data() {
      return {
      }
    },
    methods: {
    },
    mounted() {
      var ROOT_PATH = 'https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples';
      var chartDom = document.getElementById('ball');
      var myChart = echarts.init(chartDom);
      var option;
      myChart.showLoading();
      $.get(ROOT_PATH + '/data/asset/data/webkit-dep.json', function (webkitDep) {
        myChart.hideLoading();
        option = {
          legend: {
            data: ['HTMLElement', 'WebGL', 'SVG', 'CSS', 'Other']
          },
          series: [
            {
              type: 'graph',
              layout: 'force',
              animation: false,
              label: {
                position: 'right',
                formatter: '{b}'
              },
              draggable: true,
              data: webkitDep.nodes.map(function (node, idx) {
                node.id = idx;
                return node;
              }),
              categories: webkitDep.categories,
              force: {
                edgeLength: 5,
                repulsion: 20,
                gravity: 0.2
              },
              edges: webkitDep.links
            }
          ]
        };
        myChart.setOption(option);
      });
    }
  }
  </script>
  
  <style scoped>
  #ball {
    width: 1000px;
    height: 1500px;
  }
  </style>
  
  <style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  </style>