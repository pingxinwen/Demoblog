<template>
  <div>
    <div class="userIcon">
      <img
          src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Vue.js_Logo_2.svg/1200px-Vue.js_Logo_2.svg.png">
    </div>
    <div class="main-content">
      <div class="username">{{ comment.uname }}</div>
      <div class="time">{{ comment.time }}</div>
      <div class="content">{{ comment.content }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommentItem",
  props: ['comment']
}
</script>

<style scoped>
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
</style>