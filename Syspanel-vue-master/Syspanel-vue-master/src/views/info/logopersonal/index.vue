<template>
  <div class="page-wrapper">
    <div class="page-content border-bottom">
      <!-- 顶部搜索框 -->
      <div>
        <el-form :inline="true" :model="searchInfo" class="demo-form-inline">
          <el-form-item label="日期">
            <el-date-picker
              v-model="searchInfo.time"
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd"
              class="form-item-width"
              clearable
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item style="margin-left: 30px" label="类型">
            <el-select v-model="searchInfo.type" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
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
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        :header-cell-style="{ background: '#eef1f6', color: '#606266' }"
      >
        <el-table-column
          v-for="tab in tableTitle"
          :key="tab.code"
          :label="tab.name"
          show-overflow-tooltip
          :width="tab.width"
        >
       
          <template slot-scope="scope">
            <span>{{ scope.row[tab.code] }} </span>
          </template>
        </el-table-column>
        <el-table-column 
        label="操作" 
        width="200">
          <template slot-scope="scope">
            <span  @click="updateRow(scope.row)" class="check-btn">详情</span>
            <span @click="deleteRow(scope.row)" class="delete-btn">删除</span>
          </template>
        </el-table-column>
      </el-table>
     
      
      <!-- 分页组件 -->
      <div>
        <el-button type="primary" title="" icon="el-icon-document" @click="downExcel"
          style="margin-top: 10px;">导出为Execl</el-button>
        <Pagination
          :total="dataTotal"
          :page="searchInfo.page"
          :limit="searchInfo.limit"
          @pagination="onChangePages"
        />
      </div>
    </div>
   <!-- 文章弹框 -->
  <el-dialog :visible.sync="dialogVisible" top="5vh" width="672px">
    <div
    style=" background-color: #f7f7f7; 
  padding: 20px; 
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); ">
  <h1
  style="text-align: center;
  color: lightskyblue;">文章内容</h1>
<p
style="font-size: 16px; 
  line-height: 1.6;
  color: #333;
  margin: 10px 0;
  text-align: center;">{{ selectedRow.title }}</p> <!-- 显示文章内容 -->
    </div>
    
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">关闭</el-button>
    </span>
  </el-dialog>
  </div>
 
</template>

<script>
import Pagination from '@/components/Pagination'
import { getTableListFuns } from '../../../api/Res/get'
import ExportJsonExcel from "js-export-excel";
import {deleteLog} from '../../../api/Res/get'
export default {
  components: {
    Pagination,
  },
  data() {
    return {
      // 弹窗状态
      dialogVisible:false,
      selectedRow: {}, // 用于存储当前行数据的变量
      // 查询数据
      searchInfo: {
        time: '',
        type:'',
        page: 1,
        size: 10,
      },
      // 表格数据
      tableData: [], 
      // 表格标题数组
      tableTitle: [
        { name: '时间', code: 'time', width: '' },
        { name: '类型', code: 'type', width: '' },
        { name: '文章内容', code: 'title', width: '' },
      ],
      // 数据总数
      dataTotal: 100,
      // 选择类型
      options: [{
          value: '值班',
          label: '值班'
        }, {
          value: '巡检',
          label: '巡检'
        }, {
          value: '故障',
          label: '故障'
        }],
        value: '',
        option:{}
    }
  },
  mounted() {
    this.onGetDataList()
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
      this.searchInfo.type = '';
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
      getTableListFuns(this.searchInfo).then((result) => {
        this.tableData = result.data
        this.dataTotal = result.totalCount
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
            时间: dataList[i].time,
            类型: dataList[i].type,
            文章内容: dataList[i].title,
          };

          dataTable.push(obj); //   设置excel每列获取的数据源
        }
      }
      this.option.fileName = "用户个人日志"; //excel文件名
      //excel文件数据
      this.option.datas = [
        {
          //   excel文件的数据源
          sheetData: dataTable,
          //   excel文件sheet的表名
          sheetName: "sheet",
          //   excel文件表头名
          sheetHeader: ["时间", "类型", "文章内容"],
          //   excel文件列名
          sheetFilter: ["时间", "类型", "文章内容"],
          columnWidths: ['10', '10', '20']//excel列宽度设置
        },
      ];
      this.Ture();
    },
    //查看文章详情
    updateRow(row) {
      this.selectedRow = row; // 将当前行数据保存到selectedRow
      this.dialogVisible = true; // 打开弹窗
    },
   // 删除数据
   deleteRow(row) {
      console.log(row)
      this.$confirm('确定要删除该日志吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteLog(row).then((response) => {
          if (response.status === 200 || response.data.success) { // 假设成功状态码或响应体中包含成功标志  
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.onGetDataList(); // 删除成功后刷新数据列表  
          } else {
            // 处理删除失败的情况，比如显示错误消息  
            this.$message({
              type: 'info',
              message: '删除失败'
            });
            
          }
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '删除已取消'
        });
      });


    },
  },
}
</script>

<style lang="less" scoped>
.page-wrapper {
  margin-bottom: 80px;
}

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
.check-btn{
  cursor: pointer;
  color: rgba(67, 120, 255, 0.977);

}
.check-btn:active {
  color: red;
}
.delete-btn{
  cursor: pointer;
  margin-left: 20px;
  color: red;
}
.delete-btn:active {
  color: rgba(67, 120, 255, 0.977);
}
</style>