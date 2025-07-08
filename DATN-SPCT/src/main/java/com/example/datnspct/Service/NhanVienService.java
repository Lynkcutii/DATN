package com.example.datnspct.Service;

import com.example.datnspct.dto.NhanVienDTO;
import com.example.datnspct.dto.Request.NhanVienCreateRequest;
import com.example.datnspct.dto.Request.NhanVienUpdateRequest;

import java.util.List;

public interface NhanVienService {
    void create ( NhanVienCreateRequest request);
    void inactiveNhanVien (String nhanVienId);
    void update (NhanVienUpdateRequest request);

    NhanVienDTO getById (String nhanVienId);
    List<NhanVienDTO> getAll();

}
