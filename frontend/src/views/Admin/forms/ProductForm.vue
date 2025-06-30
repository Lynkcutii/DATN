<template>
  <h1 class="h3 mb-4 text-gray-800">{{ isEditing ? 'Chỉnh sửa Sản phẩm' : 'Thêm Sản phẩm mới' }}</h1>
  
  <form @submit.prevent="saveProduct">
    <div class="row">
      <!-- Cột trái: Thông tin cơ bản -->
      <div class="col-lg-8">
        <div class="card shadow mb-4">
          <div class="card-header">Thông tin cơ bản</div>
          <div class="card-body">
            <div class="mb-3">
              <label for="productName" class="form-label">Tên sản phẩm</label>
              <input type="text" class="form-control" id="productName" v-model="product.name" required>
            </div>
            <div class="mb-3">
              <label for="productDescription" class="form-label">Mô tả</label>
              <textarea class="form-control" id="productDescription" rows="5" v-model="product.description"></textarea>
            </div>
          </div>
        </div>
      </div>
      
      <!-- Cột phải: Giá, tồn kho, danh mục... -->
      <div class="col-lg-4">
        <div class="card shadow mb-4">
          <div class="card-header">Thông tin bán hàng</div>
          <div class="card-body">
            <div class="mb-3">
              <label for="productPrice" class="form-label">Giá bán (VND)</label>
              <input type="number" class="form-control" id="productPrice" v-model.number="product.price">
            </div>
             <div class="mb-3">
              <label for="productStock" class="form-label">Tồn kho</label>
              <input type="number" class="form-control" id="productStock" v-model.number="product.stock">
            </div>
            <div class="mb-3">
              <label for="productCategory" class="form-label">Danh mục</label>
              <select class="form-select" id="productCategory" v-model="product.categoryId">
                <option value="1">Áo thun</option>
                <option value="2">Áo Hoodie</option>
                <option value="3">Quần Dài</option>
              </select>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div class="d-flex justify-content-end gap-2">
      <router-link :to="{ name: 'admin.products.list' }" class="btn btn-secondary">Hủy</router-link>
      <button type="submit" class="btn btn-primary">Lưu sản phẩm</button>
    </div>
  </form>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();

// Dùng computed property để kiểm tra xem đây là trang Sửa hay Thêm
const isEditing = computed(() => !!route.params.id);
const productId = ref(route.params.id);

// Dữ liệu mẫu cho sản phẩm
const product = ref({
  name: '',
  description: '',
  price: 0,
  stock: 0,
  categoryId: 1
});

onMounted(() => {
  if (isEditing.value) {
    // Nếu là trang Sửa, ta sẽ gọi API để lấy thông tin sản phẩm theo ID
    console.log(`Lấy dữ liệu cho sản phẩm có ID: ${productId.value}`);
    // Giả lập dữ liệu đã lấy về
    product.value = {
      name: 'Áo Thun Thể Thao Co Dãn 4 Chiều',
      description: 'Mô tả chi tiết cho áo thun...',
      price: 250000,
      stock: 150,
      categoryId: 1
    };
  }
});

const saveProduct = () => {
  if (isEditing.value) {
    // Gọi API để CẬP NHẬT sản phẩm
    console.log('Đang cập nhật sản phẩm:', product.value);
  } else {
    // Gọi API để TẠO MỚI sản phẩm
    console.log('Đang tạo mới sản phẩm:', product.value);
  }
  // Sau khi lưu thành công, quay về trang danh sách
  router.push({ name: 'admin.products.list' });
};
</script>