package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonDTO {
    private Integer IdHD;

    private String maHD;

    private Integer idKhachHang;

    private Integer idNhanVien;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private BigDecimal tongTien;

    private Boolean trangThai;
}
