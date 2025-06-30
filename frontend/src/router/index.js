import { createRouter, createWebHistory } from 'vue-router'
import AdminLayout from '../layouts/Adminlayout.vue'
import Dashboard from '../views/Admin/Dashboard.vue'
import DefaultLayout from '../components/Client/DefaultHeader.vue'
import Home from '../views/Client/TrangChu.vue'

// import Dashboard from '../views/Admin/Dashboard.vue';
import Products from '../views/Admin/Products.vue';
import Orders from '../views/Admin/Orders.vue';
import PointOfSale from '../views/Admin/PointOfSale.vue';
import Attributes from '../views/Admin/Attributes.vue';
import Accounts from '../views/Admin/Accounts.vue';
import Posts from '../views/Admin/Posts.vue';
import Reviews from '../views/Admin/Reviews.vue';
import ProductForm from '../views/Admin/forms/ProductForm.vue';
import AccountForm from '../views/Admin/forms/AccountForm.vue';
import PostForm from '../views/Admin/forms/PostForm.vue';
import OrderDetail from '../views/Admin/OrderDetail.vue';
// import Category from '@/views/Category.vue'
// import FilterPrice from '@/views/FilterPrice.vue'
// import SearchProduct from '@/views/SearchProduct.vue'

const routes = [
  {
    path: '/',
    component: DefaultLayout,
    children: [
      { path: '', name: 'home', component: Home, meta: { title: 'Trang chủ' } },
      // { path: 'category', name: 'category', component: Category, meta: { title: 'Danh mục' } },
      // { path: 'fiter-price', name: 'filter-price', component: FilterPrice, meta: { title: 'Lọc Theo Giá' } },
      // { path: 'searchProduct', name: 'search-product', component: SearchProduct, meta: { title: 'Tìm kiếm sản phẩm' } }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/Authentical/LoginView.vue'),
    meta: { title: 'Đăng nhập' }
  },
  {
    path: '/admin',
    component: AdminLayout,
    children: [
      { path: '', redirect: '/admin/dashboard' },
      { path: 'dashboard', name: 'admin.dashboard', component: Dashboard },
      
      // Products
      { path: 'products', name: 'admin.products.list', component: Products },
      { path: 'products/create', name: 'admin.products.create', component: ProductForm },
      { path: 'products/:id/edit', name: 'admin.products.edit', component: ProductForm },

      // Orders
      { path: 'orders', name: 'admin.orders.list', component: Orders },
      { path: 'orders/:id', name: 'admin.orders.detail', component: OrderDetail },
      
      // Accounts
      { path: 'accounts', name: 'admin.accounts.list', component: Accounts },
      { path: 'accounts/create', name: 'admin.accounts.create', component: AccountForm },
      { path: 'accounts/:id/edit', name: 'admin.accounts.edit', component: AccountForm },

      // Posts
      { path: 'posts', name: 'admin.posts.list', component: Posts },
      { path: 'posts/create', name: 'admin.posts.create', component: PostForm },
      { path: 'posts/:id/edit', name: 'admin.posts.edit', component: PostForm },
      
      
      //{ path: 'pos', name: 'admin.pos', component: PointOfSale },
      { path: 'attributes', name: 'admin.attributes', component: Attributes },
      { path: 'reviews', name: 'admin.reviews.list', component: Reviews },

    ]
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.afterEach((to) => {
  const defaultTitle = 'Nexvibe'
  if (typeof window !== 'undefined' && document) {
    document.title = to.meta?.title || defaultTitle
  }
})

export default router