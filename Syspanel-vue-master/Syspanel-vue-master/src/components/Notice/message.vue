<template>
  <el-scrollbar style="height: 200px;">
    <el-row :gutter="20">
      <el-col :span="23" v-for="message in messages" :key="message.id">
        <el-alert
          :type="message.type"
          :title="message.title"
          :description="message.description"
          show-icon
          style="margin-bottom: 5px;"
        >
        </el-alert>
      </el-col>
    </el-row>
  </el-scrollbar>
</template>

<script>
import {getMessages} from '../../api/Res/get'
export default { 
  data() {  
    return {  
      activeIndex: '1', // 默认激活的菜单项  
      messages:[],
    };  
  },  
  created() {  
    // 在组件创建后立即调用此方法  
    this.fetchMessages();  
  }, 
  methods: {  
    fetchMessages() {  
      // 假设你的后端API端点是/api/messages  
      getMessages().then((result) => { 
        this.messages = result.data; 

      }).catch((error) => {  
        console.error('加载消息失败:', error);  
        // 可以在这里添加更多的错误处理逻辑，比如显示一个错误消息给用户  
      });
    },  
    goToMessage() {  
      this.$router.push('/message');  
    },  
  }  
};  
</script>
<style >
 
 .el-scrollbar .el-scrollbar__wrap {
  overflow-x: hidden;
}

.el-select-dropdown .el-scrollbar {
  padding-bottom: 17px;
}

</style>
