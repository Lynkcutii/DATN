package com.example.datnspct.Controller.clinet;

import com.example.datnspct.dto.KhachHangDTO;
import com.example.datnspct.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/api/khachhang")
public class KhachHangClientController {

    @Autowired
    private KhachHangService khachHangService;

    // Lấy thông tin khách hàng theo idTK
    @GetMapping("/byTaiKhoanId/{idTK}")
    public ResponseEntity<KhachHangDTO> getKhachHangByTaiKhoanId(@PathVariable Integer idTK) {
        try {
            KhachHangDTO khachHangDTO = khachHangService.layKhachHangTheoTaiKhoanId(idTK);
            return ResponseEntity.ok(khachHangDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}