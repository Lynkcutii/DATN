package com.example.datnspct.Controller;

import com.example.datnspct.Service.PhuongThucThanhToanService;
import com.example.datnspct.dto.PhuongThucThanhToanDTO;
import com.example.datnspct.dto.Request.PhuongThucThanhToanCreateRequest;
import com.example.datnspct.dto.Request.PhuongThucThanhToanUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Payment")
@RequiredArgsConstructor
public class PhuongThucThanhToanController {
    private final PhuongThucThanhToanService phuongThucThanhToanService;

    @GetMapping("/get-All")
    public ResponseEntity<List<PhuongThucThanhToanDTO>> getAll() {
        return ResponseEntity.ok(phuongThucThanhToanService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody PhuongThucThanhToanCreateRequest request){
        phuongThucThanhToanService.create(request);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam String phuongThucThanhToanId) {
        phuongThucThanhToanService.delete(phuongThucThanhToanId);
    }
    @PutMapping("/update")
    public void update(@RequestBody PhuongThucThanhToanUpdateRequest request){
        phuongThucThanhToanService.update(request);
    }
    @GetMapping("/get-by-id")
    public PhuongThucThanhToanDTO getById(@RequestParam String phuongThucThanhToanId){
        return phuongThucThanhToanService.getById(phuongThucThanhToanId);
    }
}
