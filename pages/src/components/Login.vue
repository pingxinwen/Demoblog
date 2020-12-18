<template>
  <div id="login_form">
    <el-form label-width="auto" class="login_container">
      <h3>登录微博</h3>
      <el-form-item>
        <el-input type="text" v-model="loginForm.username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item style="text-align: center;width: 100%" size="large">
        <el-button class="login-button" @click="login">登录</el-button>
        <el-button class="login-button">注册</el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="login-button">游客访问</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    login() {
      this.$axios
          .post('/login', {
            username: this.loginForm.username,
            password: this.loginForm.password
          })
          .then(function (response) {
            if (response.data === 1 && response.status === 200) {
              console.log("登录成功")
            } else if(response.data === -1){
              console.log("密码错误")
            }
          })
          .catch(function () {
            console.log("连接失败")
          })
    }
  }
}
</script>

<style scoped>
body {
  margin: 0;
}

#login_form {
  height: 100%;
  width: 100%;
  position: absolute;
  left: 0;
  top: 0;
  background: url("../assets/img/pid-83928679.jpg") no-repeat;
}

.login_container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 30%;
  padding: 35px 35px 15px 35px;
  background: #ffffff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 35px #cac6c6;
}

.login_container h3 {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}

.login-button {
  margin: 0 auto;
  background: #eaeaea;
}
</style>