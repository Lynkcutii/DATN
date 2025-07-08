package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienUpdateRequest {
    private Integer idNV; // IdNV
    private String maNV;
    private String tenNV;
    private String gioiTinh;
    private LocalDateTime ngaySinh;
    private String sdt;
    private String email;
    private String diaChi;
    private Integer idTK;
    private Boolean trangThai;
}
