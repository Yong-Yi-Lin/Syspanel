<template>
    <el-row :gutter="20">
      <el-col :span="23" v-for="notification in filteredNotifications" :key="notification.id">
        <el-alert
          :type="notification.type"
          :title="notification.title"
          :description="notification.description + ' - ' + notification.time"
          style="margin-top: 5px;">
        </el-alert>
      </el-col>
    </el-row>
  </template>
  
  <script>
  export default {
    data() {
      return {
        activeIndex: '1', // 默认激活的菜单项
        notifications: [
          { id: 1, type: 'success', title: '域名服务', description: '郑曦月申请开通arco.design域名', time: '今天 12:30:01' },
          { id: 2, type: 'success', title: '域名服务', description: '郑曦月申请开通arco.design域名', time: '今天 12:30:01' },
          { id: 3, type: 'success', title: '域名服务', description: '郑曦月申请开通arco.design域名', time: '今天 12:30:01' },
          // ... 更多通知
        ]
      };
    },
    // 设置最多可以放三条信息
    computed: {
      filteredNotifications() {
        return this.notifications.filter((notification, index) => index < 3);
      }
    },
    methods: {
      goToMessage() {
        this.$router.push('/message');
      },
      goToNotification() {
        this.$router.push('/notification');
      }
    }
  };
  </script>
  
  <style >
  .notification {
    border: 1px solid #ddd;
    background-color: rgb(235, 241, 247);
    border-radius: 4px;
    margin: auto;
    margin-bottom: 5px;
    top: 10px;
  }
  
  </style>
  
  <style scoped>
  .notification {
    display: flex;
    flex-direction: column;
    gap: 4px; /* 添加间距 */
  }
  
  .content {
    font-size: 20px; /* 设置内容字体大小 */
    color: #40a0ffe1; /* 设置内容颜色 */
    padding-left: 2px;
  }
  
  .action-time {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .action {
    font-size: 14px; /* 设置操作字体大小 */
    color: #050505; /* 设置操作颜色 */
    padding-left: 2px;
  }
  
  .time {
    font-size: 10px; /* 设置时间字体大小 */
    color: #909399; /* 设置时间颜色 */
  }
  </style>
  