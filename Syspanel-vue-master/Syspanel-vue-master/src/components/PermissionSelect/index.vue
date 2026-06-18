<template>
  <div>
    <el-cascader
     
      :options="options"
      v-model="selectedOptions"
      @change="handleChange"
      clearable
    >
    </el-cascader>
  </div>
</template>

<script>
import { regionData, CodeToText } from '../../api/options'

export default {
  data() {
    return {
      options: [],  // 用于存储级联选择器的选项数据
      selectedOptions: [],  // 用于存储用户选择的选项
    }
  },
  async mounted() {
    try {
      // 从 API 获取数据并设置 options
      this.options = await regionData();
    } catch (error) {
      console.error('Failed to fetch region data:', error);
    }
  },
  methods: {
    handleChange() {
      // 将选中的值转换为对应的文本
      let loc = this.selectedOptions.map(option => CodeToText[option]);
      this.$emit('handleChange', loc);
    },
  },
}
</script>