package com.example.datnspct.Service;


import com.example.datnspct.dto.PhuongThucThanhToanDTO;
import com.example.datnspct.dto.Request.PhuongThucThanhToanCreateRequest;
import com.example.datnspct.dto.Request.PhuongThucThanhToanUpdateRequest;

import java.util.List;

public interface PhuongThucThanhToanService {
    void create ( PhuongThucThanhToanCreateRequest request);
    void delete (String phuongThucThanhToanId);
    void update (PhuongThucThanhToanUpdateRequest request);

    PhuongThucThanhToanDTO getById (String phuongThucThanhToanId);
    List<PhuongThucThanhToanDTO> getAll();

}
