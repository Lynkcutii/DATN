<template>
  <div v-if="product">
    <div class="row">
      <!-- Cột trái: Thư viện ảnh sản phẩm -->
      <div class="col-lg-6">
        <img :src="activeImageUrl" class="img-fluid rounded shadow-sm main-product-image mb-3">
        <div class="d-flex">
          <img v-for="image in product.gallery" :key="image.id" 
               :src="image.url" 
               @click="activeImageUrl = image.url"
               class="img-fluid rounded me-2 thumbnail"
               :class="{ 'active-thumbnail': activeImageUrl === image.url }">
        </div>
      </div>
      
      <!-- Cột phải: Thông tin và lựa chọn -->
      <div class="col-lg-6 ps-lg-5 mt-4 mt-lg-0">
        <h1 class="fw-bold display-5">{{ product.name }}</h1>
        <div class="d-flex align-items-center mb-3">
            <div class="text-warning me-2">
                <i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i>
            </div>
            <span class="text-muted">(125 đánh giá)</span>
        </div>
        <p class="h3 fw-bolder text-danger mb-4">{{ product.price.toLocaleString() }}đ</p>
        
        <p class="text-muted">{{ product.short_description }}</p>
        <hr>
        
        <!-- Lựa chọn Màu sắc -->
        <div class="mb-3">
          <label class="form-label fw-bold">Màu sắc: <span class="text-dark">{{ selectedVariant.color }}</span></label>
          <div>
            <button v-for="color in product.colors" :key="color" 
                    @click="selectColor(color)" 
                    class="btn me-2 mb-2 color-option" 
                    :class="{ 'active': selectedVariant.color === color }"
                    :style="{ backgroundColor: color.hex }" 
                    :title="color.name"></button>
          </div>
        </div>
        
        <!-- Lựa chọn Size -->
        <div class="mb-4">
          <label class="form-label fw-bold">Size:</label>
          <div>
            <button v-for="size in product.sizes" :key="size" 
                    @click="selectedVariant.size = size" 
                    class="btn btn-outline-dark me-2 mb-2" 
                    :class="{ 'active': selectedVariant.size === size }">{{ size }}</button>
          </div>
        </div>
        
        <!-- Thêm vào giỏ hàng -->
        <div class="d-flex align-items-center">
          <input type="number" class="form-control me-3" style="width: 80px;" v-model.number="quantity" min="1">
          <button @click="handleAddToCart" class="btn btn-primary btn-lg flex-grow-1">
            <i class="fas fa-cart-plus me-2"></i>Thêm vào giỏ
          </button>
        </div>
      </div>
    </div>

    <!-- Tab Mô tả chi tiết & Đánh giá -->
    <div class="row mt-5">
      <div class="col-12">
        <div class="card">
          <div class="card-header">
            <ul class="nav nav-tabs card-header-tabs">
              <li class="nav-item"><a class="nav-link active" data-bs-toggle="tab" href="#description">Mô tả chi tiết</a></li>
              <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#reviews">Đánh giá (125)</a></li>
            </ul>
          </div>
          <div class="card-body tab-content">
            <div class="tab-pane fade show active p-3" id="description">
              <p>Dòng sản phẩm NEXVIBE Pro được thiết kế với chất liệu cao cấp, co dãn 4 chiều, mang lại sự thoải mái tối đa cho mọi hoạt động. Công nghệ dệt kim tiên tiến giúp thoáng khí, thấm hút mồ hôi hiệu quả, giữ cho bạn luôn khô ráo và tự tin trong suốt buổi tập.</p>
              <ul>
                <li>Chất liệu: 95% Polyester, 5% Spandex</li>
                <li>Công nghệ: Dri-FIT Max</li>
                <li>Xuất xứ: Việt Nam</li>
              </ul>
            </div>
            <div class="tab-pane fade p-3" id="reviews">
              <!-- (Nội dung phần đánh giá sẽ được thêm vào đây) -->
              <p>Phần đánh giá của khách hàng.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div v-else class="text-center p-5">
      <h2>Đang tải thông tin sản phẩm...</h2>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { useCartStore } from '@/stores/cart';
const cart = useCartStore();
const props = defineProps(['slug']);

// Dữ liệu mẫu, sau này sẽ được lấy từ API dựa vào props.slug
const product = ref(null);
const activeImageUrl = ref('');

const quantity = ref(1);
const selectedVariant = reactive({ color: null, size: null });

const selectColor = (color) => {
    selectedVariant.color = color.name;
    // Tự động chuyển ảnh chính khi người dùng chọn màu
    const colorImage = product.value.gallery.find(img => img.color === color.name);
    if (colorImage) {
        activeImageUrl.value = colorImage.url;
    }
}

const handleAddToCart = () => {
    if (!selectedVariant.color || !selectedVariant.size) {
        alert('Vui lòng chọn đầy đủ màu sắc và size!');
        return;
    }
    cart.addToCart(product.value, quantity.value, selectedVariant);
    alert(`Đã thêm ${quantity.value} "${product.value.name} - ${selectedVariant.color} - ${selectedVariant.size}" vào giỏ!`);
}

onMounted(() => {
    // Giả lập việc gọi API để lấy dữ liệu sản phẩm
    console.log(`Fetching product with slug: ${props.slug}`);
    setTimeout(() => {
        const sampleProduct = {
            id: 1, name: 'Áo Thun Năng Động', price: 350000, slug: 'ao-thun-nang-dong',
            short_description: 'Dòng sản phẩm NEXVIBE Pro được thiết kế với chất liệu cao cấp, co dãn 4 chiều, mang lại sự thoải mái tối đa cho mọi hoạt động.',
            gallery: [
                { id: 1, url: 'https://picsum.photos/600/600?random=10', color: 'Đen' },
                { id: 2, url: 'https://picsum.photos/600/600?random=11', color: 'Trắng' },
                { id: 3, url: 'https://picsum.photos/600/600?random=12', color: 'Xanh' },
                { id: 4, url: 'https://picsum.photos/600/600?random=13', color: null }, // Ảnh chung
            ],
            colors: [{name: 'Đen', hex: '#000000'}, {name: 'Trắng', hex: '#FFFFFF'}, {name: 'Xanh', hex: '#0d6efd'}], 
            sizes: ['S', 'M', 'L', 'XL']
        };
        product.value = sampleProduct;
        activeImageUrl.value = sampleProduct.gallery[0].url; // Set ảnh chính ban đầu
        // Tự động chọn màu và size đầu tiên
        selectedVariant.color = sampleProduct.colors[0].name;
        selectedVariant.size = sampleProduct.sizes[0];
    }, 500);
});
</script>

<style scoped>
.main-product-image {
    aspect-ratio: 1 / 1;
    object-fit: cover;
}
.thumbnail {
    width: 80px;
    height: 80px;
    object-fit: cover;
    cursor: pointer;
    border: 2px solid transparent;
    transition: border-color 0.2s;
}
.thumbnail:hover, .active-thumbnail {
    border-color: #0d6efd;
}
.color-option {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    border: 2px solid #ddd;
    transition: transform 0.2s;
}
.color-option.active {
    transform: scale(1.2);
    box-shadow: 0 0 0 2px white, 0 0 0 4px #0d6efd;
}
</style>