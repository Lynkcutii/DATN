import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/home",
  withCredentials: true,
});


// 🟢 Lấy tất cả products có phân trang
export const getAllSanPham = async (page = 0) => {
  try {
    const response = await axiosInstance.get("", {
      params: { page },
    });
    return response.data;
  } catch (error) {
    console.error("An error was thrown while loading the products of client: ", error);
    throw error.response?.data || "Error getting products";
  }
};


export const detailSanPham = async (id) => {
  try {
    console.log("Gọi API với ID:", id);
    const response = await axiosInstance.get(`/${id}`);
    return response.data;
  } catch (error) {
    console.error("Lỗi khi gọi API detailSanPham:", error);
    throw error.response?.data || "Error getting product details";
  }
};