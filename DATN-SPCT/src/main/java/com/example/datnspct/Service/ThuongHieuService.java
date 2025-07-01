package com.example.datnspct.Service;


import com.example.datnspct.dto.Request.ThuongHieuCreateRequest;
import com.example.datnspct.dto.Request.ThuongHieuUpdateRequest;
import com.example.datnspct.dto.ThuongHieuDTO;

import java.util.List;

public interface ThuongHieuService {
    void create ( ThuongHieuCreateRequest request);
    void inactiveThuongHieu (String thuongHieuId);
    void update (ThuongHieuUpdateRequest request);

    ThuongHieuDTO getById (String thuongHieuId);
    List<ThuongHieuDTO> getAll();

}
