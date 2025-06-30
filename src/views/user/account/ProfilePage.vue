<template>
  <div class="row">
    <div class="col-md-3">
        <h4>Tài khoản</h4>
        <div class="list-group">
            <router-link to="/profile" class="list-group-item list-group-item-action active">Thông tin cá nhân</router-link>
            <router-link to="/order-history" class="list-group-item list-group-item-action">Lịch sử đơn hàng</router-link>
        </div>
    </div>
    <div class="col-md-9">
        <h4>Thông tin cá nhân</h4>
        <div class="card"><div class="card-body">
            <form>
                <div class="mb-3"><label class="form-label">Họ tên</label><input type="text" class="form-control" :value="auth.user?.name"></div>
                <div class="mb-3"><label class="form-label">Email</label><input type="email" class="form-control" :value="auth.user?.email" disabled></div>
                <button class="btn btn-primary">Lưu thay đổi</button>
            </form>
            <hr>
            <h5>Đổi mật khẩu</h5>
            <form>
                 <div class="mb-3"><label class="form-label">Mật khẩu hiện tại</label><input type="password" class="form-control"></div>
                 <div class="mb-3"><label class="form-label">Mật khẩu mới</label><input type="password" class="form-control"></div>
                 <div class="mb-3"><label class="form-label">Nhập lại mật khẩu mới</label><input type="password" class="form-control"></div>
                 <button class="btn btn-dark">Đổi mật khẩu</button>
            </form>
        </div></div>
    </div>
  </div>
</template>
<script setup>
import { useAuthStore } from '@/stores/auth';
const auth = useAuthStore();
</script>