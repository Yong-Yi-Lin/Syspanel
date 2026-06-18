// import http from "@http"
// import env from "@api"

// 登录方法
export function loginFun(data) {
    return new Promise((resolve) => {
        localStorage.setItem('userInfo', JSON.stringify(data))
        resolve()
    })
}

// 退出登录
export function outLoginFun(data) {
    console.log(data);
    return new Promise((resolve) => {
        localStorage.removeItem('userInfo')
        resolve()
    })
}
