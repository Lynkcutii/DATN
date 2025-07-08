package org.example.module_banquanao.Service.LoginService;

import org.example.module_banquanao.Entity.TaiKhoan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final TaiKhoan taiKhoan;

    public CustomUserDetails(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Giả sử ChucVu là vai trò (role), chuyển thành GrantedAuthority
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + taiKhoan.getChucVu()));
    }

    @Override
    public String getPassword() {
        return taiKhoan.getMatKhau();
    }

    @Override
    public String getUsername() {
        return taiKhoan.getTaiKhoan();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Có thể tùy chỉnh dựa trên TrangThai nếu cần
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Có thể tùy chỉnh dựa trên TrangThai nếu cần
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Có thể tùy chỉnh nếu cần
    }

    @Override
    public boolean isEnabled() {
        return taiKhoan.getTrangThai(); // Giả sử TrangThai = 1 là enabled
    }
}