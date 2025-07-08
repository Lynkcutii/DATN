package com.example.datnspct.Service;

import com.example.datnspct.dto.GioHangCTDTO;
import com.example.datnspct.dto.Request.GioHangCTCreateRequest;
import com.example.datnspct.dto.Request.GioHangCTUpdateRequest;

import java.util.List;

public interface GioHangCTService {
    void create ( GioHangCTCreateRequest request);
    void delete (String gioHangCTId);
    void update (GioHangCTUpdateRequest request);

    GioHangCTDTO getById (String gioHangId);
    List<GioHangCTDTO> getAll();

}
