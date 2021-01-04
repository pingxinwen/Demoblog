<template>
  <div id="home">
    <Editor></Editor>
    <BlogShow ref="ChildUpdate"></BlogShow>
  </div>
</template>

<script>
import Editor from "@/components/home/Editor";
import BlogShow from "@/components/home/BlogShow";
export default {
  name: "Home",
  components: {BlogShow, Editor},
  methods:{
    flush(){
      this.$refs.childUpdate.getBlog()
    }
  }
}
</script>

<style scoped>
#home {
  width: 40%;
  margin:20px auto;
}
</style>