import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/admin/staff",
  withCredentials: true, // ❗ Gửi cookie (JSESSIONID) qua CORS
});

// 🟢 Lấy tất cả staff có phân trang
export const getAllStaff = async (page = 0) => {
  try {
    const response = await axiosInstance.get("", {
      params: { page },
    });
    return response.data;
  } catch (error) {
    console.error("An error was thrown while loading the staff of admin: ", error);
    throw error.response?.data || "Error getting staff";
  }
};

// 🟢 Lấy chi tiết một staff theo id
export const detailStaff = async (id) => {
  try {
    const response = await axiosInstance.get(`/${id}`);
    return response.data;
  } catch (error) {
    console.error("Có lỗi khi Detail Staff phía admin:", error);
    throw error.response?.data || "Lỗi lấy Staff";
  }
};

// 🟢 Cập nhật thông tin staff
export const updateStaff = async (id, staffRequest) => {
  try {
    const response = await axiosInstance.put(`/${id}`, staffRequest);
    return response.data;
  } catch (error) {
    if (error.response) {
      // Server trả về lỗi (status != 2xx)
      console.error("Lỗi phản hồi từ server:", error.response.data);
      throw error.response.data;
    } else if (error.request) {
      // Request đã gửi đi rồi nhưng không nhận được phản hồi từ server
      console.error("Không có phản hồi từ server:", error.request);
      throw "Không có phản hồi từ server";
    } else {
      // Lỗi xảy ra trong quá trình tạo request (cấu hình axios sai, v.v...)
      console.error("Lỗi khác:", error.message);
      throw error.message;
    }
  }
};

// 🟢 Thêm mới staff
export const addStaff = async(staffRequest) => {
    try {
        const response = await axiosInstance.post(``, staffRequest);
        return response.data;
    }
    catch(error) {
     console.error("Có lỗi khi tạo staff phía admin:", error.response ? error.response.data : error.message);
    throw error.response?.data || "Lỗi tạo Satff";
  }
}


// 🟢 Tìm kiếm staff theo keyword (mã, tên, email)
export const searchNhanVien = async (keyword, page = 0, trangthai) => {
  try {
    const params = {
      keyword: keyword,
      page: page
    };

    // Chỉ thêm nếu trangthai không phải null hoặc undefined
    if (trangthai !== null && trangthai !== undefined) {
      params.trangthai = trangthai;
      // alert(params.trangthai)
    }

    const response = await axiosInstance.get("", { params });
    return response.data;
  } catch (error) {
    console.error("Lỗi khi tìm kiếm nhân viên:", error);
    throw error.response?.data || "Lỗi tìm kiếm nhân viên";
  }
};