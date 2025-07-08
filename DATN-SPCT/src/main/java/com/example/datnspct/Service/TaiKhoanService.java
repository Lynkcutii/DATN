package com.example.datnspct.Service;

import com.example.datnspct.dto.Request.TaiKhoanCreateRequest;
import com.example.datnspct.dto.Request.TaiKhoanUpdateRequest;
import com.example.datnspct.dto.TaiKhoanDTO;

import java.util.List;

public interface TaiKhoanService {
    void create ( TaiKhoanCreateRequest request);
    void inactiveTaiKhoan (String taiKhoanId);
    void update (TaiKhoanUpdateRequest request);

    TaiKhoanDTO getById (String taiKhoanId);
    List<TaiKhoanDTO> getAll();

}
