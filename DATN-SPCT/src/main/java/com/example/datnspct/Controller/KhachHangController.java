package com.example.datnspct.Controller;

import com.example.datnspct.Service.KhachHangService;
import com.example.datnspct.dto.KhachHangDTO;
import com.example.datnspct.dto.Request.KhachHangCreateRequest;
import com.example.datnspct.dto.Request.KhachHangUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CLIENT")
@RequiredArgsConstructor
public class KhachHangController {
    private final KhachHangService khachHangService;

    @GetMapping("/get-All")
    public ResponseEntity<List<KhachHangDTO>> getAll() {
        return ResponseEntity.ok(khachHangService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody KhachHangCreateRequest request){
        khachHangService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String khachHangId) {
        khachHangService.inactiveKhachHang(khachHangId);
    }
    @PutMapping("/update")
    public void update(@RequestBody KhachHangUpdateRequest request){
        khachHangService.update(request);
    }
    @GetMapping("/get-by-id")
    public KhachHangDTO getById(@RequestParam String khachHangId){
        return khachHangService.getById(khachHangId);
    }
}
