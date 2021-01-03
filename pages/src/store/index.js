import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        isLogin:false,
        user:{
            uname:'游客',
            username:'游客'
        }
    },
    mutations:{
        login(state, userinfo){
            state.isLogin = true
            state.user = userinfo
        }
    }
})