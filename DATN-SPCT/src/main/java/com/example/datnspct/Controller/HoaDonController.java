package com.example.datnspct.Controller;

import com.example.datnspct.Service.HoaDonService;
import com.example.datnspct.dto.HoaDonDTO;
import com.example.datnspct.dto.Request.HoaDonCreateRequest;
import com.example.datnspct.dto.Request.HoaDonUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HoaDon")
@RequiredArgsConstructor
public class HoaDonController {
    private final HoaDonService hoaDonService;

    @GetMapping("/get-All")
    public ResponseEntity<List<HoaDonDTO>> getAll() {
        return ResponseEntity.ok(hoaDonService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody HoaDonCreateRequest request){
        hoaDonService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String hoaDonId) {
        hoaDonService.inactiveHoaDon(hoaDonId);
    }
    @PutMapping("/update")
    public void update(@RequestBody HoaDonUpdateRequest request){
        hoaDonService.update(request);
    }
    @GetMapping("/get-by-id")
    public HoaDonDTO getById(@RequestParam String hoaDonId){
        return hoaDonService.getById(hoaDonId);
    }
}
