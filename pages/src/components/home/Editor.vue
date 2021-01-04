<template>
  <div class="editor">
    <el-input
        type="textarea"
        :rows="5"
        placholder="随时随地写入你的新想法！"
        v-model="text_raw"
        resize="none"
        style="">
    </el-input>
    <div>
      <el-row class="tool" style="vertical-align: center">
        <el-col :span="2">
          <div style="margin: auto 0">
            <el-button class="submit" size="small" icon="el-icon-picture" @click="change_upload"></el-button>
          </div>
        </el-col>
        <el-col :span="19" style="text-align: right;vertical-align: center">
          <div style="margin-right: 20px">还可以输入{{ 140 - text_raw.length }}字</div>
        </el-col>
        <el-col :span="3">
          <div style="margin: auto 10px auto 0">
            <el-button type="primary" size="small" class="submit" icon="el-icon-upload2" @click="post_blog">提交
            </el-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-upload
        v-show="show_image_upload"
        action="#"
        :auto-upload="false"
        :limit="9"
        multiple
        :file-list="pic_list"
        list-type="picture-card">
      <div>点击上传图片</div>
    </el-upload>
  </div>
</template>

<script>
export default {
  name: "Editor",
  data() {
    return {
      text_raw: '',
      pic_list: [],
      show_image_upload: false
    }
  },
  methods: {
    change_upload() {
      this.show_image_upload = !this.show_image_upload
    },
    post_blog() {
      if (this.text_raw.length > 140) {
        this.$message.error('字数超出限制')
      } else {
        this.$axios.post('/blog', {
          username: this.$store.state.user.username,
          content: this.text_raw,
          imgUrls: this.pic_list
        }).then(response => {
          if (response.data.state === 'OK') {
            this.$message.success('发布成功')
          } else {
            this.$message.error('发布失败')
          }
        }).catch(() => {
          this.$message.error('网络连接失败')
        })
      }
    }
  }
}
</script>

<style scoped>
textarea:focus {
  border-color: #111;
}

.editor {
  margin: 20px auto 0 auto;
  padding: 10px;
  background: white;
}

.tool {
  width: 100%;
  margin: 0;
  padding: 0 0 0 0;
}

.submit {
  margin: auto 0;
}
</style>