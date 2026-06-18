<template>
  <div class="process-main">

    <div class="toolsearch">
      <el-row>
        <el-col :span="22">
          <div style="width: 100% ;padding-top:20px">
            <el-form>
              <el-form-item class="float-right">
                <el-row :gutter="20">
                  <el-col :span="12">
                    <div class="search-button">
                      <el-input v-model="input1" suffix-icon="el-icon-search" :placeholder="'按pid搜索'" @clear="search"
                        class="searchinput"></el-input>
                    </div>
                  </el-col>
                  <el-col :span="12">
                    <div class="search-button">
                      <el-input v-model="input2" suffix-icon="el-icon-search" :placeholder="'按名字搜索'" @clear="search"
                        class="searchinput"></el-input>
                    </div>
                  </el-col>
                </el-row>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="main">
      <el-table :data="tableData"
        style="width: 98%;padding-left:20px; padding-top:10px; padding-bottom:20px;margin:auto; border-radius:10px;"
        :default-sort="{ prop: 'date', order: 'descending' }">

        <el-table-column prop="pid" label="PID" sortable width="180">
        </el-table-column>
        <el-table-column prop="name" label="名称" width="180">
        </el-table-column>
        <el-table-column prop="username" label="用户名" width="180">
        </el-table-column>
        <el-table-column prop="cpu" label="CPU(%)" sortable width="180">
        </el-table-column>
        <el-table-column prop="memory" label="内存(GB)" sortable width="180">
        </el-table-column>
        <el-table-column prop="statue" label="状态" width="180">
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button @click.native.prevent="stopProcess(scope.$index, scope.row)" type="text" size="large">
              停止运行
            </el-button>
          </template>
        </el-table-column>
      </el-table>


    </div>
  </div>
</template>

<script>
// import { getTableprocess } from '../../../api/process';
import { getProcessedInfo } from '../../../api/Res/get'
import { killProcess } from '../../../api/Res/post'


export default {
  data() {
    return {
      tableData: [
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
          statue: 'stop'
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 1,
          memory: 11,
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        }
        ,
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '001',
          name: 'idea',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '003',
          name: 'pyhton',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '002',
          name: 'java',
          username: 'rue',
          cpu: 12,
          memory: 11,
        }
        ,
        {
          pid: '002',
          name: 'java',
          username: 'rue',
          cpu: 12,
          memory: 11,
        }
        ,
        {
          pid: '002',
          name: 'java',
          username: 'rue',
          cpu: 12,
          memory: 11,
        }
        ,
        {
          pid: '002',
          name: 'java',
          username: 'rue',
          cpu: 12,
          memory: 11,
        },
        {
          pid: '002',
          name: 'java',
          username: 'rue',
          cpu: 12,
          memory: 11,
        }
      ], // 初始化为空数组
      searchInfo: '', // 搜索信息
      placeholder: '', // 搜索提示信息
      input1: '',
      input2: '',
    };
  },
  mounted() {
    this.onGetDataList(); // 组件挂载后立即获取数据
    this.interval = setInterval(this.onGetDataList, 2000);
  },
  methods: {
    search() {
      console.log(this.$refs.searchinput.getAttribute('readonly')); // 检查 readonly 属性
      console.log(this.$refs.searchinput.getAttribute('disabled')); // 检查 disabled 属性
      console.log('搜索 function 触发')
      console.log(this.$refs.searchinput.value)
    },
    stopProcess(index, row) {
      console.log('停止运行', row);
      // alert(row.pid);
      this.tableData.splice(index, 1);
      killProcess(row.pid).then(result => {
        if (result.data.code === 2000) {
          this.$notify({
            title: '停止进程',
            message: '进程被停止',
            type: 'success',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        }else{
          this.$notify({
            title: '停止进程',
            message: '进程没有被停止，发生了错误',
            type: 'success',
            duration: 3000, // 提示框显示时间，单位毫秒
          });
        }
      })
    },
    onGetDataList() {
      getProcessedInfo().then(response => {
        // console.log(response);
        if (response.data.code === 2000) {
          // alert(response.data);
          // alert("进程信息获取成功！");
          // console.log(response.data.data);
          this.tableData = JSON.parse(response.data.data);
        } else if (response.data.message === '获取失败') {
          // alert("进程信息获取失败");
        }
      })
    }

  }
};
</script>

<style >
.main {
  width: 100%;
  margin: auto;
  padding-bottom: 80px;

}

.toolsearch {
  width: 100%;

}

.el-table {
  border-collapse: collapse;
  border-spacing: 0;
  border: 1px solid #e0e0e0;
}

.search-button {
  float: right;
  padding-top: 5px;

}

.searchinput .el-input__inner {
  border-radius: 30px;
  /* 使输入框的边角变圆 */
  border: 1px solid #dcdfe6;
  padding: 0 15px;
  /* 增加内边距 */
  height: 40px;
  line-height: 40px;
  transition: border-color 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.float-right {
  float: right;
}
</style>