package com.example.datnspct.Controller;

import com.example.datnspct.Service.SanPhamService;
import com.example.datnspct.dto.Request.SanPhamCreateRequest;
import com.example.datnspct.dto.Request.SanPhamUpdateRequest;
import com.example.datnspct.dto.SanPhamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
@RequiredArgsConstructor
public class SanPhamController {
    private final SanPhamService sanPhamService;

    @GetMapping("/get-All")
    public ResponseEntity<List<SanPhamDTO>> getAll() {
        return ResponseEntity.ok(sanPhamService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody SanPhamCreateRequest request){
        sanPhamService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String sanPhamId) {
        sanPhamService.inactiveSanPham(sanPhamId);
    }
    @PutMapping("/update")
    public void update(@RequestBody SanPhamUpdateRequest request){
        sanPhamService.update(request);
    }
    @GetMapping("/get-by-id")
    public SanPhamDTO getById(@RequestParam String sanPhamId){
        return sanPhamService.getById(sanPhamId);
    }
}
