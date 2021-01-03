import Vue from 'vue'
import App from './App.vue'
import router from './route'

import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui' //element-ui的全部组件
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css
import axios from 'axios'
import store from './store'


Vue.use(ElementUI) //使用elementUI

Vue.prototype.$axios = axios
axios.defaults.baseURL = '/api'
Vue.config.productionTip = false

router.beforeEach(((to, from, next) => {
  if (to.meta.requireAuth) {
    if (store.state.user.username){
      next()
    }else {
      next({
        path: 'login',
        query: {redirect:to.fullPath}
      })
    }
  } else {
    next()
  }
}))

new Vue({
  render: h => h(App),
  router,
  store,
  components: {App},
  template: '<App/>'
}).$mount('#app')


