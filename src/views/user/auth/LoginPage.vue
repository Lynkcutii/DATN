<template>
  <div class="auth-page-wrapper" style="background-image: url('https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?q=80&w=2070');">
    <div class="auth-form-container">
      <div class="card">
        <div class="card-body p-4 p-lg-5">
          <div class="text-center mb-4">
            <img src="/img/logo/logo.jpeg" alt="Nexvibe Logo" height="50" class="mb-3">
            <h2 class="card-title fw-bold">Chào mừng trở lại!</h2>
            <p class="text-muted">Đăng nhập để tiếp tục</p>
          </div>
          <form @submit.prevent="handleLogin">
            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input type="email" class="form-control" id="email" placeholder="Nhập email của bạn">
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">Mật khẩu</label>
              <input type="password" class="form-control" id="password" placeholder="Nhập mật khẩu">
            </div>
            <div class="d-flex justify-content-between align-items-center mb-4">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="rememberMe">
                <label class="form-check-label" for="rememberMe">Ghi nhớ tôi</label>
              </div>
              <a href="#" class="small">Quên mật khẩu?</a>
            </div>
            <button type="submit" class="btn btn-primary w-100 btn-lg">Đăng nhập</button>
          </form>
          <div class="text-center mt-4">
            <small class="text-muted">Chưa có tài khoản? <router-link to="/register">Đăng ký ngay</router-link></small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';
import { useToast } from 'vue-toastification';

const auth = useAuthStore();
const router = useRouter();
const toast = useToast();

const handleLogin = () => {
    // Giả lập đăng nhập thành công
    const userData = { id: 1, name: 'Trần Minh Tuấn', email: 'tuan.tm@nexvibe.com' };
    const token = 'fake-jwt-token-string';
    auth.login(userData, token);
    toast.success('Đăng nhập thành công!');
    router.push({ name: 'home' });
};
</script>