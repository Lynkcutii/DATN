package com.example.datnspct.Controller;


import com.example.datnspct.Service.SanPhamCTService;
import com.example.datnspct.dto.Request.SanPhamCTCreateRequest;
import com.example.datnspct.dto.Request.SanPhamCTUpdateRequest;
import com.example.datnspct.dto.SanPhamCTDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
@RequiredArgsConstructor
public class SanPhamCTController {
    private final SanPhamCTService sanPhamCTService;

    @GetMapping("/san-pham-chi-tiet")
    public ResponseEntity<List<SanPhamCTDTO>> getAll() {
        return ResponseEntity.ok(sanPhamCTService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody SanPhamCTCreateRequest request){
        sanPhamCTService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String sanPhamCTId){
        sanPhamCTService.inactiveSanPhamCT(sanPhamCTId);
    }
    @PutMapping("/update")
    public void update(@RequestBody SanPhamCTUpdateRequest request){
        sanPhamCTService.update(request);
    }
    @GetMapping("/get-by-id")
    public SanPhamCTDTO getById(@RequestParam String sanPhamCTId){
     return sanPhamCTService.getById(sanPhamCTId);
    }
}
