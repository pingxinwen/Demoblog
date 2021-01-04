<template>
  <div id="login_form">
    <el-form label-width="auto" class="form_container">
      <h3>登录微博</h3>
      <el-form-item>
        <el-input type="text" v-model="loginForm.username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%" size="large">
        <el-button type="primary" class="login-button" @click="login">登录</el-button>
        <el-button class="login-button" @click="register">注册</el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="login-button">游客访问</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import router from "@/route";

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },

    }
  },
  methods: {
    login() {
      this.$axios
          .post('/login', {
            username: this.loginForm.username,
            password: this.loginForm.password
          })
          .then(response => {
            if (response.data.state === 'OK') {
              this.$message.success("登录成功！")
              //存储用户信息
              if(response.data.info!=null)
                this.$store.commit('login',{
                  username: response.data.username,
                  uname:response.data.info.username
              })
              //之后跳转到首页
              setTimeout(()=>{
                if(this.$router.history)
                this.$router.replace('/home')
              },1000)
            } else if (response.data.state === 'Not exist') {
              this.$message.error("密码错误，请重试！")
            } else if (response.data.state === 'Wrong password') {
              this.$message.warning("用户名不存在！")
            }
          })
          .catch(()=>{
            this.$message.error("网络连接失败")
          })
    },
    register() {
      router.push({name:'register'})
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
}

.form_container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 30%;
  padding: 35px 35px 15px 35px;
  background: #ffffff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 35px #cac6c6;
}

.form_container h3 {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}

.login-button {
  margin: 0 30px;
}

</style>