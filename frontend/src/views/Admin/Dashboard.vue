<template>
  <h1 class="h3 mb-4 text-gray-800">Dashboard</h1>
  <div class="row">
    <div class="col-xl-3 col-md-6 mb-4">
      <div class="card border-start-primary shadow h-100 py-2">
        <div class="card-body">
          <div class="row g-0 align-items-center">
            <div class="col">
              <div class="text-xs fw-bold text-primary text-uppercase mb-1">Doanh thu (Tháng)</div>
              <div class="h5 mb-0 fw-bold text-gray-800">{{ formatCurrency(stats.revenue) }}</div>
            </div>
            <div class="col-auto">
              <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-xl-3 col-md-6 mb-4"><div class="card border-start-success shadow h-100 py-2"><div class="card-body"><div class="row g-0 align-items-center"><div class="col"><div class="text-xs fw-bold text-success text-uppercase mb-1">Đơn hàng mới (Hôm nay)</div><div class="h5 mb-0 fw-bold text-gray-800">{{ stats.newOrders }}</div></div><div class="col-auto"><i class="fas fa-box fa-2x text-gray-300"></i></div></div></div></div></div>
    <div class="col-xl-3 col-md-6 mb-4"><div class="card border-start-info shadow h-100 py-2"><div class="card-body"><div class="row g-0 align-items-center"><div class="col"><div class="text-xs fw-bold text-info text-uppercase mb-1">Khách hàng mới (Tháng)</div><div class="h5 mb-0 fw-bold text-gray-800">{{ stats.newCustomers }}</div></div><div class="col-auto"><i class="fas fa-users fa-2x text-gray-300"></i></div></div></div></div></div>
    <div class="col-xl-3 col-md-6 mb-4"><div class="card border-start-warning shadow h-100 py-2"><div class="card-body"><div class="row g-0 align-items-center"><div class="col"><div class="text-xs fw-bold text-warning text-uppercase mb-1">Sản phẩm sắp hết</div><div class="h5 mb-0 fw-bold text-gray-800">{{ stats.lowStockProducts }}</div></div><div class="col-auto"><i class="fas fa-exclamation-triangle fa-2x text-gray-300"></i></div></div></div></div></div>
  </div>
  <div class="row">
    <div class="col-lg-7 mb-4">
      <div class="card shadow">
        <div class="card-header py-3">
          <h6 class="m-0 fw-bold text-primary">Biểu đồ Doanh thu (7 ngày gần nhất)</h6>
        </div>
        <div class="card-body">
          <div class="chart-container" style="position: relative; height:320px">
            <Line v-if="loaded" :data="chartData" :options="chartOptions" />
            <div v-else class="text-center p-5">Đang tải dữ liệu biểu đồ...</div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-lg-5 mb-4">
      <div class="card shadow">
        <div class="card-header py-3">
          <h6 class="m-0 fw-bold text-primary">Sản phẩm Bán chạy</h6>
        </div>
        <div class="card-body">
          <ul class="list-group list-group-flush">
            <li v-for="product in topSellingProducts" :key="product.id" class="list-group-item d-flex justify-content-between align-items-center">
              {{ product.name }}
              <span class="badge bg-primary rounded-pill">{{ product.sold }}</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { Line } from 'vue-chartjs';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);


const stats = ref({
    revenue: 85400000, newOrders: 12, newCustomers: 25, lowStockProducts: 3,
});
const topSellingProducts = ref([
    { id: 1, name: 'Áo Thun Thể Thao', sold: 140 }, { id: 2, name: 'Quần Jogger', sold: 98 },
    { id: 3, name: 'Áo Hoodie Nexvibe', sold: 75 }, { id: 4, name: 'Áo Ba Lỗ Tập Gym', sold: 62 },
    { id: 5, name: 'Mũ Lưỡi Trai Nexvibe', sold: 55 },
]);

const loaded = ref(false);
const chartData = ref({
  labels: [],
  datasets: [{
    label: 'Doanh thu',
    backgroundColor: 'rgba(78, 115, 223, 0.05)',
    borderColor: 'rgba(78, 115, 223, 1)',
    data: [],
    fill: true,
    tension: 0.3
  }]
});
const chartOptions = ref({
  responsive: true,
  maintainAspectRatio: false
});

const fetchChartData = async () => {
    setTimeout(() => {
        const responseFromApi = {
            labels: ["22/06", "23/06", "24/06", "25/06", "26/06", "27/06", "28/06"],
            data: [5500000, 7200000, 6800000, 9100000, 8500000, 11200000, 10500000]
        };
        
        chartData.value.labels = responseFromApi.labels;
        chartData.value.datasets[0].data = responseFromApi.data;
        loaded.value = true;
    }, 1000);
};

const formatCurrency = (value) => {
  return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
};

onMounted(() => {
    console.log('Dashboard mounted, fetching data...');
    fetchChartData();
});
</script>

<style scoped>
.card .border-start-primary { border-left: .25rem solid #4e73df!important; }
.card .border-start-success { border-left: .25rem solid #1cc88a!important; }
.card .border-start-info { border-left: .25rem solid #36b9cc!important; }
.card .border-start-warning { border-left: .25rem solid #f6c23e!important; }
.text-gray-300 { color: #dddfeb!important; }
</style>