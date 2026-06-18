// import storage from '@/utils/storage'
// import router from "@/router"
// import Message from '../components/Notice/message.vue'
// import {handleLogout} from '../api/Res/get'
// import timeout from '@/views/style/index.vue'

// // import { component } from 'vue/types/umd'
// // export default{
// //     components:{
// //         timeout
// //     }
// // },
// console.log(timeout);
// let lastTime = new Date().getTime()
// console.log(lastTime);
// // let intervalId = null;
// let currentTime = new Date().getTime()
// let timeOut = 300 * 60 * 1000 //设置超时时间: 30分钟
// window.onload = function () {
//     window.document.onmousedown = function () {
//     storage.setItem("lastTime", new Date().getTime())
//     console.log("用户操作了");
// }
// };
// function checkTimeout() {
// currentTime = new Date().getTime()//更新当前时间\n\n
// lastTime = storage.getItem("lastTime");
// if (currentTime - lastTime > timeOut) { //判断是否超时
// // 清除storage的数据(登陆信息和token)
//     console.log("已超时");
//     storage.clear()
// // 跳到登陆页
//     console.log(router.children)
//     console.log(router)
//     handleLogout();
// // if(router.currentRouter.name == 'Login') return // 当前已经是登陆页时不做跳转
//     router.push({ name: 'Login' })
//     // clearInterval(intervalId);
//     Message({ message: '您已超时退出', type: 'info' });  
// }}
// export default function () {/* 定时器 间隔30秒检测是否长时间未操作页面 */
//     console.log("每隔30秒检查一下")
//     window.setInterval(checkTimeout, 30000);
// }
// // import storage from '@/utils/storage'  
// // import router from "@/router"  
// // import Message from '../components/Notice/message.vue'  
// // import { handleLogout } from '../api/Res/get' 
// // window.onload = function () {
// //     window.document.onmousedown = function () {
// //     storage.setItem("lastTime", new Date().getTime())
// //     console.log("用户操作了");
// //     }
// // }; 
// // // let currentTime = new Date().getTime()
// // let timeOut = 1 * 60 * 1000;
// // let lastTime = new Date().getTime()  
// // let intervalId = null; // 用于保存定时器的引用  
  
// // function checkTimeout() {  
// //     const currentTime = new Date().getTime();  
// //     lastTime = storage.getItem("lastTime") || 0; // 如果没有设置lastTime，则默认为0  
// //     if (currentTime - lastTime > timeOut) { // 假设超时时间为1分钟，为了测试方便  
// //         console.log("已超时");  
// //         storage.clear();  
// //         handleLogout();  
// //         router.push({ name: 'Login' });  
// //         clearInterval(intervalId); // 清除定时器  
// //         Message({ message: '您已超时退出', type: 'info' });  
// //     }  
// // }  
  
// // export default function () {  
// //     console.log("每隔30秒检查一下");  
// //     intervalId = window.setInterval(checkTimeout, 30000); // 保存定时器的引用  
  
// //     // 监听路由变化，以便在需要时清除定时器  
// //     router.beforeEach((to, from, next) => {  
// //         if (to.name === 'Login' || to.name === 'OtherPageWhereYouWantToStop') {  
// //             clearInterval(intervalId); // 如果即将进入登录页面或其他不需要检测超时的页面，清除定时器  
// //         }  
// //         next();  
// //     });  
// // }  
  
// // 注意：这里的 Message 组件可能无法直接作为函数调用，  
// // 如果它是一个Vue组件，您可能需要使用Vue的实例或其他方式来显示消息  


import storage from '@/utils/storage'
import router from "@/router"
// import {Message } from 'element-ui'
import { Message } from 'element-ui'
import {handleLogout} from '../api/Res/get'
import {getOutTime} from '../api/Res/get'
import {isUserLogin} from '../api/Res/get'

// let lastTime = new Date().getTime()
// console.log(lastTime);

window.onload = function () {
    window.document.onmousedown = function () {
    storage.setItem("lastTime", new Date().getTime())
    console.log("用户操作了");
}
};
async function checkTimeout() {  
    const currentTime = new Date().getTime(); // 更新当前时间  
    const lastTime = storage.getItem("lastTime");  
    try {  
        const loginResult = await isUserLogin(); // 等待登录检查结果  
        let isLogin = false;  
        if (loginResult.data.code === 2000) {  
            isLogin = true;  
        } else {  
            isLogin = false;  
        }  
        console.log(isLogin);
        if (!isLogin) { 
            console.log("用户未登录，不用检查");
            return; // 如果未登录，则直接返回，不执行后续代码  
        }  
        const timeOutResult = await getOutTime(); // 获取超时时间  
        if (timeOutResult.data.code === 2000) {  
            const timeOut = timeOutResult.data.data*60000;
            // console.log("退出时间为：");
            // console.log(timeOut);
            console.log(currentTime - lastTime);
            console.log(timeOut- (currentTime - lastTime));
            if (currentTime - lastTime >= timeOut) { // 判断是否超时  
                console.log("已超时");   
                handleLogout();  
                router.push({ name: 'Login' }) // 跳到登陆页 
                Message({ message: '您已超时退出', type: 'info' });  
            }else if(timeOut- (currentTime - lastTime) <= 30000){
                Message({ message: '您已经长时间未操作，系统即将退出登录，如需修改超时时间，请点击面板设置进行修改', type: 'info',duration: 7000 });  

            }

        }  
    } catch (error) {  
        console.error('Error checking login status or timeout:', error);  
    }  
}
export default function () {/* 定时器 间隔30秒检测是否长时间未操作页面 */
    window.setInterval(checkTimeout, 30000);
}
