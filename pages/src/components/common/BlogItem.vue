<template>
  <div class="blog-item">
    <div class="userIcon">
      <img
          src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Vue.js_Logo_2.svg/1200px-Vue.js_Logo_2.svg.png">
    </div>
    <div class="main-content">
      <div class="username">{{ getName }}</div>
      <div class="time">{{ getTime }}</div>
      <div class="content">{{ blog.content }}</div>
      <div class="blog-img">
        <el-image
            style="width: 100px; height: 100px"
            v-for="url in blog.imgUrls"
            :src="url"
            :key="url"
            :preview-src-list="blog.imgUrls">
        </el-image>
      </div>
      <el-row>
        <el-col :span="6">
          <el-button size="mini" plain @click="showComment">评论 {{ blog.comments.length }}</el-button>
        </el-col>
        <el-col :span="6">
          <el-button size="mini" plain @click="setlike" v-bind:type="isLike">点赞 {{ blog.likes }}</el-button>
        </el-col>
      </el-row>
    </div>
    <div v-if="comment_show">
      <div class="input">
        <el-input
            type="textarea"
            :rows="3"
            v-model="comment"
            resize="none"
            placeholder="发表友善的评价"
        ></el-input>
        <el-row style="margin: 10px 0">
          <el-col :span="3" :offset="20">
            <el-button @click="post_comment">提交</el-button>
          </el-col>
        </el-row>
      </div>
      <div v-if="blog.comments.length===0" style="padding: 30px 0;color: #cac6c6;font-size: 14px;text-align: center">
        <div>暂时还没有评论……</div>
      </div>
      <CommentItem
          v-for="item in blog.comments"
          v-bind:key="item.id"
          v-bind:comment="item"
      ></CommentItem>
    </div>
  </div>
</template>

<script>
import CommentItem from "@/components/common/CommentItem";
export default {
  name: "BlogItem",
  components: {CommentItem},
  props:['blog'],
  data(){
    return {
      comment_show:false,
      comment:''
    }
  },
  computed:{
    isLike(){
      return this.blog.like?'primary':''
    },
    getTime(){
      let dt = new Date(this.blog.time);
      return dt.toLocaleString()
    },
    getName(){
      return this.blog.uname===''?this.blog.username:this.blog.uname
    }
  },
  methods:{
    setlike(){
      if(!this.blog.like&&this.$store.state.isLogin){
        //发起点赞请求
        this.$axios.post('/thumb',{
          username:this.$store.state.user.username,
          blogId:this.blog.id
        })
        .then(response => {
          if (response.data.state==='OK'){
            this.$message.success('点赞成功')
            this.blog.likes+=1
            this.blog.like=false
          }else if(response.data.state === 'already exist'){
            this.$message.info("您已经点过赞了")
            this.blog.like=true
          }
        })
        .catch(()=>{
          this.$message.error("网络连接失败")
        })
      }
    },
    showComment(){
      this.comment_show=!this.comment_show;
    },
    post_comment(){
      this.$axios.post("/comment",{
        blog_id:this.blog.id,
        content:this.comment
      })
          .then(response =>{
            if (response.data.state==="OK"){
              this.$message.success("发布成功")
              this.blog.comments.push(response.data.comment)
            }
          })
    }
  }
}
</script>

<style scoped>
.blog-item {
  width: 100%;
  margin: 30px auto;
  background: white;
  text-align: left;
}

.userIcon {
  float: left;
  width: 48px;
  height: 48px;
  margin-left: 24px;
  margin-top: 27px;
}

.userIcon img {
  width: 48px;
  height: 48px;
}

.main-content {
  margin-left: 88px;
  padding-top: 27px;
  padding-bottom: 27px;
}

.username {
  margin: 0;
  font-size: 16px;
  color: #222;
}

.time {
  margin: 0;
  padding-top: 4px;
  font-size: 14px;
  color: #cac6c6;
}

.content {
  margin: 0;
  padding-top: 10px;
  padding-bottom: 10px;
  font-size: 16px;
  color: #333;
}

.input {
  margin: 20px 40px;
}
</style>