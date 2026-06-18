// import http from "@http"
// import env from "@api"

/**
 * 查询日志数据
 * @param {Object} data 
 * @returns 
 */
export function getTableListFun(data) {
    console.log(data);
    return Promise.resolve([
        {
            number: 'admin',
            time: '2016-05-02',
            type:'值班',
            title:'今天没有喝到瑞，sad',
        },
        {
            number: '永一',
            time: '2016-05-02',
            type:'值班',
            title:'好看吗？好看请我吃一顿疯狂星期四',
        },
        {
            number: 'rue',
            time: '2016-05-02',
            type:'故障',
            title:'这个破组件为什么塞不进去',
        },
        {
            number: 'admin',
            time: '2016-05-02',
            type:'值班',
            title:'今天没有好吃的，sad45555555555555555555555555555555555555555555555555555555555555555555555555555555555',
        },
    ])
}