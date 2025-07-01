package com.example.datnspct.Controller;

import com.example.datnspct.Service.DanhMucService;
import com.example.datnspct.dto.DanhMucDTO;
import com.example.datnspct.dto.Request.DanhMucCreateRequest;
import com.example.datnspct.dto.Request.DanhMucUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
@RequiredArgsConstructor
public class DanhMucController {
    private final DanhMucService danhMucService;

    @GetMapping("/get-All")
    public ResponseEntity<List<DanhMucDTO>> getAll() {
        return ResponseEntity.ok(danhMucService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody DanhMucCreateRequest request){
        danhMucService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String danhMucId) {
        danhMucService.inactiveDanhMuc(danhMucId);
    }
    @PutMapping("/update")
    public void update(@RequestBody DanhMucUpdateRequest request){
        danhMucService.update(request);
    }
    @GetMapping("/get-by-id")
    public DanhMucDTO getById(@RequestParam String danhMucId){
        return danhMucService.getById(danhMucId);
    }
}
