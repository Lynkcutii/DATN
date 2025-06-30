import axios from 'axios'

export const API_URL = 'http://localhost:8080/api/auth' 

const register = async (userData) => {
  try {
    const response = await axios.post(`${API_URL}/register`,
       userData ,
       { withCredentials: true });
    const { message, roles } = response.data;
    return { message, roles };
  } catch (error) {
    if (error.response) {
      throw error.response.data || 'Lỗi đăng ký';
    } else if (error.request) {
      throw 'Lỗi kết nối mạng';
    } else {
      throw 'Có lỗi xảy ra khi gửi yêu cầu đăng ký';
    }
  }
};




export const logout = async () => {
  try {
    const response = await axios.post(`${API_URL}/logout`, {}, {
      withCredentials: true
    })
    return response.data 
  } catch (error) {
    throw error.response?.data || 'Lỗi đăng xuất'
  }
}

export const getCurrentUser = async () => {
  try {
    const response = await axios.get(`${API_URL}/me`, {
      withCredentials: true
    })
    return response.data  
  } catch (error) {
    throw error.response?.data || 'Lỗi lấy thông tin người dùng'
  }
}

export default {
  register,
  logout,
  getCurrentUser
}