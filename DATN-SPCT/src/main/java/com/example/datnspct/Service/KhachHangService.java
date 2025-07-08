package com.example.datnspct.Service;


import com.example.datnspct.dto.KhachHangDTO;
import com.example.datnspct.dto.Request.KhachHangCreateRequest;
import com.example.datnspct.dto.Request.KhachHangUpdateRequest;

import java.util.List;

public interface KhachHangService {
    void create ( KhachHangCreateRequest request);
    void inactiveKhachHang (String khachHangId);
    void update (KhachHangUpdateRequest request);

    KhachHangDTO getById (String khachHangId);
    List<KhachHangDTO> getAll();

}
