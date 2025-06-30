<template>
  <div class="row">
    <div class="col-md-3"><h4>Tài khoản</h4><div class="list-group"><router-link to="/profile" class="list-group-item list-group-item-action">Thông tin cá nhân</router-link><router-link to="/order-history" class="list-group-item list-group-item-action active">Lịch sử đơn hàng</router-link></div></div>
    <div class="col-md-9">
        <h4>Lịch sử đơn hàng</h4>
        <div class="card"><div class="card-body">
            <table class="table table-hover">
                <thead><tr><th>Mã ĐH</th><th>Ngày đặt</th><th>Tổng tiền</th><th>Trạng thái</th><th></th></tr></thead>
                <tbody><tr><td>#1052</td><td>28/06/2024</td><td>830.000đ</td><td><span class="badge bg-info">Đang giao</span></td><td><a href="#">Xem chi tiết</a></td></tr></tbody>
            </table>
        </div></div>
    </div>
  </div>
</template>