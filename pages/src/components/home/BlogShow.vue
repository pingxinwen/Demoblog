<template>
  <div id="blog-show">
    <div id="controller">
      <el-dropdown @command="handleCommand">
        <el-button type="primary">
          {{ show_type[command_now] }}<i class="el-icon-arrow-down"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="time">{{ show_type.time }}</el-dropdown-item>
          <el-dropdown-item command="comment">{{ show_type.comment }}</el-dropdown-item>
          <el-dropdown-item command="thumb">{{ show_type.thumb }}</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <blog-item
      v-for="item in blog_data"
      v-bind:key="item.id"
      v-bind:blog="item"
    ></blog-item>
  </div>
</template>

<script>
import BlogItem from "@/components/common/BlogItem";
export default {
  name: "BlogShow",
  components: {BlogItem},
  data() {
    return {
      command_now: 'time',
      show_type: {time: '按时间排序', comment: '按评论排序', thumb: '按点赞排序'},
      blog_data: [{
        id: 0,
        uname: '测试',
        time: new Date(),
        content: '测试用正文',
        imgUrls: ['https://pic4.zhimg.com/80/v2-fa7a5f8d52a41060c2c0b66ca2bb37fb_720w.jpg?source=3af55fa1'],
        comments: [],
        likes: 0
      }, {
        id:1,
        uname: '测试2',
        time: new Date(),
        content: '测试用正文',
        imgUrls: ['https://pic4.zhimg.com/80/v2-fa7a5f8d52a41060c2c0b66ca2bb37fb_720w.jpg?source=3af55fa1'],
        comments: [],
        likes: 0
      }]
    }
  },
  methods: {
    handleCommand: function (command) {
      if (command !== this.command_now) {
        this.blog_data.splice(0, this.blog_data.length)
        this.command_now = command
        switch (command) {
          case 'thumb':
            //重新请求
            break
          case 'time':
            break
          case 'comment':
            break
        }
      }
    }
  }
}
</script>

<style scoped>
#controller {
  width: 100%;
  margin: 20px auto;
  background: white;
  vertical-align: center;
  text-align: right;
}

</style>