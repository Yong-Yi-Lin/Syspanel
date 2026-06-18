// 示例的后端 API 代码（Node.js + Express）

// const express = require('express');
// const app = express();
// const port = 3000;

// 模拟的选项数据
const optionsForMan = [
    { value: 'admin', label: '管理员' },
    { value: 'employee', label: '员工' }
  ];
  
  // 模拟的CodeToText映射数据
  const CodeToText = {
    'admin': '管理员',
    'employee': '员工'
  };
  
  // 获取选项数据的模拟 API 方法
  export const regionData = () => {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve(optionsForMan);
      }, 500);  // 模拟的网络延迟
    });
  };
  
  // 导出模拟的映射数据
  export { CodeToText };