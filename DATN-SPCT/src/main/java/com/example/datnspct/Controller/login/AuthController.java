package com.example.datnspct.Controller.login;

import com.example.datnspct.Model.KhachHang;
import com.example.datnspct.Model.NhanVien;
import com.example.datnspct.Model.TaiKhoan;
import com.example.datnspct.Repository.KhachHangRepository;
import com.example.datnspct.Repository.NhanVienRepository;
import com.example.datnspct.Repository.login.TaiKhoanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final TaiKhoanRepository taiKhoanRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(TaiKhoanRepository taiKhoanRepository,
                          KhachHangRepository khachHangRepository,
                          NhanVienRepository nhanVienRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody TaiKhoan taiKhoan) {
        if (taiKhoanRepository.findByTaiKhoan(taiKhoan.getTaiKhoan()).isPresent()) {
            return ResponseEntity.badRequest().body("Tài khoản đã tồn tại!");
        }

        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoan.getMatKhau()));
        taiKhoan.setTrangThai(true);
        if (taiKhoan.getChucVu() == null) {
            taiKhoan.setChucVu("KHACH_HANG");
        }

        taiKhoanRepository.save(taiKhoan);
        return ResponseEntity.ok("Đăng ký thành công!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TaiKhoan loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getTaiKhoan(),
                            loginRequest.getMatKhau()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TaiKhoan taiKhoan = taiKhoanRepository.findByTaiKhoan(loginRequest.getTaiKhoan())
                    .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

            Map<String, Object> response = new HashMap<>();
            response.put("idTK", taiKhoan.getIdTK());
            response.put("taiKhoan", taiKhoan.getTaiKhoan());
            response.put("chucVu", taiKhoan.getChucVu());

            Optional<KhachHang> khachHang = khachHangRepository.findByTaiKhoanIdTK(taiKhoan.getIdTK());
            khachHang.ifPresent(kh -> response.put("idKH", kh.getIdKH()));

            Optional<NhanVien> nhanVien = nhanVienRepository.findByTaiKhoanIdTK(taiKhoan.getIdTK());
            nhanVien.ifPresent(nv -> response.put("idNV", nv.getIdNV()));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Đăng nhập thất bại: " + e.getMessage()));
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
                return ResponseEntity.status(401).body("Chưa đăng nhập");
            }

            String username = authentication.getName();
            TaiKhoan taiKhoan = taiKhoanRepository.findByTaiKhoan(username)
                    .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

            Map<String, Object> response = new HashMap<>();
            response.put("idTK", taiKhoan.getIdTK());
            response.put("taiKhoan", taiKhoan.getTaiKhoan());
            response.put("chucVu", taiKhoan.getChucVu());

            Optional<KhachHang> khachHang = khachHangRepository.findByTaiKhoanIdTK(taiKhoan.getIdTK());
            khachHang.ifPresent(kh -> response.put("idKH", kh.getIdKH()));

            Optional<NhanVien> nhanVien = nhanVienRepository.findByTaiKhoanIdTK(taiKhoan.getIdTK());
            nhanVien.ifPresent(nv -> response.put("idNV", nv.getIdNV()));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Chưa đăng nhập");
        }
    }
}