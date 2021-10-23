import Vue from 'vue'
import App from './App.vue'

import Vuex from 'vuex';
import VueRouter from 'vue-router'

import './bootstrap-vue';

Vue.use(Vuex);
Vue.use(VueRouter);

// vuex

const store = new Vuex.Store({
  state: {
    user: {},
    token: '',
  },
  getters:{
    user(state) { return state.user },
    token(state) { return state.token },
  },
  mutations: {
    setUser (state, user) { state.user = user },
    setToken (state, token) { state.token = token}
  }
})

// routers

import Home from '@/components/Home.vue';
import Login from '@/components/Login.vue';

const routes = [
  { path: '/home', component: Home },
  { path: '/login', component: Login },
]

const router = new VueRouter({
  mode: 'history',
  routes : routes,
})

Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
