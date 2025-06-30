<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import LoginService from '@/Service/LoginService/Login.js';

const isLoggedIn = ref(false);
const router = useRouter();
const user = ref(null);
const showLogout = ref(false); // Biến để kiểm soát hiển thị dropdown Đăng xuất

onMounted(async () => {
  const storedLoginStatus = localStorage.getItem('isLoggedIn');

  if (storedLoginStatus === 'true') {
    try {
      const currentUser = await LoginService.getCurrentUser();
      isLoggedIn.value = true;
      user.value = currentUser;
    } catch (err) {
      isLoggedIn.value = false;
      localStorage.removeItem('isLoggedIn');
    }
  } else {
    isLoggedIn.value = false;
  }
});

const handleLogout = async () => {
  try {
    await LoginService.logout();
    isLoggedIn.value = false;
    localStorage.removeItem('isLoggedIn');
    showLogout.value = false; // Ẩn dropdown sau khi đăng xuất
    router.push('/client/home'); // Chuyển về trang chủ sau khi đăng xuất
  } catch (err) {
    console.error('Lỗi đăng xuất:', err);
  }
};

// Hiển thị/ẩn dropdown khi nhấp vào tên
const toggleLogout = () => {
  if (isLoggedIn.value) {
    showLogout.value = !showLogout.value;
  }
};
</script>

<template>
  <header class="client-header">
    <div class="logo">
      <router-link to="/client/home">Trang chủ</router-link>
    </div>
    <nav>
      <ul>
        <li><router-link to="/client/category">Danh mục</router-link></li>
        <li><router-link to="/client/users">Lọc Theo Giá</router-link></li>
        <li><router-link to="/client/products">Bạn muốn tìm gì</router-link></li>
        <li><router-link to="/client/products">Đơn mua của tôi</router-link></li>
        <li><router-link to="/client/orders">Tra cứu đơn hàng</router-link></li>
        <li><router-link to="/client/shopping-cart"><i class="bi bi-cart-fill"></i> Giỏ hàng</router-link></li>
        <!-- Tên người dùng với dropdown -->
        <li class="username" @click="toggleLogout" v-if="isLoggedIn">
          <span class="user-info"><i class="bi bi-person-circle"></i> {{ user?.fullName || 'Người dùng' }}</span>
          <ul v-if="showLogout" class="logout-dropdown">
            <li>
              <a href="#" @click.prevent="handleLogout" class="logout-link">
                <i class="bi bi-box-arrow-right"></i> Đăng xuất
              </a>
            </li>
          </ul>
        </li>
        <li v-if="!isLoggedIn">
          <router-link to="/login">Đăng nhập</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>

<style scoped>
.client-header {
  background-color: #2c3e50;
  color: white;
  padding: 10px 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

.client-header .logo a {
  color: white;
  font-size: 24px;
  text-decoration: none;
  font-weight: bold;
  transition: color 0.3s ease;
}

.client-header .logo a:hover {
  color: #f1c40f;
}

nav ul {
  list-style-type: none;
  padding: 0;
  display: flex;
  align-items: center;
}

nav ul li {
  margin-right: 25px;
  position: relative;
}

nav ul li a {
  color: white;
  text-decoration: none;
  font-size: 16px;
  padding: 5px 12px;
  transition: color 0.3s ease, background-color 0.3s ease;
  display: flex;
  align-items: center;
}

nav ul li a i {
  margin-right: 6px;
}

nav ul li a:hover {
  color: #f1c40f;
  background-color: rgba(255, 255, 255, 0.1);
  border-radius: 4px;
}

.username {
  cursor: pointer;
  padding: 8px 12px;
  border-radius: 20px;
  background-color: rgba(255, 255, 255, 0.05);
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.username:hover {
  background-color: rgba(255, 255, 255, 0.15);
  transform: translateY(-2px);
}

.user-info {
  display: inline-block;
}

.logout-dropdown {
  position: absolute;
  top: 100%;
  left: 0;
  background: linear-gradient(135deg, #34495e, #2c3e50);
  list-style: none;
  padding: 8px 0;
  margin: 0;
  min-width: 150px;
  border-radius: 8px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
  animation: slideDown 0.2s ease-out;
  z-index: 1000;
}

.logout-dropdown li {
  padding: 5px 15px;
}

.logout-link {
  color: white;
  text-decoration: none;
  display: flex;
  align-items: center;
  padding: 8px 15px;
  border-radius: 4px;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.logout-link i {
  margin-right: 8px;
}

.logout-link:hover {
  background-color: #f1c40f;
  color: #2c3e50;
}

@keyframes slideDown {
  from { opacity: 0; transform: translateY(-10px); }
  to { opacity: 1; transform: translateY(0); }
}
</style>