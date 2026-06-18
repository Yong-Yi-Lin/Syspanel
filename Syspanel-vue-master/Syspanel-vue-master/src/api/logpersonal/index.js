// import http from "@http"
// import env from "@api"

/**
 * 查询日志数据
 * @param {Object} data 
 * @returns 
 */
export function getTableListFuns(data) {
    console.log(data);
    return Promise.resolve([
        {
    
            time: '2016-05-02',
            type:'值班',
            title:'今天没有喝到瑞，sad',
        },
        {
          
            time: '2016-05-02',
            type:'值班',
            title:'好看吗？好看请我吃一顿疯狂星期四',
        },
        {
           
            time: '2016-05-02',
            type:'故障',
            title:'这个破组件为什么塞不进去',
        },
        {
          
            time: '2016-05-02',
            type:'值班',
            title:'今天没有好吃的，sad45555555555555555555555555555555555555555555555555555555555555555555555555555555555',
        },
    ])
}