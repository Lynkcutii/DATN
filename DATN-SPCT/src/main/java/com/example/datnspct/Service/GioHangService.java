package com.example.datnspct.Service;

import com.example.datnspct.dto.GioHangDTO;
import com.example.datnspct.dto.Request.GioHangCreateRequest;
import com.example.datnspct.dto.Request.GioHangUpdateRequest;

import java.util.List;

public interface GioHangService {
    void create ( GioHangCreateRequest request);
    void inactiveGioHang (String gioHangId);
    void update (GioHangUpdateRequest request);

    GioHangDTO getById (String gioHangId);
    List<GioHangDTO> getAll();

}
