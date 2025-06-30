<script setup>
// import { getAllSanPham } from "@/Service/ClientService/SanPhamServies";
import { ref, onMounted, computed } from "vue";
import { ElMessageBox } from 'element-plus';
import { useRouter } from "vue-router";
import { useStore } from 'vuex'; // Sử dụng Vuex nếu bạn có

const store = useStore();
const router = useRouter();
const products = ref([]);
const currentPage = ref(0);
const totalPages = ref(0);

const isLoggedIn = computed(() => store.getters.isAuthenticated || !!localStorage.getItem("isLoggedIn"));

const fetchProducts = async (page, size = 5) => {
  try {
    const data = await getAllSanPham(page, size);
    products.value = data.content || [];
    totalPages.value = data.totalPages || 1;
    console.log("Total Pages:", totalPages.value);
  } catch (error) {
    console.error("Lỗi khi tải sản phẩm:", error);
  }
};

const formatPrice = (price) => {
  return new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  }).format(price || 0);
};

const changePage = (page) => {
  if (page >= 0 && page < totalPages.value) {
    currentPage.value = page;
    fetchProducts(page);
  }
};

// Hàm điều hướng đến trang chi tiết sản phẩm
const goToProductDetail = (productId) => {
  router.push(`/product-detail/${productId}`);
};



const handlDangNhap = async (productId) => {
  if (!isLoggedIn.value) {
    try {
      await ElMessageBox.confirm(
        'Bạn cần đăng nhập để tiếp tục mua hàng. Bạn có muốn đăng nhập không?',
        'Xác nhận đăng nhập',
        {
          confirmButtonText: 'Đăng nhập',
          cancelButtonText: 'Hủy',
          type: 'warning',
        }
      );
      router.push('/login');
    } catch {
      // Hủy -> không làm gì
    }
  } else {
    
  }
};

onMounted(() => {
  fetchProducts(currentPage.value);
});
</script>

<template>
  <div class="product-list container py-4">
    <h2 class="text-center mb-3">
      SẢN PHẨM MỚI
      <hr class="hr-red" />
    </h2>

    <div v-if="products.length" class="row g-3">
      <div v-for="product in products" :key="product.id" class="col-6 col-md-4 col-lg-2_4 d-flex">
        <div class="product-card flex-fill d-flex flex-column">
          <!-- Ảnh sản phẩm có thể click để xem chi tiết -->
          <div class="image-placeholder" @click="goToProductDetail(product.id)" style="cursor: pointer;">
            <img
              :src="product.anhGiay"
              alt="Ảnh giày"
              class="img-thumbnail w-100"
              v-if="product.anhGiay"
            />
          </div>
          
          <!-- Tên sản phẩm có thể click để xem chi tiết -->
          <div class="brand mt-2">{{ product.thuongHieu }}</div>
          <h5 class="product-name mt-1 clickable" @click="goToProductDetail(product.id)">
            {{ product.tenGiay }}
          </h5>
          <h5 class="product-name mt-1">Loại màu: {{ product.tenMauSac }}</h5>
          <h5 class="product-name mt-1">Size: {{ product.tenKichThuoc }}</h5>
          
          <div class="price-group">
            <span class="discounted-price">Giá: {{ formatPrice(product.giaBan) }}</span>
          </div>
          
          <div class="labels mt-auto">
            <span class="label-sale">SIÊU SALE</span>
            <button class="label-new" @click="handleBuyNow(product.id)">Mua Ngay</button>
          </div>
        </div>
      </div>
    </div>

    <div v-else class="no-products text-center p-4 border border-dashed border-dark">
      Không có sản phẩm nào.
    </div>

    <div
      class="pagination d-flex justify-content-center align-items-center gap-2 mt-4"
      v-if="totalPages > 1"
    >
      <button
        @click="changePage(currentPage - 1)"
        :disabled="currentPage === 0"
        class="btn btn-outline-dark"
      >
        <i class="bi bi-chevron-left"></i> Trước
      </button>
      <span class="fw-bold">
        Trang {{ currentPage + 1 }} / {{ totalPages }}
      </span>
      <button
        @click="changePage(currentPage + 1)"
        :disabled="currentPage >= totalPages - 1"
        class="btn btn-outline-dark"
      >
        Sau <i class="bi bi-chevron-right"></i>
      </button>
    </div>
  </div>
</template>

<style scoped>
/* Giữ nguyên style như trước */
.product-list {
  padding: 20px;
}

.product-card {
  border: 1px solid black;
  padding: 10px;
  text-align: center;
  display: flex;
  flex-direction: column;
}

.image-placeholder {
  height: 150px;
  background-color: #f0f0f0;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: transform 0.2s ease;
}

.image-placeholder:hover {
  transform: scale(1.02);
}

.image-placeholder img {
  max-height: 100%;
  object-fit: contain;
}

.brand {
  font-size: 14px;
  color: #555;
}

.product-name {
  font-size: 16px;
  margin-bottom: 5px;
}

.clickable {
  cursor: pointer;
  transition: color 0.2s ease;
}

.clickable:hover {
  color: #dc3545;
  text-decoration: underline;
}

.price-group {
  margin: 5px 0;
}

.discounted-price {
  color: #dc3545;
  font-weight: bold;
}

.labels {
  margin-top: 5px;
}

.label-sale,
.label-new {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 5px;
  font-size: 12px;
  margin-right: 5px;
}

.label-sale {
  background-color: #ffcc00;
  color: #333;
}

.label-new {
  background-color: #28a745;
  color: white;
  border: none;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.label-new:hover {
  background-color: #218838;
}

.no-products {
  text-align: center;
  font-style: italic;
  color: gray;
  padding: 40px;
}

.pagination {
  margin-top: 30px;
  padding: 10px 0;
  text-align: center;
}

.hr-red {
  border: 0;
  height: 2px;
  background: #dc3545;
  margin: 10px auto;
  width: 50px;
}

@media (max-width: 768px) {
  .col-md-4 {
    flex: 0 0 50%;
    max-width: 50%;
  }
}

@media (min-width: 992px) {
  .col-lg-2_4 {
    flex: 0 0 auto;
    width: 20%;
  }
}

@media (max-width: 576px) {
  .col-md-4 {
    flex: 0 0 100%;
    max-width: 100%;
  }
}
</style>