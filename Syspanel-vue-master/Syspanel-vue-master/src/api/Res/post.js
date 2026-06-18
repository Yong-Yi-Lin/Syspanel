// 导入axios配置
import request from '../../utils/request'
// import qs from 'qs';


// 登录账号
export function LoginCheckUser(formData) {
    console.log(formData);
    return request({
        url: '/login/checkUser',
        method: 'post',
        data: formData,

        headers: {
            // 'Content-Type':'multipart/form-data',
            'processData': false,
            'Content-Type': false
        }
        // multipart/form-data
    })
}

export function checkIsExpired(Data) {
    console.log(Data);
    return request({
        url: '/verification-code/checkIsExpired',
        method: 'post',
        params: {
            email: Data
        },
        headers: {
            // 'Content-Type': 'application/json',
            // 'processData': false,
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
        //application/json
        // multipart/form-data
        // 'application/x-www-form-urlencoded; charset=UTF-8
    })
}
export function sendmail(Data) {
    console.log(Data);
    return request({
        url: '/verification-code/sendmail',
        method: 'post',
        params: {
            email: Data
        },
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        }
    })

}
export function resetPwd(Data) {
    return request({
        url: '/resetPwd/checkUser',
        method: 'post',
        data: Data,
        headers: {
            'processData': false,
            'Content-Type': false
        }
    })
}


export function register(Data) {
    console.log(Data);
    return request({
        url: '/register/addUser',
        method: 'post',
        data: Data,
        headers: {
            'processData': false,
            'Content-Type': false
        }
    })
}

export function saveMonitorSetting(status, data) {
    console.log(status, data);
    const monitorSettings = {
        status: status,
        number: data.number,
        collectionInterval: data.interval, // 提取日期部分 
        cpuWarningValue: data.cpumin,
        cpuDangerValue: data.cpumax,
        memoryWarningValue: data.memorymin,
        memoryDangerValue: data.memorymax
    }
    return request({
        url: '/MonitorSetting/saveSetting',
        method: 'post',
        data: monitorSettings,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}


export function warningHandling(data) {
    // console.log(data);
    const monitorSettings = {
        number: data.number,
        collectionInterval: data.interval, // 提取日期部分 
        cpuWarningValue: data.cpumin,
        cpuDangerValue: data.cpumax,
        memoryWarningValue: data.memorymin,
        memoryDangerValue: data.memorymax
    }
    return request({
        url: '/warn/warning_handling',
        method: 'post',
        data: monitorSettings,
        headers: {
            // 'processData': false,
            // 'Content-Type': false
            'Content-Type': 'application/json'
        }
    })
}

export function killProcess(pid) {
    console.log(pid);
    return request({
        url: '/process/killProcess?pid=' + pid,
        method: 'post',
        // data: pid,
        headers: {
            'processData': false,
            'Content-Type': false
        }
    })
}

export function isLoginSSH(data) {
    const fd = new FormData();
    fd.set("host", data.listenAddress)
    fd.set("port", data.port)
    fd.set("username", data.RootLogin)
    fd.set("password", data.PasswordAuth)

    return request({
        url: '/ssh/getLogin',
        method: 'post',
        data: fd,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function disconnectSSH(data) {
    const fd = new FormData();
    fd.set("host", data.listenAddress)
    fd.set("port", data.port)
    fd.set("username", data.RootLogin)
    fd.set("password", data.PasswordAuth)

    return request({
        url: '/ssh/disconnectSSH',
        method: 'post',
        data: fd,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function sshToCmd(data, code) {
    const fd = new FormData();
    fd.set("host", data.listenAddress)
    fd.set("port", data.port)
    fd.set("username", data.RootLogin)
    fd.set("password", data.PasswordAuth)
    fd.set("cmd", code)

    return request({
        url: '/ssh/getSSH',
        method: 'post',
        data: fd,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
export function updatename(data) {
    console.log(data)
    return request({
        url: '/user/updatename',
        method: 'post',
        data: data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
export function updatepassword(data) {
    console.log(data)
    return request({
        url: '/user/updatepassword',
        method: 'post',
        data: data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
export function updateemail(data) {
    console.log(data)
    return request({
        url: '/user/updateemail',
        method: 'post',
        data: data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function addLog(Data) {
    console.log(Data);
    return request({
        url: '/logs/addLog',
        method: 'post',
        data: Data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function addUserLog(Data) {
    console.log(Data);
    return request({
        url: '/logs/addUserLog',
        method: 'post',
        data: Data,
        // xhrFields:{
        //     withCredentials:true
        // },
        headers: {
            'Content-Type': 'application/json', // 设置为JSON  
        },
    })
}

export function updateUserByName(data) {
    console.log(data);
    return request({
        url: '/user/updateUser',
        method: 'post',
        data: data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function addUser(data) {
    console.log(data);
    return request({
        url: '/user/addUser',
        method: 'post',
        data: data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
export function updatetimeout(data){
    console.log(data);
    return request({
        url: '/user/updatetimeout',
        method: 'post',
        data:data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}


export function Aiqwen2(data){
    console.log(data);
    return request({
        url: '/api/qwen2/message',
        method: 'post',
        data:data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}