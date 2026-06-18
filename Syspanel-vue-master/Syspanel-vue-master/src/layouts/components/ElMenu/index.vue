<template>
  <div>
    <el-menu
      :default-active="$route.path"
      :unique-opened="true"
      class="el-menu-vertical-demo"
    >
      <MenuItem
        v-for="(route, index) in routersList"
        :key="index"
        :item="route"
        :fatherPath="route.path"
      ></MenuItem>
    
    </el-menu>
  </div>
</template>

<script>
import routers from "../../../router";
import MenuItem from "./MenuItem.vue";
export default {
  components: {
    MenuItem,
  },
  data() {
    return {
      routersList: [],
    };
  },
  mounted() {
    // 获取所有定义的一级菜单和多级菜单
    this.routersList = routers.options.routes[0].children;
  },
};
</script>

<style scoped>

/* 菜单项样式 */
.el-menu-item {
  font-size: 16px; /* 调整字体大小 */
  margin: 0; /* 移除不必要的上下边距 */
  
  transition: background-color 0.3s, color 0.3s; /* 添加过渡效果 */
}

/* 菜单项悬停和激活样式 */
.el-menu-item:hover,
.el-menu-item.is-active {
  background-color:#91d5ff;  /* 悬停和激活时的背景颜色 */
  color: #333; /* 悬浮和激活时的字体颜色 */
  /* 添加毛玻璃效果 */
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(4px);
}

/* 子菜单项悬停和激活样式 */
.el-menu-item-group__items .el-menu-item:hover,
.el-menu-item-group__items .el-menu-item.is-active {

  /* background-color:  #c0dafbbf;  悬停和激活时的背景颜色 */
  color: #f7f7f7; /* 悬浮和激活时的字体颜色 */
  /* 添加毛玻璃效果 */
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(4px);
}

/* 子菜单样式 */
.el-menu--inline {
  background-color:#e6f7ff; /* 使用柔和的背景颜色 */
}

/* 子菜单项样式 */
.el-menu-item-group__items .el-menu-item {
  padding-left: 40px; /* 调整子菜单项的左边距 */
}


</style>
