<template>
  <div>
    <div class="page-content border-bottom">
      <!-- 顶部搜索框 -->
      <div>
        <el-form :inline="true" :model="searchInfo" class="demo-form-inline">
          <el-form-item label="日期">
            <el-date-picker v-model="searchInfo.time" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
              class="form-item-width" clearable>
            </el-date-picker>
          </el-form-item>
          <el-form-item style="margin-left: 30px" label="账号">
            <el-input v-model="searchInfo.number" placeholder="账号" class="form-item-width" clearable></el-input>
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
// import { getMonitorTabDataFun } from '../../../api/monitor'
import { getMonitorTabDataFun } from '../../../api/Res/get'
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
        number: '',
        page: 1,
        size: 10,
      },
      // 表格数据
      tableData: [],
      // 表格标题数组
      tableTitle: [
        { name: '账号', code: 'number', width: '' },
        { name: '登录时间', code: 'star', width: '' },
        { name: '退出时间', code: 'end', width: '' },
      ],
      // 数据总数
      dataTotal: 100,
      option: {},
    }
  },
  mounted() {
    // 查询
    this.onGetDataList()
  },
  methods: {
    // 查询
    onSearch() {
      this.searchInfo.page = 1
      this.onGetDataList()
    },
    // 重置
    onReset() {
      this.searchInfo.time = ''
      this.searchInfo.number = ''
      this.onSearch()
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
      getMonitorTabDataFun(this.searchInfo).then((result) => {
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
            账号: dataList[i].number,
            登录时间: dataList[i].star,
            退出时间: dataList[i].end,
          };
          dataTable.push(obj); //   设置excel每列获取的数据源
        }
      }
      this.option.fileName = "账号监控"; //excel文件名
      //excel文件数据
      this.option.datas = [
        {
          //   excel文件的数据源
          sheetData: dataTable,
          //   excel文件sheet的表名
          sheetName: "sheet",
          //   excel文件表头名
          sheetHeader: ["账号", "登录时间", "退出时间"],
          //   excel文件列名
          sheetFilter: ["账号", "登录时间", "退出时间"],
          columnWidths: ['10', '10', '20']//excel列宽度设置
        },
      ];
      this.Ture();
    },
  },
}
</script>

<style  scoped>
.form-item-width {
  width: 250px;
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
</style>