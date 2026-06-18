// import http from "@http"
// import env from "@api"

// 获取账号数据
export function getAccountDtatFun(data) {
    console.log(data);
    return Promise.resolve([
        {
            number: "admin",
            time: "2021-11-30",
            permission: "牛马员工"
        },
        {
            number: "永一",
            time: "2021-11-30",
            permission: "精神老板"
        },
        {
            number: "rue",
            time: "2021-11-30",
            permission: "路人甲"
        },
    ])
}
