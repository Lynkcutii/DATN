package com.example.datnspct.Service;


import com.example.datnspct.dto.Request.SanPhamCTCreateRequest;
import com.example.datnspct.dto.Request.SanPhamCTUpdateRequest;
import com.example.datnspct.dto.SanPhamCTDTO;

import java.util.List;

public interface SanPhamCTService {
    void create (SanPhamCTCreateRequest request);
    void inactiveSanPhamCT (String SanPhamCTId);
    void update (SanPhamCTUpdateRequest request);

    SanPhamCTDTO getById (String SanPhamCTId);
    List<SanPhamCTDTO> getAll();

}
