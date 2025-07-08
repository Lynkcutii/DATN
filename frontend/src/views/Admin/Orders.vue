<template>
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h1 class="h3 mb-0 text-gray-800">Quản lý Đơn hàng</h1>
    <!-- Thông thường trang đơn hàng không có nút "Thêm mới" vì đơn hàng được tạo từ phía khách hàng hoặc POS -->
  </div>
  <div class="card">
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-hover align-middle">
          <thead>
            <tr>
              <th>Mã ĐH</th>
              <th>Khách hàng</th>
              <th>Ngày đặt</th>
              <th>Tổng tiền</th>
              <th>Thanh toán</th>
              <th>Giao hàng</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <!-- Dữ liệu mẫu - Sau này bạn sẽ dùng v-for để lặp qua một mảng dữ liệu -->
            <tr>
              <th>#1052</th>
              <td>Nguyễn Văn A</td>
              <td>28/06/2024</td>
              <td>800.000đ</td>
              <td><span class="badge bg-success">Đã trả</span></td>
              <td><span class="badge bg-info">Đang giao</span></td>
              <td>
                <!-- Sửa thẻ <a> thành <router-link> -->
                <router-link :to="{ name: 'admin.orders.detail', params: { id: 1052 } }" class="btn btn-link text-primary p-1" title="Xem chi tiết">
                  <i class="fas fa-eye"></i>
                </router-link>
              </td>
            </tr>
            <tr>
              <th>#1051</th>
              <td>Trần Thị B</td>
              <td>27/06/2024</td>
              <td>320.000đ</td>
              <td><span class="badge bg-warning">Chưa trả</span></td>
              <td><span class="badge bg-secondary">Chờ xử lý</span></td>
              <td>
                <!-- Sửa thẻ <a> thành <router-link> -->
                <router-link :to="{ name: 'admin.orders.detail', params: { id: 1051 } }" class="btn btn-link text-primary p-1" title="Xem chi tiết">
                  <i class="fas fa-eye"></i>
                </router-link>
              </td>
            </tr>
             <tr>
              <th>#1050</th>
              <td>Lê Văn C</td>
              <td>26/06/2024</td>
              <td>250.000đ</td>
              <td><span class="badge bg-success">Đã trả</span></td>
              <td><span class="badge bg-primary">Hoàn thành</span></td>
              <td>
                <!-- Sửa thẻ <a> thành <router-link> -->
                <router-link :to="{ name: 'admin.orders.detail', params: { id: 1050 } }" class="btn btn-link text-primary p-1" title="Xem chi tiết">
                  <i class="fas fa-eye"></i>
                </router-link>
              </td>
            </tr>
             <tr>
              <th>#1049</th>
              <td>Phạm Thị D</td>
              <td>25/06/2024</td>
              <td>550.000đ</td>
              <td><span class="badge bg-danger">Thất bại</span></td>
              <td><span class="badge bg-danger">Đã hủy</span></td>
              <td>
                <!-- Sửa thẻ <a> thành <router-link> -->
                <router-link :to="{ name: 'admin.orders.detail', params: { id: 1049 } }" class="btn btn-link text-primary p-1" title="Xem chi tiết">
                  <i class="fas fa-eye"></i>
                </router-link>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup>
// Hiện tại trang này chỉ để hiển thị danh sách, chưa cần logic phức tạp.
// Sau này bạn có thể thêm logic để lấy danh sách đơn hàng từ API tại đây.
</script>