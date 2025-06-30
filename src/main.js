// src/main.js

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from './router'
import App from './App.vue'

// Import CSS của các thư viện
import 'bootstrap/dist/css/bootstrap.min.css'
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';

// Import CSS tùy chỉnh của bạn
import './assets/style.css'

// Import JS của Bootstrap
import 'bootstrap'

const pinia = createPinia()
const app = createApp(App)

app.use(router)
app.use(pinia)
app.mount('#app')