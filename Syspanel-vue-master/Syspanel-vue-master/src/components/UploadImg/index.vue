<template>
  <div>
    <div class="upload-main">
      <el-upload
        action="#"
        :auto-upload="false"
        list-type="picture-card"
        :on-change="handleChange"
        :on-remove="handleRemove"
        :on-exceed="handleExceed"
        :on-preview="handlePreview"
        :file-list="imageList"
        :multiple="multiple"
        :limit="limit"
        accept="image/*"
      >
        <i class="el-icon-plus"></i>
      </el-upload>
      <div class="limit-text">{{ imageList.length }}/{{ limit }}</div>
    </div>

    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="" />
    </el-dialog>
  </div>
</template>

<script>
import http from '@http'
import api from '@api'
export default {
  props: {
    limit: {
      type: Number,
      default: 4,
    },
    multiple: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      // 已选择的图片列表
      imageList: [],
      dialogImageUrl: '',
      dialogVisible: false,
    }
  },
  methods: {
    // 选择图片触发
    handleChange(file) {
      // 创建form对象
      let param = new FormData()
      // 通过append向form对象添加数据
      param.append('file', file.raw)
      // 添加请求头
      let config = {
        headers: { 'Content-Type': 'multipart/form-data' },
      }
      // 请求接口
      http
        .post(`${api.fayongApi}/app/file/upload`, param, config)
        .then((res) => {
          if (res.code === 1 && res.msg) {
            this.imageList.push({
              uid: file.uid,
              url: res.msg,
              name: res.originalFilename,
            })
          }
        })
        .catch(() => {
          this.imageList.pop()
          this.msgerror('图片上传失败,请重试')
        })
    },
    // 删除图片触发
    handleRemove(file) {
      // 当前删除的图片下标
      const removeIndex = this.imageList.findIndex(
        (item) => item.uid === file.uid
      )
      // 删除已选择的数据
      this.imageList.splice(removeIndex, 1)
    },
    // 文件超出个数限制
    handleExceed() {
      this.$message.error(`最多上传${this.limit}张`)
    },
    // 回填图片信息
    setFileList(fileList) {
      this.imageList = fileList
    },
    // 预览大图
    handlePreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
  },
  updated() {
    this.$nextTick(() => {
      this.$emit('handleChangeImg', this.imageList)
    })
  },
}
</script>

<style scoped lang="less">
.upload-main {
  display: flex;
  align-items: flex-end;
  .limit-text {
    margin-left: 15px;
    color: #9e9e9e;
  }
}
</style>