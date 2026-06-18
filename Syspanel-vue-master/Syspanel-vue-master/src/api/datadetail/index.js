// import http from "@http"
// import env from "@api

// 获取诈骗数据
export function getDetailTabDataFun(data) {
    console.log(data);
    return Promise.resolve([
        {
            // 诈骗时间
            fraudTime: "2021-12-01 20:39:16",
            // 预警等级
            warningLevel: "低危",
            // 诈骗类型
            fraudType: "电话诈骗",
            // 诈骗域名
            fraudDomain: "www.baidu.com",
            // 诈骗IP
            fraudIp: "198.187.28.18",
            // 受害人IP
            victimIp: "198.187.28.18",
            // 受害人归属地
            homeLocation: "威海市",
            // 创建时间
            createTime: "2021-12-01 20:38:25",
            // 诈骗地址归属国家
            fraudNational: "中国",
            // 诈骗网站端口
            fraudProt: "8888",
            // 受害人端口
            victimProt: "8888",
            // 用户名
            userName: "张三丰",
            // 地址
            address: "威海市环翠区北海新城2期",
            // 身份证号
            idNumber: "410892222288780000",
            // 身份证归属地
            idLocation: "山东威海",
            // 电话号码
            phone: "15098989898",
            // 电话归属地
            phoneLocation: "中国山西",
            // 银行卡号
            cardNumber: "9988888888839282",
            // 通话时长
            callDuration: "2小时5分",
            // 诈骗金额
            fraudMoney: "3000",
            // 消息可信度
            reliability: "100%",
            // 推送单位
            pushUnit: "市公安局",
            // 推送次数
            pushCount: 12
        }
    ])
}

// 获取诈骗类型
export function getFraudTypeListFun() {
    return Promise.resolve([
        {
            id: 1,
            code: "dianhua",
            name: "电话诈骗"
        },
        {
            id: 2,
            code: "daikuan",
            name: "贷款"
        },
        {
            id: 3,
            code: "maicha",
            name: "卖茶"
        },
    ])
}