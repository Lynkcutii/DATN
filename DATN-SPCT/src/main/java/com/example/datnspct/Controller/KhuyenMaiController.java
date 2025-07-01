package com.example.datnspct.Controller;

import com.example.datnspct.Service.KhuyenMaiService;
import com.example.datnspct.dto.KhuyenMaiDTO;
import com.example.datnspct.dto.Request.KhuyenMaiCreateRequest;
import com.example.datnspct.dto.Request.KhuyenMaiUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Promotion")
@RequiredArgsConstructor
public class KhuyenMaiController {
    private final KhuyenMaiService khuyenMaiService;

    @GetMapping("/get-All")
    public ResponseEntity<List<KhuyenMaiDTO>> getAll() {
        return ResponseEntity.ok(khuyenMaiService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody KhuyenMaiCreateRequest request){
        khuyenMaiService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String khuyenMaiId) {
        khuyenMaiService.inactiveKhuyenMai(khuyenMaiId);
    }
    @PutMapping("/update")
    public void update(@RequestBody KhuyenMaiUpdateRequest request){
        khuyenMaiService.update(request);
    }
    @GetMapping("/get-by-id")
    public KhuyenMaiDTO getById(@RequestParam String khuyenMaiId){
        return khuyenMaiService.getById(khuyenMaiId);
    }
}
