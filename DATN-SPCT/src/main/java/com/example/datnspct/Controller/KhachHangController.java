package com.example.datnspct.Controller;

import com.example.datnspct.dto.KhachHangDTO;
import com.example.datnspct.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/api/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    // Tạo mới
    @PostMapping
    public ResponseEntity<KhachHangDTO> taoKhachHang(@RequestBody KhachHangDTO khachHangDTO) {
        KhachHangDTO khachHangDaTao = khachHangService.taoKhachHang(khachHangDTO);
        return ResponseEntity.ok(khachHangDaTao);
    }

    // Lấy theo ID
    @GetMapping("/{id}")
    public ResponseEntity<KhachHangDTO> layKhachHangTheoId(@PathVariable Integer id) {
        KhachHangDTO khachHangDTO = khachHangService.layKhachHangTheoId(id);
        return ResponseEntity.ok(khachHangDTO);
    }

    // Lấy tất cả
    @GetMapping
    public ResponseEntity<List<KhachHangDTO>> layTatCaKhachHang() {
        List<KhachHangDTO> khachHangDTOs = khachHangService.layTatCaKhachHang();
        return ResponseEntity.ok(khachHangDTOs);
    }

    // Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<KhachHangDTO> capNhatKhachHang(@PathVariable Integer id, @RequestBody KhachHangDTO khachHangDTO) {
        KhachHangDTO khachHangDaCapNhat = khachHangService.capNhatKhachHang(id, khachHangDTO);
        return ResponseEntity.ok(khachHangDaCapNhat);
    }

    // Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaKhachHang(@PathVariable Integer id) {
        khachHangService.xoaKhachHang(id);
        return ResponseEntity.noContent().build();
    }
}