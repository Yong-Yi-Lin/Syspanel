<template>
  <div>
    <!-- 左侧返回和首页 -->
    <div class="left-caozuoicon">
      <div @click="goBack">
        <i class="el-icon-back"></i>
      </div>
      <div @click="goHome">
        <i class="el-icon-s-home"></i>
      </div>
      <div class="pgeas-tab">
        <el-tag effect="dark" closable @close="goBack">
          <i :class="nowRouteIcon"></i>
          <span style="margin-left: 5px">{{ nowRouteName }}</span>
        </el-tag>
      </div>
    </div>
    <!-- 右侧用户登录图标 -->
    <div class="user-logininfo">
      <!-- 顶部搜索框 -->
      <HeaderSeach />
      <div class="rightname"
      >用户名:{{ username }}</div>
      <el-dropdown @command="clickmenu">
        <span class="el-dropdown-link right-userName">
          <div class="image">
            <img src="../../assets/logo/userimg.jpeg" alt="" srcset="" />
          </div>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="outlogin">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import {getUserName} from '../../api/Res/get'
import { outLoginFun } from '../../api/Res/get'
import HeaderSeach from './HeaderSearch'
import { personalFun } from '../../api/personal';
export default {
  components: {
    HeaderSeach,
  },
  data() {
    return {
      // 获取当前页面名称
      nowRouteName: '',
      nowRouteIcon: '',
      userInfo: '',
      username:'rue111',     // 当前登录用户名
    }
  },
  mounted() {
    // 设置标题
    this.setNowRouteName(this.$route)
    // 获取用户信息
    this.getUserInfo()
  },
  methods: {
    // 获取用户信息
    getUserInfo() {
      getUserName().then((result) => {
        // 回到首页
        this.username=result.username
      })
      this.userInfo = JSON.parse(localStorage.getItem('userInfo'))
    },
    // 获取当前页面标题
    setNowRouteName(route) {
      this.nowRouteName = route.meta.title
      this.nowRouteIcon = route.meta.icon
    },
    // 关闭标签事件
    goBack() {
      // 判断路由是否能返回
      if (window.history.length <= 2) {
        this.goHome()
      } else {
        this.$router.go(-1)
      }
    },
    // 回到首页
    goHome() {
      this.$router.replace('/home')
    },
    // 点击下拉菜单回调
    clickmenu(e) {
      if (e === 'outlogin') {
        this.outLogin()
      }
    },
    // 退出登录
    outLogin() {
      outLoginFun().then(() => {
        // 回到首页
        this.$router.replace('/')
      })
    },
     // 跳转到个人页面
  personal(){
    personalFun().then(()=>{
    //  个人页面
    this.$router.replace('/test')
    })
  },
  },
 
  watch: {
    $route: function (newRoute) {
      this.setNowRouteName(newRoute)
    },
  },
}
</script>

<style lang="less" scoped>
// 左侧操作图标
.left-caozuoicon {
  display: flex;
  align-items: center;
  gap: 30px;
  padding-left: 20px;
  box-sizing: border-box;
  .pgeas-tab {
    display: flex;
    height: 40px;
    justify-content: center;
    align-items: center;
    gap: 15px;
  }
}
// 右侧用户头像
.user-logininfo {
  display: flex;
  align-items: center;
  position: fixed;
  right: 20px;
  gap: 10px;
  cursor: pointer;
  .image {
    width: 30px;
    height: 30px;
    border-radius: 15px;
    overflow: hidden;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .rightname{
  align-items: center;
  width: 50%;
  margin: auto;
  padding-right: 20px;
}
}

.right-userName {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: auto;
}
</style>