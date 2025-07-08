package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienDTO {
    private Integer idNV;
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
