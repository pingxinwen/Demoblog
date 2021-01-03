<template>
  <el-menu
      :default-active="this.$route.path"
      router
      mode="horizontal"
      text-color="#222"
      active-text-color="#66CCFF"
      class="nav"
      @select="handler">
    <el-menu-item
          style="left: 8%"
          v-for="(item,i) in navList"
          :key="i"
          :index="item.name">
      {{ item.title }}
    </el-menu-item>
    <span class="title">迷你微博  -  Mini Blog</span>
    <div style="float: right" class="welcome">
      <span>欢迎您，{{ $store.state.user.uname }}</span>
      <el-button round style="margin-left: 10px">设置</el-button>
      <el-button round style="margin-left: 10px" v-show="!$store.state.isLogin" @click="$router.push('login')">登录</el-button>
    </div>
  </el-menu>
</template>

<script>
export default {
  name: "NavMenu",
  data() {
    return {
      navList: [
        {name: '/home', title: '首页'},
        {name: '/square', title: '广场'},
        {name: '/i/'+this.$store.state.user.username, title: '个人中心'}
      ],
      name: ''
    }
  },
  methods:{
    handler: function (index){
      if(!this.$store.state.isLogin){
        if(index==='/home'||index==='/i/'+this.$store.state.user.username){
          this.$message.warning('请您登录')
        }
      }
    }
  }
}
</script>

<style scoped>
.nav {
  width: 100%;
  padding-top: 0;
}

span.title {
  position: absolute;
  padding-top: 15px;
  font-size: 20px;
  font-weight: bold;
  right: 43%;
}

.welcome {
  position: absolute;
  font-size: 18px;
  padding-top: 15px;
  right: 8%;
}
</style>