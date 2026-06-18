// import http from "@http"
// import env from "@api"

export function getMonitorTabDataFun(data) {
    console.log(data);
    return Promise.resolve([
        {
            number: "admin",
            star: "2024-06-11 12:00",
            end: "2024-06-12 16:00"
        },
        {
            number: "rue",
            star: "2024-06-11 1:00",
            end: "2024-06-11 6:00"
        },
        {
            number: "admin",
            star: "2024-06-12 12:00",
            end: "2024-06-14 16:00"
        },
    ])
}