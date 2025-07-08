package com.example.datnspct.Service;


import com.example.datnspct.dto.KhuyenMaiDTO;
import com.example.datnspct.dto.Request.KhuyenMaiCreateRequest;
import com.example.datnspct.dto.Request.KhuyenMaiUpdateRequest;

import java.util.List;

public interface KhuyenMaiService {
    void create ( KhuyenMaiCreateRequest request);
    void inactiveKhuyenMai (String khuyenMaiId);
    void update (KhuyenMaiUpdateRequest request);

    KhuyenMaiDTO getById (String khuyenMaiId);
    List<KhuyenMaiDTO> getAll();

}
