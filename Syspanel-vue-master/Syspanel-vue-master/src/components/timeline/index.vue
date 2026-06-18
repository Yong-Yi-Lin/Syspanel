<template>
<div>
    <el-timeline style="margin: 30px;" class="timelin">  
      <el-timeline-item  
        v-for="(activity, index) in sortedActivities"  
        :key="index"  
        :icon="activity.icon"  
        :type="activity.type"  
        :color="activity.color"  
        :size="activity.size"  
        :timestamp="activity.timestamp"  
      >  
        {{ activity.content }}  
      </el-timeline-item>  
    </el-timeline>  
</div>
</template>
<script>

// import Pagination from '@/components/Pagination'
// import { getDetailTabDataFun, getFraudTypeListFun } from '@/api/datadetail'
// import DateEdit from './components/DateEdit.vue'
export default {
  
  data() {
    return {
      reverse: true,
      activities: [{
        content: '活动按期开始',
        timestamp: '2018-04-15'
      }, {
        content: '通过审核',
        timestamp: '2018-04-13'
      }, {
        content: '创建成功',
        timestamp: '2018-04-11',
        color: '#0bbd87'
      }, {
        content: '巡检记录',
        timestamp: '2018-04-11'
      }, {
        content: '发现故障',
        timestamp: '2018-04-11',
        icon: 'el-icon-more',
        color: 'red'
      }, {
        content: '修复成功',
        timestamp: '2018-04-11'
      }]
    };
  },
  computed: {
    sortedActivities() {  
      return this.reverse ? this.activities.slice().reverse() : this.activities;  
    }  
  }
};
</script>
<style scoped>
.demo-form-inline{
 float: right;
 color: #72b6f5, 
}
</style>
