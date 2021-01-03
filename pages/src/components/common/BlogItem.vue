<template>
  <div class="blog-item">
    <div class="userIcon">
      <img
          src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Vue.js_Logo_2.svg/1200px-Vue.js_Logo_2.svg.png">
    </div>
    <div class="main-content">
      <div class="username">{{ blog.uname }}</div>
      <div class="time">{{ blog.time.toISOString() }}</div>
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
          <el-button size="mini" plain>评论 {{ blog.comments.length }}</el-button>
        </el-col>
        <el-col :span="6">
          <el-button size="mini" plain>点赞 {{ blog.thumb }}</el-button>
        </el-col>
      </el-row>
    </div>
<!--    <div v-if=""></div>-->
  </div>
</template>

<script>
export default {
  name: "BlogItem",
  props:['blog']
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
  padding-bottom: 40px;
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
  padding-top: 8px;
  font-size: 16px;
  color: #333;
}
</style>