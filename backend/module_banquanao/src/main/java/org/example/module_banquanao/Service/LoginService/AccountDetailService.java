package org.example.module_banquanao.Service.LoginService;

import lombok.RequiredArgsConstructor;
import org.example.module_banquanao.Entity.TaiKhoan;
import org.example.module_banquanao.Repository.TaiKhoanRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailService implements UserDetailsService {

    private final TaiKhoanRepository taiKhoanRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTaiKhoan(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản với tai_khoan: " + username));
        return new CustomUserDetails(taiKhoan);
    }
}