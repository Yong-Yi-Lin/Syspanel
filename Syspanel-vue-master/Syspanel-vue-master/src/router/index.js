import Vue from "vue";
import VueRouter from "vue-router";
import Layouts from "../layouts";
import Parent from "../layouts/components/AppContent"
import Personal from "../views/personal/index.vue"

// import Main from '@/components/v-charts/mian.vue';



Vue.use(VueRouter);

/**
//  *  path: "/login",   ------页面地址
 meta: {
      title: "登录",    ------页面标题
      icon: "el-icon-user-solid",  ------菜单图标
      oneself: true,  ------是否在单独页面打开
      hide: true,  ------是否隐藏改菜单
    },
 component: () => import("../views/login/index.vue"),  ------组件地址
 */

 

const routes = [
   
    {
        path: "/",
        redirect: "/",
        component: Layouts,
        children: [
            {
                path: "/",
                name: "Login",
                meta: {
                    title: "登录",
                    oneself: true,
                    hide: true,
                },
                component: () => import("../views/login"),
            },

            {
                path:"/find",
                name:"Find",
                meta: {
                    title: "找回密码",
                    oneself: true,
                    hide: true,
                },
                component: () => import("../views/find"),
            },{
                path:"/register",
                name:"Register",
                meta: {
                    title: "注册",
                    oneself: true,
                    hide: true,
                },
                component: () => import("../views/register"),
            },
         
       
       
           
            {
                path: "/home",
                meta: {title: "首页", icon: "el-icon-house"},
                component: () => import("../views/home"),
            },
            {
                path: "/datadetail",
                meta: {title: "主机",
                     icon: "el-icon-c-scale-to-original"},
                     component:Parent,
                     children:[
                         {
                             path: "monitor",
                             meta: {title: "监控", icon: "el-icon-monitor"},
                             component: () => import("../views/datadetail/monitor/index.vue"),
                         },
                         {
                            path:'process',
                            name:'process',
                            meta:{title:"进程管理",icon:"el-icon-coin"},
                            component:() =>import('../views/datadetail/process/index.vue'),         
                         },
                         {
                            path: "remote",
                            meta: { title: "远程管理", icon: "el-icon-connection" },
                            component: () => import('../views/datadetail/remote/index.vue')
                          },
                         {
                            path: "set",
                            meta: { title: "预警监控设置", icon: "el-icon-turn-off" },
                            component: () => import("../views/datadetail/setting/index.vue"), // 注意路径拼写
                          },
     
                       
                     ]
            },
            {
                path: "/worklog",
                meta: {title: "内容登记", icon: "el-icon-edit-outline"},
                component:Parent,
                children:[
                    {
                        path: "log",
                        meta: {title: "添加日志", icon: "el-icon-document-copy"},
                        component: () => import("../views/worklog/log/index.vue"),
                    },
                    {
                        path: "userlog",
                        meta: {title: "添加体验反馈", icon: "el-icon-document-copy"},
                        component: () => import("../views/worklog/userlog/index.vue"),
                    },

                  
                ]
            },
            {
                path: "/info",
                meta: {title: "信息管理", icon: "el-icon-receiving"},
                component: Parent,
                children:[
                    {
                        path: "warnlog",
                        meta: {title: "预警监控", icon: "el-icon-stopwatch"},
                        component: () => import("../views/systemsetting/warn-log/index.vue"),
                    },{
                        path: "personallog",
                        meta: {title: "查看日志", icon: "el-icon-postcard"},
                        component: () => import("../views/info/logopersonal/index.vue"),
                    }
                ]
            },
            {
                path: "/systemsetting",
                meta: {title: "平台管理", icon: "el-icon-set-up"},
                component: Parent,
                children: [
                    {
                        path: "log",
                        meta: {title: "日志管理", icon: "el-icon-document",requiresAdmin: true},
                        // meta: {  },
                        component: () => import("../views/systemsetting/logadmin"),
                    },
                    {
                        path: "accout",
                        meta: {title: "账号管理", icon: "el-icon-discover",requiresAdmin: true},
                        // meta: { requiresAdmin: true },
                        component: () => import("../views/systemsetting/account"),
                    },
            
                    {
                        path: "monitor",
                        meta: {title: "账号监控", icon: "el-icon-data-line",requiresAdmin: true},
                        // meta: { requiresAdmin: true },
                        component: () => import("../views/systemsetting/monitor"),
                    },
                   
                ]
            },
            {
                path: "/platform",
                meta: {title: "平台优化", icon: "el-icon-postcard"},
                component:Parent,
                children:[
                    {
                        path: "comment",
                        meta: {title: "用户反馈", icon: "el-icon-service",requiresAdmin: true},
                        // meta: { requiresAdmin: true },
                        component: () => import("../views/platform/usercomments/index.vue"),
                    },

                  
                ]
            },
            {
                path: "/ai",
                meta: {title: "智能办公", icon: "el-icon-coordinate"},
                component: () => import("../views/Ai"),
            },
            {
                path: "/style",
                meta: {title: "面板设置", icon: "el-icon-setting"},
                component: () => import("../views/style"),
            },
            {
                path: "/logout",
                meta: {title: "退出登录", icon: "el-icon-unlock",toExit: true},
                component: () => import("../views/logout"),
            },
        ],

    },
    {
       path:"/test",
       component:Personal
    },
    
];

//获取原型对象上的push函数
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法，取消路由重复的报错
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});
// import {isUserLogin} from '../api/Res/get'
// import {handleLogout} from '../api/Res/get'
// import {Message } from 'element-ui'
// 定义路由守卫  
// router.beforeEach((to, from, next) => {  
//     // 检查用户是否已登录  
//     // console(to.meta.toExit);
//     if (to.meta && to.meta.oneself) {  
//         // 如果是登录、找回密码、注册等页面，则直接放行  
//         console.log("这是登录、找回密码或注册页面，无需检查登录状态");  
//         next();  
//     } else if(to.meta.toExit){
//         // console.log("退出登录");
//         console.log("执行退出登录");  
//         handleLogout().then(result => { // 假设 handleLogout 不需要特定参数  
//             if (result.data.code === 2000) {  
//                 console.log("成功退出");  
//                 Message({ message: '成功退出', type: 'success' });  
//                 next('/');  
//             } else {  
//                 console.error("退出失败", result);  
//                 Message({ message: '退出失败', type: 'error' });  
//                 // 可以选择重定向到错误页面或保持当前页面  
//                 // 这里选择重定向到错误页面  
//                 next(false); // 假设有一个名为 ErrorPage 的路由  
//             }  
//         }).catch(error => {  
//             console.error('退出时发生错误:', error);  
//             Message({ message: '退出时发生错误', type: 'error' });  
//             next(false); // 或其他适当的错误处理  
//         });
        
//     }else {  
//         // 检查用户是否已登录  
//         console.log("检查用户登录状态");  
//         isUserAuthenticated(to, from, next);  
//     }   
//   });  
  
//   // 辅助函数，用于检查用户是否已登录  
//   function isUserAuthenticated(to, from, next) {  
//     isUserLogin().then(result => {  
//       // 假设result.data.code为0表示未登录，非0表示已登录  
//       if (result.data.code === 2000) {  
//         // 用户已登录，继续正常导航 
//         // 用户已登录  
//       const userRole = result.data.data; // 获取用户身份  
//         console.log(userRole);
//       // 检查是否需要管理员权限  
//       if (to.meta.requiresAdmin && userRole !== 'admin') {  
//         // 如果没有管理员权限但试图访问管理员页面  
//         // alert("您没有权限访问此页面，请联系管理员！"); 
//             Message({ message: '您没有权限访问此页面，请联系管理员！',
//             type: 'error' 
//            }) 
//         next('/home'); // 重定向到首页或其他非管理员页面  
//       } else {  
//         // 用户已登录且有权限（或不需要特殊权限），继续正常导航  
//         console.log("用户已登录且有权访问");  
//         next();  
//       }    
//       } else {  
//         // 用户未登录  
//         if (isSamePage(to, from)) {  
//           // 如果试图访问的是当前页面，不做处理  
//           next();  
//         } else {  
//           // 否则重定向到登录页面（这里假设登录页面是'/login'，而不是'/user'）
//         //   alert("您还未登录，请先登录！");
//           Message({ message: '您还未登录，请先登录！',
//              type: 'error' 
//         })
//           next('/');  
//         }  
//       }  
//     }).catch(error => {  
//       // 处理Promise拒绝的情况  
//       console.error('检查登录状态时发生错误:', error);  
//       // 可以选择重定向到错误页面或保持当前页面  
//       next(false); // 在某些情况下，可能需要重定向到错误页面  
//     });  
//   }  
//   // 辅助函数，用于判断是否是同一个页面  
//   function isSamePage(to, from) { 
//     console.log(to.path === from.path); 
//     return to.path === from.path;  
//   }  
// 后置守卫
router.afterEach(() => {
    // 在路由跳转后执行
    setTimeout(() => {
        document.querySelectorAll('.el-container').forEach(el => el.style.display = 'none');
        document.querySelectorAll('.el-container').forEach(el => el.style.display = 'block');
    }, 0);
}); 
  export default router;
// export default new VueRouter({  
//     routes  
//   });