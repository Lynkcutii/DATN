package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThuongHieuCreateRequest {
    private String maThuongHieu;
    private String tenThuongHieu;
    private Boolean trangThai;
}
