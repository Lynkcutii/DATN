package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamUpdateRequest {
    private Integer idSP;

    private String maSP;

    private String tenSP;

    private Boolean trangThai;
}
