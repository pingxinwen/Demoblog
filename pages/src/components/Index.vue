<template>
  <div>
    <nav-menu></nav-menu>
    <router-view/>
  </div>
</template>

<script>
import NavMenu from "@/components/common/NavMenu";
export default {
name: "Index",
  components:{NavMenu}
}
</script>

<style scoped>

</style>