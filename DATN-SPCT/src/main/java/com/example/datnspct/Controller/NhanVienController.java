package com.example.datnspct.Controller;


import com.example.datnspct.Service.NhanVienService;
import com.example.datnspct.dto.NhanVienDTO;
import com.example.datnspct.dto.Request.NhanVienCreateRequest;
import com.example.datnspct.dto.Request.NhanVienUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/STAFF")
@RequiredArgsConstructor
public class NhanVienController {
    private final NhanVienService nhanVienService;

    @GetMapping("/get-All")
    public ResponseEntity<List<NhanVienDTO>> getAll() {
        return ResponseEntity.ok(nhanVienService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody NhanVienCreateRequest request){
        nhanVienService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String nhanVienId) {
        nhanVienService.inactiveNhanVien(nhanVienId);
    }
    @PutMapping("/update")
    public void update(@RequestBody NhanVienUpdateRequest request){
        nhanVienService.update(request);
    }
    @GetMapping("/get-by-id")
    public NhanVienDTO getById(@RequestParam String nhanVienId){
        return nhanVienService.getById(nhanVienId);
    }
}
