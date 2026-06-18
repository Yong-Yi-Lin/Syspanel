<template>
    <div >
        <!--echarts的容器-->
        <div ref="diskEcharts" id="main-third" style="width: 100%;height: 430px;background:#fff"></div>
    </div>
</template>
   
<script>
import * as echarts from 'echarts'
import { getSystemInfoCri } from '../../api/Res/get'
export default {
    // name: 'main-third',
    props: {
        intervalTime: {
            type: String,
            required: true
        },
    },
    data() {
        return {
            //   charts: '',
            all_data: {},
            disk_usage: 0,
            // data_use: 0
            current_time: 0,
            showTime: [],
            // "155", "400", "900", "800", "300", "900", "270"
            showValue1: [],//数据
            option: {},
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
            this.diskEcharts = echarts.init(this.$refs.diskEcharts)
            this.option = {
                title: {
                    left: '3%',
                    top: '5%',
                    text: "磁盘利用率",//标题文本，支持使用 \n 换行。
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    align: 'right',//文字在前图标在后
                    left: '3%',
                    top: '15%',
                    data: ['磁盘利用率']
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
                    // axisTick: {
                    //     alignWithLabel: true //保证刻度线和标签对齐
                    // },
                    data: this.showTime //x坐标的名称

                },
                yAxis: {
                    type: 'value',
                    // boundaryGap: true,
                    // splitNumber: 4, //纵坐标数
                    // interval: 250 //强制设置坐标轴分割间隔
                },

                series: [{
                    name: '磁盘利用率%',
                    type: 'line', //折线图line;柱形图bar;饼图pie
                    stack: '总量',
                    areaStyle: {
                        //显示区域颜色---渐变效果
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                offset: 0, color: ' #72b6f5' // 0% 处的颜色
                            }, {
                                offset: 1, color: '#ffffff' // 100% 处的颜色
                            }],
                            global: false // 缺省为 false
                        }
                    },
                    itemStyle: {
                        color: ' #72b6f5', //改变折线点的颜色
                        lineStyle: {
                            color: '#40ff59' //改变折线颜色
                        }
                    },
                    data: this.showValue1
                }]
            }
            this.diskEcharts.setOption(this.option);
        },
        fetchData() {
            getSystemInfoCri().then(res => {
                if (res.data.code === 2000) {
                    this.all_data = res.data.data;
                    // console.log(this.all_data);
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
            if (this.showTime.length >= 4) {
                this.showTime.shift();
                this.showValue1.shift();
            }
            this.showTime.push(this.current_time);
            this.showValue1.push(this.disk_usage);
            // alert(this.showValue1);
            this.diskEcharts.setOption(this.option);
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

</style>