<!-- <template>
  <div>
    <el-select
      ref="headerSearchSelect"
      v-model="selectVal"
      :remote-method="querySearch"
      filterable
      default-first-option
      remote
      placeholder="Search"
      class="header-search-select"
      @change="onChangeSelect"
    >
      <el-option
        v-for="option in selectList"
        :key="option.item.path"
        :value="option.item.path"
        :label="option.item.title"
      />
    </el-select>
  </div>
</template>

<script>
import Fuse from 'fuse.js'
import path from 'path'
export default {
  name: 'HeaderSearch',
  data() {
    return {
      // fuse配置信息
      fuse: null,
      // 输入框绑定值
      selectVal: '',
      // 下拉框列表
      selectList: [],
      // 用于存放模糊搜索的路由数据
      allRouter: [],
    }
  },
  mounted() {
    // 获取全部的路由数据
    let allRouter = this.$router.options.routes[0].children
    this.resolvePath('/', '', allRouter)
    this.initFuse(this.allRouter)
  },
  methods: {
    // 递归初始化path
    resolvePath(basePath = '/', baseName = '', routers) {
      routers.forEach((route) => {
        let routeName = path.resolve(baseName, route.meta.title).slice(1)
        let routePath = path.resolve(basePath, route.path)
        if (!route.children) {
          // 页面不是隐藏的才会push到数据中
          !route.meta.hide &&
            this.allRouter.push({
              title: routeName,
              path: routePath,
            })
        } else {
          // 递归调用获取嵌套数据
          this.resolvePath(routePath, routeName, route.children)
        }
      })
    },
    // 初始化配置Fuse
    initFuse(list) {
      this.fuse = new Fuse(list, {
        shouldSort: true,
        threshold: 0.4,
        location: 0,
        distance: 100,
        maxPatternLength: 32,
        minMatchCharLength: 1,
        keys: [
          {
            name: 'title',
            weight: 0.7,
          },
          {
            name: 'path',
            weight: 0.3,
          },
        ],
      })
    },
    // 模糊搜索路径
    querySearch(query) {
      if (query !== '') {
        console.log(this.fuse.search(query))
        this.selectList = this.fuse.search(query)
      } else {
        this.selectList = []
      }
    },
    // 选中某一项时触发
    onChangeSelect(path) {
      this.$router.push(path)
    },
    // 判断是否是外部连接
    ishttp(url) {
      return url.indexOf('http://') !== -1 || url.indexOf('https://') !== -1
    },
  },
}
</script>

 -->
 <template>
  <div class="search">
    <el-select
      ref="headerSearchSelect"
      v-model="selectVal"
      :remote-method="querySearch"
      filterable
      default-first-option
      remote
      placeholder="查询所需要的功能"
      class="header-search-select"
      @change="onChangeSelect"
    >
      <el-option
        v-for="option in selectList"
        :key="option.item.path"
        :value="option.item.path"
        :label="option.item.title"
      />
      <i slot="prefix" class="el-input__icon el-icon-search"></i>
    </el-select>
  </div>
</template>

<script>
import Fuse from 'fuse.js'
import path from 'path'

export default {
  name: 'HeaderSearch',
  data() {
    return {
      // fuse配置信息
      fuse: null,
      // 输入框绑定值
      selectVal: '',
      // 下拉框列表
      selectList: [],
      // 用于存放模糊搜索的路由数据
      allRouter: [],
    }
  },
  mounted() {
    // 确保路由已经初始化
    if (this.$router && this.$router.options && this.$router.options.routes) {
      let allRouter = this.$router.options.routes[0].children;
      if (allRouter) {
        this.resolvePath('/', '', allRouter);
        this.initFuse(this.allRouter);
      } else {
        console.error('No routes found in this.$router.options.routes[0].children');
      }
    } else {
      console.error('Router or routes not properly initialized');
    }
  },
  methods: {
    // 递归初始化path
    resolvePath(basePath = '/', baseName = '', routers) {
      routers.forEach((route) => {
        let routeName = path.join(baseName, route.meta.title).slice(1)
        let routePath = path.join(basePath, route.path)
        if (!route.children) {
          // 页面不是隐藏的才会push到数据中
          !route.meta.hide &&
            this.allRouter.push({
              title: routeName,
              path: routePath,
            })
        } else {
          // 递归调用获取嵌套数据
          this.resolvePath(routePath, routeName, route.children)
        }
      })
    },
    // 初始化配置Fuse
    initFuse(list) {
      this.fuse = new Fuse(list, {
        shouldSort: true,
        threshold: 0.4,
        location: 0,
        distance: 100,
        maxPatternLength: 32,
        minMatchCharLength: 1,
        keys: [
          {
            name: 'title',
            weight: 0.7,
          },
          {
            name: 'path',
            weight: 0.3,
          },
        ],
      })
    },
    // 模糊搜索路径
    querySearch(query) {
      if (query !== '') {
        // 使用计算属性来缓存搜索结果
        this.selectList = this.fuse.search(query)
      } else {
        this.selectList = []
      }
    },
    // 选中某一项时触发
    onChangeSelect(path) {
      this.$router.push(path)
    },
  },
}
</script>
<style >

.search {
  display: flex;
  justify-content: flex-end;
  padding-right: 80px;
  margin: auto;
  width: 100%;
}

.header-search-select {
  width: 700px; /* 增加搜索框的宽度 */
}

.header-search-select .el-input__inner {
  border-radius: 30px; /* 使输入框的边角变圆 */
  border: 1px solid #dcdfe6;
  padding: 0 15px; /* 增加内边距 */
  height: 40px;
  line-height: 40px;
  transition: border-color 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.header-search-select .el-input__inner:hover {
  border-color: #c0c4cc;
}

.header-search-select .el-input__inner:focus {
  border-color: #409eff;
}

.header-search-select .el-input__icon {
  line-height: 40px; /* 确保图标与输入框对齐 */
  cursor: pointer; /* 更改鼠标指针样式，以指示可点击 */
}

/* 隐藏默认的箭头图标 */
.header-search-select .el-input__suffix {
  display: none;
}

/* 添加搜索图标 */
.header-search-select .el-input__prefix {
  right: 10px; /* 调整图标位置 */
  left: auto;
}

/* 自定义搜索图标 */
.header-search-select .el-input__prefix .el-input__icon {
  font-size: 16px; /* 调整图标大小 */
  color: #c0c4cc; /* 调整图标颜色 */
}

.header-search-select .el-input__prefix .el-input__icon:hover {
  color: #409eff; /* 调整图标悬停颜色 */
}

</style>