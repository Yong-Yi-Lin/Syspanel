<template>
    <div class="custom-header">
      <div class="logo-container">
        <img :src="require('@/assets/svg/logo.svg')" alt="Logo" class="fixed-svg" />
        <img :src="require('@/assets/svg/syspanel.svg')" alt="Logo" class="fixed-svgs" />
      </div>
      <div class="icon-container">
       
      
      </div>
    </div>
  </template>
      
    <script>  
    export default {  
      // ... 组件的其他部分  
    }  
    </script>  
      
    <style scoped>  
    .custom-header {  
      display: flex;  
      justify-content: space-between;  
      align-items: center;  
      height: 60px; /* 设置你想要的 header 高度 */  
      background-color: #f5f7fa5d; /* 设置背景色 */  
      position: fixed; /* 使 header 固定在顶部 */  
      top: 0;  
      left: 0;  
      right: 0;  
      z-index: 1000; /* 确保 header 在其他内容之上 */  
      padding: 0 15px; /* 添加内边距以避免内容紧贴边缘 */  
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */  
    }  
      
    .logo-container, .icon-container {  
      display: flex;  
      align-items: center;  
    }  
      
    .fixed-svg {   
      height: 65px; /* 设置 SVG 的高度 */  
         width: 40px; /* 保持 SVG 的宽高比 */  
    }  
    .fixed-svgs {   
      height: 50px; /* 设置 SVG 的高度 */  
      width: auto; /* 保持 SVG 的宽高比 */  
    }  
    </style>