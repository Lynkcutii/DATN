package com.example.datnspct.Controller;

import com.example.datnspct.Service.GioHangCTService;
import com.example.datnspct.Service.GioHangService;
import com.example.datnspct.dto.GioHangCTDTO;
import com.example.datnspct.dto.GioHangDTO;
import com.example.datnspct.dto.Request.GioHangCTCreateRequest;
import com.example.datnspct.dto.Request.GioHangCTUpdateRequest;
import com.example.datnspct.dto.Request.GioHangCreateRequest;
import com.example.datnspct.dto.Request.GioHangUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CART-DETAILS")
@RequiredArgsConstructor
public class GioHangCTController {
    private final GioHangCTService gioHangCTService;

    @GetMapping("/get-All")
    public ResponseEntity<List<GioHangCTDTO>> getAll() {
        return ResponseEntity.ok(gioHangCTService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody GioHangCTCreateRequest request){
        gioHangCTService.create(request);
    }
    @GetMapping("/inactive")
    public void delete(@RequestParam String gioHangCTId) {
        gioHangCTService.delete(gioHangCTId);
    }
    @PutMapping("/update")
    public void update(@RequestBody GioHangCTUpdateRequest request){
        gioHangCTService.update(request);
    }
    @GetMapping("/get-by-id")
    public GioHangCTDTO getById(@RequestParam String gioHangCTId){
        return gioHangCTService.getById(gioHangCTId);
    }
}
