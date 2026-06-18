<template>
  <div class="container">
    <div class="main_n">
      <message v-if="showMessageComponent"/>
    </div> 
  </div>
</template>

<script>
import message from './message.vue';


export default {
  components: {
    message,

  },
  data() {
    return {
      activeIndex: '1', // 默认激活的菜单项
      showMessageComponent: true,
      showNotificationComponent: false,
    };
  },
  methods: {
    showMessage() {
      this.showMessageComponent = true;
      this.showNotificationComponent = false;
      this.activeIndex = '1';
    },
   
  }
};
</script>

<style >

.main_n {
  width: 100%;
  padding-left: 10px;
}
</style>
