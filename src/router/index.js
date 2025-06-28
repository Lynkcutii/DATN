import { createRouter, createWebHistory } from 'vue-router';
import AdminLayout from '../components/layouts/AdminLayout.vue';
import Dashboard from '../views/Dashboard.vue';
import Products from '../views/Products.vue';
import Orders from '../views/Orders.vue';
import PointOfSale from '../views/PointOfSale.vue';
import Attributes from '../views/Attributes.vue';
import Accounts from '../views/Accounts.vue';
import Posts from '../views/Posts.vue';
import Reviews from '../views/Reviews.vue';
import ProductForm from '../views/forms/ProductForm.vue';
import AccountForm from '../views/forms/AccountForm.vue';
import PostForm from '../views/forms/PostForm.vue';
import OrderDetail from '../views/OrderDetail.vue';

const routes = [
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
            
            
            { path: 'pos', name: 'admin.pos', component: PointOfSale },
            { path: 'attributes', name: 'admin.attributes', component: Attributes },
            { path: 'reviews', name: 'admin.reviews.list', component: Reviews },
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;