<script setup>
import { ref, onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import LoginService from "@/service/LoginService/Login";

const isLoggedIn = ref(false);
const router = useRouter();
const route = useRoute();


onMounted(async () => {

  const storedLoginStatus = localStorage.getItem("isLoggedIn");

  if (storedLoginStatus === "true") {

    try {
      await LoginService.getCurrentUser(); 
      isLoggedIn.value = true;
    } catch (err) {
    
      isLoggedIn.value = false; 
      localStorage.removeItem("isLoggedIn"); 
    }
  } else {
  
    isLoggedIn.value = false; 
  }
});


const isLoginPage = computed(() => route.path === '/login')

const getLoginLink = computed(() => {
  if (isLoginPage.value) {
    return null  
  }
  
  return `/login?redirect=${encodeURIComponent(route.fullPath)}`
})

const goToLogin = () => {
  if (getLoginLink.value) {
    router.push(getLoginLink.value)
  }
}


</script>

<template>
  <header class="client-header">
    <div class="logo">
      <router-link to="/">Nextvibe</router-link>
    </div>
    <nav>
      <ul>
        <li><router-link to="/category">Danh mục</router-link></li>
        <li><router-link to="/fiter-price">Lọc Theo Giá</router-link></li>
        <li><router-link to="/searchProduct">Bạn muốn tìm gì</router-link></li>
        <!-- <li v-if="isLoggedIn">
          <a href="#" @click.prevent="handleLogout">Đăng xuất</a>
        </li> -->
        <li>
          <a
            href="#"
            @click.prevent="goToLogin"
            :class="{ 'disabled-link': isLoginPage }"
            :disabled="isLoginPage"
          >
            Đăng nhập
          </a>
        </li>
      </ul>
    </nav>
  </header>
</template>

<style scoped>
.client-header {
  background-color: #333;
  color: white;
  padding: 10px 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.client-header .logo a {
  color: white;
  font-size: 24px;
  text-decoration: none;
}

nav ul {
  list-style-type: none;
  padding: 0;
  display: flex;
}

nav ul li {
  margin-right: 20px;
}

nav ul li a {
  color: white;
  text-decoration: none;
  font-size: 16px;
}

nav ul li a:hover {
  text-decoration: underline;
}

</style>