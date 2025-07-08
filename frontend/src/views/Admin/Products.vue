<template>
  <!-- Header -->
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h1 class="h3 mb-0 text-gray-800">Danh Sách Sản Phẩm</h1>
    <!-- Nút "Thêm Sản Phẩm" giờ sẽ điều hướng đến trang tạo mới -->
    <router-link :to="{ name: 'admin.products.create' }" class="btn btn-primary d-flex align-items-center gap-2">
      <i class="fas fa-plus"></i> Thêm Sản Phẩm
    </router-link>
  </div>
  
  <!-- Filter Card -->
  <div class="card mb-4">
    <div class="card-body">
      <h5 class="card-title mb-3">Bộ lọc</h5>
      <!-- (Nội dung bộ lọc có thể thêm vào đây sau) -->
    </div>
  </div>

  <!-- Products Table Card -->
  <div class="card">
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-hover align-middle">
          <thead>
            <tr>
              <th>SKU</th>
              <th>Hình Ảnh</th>
              <th>Tên Sản Phẩm</th>
              <th>Giá Bán</th>
              <th>Tồn Kho</th>
              <th>Trạng Thái</th>
              <th>Hành Động</th>
            </tr>
          </thead>
          <tbody>
            <!-- Dữ liệu mẫu, sau này bạn sẽ dùng v-for để lặp qua danh sách sản phẩm từ API -->
            <tr>
              <th>NV-T001</th>
              <td><img src="https://i.imgur.com/3pWjC0a.png" class="product-thumbnail"></td>
              <td>Áo Thun Thể Thao Co Dãn 4 Chiều</td>
              <td>250.000</td>
              <td>150</td>
              <td><span class="badge rounded-pill text-bg-success">Còn hàng</span></td>
              <td>
                <!-- Nút Sửa, điều hướng đến trang chỉnh sửa với id sản phẩm -->
                <router-link :to="{ name: 'admin.products.edit', params: { id: 1 } }" class="btn btn-link text-primary p-1" title="Sửa">
                  <i class="fas fa-edit"></i>
                </router-link>
                <!-- Nút Xóa, sẽ trigger mở popup xác nhận -->
                <button @click="openDeleteModal(1, 'Áo Thun Thể Thao Co Dãn 4 Chiều')" class="btn btn-link text-danger p-1" title="Xóa">
                  <i class="fas fa-trash-alt"></i>
                </button>
              </td>
            </tr>
            <!-- Thêm một sản phẩm nữa để ví dụ -->
             <tr>
              <th>NV-H002</th>
              <td><img src="https://i.imgur.com/fG0m92F.png" class="product-thumbnail"></td>
              <td>Áo Hoodie Nỉ Bông Nexvibe Logo</td>
              <td>550.000</td>
              <td>8</td>
              <td><span class="badge rounded-pill text-bg-warning">Sắp hết</span></td>
              <td>
                <router-link :to="{ name: 'admin.products.edit', params: { id: 2 } }" class="btn btn-link text-primary p-1" title="Sửa">
                  <i class="fas fa-edit"></i>
                </router-link>
                <button @click="openDeleteModal(2, 'Áo Hoodie Nỉ Bông Nexvibe Logo')" class="btn btn-link text-danger p-1" title="Xóa">
                  <i class="fas fa-trash-alt"></i>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <!-- Modal xác nhận xóa -->
  <div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="deleteModalLabel">Xác nhận Xóa</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          Bạn có chắc chắn muốn xóa sản phẩm "<strong>{{ productNameToDelete }}</strong>"?<br>
          Hành động này không thể hoàn tác.
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-danger" @click="confirmDelete">Xóa</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { Modal } from 'bootstrap';

// Biến để lưu trữ đối tượng Modal của Bootstrap
let deleteModalInstance = null;
// Biến để lưu trữ ID và tên của sản phẩm sắp bị xóa
const productIdToDelete = ref(null);
const productNameToDelete = ref('');

// Hàm này được gọi khi người dùng nhấn nút xóa
const openDeleteModal = (productId, productName) => {
  productIdToDelete.value = productId;
  productNameToDelete.value = productName;
  if (deleteModalInstance) {
    deleteModalInstance.show();
  }
};

// Hàm này được gọi khi người dùng nhấn nút "Xóa" trong modal
const confirmDelete = () => {
  console.log(`Đang thực hiện xóa sản phẩm có ID: ${productIdToDelete.value}`);
  
  // NƠI BẠN SẼ GỌI API ĐỂ XÓA DỮ LIỆU TRÊN SERVER
  // Ví dụ:
  // await api.delete(`/products/${productIdToDelete.value}`);
  
  // Sau khi API chạy xong, ẩn modal đi
  if (deleteModalInstance) {
    deleteModalInstance.hide();
  }
  
  // Và có thể tải lại danh sách sản phẩm hoặc xóa item khỏi mảng trên UI
  // alert(`Đã xóa sản phẩm: ${productNameToDelete.value}`);
};

// Khởi tạo đối tượng Modal khi component được gắn vào DOM
onMounted(() => {
  const modalElement = document.getElementById('deleteConfirmModal');
  if (modalElement) {
    deleteModalInstance = new Modal(modalElement);
  }
});

// Hủy đối tượng Modal khi component bị gỡ khỏi DOM để tránh rò rỉ bộ nhớ
onUnmounted(() => {
    if (deleteModalInstance) {
        deleteModalInstance.dispose();
    }
})
</script>