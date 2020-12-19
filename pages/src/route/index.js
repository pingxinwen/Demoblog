import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/User/Login'
import Register from "@/components/User/Register";

Vue.use(VueRouter)

const routes=[
    {
        path: '/',
        redirect: 'login'
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/register',
        name:'register',
        component: Register
    }
]
const router = new VueRouter({
    routes: routes
})
export default router