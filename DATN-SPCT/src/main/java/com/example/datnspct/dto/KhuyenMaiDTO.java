package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhuyenMaiDTO {
    private Integer idKM;

    private String maKM;

    private String tenKM;

    private LocalDateTime ngayBatDau;

    private LocalDateTime ngayKetThuc;

    private Integer soLuong;

    private Boolean trangThai;
}
