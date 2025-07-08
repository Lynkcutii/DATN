package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHangCTCreateRequest {
    private Integer idGH;
    private Integer idSPCT;
    private Integer soLuong;
    private BigDecimal donGia;
}

