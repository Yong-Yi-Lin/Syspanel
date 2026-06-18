import Vue from "vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

import App from "./App.vue";
import router from "./router";
import "echarts-liquidfill";
import * as echarts from "echarts";

import mavonEditor from 'mavon-editor';

// import Components from 'components';
// /关于home页面的组件
import Ball from './components/v-charts/ball.vue';
import First from './components/v-charts/first.vue';
import second from "./components/v-charts/second.vue";
import third from "./components/v-charts/third.vue";
import four from "./components/v-charts/four.vue";
import five from "./components/v-charts/five.vue";
import Index from './components/v-charts/index.vue';
import Num from './components/v-charts/manger-num.vue';
import Con from './components/v-charts/manger-con.vue';
import Notice from './components/Notice/index.vue';
import Warns from './components/Warnning/index.vue';
import Danger from './components/danger/index.vue';


import Astrict from '@/utils/astrict'
Vue.use(Astrict)
Vue.component('Ball', Ball);
Vue.component('First', First);
Vue.component('Second',second);
Vue.component('Third',third);
Vue.component('Four',four);
Vue.component('Five',five);

Vue.component('Index', Index);
Vue.component('Num', Num);
Vue.component('Con', Con);

Vue.component('Notice', Notice);
Vue.component('Warns', Warns);
Vue.component('Danger',Danger);


// Vue.prototype.$axios=axios;
Vue.prototype.$echarts = echarts
 
Vue.use(ElementUI)
Vue.use(mavonEditor)
Vue.config.productionTip = false;

/**
 * 配置全局loading提示框
 * 显示loading this.showLoading()
 * 关闭loading this.hideLoading()
 */
Vue.prototype.loading = null
Vue.prototype.showLoading = function (msg = 'Loading') {
  Vue.prototype.loading = this.$loading({
    lock: true,
    text: msg,
    spinner: 'el-icon-loading',
    background: 'rgba(0, 0, 0, 0.7)'
  });
}
Vue.prototype.hideLoading = function () {
  Vue.prototype.loading.close();
}

Vue.prototype.msgsuccess = function (msg) {
  this.$message({
    showClose: true,
    message: msg,
    type: "success"
  });
}

Vue.prototype.msgerror = function (msg) {
  this.$message({
    showClose: true,
    message: msg,
    type: "error"
  });
}

Vue.prototype.msginfo = function (msg) {
  this.$message.info(msg);
}

//全局修改弹窗黑幕点击关闭弹窗默认组件的配置
ElementUI.Dialog.props.appendToBody.default = true




//设置ElementUI组件的默认大小
Vue.use(ElementUI, {
  size: 'medium'
})


new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");
// App.use(Components);