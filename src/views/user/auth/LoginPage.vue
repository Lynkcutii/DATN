<template>
  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-4">
      <div class="card shadow-sm">
        <div class="card-body p-4">
          <h2 class="card-title text-center mb-4">Đăng nhập</h2>
          <form @submit.prevent="handleLogin">
            <div class="mb-3"><label class="form-label">Email</label><input type="email" class="form-control"></div>
            <div class="mb-3"><label class="form-label">Mật khẩu</label><input type="password" class="form-control"></div>
            <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
          </form>
          <div class="text-center mt-3">
            <small>Chưa có tài khoản? <router-link to="/register">Đăng ký ngay</router-link></small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';
const auth = useAuthStore();
const router = useRouter();

const handleLogin = () => {
    // Giả lập đăng nhập thành công
    const userData = { id: 1, name: 'Trần Minh Tuấn', email: 'tuan.tm@nexvibe.com' };
    const token = 'fake-jwt-token-string';
    auth.login(userData, token);
    router.push({ name: 'home' });
};
</script>