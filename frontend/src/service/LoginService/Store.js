import { createStore } from 'vuex'
import LoginService from './Login'

const store = createStore({
  state() {
    return {
      isLoggedIn: localStorage.getItem('isLoggedIn') === 'true',
      user: null,
      roles: []
    }
  },
  mutations: {
    setUser(state, user) {
      state.user = user
    },
    setRoles(state, roles) {
      state.roles = roles
    },
    clearUser(state) {
      state.user = null
      state.roles = []
      state.isLoggedIn = false
    },
    setLoggedIn(state, status) {
      state.isLoggedIn = status
    }
  },
  actions: {
    async login({ commit }, { tai_khoan, mat_khau }) {
      try {
        const { message, roles } = await LoginService.login(tai_khoan, mat_khau)
        commit('setUser', { tai_khoan })
        commit('setRoles', roles)
        commit('setLoggedIn', 'true')
        localStorage.setItem('isLoggedIn', 'true')
        return message
      } catch (error) {
        throw error.response?.data || 'Lỗi đăng nhập'
      }
    }
  },
  getters: {
    isAdmin(state) {
      return state.roles.includes('ROLE_ADMIN')
    }
  }
})

export default store