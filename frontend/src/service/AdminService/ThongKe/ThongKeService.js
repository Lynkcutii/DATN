import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/admin/thongKe",
    withCredentials: true, // ❗ Gửi cookie (JSESSIONID) qua CORS
});

export const getRevenueByDateRange = async (startDate, endDate) => {
  try {
    const response = await axiosInstance.get("/doanhThuTheoNgay", {
      params: {
        startDate,
        endDate,
      },
    });
    return response.data; // List<RevenueStatisticsDTO>
  } catch (error) {
    if (error.response) {
      // Server phản hồi lỗi
      console.error("Error response data:", error.response.data);
      console.error("Error response status:", error.response.status);
      console.error("Error response headers:", error.response.headers);
      throw new Error(
        `Request failed with status ${error.response.status}: ${JSON.stringify(error.response.data)}`
      );
    } else if (error.request) {
      // Request đã gửi nhưng không nhận được phản hồi
      console.error("No response received:", error.request);
      throw new Error("No response received from server.");
    } else {
      // Lỗi khác khi thiết lập request
      console.error("Error setting up request:", error.message);
      throw new Error(error.message);
    }
  }
};


export const getRevenueByMonth = async (startDate, endDate) => {
  try {
    const response = await axiosInstance.get("/doanhThuTheoThang", {
      params: {
        startDate,
        endDate,
      },
    });
    return response.data; // List<RevenueStatisticsDTO>
  } catch (error) {
    console.error("Error fetching revenue by month:", error);
    throw error;
  }
};

export const getTopSellingProducts = async (startDate, endDate) => {
  try {
    const response = await axiosInstance.get("/sanPhamBanChay", {
      params: {
        startDate,
        endDate,
      },
    });
    return response.data; // List<ProductStatisticsDTO>
  } catch (error) {
    console.error("Error fetching top selling products:", error);
    throw error;
  }
};

export const getOverviewByDate = async (date) => {
  try {
    const response = await axiosInstance.get("/tongQuan", {
      params: {
        date,
      },
    });
    return response.data; // { totalOrders, totalRevenue, totalCustomers }
  } catch (error) {
    console.error("Error fetching overview by date:", error);
    throw error;
  }
};