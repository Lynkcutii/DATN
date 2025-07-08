package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangCTDTO {
    private Integer idGHCT;
    private Integer idGH;
    private Integer idSPCT;
    private Integer soLuong;
    private BigDecimal donGia;
}
