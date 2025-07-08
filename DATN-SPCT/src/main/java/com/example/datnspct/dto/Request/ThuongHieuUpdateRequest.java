package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThuongHieuUpdateRequest {
    private Integer idThuongHieu;
    private String maThuongHieu;
    private String tenThuongHieu;
    private Boolean trangThai;
}
