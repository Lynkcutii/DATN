package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangUpdateRequest {
    private Integer idKH;
    private String maKH;
    private String tenKH;
    private String gioiTinh;
    private String sdt;
    private String diaChi;
    private Boolean trangThai;
}
