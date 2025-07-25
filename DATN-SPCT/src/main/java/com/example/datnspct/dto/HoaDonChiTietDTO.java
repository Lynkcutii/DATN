package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTietDTO {
    private Integer idHDCT;

    private Integer idSanPham;
    private String tenSanPham;

    private Integer idHoaDon;

    private Integer idKhuyenMai;
    private String tenKhuyenMai;

    private Integer idPhuongThucThanhToan;
    private String tenPhuongThucThanhToan;

    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;

    private Boolean trangThai;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
}
