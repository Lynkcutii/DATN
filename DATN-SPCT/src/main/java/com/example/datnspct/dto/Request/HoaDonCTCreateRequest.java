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
public class HoaDonCTCreateRequest {
    private Integer idSP;          // Id sản phẩm chi tiết
    private Integer idHD;          // Id hóa đơn
    private Integer idKM;          // Id khuyến mãi (có thể null)
    private Integer idPTT;         // Id phương thức thanh toán

    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;

    private Boolean trangThai;
}
