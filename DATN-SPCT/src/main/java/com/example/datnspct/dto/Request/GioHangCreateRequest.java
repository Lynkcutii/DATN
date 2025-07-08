package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHangCreateRequest {
    private String maGH;
    private Integer idKH;
    private Integer idTK;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private Boolean trangThai;
}

