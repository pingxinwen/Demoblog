import Vue from 'vue'
import App from './App.vue'
import router from './route'
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui' //element-ui的全部组件
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css
import axios from 'axios'

Vue.use(ElementUI) //使用elementUI

Vue.use(axios)
Vue.prototype.$axios = axios
axios.defaults.baseURL = '/api'
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  components: {App},
  template: '<App/>'
}).$mount('#app')


