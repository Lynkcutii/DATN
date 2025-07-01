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
public class SanPhamCTCreateRequest {

    private String maSPCT;

    private Integer idSP;
    private Integer idChatLieu;
    private Integer idThuongHieu;
    private Integer idSize;
    private Integer idMauSac;

    private Integer soLuong;
    private BigDecimal gia;
    private String moTa;

    private Boolean trangThai;

}
