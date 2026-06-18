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
            title:'老板快还钱',
        },
        {
            number: 'rue',
            time: '2016-05-02',
            title:'一点都不好用看不懂',
        },
        {
            number: '永一',
            time: '2016-05-02',
            title:'这个bug为什么还不修',
        },
    ])
}