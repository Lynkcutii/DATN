package com.example.datnspct.Service;

import com.example.datnspct.dto.Request.SanPhamCreateRequest;
import com.example.datnspct.dto.Request.SanPhamUpdateRequest;
import com.example.datnspct.dto.SanPhamDTO;

import java.util.List;

public interface SanPhamService {
    void create ( SanPhamCreateRequest request);
    void inactiveSanPham (String sanPhamId);
    void update (SanPhamUpdateRequest request);

    SanPhamDTO getById (String sanPhamId);
    List<SanPhamDTO> getAll();

}
