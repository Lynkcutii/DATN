package org.example.module_banquanao.Controller.LoginController;

import org.example.module_banquanao.Entity.TaiKhoan;
import org.example.module_banquanao.Repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request, HttpServletRequest httpRequest) {
        System.out.println("Received login request: " + request);
        try {
            String taiKhoan = request.get("tai_khoan");
            String matKhau = request.get("mat_khau");
            System.out.println("Processing login for tai_khoan: " + taiKhoan);
            System.out.println("Received mat_khau: " + matKhau);

            if (taiKhoan == null || matKhau == null) {
                System.out.println("Missing credentials: tai_khoan=" + taiKhoan + ", mat_khau=" + matKhau);
                return ResponseEntity.badRequest().body("Thiếu thông tin tài khoản hoặc mật khẩu!");
            }

            TaiKhoan user = taiKhoanRepository.findByTaiKhoan(taiKhoan)
                    .orElseThrow(() -> {
                        System.out.println("Tai khoan khong ton tai: " + taiKhoan);
                        return new RuntimeException("Tài khoản không tồn tại!");
                    });

            if (!user.getMatKhau().equals(matKhau)) {
                System.out.println("Sai mat khau cho tai khoan: " + taiKhoan + ", expected: " + user.getMatKhau());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai mật khẩu!");
            }

            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("user", user);
            System.out.println("Dang nhap thanh cong cho tai khoan: " + taiKhoan + ", session created: " + session.getId());

            // Sử dụng HashMap để xử lý giá trị null
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Đăng nhập thành công!");
            responseBody.put("roles", user.getChucVu());
            responseBody.put("id", user.getId());
            responseBody.put("fullName", user.getFullName()); // Giả sử có getter fullName, nếu không có thì bỏ hoặc để null an toàn

            ResponseEntity<?> response = ResponseEntity.ok(responseBody);
            System.out.println("Response prepared: " + responseBody);
            return response;
        } catch (RuntimeException e) {
            System.err.println("Login error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi server: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(Map.of("message", "Đăng xuất thành công!"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        System.out.println("Checking current user");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            System.out.println("No session or user found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chưa đăng nhập");
        }

        TaiKhoan user = (TaiKhoan) session.getAttribute("user");
        // Sử dụng HashMap để xử lý giá trị null an toàn
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", user.getId());
        responseBody.put("tai_khoan", user.getTaiKhoan());
        responseBody.put("roles", user.getChucVu());
        responseBody.put("fullName", user.getFullName()); // Giả sử có getter fullName, nếu không có thì để null

        return ResponseEntity.ok(responseBody);
    }
}