package com.example.datnspct.Controller;

import com.example.datnspct.Service.GioHangService;
import com.example.datnspct.dto.GioHangDTO;
import com.example.datnspct.dto.Request.GioHangCreateRequest;
import com.example.datnspct.dto.Request.GioHangUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CART")
@RequiredArgsConstructor
public class GioHangController {
    private final GioHangService gioHangService;

    @GetMapping("/get-All")
    public ResponseEntity<List<GioHangDTO>> getAll() {
        return ResponseEntity.ok(gioHangService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody GioHangCreateRequest request){
        gioHangService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String gioHangId) {
        gioHangService.inactiveGioHang(gioHangId);
    }
    @PutMapping("/update")
    public void update(@RequestBody GioHangUpdateRequest request){
        gioHangService.update(request);
    }
    @GetMapping("/get-by-id")
    public GioHangDTO getById(@RequestParam String gioHangId){
        return gioHangService.getById(gioHangId);
    }
}
