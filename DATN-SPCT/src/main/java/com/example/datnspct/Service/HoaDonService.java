package com.example.datnspct.Service;


import com.example.datnspct.dto.HoaDonDTO;
import com.example.datnspct.dto.Request.HoaDonCreateRequest;
import com.example.datnspct.dto.Request.HoaDonUpdateRequest;

import java.util.List;

public interface HoaDonService {
    void create (HoaDonCreateRequest request);
    void inactiveHoaDon (String HoaDonId);
    void update (HoaDonUpdateRequest request);

    HoaDonDTO getById (String HoaDonId);
    List<HoaDonDTO> getAll();

}
