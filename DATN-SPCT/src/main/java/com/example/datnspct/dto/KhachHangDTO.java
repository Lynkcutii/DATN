package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangDTO {
    private Integer idKH;
    private String maKH;
    private String tenKH;
    private String gioiTinh;
    private String sdt;
    private String diaChi;
    private TaiKhoanDTO taiKhoan; // Liên kết tới DTO TaiKhoan
    private Boolean trangThai;
}
