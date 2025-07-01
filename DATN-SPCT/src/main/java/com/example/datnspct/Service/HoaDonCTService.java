package com.example.datnspct.Service;


import com.example.datnspct.dto.HoaDonChiTietDTO;
import com.example.datnspct.dto.Request.HoaDonCTCreateRequest;
import com.example.datnspct.dto.Request.HoaDonCTUpdateRequest;

import java.util.List;

public interface HoaDonCTService {
    void create (HoaDonCTCreateRequest request);
    void inactiveHoaDonCT (String hoaDonCTId);
    void update (HoaDonCTUpdateRequest request);

    HoaDonChiTietDTO getById (String HoaDonCTId);
    List<HoaDonChiTietDTO> getAll();

}
