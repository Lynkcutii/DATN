import './assets/main.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './service/LoginService/Store'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

axios.defaults.baseURL = import.meta.env.VITE_API_URL || 'http://localhost:8080'
axios.defaults.withCredentials = true

const app = createApp(App)
app.use(router)
app.use(store)
app.mount('#app')