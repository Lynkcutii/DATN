import adminLayout from "@/layouts/Adminlayout.vue";
// import staffAdminRoutes from "./StaffRoutes/StaffRoutes";
// import DotGiamGiaAdminRoutes from "./DotGiamGiaRoutes/DotGiamGia";
import khachHangAdminRoutes from "./KhachHangRoutes";
// import { BanHangTaiQuay } from '@/views/Admin';
// import SanPhamAdminRoutes from "./SanPhamRoutes/SanPham";
// import thongKeRoutes from "./ThongKe/ThongKeRoutes";
// import PhieuGiamGiaAdminRoutes from "./PhieuGiamGiaRoutes/PhieuGiamGia";

const adminRoutes = [
  {
    path: "/admin",
    component: adminLayout,
    children: [
      // { path: 'ban-hang-tai-quay', name: 'BanHangTaiQuay', component: BanHangTaiQuay },
      // { path: 'dashboard', name: 'AdminDashboard', component: () => import('@/views/Admin/Dashboard.vue') },
      // { path: 'orders', name: 'AdminOrders', component: () => import('@/views/Admin/Orders.vue') },
      // { path: 'quan-ly-don-hang', name: 'QuanLyDonHang', component: () => import('@/views/Admin/QuanLyDonHang.vue') },
      // { path: 'reports', name: 'AdminReports', component: () => import('@/views/Admin/Reports.vue') },
      // ...staffAdminRoutes,
      // ...DotGiamGiaAdminRoutes,
      // ...khachHangAdminRoutes,
      // ...SanPhamAdminRoutes,
      // ...thongKeRoutes,
      // ...PhieuGiamGiaAdminRoutes
    ],
  },
];

export default adminRoutes;