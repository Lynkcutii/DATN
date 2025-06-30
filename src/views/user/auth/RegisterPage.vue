<template>
  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-4">
      <div class="card shadow-sm">
        <div class="card-body p-4">
          <h2 class="card-title text-center mb-4">Đăng ký tài khoản</h2>
          <form>
            <div class="mb-3"><label class="form-label">Họ và tên</label><input type="text" class="form-control"></div>
            <div class="mb-3"><label class="form-label">Email</label><input type="email" class="form-control"></div>
            <div class="mb-3"><label class="form-label">Mật khẩu</label><input type="password" class="form-control"></div>
            <div class="mb-3"><label class="form-label">Nhập lại mật khẩu</label><input type="password" class="form-control"></div>
            <button type="submit" class="btn btn-primary w-100">Đăng ký</button>
          </form>
          <div class="text-center mt-3">
            <small>Đã có tài khoản? <router-link to="/login">Đăng nhập</router-link></small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>