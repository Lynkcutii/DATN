package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonCTUpdateRequest {
    private Integer idHDCT;        // Id chi tiết hóa đơn

    private Integer idSP;
    private Integer idHD;
    private Integer idKM;
    private Integer idPTT;

    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;

    private Boolean trangThai;
}
