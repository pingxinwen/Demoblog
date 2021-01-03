<template>
  <div id="home">
    <Editor></Editor>
    <BlogShow></BlogShow>
  </div>
</template>

<script>
import Editor from "@/components/home/Editor";
import BlogShow from "@/components/home/BlogShow";
export default {
  name: "Home",
  components: {BlogShow, Editor}
}
</script>

<style scoped>
#home {
  width: 40%;
  margin:20px auto;
}
</style>