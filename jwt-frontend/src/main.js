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
    getUser(state) { return state.user },
    getToken(state) { return state.token },
  },
  mutations: {
    setUser (state, user) { state.user = user },
    setToken (state, token) { state.token = token}
  }
})

// routers

import Hello from '@/components/HelloWorld.vue';

const routes = [
  { path: '/hello', component: Hello },
]

const router = new VueRouter({
  routes
})

Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
