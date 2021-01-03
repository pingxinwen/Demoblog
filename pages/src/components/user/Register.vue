<template>
  <div id="register_form">
    <el-form :model="userInfo" status-icon :rules="rules" ref="userInfo" label-width="100px" class="form_container">
      <h3>注册用户</h3>
      <el-form-item label="用户名" prop="name">
        <el-input v-model="userInfo.name" placeholder="仅支持字母、数字、下划线，5个字符以上"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="userInfo.password" placeholder="仅支持字母、数字、英文符号，6个字符以上"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPassword">
        <el-input type="password" v-model="userInfo.checkPassword" placeholder="请再次输入密码"></el-input>
      </el-form-item>
      <el-form-item label="电子邮箱" prop="email">
        <el-input v-model="userInfo.email" placeholder="邮箱可用来找回密码"></el-input>
      </el-form-item>
      <el-button type="primary" @click="submit('userInfo')">提交</el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    const validatorPass2 = (rule, value, callback) => {
      if (value !== this.userInfo.password) {
        callback(new Error('两次密码不一致'))
      } else {
        callback()
      }
    };
    const validatorEmail = (rule, value, callback) => {
      if (value !== '') {
        const reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!reg.test(value)) {
          return callback(new Error('邮箱格式不正确'))
        }
      }
      callback()
    };
    return {
      userInfo: {
        name: '',
        password: '',
        checkPassword: '',
        email: ''
      },
      rules: {
        name: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, message: '密码至少要有六个字符', trigger: "blur"}
        ],
        checkPassword: [
          {required: true, message: '请再次输入密码', trigger: 'blur'},
          {min: 6, message: '密码至少要有六个字符', trigger: "blur"},
          {validator: validatorPass2, trigger: 'blur'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'},
          {validator: validatorEmail, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if(valid) {
            this.$axios
          .post('/register',this.userInfo)
          .then(response =>{
            if (response.data.mes === 'OK'){
              //显示提醒信息
              this.$message({
                message:"注册成功！自动跳转到登录界面。",
                type:'success'
              })
              //此时应引导用户前往登录界面
              setTimeout(()=>{
                this.$router.push("login")
              },3000)
            } else if(response.data.mes === 'user exist'){
              this.$message({
                message:"已存在此用户。请尝试其他用户名！",
                type:'warning'
              })
            } else if (response.data.mes === 'error check'){
              this.$message.error({
                message:"密码不一致，请再次检查密码！"
              })
            } else if (response.data.mes === 'empty data'){
              this.$message({
                message:"提交数据错误，请重试。",
                type:"warning"
              })
            } else {
              this.$message({
                message:"提交遇到问题，请重试。",
                type:"waring"
              })
            }
          })
          .catch(()=>{
            this.$message.error({
              message:"网络连接失败"
            })
          })
        }else {
          this.$message.error({
            message:"请检查填写数据！"
          })
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
#register_form {
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

</style>