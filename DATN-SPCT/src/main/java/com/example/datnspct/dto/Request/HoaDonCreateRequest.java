package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonCreateRequest {
    private String maHD;

    private Integer idKhachHang;

    private Integer idNhanVien;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private BigDecimal tongTien;

    private Boolean trangThai;
}
