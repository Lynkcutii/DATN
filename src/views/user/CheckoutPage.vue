<template>
  <div>
    <h1 class="mb-4">Thanh toán</h1>
    <div class="row">
      <div class="col-md-7">
        <h4>Thông tin giao hàng</h4>
        <form>
          <div class="mb-3"><input type="text" class="form-control" placeholder="Họ và tên"></div>
          <div class="row"><div class="col-md-7 mb-3"><input type="email" class="form-control" placeholder="Email"></div><div class="col-md-5 mb-3"><input type="text" class="form-control" placeholder="Số điện thoại"></div></div>
          <div class="mb-3"><input type="text" class="form-control" placeholder="Địa chỉ"></div>
          <div class="mb-3"><textarea class="form-control" placeholder="Ghi chú (tùy chọn)"></textarea></div>
        </form>
      </div>
      <div class="col-md-5">
        <h4>Đơn hàng của bạn</h4>
        <div class="card">
            <div class="card-body">
                <div v-for="item in cart.items" :key="item.uniqueId" class="d-flex justify-content-between mb-2">
                    <span>{{ item.name }} (x{{ item.quantity }})</span>
                    <strong>{{ (item.price * item.quantity).toLocaleString() }}đ</strong>
                </div>
                <hr>
                <div class="d-flex justify-content-between"><span>Tạm tính</span> <span>{{ cart.subTotal.toLocaleString() }}đ</span></div>
                <div class="d-flex justify-content-between"><span>Phí vận chuyển</span> <span>Miễn phí</span></div>
                <hr>
                <div class="d-flex justify-content-between fw-bold h5"><span>Tổng cộng</span> <span>{{ cart.subTotal.toLocaleString() }}đ</span></div>
            </div>
        </div>
        <button class="btn btn-primary w-100 mt-3 btn-lg">Hoàn tất Đơn hàng</button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { useCartStore } from '@/stores/cart';
const cart = useCartStore();
</script>