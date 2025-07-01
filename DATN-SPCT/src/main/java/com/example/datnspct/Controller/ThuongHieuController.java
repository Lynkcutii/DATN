package com.example.datnspct.Controller;

import com.example.datnspct.Service.ThuongHieuService;
import com.example.datnspct.dto.Request.ThuongHieuCreateRequest;
import com.example.datnspct.dto.Request.ThuongHieuUpdateRequest;
import com.example.datnspct.dto.ThuongHieuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Trademark")
@RequiredArgsConstructor
public class ThuongHieuController {
    private final ThuongHieuService thuongHieuService;

    @GetMapping("/get-All")
    public ResponseEntity<List<ThuongHieuDTO>> getAll() {
        return ResponseEntity.ok(thuongHieuService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody ThuongHieuCreateRequest request){
        thuongHieuService.create(request);
    }
    @GetMapping("/inactive")
    public void inactive(@RequestParam String thuongHieuId) {
        thuongHieuService.inactiveThuongHieu(thuongHieuId);
    }
    @PutMapping("/update")
    public void update(@RequestBody ThuongHieuUpdateRequest request){
        thuongHieuService.update(request);
    }
    @GetMapping("/get-by-id")
    public ThuongHieuDTO getById(@RequestParam String thuongHieuId){
        return thuongHieuService.getById(thuongHieuId);
    }
}
