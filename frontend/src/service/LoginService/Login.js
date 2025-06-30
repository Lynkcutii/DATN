import axios from 'axios'

export const API_URL = 'http://localhost:8080/api/auth'

const login = async (tai_khoan, mat_khau) => {
  try {
    const response = await axios.post(`${API_URL}/login`, { tai_khoan, mat_khau }, {
      withCredentials: true,
      headers: { 'Content-Type': 'application/json' }
    })
    const { message, roles, id, fullName } = response.data
    localStorage.setItem('user', JSON.stringify({ id, tai_khoan, roles, fullName }))
    return { message, roles, id, fullName }
  } catch (error) {
    if (error.response) {
      throw error.response.data || 'Lỗi đăng nhập'
    } else if (error.request) {
      throw 'Lỗi kết nối mạng'
    } else {
      throw 'Có lỗi xảy ra khi gửi yêu cầu đăng nhập'
    }
  }
}

export default {
  login
}