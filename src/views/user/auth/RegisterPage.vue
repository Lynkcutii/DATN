<template>
  <div class="auth-page-wrapper" style="background-image: url('https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?q=80&w=2070');">
    <div class="auth-form-container">
      <div class="card">
        <div class="card-body p-4 p-lg-5">
          <div class="text-center mb-4">
             <img src="/img/logo/logo.jpeg" alt="Nexvibe Logo" height="50" class="mb-3">
            <h2 class="card-title fw-bold">Tạo tài khoản mới</h2>
            <p class="text-muted">Bắt đầu hành trình cùng NEXVIBE</p>
          </div>
          <form>
            <div class="mb-3"><label class="form-label">Họ và tên</label><input type="text" class="form-control" placeholder="Nhập họ và tên"></div>
            <div class="mb-3"><label class="form-label">Email</label><input type="email" class="form-control" placeholder="Nhập email của bạn"></div>
            <div class="mb-3"><label class="form-label">Mật khẩu</label><input type="password" class="form-control" placeholder="Ít nhất 6 ký tự"></div>
            <div class="mb-3"><label class="form-label">Nhập lại mật khẩu</label><input type="password" class="form-control"></div>
            <button type="submit" class="btn btn-primary w-100 btn-lg">Đăng ký</button>
          </form>
          <div class="text-center mt-4">
            <small class="text-muted">Đã có tài khoản? <router-link to="/login">Đăng nhập</router-link></small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>