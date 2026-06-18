
const path = require('path')
function resolve(dir) {
    return path.join(__dirname, dir)
}
module.exports = {
    publicPath: '/',
    devServer: {
        disableHostCheck: true, //禁用主机检查 
        proxy: {
            "/node": { // 设置以什么前缀开头的请求用来代理
                target: "http://localhost:8081", //要访问的跨域的域名
                secure: false, // 使用的是http协议则设置为false，https协议则设置为true
                changOrigin: true, //开启代理
                pathRewrite: {
                    "^/node": "",
                },
            },
        },
    },
    configureWebpack: {
        resolve: {
            alias: {
                '@': resolve('src'),
                "@http": resolve("src/utils/http.js"),
                "@api": resolve("src/utils/baseurl.js")
            }
        }
    },
   

};
