<template>
  <nav id="sidebar">
    <div class="sidebar-header">
        <img src="../img/logo/logo.jpeg" alt="Nexvibe Logo">
    </div>

    <ul class="nav flex-column">
        <li class="nav-item">
            <router-link to="/admin/dashboard" class="nav-link"><i class="fas fa-fw fa-tachometer-alt"></i> Dashboard</router-link>
        </li>
        <li class="nav-item">
            <router-link to="/admin/pos" class="nav-link"><i class="fas fa-fw fa-cash-register"></i> Bán hàng tại quầy</router-link>
        </li>
        <li class="nav-item">
            <router-link to="/admin/products" class="nav-link"><i class="fas fa-fw fa-shirt"></i> Quản lý Sản phẩm</router-link>
        </li>
        <li class="nav-item">
            <router-link to="/admin/orders" class="nav-link"><i class="fas fa-fw fa-box-open"></i> Quản lý Đơn hàng</router-link>
        </li>
        <li class="nav-item">
            <router-link to="/admin/attributes" class="nav-link"><i class="fas fa-fw fa-tags"></i> Quản lý Thuộc tính</router-link>
        </li>
        <li class="nav-item">
            <router-link to="/admin/accounts" class="nav-link"><i class="fas fa-fw fa-users-cog"></i> Quản lý Tài khoản</router-link>
        </li>
        <li class="nav-item">
            <router-link to="/admin/posts" class="nav-link"><i class="fas fa-fw fa-newspaper"></i> Quản lý Bài viết</router-link>
        </li>
        <li class="nav-item">
            <router-link to="/admin/reviews" class="nav-link"><i class="fas fa-fw fa-star"></i> Quản lý Đánh giá</router-link>
        </li>
    </ul>
  </nav>
</template>

<style scoped>
  .nav-link.router-link-exact-active {
    background-color: #0f172a;
    color: #fff;
    font-weight: 500;
    border-left: 4px solid #38bdf8;
  }
  .nav-link.router-link-exact-active i.fa-fw {
    color: #38bdf8;
  }
</style>