<!-- 
<template>
    <el-card style="width: 95%; margin-top: 10px; margin-left: 15px;">
        <div class="talkContent">
      <div class="talkShow">
        <div :class="[(item.person=='mechanical')?'mechanicalTalk':'mineTalk']" v-for="(item,index) in talkList" :key="index">
            <span>{{item.say}}</span>
        </div>
      </div>
      <div class="talkInput">
        <form @submit.prevent="getQuestion" class="userSearch">
          <el-input
            placeholder="请输入内容"
            v-model="contentVal"
            size="small"
            style="width: 100%;">
            <i slot="suffix" class="el-input__icon el-icon-position" style="cursor: pointer;" @click="getQuestion"></i>
          </el-input>
       </form>
      </div>
    </div>
    </el-card>
    
  </template>
  
  <script>
  export default {
    data() {
      return {
        talkList: [
          { id:"1", person: 'mechanical', say: '你好，有什么可以帮到你呢？' },
        ],
        isMine: 1,
        contentVal: '',
      };
    },
    methods: {
      getQuestion() {
        if (this.contentVal == '') {
          this.$message.error('请输入内容');
          return;
        }
        // admin提问数据push()
        this.talkList.push({ id: Date.now(), person:'admin', say: this.contentVal });
        // 清空输入栏数据
        this.contentVal = '';
        this.getAnswer();
      },
      async getAnswer() {
        // 调用API获取回答， 模拟后端返回的数据流式输出
        const data ='文字逐个展示，文字逐个展示，文字逐个展示文字逐个展示文字逐个展示文字逐个展示文字逐个展示文字逐个展示文字逐个展示文字逐个展示文字逐个展示';
        // mechanical 模拟回答
        this.talkList.push({ id: Date.now(), person:'mechanical', say: '...' });
        // 流式输出文字逐个展示
        let text = "";
        const length = this.talkList.length;
        for (let i = 0; i < data.length; i++) {
          text += data.charAt(i);
          await new Promise((resolve) => {
            setTimeout(resolve, 50)
          });
          this.talkList[length - 1].say= text;
        }
      }
    },
  };
  </script>
  
  <style scoped>
  .talkContent {
    margin: 50px auto 0;
    font-size: 14px;
  }
  
  .talkShow {
    height: 400px;
    margin: 10px auto 0;
    overflow: auto;
    border: #e6e3e3 solid 1px;
  }
  
  .talkInput {
    margin: 10px auto 0;
  }
  
  .mechanicalTalk {
    margin: 10px;
  }
  
  .mechanicalTalk span {
    display: inline-block;
    background:#b3ceeb;
    border-radius: 10px;
    padding: 5px 10px;
    border: 1px solid rgb(214, 216, 219);
    border-top-left-radius: 0px;
    word-break: break-all;
    text-align: left;
  }
  
  .mineTalk {
    margin: 10px;
    text-align: right;
  }
  
  .mineTalk span {
    display: inline-block;
    border-radius: 10px;
    border-top-right-radius: 0px;
    margin-top: 10px;
    background: #d4e2ef;
    color: #fff;
    padding: 5px 10px;
    word-break: break-all;
    text-align: left;
  }
  
  </style>
  
   -->
   <template>
    <el-card style="width: 95%; margin-top: 10px; margin-left: 15px;
    background-size: cover;
    " class="ba">
        <template v-slot:header>
              <div style="display: flex; align-items: center;">
                <div style="width: 5px; height: 15px; background-color: #007bff; border-radius: 5px; margin-right: 8px;">
                </div>
                <span style="font-weight: bold; font-size: 24px;">AI Panel</span>
              </div>
            </template>
      <div class="talkContent">
        <div class="talkShow">
          <div v-for="(item, index) in talkList" :key="index" :class="item.person === 'mechanical' ? 'mechanicalTalk' : 'mineTalk'">
           <!-- 头像根据person条件渲染 -->
          <img v-if="item.person === 'mechanical'" :src="require('@/assets/logo/logo.png')" class="avatar" />
          
            <span>{{ item.say }}</span>
            <img v-if="item.person === 'admin'" :src="require('@/assets/logo/userimg.jpeg')" class="avatars" />
          </div>
        </div>
        <div class="talkInput">
          <form @submit.prevent="getQuestion" class="userSearch">
            <el-input
              placeholder="请输入内容"
              v-model="contentVal"
             size="larget"
              style="width: 100%;">
              <i slot="suffix" 
              class="el-input__icon el-icon-s-promotion"
               style="cursor: pointer;"
                @click="getQuestion"
                :class="{ 'icon-clicked': isIconClicked }"></i>
            </el-input>
          </form>
        </div>
      </div>
    </el-card>
  </template>
  
  <script>
  import {Aiqwen2} from '../../api/Res/post'

  export default {
    data() {
      return {
        talkList: [
          { id: "1", person: 'mechanical', say: '你好，有什么可以帮到你呢？' },
        ],
        contentVal: '',
        isIconClicked: false, // 初始状态为未点击
        data:'hello'
      };
    },
    mounted() {
    this.$notify({
                title: '提示',
                type: 'success',
                message: '和AI进行对话',
                duration: 1500,
                dangerouslyUseHTMLString: true,
                customClass:'notify-success',
              });   
  },
    methods: {
      getQuestion() {
        this.isIconClicked = true; // 点击后设置为已点击状态
           if (this.contentVal === '') {
          this.$message.error('请输入内容');
          // this.isIconClicked = false;
          return;
        }
        // admin提问数据push()
        this.talkList.push({ id: Date.now(), person: 'admin', say: this.contentVal });
        // 清空输入栏数据
        // this.contentVal = '';
        this.getAnswer();
      },
      getAnswer() {
        // 调用API获取回答，模拟后端返回的数据流式输出
        // let data = 'hello';

        // Aiqwen2(this.contentVal).then(req=>{
        //   this.contentVal = '';
        //   if(req.data.code === 2000){
        //     this.data = req.data.data;
            
        //   }else{
        //     this.data = '回答失败，请检查'
        //   }
        // });
        
        // mechanical 模拟回答
        this.talkList.push({ id: Date.now(), person: 'mechanical', say: '...' });
        // 流式输出文字逐个展示
        let text = "";
        const length = this.talkList.length;
        Aiqwen2(this.contentVal).then(req=>{
          this.contentVal = '';
          if(req.data.code === 2000){
            this.data = req.data.data;
          }else{
            this.data = '回答失败，请检查'
          }
          for (let i = 0; i < this.data.length; i++) {
          text += this.data.charAt(i);
          // await new Promise((resolve) => {
          //   setTimeout(resolve, 50)
          // });
          this.talkList[length - 1].say = text;
        }
        });
        // for (let i = 0; i < this.data.length; i++) {
        //   text += this.data.charAt(i);
        //   // await new Promise((resolve) => {
        //   //   setTimeout(resolve, 50)
        //   // });
        //   this.talkList[length - 1].say = text;
        // }
      }
    },
  };
  </script>
  
  <style scoped>
  .ba{
    background-image: url('../../assets/images/1panel-login-bg.png'); 
    background-size:100% 100%;
  }
  .talkContent {
    margin: auto;
    font-size: 14px;
    height: 620px;
    background-image: url('../../assets/svg/syspanel.svg'); 
    background-position: center;
    background-repeat: no-repeat; /* 确保背景图片不重复 */
    background-size: 20%; /* 使背景图片适应容器大小 */
    background-color:#fff; 
}
  .talkShow {
  height: 560px;
  margin: 10px auto 0;
  overflow: auto;
  border: #d2e3f5 solid 4px;
  position: relative;
  background-color: rgba(252, 249, 249, 0.378);
 
}
  /* .talkInput {
    margin: 10px auto 0;
  height: 40px;

  } */

  .talkInput {
  margin: 10px auto 0;
  background-color: #f0f0f0; /* 设置输入框背景颜色 */
  border: 4px solid #ccc; /* 设置输入框边框 */
  border-radius: 5px; /* 设置输入框圆角 */
  width: 100%;
}


.userSearch i {
  font-size: 24px; /* 设置图标大小 */
  margin-left: 10px; /* 设置图标与输入框之间的间距 */
}
.userSearch input:active + i {
  color: #65c1e8; /* 设置点击后的图标颜色 */
}
  .avatar {
    width: 40px;
    height: 40px;
    /* border-radius: 50%;
    display: inline-block;
    vertical-align: middle;
    object-fit: cover; */
    margin-top: 5px;
}

  .avatars{
    width: 40px;
    height: 40px;
    border-radius: 50%;
    display: inline-block;
    vertical-align: middle;
    object-fit: cover;
  }
  .mechanicalTalk {
    margin-top: 10px;
    margin: 15px;
    display: flex;
    align-items: center;
  }
  
  .mechanicalTalk .avatar {
    margin-right: 10px;
  }
  
  /* .mechanicalTalk span {
    display: inline-block;
    background: #b3ceeb;
    border-radius: 10px;
    padding: 5px 10px;
    border: 1px solid rgb(214, 216, 219);
    border-top-left-radius: 0px;
    word-break: break-all;
    text-align: left;
  } */
  .mechanicalTalk span {
    display: inline-block;
    background: #ffffff; /* 背景色可以选择较浅的灰色，更像聊天框 */
    border-radius: 15px; /* 增大圆角，使其看起来更像聊天气泡 */
    border-bottom-left-radius: 0px;
    padding: 10px 15px; /* 增加内边距，使文本更舒适 */
    border: 1px solid #ccc; /* 更柔和的边框颜色 */
    word-break: break-word; /* 改成 break-word 以避免单词被截断 */
    text-align: left;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影效果，使其有浮动感 */
    max-width: 80%; /* 设置最大宽度，避免过宽 */
    position: relative; /* 使伪元素定位更方便 */
    margin-bottom: 10px; /* 添加底部间距，方便多个聊天框排列 */
}


  .mineTalk {
    margin: 10px;
    display: flex;
    align-items: center;
    justify-content: flex-end;

  }
  
  .mineTalk .avatars {
    margin-left: 10px;
  }
  
  .mineTalk span {
    display: inline-block;
    background: #65c1e8;
    padding: 10px 15px; /* 增加内边距，使文本更舒适 */
    color: #fff;
    border-radius: 10px;
    border-bottom-right-radius: 0px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影效果，使其有浮动感 */
    max-width: 80%; /* 设置最大宽度，避免过宽 */
    padding: 5px 10px;
    word-break: break-all;
    text-align: left;
  }
  .icon-clicked {
  color: blue; /* 点击后图标的颜色 */
}
  </style>