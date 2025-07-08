package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDTO {
    private Integer idSP;

    private String maSP;

    private String tenSP;

    private Boolean trangThai;
}
