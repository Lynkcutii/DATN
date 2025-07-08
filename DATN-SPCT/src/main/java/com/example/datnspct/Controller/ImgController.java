package com.example.datnspct.Controller;

import com.example.datnspct.Service.ImgService;
import com.example.datnspct.dto.ImgDTO;
import com.example.datnspct.dto.Request.ImgCreateRequest;
import com.example.datnspct.dto.Request.ImgUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Img")
@RequiredArgsConstructor
public class ImgController {
    private final ImgService imgService;

    @GetMapping("/get-All")
    public ResponseEntity<List<ImgDTO>> getAll() {
        return ResponseEntity.ok(imgService.getAll());
    }

    @PostMapping("/create")
    public void create(@RequestBody ImgCreateRequest request){
        imgService.create(request);
    }
    @DeleteMapping("/delete")
    public void inactive(@RequestParam String imgId){
        imgService.delete(imgId);
    }
    @PutMapping("/update")
    public void update(@RequestBody ImgUpdateRequest request){
        imgService.update(request);
    }
    @GetMapping("/get-by-id")
    public ImgDTO getById(@RequestParam String imgId){
     return imgService.getById(imgId);
    }
}
