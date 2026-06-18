<template>
      <div style="width: 100%;height:400px;" ref='charts' id="workSheet"></div>

</template>

<script>
    import * as echarts from 'echarts';
    var arr = [];//用来存放最近七天的时间
    function getBeforeDate(n) {
        var n = n;
        var d = new Date();
        var year = d.getFullYear();
        var mon = d.getMonth() + 1;
        var day = d.getDate();
        var s;
        if (day <= n) {
            if (mon > 1) {
                mon = mon - 1;
            } else {
                year = year - 1;
                mon = 12;
            }
        }
        d.setDate(d.getDate() + n); //很重要，+n取得是前一天的时间
        year = d.getFullYear();
        mon = d.getMonth() + 1;
        day = d.getDate();
        s = (year < 10 ? ('0' + year) : year) + "-" + (mon < 10 ? ('0' + mon) : mon) + "-" + (day < 10 ? ('0' + day) : day);
        return s;
    }

    for (var i = 0; i > -7; i--) {
        arr.push(getBeforeDate(i));
    }
    
    export default {
        data(){
            return{
                option: {
                    title: {
                        text: '案件受理',
                        textStyle: {
                            color: '#495060',
                            // fontWeight: 'bold',
                            fontSize: 14
                        }
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['投诉案件', '异议案件', '咨询案件']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: true,
                        data: arr.reverse(),
                        nameGap: 7,//坐标轴名称与轴线之间的距离。
                        axisTick: {//是否显示坐标轴刻度。
                            inside: true//坐标轴刻度是否朝内，默认朝外。
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '投诉案件',
                            type: 'line',
                            // stack: 'Total',
                            smooth: true,
                            data: [12, 13, 10, 34, 50, 30, 21],
                            color: '#ee6666',
                            lineStyle: {
                                color: '#ee6666'
                            }
                        },
                        {
                            name: '异议案件',
                            type: 'line',
                            // stack: 'Total',
                            smooth: true,
                            data: [22, 18, 19, 34, 29, 33, 31],
                            color: '#37A2DA',
                            lineStyle: {
                                color: '#37A2DA'
                            }
                        },
                        {
                            name: '咨询案件',
                            type: 'line',
                            // stack: 'Total',
                            smooth: true,
                            data: [50, 32, 20, 54, 19, 33, 41],
                            color: '#91cc75',
                            lineStyle: {
                                color: '#91cc75'
                            }
                        },
                    ]
                 },
                 myChart: null,
            }
        },
        mounted(){
            this.mycharts()
        },
        methods:{
            mycharts() {
                var workDom = document.getElementById('workSheet')
                if (this.myChart != null) {
                    this.myChart.dispose();
                    this.myChart = echarts.init(this.$refs.charts, "macarons");
                } else {
                    this.myChart = echarts.init(this.$refs.charts, "macarons");
                }
                this.myChart.setOption(this.option)
                //图表自适应
                window.addEventListener("resize", () => {
                    this.myChart.resize() // myChart 是实例对象
                })
            },
        }
    }
</script>
