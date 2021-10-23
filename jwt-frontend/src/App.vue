<template>
  <router-view></router-view>
</template>

<script>

import axios from "axios";
axios.defaults.baseURL = 'http://localhost:8081';

export default {

    name: 'App',
    components: {
      
    },

    computed:{
      user(){ return this.$store.state.user },
    }, 

    // computed: {
    //     usuarioLogado(){   
    //       return this.$store.state.login.usuarioLogado   
    //     },
    //   },

    beforeMount(){
      if(! this.user ||  Object.keys(this.user).length === 0 )
        this.$router.push('/login')
    }

}
</script>

<style>

</style>
