<template>
  <div id="blog-show">
    <div id="controller">
      <el-dropdown @command="handleCommand">
        <el-button type="primary">
          {{ show_type[command_now] }}<i class="el-icon-arrow-down"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="time">{{ show_type.time }}</el-dropdown-item>
          <el-dropdown-item command="commentNum">{{ show_type.commentNum }}</el-dropdown-item>
          <el-dropdown-item command="likes">{{ show_type.likes }}</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <blog-item
        v-for="item in blog_data"
        v-bind:key="item.id"
        v-bind:blog="item"
    ></blog-item>
    <el-pagination
        :current-page="pages"
        @current-change="getNew"
        background
        layout="prev,pager,next"
        :page-count="page_limit"></el-pagination>
  </div>
</template>

<script>
import BlogItem from "@/components/common/BlogItem";

export default {
  name: "AllBlog",
  components: {BlogItem},
  mounted() {
    this.getBlog('time')
  },
  data() {
    return {
      command_now: 'time',
      show_type: {time: '按时间排序', commentNum: '按评论排序', likes: '按点赞排序'},
      pages: 1,
      page_limit: 0,
      blog_data: []
    }
  },
  methods: {
    handleCommand(command) {
      if (command !== this.command_now) {
        this.blog_data.splice(0, this.blog_data.length)
        this.command_now = command
        this.getBlog(this.command_now)
      }
    },
    getBlog(command) {
      console.log(this.pages)
      this.$axios.get("/blog", {
        params: {
          page: this.pages - 1,
          type: command,
          username:this.$store.state.user.username
        }
      }).then(response => {
        this.blog_data = response.data.blogs.content
        //this.pages = response.data.content.number
        this.page_limit = response.data.blogs.totalPages
      }).catch(() => {
            this.$message.error("网络故障")
      })
    },
    getNew(val) {
      this.pages = val
      this.getBlog(this.command_now)
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