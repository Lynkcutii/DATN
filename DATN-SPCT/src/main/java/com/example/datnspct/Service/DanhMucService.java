package com.example.datnspct.Service;


import com.example.datnspct.dto.DanhMucDTO;
import com.example.datnspct.dto.Request.DanhMucCreateRequest;
import com.example.datnspct.dto.Request.DanhMucUpdateRequest;

import java.util.List;

public interface DanhMucService {
    void create ( DanhMucCreateRequest request);
    void inactiveDanhMuc (String danhMucId);
    void update (DanhMucUpdateRequest request);

    DanhMucDTO getById (String danhMucId);
    List<DanhMucDTO> getAll();

}
