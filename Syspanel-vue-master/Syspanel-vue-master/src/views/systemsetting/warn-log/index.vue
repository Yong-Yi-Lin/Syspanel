<template>
  <div class="page-wrapper">
    <div class="page-content border-bottom">
      <!-- 顶部搜索框 -->
      <div>
        <el-form :inline="true" :model="searchInfo" class="demo-form-inline">
          <el-form-item label="日期">
            <el-date-picker v-model="searchInfo.time" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
              class="form-item-width" clearable>
            </el-date-picker>
          </el-form-item>
          <!-- <el-form-item style="margin-left: 30px" label="账号">
              <el-input
                v-model="searchInfo.number"
                placeholder="账号"
                class="form-item-width"
                clearable
              ></el-input> -->
          <!-- </el-form-item> -->
          <el-form-item style="margin-left: 30px" label="类型">
            <el-select v-model="searchInfo.value" placeholder="请选择">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item class="right-btns">
            <el-button type="primary" @click="onSearch">查询</el-button>
            <el-button @click="onReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="page-content margin-top">
      <!-- 表格组件 -->
      <el-table :data="tableData" stripe style="width: 100%"
        :header-cell-style="{ background: '#eef1f6', color: '#606266' }">
        <el-table-column v-for="tab in tableTitle" :key="tab.code" :label="tab.name" show-overflow-tooltip
          :width="tab.width">
          <template slot-scope="scope">
            <span>{{ scope.row[tab.code] }} </span>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页组件 -->
      <div>
        <el-button type="primary" title="" icon="el-icon-document" @click="downExcel"
          style="margin-top: 10px;">导出为Execl</el-button>
        <Pagination :total="dataTotal" :page="searchInfo.page" :limit="searchInfo.limit" @pagination="onChangePages" />
      </div>
    </div>
  </div>
</template>
  
<script>
import Pagination from '@/components/Pagination'
// import { getTableList } from '../../../api/warnlog'
import { getWarnInfo } from '../../../api/Res/get'
import ExportJsonExcel from "js-export-excel";


export default {
  components: {
    Pagination,
  },
  data() {
    return {
      // 查询数据
      searchInfo: {
        time: '',
        value: '',
        page: 1,
        size: 10,
      },
      // 表格数据
      tableData: [],
      // 表格标题数组
      tableTitle: [
        { name: '警告标题', code: 'title', width: '' },
        { name: '时间', code: 'time', width: '' },
        { name: '类型', code: 'type', width: '' },
        { name: '内容', code: 'message', width: '' },
      ],
      // 数据总数
      dataTotal: 100,
      // 选择类型
      options: [{
        value: '警告',
        label: '警告'
      },
      {
        value: '危险',
        label: '危险'
      }],
      value: '',
      dataTable: [],
      option: {},
    }
  },
  mounted() {
    this.onGetDataList();
  },
  methods: {
    // 查询方法
    onSearch() {
      this.$confirm('确定要查询吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.searchInfo.page = 1;
        this.onGetDataList();
        this.$message({
          type: 'success',
          message: '查询成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '查询已取消'
        });
      });
    },

    // 重置方法
    onReset() {
      this.searchInfo.time = '';
      this.searchInfo.value = '';
      this.$message({
        type: 'info',
        message: '重置成功'
      });
    },
    // 分页方法
    onChangePages(pageInfo) {
      const { page, limit } = pageInfo
      this.searchInfo.page = page
      this.searchInfo.size = limit
      this.onGetDataList()
    },
    // 核心查询数据方法
    onGetDataList() {
      // 查询数据方法
      getWarnInfo(this.searchInfo).then((result) => {
        console.log(result.data.data);
        this.tableData = result.data;
        this.dataTotal = result.totalCount;
      })
    },

    Ture() {
      //   创建ExportJsonExcel实例对象
      let toExcel = new ExportJsonExcel(this.option);
      //   调用保存方法
      toExcel.saveExcel();
    },

    downExcel() {
      const dataList = this.tableData;
      let dataTable = []; //   dataTable代表excel文件中的数据内容
      if (dataList) {
        for (let i in dataList) {
          let obj = {
            警告标题: dataList[i].title,
            时间: dataList[i].time,
            类型: dataList[i].type,
            内容: dataList[i].message,
          };

          dataTable.push(obj); //   设置excel每列获取的数据源
        }
      }
      this.option.fileName = "预警日志"; //excel文件名
      //excel文件数据
      this.option.datas = [
        {
          //   excel文件的数据源
          sheetData: dataTable,
          //   excel文件sheet的表名
          sheetName: "sheet",
          //   excel文件表头名
          sheetHeader: ["警告标题", "时间", "类型", "内容"],
          //   excel文件列名
          sheetFilter: ["警告标题", "时间", "类型", "内容"],
          columnWidths: ['10', '10', '20']//excel列宽度设置
        },
      ];
      this.Ture();
    },
  },
}
</script>
  
  <!-- <style lang="less" scoped>
  .form-item-width {
    float:left;
    width: 250px;
    padding-right:25px;
  }
  .right-btns {
    position: absolute;
    right:20px !important;
    
  }
  .el-form-item {
    margin-bottom: 4px !important;
  }
  .border-bottom {
    border-bottom: 1px solid #e2e2e4;
  }
  .margin-top {
    margin-top: -15px;
  }
  .demo-form-inline{
    float:left;
  }

  
  </style> -->
  
<style  scoped>
.page-wrapper {
  margin-bottom: 80px;
}

.form-item-width {
  float: left;
  width: 250px;
  padding-right: 25px;
}

.right-btns {
  position: absolute;
  right: 20px !important;
}

.el-form-item {
  margin-bottom: 4px !important;
}

.border-bottom {
  border-bottom: 1px solid #e2e2e4;
}

.margin-top {
  margin-top: -15px;
}

.demo-form-inline {
  float: left;
}
</style>