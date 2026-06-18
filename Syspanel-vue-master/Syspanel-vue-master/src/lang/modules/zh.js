import fit2cloudZhLocale from 'fit2cloud-ui-plus/src/locale/lang/zh-cn';
let xpackZhLocale = {};
const xpackModules = import.meta.globEager('../../xpack/lang/*.js');

if (xpackModules['../../xpack/lang/zh.js']) {
    xpackZhLocale = xpackModules['../../xpack/lang/zh.js'].default || {};
}

const message={
    home:{
        restart_1panel: '重启面板',
        restart_system: '重启服务器',
        operationSuccess: '操作成功，正在重启，请稍后手动刷新浏览器！',
        overview: '概览',
        entranceHelper: '设置安全入口有利于提高系统的安全性，如有需要，前往 面板设置-安全 中，启用安全入口',
        appInstalled: '已安装应用',
        systemInfo: '系统信息',
        hostname: '主机名称',
        platformVersion: '发行版本',
        kernelVersion: '内核版本',
        kernelArch: '系统类型',
        network: '流量',
        io: '磁盘 IO',
        baseInfo: '基本信息',
        totalSend: '总发送',
        totalRecv: '总接收',
        rwPerSecond: '读写次数',
        ioDelay: '读写延迟',
        uptime: '启动时间',
        runningTime: '运行时间',
        mem: '系统',
        swapMem: 'Swap 分区',

        runSmoothly: '运行流畅',
        runNormal: '运行正常',
        runSlowly: '运行缓慢',
        runJam: '运行堵塞',

        core: '物理核心',
        logicCore: '逻辑核心',
        loadAverage: '最近 {0} 分钟平均负载',
        load: '负载',
        mount: '挂载点',
        fileSystem: '文件系统',
        total: '总数',
        used: '已用',
        free: '可用',
        percent: '使用率',
        app: '推荐应用',
        goInstall: '去安装',

        networkCard: '网卡',
        disk: '磁盘',
    },
};
export default{
    ...fit2cloudZhLocale,
    ...message,
    ...xpackZhLocale,
};
