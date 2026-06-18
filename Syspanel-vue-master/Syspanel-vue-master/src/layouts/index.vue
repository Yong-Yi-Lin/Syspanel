<template>
  <div>
    <template v-if="!isOneself">
      <div class="app-wrapper">
        <div class="system-title">
          <div class="image">
            <img class="imaget01" src="../assets/svg/logo.svg" alt="" srcset="" />
            <img class="imaget02" src="../assets/svg/syspanel.svg" alt="" srcset="" />
          </div>
        </div>
        
        <div class="sidebar-container">
          
    
          <ElMenu/>
          
        </div>



        <div class="main-container">
          <HeaderNav class="header-main" />
          <AppContent class="app-main" />
          
        </div>
        <div class="main-footer">
          <Footer/>
        </div>
      </div>
    </template>
    <template v-else>
      <AppContent />
    </template>

  </div>
</template>

<script>
import ElMenu from './components/ElMenu/index.vue'
import HeaderNav from './components/HeaderNav.vue'
import AppContent from './components/AppContent.vue'
import  Footer  from './components/AppFooter.vue';

export default {
  data() {
    return {
      isOneself: false,
      systemTitle: '',
      globalStore: {
        isFullScreen: false
      }
    }
  },
  components: {
    ElMenu,
    HeaderNav,
    AppContent,
   Footer,
  },
  mounted() {
    this.isOneself = this.$route.meta.oneself
    this.getLocalUserInfo()
  },
  methods: {
    getLocalUserInfo() {
      let localUserInfo = localStorage.getItem('userInfo')
      if (localUserInfo) {
        let { systemTitle } = JSON.parse(localUserInfo)
        this.systemTitle = systemTitle
      }
    },
  },
  watch: {
    $route: function (newVal) {
      this.getLocalUserInfo()
      this.isOneself = newVal.meta.oneself
    },
  },
}
</script>
<!-- <script>
export default {
  data() {
    return {
      sidebarCollapsed: false, // 控制侧边栏是否折叠
    };
  },
  methods: {
    toggleSidebar() {
      this.sidebarCollapsed = !this.sidebarCollapsed;
    }
  }
};
</script> -->

<style lang="less" scoped>
.app-wrapper {
  position: relative;
  height: 100%;
  width: 100%;
  // 系统标题
  .system-title {
    display: flex;
    position: fixed;
    justify-content: space-around;
    align-items: center;
    color: rgb(149, 183, 227);
    width: 200px;
    height: 50px;
    top: 0;
    left: 0;
    // background-color:#cadefc9c;
    padding: -30px 10px;
    box-sizing: border-box;
     /* 添加毛玻璃效果 */
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    .image {
  display: flex;
  align-items: center;
  padding-right: 150px;
}

.image img {
  width: 105px;
    height: 50px;
    overflow: hidden;
    margin-right: -35px;
  border-radius: 20px;

}
    .title {
      font-weight: 700;
    }
  }
  // 左侧菜单
  .sidebar-container {
  -webkit-transition: width 0.28s ease;
  transition: width 0.28s ease;
  width: 200px !important;
  background-color: #fcfcfc; 
  height: 100%;
  position: fixed;
  font-size: 14px; /* 设置一个合适的字体大小 */
  top: 51px; /* 调整顶部间距 */
  // bottom: 0;
  left: 0;
  z-index: 1001;
  overflow-y: auto; /* 添加滚动条 */
  -webkit-box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1); /* 调整阴影效果 */
   /* 添加蓝色的毛玻璃效果 */
   box-shadow: 0 2px 12px 0 rgba(87, 150, 239, 0.1);
  backdrop-filter: blur(4px);
  // border-right: 1px solid #2472e0; /* 添加蓝色边框以增加层次感 */
  & > div {
    width: 100% !important; /* 使内部div宽度充满侧边栏 */
    // padding: 10px; /* 添加内边距 */
  }


}

  // 右侧主题内容
  .main-container {
    -webkit-transition: margin-left 0.28s;
    transition: margin-left 0.28s;
    position: fixed;
    width: calc(100vw - 200px);
    top: 50px;
    right: 0;
    bottom: 0;
    left: 200px;
    // 头部导航
    .header-main {
      position: fixed;
      height: 50px;
      width: 100%;
      left: 200px;
      right: 0;
      top: 0;
      display: flex;
      align-items: center;
      padding-left: 15px;
      box-sizing: border-box;
      border-bottom: 1px solid rgba(0, 0, 0, 0.2);
    }
    // 内容展示区
    .app-main {
      position: relative;
      height: 95vh;
      width: 100%;
      overflow: auto;
      background-color: #efeff493;
    }
    
  }

}
</style>
<style >
/* 确保Footer的样式 */
.main-footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  /* 其他样式 */
}

</style>
