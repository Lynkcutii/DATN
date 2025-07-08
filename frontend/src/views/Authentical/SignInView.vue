<script setup>
import { ref, watch } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

const tai_khoan = ref('')
const mat_khau = ref('')
const error = ref('')
const isLoading = ref(false)
const router = useRouter()
const store = useStore()

const handleLogin = async () => {
  if (!tai_khoan.value.trim() || !mat_khau.value.trim()) {
    error.value = 'Vui lòng nhập tài khoản và mật khẩu'
    return
  }
  try {
    isLoading.value = true
    await store.dispatch('login', {
      tai_khoan: tai_khoan.value,
      mat_khau: mat_khau.value
    })
    router.push('/admin/dashboard')
  } catch (err) {
    error.value = err.message || 'Đăng nhập thất bại'
  } finally {
    isLoading.value = false
  }
}

watch([tai_khoan, mat_khau], () => {
  error.value = ''
})
</script>

<template>
  <div class="login-page">
    <div class="login-container">
      <div class="login-header">
        <h2>Đăng nhập</h2>
        <p class="subtitle">Chào mừng bạn quay trở lại</p>
      </div>

      <form @submit.prevent="handleLogin" class="login-form">
        <div class="form-group">
          <label for="tai_khoan">Tài khoản</label>
          <input 
            id="tai_khoan"
            v-model="tai_khoan" 
            type="text" 
            placeholder="Nhập tài khoản của bạn" 
            required 
            class="form-input"
          />
        </div>

        <div class="form-group">
          <label for="mat_khau">Mật khẩu</label>
          <input 
            id="mat_khau"
            v-model="mat_khau" 
            type="password" 
            placeholder="Nhập mật khẩu" 
            autocomplete="current-password" 
            required 
            class="form-input"
          />
        </div>

        <button type="submit" :disabled="isLoading" class="login-button">
          <span v-if="isLoading" class="loading-spinner"></span>
          {{ isLoading ? 'Đang xử lý...' : 'Đăng nhập' }}
        </button>

        <div v-if="error" class="error-message">
          <svg class="error-icon" viewBox="0 0 20 20" fill="currentColor">
            <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7 4a1 1 0 11-2 0 1 1 0 012 0zm-1-9a1 1 0 00-1 1v4a1 1 0 102 0V6a1 1 0 00-1-1z" clip-rule="evenodd" />
          </svg>
          {{ error }}
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
:root {
  --primary-brown: #8B4513;
  --light-brown: #D2B48C;
  --dark-brown: #654321;
  --cream: #F5F5DC;
  --white: #FFFFFF;
  --gray-50: #F9FAFB;
  --gray-100: #F3F4F6;
  --gray-200: #E5E7EB;
  --gray-300: #D1D5DB;
  --gray-400: #9CA3AF;
  --gray-600: #4B5563;
  --gray-700: #374151;
  --error-red: #DC2626;
  --error-bg: #FEF2F2;
  --error-border: #FECACA;
  --border-radius: 8px;
  --shadow-lg: 0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1);
  --transition: all 0.15s ease-in-out;
}

.login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, var(--cream) 0%, var(--gray-50) 100%);
  padding: 1rem;
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.login-container {
  width: 100%;
  max-width: 400px;
  background: var(--white);
  border-radius: 12px;
  box-shadow: var(--shadow-lg);
  border: 1px solid var(--gray-200);
  overflow: hidden;
}

.login-header {
  padding: 2rem 2rem 1.5rem;
  text-align: center;
  background: var(--white);
  border-bottom: 1px solid var(--gray-100);
}

.login-header h2 {
  margin: 0 0 0.5rem;
  font-size: 1.875rem;
  font-weight: 700;
  color: var(--primary-brown);
  letter-spacing: -0.025em;
}

.subtitle {
  margin: 0;
  font-size: 0.875rem;
  color: var(--gray-600);
  font-weight: 400;
}

.login-form {
  padding: 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-size: 0.875rem;
  font-weight: 500;
  color: var(--gray-700);
}

.form-input {
  width: 100%;
  padding: 0.75rem 1rem;
  border: 1px solid var(--gray-300);
  border-radius: var(--border-radius);
  font-size: 1rem;
  color: var(--gray-700);
  background: var(--white);
  transition: var(--transition);
  box-sizing: border-box;
}

.form-input::placeholder {
  color: var(--gray-400);
}

.form-input:focus {
  outline: none;
  border-color: var(--primary-brown);
  box-shadow: 0 0 0 3px rgba(139, 69, 19, 0.1);
}

.form-input:hover {
  border-color: var(--gray-400);
}

.login-button {
  width: 100%;
  padding: 0.75rem 1rem;
  background: var(--primary-brown);
  color: var(--white);
  border: none;
  border-radius: var(--border-radius);
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: var(--transition);
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  margin-bottom: 1rem;
}

.login-button:hover:not(:disabled) {
  background: var(--dark-brown);
  transform: translateY(-1px);
}

.login-button:active:not(:disabled) {
  transform: translateY(0);
}

.login-button:disabled {
  background: var(--gray-400);
  cursor: not-allowed;
  transform: none;
}

.loading-spinner {
  width: 1rem;
  height: 1rem;
  border: 2px solid transparent;
  border-top: 2px solid currentColor;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}

.error-message {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1rem;
  background: var(--error-bg);
  border: 1px solid var(--error-border);
  border-radius: var(--border-radius);
  color: var(--error-red);
  font-size: 0.875rem;
  margin-bottom: 1rem;
}

.error-icon {
  width: 1.25rem;
  height: 1.25rem;
  flex-shrink: 0;
}

@media (max-width: 480px) {
  .login-page {
    padding: 0.5rem;
  }
  
  .login-container {
    max-width: 100%;
  }
  
  .login-header {
    padding: 1.5rem 1.5rem 1rem;
  }
  
  .login-header h2 {
    font-size: 1.5rem;
  }
  
  .login-form {
    padding: 1.5rem;
  }
  
  .form-group {
    margin-bottom: 1.25rem;
  }
}

.form-input:focus,
.login-button:focus {
  outline: 2px solid var(--primary-brown);
  outline-offset: 2px;
}
</style>