package com.example.datnspct.Controller;


import com.example.datnspct.Service.HoaDonCTService;
import com.example.datnspct.Service.SanPhamCTService;
import com.example.datnspct.dto.HoaDonChiTietDTO;
import com.example.datnspct.dto.Request.HoaDonCTCreateRequest;
import com.example.datnspct.dto.Request.HoaDonCTUpdateRequest;
import com.example.datnspct.dto.Request.SanPhamCTCreateRequest;
import com.example.datnspct.dto.Request.SanPhamCTUpdateRequest;
import com.example.datnspct.dto.SanPhamCTDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Invoice")
@RequiredArgsConstructor
public class HoaDonCTController {
    private final HoaDonCTService hoaDonCTService;

    @GetMapping("/san-pham-chi-tiet")
    public ResponseEntity<List<HoaDonChiTietDTO>> getAll() {
        return ResponseEntity.ok(hoaDonCTService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody HoaDonCTCreateRequest request){
        hoaDonCTService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String hoaDonCTId){
        hoaDonCTService.inactiveHoaDonCT(hoaDonCTId);
    }
    @PutMapping("/update")
    public void update(@RequestBody HoaDonCTUpdateRequest request){
        hoaDonCTService.update(request);
    }
    @GetMapping("/get-by-id")
    public HoaDonChiTietDTO getById(@RequestParam String hoaDonCTId){
     return hoaDonCTService.getById(hoaDonCTId);
    }
}
