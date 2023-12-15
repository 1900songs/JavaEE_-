import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)


const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/home',
    children: [
      { path: '403', name: 'NoAuth', meta: { name: '无权限' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: '管理员信息' }, component: () => import('../views/manager/Admin') },
      { path: 'doctor', name: 'Doctor', meta: { name: '医生信息' }, component: () => import('../views/manager/Doctor') },
      { path: 'user', name: 'User', meta: { name: '患者信息' }, component: () => import('../views/manager/User') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/AdminPerson') },
      { path: 'doctorPerson', name: 'DoctorPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/DoctorPerson') },
      { path: 'userPerson', name: 'UserPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/UserPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' }, component: () => import('../views/manager/Password') },
      { path: 'notice', name: 'Notice', meta: { name: '公告信息' }, component: () => import('../views/manager/Notice') },
      { path: 'department', name: 'Department', meta: { name: '科室信息' }, component: () => import('../views/manager/Department') },
      { path: 'plan', name: 'Plan', meta: { name: '医生排班' }, component: () => import('../views/manager/Plan') },
      { path: 'doctorCard', name: 'DoctorCard', meta: { name: '预约挂号' }, component: () => import('../views/manager/DoctorCard') },
      { path: 'reserve', name: 'Reserve', meta: { name: '患者挂号' }, component: () => import('../views/manager/Reserve') },
      { path: 'record', name: 'Record', meta: { name: '我的就诊' }, component: () => import('../views/manager/Record') },
      { path: 'registration', name: 'Registration', meta: { name: '住院登记' }, component: () => import('../views/manager/Registration') },
    ]
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    children: [
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/front/Home') },
      { path: 'person', name: 'Person', meta: { name: '个人信息' }, component: () => import('../views/front/Person') },
    ]
  },
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', meta: { name: '注册' }, component: () => import('../views/Register.vue') },
  { path: '*', name: 'NotFound', meta: { name: '无法访问' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router
