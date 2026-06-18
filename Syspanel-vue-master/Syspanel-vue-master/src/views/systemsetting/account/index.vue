<template>
  <div>
    <div class="page-content border-bottom">
      <!-- 顶部搜索框 -->
      <div>
        <el-form :inline="true" :model="searchInfo" class="demo-form-inline">
          <el-form-item label="账号">
            <el-input v-model="searchInfo.number" placeholder="账号" class="form-item-width" clearable></el-input>
          </el-form-item>
          <!-- 权限选择框 -->
          <el-form-item style="margin-left: 30px" label="权限类别">
            <el-select v-model="searchInfo.category" placeholder="请选择权限类别">
              <el-option label="普通用户" value="guest"></el-option>
              <el-option label="管理员" value="admin"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item class="right-btns">
            <el-button type="primary" @click="onAddUser">新增</el-button>
            <el-button type="primary" @click="onSearch">查询</el-button>
            <el-button @click="onReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <!-- 表格展示区域 -->
    <div class="page-content margin-top">
      <!-- 表格组件 -->
      <el-table :data="tableData" stripe style="width: 100%"
        :header-cell-style="{ background: '#eef1f6', color: '#606266' }">
        <!-- 循环展示表格 -->
        <el-table-column v-for="tab in tableTitle" :key="tab.code" :label="tab.name" show-overflow-tooltip
          :width="tab.width">
          <template slot-scope="scope">
            <span>{{ scope.row[tab.code] }} </span>
          </template>
        </el-table-column>
        <!-- 操作 -->
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <span @click="updateRow(scope.row)" class="update-btn">修改</span>
            <span @click="deleteRow(scope.row)" class="delete-btn">删除</span>
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


    <!-- 新增账号弹框 -->
    <el-dialog :title="isUpdate ? '修改' : '新增'" :visible.sync="dialogVisible" top="5vh" width="672px">
      <el-form ref="form" class="dialog-form" :model="accountEdit" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="accountEdit.number" @blur="nameHandleBlur" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="accountEdit.pwd" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="accountEdit.email" @blur="emailHandleBlur" placeholder="请输入邮箱号"></el-input>
        </el-form-item>
        <el-form-item label="权限">
          <PermissionSelect @handleChange="changeDialogArea" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmitDialog">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

import Pagination from '@/components/Pagination'
import { getAccountDtatFun } from '../../../api/Res/get'
import { updateUserByName } from '../../../api/Res/post'
import { addUser } from '../../../api/Res/post'
import { isExistByName } from '../../../api/Res/get'
import { isExistByEmail } from '../../../api/Res/get'
import { deleteUserByName } from '../../../api/Res/get'
import PermissionSelect from '@/components/PermissionSelect'
import ExportJsonExcel from "js-export-excel";

export default {
  components: {
    Pagination,
    PermissionSelect,
  },
  data() {
    return {
      // 查询数据
      searchInfo: {
        category: '',
        number: '',
        // address: '',
        page: 1,
        size: 10,
      },
      // 弹框表单信息
      accountEdit: {
        name: '',
        number: '',
        pwd: '',
        email: '',
        area: '',
      },
      // 表格数据
      tableData: [],
      // 数据总数
      dataTotal: 100,
      // 标记是否新增
      isUpdate: false,
      // 是否展示弹框
      dialogVisible: false,
      // 表格标题数组
      tableTitle: [
        { name: '账号', code: 'number', width: '' },
        { name: '创建时间', code: 'time', width: '' },
        { name: '权限', code: 'permission', width: '' },
      ],
      option: {},
    }
  },
  mounted() {
    // let myCookieValue = this.getCookie('error'); // 确保这里使用的是Cookie的实际名称'error'  
    // if (!myCookieValue) {
    //   this.$message.error("没有检测到错误消息Cookie"); // 修正错误消息  
    // } else {
    //   console.log("这是我的cookie: " + myCookieValue);
    //   alert(myCookieValue);
    //   this.$message.error(myCookieValue); // 现在显示Cookie的实际值  
    // }
    // 查询数据
    this.onSearch()
  },
  methods: {
    // 切换权限触发
    changeArea(area) {
      this.searchInfo.address = area.join('')
      this.onGetDataList()
    },
    // 切换弹框中的权限
    changeDialogArea(area) {
      if (!this.accountEdit.area) {
        this.accountEdit.area = area[0];

      }
      console.log(area)
    },
    // 查询
    onSearch() {
      // console.log(this.searchInfo)
      this.searchInfo.page = 1
      this.onGetDataList()
    },
    // 重置
    onReset() {
      this.searchInfo.number = ''
      this.searchInfo.category = ''
      this.onSearch()
    },
    // 新增方法
    onAddUser() {
      this.isUpdate = false
      this.dialogVisible = true
      this.accountEdit.number = ''
      this.accountEdit.pwd = ''
      this.accountEdit.area = ''
      this.accountEdit.email = ''
    },
    // 更新数据
    updateRow(row) {
      this.isUpdate = true
      this.dialogVisible = true
      this.accountEdit.number = ''
      this.accountEdit.pwd = ''
      this.accountEdit.area = ''
      this.accountEdit.email = ''
      this.accountEdit.name = row.number;
      console.log(row)
    },
    nameHandleBlur() {
      console.log(this.accountEdit.number);
      isExistByName(this.accountEdit.number).then((response) => {
        if (response.status === 200 || response.data.success) {
          console.log("查询的结果是" + response.data.message);
        }
        if (response.data.message === "用户名存在") {
          this.$message.error(response.data.message + "，请重新输入");
          this.accountEdit.number = ''
        }
      })
    },
    emailHandleBlur() {
      console.log(this.accountEdit.email);
      isExistByEmail(this.accountEdit.email).then((response) => {
        if (response.status === 200 || response.data.success) {
          console.log("查询的结果是" + response.data.message);
        }
        if (response.data.message === "邮箱已存在") {
          this.$message.error(response.data.message + "，请重新输入");
          this.accountEdit.email = ''
        }
      })
    },
    // getCookie(name) {
    //   let matches = document.cookie.match(new RegExp(
    //     "(?:^|; )" + name.replace(/[.*+?^${}()|[\]\\\/]/g, '\\$&') + "=([^;]*)"  // eslint-disable-line no-useless-escape  
    //   ));
    //   return matches ? decodeURIComponent(matches[1]) : undefined;
    // },
    // 删除数据
    deleteRow(row) {
      console.log(row)
      this.$confirm('确定要删除该用户吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUserByName(row.number).then((response) => {
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
      getAccountDtatFun(this.searchInfo).then((result) => {
        this.tableData = result.data;
        this.dataTotal = result.totalCount;
      })
    },
    // 弹框确认方法
    onSubmitDialog() {
      console.log(this.accountEdit);
      if (this.isUpdate) {
        if (!this.accountEdit.number & !this.accountEdit.area & !this.accountEdit.pwd & !this.accountEdit.email) {
          this.$message.error("信息为空，无法修改");
          return;
        }
        updateUserByName(this.accountEdit).then((response) => {
          if (response.status === 200 || response.data.success) { // 假设成功状态码或响应体中包含成功标志  
            this.$message.success("修改数据成功，请刷新");
          } else {
            // 处理删除失败的情况，比如显示错误消息  
            this.$message.error('修改失败: ' + response.data.message || '未知错误');
          }
        })
      } else {
        if (!this.accountEdit.number | !this.accountEdit.area | !this.accountEdit.pwd | !this.accountEdit.email) {
          this.$message.error("信息为空，无法新增，请补充完整");
          return;
        }
        addUser(this.accountEdit).then((response) => {
          if (response.status === 200 || response.data.success) { // 假设成功状态码或响应体中包含成功标志  
            this.$message.success("新增数据成功，请刷新");
          } else {
            // 处理删除失败的情况，比如显示错误消息  
            this.$message.error('新增失败: ' + response.data.message || '未知错误');
          }
        })
      }
      this.dialogVisible = false;
      // this.resetForm();
      this.onGetDataList();
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
            创建时间: dataList[i].time,
            权限: dataList[i].permission,
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
          sheetHeader: ["账号", "创建时间", "权限"],
          //   excel文件列名
          sheetFilter: ["账号", "创建时间", "权限"],
          columnWidths: ['10', '10', '20']//excel列宽度设置
        },
      ];
      this.Ture();
    },
  },
}
</script>

<style scoped >
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

.update-btn {
  cursor: pointer;
  color: #409eff;
}

.delete-btn {
  cursor: pointer;
  color: #f56c6c;
  margin-left: 20px;
}

.dialog-form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}
</style>