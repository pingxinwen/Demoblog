<template>
  <div id="square">
    <AllBlog></AllBlog>
  </div>
</template>

<script>
import AllBlog from "@/components/square/AllBlog";
export default {
name: "Square",
  components: {AllBlog}
}
</script>

<style scoped>
#square {
  width: 40%;
  margin:20px auto;
}
</style>