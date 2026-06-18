<template>
  <div class="footer">
      <div class="left">
          <el-link href="https://gitee.com/xxxli9/Syspanel-vue" target="_blank">Copyright © 2024 syspanel</el-link>
          <span class="divider">|</span>
      </div>
      <div class="right">
          <el-link href="https://gitee.com/xxxli9/Syspanel-vue" class="git-link" target="_blank" type="primary">git地址</el-link>
          <span class="divider">|</span>
          <el-link href="https://www.yuque.com/syspanel/fi2wrg/po1f5hyetwf5xplo?singleDoc# " class="manual-link" type="primary" target="_blank">使用手册</el-link>
      </div>
  </div>
</template>

<style scoped lang="scss">
.footer {
  display: flex;
  align-items: center;
  justify-content: flex-end; // 使用space-between使内容左右对齐
  height: 30px;
  background: #ffffff;
  border-top: 1px solid #e4e7ed;
  box-sizing: border-box;
  padding: 10px 20px;
}

a {
  font-size: 12px;
  color: #858585;
  text-decoration: none;
  letter-spacing: 0.5px;
}

.divider {
  margin: 0 5px;
}
</style>