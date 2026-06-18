// import http from "@http"
// import env from "@api"

export function getDataAdminTabDataFun(data){
    console.log(data);
    return Promise.resolve([
        {
            address:"bilibili",
            dataCount:20
        },
        {
            address:"知乎",
            dataCount:60
        },
        {
            address:"百度",
            dataCount:10
        },
       
    ])
}