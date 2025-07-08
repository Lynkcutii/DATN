package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KhuyenMaiCreateRequest {

    private String maKM;

    private String tenKM;

    private LocalDateTime ngayBatDau;

    private LocalDateTime ngayKetThuc;

    private Integer soLuong;

    private Boolean trangThai;
}
