package com.example.datnspct.Controller;

import com.example.datnspct.Service.TaiKhoanService;
import com.example.datnspct.dto.Request.TaiKhoanCreateRequest;
import com.example.datnspct.dto.Request.TaiKhoanUpdateRequest;
import com.example.datnspct.dto.TaiKhoanDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ACCOUNT")
@RequiredArgsConstructor
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;

    @GetMapping("/get-All")
    public ResponseEntity<List<TaiKhoanDTO>> getAll() {
        return ResponseEntity.ok(taiKhoanService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody TaiKhoanCreateRequest request){
        taiKhoanService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String taiKhoanId) {
        taiKhoanService.inactiveTaiKhoan(taiKhoanId);
    }
    @PutMapping("/update")
    public void update(@RequestBody TaiKhoanUpdateRequest request){
        taiKhoanService.update(request);
    }
    @GetMapping("/get-by-id")
    public TaiKhoanDTO getById(@RequestParam String taiKhoanId){
        return taiKhoanService.getById(taiKhoanId);
    }
}
