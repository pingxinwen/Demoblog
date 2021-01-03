import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/user/Login'
import Register from "@/components/user/Register";
import Index from "@/components/Index";
import Home from "@/components/home/Home";
import Square from "@/components/square/Square";
import Identity from "@/components/identity/Identity";

Vue.use(VueRouter)

const routes=[
    {
        path: '/index',
        name: 'index',
        component: Index,
        redirect: '/square',
        children: [
            {
                path: '/home',
                name: 'home',
                component: Home,
                meta:{
                    requireAuth: false
                }
            },
            {
                path: '/square',
                name: 'square',
                component: Square,
                meta: {
                    requireAuth: false
                }
            },
            {
                path: '/i/:user',
                name: 'identity',
                component: Identity,
                meta: {
                    requireAuth: false
                }
            }
        ]
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
    mode:'history',
    routes: routes
})
export default router