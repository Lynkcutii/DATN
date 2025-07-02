package com.example.datnspct.Controller;

import com.example.datnspct.dto.PhuongThucThanhToanDTO;
import com.example.datnspct.Service.PhuongThucThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phuongthucthanhtoan")
public class PhuongThucThanhToanController {
    @Autowired
    private PhuongThucThanhToanService service;

    // Tạo mới
    @PostMapping
    public ResponseEntity<PhuongThucThanhToanDTO> create(@RequestBody PhuongThucThanhToanDTO dto) {
        PhuongThucThanhToanDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }

    // Lấy theo ID
    @GetMapping("/{id}")
    public ResponseEntity<PhuongThucThanhToanDTO> getById(@PathVariable Integer id) {
        PhuongThucThanhToanDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    // Lấy tất cả
    @GetMapping
    public ResponseEntity<List<PhuongThucThanhToanDTO>> getAll() {
        List<PhuongThucThanhToanDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<PhuongThucThanhToanDTO> update(@PathVariable Integer id, @RequestBody PhuongThucThanhToanDTO dto) {
        PhuongThucThanhToanDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
